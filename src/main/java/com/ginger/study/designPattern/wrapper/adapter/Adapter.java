package com.ginger.study.designPattern.wrapper.adapter;

/**
 * Created by Ginger on 17-11-24
 * 类适配器和对象适配器
 * http://www.cnblogs.com/java-my-life/archive/2012/04/13/2442795.html
 */
public class Adapter{

}

class ClassAdapter{                                           // 类适配器模式
    private Adaptee adaptee;

    public ClassAdapter(Adaptee adaptee){
        this.adaptee = adaptee;
    }
    /**
     * 源类Adaptee没有方法sampleOperation2
     * 因此由适配器类需要补充此方法
     */
    public void sampleOperation2(){
        //写相关的代码
    }
}
class InstanceAdapter extends Adaptee implements Target{      // 对象适配器
    private Adaptee adaptee;

    public InstanceAdapter(Adaptee adaptee){
        this.adaptee = adaptee;
    }
    /**
     * 源类Adaptee有方法sampleOperation1
     * 因此适配器类直接委派即可
     */
    public void sampleOperation1(){
        this.adaptee.sampleOperation1();
    }
    /**
     * 源类Adaptee没有方法sampleOperation2
     * 因此由适配器类需要补充此方法
     */
    public void sampleOperation2(){
        //写相关的代码
    }
}
