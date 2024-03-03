package org.mindustack.minterpreter;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Minterpreter {

	public static int test(String code, double expectation, int limit, PrintStream PrintStream) {
		double value = new Executor(Parser.parse(code), PrintStream).run(limit).getRegister("a0").value;

		if (Math.abs(value - expectation) < 1e-3) {
			return 0;
		}
		;
		return 1;
	}

	public static int test(InputStream inputStream, double expectation, int limit, PrintStream PrintStream) {
		byte[] buffer = new byte[1024];
		String content = "";
		int length;
		// 从输入流中读取数据，直到没有数据为止
		try {
			while ((length = inputStream.read(buffer)) > 0) {
				// 将字节转换为字符串，拼接到内容变量中
				content += new String(buffer, 0, length);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// 关闭输入流

		return test(content, expectation, limit, PrintStream);
	}

	// String code= """
	// start:
	// op add s d 1
	// set d s
	// jump start LessThan d 5
	// set f 1
	// """;
	//
	// var module = Parser.parse(code);
	// var executor = new Executor(module);
	// executor.run(32);

	public List<Instruction> instructions = new ArrayList<>();
	public Map<String, Instruction> labels = new HashMap<>();

	public List<Instruction> parse(String source) {
		source = source.trim();
		String[] splitLines = source.split("\n");
		String label = null;
		for (String line : splitLines) {
			if (line.startsWith("#")) {
				continue;
			} else if (line.endsWith(":")) {
				label = line.replaceAll("[:]", "");

			} else if (line.equals("")) {

				continue;
			} else {
				Instruction instruction;
				String[] split = line.split(" ");
				switch (split[0]) {

					case "op": {
						instruction = new ALUinst(
								split[1],
								Variable.getVar(split[2]),
								Variable.getVar(split[3]),
								Variable.getVar(split[4]));
					}
					case "set": {
						instruction = new SetInst(Variable.getVar(split[1]),
								Variable.getVar(split[2]));
					}
					case "jump": {
						instruction = new JmpInst(split[1], split[2], Variable.getVar(split[3]),
								Variable.getVar(split[4]));
					}
						if (label != null) {
							labels.put(label, instruction);
							label = null;
						}

						instructions.add(instruction);
				}
			}

		}
		return instructions;
	}

}
