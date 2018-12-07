package com.ginger.study.jdk.java.lang.annotation;

import java.lang.annotation.*;

/**
 *
 * http://www.cnblogs.com/peida/archive/2013/04/24/3036689.html
 * 四个元注解--meta-annotation  元注解的作用就是负责注解其他注解
 */
@Target({ElementType.METHOD, ElementType.FIELD})


@Retention(RetentionPolicy.RUNTIME)   // 定义该Annotation被保留的时间 --- 注解的保留策略
//Column注解的的RetentionPolicy的属性值是RUTIME,这样注解处理器可以通过反射，获取到该注解的属性值，从而去做一些运行时的逻辑处理
// 1.SOURCE:在源文件中有效（即源文件保留）
// 2.CLASS:在class文件中有效（即class保留）
// 3.RUNTIME:在运行时有效（即运行时保留）
@Documented//说明该注解将被包含在javadoc中

public @interface Transformed {

}
