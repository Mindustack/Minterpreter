package org.mindustack.minterpreter;

public class SetInst extends Instruction {
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
