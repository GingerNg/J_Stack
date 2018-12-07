package com.ginger.study.designPattern.template;

/**
 * Created by Ginger on 18-1-11
 */
public class ConcreteTemplate extends AbstractTemplate{
    //基本方法的实现
    @Override
    public void abstractMethod() {
        //业务相关的代码
    }
    //重写父类的方法
    @Override
    public void dohookMethod() {
        //业务相关的代码
    }
}
