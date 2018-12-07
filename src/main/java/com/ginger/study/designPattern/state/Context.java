package com.ginger.study.designPattern.state;

/**
 * https://www.cnblogs.com/ysw-go/p/5404918.html
 */
//定义当前的状态
public class Context {   // 持有一个实例,该实例决定context的状态

    private State state;

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }
    public String stateMessage(){
        return state.getState();
    }

    public static void main(String args[]){    // 感觉可以和工厂模式一起用  工厂根据不同的StrategyType生成不同的实例
        Context context=new Context();
        context.setState(new Rain());
        System.out.println(context.stateMessage());
        context.setState(new Sunshine());
        System.out.println(context.stateMessage());
    }

}

class Sunshine implements State{

    @Override
    public String getState() {

        return "晴天";
    }

}
class Rain implements State{

    @Override
    public String getState() {

        return "下雨";
    }

}
