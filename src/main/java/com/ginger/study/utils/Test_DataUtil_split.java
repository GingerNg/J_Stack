package com.ginger.study.utils;

import java.sql.Timestamp;
import java.util.Date;

/**
 * Created by ginger on 17-5-24.
 */
public class Test_DataUtil_split {
    public static void main(String[] args) {
        long currentTime = DateUtil.fetchNow(DateUtil.FORMAT_TYPE.DATETIME);
        Timestamp ts = new Timestamp(currentTime);

        System.out.println(currentTime); //20170605111733
        System.out.println(ts.toString());
        System.out.println(new Timestamp(System.currentTimeMillis()));

        Timestamp timestamp=new Timestamp(new Date().getTime());
        long test = System.currentTimeMillis();
        System.out.println(System.currentTimeMillis());  // 13位

        // 生成6位随机数
        System.out.println(String.valueOf((1000000+1000000*Math.random())).substring(1, 7));

        System.out.println("\u007C"+"@"+"\u007C");
        System.out.println("@");

        String dataRange = "011|@||@|klklklklk";

        String[] dataResponse = String.valueOf(dataRange).split("\\u007C"+"@"+"\\u007C");// (1)编码方式

        for (String str:dataResponse){
            System.out.println(str);
        }


//        String[] datas = dataRange.split("\\|@\\|");  // (2)转义符
//        for (String str:datas){
//            System.out.println(str);
//        }
//
//        String dataRange1 = "|@|test|@||@|test2|@||@|";
//
//        String[] data1 = dataRange1.split("\\|@\\|",-1);  // (2)转义符
//        for (String str:data1){
//            System.out.println(str);
//        }

    }
}
