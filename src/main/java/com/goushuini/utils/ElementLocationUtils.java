package com.goushuini.utils;

import java.text.MessageFormat;
/**
 * 根据什么方法来定位元素
 * @author 邹元
 * @date 2017年12月14
 */

import org.openqa.selenium.By;

public class ElementLocationUtils {
    
    private static String key;
    private static String value;
    
    /**
     * 获取key的方法，其中key表示id、name、css、xpath等
     * @param expression
     * @return
     */
    private static String getKey (String expression) {
        try {
            key = expression.split(",")[0];          
        } catch (Exception e) {
            System.out.println("元素定位的表达式格式不正确");
            e.printStackTrace();
        }
        return key;
    }
    
    /**
     * 获取value的方法，其中value表示根据id或者xpath定位元素时，
     * 定位元素的表达式是什么，例如：By.name("userName")，双引号
     * 里的值就是这里的value
     * @param expression
     * @return
     */
    private static String getValue (String expression) {
        try {
            value = expression.split(",")[1];          
        } catch (Exception e) {
            System.out.println("元素定位的表达式格式不正确");
            e.printStackTrace();
        }
        return value;
    }
    
    /**
     * 将定位到的元素表达式返回，返回值是By类型
     * @param expression
     * @return
     */
    public static By elementLocation (String expression) {
        return selectElementLocationWays(getKey(expression),getValue(expression));                      
    }
    
    /**
     * value含参数时（样式如：//android.widget.TextView[@text={0}]），
     * {0}为待传入的值，可用该方法传参，并将定位到的元素表达式返回，返回值是By类型
     * @param expression
     * @return
     */
    public static By elementLocationContainParams (String params,String expression) {
        String trueValue = MessageFormat.format(getValue(expression), params);
        return selectElementLocationWays(getKey(expression),trueValue);                       
    }
    
    /**
     * 根据参数key值判断，将什么定位表达式返回，返回值是By类型
     * @param key
     * @param value
     * @return
     */
    private static By selectElementLocationWays (String key,String value) {
        if ("id".equals(key.toLowerCase().trim())) {
            return By.id(value);
        }
        if ("name".equals(key.toLowerCase().trim())) {
            return By.name(value);
        }
        if ("classname".equals(key.toLowerCase().trim())) {
            return By.className(value);
        }
        if ("xpath".equals(key.toLowerCase().trim())) {
            return By.xpath(value);
        }
        if ("css".equals(key.toLowerCase().trim())) {
            return By.cssSelector(value);
        }
        if ("partiallinktext".equals(key.toLowerCase().trim())) {
            return By.partialLinkText(value);
        }
        if ("linktext".equals(key.toLowerCase().trim())) {
            return By.linkText(value);
        }
        return null;
    }

}
