package com.ginger.study.architecture.Disturbution.Kafka;

import java.util.Properties;

import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;


public class KafkaProducer extends Thread {
    private final Producer<String, String> producer;
    private final String topic;
    private final Properties props = new Properties();

    public KafkaProducer(String topic)
    {
        props.put("serializer.class", "kafka.serializer.ByteArraySerializer");
//        props.put("serializer.class", "kafka.serializer.DefaultEncoder");  //默认是：kafka.producer.DefaultPartitioner ==> 安装key的hash进行分区
        props.put("metadata.broker.list", "XX:9092");   // bin/kafka-console-producer.sh --topic topic_1 --broker-list localhost:9092,localhost:9093
//        props.put("metadata.broker.list", "XXX:9093");
        props.put("partitioner.class","KafakTest.CidPartitioner");
        producer = new kafka.javaapi.producer.Producer<String, String>(new ProducerConfig(props));
        this.topic = topic;
    }

    @Override
    public void run() {
        int messageNo = 1;
        while (true)
        {
            if (messageNo>10){
                break;
            }
            String messageStr = new String("Message_" + messageNo);
            System.out.println("Send:" + messageStr);
            if (props.containsKey("partitioner.class")){
                producer.send(new KeyedMessage<String, String>(topic,"0",messageStr));
            }else{
                producer.send(new KeyedMessage<String, String>(topic, messageStr)); //
            }

            messageNo++;
            try {
                sleep(3000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
}
