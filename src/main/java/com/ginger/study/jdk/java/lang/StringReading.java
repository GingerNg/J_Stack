package com.ginger.study.jdk.java.lang;
/**
 * Created by ginger on 17-5-31.
 *

 * @Link String
 *
 * interoperability   互用性
 *
 *
 */
import java.util.Random;
public class StringReading {
    private static Random random = new Random();
    public static void main(String[] args){

        for(int i=0;i<=15;i++){
            int region = random.nextInt(3);
//            System.out.print(region     );
            String rowKey = String.valueOf(String.valueOf(region).hashCode()+(String.valueOf("数据质量评估")
                    +"key"+"01111jkjkj111000").hashCode());
            System.out.println(rowKey);
        }

        System.out.println("test:"+("0" + String.valueOf(1)).getBytes());  // byte[]


        test_codePointAt();


    }

    public static void test_codePointAt(){
        String str = "abcdefghijklmnopqrstuvwxyz";
        System.out.println(str.codePointAt(2));     // 返回字符对应的ASCII码值或unicode值
    }

/**
 * String源码阅读:
 * String is character sequence
 * String内部是一个char数组  private final char value[];  因为该数组是不可变的,所有String对象不可变

 类继承结构:
 public final class String implements java.io.Serializable, Comparable<String>, CharSequence

 private static final long serialVersionUID = -6849794470754667710L;
 serialVersionUID   // 用于验证版本一致性
 https://www.cnblogs.com/guanghuiqq/archive/2012/07/18/2597036.html

 * 构造器之一:
 * public String(char value[]) {  // 通过char数组创建String对象
 this.value = Arrays.copyOf(value, value.length);   // 复制一个数组
 }



 this.value = Arrays.copyOfRange(value, offset, offset+count);


 Common private utility method
 private static void checkBounds(byte[] bytes, int offset, int length)


 StringCoding类

 public String(byte bytes[], String charsetName)
 throws UnsupportedEncodingException {
 this(bytes, 0, bytes.length, charsetName);   // 用this关键字调用自身另一个构造器方法
 }


 getBytes()方法的实现: 对字符数组遍历,执行dst[j++] = (byte)val[i++];


 lexicographically  字典序; 按字典顺序;


 delimiter 分隔符 定界符

 blockquote 块引用


 Appendable 接口


 ObjectStreamField: 用来提取序列化过程中某个对象内的字段【成员属性】元数据信息


 */



}
