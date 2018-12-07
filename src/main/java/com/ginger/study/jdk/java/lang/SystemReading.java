package com.ginger.study.jdk.java.lang;

/**
 * Created by Ginger on 17-11-17
 */
public class SystemReading {


    /**
     *
     *
     public static native void arraycopy(Object src,  int  srcPos,
     Object dest, int destPos,
     int length);

     测试某些代码执行的时间长度:
     long startTime = System.nanoTime();
     // ... the code being measured ...
     long estimatedTime = System.nanoTime() - startTime;

     *
     */

    public static void main(String[] args){
        long startTime = System.nanoTime();
        // ... the code being measured ...
        long estimatedTime = System.nanoTime() - startTime;   // measure elapsed time
        System.out.println(estimatedTime);
    }



}
