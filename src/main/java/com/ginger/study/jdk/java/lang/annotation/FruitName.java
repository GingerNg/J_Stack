package com.ginger.study.jdk.java.lang.annotation;

import java.lang.annotation.*;

/**
 * Created by Ginger on 17-9-25
 */

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface FruitName {
    String value() default "pear";
}
