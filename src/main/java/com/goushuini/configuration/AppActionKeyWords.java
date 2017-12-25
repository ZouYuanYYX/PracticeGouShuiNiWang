package com.goushuini.configuration;

import com.goushuini.PracticeGouShuiNi171212.TestCase;
import com.goushuini.h5operation.AssertOperation;
import com.goushuini.h5operation.ClickOperation;
import com.goushuini.h5operation.InputTextOperation;
import com.goushuini.utils.AppSwipeUtils;
import com.goushuini.utils.ElementLocationUtils;
import com.goushuini.utils.InitialUtils;
import com.goushuini.utils.LogUtils;

public class AppActionKeyWords {
	public void openApp (String product,String value1,String value2,String value3,
    		String value4,String elementLocation1,String elementLocation2) {
        try {
    		InitialUtils.appDriverInitial(product);
    		LogUtils.info(product+"app启动成功");
        } catch (Exception e) {
        	LogUtils.info(product+"app启动异常，具体异常信息为"+e.getMessage());
        	e.printStackTrace();
        }
    }
    
    public void closeApp (String product,String value1,String value2,String value3,
    		String value4,String elementLocation1,String elementLocation2) {    	
    	try {
    		InitialUtils.appClose(product);
    		LogUtils.info(product+"app关闭成功");
    	} catch (Exception e) {
        	LogUtils.info(product+"app关闭异常，具体异常信息为"+e.getMessage());
        	e.printStackTrace();
    	}
    }   
    
    public void sleep (String product,String time,String value2,String value3,
    		String value4,String elementLocation1,String elementLocation2) {
        try {        
            Thread.sleep(Integer.parseInt(time));
            LogUtils.info("休眠"+Integer.parseInt(time)/1000+"秒成功");
        } catch (Exception e) {
        	TestCase.result = false;
        	LogUtils.info("线程休眠时出现异常，具体异常信息"+e.getMessage());
            e.printStackTrace();
        }
    }
    
    /**
     * 输入
     * @param product
     * @param value1
     * @param value2
     * @param value3
     * @param value4
     * @param elementLocation1
     * @param elementLocation2
     */
    public void inputText (String product,String value1,String value2,String value3,
    		String value4,String elementLocation1,String elementLocation2) {
        try {
        	if ("货主".equals(product.trim())||"货运站".equals(product.trim())) {
        		InputTextOperation.inputText(InitialUtils.appShipperDriver, ElementLocationUtils.elementLocation(elementLocation1), value1);
            	LogUtils.info("app输入框成功输入"+value1);
        	}
            if ("车主".equals(product.trim())||"司机".equals(product.trim())) {
            	InputTextOperation.inputText(InitialUtils.appCarrierDriver, ElementLocationUtils.elementLocation(elementLocation1), value1);
            	LogUtils.info("app输入框成功输入"+value1);
            }
        } catch (Exception e) {
        		TestCase.result = false;
	        	LogUtils.info("往输入框里输入"+value1+"出现异常，具体异常信息为"+e.getMessage());
	        	e.printStackTrace();
        }   	
    }
    
    /**
     * 清空输入框
     * @param product
     * @param value1
     * @param value2
     * @param value3
     * @param value4
     * @param elementLocation1
     * @param elementLocation2
     */
    public void clearText (String product,String value1,String value2,String value3,
    		String value4,String elementLocation1,String elementLocation2) {
    	try {
        	if ("货主".equals(product.trim())||"货运站".equals(product.trim())) {
        		InputTextOperation.clearText(InitialUtils.appShipperDriver, ElementLocationUtils.elementLocation(elementLocation1));
            	LogUtils.info("app输入框成功清空");
        	}
            if ("车主".equals(product.trim())||"司机".equals(product.trim())) {
            	InputTextOperation.clearText(InitialUtils.appCarrierDriver, ElementLocationUtils.elementLocation(elementLocation1));
            	LogUtils.info("app输入框成功清空");
            } 
        } catch (Exception e) {
        		TestCase.result = false;
	        	LogUtils.info("输入框清空出现异常，具体异常信息为"+e.getMessage());
	        	e.printStackTrace();
        }  
    }

