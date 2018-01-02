package com.goushuini.element.operation;

import java.io.File;
import java.io.FileOutputStream;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

/**
 * 屏幕截图工具
 * @author joy
 * @date 2018年1月2日
 */

public class ScreenShotOperation {
    
    /**
     * 屏幕截图工具，截的图以测试用例序号命名
     * @param driver
     * @param path
     * @param testCaseId
     */
    public static void screenShot(WebDriver driver,String path,String sheetName,String testCaseId) {
        File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        try {           
            //将截图保存，并以测试用例id命名图片
            FileOutputStream out = new FileOutputStream(path+"/"+sheetName+"_"+testCaseId+".png");
            FileUtils.copyFile(scrFile, out);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
