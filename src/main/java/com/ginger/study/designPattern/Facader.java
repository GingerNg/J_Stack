package com.ginger.study.designPattern;

import java.util.HashMap;
import java.util.Map;

/**
 * 外观模式Facade（结构型）
 *
 * 外观类持有子系统业务类的实例
 *引入外观类之后，与子系统业务类之间的交互统一由外观类来完成
 *
 * http://blog.csdn.net/lovelion/article/details/
 *
 *
 */
public class Facader {

    Map SubSyss = new HashMap();

    public Facader(SubSysA subSysA, SubSysB subSysB){
        SubSyss.put(SubSysA.class,subSysA);
        SubSyss.put(SubSysB.class,subSysB);
    }

    private SubSysA subSysA = new SubSysA();
    private SubSysB subSysB = new SubSysB();

    void method(){
        subSysA.methodA();
        subSysB.methodB();
    }







    class SubSysA{

        void methodA(){

        }

    }

    class SubSysB{
        void methodB(){

        }

    }


}
