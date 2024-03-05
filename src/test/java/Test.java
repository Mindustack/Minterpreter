import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.FileNotFoundException;
import java.io.PrintStream;

import org.junit.jupiter.api.BeforeAll;
import org.mindustack.minterpreter.Minterpreter;

public class Test {

  @BeforeAll
  public static void setup() throws FileNotFoundException {
    System.setOut(new PrintStream("./output.txt"));
  }

  @org.junit.jupiter.api.Test
  public void testJmp() {

    String code = """
        jump begin always
        start:
        set ret 1
        stop
        begin:
        set ret 2
        jump start equal ret 2
        			""";
    Minterpreter i = new Minterpreter();
    double ret = i.parse(code).run().getRet();
    ;
    assertEquals(1, ret);
  }

  @org.junit.jupiter.api.Test
  public void testOp() {

    assertEquals(2, new Minterpreter()
        .parse("op add ret 1 1")
        .run()
        .getRet());
  }

  @org.junit.jupiter.api.Test
  public void testRW() {

    assertEquals(2, new Minterpreter()
        .parse("write 2 0 1\nread ret 0 1")
        .run()
        .getRet());
  }

  @org.junit.jupiter.api.Test
  public void testSingleinst() {

    assertEquals(1, new Minterpreter().parse("set ret 1 ")
        .run()
        .getRet());
  }

  @org.junit.jupiter.api.Test
  public void testParse() {

    String code = """

        //start of code

        op add a0 a0 1
        set a1 a0
        jump start lessThan a1 5
        op sub a0 a0 a1
        write 2 m0 a0
        read a1 m0 2
        stop
        			""";
    Minterpreter i = new Minterpreter();
    i.parse(code);
  }
}
