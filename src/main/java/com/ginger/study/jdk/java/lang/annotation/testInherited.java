package com.ginger.study.jdk.java.lang.annotation;

import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by Ginger on 17-9-25
 */
@Inherited
@Retention(RetentionPolicy.RUNTIME)
public @interface testInherited {
//    String name();
}
