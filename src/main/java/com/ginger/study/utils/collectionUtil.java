package com.ginger.study.utils;

import java.util.Collection;

/**
 * Created by Ginger on 17-11-17
 */
public class collectionUtil {

    public static boolean isCollectionEmpty(Collection t){
        if(null!=t && t.size()!=0){
            return false;
        }
        return true;
    }

}
