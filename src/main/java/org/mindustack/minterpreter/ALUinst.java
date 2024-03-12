package org.mindustack.minterpreter;

public class ALUinst extends Instruction {

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
