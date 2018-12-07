package com.ginger.study.designPattern;

import com.ginger.study.domaindao.model.Food;

/**
 * Created by Ginger on 17-11-15
 */
public class Holder {


    private Food food = new Food();

    public void init(){
        food.setPrice("10");
        food.setPrice("10");

    }

    public Food getFood() throws CloneNotSupportedException{
            return (Food) food.clone();
    }
}
