package com.ginger.study.designPattern.factory;

import com.ginger.study.domaindao.model.Food;

/**
 * singleton
 * factory
 */
public class DomainObjectFactory {
    private static DomainObjectFactory factory= new DomainObjectFactory();

    public static DomainObjectFactory getFactory(){
        return factory;
    }

    private DomainObjectFactory(){}

    public <T> T getObject(Class<T> type) {
        if (type == Food.class){
            return (T) new Food();
        }

        else {
            throw new RuntimeException("unsupported type: " + type);
        }
    }
}
