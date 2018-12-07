package com.ginger.study.utils;

/**
 * Created by Ginger on 17-11-17
 */
public class objectUtil {
    /**
     * obj的null断言
     * @Title assertNull
     * @Description
     * @param t
     * @param defaultValue
     * @return
     */
    public static <T> T assertNull(T t, T defaultValue){

        if(null==t){
            return defaultValue;
        }
        return t;

    }

}
