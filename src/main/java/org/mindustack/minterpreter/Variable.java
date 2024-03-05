package org.mindustack.minterpreter;

import java.util.HashMap;
import java.util.Map;
class VariableFactory{
  VariableFactory(){}

  public Map<String, Variable> variables = new HashMap<>();

  public Variable getVar(String name) {
    try {
      double d = Double.parseDouble(name);
      return new Const(d);
    } catch (Exception e) {
    }
    if (name == null)
      return null;
    if (variables.containsKey(name)) {
      return variables.get(name);
    }
    Variable v = new Variable();
    v.name = name;
    v.value = 0;
    if (name.startsWith("memory")) {

      v.value = Integer.parseInt(name.substring(6));
    }
    variables.put(name, v);
    return v;
  }

}
public class Variable {

  public String name;
  public double value;

  protected Variable() {
  }

  public long asInteger() {
    long i = Math.round(value);
    this.value=i;
    return i;
  }

}

class Const extends Variable {

  Const(double v) {
    this.value = v;
  }
}
