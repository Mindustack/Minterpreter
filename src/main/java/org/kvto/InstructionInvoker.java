package org.kvto;



public abstract class Instruction {

   private final String Identifier;
   protected static String[] instruction;

    protected Instruction(String identifier) {
        Identifier = identifier;
    }

    static boolean check(String[] inst){
        if (inst[0].equals("set")){
            instruction=inst;
            return true;
        }
            return false;

    }
    abstract static void execute(Executer executer);

}
 class setInst extends  Instruction{


    setInst() {
        super("set");
    }

    @Override
   static void  execute(Executer executer) {

        var r1 = executer.getRegister(instruction[1]);
        var r2 = executer.getRegister(instruction[2]);
        r1.setValue(r2);
    }
}
class opInst extends  Instruction{


    opInst() {
        super("op");
    }

    @Override
    void execute(Executer executer) {

        var ret = executer.getRegister(instruction[1]);
        String op=instruction[2];
        var r1 = executer.getRegister(instruction[3]);
        var r2 = executer.getRegister(instruction[4]);

        switch (op){
            case "add" ->{
                ret.setValue(r1.value+r2.value);
                return;
            }

        }

    }
}
class jumpInst extends  Instruction{


    jumpInst() {
        super("jump");
    }

    @Override
    void execute(Executer executer) {

        var dest = instruction[1];
        String op=instruction[2];
        if (op.equals("always")){
            executer.jump(dest);
            return;
        }
        var r1 = executer.getRegister(instruction[3]);
        var r2 = executer.getRegister(instruction[4]);

        switch (op){
            case "LessThan" ->{

                if (r1.value<r2.value){
            executer.jump(dest);

                }
                return;
            }

        }

    }
}
class readInst extends  Instruction{


    readInst() {
        super("read");
    }

    @Override
    void execute(Executer executer) {

                var ret = executer.getRegister(instruction[1]);

        var m1 = executer.getMemory(instruction[2]);
        var index = executer.getRegister(instruction[3]);
       ret.setValue(m1.read(index.value));
    }
}
class writeInst extends  Instruction{


    writeInst() {
        super("write");
    }

    @Override
    void execute(Executer executer) {

                var v = executer.getRegister(instruction[1]);

        var m1 = executer.getMemory(instruction[2]);
        var index = executer.getRegister(instruction[3]);
       m1.write(index.value,v.value);
    }
}