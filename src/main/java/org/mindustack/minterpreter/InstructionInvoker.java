package org.mindustack.minterpreter;

public abstract class InstructionInvoker {
    public static final String AddOperation = "add";
    public static final String SubOperation = "sub";
    public static final String MulOperation = "mul";


    public static final String OrOperation = "or";
    public static final String XorOperation = "xor";
    public static final String ShiftLeftOperation = "shl";//移位
    public static final String ShiftRightOperation = "shr";//too
    public static final String DivOperation = "div";
    public static final String ModOpertion = "mod";


    public static final String LessThanOperation = "lessThan";
    public static final String GreaterThanOperation = "greaterThan";
    public static final String LessThanEqOperation = "lessThanEq";
    public static final String GreaterThanEqOperation = "greaterThanEq";
    public static final String EqualOperation = "equal";
    public static final String NotEqualOperation = "notEqual";

    protected static String[] instruction;
    private final String Identifier;

    protected InstructionInvoker(String identifier) {
        Identifier = identifier;
    }

    boolean check(String[] inst) {
        if (inst[0].equals(Identifier)) {
            instruction = inst;
            return true;
        }
        return false;

    }

    abstract void execute(Executor executer);

}

class setInstInvoker extends InstructionInvoker {


    setInstInvoker() {
        super("set");
    }

    @Override
    void execute(Executor executer) {

        var r1 = executer.getRegister(instruction[1]);
        var r2 = executer.getRegister(instruction[2]);
        r1.setValue(r2);
    }
}

class opInstInvoker extends InstructionInvoker {


    opInstInvoker() {
        super("op");
    }

    @Override
    void execute(Executor executer) {


        String op = instruction[1];
        var ret = executer.getRegister(instruction[2]);
        var r1 = executer.getRegister(instruction[3]);
        var r2 = executer.getRegister(instruction[4]);

        switch (op) {
            case AddOperation:
                ret.setValue(r1.value + r2.value);
                break;

            case SubOperation:
                ret.setValue(r1.value - r2.value);
                break;

            case MulOperation:
                ret.setValue(r1.value * r2.value);
                break;

            case DivOperation:
                ret.setValue(r1.value / r2.value);
                break;

            case ModOpertion:
                ret.setValue(r1.value % r2.value);
                break;

            case LessThanOperation:
                ret.setValue(r1.value < r2.value);
                break;

            case LessThanEqOperation:
                ret.setValue(r1.value <= r2.value);
                break;

            case GreaterThanOperation:
                ret.setValue(r1.value > r2.value);
                break;

            case GreaterThanEqOperation:
                ret.setValue(r1.value >= r2.value);
                break;

            case EqualOperation:
                ret.setValue(r1.value == r2.value);
                break;

            case NotEqualOperation:
                ret.setValue(r1.value != r2.value);
                break;

            default:
                // 处理未知操作符的逻辑
                break;
        }


    }
}

class jumpInstInvoker extends InstructionInvoker {


    jumpInstInvoker() {
        super("jump");
    }

    @Override
    void execute(Executor executer) {

        var dest = instruction[1];
        String op = instruction[2];
        if (op.equals("always")) {
            executer.jump(dest);
            return;
        }
        var r1 = executer.getRegister(instruction[3]);
        var r2 = executer.getRegister(instruction[4]);

        switch (op) {
            case LessThanOperation:
                if (r1.value < r2.value) {
                    executer.jump(dest);
                }
                break;

            case LessThanEqOperation:
                if (r1.value <= r2.value) {
                    executer.jump(dest);
                }
                break;

            case GreaterThanOperation:
                if (r1.value > r2.value) {
                    executer.jump(dest);
                }
                break;

            case GreaterThanEqOperation:
                if (r1.value >= r2.value) {
                    executer.jump(dest);
                }
                break;

            case EqualOperation:
                if (r1.value == r2.value) {
                    executer.jump(dest);
                }
                break;

            case NotEqualOperation:
                if (r1.value != r2.value) {
                    executer.jump(dest);
                }
                break;

            default:
                // 处理未知操作符的逻辑
                break;
        }


    }
}

class readInstInvoker extends InstructionInvoker {


    readInstInvoker() {
        super("read");
    }

    @Override
    void execute(Executor executer) {

        var ret = executer.getRegister(instruction[1]);

        var m1 = executer.getMemory(instruction[2]);
        var index = executer.getRegister(instruction[3]);
        ret.setValue(m1.read(index.value));
    }
}

class writeInstInvoker extends InstructionInvoker {


    writeInstInvoker() {
        super("write");
    }

    @Override
    void execute(Executor executer) {

        var v = executer.getRegister(instruction[1]);

        var m1 = executer.getMemory(instruction[2]);
        var index = executer.getRegister(instruction[3]);
        m1.write(index.value, v.value);
    }
}