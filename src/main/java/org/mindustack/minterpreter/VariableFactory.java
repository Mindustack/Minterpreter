package org.mindustack.minterpreter;

import java.util.HashMap;
import java.util.Map;

class VariableFactory {
  static Variable nullVariable = new Variable("_null", 0);

  public Map<String, Variable> variables = new HashMap<>();
  VariableFactory() {
  }

  public Variable getVar(String name) {
    try {
      double d = Double.parseDouble(name);
      return new Const(d);
    } catch (Exception e) {
    }
    if (name == null)
      //return nullVariable;
      return null;
    if (variables.containsKey(name)) {
      return variables.get(name);
    }
    Variable v = new Variable(name, 0);
    if (name.startsWith("memory")) {

      v.value = Integer.parseInt(name.substring(6));
    }
    variables.put(name, v);
    return v;
  }

}
