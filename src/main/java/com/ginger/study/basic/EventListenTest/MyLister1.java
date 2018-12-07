package com.ginger.study.basic.EventListenTest;

/**
 * Created by Ginger on 17-9-15
 */
public class MyLister1 extends MyEvent1 implements Lister  {

//    private MyEvent1 myEvent1 = new MyEvent1();

    public void handle(){
        System.out.println("listen1");
    }


    String template = "";  //pre

}
