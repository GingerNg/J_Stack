package com.ginger.study.jdk.java.lang.annotation;


import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

/**
 * Created by Ginger on 17-9-25
 */
@testInherited
public class HelloDoc {

    @testDocument(str = "test")
    public String str;

    @testDocument(str = "1")  // 不是初始化
    public String str1 = "1";

    @FruitName
    public String fruit;

    public void setStr(String str) {
        this.str = str;
    }

    public void setStr1(String str1) {
        this.str1 = str1;
    }

    public void setFruit(String fruit) {
        this.fruit = fruit;
    }

    public static void main(String[] args){
        HelloDoc helloDoc = new HelloDoc();
        System.out.println(helloDoc.str);
        System.out.println(helloDoc.fruit);
        System.out.println(helloDoc.str1);

        try{

            Class<?> cls = Class.forName("com.ginger.study.jdk.java.lang.annotation.HelloDoc");  // 反射

//            getFields()：获得某个类的所有的公共（public）的字段，包括父类中的字段。
//            getDeclaredFields()：获得某个类的所有声明的字段，即包括public、private和proteced，但是不包括父类的申明字段。

            Field[] fields = cls.getDeclaredFields(); // 获得字段名

            for (Field field : fields) {
                /**
                 * 方法1
                 */

                Annotation[] annotations = field.getDeclaredAnnotations();
                for (int i = 0;i<annotations.length;i++){
//                    System.out.println(annotations[i]);

                        System.out.println(annotations[i].annotationType());   // 类名

                    if (annotations[i].annotationType()==testDocument.class){
                        System.out.println(((testDocument) annotations[i]).str());
                    }


                }

                /**
                 * 方法2
                 */
                if(field.isAnnotationPresent(FruitName.class)==true){
                    FruitName name = field.getAnnotation(FruitName.class); // 获得对象 name
                    System.out.println("Fruit Name:"+name.value());  // 获得对象的值
                }


                if (field.getAnnotatedType().getType()==testInherited.class){
                    System.out.println(field.getName().toString());
                }
                System.out.println(field.getAnnotatedType());
//                System.out.println(field.getAnnotatedType().getType());   // String
                if (field.getAnnotatedType().getType()==testDocument.class){
                    System.out.println(field.getName().toString());
                }
                field.getName();
                //                if(field.isAnnotationPresent(FruitColor.class)){
//                    FruitColor color = field.getAnnotation(FruitColor.class);
//                    System.out.println("Fruit Color:"+color.fruitColor());
//                }
//                if(field.isAnnotationPresent(FruitProvider.class)){
//                    FruitProvider Provider = field.getAnnotation(FruitProvider.class);
//                    System.out.println("Fruit FruitProvider: ProviderID:"+Provider.id()+" Provider:"+Provider.user() +" ProviderAddress:"+Provider.address());
//                }
            }



        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }

    }

}
