package com.ginger.study.jdk.java.lang.ThreadLocal;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Ginger on 18-1-12
 */

public class ThreadLocalDemo implements Runnable {

    //    AtomicInteger是一个提供原子操作的Integer类，通过线程安全的方式操作加减。
    private static AtomicInteger ai = new AtomicInteger(0);

    public void run() {
        Context context = new Context();
        context.setTransactionId(getName());
        MyThreadLocal.set(context);
        System.out.println("request[" + Thread.currentThread().getName() + "]:" + context.getTransactionId());
        new BusinessService().businessMethod();
        MyThreadLocal.unset();
    }

    private String getName() {
        return ai.getAndIncrement() + "";
    }

    public static void main(String[] args) {  // 主线程
        ThreadLocalDemo tld = new ThreadLocalDemo();
        new Thread(tld).start(); //子线程
        new Thread(tld).start(); //子线程
    }

}

/**
 * 其中引用了Context类
 */
class MyThreadLocal {
    private static final ThreadLocal<Context> userThreadLocal = new ThreadLocal<Context>();

    public static void set(Context user) {
        userThreadLocal.set(user);
    }

    public static void unset() {
        userThreadLocal.remove();
    }

    public static Context get() {
        return userThreadLocal.get();
    }

}


class BusinessService {

    public void businessMethod() {
        Context context = MyThreadLocal.get();  //从线程本地变量中取出对应的变量
        System.out.println("service[" + Thread.currentThread().getName() + "]:" + context.getTransactionId());
    }

}


/*
 * 包含业务唯一标识的类
 * */
class Context {
    private String transactionId;

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

}



