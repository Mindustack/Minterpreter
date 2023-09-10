package org.mindustack.minterpreter;


import java.io.PrintStream;

public class Minterpreter {


    public static int test(String code,double expectation,int limit,PrintStream PrintStream){
        double value = new Executor(Parser.parse(code),PrintStream).run(limit).getRegister("a0").value;
        if ( Math.abs(value - expectation) < 1e-3) {
            return 0;
        };
        return 1;
    }
   

//        String code= """
//                start:
//                op add s d 1
//                set d s
//                jump start LessThan d 5
//                set f 1
//                """;
//
//        var module = Parser.parse(code);
//        var executor = new Executor(module);
//        executor.run(32);


    
}