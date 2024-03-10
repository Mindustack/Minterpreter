package org.mindustack.minterpreter;

public class Variable {

  public String name;
  public double value;

  protected Variable(String name, double value) {
    this.name = name;
    this.value = value;
  }

  public long asInteger() {
    long i = Math.round(value);
    this.value = i;
    return i;
  }

}

class Const extends Variable {
  Const(double v) {
    super("_const", v);
  }
}
