package com.ginger.study.basic.EventListenTest;

import java.util.*;

/**
 * http://www.cnblogs.com/qinwangchen/p/5445413.html
 */
//自定义的事件状态对象类
class MyEvent extends EventObject
{
    private Object obj;
    //此监听对象可以是自定义对象
    private String sName;
    public MyEvent(Object source,String sName)
    {
        super(source);
        this.obj=source;
        this.sName=sName;  }
    public Object getObj()
    {
        return obj;
    }
    public String getsName()
    {
        return sName;
    }
}
//定义事件源
class MyEventSource
{
    private Vector list=new Vector();
    private String   sName  = null;
    public MyEventSource()
    {
        super();
    }
    public void addMyEventListener(MyEventListener me)
    {
        list.add(me);
    }
    public void deleteMyEventListener(MyEventListener me)
    {
        list.remove(me);
    }
    public void notifyMyEvent(MyEvent me)    // 激活事件
    {
        Iterator it=list.iterator();
        while(it.hasNext())
        {
            //在类中实例化自定义的监听器对象,并调用监听器方法
            ((MyEventListener) it.next()).handleEvent(me);
        }
    }
    public void setName(String str) {   //？？
        boolean bool = false;
        if (str == null && sName != null)
            bool = true;
        else if (str != null && sName == null)
            bool = true;
//        else if (!sName.equals(str))
//            bool = true;
        this.sName = str;
        // 如果改变则执行事件
        if (bool)
            notifyMyEvent(new MyEvent(this, sName));
    }
    public String getsName()
    {   return sName;   }
}


//定义自定义监听器接口，继承EventListener
interface MyEventListener extends EventListener
{
    void handleEvent (MyEvent me);
}

//自定义监听器，继承自定义监听接口
class Mylistener implements MyEventListener
{
    public Map<Integer, String> map =null;
    public int i=0;

    public Mylistener(Map<Integer, String>  map)
    {
        this.map = map;
        MyEventSource mes = new MyEventSource();
        mes.addMyEventListener(this);
        mes.setName(null);       // 事件
    }

    //实现接口中的方法
    public void handleEvent(MyEvent me)
    {
        System.out.println("me.getSource()  "+me.getSource());
        System.out.println("me.getsName()  "+me.getsName());
        //此处可以将写，将监听到的对象存入map中
        map.put(++i, me.getsName());
    }
}
/**
 *  * EventListener----> MyEventListener----> Mylistener
 *
 * MyEventSource
 *
 *  * * EventObject----> MyEvent
 *
 * MyEventSource发生事件----> 触发event listener 处理 MyEvent
 *
 */

//主函数
public class EventListenerTest
{
    public static void main(String args[])
    {
//        Map<Integer, String>  map = new HashMap<Integer, String>();
//        Mylistener mylistener = new Mylistener(map);  // 实例化一个监听器

        // My demo

        EventSoure eventSoure = new EventSoure();
        eventSoure.init();
        Event event1 = new MyEvent1();
        Event event2 = new MyEvent2();
//        System.out.println(event.getClass().getName());  // 获得实现类的全类名



        eventSoure.trigger(event1);
        eventSoure.trigger(event2);
    }
}

