package com.ginger.study.basic.EventListenTest;

/**
 * Created by Ginger on 17-9-15
 */
public class MyLister2 extends MyEvent2 implements Lister{

    @Override
    public void handle() {
        System.out.println("listen2");
    }


    // Hie

}
