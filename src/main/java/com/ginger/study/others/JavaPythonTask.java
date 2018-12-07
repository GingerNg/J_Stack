package com.ginger.study.others;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;


public class JavaPythonTask {

    private static final Logger logger = LoggerFactory.getLogger(JavaPythonTask.class);

    public static void main(String args[]) throws Exception{
        FileInputStream fis= null;
        try {
//            Process proc = Runtime.getRuntime().exec("python  XXXX/XX.py");
//            proc.waitFor();
//            System.out.println("结束");

            //执行python脚本
//            PythonInterpreter interpreter = new PythonInterpreter();
//            interpreter.exec("days=('mod','Tue','Wed','Thu','Fri','Sat','Sun'); ");
//            interpreter.exec("print days[1];");

//            interpreter.execfile("/XXX/XXXX.py");

            // 获取文件的大小
            File f= new File("FILEPATH");
            fis= new FileInputStream(f);
            logger.info("size:"+fis.available());


        }catch (Exception e){
            logger.error("Python执行失败,{}",e);
        }

        // 执行的脚本有第三方依赖
        Process proc = Runtime.getRuntime().exec("python  /PATH/XXXX.py");
        proc.waitFor();
        System.out.println("结束");

    }
}
