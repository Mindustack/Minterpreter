package org.mindustack.minterpreter;

import java.io.IOException;
import java.io.InputStream;

public class Parser {
    static Module parse(String code) {

        Module module = new Module();

        code=code.trim();
        String[] splitLines = code.split("\n");


        for (String line : splitLines) {
            if (line.startsWith("#")) {
                continue;
            } else if (line.endsWith(":")) {
                module.labels.put(line.replaceAll("[:]", ""), module.insts.size());
            }else if(line.equals("")){

                continue;
            } else {
                String[] split = line.split(" ");
                module.insts.add(split);
//
            }
        }

        return module;
    }
    static Module parse(InputStream inputStream) throws IOException {

        byte[] buffer = new byte[1024];
        String content="";
            int length;
            //从输入流中读取数据，直到没有数据为止
            while ((length = inputStream.read(buffer)) > 0) {
                //将字节转换为字符串，拼接到内容变量中
                content += new String(buffer, 0, length);
            }
            
            //关闭输入流
            inputStream.close();
            return parse(content);
    }


}
