package com.ginger.study.utils.config;

/**
 */


import com.google.common.collect.Lists;
import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

public class XmlConfigParser {

    protected static Logger logger = LoggerFactory.getLogger(XmlParser2List.class);

    public static <T> List<T> parseXml2List(Class<T> targetClass, String path, String startElement) {
        logger.info("XmlConfigParser parseXml2List path: {}", path);

        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        InputStream stream = classLoader.getResourceAsStream(path);
        XmlParser2List xmlParser = new XmlParser2List();
        List<Map<Object, Object>> mapList = xmlParser.parse(stream, startElement);

        List<T> entList = Lists.newArrayList();
        if (mapList.isEmpty() || mapList == null) {
            return entList;
        }

        for (Map<Object, Object> map : mapList) {
            try {
                T ent = targetClass.newInstance();
                BeanUtils.populate(ent, map);
                entList.add(ent);
            } catch (Exception e) {
                logger.error("XmlConfigParser parseXml2List failed: {}", path);
                throw new RuntimeException("Failed to populate from map to bean", e);
            }
        }

        return entList;
    }

    public static <T> T parseXml2Object(Class<T> targetClass, String path) {
        logger.info("XmlConfigParser parseXml2Object path: {}", path);

        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        InputStream stream = classLoader.getResourceAsStream(path);
        XmlParser2Map xmlParser = new XmlParser2Map();
        Map<Object, Object> map = xmlParser.parse(stream, null);

        T instance;
        try {
            instance = targetClass.newInstance();
        } catch (Exception e) {
            logger.error("XmlConfigParser targetClass newInstance failed: {}", path);
            throw new RuntimeException("Failed to targetClass newInstance", e);
        }

        if (map == null) {
            return instance;
        }

        try {
            BeanUtils.populate(instance, map);
        } catch (Exception e) {
            logger.error("XmlConfigParser parseXml2List failed: {}", path);
            throw new RuntimeException("Failed to populate from map to bean", e);
        }

        return instance;
    }

    public static void main(String[] args) {
        System.out.print(1);
    }


}

