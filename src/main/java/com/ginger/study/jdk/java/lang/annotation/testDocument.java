package com.ginger.study.jdk.java.lang.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by Ginger on 17-9-25
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface testDocument {

    String str() default "testDoc";

}
