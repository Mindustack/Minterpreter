package org.mindustack.minterpreter;

public class ReadInst extends Instruction {
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
