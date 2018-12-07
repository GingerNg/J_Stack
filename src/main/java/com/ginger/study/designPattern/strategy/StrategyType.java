package com.ginger.study.designPattern.strategy;

/**
 * Created by Ginger on 17-11-23
 */
public enum StrategyType {

    A("A"),
    B("按地理区域随机抽样"),
    ;

    private String description;

    StrategyType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public static void main(String[] args){
        System.out.println(StrategyType.B.getDescription());
        System.out.println(StrategyType.B.toString());
        System.out.println(StrategyType.B);
        System.out.println(StrategyType.valueOf(StrategyType.A.getDescription()));
        System.out.println(StrategyType.valueOf(StrategyType.B.toString()));
    }

}
