/**
 * @author      : root (root@localhost)
 * @file        : Instruction
 * @created     : Sunday Mar 03, 2024 13:01:35 CST
 */
package org.mindustack.minterpreter;

public class Instruction {
	public Variable r2;
	public Variable r1;
	public Variable ret;
	public static final String AddOperation = "add";
	public static final String SubOperation = "sub";
	public static final String MulOperation = "mul";

	public static final String OrOperation = "or";
	public static final String XorOperation = "xor";
	public static final String ShiftLeftOperation = "shl";// 移位
	public static final String ShiftRightOperation = "shr";// too
	public static final String DivOperation = "div";
	public static final String ModOpertion = "mod";

	public static final String LessThanOperation = "lessThan";
	public static final String GreaterThanOperation = "greaterThan";
	public static final String LessThanEqOperation = "lessThanEq";
	public static final String GreaterThanEqOperation = "greaterThanEq";
	public static final String EqualOperation = "equal";
	public static final String NotEqualOperation = "notEqual";

	public Instruction() {

	}
}

class ALUinst extends Instruction {
	public String op;

	public ALUinst(String op, Variable ret, Variable r1, Variable r2) {

		this.op = op;
		this.ret = ret;
		this.r1 = r1;
		this.r2 = r2;

	}
}

class SetInst extends Instruction {
	public SetInst(Variable ret, Variable r1) {
		this.ret = ret;
		this.r2 = r1;

	}
}

class StopInst extends Instruction {
	public StopInst() {
	}
}

class JmpInst extends Instruction {
	public String label;
	public String op;

	public JmpInst(String label, String op, Variable r1, Variable r2) {
		this.label = label;
		this.op = op;
		this.r1 = r2;
		this.r2 = r2;
	}
}

class ReadInst extends Instruction {
	public ReadInst(Variable data, Variable memory, Variable index) {
		this.ret = data;
		this.r1 = memory;
		this.r2 = index;
	}
}

class WriteInst extends Instruction {
	public WriteInst(Variable ret, Variable memory, Variable index) {
		this.ret = ret;
		this.r1 = memory;
		this.r2 = index;
	}
}
