package com.ginger.study.jdk.java.util.concurrent.CallableDemo;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * Created by ginger on 17-6-25.
 * 四种类型的线程池
 */
public class ThreadPoolDemo {

    /**
     *使用Executors创建线程池
     * Executors 工厂方法
     */

    //(1) 创建可以容纳3个线程的线程池， 空闲线程不会销毁
//    If additional tasks are submitted when all threads are active,they will wait in the queue until a thread is available.
    ExecutorService fixedThreadPool = Executors.newFixedThreadPool(3);

     //(2) 线程池的大小会根据执行的任务数动态分配
//    CachedThreadPool会创建一个缓存区，将初始化的线程缓存起来。会终止并且从缓存中移除已有60秒(默认值)未被使用的线程。
    static ExecutorService cachedThreadPool = Executors.newCachedThreadPool();

    //(3) 创建单个线程的线程池，如果当前线程在执行任务时突然中断，则会创建一个新的线程替代它继续执行任务
    ExecutorService singleThreadPool = Executors.newSingleThreadExecutor();

    //(4) 效果类似于Timer定时器
    //ScheduledThreadPool是一个固定大小的线程池，与FixedThreadPool类似，执行的任务是定时执行。
    ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(3);

public static void main(String[] args)throws InterruptedException, ExecutionException{
    List<Future<String>> resultList = new ArrayList<Future<String>>();

    /**
     * 使用submit方法来提交任务，它会返回一个future，我们可以通过这个future来判断任务是否执行成功，
     * 通过future的get方法获取返回值，get方法会阻塞直到任务完成。
     */
        // 使用ExecutorService执行Callable类型的任务，并将结果保存在future变量中
        Future<String> future1 = cachedThreadPool.submit(new TaskWithResult(3));
        Future<String> future2 = cachedThreadPool.submit(new TaskWithResult(4));
        resultList.add(future1);
        resultList.add(future2);

    for (Future<String> future : resultList)
    {
        while (!future.isDone());// Future返回如果没有完成，则一直循环等待，直到Future返回完成
        {
            System.out.println(future.get()); // 打印各个线程（任务）执行的结果
        }
    }
    cachedThreadPool.shutdown(); //shutdown() 方法在终止前允许执行以前提交的任务
}


}

