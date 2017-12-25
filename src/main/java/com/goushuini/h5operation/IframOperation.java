package com.goushuini.h5operation;

import java.text.MessageFormat;

import org.openqa.selenium.WebDriver;

public class IframOperation {
	
	/**
     * 切入iframe（web端）
     * @param driver
     * @param iframe
    */
    public static void intoIframe (WebDriver driver,String iframe) {
        driver.switchTo().frame(iframe);
    }
    
    /**
     * 切入iframe（web端）
     * @param driver
     * @param iframe
    */
    public static void intoIframeContainsParams (WebDriver driver,String iframe,String transId) {
        String frame = MessageFormat.format(iframe, transId);
        driver.switchTo().frame(frame);
    }
    /**
     * 切出iframe（web端）
     * @param driver
    */
    public static void outIframe (WebDriver driver) {
        driver.switchTo().defaultContent();
    }

}
