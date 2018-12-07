package com.ginger.study.designPattern.template;

/**
 * Created by Ginger on 18-1-11
 */
public abstract class AbstractTemplate {
    /**
     * 模板方法
     */
    public void templateMethod(){
        //调用基本方法
        abstractMethod();
        dohookMethod();
        concreteMethod();
    }
    /**
     * 基本方法的声明（由子类实现）
     * 把基本操作方法组合在一起形成一个总算法或一个总行为的方法。
     */
    protected abstract void abstractMethod();
    /**
     * 基本方法(空方法)
     * 一个钩子方法由抽象类声明并实现，而子类会加以扩展。通常抽象类给出的实现是一个空实现，作为方法的默认实现。
     * 一个缺省适配模式讲的是一个类为一个接口提供一个默认的空实现，从而使得缺省适配类的子类不必像实现接口那样必须给出所有方法的实现
     * 命名规则是设计师之间赖以沟通的管道之一，使用恰当的命名规则可以帮助不同设计师之间的沟通。
     * 钩子方法的名字应当以do开始，这是熟悉设计模式的Java开发人员的标准做法。
     */
    protected void dohookMethod(){}
    /**
     * 基本方法（已经实现）
     */
    private final void concreteMethod(){
        //业务相关的代码
    }
}
