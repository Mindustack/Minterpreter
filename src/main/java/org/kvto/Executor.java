package org.kvto;

import java.util.HashMap;

public class Executer {
    HashMap<String,Register> registers;
     Register constReg,counter;
    Executer(){

        registers = new HashMap<>();
        constReg = new Register("const");
        counter=new Register("@counter");
        registers.put("@counter",counter);
        memories=new HashMap<>();
    }

    HashMap<String,Memory> memories;
    public Memory getMemory(String name) {
       return memories.getOrDefault(name,new Memory(name));
    }

    Register getRegister(String name){

         try {
         var v = Double.parseDouble(name);

         return constReg.setValue(v);
         }catch (NumberFormatException e){
         return registers.getOrDefault(name,new Register(name));

         }



    }
    Module module;

    public void jump(String dest) {

        counter.setValue(module.labels.get(dest));

    }
}
