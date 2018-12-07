package com.ginger.study.others;

import java.io.*;

/**
 * Created by Ginger on 17-12-28
 */
public class Tail_F {

    public static void main(String[] args) throws Exception{
            String srcFilename = "XXXX/log.txt";
            String charset = "GBK";
            InputStream fileInputStream = new FileInputStream(srcFilename);
//            fileInputStream.skip(10); // skip n bytes
            Reader fileReader = new InputStreamReader(fileInputStream, charset);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String singleLine;
            while(true){
                if( (singleLine = bufferedReader.readLine()) != null ){
                    System.out.println(singleLine);
                    continue;
                }
//                try {
//                    Thread.sleep(1000L);
//                } catch (InterruptedException e) {
//                    Thread.currentThread().interrupt();
//                    break;
//                }
            }
//            bufferedReader.close();
        }

}
