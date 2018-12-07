package com.ginger.study.architecture.Disturbution.Kafka;

public class KafkaConsumerProducerDemo {
    public static void main(String[] args)
    {
        // 启动两个线程：
        KafkaProducer producerThread = new KafkaProducer(KafkaProperties.topic);
        producerThread.start();

        KafkaConsumer consumerThread = new KafkaConsumer(KafkaProperties.topic);
        consumerThread.start();
    }
}
