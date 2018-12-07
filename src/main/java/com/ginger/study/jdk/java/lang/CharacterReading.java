package com.ginger.study.jdk.java.lang;

/**
 * Created by Ginger on 17-11-17
 */
public class CharacterReading {
    /***
     *
     * The {@code Character} class wraps a value of the primitive
     * type {@code char} in an object.
     *
     *
     *
     // 定位   todo
     static int codePointAtImpl(char[] a, int index, int limit) {
     char c1 = a[index];
     if (isHighSurrogate(c1) && ++index < limit) {
     char c2 = a[index];
     if (isLowSurrogate(c2)) {
     return toCodePoint(c1, c2);
     }
     }
     return c1;
     }
     *
     */

    Character character = new Character('c');

}
