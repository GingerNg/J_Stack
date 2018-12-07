package com.ginger.study.domaindao;

import java.io.Serializable;
import java.util.LinkedList;

/**
 * https://kb.cnblogs.com/page/520743/
 *
 * 　　1，失血模型  Model 中，仅包含状态(属性），不包含行为(方法），采用这种设计时，需要分离出DB层，专门用于数据库操作。
 * 　　2，贫血模型  domain ojbect包含了不依赖于持久化的领域逻辑
 * 　　3，充血模型  Model 中既包括状态，又包括行为，是最符合面向对象的设计方式。
 * 　　4，胀血模型
 *
 *      Vo - value object 只有状态（变量）
 *      Logic/Service/Manager 只有行为(方法)
 */

public class Item implements Serializable {

    private Long id = null;
    private int version;
    private String name;
    private Bid bid;

    public Long getId() {
        return id;
    }

    public int getVersion() {
        return version;
    }

    public String getName() {
        return name;
    }

    public LinkedList getBids() {
        return new LinkedList();
    }

    public Bid placeBid()
            throws Exception {

        // Create new Bid
        Bid newBid = new Bid();
//
        // Place bid for this Item
        this.getBids().add(newBid);   // 请注意这一句，透明的进行了持久化，但是不能在这里调用ItemDao，Item不能对ItemDao产生依赖！return newBid;
        return newBid;
    }
}

class Bid {
    private String name;
}