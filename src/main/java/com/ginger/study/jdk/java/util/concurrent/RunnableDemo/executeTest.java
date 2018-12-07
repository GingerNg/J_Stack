package com.ginger.study.jdk.java.util.concurrent.RunnableDemo;

import java.util.concurrent.*;

/**
 * Created by ginger on 17-6-23.
 */
public class executeTest
{
    public static void main(String[] args) throws InterruptedException
    {
        BlockingQueue<Runnable> workQueue=new LinkedBlockingQueue();   // 阻塞队列
        /**(1)
         * 创建线程池
         * class ThreadPoolExecutor extends AbstractExecutorService
         */

        ThreadPoolExecutor poolExecutor=new ThreadPoolExecutor(2, 3, 60, TimeUnit.SECONDS, workQueue);

        /**(2)
         * class ScheduledThreadPoolExecutor
         * extends ThreadPoolExecutor
         * implements ScheduledExecutorService
         * 执行定时及周期性任务
         * Timer timer = new Timer();
         * timer.schedule();
         */
        ScheduledThreadPoolExecutor poolExecutor2=new ScheduledThreadPoolExecutor(2);

        /**
         * 使用execute向线程池提交任务
         */
        poolExecutor.execute(new Task1());
//        Thread.sleep(2000);
        poolExecutor.execute(new Task2());

        poolExecutor.shutdown();
    }
}

class Task1 implements Runnable
{

    public void run()
    {
        try {
            System.out.println("暂行200ms");
            Thread.sleep(200);
            System.out.println("执行任务1");
        }catch(Exception e){

        }

    }
}

class Task2 implements Runnable
{

    public void run()
    {
        System.out.println("执行任务2");
    }
}
