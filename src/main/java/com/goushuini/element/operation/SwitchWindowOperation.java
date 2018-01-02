package com.goushuini.element.operation;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.WebDriver;

public class SwitchWindowOperation {
	
	/**
	 * 切换窗口（web端）
	 * @param driver
	 * @param windowPage
	 */
	public static void switchWindow (WebDriver driver,String windowPage) {
        int windowNum = Integer.parseInt(windowPage);
        //切换窗口
        Set<String> winHandles=driver.getWindowHandles();//得到当前窗口的set集合
        List<String> it=new ArrayList<String>(winHandles);//将set集合存入list对象
        driver.switchTo().window(it.get(windowNum));
    }

}
