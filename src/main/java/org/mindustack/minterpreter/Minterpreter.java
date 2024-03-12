package org.mindustack.minterpreter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
public class Minterpreter {
  public List<Instruction> instructions = new ArrayList<>();
  public Map<String, Instruction> labels = new HashMap<>();
  public VariableFactory varFctr;
  public MemoryFactory memFctr;
  public Minterpreter() {
    this.memFctr = new MemoryFactory();
    this.varFctr = new VariableFactory();

    logger.info("minterpreter started");
  }

  public static Logger logger;
  static {
logger = Logger.getLogger("Minterpreter");
    try {
      FileHandler fileHandler = new FileHandler("log.txt");
      logger.addHandler(fileHandler);
      fileHandler.setFormatter(new SimpleFormatter());
    } catch (SecurityException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * 
   * @param source mindustry logic plain code
   * @return itself for continue use
   */
  public Minterpreter parse(String source) {
    source = source.trim();
    String[] splitLines = source.split("\n");
    String[] split;
    String label = null;
    int count = 0;
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

        switch (split[0]) {

          case "op" -> {
            instruction = new ALUinst(
                count,
                split[1],
                this.varFctr.getVar(split[2]),
                this.varFctr.getVar(split.length < 4 ? null : split[3]),
                this.varFctr.getVar(split.length < 5 ? null : split[4]));
          }
          case "set" -> {
            instruction = new SetInst(
                count,
                this.varFctr.getVar(split[1]),
                this.varFctr.getVar(split[2]));
          }
          case "stop" -> {
            instruction = new StopInst(count);
          }
          case "jump" -> {

            if (split[2].matches("[0-9]*"))
              logger
                  .warning("minterpreter does not support number jmp ,are you using number as label? that's not nice");
            instruction = new JmpInst(count,
                split[1],

                split[2],
                this.varFctr.getVar(split.length < 4 ? null : split[3]),
                this.varFctr.getVar(split.length < 5 ? null : split[4]));
          }
          case "write" -> {
            instruction = new WriteInst(count,
                this.varFctr.getVar(split[1]),
                this.varFctr.getVar(split[2]),
                this.varFctr.getVar(split[3]));
          }
          case "read" -> {
            instruction = new ReadInst(count,
                this.varFctr.getVar(split[1]),
                this.varFctr.getVar(split[2]),
                this.varFctr.getVar(split[3]));
          }
          default -> {
            logger.severe("can't parse: " + line);
            throw new RuntimeException("invalid inst:" + Arrays.toString(split));
          }

        }

        if (label != null) {

          labels.put(label, instruction);
          label = null;
        }
        instructions.add(instruction);
        count++;
        logger.info("parsed: " + line + " as: " + instruction.toString());
      }
    }

    return this;
  }

  Variable counter;

  /**
   * run logic code for limited steps default 1024
   * 
   * @return
   */
  public Minterpreter run() {
    return run(1024);
  }

  /**
   * run logic code for limited steps default 1024
   * 
   * @param stepLimit
   * @return
   */
  public Minterpreter run(int stepLimit) {
    Instruction inst;

    counter = this.varFctr.getVar("@counter");
    int step = 0;
    while (step < stepLimit) {

      inst = instructions.get((int) counter.asInteger());
      inst.execute(this);
      logger.info(inst.toString());
      step++;
      if (counter.value >= instructions.size()||counter.value<0)
        break;
    }
    return this;

  }

  /**
   * @return return the value in 'ret' variable to vertify
   */
  public double getRet() {
    return this.varFctr.getVar("ret").value;
  }
}
