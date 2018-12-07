package com.ginger.study.architecture.cache;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Ginger on 18-1-9
 */
public class TestCache {
    public static void main(String[] args) {



        //Demo1
        Cache c1 = new Cache(); // 被缓存的对象
        c1.setKey("China");
        c1.setValue("中华人民共和国");

        Cache c2 = new Cache();
        c2.setKey("American");
        c2.setValue("美利坚合众国");

        CacheManager.putCache("one", c1);
        CacheManager.putCache("two", c2);
        System.out.println("缓存大小："+CacheManager.getCacheSize());
        System.out.println("key为one的缓存对象Value："+CacheManager.getCacheInfo("one").getValue());


        System.out.println("-----------------------------------");


        //Demo2
        Cache c3 = new Cache();
        c3.setKey("小客车品牌列表");

        List<String> list1 = new ArrayList<String>();
        list1.add("奔驰");
        list1.add("宝马");
        list1.add("奥迪");
        list1.add("现代");

        c3.setValue(list1);

        CacheManager.putCache("car", c3);
        List<String> list2 = new ArrayList<String>();
        Object obCar = CacheManager.getCacheInfo("car").getValue();
        if(obCar instanceof List){
            list2 = (List<String>) obCar;
        }
        for(String brand : list2){
            System.out.println("Key为car的品牌："+brand);
        }


        System.out.println("-----------------------------------");


        //Demo3
        Cache c4 = new Cache();
        c4.setKey("朗行-自动1.8T");

        Cache c5 = new Cache();
        c5.setKey("朗境-双离合2.0T");

        Cache c6 = new Cache();
        c6.setKey("夏朗-自动1.8T");

        Cache c7 = new Cache();
        c7.setKey("朗逸-双离合2.0T");

        Cache c8 = new Cache();
        c8.setKey("速腾-自动1.8T");

        Cache c9 = new Cache();
        c9.setKey("迈腾-双离合2.0T");

        Cache c10 = new Cache();
        c10.setKey("辉腾-双离合2.0T");
        Map<String,String> map1 = new HashMap<String,String>();
        map1.put("全景天窗", "是");
        map1.put("发动机排量", "2.0L");
        map1.put("排放标准", "国5");
        c10.setValue(map1);

        Cache c11 = new Cache();
        c11.setKey("英菲尼迪-1.8T都市精英版");

        CacheManager.putCache("朗行", c4); // key - (key-value)
        CacheManager.putCache("朗境", c5);
        CacheManager.putCache("朗逸", c6);
        CacheManager.putCache("夏朗", c7);
        CacheManager.putCache("速腾", c8);
        CacheManager.putCache("迈腾", c9);
        CacheManager.putCache("辉腾", c10);

        //得到朗系家族的车产品
        Object carObj2 = CacheManager.getCacheListkey("朗");
        List<String> list3 = new ArrayList<String>();
        if(carObj2 instanceof List){
            list3 = (List<String>) carObj2;
        }
        for(String product : list3){
            System.out.println("朗系家族："+product);
        }

        System.out.println("-----------------------------------");

        //得到X腾系列的车产品
        Object carObj3 = CacheManager.getCacheListkey("腾");
        List<String> list4 = new ArrayList<String>();
        if(carObj2 instanceof List){
            list4 = (List<String>) carObj3;
        }
        for(String product : list4){
            System.out.println("腾系家族："+product);
        }

        System.out.println("-----------------------------------");
        System.out.println("此时的缓存大小："+CacheManager.getCacheSize()); // 缓存对象的个数

    }
}
