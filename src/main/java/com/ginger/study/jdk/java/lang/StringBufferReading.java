package com.ginger.study.jdk.java.lang;

/**
 * Created by Ginger on 17-11-17
 */
public class StringBufferReading {

    /***
     * jdk类说明:
     *  * As of  release JDK 5, this class has been supplemented with an equivalent
     * class designed for use by a single thread, {@link StringBuilder}.  The
     * {@code StringBuilder} class should generally be used in preference to
     * this one, as it supports all of the same operations but it is faster, as
     * it performs no synchronization.
     *
     *
     * 类继承结构
     * abstract class AbstractStringBuilder implements Appendable, CharSequence
     * public final class StringBuilder extends AbstractStringBuilder implements java.io.Serializable, CharSequence
     *
     * StringBuilder不可被继承
     *
     *
     */

    public static void main(String[] args){
        StringBuilder b = new StringBuilder();
    }

}
