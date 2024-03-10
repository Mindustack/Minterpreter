/**
 * @author      : root (root@localhost)
 * @file        : Instruction
 * @created     : Sunday Mar 03, 2024 13:01:35 CST
 */
package org.mindustack.minterpreter;

import java.util.Optional;

public abstract class Instruction {
  public Variable r2;
  public Variable r1;
  public Variable ret;
  int index;

  public String op;

  @Override
  public String toString() {
    return this.index + ": " + this.getClass().getSimpleName()
        + (this.op == null ? ""
            : (' ' + this.op + ':' ))
        + (this.ret == null ? ""
            : (' ' + this.ret.name + ':' + this.ret.value))
        + (this.r1 == null ? ""
            : (' ' + this.r1.name + ':' + this.r1.value))
        + (this.r2 == null ? "" : (' ' + this.r2.name + ':' + this.r2.value))

    ;
  };

  void execute(Minterpreter env) {
  }

}

class ALUinst extends Instruction {

  public ALUinst(int index, String op, Variable ret, Variable r1, Variable r2) {
    this.index = index;
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
    env.counter.value++;
  }
}

class SetInst extends Instruction {
  public SetInst(int index, Variable ret, Variable r1) {
    this.index = index;
    this.ret = ret;
    this.r1 = r1;

  }

  @Override
  void execute(Minterpreter env) {
    this.ret.value = r1.value;
    env.counter.value++;
  }
}

class StopInst extends Instruction {
  public StopInst(int index) {
    this.index = index;
  }

  @Override
  void execute(Minterpreter env) {
    env.counter.value=-1;
  }
}

class JmpInst extends Instruction {
  public String label;

  public JmpInst(int index, String label, String op, Variable r1, Variable r2) {
    this.index = index;
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
      env.counter.value = env.labels.get(this.label).index;
  }
}

class ReadInst extends Instruction {
  public ReadInst(int index, Variable data, Variable memory, Variable ptr) {
    this.index = index;
    this.ret = data;
    this.r1 = memory;
    this.r2 = ptr;
  }

  @Override
  void execute(Minterpreter env) {
    env.memFctr.getMem(r1).read(ret, r2);
    env.counter.value++;
  }
}

class WriteInst extends Instruction {
  public WriteInst(int index, Variable ret, Variable memory, Variable ptr) {
    this.index = index;
    this.ret = ret;
    this.r1 = memory;
    this.r2 = ptr;
  }

  @Override
  void execute(Minterpreter env) {
    env.memFctr.getMem(r1).write(ret, r2);
    env.counter.value++;
  }
}
