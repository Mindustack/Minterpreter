package org.mindustack.minterpreter;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class Variable {

	public String name;
	public double value;
	public static Map<String, Variable> variables = new HashMap<>();

	private Variable() {
	}

	public static Variable getVar(String name) {
		if(name==null)name="";
		if (variables.containsValue(name)) {
			return variables.get(name);
		}
		Variable v = new Variable();
		v.name = name;
		v.value = 0;
		variables.put(name, v);
		return v;
	}

	public Variable setValue(boolean value) {

		if (value) {
			this.value = 1;

		} else {
			this.value = 0;
		}

		return this;

	}

	public void setValue(Variable reg) {
		this.setValue(reg.value);
	}

	public Variable setValue(double value) {

		this.value = value;

		return this;

	}
}
