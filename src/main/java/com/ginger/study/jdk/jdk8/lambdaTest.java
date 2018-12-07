package com.ginger.study.jdk.jdk8;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.IntToDoubleFunction;
import java.util.function.Predicate;

/**
 * Created by ginger on 17-7-12.
 */

// 函数式接口
interface Inteface1 {

    int test(int x, int y);

    //    public void test1();//会报错,不能有两个方法,尽管没有使用abstract修饰
    boolean equals(Object o);//equals属于Object的方法,所以不会报错
}

// 闭包
public class lambdaTest {
    Inteface1 ff;

    public lambdaTest(Inteface1 f1) {
        ff = f1;
    }

    /**
     * R apply(T t);
     *
     * @param name
     * @param function
     * @return
     */
    public static String FunctionTest(String name, Function<String, String> function) {
        return function.apply(name);
    }


    /**
     * @param name
     * @param function
     */
    public static void ConsumerTest(String name, Consumer<String> function) {
        function.accept(name);
    }

    /**
     * @param name
     * @param function
     */
    public static boolean PredicateTest(String name, Predicate<String> function) {
        return function.test(name);
    }

    public static void filter(List<String> names, Predicate condition) {
//        for (int i=0;i<names.size();i++){
//            String name = names.get(i);
//            if(condition.test(name)){
//                System.out.println(name + " ");
//            }
//        }
        for (String name : names) {
            if (condition.test(name)) {
                System.out.println(name + " ");
            }
        }
    }

    /**
     * @param names
     */
//    将两个或更多的 Predicate 合成一个
    public static void filter1(List<String> names) {
        Predicate<String> startsWithJ = (n) -> n.startsWith("J");
        Predicate<String> fourLetterLong = (n) -> n.length() == 4;
        names.stream()
                .filter(startsWithJ.and(fourLetterLong))
                .forEach((n) -> System.out.println("nName, which starts with 'J' and four letter long is : " + n));
    }

    public static void main(String[] args) {

        Inteface1 f1 = (int x, int y) -> x + y;
        // callback
        System.out.println(f1.test(3, 4));

        //用() -> {}代码块替代了整个匿名类
        lambdaTest test1 = new lambdaTest((x, y) -> x + y);
        System.out.println(test1.ff.test(4, 5));

        //使用lambda表达式对列表进行迭代
        List features = Arrays.asList("Lambdas", "Default Method", "Stream API", "Date and Time API");
        features.forEach(n -> System.out.println(n));


        // 函数式接口Function
        System.out.println(FunctionTest("function", input -> "123" + input));

        // 函数式接口Customer
        ConsumerTest("customer", input -> System.out.println(input + "456"));


        // 函数式接口Predicate
        System.out.println(PredicateTest("predicate", input -> input.equals("boolean")));


        List<String> languages = Arrays.asList("Java", "Scala", "C++", "Haskell", "Lisp");

        System.out.println("Languages which starts with J :");
        filter(languages, input -> ((String) input).startsWith("J"));

        //    将两个或更多的 Predicate 合成一个
        filter1(languages);

        // recursive
        IntToDoubleFunction[] foo = {null};
        foo[0] = x -> (x == 0) ? 1 : x * foo[0].applyAsDouble(x - 1);
        System.out.println(foo[0].applyAsDouble(10));

        // fuck generics
        Function<Integer, Integer>[] fuckRecusive = new Function[1];
        fuckRecusive[0] = (x) -> x == 1 ? 1 : x * (fuckRecusive[0]).apply(x - 1);
        System.out.println(fuckRecusive[0].apply(10));

//        Function<Integer, Integer> tt = new Function<Integer, Integer>();

//        Function fac = null;
//        fac = x -> (int)x <= 1 ? 1;


    }
}




//Class FuncRecursive(){
//    Function func;
//    FuncRecursive(Function func){
//        this.func = func;
//    }
//}

