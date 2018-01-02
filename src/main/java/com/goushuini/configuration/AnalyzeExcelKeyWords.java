package com.goushuini.configuration;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import com.goushuini.PracticeGouShuiNi171212.TestCase;
import com.goushuini.data.Contents;
import com.goushuini.element.operation.ScreenShotOperation;
import com.goushuini.utils.ExcelUtils;
import com.goushuini.utils.InitialUtils;
import com.goushuini.utils.LogUtils;

import junit.framework.Assert;
/**
 * 解析excel测试步骤中的关键字
 * @author joy
 * @date 2017年12月15日
 */
public class AnalyzeExcelKeyWords {
    /**
     * 读取excel测试步骤中每一行的数据并存储到list集合中
     * @param mainSheetSuiteCaseId
     * @param sheetName
     * @param rownum
     */
    public void analyzeExcel (String mainSheetSuiteCaseId,String sheetName,int rowNum,String picturePath) {
        String suiteCaseId;
        String testCaseId;
        String testDevices;
        String production;
        String function;
        String operation;
        String keyWords;
        String keyWordsFunction;
        String value1;
        String value2;
        String value3;
        String value4;
        String elementLocation1;
        String elementLocation2;
        List<String> ls = new ArrayList();
        ls = ExcelUtils.getMultipleCell(sheetName, rowNum);
        suiteCaseId = ls.get(0);
        testCaseId = ls.get(1);
        testDevices = ls.get(2);
        production = ls.get(3);
        function = ls.get(4);
        operation = ls.get(5);
        keyWords = ls.get(6);
        keyWordsFunction = ls.get(7);
        value1 = ls.get(8);
        value2 = ls.get(9);
        value3 = ls.get(10);
        value4 = ls.get(11);
        elementLocation1 = ls.get(12);
        elementLocation2 = ls.get(13);
        //跑测试步骤时，只跑与主表测试用例id一致的数据
        if (mainSheetSuiteCaseId.equals(suiteCaseId)) {
            try {
                System.out.println("第"+rowNum+"步测试步骤执行");
                keyWordsAction(sheetName,keyWordsFunction,testDevices,production,value1,value2,value3,value4,elementLocation1,elementLocation2);
                if (TestCase.result == true) {
                    ScreenShotOperation.screenShot(InitialUtils.appShipperDriver, picturePath, sheetName, testCaseId);
                    //在日志中打印测试步骤执行完毕
                    LogUtils.info(testCaseId+"测试步骤执行成功");
                    ExcelUtils.setCell(rowNum, 14, "测试步骤执行成功", sheetName, Contents.PATH);
                } else {
                    //在日志中打印测试步骤执行完毕
                    ExcelUtils.setCell(rowNum, 14, "测试步骤执行失败，后续测试用例不再执行", sheetName, Contents.PATH);
                    //InitialUtils.webDriver.quit();
                    LogUtils.info(testCaseId+"测试步骤执行失败,后续测试用例不再执行");                 
                    Assert.fail("执行出现异常，测试用例执行失败");
                }
            } catch (Exception e) {
                
                e.printStackTrace();
            }
        	
        }
    }
    /**
     * 使用java反射机制分别调用app端及web端的关键字类方法
     * @param sheetName
     * @param rownum
     * @param keyWordsFunction
     * @param production
     * @param value1
     * @param value2
     * @param value3
     * @param value4
     * @param elementLocation1
     * @param elementLocation2
     */
    public void keyWordsAction (String sheetName,String keyWordsFunction,String testDevices,String production,
            String value1,String value2,String value3,String value4,String elementLocation1,
            String elementLocation2) {
        WebActionKeyWords webActionKeyWords = new WebActionKeyWords();
        AppActionKeyWords appActionKeyWords = new AppActionKeyWords();
        Class clazzWeb = webActionKeyWords.getClass();
        Class clazzApp = appActionKeyWords.getClass();
        Method[] methodsWeb = clazzWeb.getDeclaredMethods();
        Method[] methodsApp = clazzApp.getDeclaredMethods();
        if ("web".equals(testDevices)) {
        	keyWordsFunctionInvoke(methodsWeb,webActionKeyWords,keyWordsFunction,production,value1,value2,value3,value4,elementLocation1,
                    elementLocation2);
        }
        if ("app".equals(testDevices)) {
            keyWordsFunctionInvoke(methodsApp,appActionKeyWords,keyWordsFunction,production,value1,value2,value3,value4,elementLocation1,
                    elementLocation2);
        } 
    }
    /**
     * 使用java反射机制调用方法
     * @param methods
     * @param object
     * @param value1
     * @param value2
     * @param value3
     * @param value4
     * @param elementLocation1
     * @param elementLocation2
     */
    public void keyWordsFunctionInvoke (Method[] methods,Object object,String keyWordsFunction,String production,String value1,
    		String value2,String value3,String value4,String elementLocation1,String elementLocation2) {
        for (int i=0;i<methods.length;i++) {
            try {
                if (keyWordsFunction.equals(methods[i].getName())) {
                    methods[i].invoke(object, production,value1,value2,value3,value4,elementLocation1,elementLocation2); 
                    break;
                } else {
                    continue;
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }       
    }

}
