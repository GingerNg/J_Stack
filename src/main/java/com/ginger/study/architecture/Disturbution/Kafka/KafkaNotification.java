package com.ginger.study.architecture.Disturbution.Kafka;

import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;

import java.util.Properties;

/**
 * Created by Ginger on 17-11-9
 */
public class KafkaNotification implements Notification{

    private Producer<String, String> producer;
    private String topic;
    private String messageStr;
    Properties props = new Properties();


    public void init(){
        props.put("serializer.class", "kafka.serializer.StringEncoder");
//        props.put("serializer.class", "kafka.serializer.DefaultEncoder");  //默认是：kafka.producer.DefaultPartitioner ==> 安装key的hash进行分区
        props.put("metadata.broker.list", "XXX:9092");   // bin/kafka-console-producer.sh --topic topic_1 --broker-list localhost:9092,localhost:9093
//        props.put("partitioner.class","KafakTest.CidPartitioner");
        producer = new Producer<String, String>(new ProducerConfig(props));
    }

    public void sendMessage(){
        producer.send(new KeyedMessage<String, String>(topic,messageStr));
    }

}
