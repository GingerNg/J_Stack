package com.ginger.study.architecture.Disturbution.ActiveMQ;

/**
 * Created by Ginger on 17-12-10
 * ActiveMQ失效转移（Failover）
 Consumer集群（消费者集群）和Broker集群（消息服务器集群）
 * 队列消费者，
 * 主要是：
 * 1.保证如果某一个消费者死亡了，任何它没有确认完的消息会被重传别的正常的消费者来消费； (消息重传机制)
 * 2.如果一个消费者消费消息过快，就可以比别的消费者得到更多的消息；
 * 3.如果一个消费者消费消息过慢，它就会被少得到消息
 * http://manzhizhen.iteye.com/blog/2105572
 */
public class ActiveMQDemo {
}
