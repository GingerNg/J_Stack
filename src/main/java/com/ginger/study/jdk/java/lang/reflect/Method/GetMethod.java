package com.ginger.study.jdk.java.lang.reflect.Method;

/**
 * Created by Ginger on 17-11-6
 * 通过全类名获取的方法的名称和方法的参数类型
 */
import java.lang.reflect.Method;

public class GetMethod {
    public static void main(String[] args) {
        getMethodInfo("com.ginger.study.string.testStringJoin");
    }

    /**
     * 传入全类名获得对应类中所有方法名和参数名
     */
    @SuppressWarnings("rawtypes")
    private static void getMethodInfo(String pkgName) {
        try {
            Class clazz = Class.forName(pkgName);
            Method[] methods = clazz.getMethods();
            for (Method method : methods) {
                String methodName = method.getName();
                System.out.println("方法名称:" + methodName);
                Class<?>[] parameterTypes = method.getParameterTypes();
                for (Class<?> clas : parameterTypes) {
                    String parameterName = clas.getName();
                    System.out.println("参数名称:" + parameterName);
                }
                System.out.println("*****************************");
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
