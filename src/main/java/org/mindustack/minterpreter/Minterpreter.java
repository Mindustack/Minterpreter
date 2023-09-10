package org.mindustack.minterpreter;


import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;

public class Minterpreter {


    public static int test(String code,double expectation,int limit,PrintStream PrintStream){
        double value = new Executor(Parser.parse(code),PrintStream).run(limit).getRegister("a0").value;

        
        if ( Math.abs(value - expectation) < 1e-3) {
            return 0;
        };
        return 1;
    }
    public static int test(InputStream inputStream,double expectation,int limit,PrintStream PrintStream){
        byte[] buffer = new byte[1024];
        String content="";
            int length;
            //从输入流中读取数据，直到没有数据为止
            try {
                while ((length = inputStream.read(buffer)) > 0) {
                    //将字节转换为字符串，拼接到内容变量中
                    content += new String(buffer, 0, length);
                }
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            
            //关闭输入流
          
            return test(content, expectation, limit, PrintStream);
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
