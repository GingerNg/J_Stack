package com.ginger.study.jdk.java.util.concurrent.CallableDemo;

import org.python.modules.time.Time;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * Created by ginger on 17-7-12.
 */

/**
 * Runnable是执行工作的独立任务，但是它不返回任何值。
 * 在Java SE5中引入的Callable是一种具有类型参数的泛型，它的类型参数表的是从方法call()中返回的值，并且必须使用ExecutorServices.submit()方法调用它
 *

 */
public class CallableTest {
    public CallableTest(Callable callable){
    }

    public static void main(String[] args){
        new CallableTest(()->2);

        /**
         * 使用submit方法来提交任务，它会返回一个future，我们可以通过这个future来判断任务是否执行成功，
         * 通过future的get方法获取返回值，get方法会阻塞直到任务完成。
         */
        ExecutorService exec= Executors.newCachedThreadPool();
        List<Future<String>> results=new ArrayList<Future<String>>();

        for(int i=0;i<5;i++) {
            results.add(exec.submit(new TaskWithResult(i)));
        }

        System.out.println("this is main");

        for(Future<String> fs :results) {
            try {
                System.out.println(fs.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }


        exec.shutdown();
    }
    }


class TaskWithResult implements Callable<String> {
    private int id;
    public TaskWithResult(int id) {
        this.id=id;
    }

    @Override
    public String call() throws Exception {
        Time.sleep(10);
        return "result of TaskWithResult "+id;
    }
}