    /**
     * 单击
     * @param product
     * @param value1
     * @param value2
     * @param value3
     * @param value4
     * @param elementLocation1
     * @param elementLocation2
     */
    public void buttonClick (String product,String value1,String value2,String value3,
    		String value4,String elementLocation1,String elementLocation2) {
        try {
        	if ("货主".equals(product.trim())||"货运站".equals(product.trim())) {
        		ClickOperation.buttonClick(InitialUtils.appShipperDriver, ElementLocationUtils.elementLocation(elementLocation1));
            	LogUtils.info("app端单击页面元素"+elementLocation1+"成功");
        	}
            if ("车主".equals(product.trim())||"司机".equals(product.trim())) {
            	ClickOperation.buttonClick(InitialUtils.appCarrierDriver, ElementLocationUtils.elementLocation(elementLocation1));
            	LogUtils.info("app端单击页面元素"+elementLocation1+"成功");
            } 
        } catch (Exception e) {
        		TestCase.result = false;
	        	LogUtils.info("单击页面元素"+elementLocation1+"失败，具体异常信息为"+e.getMessage());
	        	e.printStackTrace();
        } 
    }
    
    /**
     * 单击含参数
     * @param product
     * @param value1
     * @param value2
     * @param value3
     * @param value4
     * @param elementLocation1
     * @param elementLocation2
     */
    public void buttonClickContainParams (String product,String value1,String value2,String value3,
    		String value4,String elementLocation1,String elementLocation2) {
        try {
        	if ("货主".equals(product.trim())||"货运站".equals(product.trim())) {
        		ClickOperation.buttonClickContainParams(InitialUtils.appShipperDriver, value1,elementLocation1);
            	LogUtils.info("app端单击页面元素"+elementLocation1+"成功");
        	}
            if ("车主".equals(product.trim())||"司机".equals(product.trim())) {
            	ClickOperation.buttonClickContainParams(InitialUtils.appCarrierDriver, value1,elementLocation1);
            	LogUtils.info("app端单击页面元素"+elementLocation1+"成功");
            } 
        } catch (Exception e) {
        		TestCase.result = false;
	        	LogUtils.info("单击页面元素"+elementLocation1+"失败，具体异常信息为"+e.getMessage());
	        	e.printStackTrace();
        } 
    }
    
    /**
     * 滑动app页面
     * @param product
     * @param swapType
     * @param countStr
     * @param value3
     * @param value4
     * @param elementLocation1
     * @param elementLocation2
     */
	public void swapApp (String product,String swapType,String countStr,String value3,
    		String value4,String elementLocation1,String elementLocation2) {
		try {
			int count = Integer.parseInt(countStr);
			if ("货主".equals(product.trim())||"货运站".equals(product.trim())) {
				for (int i=0;i<count;i++) {
					AppSwipeUtils.swapApp(InitialUtils.appShipperDriver, swapType);
				}
				LogUtils.info(product+"页面"+swapType+count+"次成功");
			} 
			if ("车主".equals(product.trim())||"司机".equals(product.trim())) {
				for (int i=0;i<count;i++) {
					AppSwipeUtils.swapApp(InitialUtils.appCarrierDriver, swapType);
				}
				LogUtils.info(product+"页面"+swapType+count+"次成功");
			}
			
		} catch (Exception e) {
			TestCase.result = false;
        	LogUtils.info(product+"滑动页面失败，具体异常信息为"+e.getMessage());
        	e.printStackTrace();
		}
	}
	
	 /**
     * 断言
     * @param product
     * @param assertString
     * @param value2
     * @param value3
     * @param value4
     * @param elementLocation1
     * @param elementLocation2
     */
    public void assertString(String product,String assertString,String value2,String value3,
    		String value4,String elementLocation1,String elementLocation2) {
    	try {
    		if ("货主".equals(product.trim())||"货运站".equals(product.trim())) {
        		AssertOperation.assertString(InitialUtils.appShipperDriver, assertString);
                LogUtils.info("成功断言关键字“"+assertString+"”");
        	}
            if ("车主".equals(product.trim())||"司机".equals(product.trim())) {
        		AssertOperation.assertString(InitialUtils.appCarrierDriver, assertString);
                LogUtils.info("成功断言关键字“"+assertString+"”");
            }
    	} catch (Exception e) {
    		TestCase.result = false;
        	LogUtils.info("出现断言失败，具体断言败信息："+e.getMessage());
        	e.printStackTrace();
    	}
	}

}
