package com.ginger.study.designPattern.strategy;

import java.util.Map;

/**
 * Created by Ginger on 17-11-23
 */
public class StrategyController implements Strategy{

private Map<StrategyType,Strategy> strategyMap;

    public void setStrategyMap(Map<StrategyType, Strategy> strategyMap) {  //每次都持有所有具体策略的实例, 占用较多内存
        this.strategyMap = strategyMap;
    }

    @Override
    public void strategyInterface(StrategyAdvice strategyAdvice) {
        //相关的业务
        // 根据type选择策略
        strategyMap.get(strategyAdvice.getStrategyType()).strategyInterface(strategyAdvice);
    }

}
