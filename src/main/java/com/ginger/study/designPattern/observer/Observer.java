package com.ginger.study.designPattern.observer;

/**
 * 可用于线程的自动启动
 * observer
 * https://www.cnblogs.com/yulinfeng/p/5874015.html
 */
public class Observer {
    /**
     * @param args
     */
    public static void main(String[] args) {
        Publish publish = new Publish();
        Subscribe subscribe = new Subscribe(publish);

        publish.setData("开始");
    }
}
