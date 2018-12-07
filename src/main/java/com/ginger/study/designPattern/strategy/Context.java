package com.ginger.study.designPattern.strategy;

/**
 * Created by Ginger on 17-11-23
 * "A菱形---->B"   A持有B的一个对象
 * "A-----三角形B"   A实现B   A is a kind of B
 */
public class Context {
    //持有一个具体策略的对象
    private Strategy strategy;
    /**
     * 构造函数，传入一个具体策略对象
     * @param strategy    具体策略对象
     */
    public Context(Strategy strategy){
        this.strategy = strategy;
    }
    /**
     * 策略方法
     */
    public void contextInterface(StrategyAdvice strategyAdvice){

        strategy.strategyInterface(strategyAdvice);
    }

    /**
     * test
     * @param args
     */
    public static void main(String[] args) {

        //选择并创建需要使用的策略对象
        ConcreteStrategyA strategy = new ConcreteStrategyA();
        //创建环境
        Context context = new Context(strategy);

        StrategyAdvice strategyAdvice = new StrategyAdvice();

        context.contextInterface(strategyAdvice);

    }


}
