package com.goushuini.h5operation;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import io.appium.java_client.android.AndroidDriver;

/**
 * H5页面上存在的各种操作，如输入文本框、单击按钮等
 * @author PC
 * @date 2017年12月20
 */

public class InputTextOperation {
	
	/**
     * 输入
     * @param driver
     * @param by
     * @param sendKey
    */
    public static void inputText (AndroidDriver driver,By by,String sendKey) {
        driver.findElement(by).sendKeys(sendKey);
    }
    
    /**
     * 输入重载（web端）
     * @param driver
     * @param by
     * @param sendKey
    */
    public static void inputText (WebDriver driver,By by,String sendKey) {
        driver.findElement(by).sendKeys(sendKey);
    }
    
    /**
     * 清空输入框
     * @param driver
     * @param by
    */
    public static void clearText (AndroidDriver driver,By by) {
        driver.findElement(by).clear();
    }
    
    /**
     * 清空输入框方法重载（web端）
     * @param driver
     * @param by
    */
    public static void clearText (WebDriver driver,By by) {
        driver.findElement(by).clear();
    }

}
