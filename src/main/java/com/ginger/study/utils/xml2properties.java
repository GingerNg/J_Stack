package com.ginger.study.utils;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Ginger on 17-11-13
 */
public class xml2properties {


    public static void xml2pros() {

        try {
            SAXReader saxReader = new SAXReader();
            Document document = saxReader.read(new File("输入xml文件路径"));
            Properties p = new Properties();
            List<Element> list = document.getRootElement().elements("string");
            for (Element e : list) {
                p.setProperty(e.attributeValue("name"), e.getTextTrim());
            }
            p.store(new FileOutputStream("输出properties文件路径"), "");
        } catch (DocumentException ex) {
            ex.printStackTrace(System.err);
        } catch (IOException ex) {
            ex.printStackTrace(System.err);
        }
    }

    public static void xmlStr2pros(String xmlStr){
        try {
            Document document = DocumentHelper.parseText(xmlStr);
            Properties p = new Properties();
            List<Element> list = document.getRootElement().elements();
            for (Element e : list) {
                System.out.println();
                p.setProperty(e.attributeValue("name"), e.getTextTrim());
            }
            p.store(new FileOutputStream("输出properties文件路径"), "");
        } catch (DocumentException ex) {
            ex.printStackTrace(System.err);
        } catch (IOException ex) {
            ex.printStackTrace(System.err);
        }
    }

//    public static void regExp(){
//        new RegExp('.*[\@]+(.*)[\.]+.*');
//    }


    public static void main(String[] args){
        String test = "<configuration>\n" +
                "  <property>\n" +
                "    <name>outputDir</name>\n" +
                "    <value>output</value>\n" +
                "  </property>\n" +
                "  <property>\n" +
                "    <name>examplesRoot</name>\n" +
                "    <value>XXX</value>\n" +
                "  </property>\n" +
                "  <property>\n" +
                "    <name>oozie.wf.application.path</name>\n" +
                "    <value>hdfs://XX:9000/user/XXX/qa/apps/accuracy/workflow.xml</value>\n" +
                "  </property>\n" +
                "  <property>\n" +
                "    <name>queueName</name>\n" +
                "    <value>default</value>\n" +
                "  </property>\n" +
                "  <property>\n" +
                "    <name>user.name</name>\n" +
                "    <value>XXX</value>\n" +
                "  </property>\n" +
                "  <property>\n" +
                "    <name>jobTracker</name>\n" +
                "    <value>XXX:8032</value>\n" +
                "  </property>\n" +
                "  <property>\n" +
                "    <name>mapreduce.job.user.name</name>\n" +
                "    <value>XXX</value>\n" +
                "  </property>\n" +
                "  <property>\n" +
                "    <name>nameNode</name>\n" +
                "    <value>hdfs://XX:9000</value>\n" +
                "  </property>\n" +
                "</configuration>";
//        xmlStr2pros(test);
        String rgex = "<name>(.*?)</name>";
        String rgex1 = "<value>(.*?)</value>";
//        System.out.println(getSubUtil(test,rgex));
//        System.out.println(getSubUtilSimple(test, rgex));
//        System.out.println(getSubUtil(test,rgex1));
//        System.out.println(getSubUtilSimple(test, rgex1));
        System.out.println(XmlStr2Properties(test));
    }

    private static Properties XmlStr2Properties(String workflowJobConf){
        Properties conf = new Properties();
        Pattern patternName = Pattern.compile("<name>(.*?)</name>");// 匹配的模式
        Pattern patternValue = Pattern.compile("<value>(.*?)</value>");
        Matcher matchName = patternName.matcher(workflowJobConf);
        Matcher matchValue = patternValue.matcher(workflowJobConf);
        while (matchName.find()&&matchValue.find()) {
            int i = 1;
            conf.setProperty(matchName.group(i),matchValue.group(i));
            i++;
        }
        return conf;
    }

    /**
     * 正则表达式匹配两个指定字符串中间的内容
     * @param soap
     * @return
     */
    public static List<String> getSubUtil(String soap,String rgex){
        List<String> list = new ArrayList<String>();
        Pattern pattern = Pattern.compile(rgex);// 匹配的模式
        Matcher m = pattern.matcher(soap);
        while (m.find()) {
            int i = 1;
            list.add(m.group(i));
            i++;
        }
        return list;
    }

    /**
     * 返回单个字符串，若匹配到多个的话就返回第一个，方法与getSubUtil一样
     * @param soap
     * @param rgex
     * @return
     */
    public static String getSubUtilSimple(String soap,String rgex){
        Pattern pattern = Pattern.compile(rgex);// 匹配的模式
        Matcher m = pattern.matcher(soap);
        while(m.find()){
            return m.group(1);
        }
        return "";
    }

}
