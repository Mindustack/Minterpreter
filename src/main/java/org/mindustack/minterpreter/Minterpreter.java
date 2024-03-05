package org.mindustack.minterpreter;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Minterpreter {
  public List<Instruction> instructions = new ArrayList<>();
  public Map<String, Instruction> labels = new HashMap<>();
  public VariableFactory varFctr;
  public MemoryFactory memFctr;

  public Minterpreter() {
    this.memFctr = new MemoryFactory();
    this.varFctr = new VariableFactory();
  }

  public Minterpreter parse(String source) {

    source = source.trim();
    String[] splitLines = source.split("\n");
    String[] split;
    String label = null;
    for (String line : splitLines) {
      if (line.startsWith("#") || line.startsWith("//")) {
        continue;
      } else if (line.endsWith(":")) {
        label = line.replaceAll("[:]", "");

      } else if (line.equals("")) {

        continue;
      } else {
        Instruction instruction = null;
        split = line.split(" ");

        // System.out.println(Arrays.toString(split));
        switch (split[0]) {

          case "op" -> {
            //System.out.println(Arrays.toString(split));
            instruction = new ALUinst(
                split[1],
                this.varFctr.getVar(split[2]),
                this.varFctr.getVar(split.length < 4 ? null : split[3]),
                this.varFctr.getVar(split.length < 5 ? null : split[4]));
          }
          case "set" -> {
            instruction = new SetInst(this.varFctr.getVar(split[1]),
                this.varFctr.getVar(split[2]));
          }
          case "stop" -> {
            instruction = new StopInst();
          }
          case "jump" -> {

            instruction = new JmpInst(split[1],
                split[2],
                this.varFctr.getVar(split.length < 4 ? null : split[3]),
                this.varFctr.getVar(split.length < 5 ? null : split[4]));
          }
          case "write" -> {
  //System.out.println(Arrays.toString(split));
            instruction = new WriteInst(
                this.varFctr.getVar(split[1]),
                this.varFctr.getVar(split[2]),
                this.varFctr.getVar(split[3]));
          }
          case "read" -> {
  //System.out.println(Arrays.toString(split));
            instruction = new ReadInst(
                this.varFctr.getVar(split[1]),
                this.varFctr.getVar(split[2]),
                this.varFctr.getVar(split[3]));
          }
          default -> {
            throw new RuntimeException("invalid inst:" + Arrays.toString(split));
          }
        }

        if (label != null) {

          labels.put(label, instruction);
          label = null;
        }
        instructions.add(instruction);
      }
    }

    return this;
  }

  Variable counter;

  public Minterpreter run() {
    Instruction inst;

    counter = this.varFctr.getVar("@counter");
    int step = 0;
    while (step < 1024) {

      inst = instructions.get((int) counter.asInteger());
      inst.execute(this);
      step++;
      counter.value++;
      if (counter.value >= instructions.size())
        break;
    }
    return this;

  }

  public double getRet() {
    return this.varFctr.getVar("ret").value;
  }
}
