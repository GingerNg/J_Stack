package com.ginger.study.jdk.java.util.concurrent;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by ginger on 17-6-25.
 * http://blog.csdn.net/defonds/article/details/44021605/
 */
public class blockingQueueTest {

//    数组阻塞队列     有界
    BlockingQueue queue = new ArrayBlockingQueue(1024);

//    DelayQueue 对元素进行持有直到一个特定的延迟到期


//    LinkedBlockingQueue链阻塞队列
BlockingQueue outbound = new LinkedBlockingQueue();

//    同步队列 SynchronousQueue  内部同时只能够容纳单个元素



}
