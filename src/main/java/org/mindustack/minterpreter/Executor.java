package org.mindustack.minterpreter;

import java.util.ArrayList;
import java.util.HashMap;

public class Executor {
    final Variable constReg = new Variable("const");
    private final Variable stepper;
    HashMap<String, Variable> registers = new HashMap<>();
    Variable counter;
    HashMap<String, Memory> memories;
    Module module;

    ArrayList<InstructionInvoker> instructionInvokers;
    boolean jumped = false;
    int steps;

    Executor(Module module) {

        counter = new Variable("@counter");
        registers.put("@counter", counter);
        stepper = new Variable("@steper");
        stepper.setValue(1);
//        registers.put("@stepper", stepper);
        memories = new HashMap<>();
        this.module = module;


        instructionInvokers = new ArrayList<>();
        instructionInvokers.add(new opInstInvoker());
        instructionInvokers.add(new jumpInstInvoker());
        instructionInvokers.add(new setInstInvoker());
        instructionInvokers.add(new readInstInvoker());
        instructionInvokers.add(new writeInstInvoker());

        counter.setValue(0);
        steps = 0;
    }

    public Memory getMemory(String name) {
        return memories.getOrDefault(name, new Memory(name));
    }

    Variable getRegister(String name) {
        Variable result;

        try {
            var v = Double.parseDouble(name);

            result = constReg.setValue(v);
        } catch (NumberFormatException e) {
            if (registers.containsKey(name)) {
                result = registers.get(name);
            } else {
                result = new Variable(name);
                registers.put(name, result);
            }


        }


        return result;
    }

    public void jump(String des) {

        counter.setValue(module.labels.get(des));
        jumped = true;
    }

    public void run() {

        run(1024);
    }

    public void run(int steps) {
        System.out.println(" * :logic to be run");

        this.steps = steps;
        while (this.steps > 0) {
            execute();
        }
    }

    void execute() {


        String[] inst = module.insts.get((int) Math.round(counter.value));

        for (InstructionInvoker instructionInvoker : this.instructionInvokers) {
            if (instructionInvoker.check(inst)) {
                instructionInvoker.execute(this);
                break;
            }
        }

        if (jumped) {
            jumped = false;
        } else {
            counter.value++;

        }
        if (counter.value >= module.insts.size()) {
            counter.setValue(0);
        }
        System.out.println(dump());
        stepper.value++;
        steps--;
    }

    String dump() {

        var stringBuilder = new StringBuilder();


        stringBuilder.append("---------------------<").append(((int) stepper.value)).append(">\n");
        ArrayList<String[]> insts = module.insts;
        for (int i = 0, instsSize = insts.size(); i < instsSize; i++) {
            String[] inst = insts.get(i);

            stringBuilder.append(i).append(' ');
            if (i == (int) Math.round(counter.value)) {
                stringBuilder.append('*');
                for (String s : inst) {
                    stringBuilder.append(s).append(" ");
                }


                stringBuilder.append('\n');
                for (Variable variable : this.registers.values()) {
                    stringBuilder.append("\t\t\t\t\t\t\t\t\t").append(variable.name).append(':').append(variable.value).append('\n');
                }
            } else {
                stringBuilder.append(' ');
                for (String s : inst) {
                    stringBuilder.append(s).append(" ");
                }
                stringBuilder.append('\n');
            }


        }


        return stringBuilder.toString();
    }
}
