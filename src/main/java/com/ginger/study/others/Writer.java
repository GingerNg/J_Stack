package com.ginger.study.others;

import java.io.*;

/**
 * Created by Ginger on 17-12-28
 */
public class Writer {
    public static void main(String[] args) throws Exception{
        String srcFilename = "XXXX/log.txt";
        String charset = "GBK";
        OutputStream fileoutputStream = new FileOutputStream(srcFilename);
//            fileInputStream.skip(10); // skip n bytes
        OutputStreamWriter writer = new OutputStreamWriter(fileoutputStream, charset);
        BufferedWriter bufferedWriter = new BufferedWriter(writer);
        int singleLine = 0;
        while(true){
//            if( (singleLine = bufferedReader.readLine()) != null ){
//                System.out.println(singleLine);
//                continue;
//            }
            singleLine ++;
                try {
                    bufferedWriter.write(singleLine+"\n");
                    bufferedWriter.flush();
                    System.out.println(singleLine);
                    Thread.sleep(1000L);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    break;
                }
    }
    }
}
