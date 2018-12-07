package com.ginger.study.utils.config;

/**
 *  * 加载资源的两种方式  (resource文件夹中的文件)
 * https://www.cnblogs.com/CloudTeng/archive/2012/04/08/2438028.html
 */
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ResourceBundle;

public class CommonPropertyConfig {

    private static Logger logger = LoggerFactory.getLogger(CommonPropertyConfig.class);

    private static String DEFAULT_PROPERTIES = "XXX";

    private ResourceBundle propertyRes;

    public static void setDefaultProperties(String defaultProperties) {
        DEFAULT_PROPERTIES = defaultProperties;
    }

    public static CommonPropertyConfig PROPERTY = new CommonPropertyConfig();

    public static CommonPropertyConfig getProperty() {
        return PROPERTY;
    }

    private ResourceBundle getPropertyRes() {
        return propertyRes;
    }

    private void setPropertyRes(ResourceBundle propertyRes) {
        this.propertyRes = propertyRes;
    }

    public static void loadProperties(String defaultProperties) {
        setDefaultProperties(defaultProperties);
        CommonPropertyConfig propertyConfig = CommonPropertyConfig.getProperty();

        ResourceBundle resource;
        try {
            resource = ResourceBundle.getBundle(DEFAULT_PROPERTIES);
            propertyConfig.setPropertyRes(resource);
        } catch (Exception e) {
            logger.error("解析proterties文件失败:", e);
            throw new RuntimeException("解析proterties文件失败", e);
        }
    }

    public static String getPropertyByKey(String key) {
        ResourceBundle resource = CommonPropertyConfig.getProperty().getPropertyRes();
        String property = resource.getString(key);
        if (null == property || "".equals(property))
            return "";

        return resource.getString(key);
    }
}
