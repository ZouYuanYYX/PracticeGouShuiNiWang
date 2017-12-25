package com.goushuini.h5operation;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.goushuini.data.GetJsonData;
import com.goushuini.utils.ElementLocationUtils;
import com.goushuini.utils.LogUtils;

import io.appium.java_client.android.AndroidDriver;

public class ClickOperation {
	
	/**
     * 单击(app端)
     * @param driver
     * @param by
    */
    public static void buttonClick (AndroidDriver driver,By by) {
        driver.findElement(by).click();
    }
	
	/**
     * 单击重载（web端）
     * @param driver
     * @param by
    */
    public static void buttonClick (WebDriver driver,By by) {
        driver.findElement(by).click();
    }
    
    /**
     * javaScript单击（web端）
     * @param driver
     * @param by
    */
    public static void javaScriptClick (WebDriver driver,By by) {        
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();",driver.findElement(by));
    }
    
    /**
     * 移动到元素位置处后再单击（web端）
     * @param driver
     * @param actions
     * @param by
    */
    public static void moveToElementClick (WebDriver driver,Actions actions,By by) {
    	actions.moveToElement(driver.findElement(by)).build().perform();
        //鼠标移动到工单工作台上后再做click操作
        driver.findElement(by).click();
    }
    
    /**
     * 单击含参数(app端)
     * @param driver
     * @param params
     * @param expression
     */
    public static void buttonClickContainParams (AndroidDriver driver,String params,String expression) {
    	driver.findElement(ElementLocationUtils.elementLocationContainParams(params, expression)).click();
    }
	
	/**
	 * 单击含参数重载（web端）
	 * @param driver
	 * @param params
	 * @param expression
	 */
    public static void buttonClickContainParams (WebDriver driver,String params,String expression) {
        driver.findElement(ElementLocationUtils.elementLocationContainParams(params, expression)).click();
    }
    
    /**
     * 多表达式单击，当数据间唯一区别字段在json串里时，可先根据某个元素的Attribute属性捞取所有数据存入list中
     * 再遍历该list，捞取符合特定条件的数据
     * @param driver
     * @param attribute
     * @param key
     * @param transId
     * @param byOne
     * @param byTwo
     */
    public static void multipleExpressionClick (WebDriver driver,String attribute,String key,String id,By byOne,By byTwo) {
        List<WebElement> e=driver.findElements(byOne);
        for(WebElement el:e){
            String jsonMessage=el.getAttribute(attribute);    
            System.out.println("获取"+attribute+"的属性值"+jsonMessage);            
            //String转为JSON对象
            String actualTransId = GetJsonData.getMultipleMemberJsonData(jsonMessage, key); 
            if(id.equals(actualTransId)){
                System.out.println("找到了元素");
                LogUtils.info("找到了元素");
                el.findElement(byTwo).click();
                break;
            }
            else{
                System.out.println("找不到元素");
                LogUtils.info("找不到元素");
                continue;
            }   
        }        
    }

}
