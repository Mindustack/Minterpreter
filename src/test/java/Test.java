import static org.junit.jupiter.api.Assertions.assertEquals;

import org.mindustack.minterpreter.Minterpreter;

public class Test {
    

    @org.junit.jupiter.api.Test
    public void test(){
        // String code= "start:\nop add a0 a0 1\nset a1 a0\njump start LessThan a1 5\nop sub a0 a1\nend";
        String code="op add sp sp -1\nstop";
        int value =Minterpreter.test(code, 0, 5, System.out);
        assertEquals(value,0);
    }
}
