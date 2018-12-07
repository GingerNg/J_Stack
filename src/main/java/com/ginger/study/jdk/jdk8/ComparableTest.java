package com.ginger.study.jdk.jdk8;
/**
 * Created by ginger on 17-7-13.
 */

import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Comparable接口 强行对实现它的每个类的对象进行整体排序
 * 实现此接口的对象列表（和数组）可以通过 Collections.sort （和 Arrays.sort ）进行自动排序
 * int compareTo(T o)
 比较此对象与指定对象的顺序。如果该对象小于、等于或大于指定对象，则分别返回负整数、零或正整数。
 http://www.cnblogs.com/gnuhpc/archive/2012/12/17/2822251.html

 Comparator位于包java.util下，而Comparable位于包java.lang下
 */
public class ComparableTest {

    private Logger logger = Logger.getLogger(getClass());


    public static void main(String[] args){
        ComparableTest comparableTest = new ComparableTest();
        comparableTest.testComparator();
    }

    private void testComparator(){
        List<Goods> goods = new ArrayList<>();//存储池

        for (int i = 0;i<10;i++){
            Goods good = new Goods();
            good.setOffset(i);
            goods.add(good);
        }

        goods.sort(new Comparator<Goods>() {//重排序
            @Override
            public int compare(Goods o1, Goods o2) {
                String offset1 = o1.getOffset();
                String offset2 = o2.getOffset();
                return offset1.compareTo(offset2);
            }
        });

        for (Goods good:goods){
            logger.info(good.getOffset());
        }
    }



    /******************innner class*************************/

    static class Goods implements Comparable{

        public void setOffset(int offset) {
            this.offset = String.valueOf(offset);
        }
        public String getOffset() {
            return offset;
        }
        private String offset;
        @Override
        public int compareTo(Object o) {
            return 0;
        }
    }

}
