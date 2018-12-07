package com.ginger.study.jdk.jdk8;
import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by ginger on 17-7-12.
 * java8之stream
 * 1.stream不存储数据
 2.stream不改变源数据
 3.stream的延迟执行特性
 * https://www.cnblogs.com/andywithu/p/7404101.html
 *
 *
 * java8之lambda表达式入门
 * lambda表达式专门针对只有一个方法的接口（即函数式接口），Comparator就是一个函数式接口
 * @FunctionalInterface的作用就是标识一个接口为函数式接口
 * java中的参数类型只能是类或者基本数据类型
 * http://www.cnblogs.com/andywithu/p/7357069.html
 */
public class steamDemo {

    public static void main(String[] args){
        //        // 不使用lambda表达式为每个订单加上12%的税
//        List<Integer> costBeforeTax = Arrays.asList(100, 200, 300, 400, 500);
//        for (Integer cost : costBeforeTax) {
//            double price = cost + .12*cost;
//            System.out.println(price);
//        }

        // 使用lambda表达式 为每个订单加上12%的税
//        将 x -> x*x lambda表达式传到 map() 方法，后者将其应用到流中的每一个元素
        List<Integer> costBeforeTax = Arrays.asList(100, 200, 300, 400, 500);
        costBeforeTax.stream().map((cost) -> cost + .12*cost).forEach(System.out::println);

//        一个 reduce() 函数可以将所有值合并成一个
        double bill = costBeforeTax.stream().map((cost) -> cost + .12*cost).reduce((sum, cost) -> sum + cost).get();
        System.out.println("Total : " + bill);


        // 将字符串换成大写并用逗号链接起来
        // collect（）是一个终端操作,它接收的参数是将流中的元素累积到汇总结果的各种方式(称为收集器)
        List<String> G7 = Arrays.asList("USA", "Japan", "France", "Germany", "Italy", "U.K.","Canada");
        String G7Countries = G7.stream().map(x -> x.toUpperCase()).collect(Collectors.joining(", "));
        System.out.println(G7Countries);

        // 用所有不同的数字创建一个正方形列表
        // distinct() 去重
        List<Integer> numbers = Arrays.asList(9, 10, 3, 4, 7, 3, 4);
        List<Integer> distinct = numbers.stream().map( i -> i*i).distinct().collect(Collectors.toList());
        System.out.printf("Original List : %s,  Square Without duplicates : %s %n", numbers, distinct);

        //获取数字的个数、最小值、最大值、总和以及平均值
        List<Integer> primes = Arrays.asList(2, 3, 5, 7, 11, 13, 17, 19, 23, 29);
        IntSummaryStatistics stats = primes.stream().mapToInt((x) -> x).summaryStatistics();
        System.out.println("Highest prime number in List : " + stats.getMax());
        System.out.println("Lowest prime number in List : " + stats.getMin());
        System.out.println("Sum of all prime numbers : " + stats.getSum());
        System.out.println("Average of all prime numbers : " + stats.getAverage());


    }

}
