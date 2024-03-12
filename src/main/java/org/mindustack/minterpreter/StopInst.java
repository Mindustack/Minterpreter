package org.mindustack.minterpreter;

public class StopInst extends Instruction {
  public StopInst(int index) {
    this.index = index;
  }

  @Override
  void execute(Minterpreter env) {
    env.counter.value=-1;
  }
}
