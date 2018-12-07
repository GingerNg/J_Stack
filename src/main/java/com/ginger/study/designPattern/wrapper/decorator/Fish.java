package com.ginger.study.designPattern.wrapper.decorator;

/**
 * Created by Ginger on 17-11-24
 */
public class Fish extends Change {

    public Fish(TheGreatestSage sage) {
        super(sage);
    }

    @Override
    public void move() {
        // 代码
        System.out.println("Fish Move");
    }
}
