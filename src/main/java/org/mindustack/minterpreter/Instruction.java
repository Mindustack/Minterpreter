/**
 * @author      : root (root@localhost)
 * @file        : Instruction
 * @created     : Sunday Mar 03, 2024 13:01:35 CST
 */
package org.mindustack.minterpreter;

public abstract class Instruction {
  public Variable r2;
  public Variable r1;
  public Variable ret;

  abstract void execute(Minterpreter env);

}

class ALUinst extends Instruction {
  public String op;

  public ALUinst(String op, Variable ret, Variable r1, Variable r2) {

    this.op = op;
    this.ret = ret;
    this.r1 = r1;
    this.r2 = r2;

  }

  @Override
  void execute(Minterpreter env) {
    switch (this.op) {

      case "add" -> {
        // System.out.println(r1.value);
        ret.value = r1.value + r2.value;
      }
      case "sub" -> {

        ret.value = r1.value - r2.value;
      }
      case "mul" -> {
        ret.value = r1.value * r2.value;
      }
      case "div" -> {
        ret.value = r1.value / r2.value;
      }
      case "mod" -> {
        ret.value = r1.value % r2.value;
      }
      case "equal" -> {
        ret.value = (double) (r1.value == r2.value ? 1 : 0);
      }
      case "notequal" -> {
        ret.value = (double) (r1.value != r2.value ? 1 : 0);
      }
      case "lessThan" -> {
        ret.value = (double) (r1.value < r2.value ? 1 : 0);
      }
      case "greaterThan" -> {
        ret.value = (double) (r1.value > r2.value ? 1 : 0);
      }
      case "lessThanEq" -> {
        ret.value = (double) (r1.value <= r2.value ? 1 : 0);
      }
      case "greaterThanEq" -> {
        ret.value = (double) (r1.value >= r2.value ? 1 : 0);
      }
      case "and" -> {
        ret.value = (double) ((int) r1.value & (int) r2.value);
      }
      case "or" -> {
        ret.value = (double) ((int) r1.value | (int) r2.value);
      }
      case "xor" -> {
        ret.value = (double) ((int) r1.value ^ (int) r2.value);
      }
      default -> {
        throw new RuntimeException("invalid operation:" + op);
      }
    }
  }
}

class SetInst extends Instruction {
  public SetInst(Variable ret, Variable r1) {
    this.ret = ret;
    this.r1 = r1;

  }

  @Override
  void execute(Minterpreter env) {
    this.ret.value = r1.value;
  }
}

class StopInst extends Instruction {
  public StopInst() {
  }

  @Override
  void execute(Minterpreter env) {
    (env.counter.value)--;
  }
}

class JmpInst extends Instruction {
  public String label;
  public String op;

  public JmpInst(String label, String op, Variable r1, Variable r2) {
    this.label = label;
    this.op = op;
    this.r1 = r2;
    this.r2 = r2;
  }

  @Override
  void execute(Minterpreter env) {
    boolean jmp = false;
    switch (this.op) {
      case "always" -> {
        jmp = true;
      }
      case "equal" -> {
        jmp = (r1.value == r2.value);
      }
      case "notequal" -> {
        jmp = (r1.value != r2.value);
      }
      case "lessThan" -> {
        jmp = (r1.value < r2.value);
      }
      case "greaterThan" -> {
        jmp = (r1.value > r2.value);
      }
      case "lessThanEq" -> {
        jmp = (r1.value <= r2.value);
      }
      case "greaterThanEq" -> {
        jmp = (r1.value >= r2.value);
      }
      case "and" -> {
        jmp = (r1.value * r2.value == 0);
      }
      case "or" -> {
        jmp = (!(r1.value + r2.value == 0));
      }
      case "xor" -> {
        jmp = (r1.value + r2.value == 1);
      }
      default -> {
        throw new RuntimeException("invalid condition:" + op);
      }
    }
    if (jmp)
      env.counter.value = env.instructions.indexOf(env.labels.get(this.label)) - 1;
  }
}

class ReadInst extends Instruction {
  public ReadInst(Variable data, Variable memory, Variable index) {
    this.ret = data;
    this.r1 = memory;
    this.r2 = index;
  }

  @Override
  void execute(Minterpreter env) {
    env.memFctr.getMem(r1).read(ret, r2);
    //System.out.println(r1.value + "r" + r2.value);
  }
}

class WriteInst extends Instruction {
  public WriteInst(Variable ret, Variable memory, Variable index) {
    this.ret = ret;
    this.r1 = memory;
    this.r2 = index;
  }

  @Override
  void execute(Minterpreter env) {
    env.memFctr.getMem(r1).write(ret, r2);
  }
}
