package com.ginger.study.architecture.Disturbution.Kafka;

/**
 * kafka demo
 * http://blog.csdn.net/honglei915/article/details/37563647
 * http://www.cnblogs.com/gnivor/p/5318319.html
 * http://www.cnblogs.com/liuming1992/p/6433055.html
 */
public interface KafkaProperties {
    final static String zkConnect = "XX:2181";
    final static String groupId = "group1";
    final static String topic = "topicTest";
    final static String kafkaServerURL = "XX";
    final static int kafkaServerPort = 9092;
    final static int kafkaProducerBufferSize = 64 * 1024;
    final static int connectionTimeOut = 20000;
    final static int reconnectInterval = 10000;
    final static String topic2 = "topic2";
    final static String topic3 = "topic3";
    final static String clientId = "SimpleConsumerDemoClient";
}
