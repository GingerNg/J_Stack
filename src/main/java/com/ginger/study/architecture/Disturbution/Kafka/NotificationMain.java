package com.ginger.study.architecture.Disturbution.Kafka;

/**
 * Created by Ginger on 17-11-9
 */
public class NotificationMain {

    public static void main(String[] args){

        KafkaNotification notification = new KafkaNotification();
        notification.init();
        notification.sendMessage();

    }

}
