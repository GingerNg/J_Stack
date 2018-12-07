package com.ginger.study.jdk.java;

/**
 */
public class elseIfTest {


    public static void main(String[] args){


        String str = "0.1";
//        System.out.println(Long.parseLong(str));
        double dl = Double.parseDouble(str);
        System.out.println(Double.parseDouble(str));


        int flag = 10;
        if(flag<100){
            System.out.println("if");
        }else if(flag<20){
            System.out.println("else if");
        }else{
            System.out.println("else");
        }
    }
}
