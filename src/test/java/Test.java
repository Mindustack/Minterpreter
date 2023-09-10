import static org.junit.jupiter.api.Assertions.assertEquals;

import org.mindustack.minterpreter.Minterpreter;

public class Test {
    

    @org.junit.jupiter.api.Test
    public void test(){
        String code= "\t\t\t\t\t\t\t\t\t\t# -- Start function main\nstart:\nop add a0 a0 1\nset a1 a0\njump start lessThan a1 5\nop sub a0 a0 a1\nstop";
        // String code="op add sp sp -1\nstop";
        int value =Minterpreter.test(code, 0, 100, System.out);
        assertEquals( 0,value);
    }
}
