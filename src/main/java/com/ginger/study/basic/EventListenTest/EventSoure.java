package com.ginger.study.basic.EventListenTest;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Ginger on 17-9-15
 */
public class EventSoure {

    private List<Lister> listers = new LinkedList<>();
    public void init(){
        listers.add(new MyLister1());
        listers.add(new MyLister2());
    }

    public void trigger(Event event){

        for (Lister ls:listers) {
            if (event.getClass().getName().equals(ls.getClass().getSuperclass().getName())){ // 通过反射
                ls.handle();
            }
        }
    }



}
