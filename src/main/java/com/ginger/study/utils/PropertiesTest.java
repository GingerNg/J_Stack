package com.ginger.study.utils;
import java.util.Properties;

public class PropertiesTest {

    public static void main(String[] args){
        Properties prop = System.getProperties();

        String os = prop.getProperty("os.name");

        System.out.println(os);

        System.out.println(isOSLinux());
    }

    /**
     * 判断当前系统是否为linux
     * @return
     */
    public static boolean isOSLinux() {
        Properties prop = System.getProperties();

        String os = prop.getProperty("os.name");
        if (os != null && os.toLowerCase().indexOf("linux") > -1) {
            return true;
        } else {
            return false;
        }
    }
}
