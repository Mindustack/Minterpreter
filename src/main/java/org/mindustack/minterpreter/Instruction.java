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
