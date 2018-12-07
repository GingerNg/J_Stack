package com.ginger.study.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.Properties;

/**
 * Desc:properties文件获取工具类
 * 五种方式让你在java中读取properties文件内容不再是难题
 * https://www.cnblogs.com/hafiz/p/5876243.html
 */
public class PropertiesUtil {
    private static final Logger logger = LoggerFactory.getLogger(PropertiesUtil.class);
    private static Properties props;

    static String prosName = "hello.conf"; // project.properties
    static{
        loadProps(prosName);
    }

    synchronized static private void loadProps(String prosName){
        logger.info("开始加载properties文件内容.......");
        props = new Properties();
        InputStream in = null;
        try {
                    in = PropertiesUtil.class.getClassLoader().getResourceAsStream(prosName);
                    //in = PropertyUtil.class.getResourceAsStream("/jdbc.properties");
                    props.load(in);
        } catch (FileNotFoundException e) {
            logger.error("jdbc.properties文件未找到");
        } catch (IOException e) {
            logger.error("出现IOException");
        } finally {
            try {
                if(null != in) {
                    in.close();
                }
            } catch (IOException e) {
                logger.error("jdbc.properties文件流关闭出现异常");
            }
        }
        logger.info("加载properties文件内容完成...........");
        logger.info("properties文件内容：" + props);
    }

    public static String getProperty(String key){
        return props.getProperty(key);
    }

    public static String getProperty(String key, String defaultValue) {
        return props.getProperty(key, defaultValue);
    }

    public static void main(String[] args){
        if (null != props){
            for (String key : props.stringPropertyNames()) {
                System.out.println(key+":"+props.get(key));
            }
        }
    }
}
