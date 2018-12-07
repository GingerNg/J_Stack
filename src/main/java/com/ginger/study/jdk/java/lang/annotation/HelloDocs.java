package com.ginger.study.jdk.java.lang.annotation;

/**
 * Created by Ginger on 17-9-25
 */
public class HelloDocs extends HelloDoc {   // 子类继承父类的注解
    public static void main(String[] args){
        HelloDocs helloDocs = new HelloDocs();
        System.out.println(helloDocs.getClass().getAnnotations()[0]);

    }
}
