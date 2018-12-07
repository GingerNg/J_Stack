package com.ginger.study.jdk.java.util;

import com.ginger.study.domaindao.model.Food;

import java.util.Arrays;

/**
 * Created by Ginger on 17-11-17
 */
public class ArraysReading {

    /***
     * Arrays 实现了各种排序算法
     * 工具类  不继承接口
     * 数组工具
     * 方法:
     * (1)sort:
     public static void sort(long[] a) {
     DualPivotQuicksort.sort(a, 0, a.length - 1, null, 0, 0);
     }
     * final class DualPivotQuicksort    http://blog.csdn.net/xjyzxx/article/details/18465661
     * 双轴快排
     *
     *
     * (2) parallelSort
     * Arrays.parallelSort使用了Java7的Fork/Join框架使排序任务可以在线程池中的多个线程中进行
     * http://blog.csdn.net/excellentyuxiao/article/details/52344628
     *
     * (3)parallelPrefix
     *
     * (4)parallelSetAll  parallelSetAll会将数组中的值按照一个函数的计算结果过滤出来
     *
     * (5) equals() 方法 判断两个数组是否相同
     * public static boolean equals(Object[] a, Object[] a2)
     *
     * (6) fill() the array to be filled
     *
     * (7)copyOf
     * 调用: System.arraycopy()
     *
     * (8)public static <T> T[] copyOfRange(T[] original, int from, int to)
     *
     * (9)  asList
     * array ---> list
     *
     * (10) deepHashCode
     *
     * (11) toString
     * 使用StringBuilder实现          enclosed in square brackets (<tt>"[]"</tt>)
     *
     * (12) public static String deepToString(Object[] a)
     *
     *
     * (13) spliterator
     * public static <T> Spliterator<T> spliterator(T[] array)
     * 类型占位符
     *使用Spliterator的时候，我们可以将元素分割成多份，分别交于不于的线程去遍历  todo
     *
     *(14) stream
     *
     *
     *
     */

    public static void main(String[] args){

        String contents = "abs123bc12dc";
        String[] words = contents.split("[\\P{L}]+");// 根据非字母字符对字符串进行分隔

        int[] values = { 1,2,3,4 };
        Arrays.parallelPrefix(values,(x,y)->x*y);

        System.out.println(Arrays.toString(values)); // [1, 2, 6, 24]

        Food food = new Food();
        food.setPrice("10");
        food.setTime("10");
        Food[] foods = new Food[2];
        Arrays.fill(foods,food);

        System.out.println(Arrays.deepToString(foods));

        int[] vals = new int[3];
        Arrays.setAll(vals,i->i+2);

        System.out.println(Arrays.toString(vals));


        System.out.println(Arrays.spliterator(vals));


//        http://blog.csdn.net/kkgbn/article/details/52168623

    }


}
