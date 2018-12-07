package com.ginger.study.designPattern.wrapper.decorator;

/**
 * Created by Ginger on 17-11-24
 */
public class Change implements TheGreatestSage {
    private TheGreatestSage sage;

    public Change(TheGreatestSage sage){
        this.sage = sage;
    }
    @Override
    public void move() {
        // 代码
        sage.move();
    }

}
