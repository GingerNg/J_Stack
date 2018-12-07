package com.ginger.study.designPattern;

import com.ginger.study.domaindao.model.Food;

/**
 * Created by Ginger on 17-11-15
 * test clone
 */
public class HolderTester {

    public static void main(String[] args) throws CloneNotSupportedException{
        Holder holder = new Holder();
        holder.init();
        Food food1 = holder.getFood();
        food1.setPrice("11");
        food1.setTime("11");
        System.out.println(food1.getPrice());
        System.out.println(food1.getTime());
        System.out.println(holder.getFood().getPrice());

    }

}
