package com.goushuini.configuration;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

import com.goushuini.PracticeGouShuiNi171212.TestCase;
import com.goushuini.data.GetDataFromDataBase;
import com.goushuini.data.GetJsonData;
import com.goushuini.element.operation.AlertOperation;
import com.goushuini.element.operation.AssertOperation;
import com.goushuini.element.operation.ClickOperation;
import com.goushuini.element.operation.IframOperation;
import com.goushuini.element.operation.InputTextOperation;
import com.goushuini.element.operation.ScreenShotOperation;
import com.goushuini.element.operation.SelectOperation;
import com.goushuini.element.operation.SwitchWindowOperation;
import com.goushuini.utils.AppSwipeUtils;
import com.goushuini.utils.ElementLocationUtils;
import com.goushuini.utils.InitialUtils;
import com.goushuini.utils.LogUtils;

import io.appium.java_client.android.AndroidDriver;

/**
 * web端关键字，区分app端和web端，因为可以直接使用该代码应用于其他项目，
 * 不需要被小二、水泥电商等条件限制死
 * @author joy
 * @date 2017年12月14日
 */

public class WebActionKeyWords {
	
	//无参构造函数
	public WebActionKeyWords() {
		
	}	
	
    public void openBrowser (String product,String browser,String driverPath,String value3,
    		String value4,String elementLocation1,String elementLocation2) {
        try {
        	InitialUtils.webInitial(browser, driverPath);
            InitialUtils.webDriver.manage().window().maximize();
        	LogUtils.info("浏览器打开成功");
        } catch (Exception e) {
        	TestCase.result = false;
        	LogUtils.info("浏览器打开失败");
        	e.printStackTrace();
        }        
    }
    
    public void openURL (String product,String url,String value2,String value3,
    		String value4,String elementLocation1,String elementLocation2) {
    		InitialUtils.webDriver.get(url);
        	LogUtils.info("浏览器访问网址："+url);             
    }
    
    public void closeBrowser (String product,String url,String value2,String value3,
    		String value4,String elementLocation1,String elementLocation2) {
        if (InitialUtils.webDriver != null) {
        	InitialUtils.webDriver.quit();
        	LogUtils.info("浏览器关闭成功");
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
    
    public void addCookie (String product,String value1,String value2,String value3,
    		String value4,String elementLocation1,String elementLocation2) {
    	try {
    		Cookie ck1 = new Cookie (value1,value2);
            if (value3 == null || value4 == null) {
            	InitialUtils.webDriver.manage().addCookie(ck1);
            } else {
                Cookie ck2 = new Cookie (value3,value4);
                InitialUtils.webDriver.manage().addCookie(ck1);
                InitialUtils.webDriver.manage().addCookie(ck2);
            }
            Set<Cookie> coo = InitialUtils.webDriver.manage().getCookies();
            System.out.println("打印cookie数据："+coo);
            LogUtils.info("使用cookie"+coo+"登录");
    	} catch (Exception e) {
    		TestCase.result = false;
        	LogUtils.info("cookie登录出现异常，具体异常信息"+e.getMessage());
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
            InputTextOperation.inputText(InitialUtils.webDriver, ElementLocationUtils.elementLocation(elementLocation1), value1);
            LogUtils.info("app输入框成功输入"+value1);
        } catch (Exception e) {
        	TestCase.result = false;
	        LogUtils.info("往输入框里输入"+value1+"出现异常，具体异常信息为"+e.getMessage());
	        e.printStackTrace();
        }   	
    }
    
    /**
     * 输入的值需要从数据库里查出来（工单id）
     * @param product
     * @param value1
     * @param value2
     * @param value3
     * @param value4
     * @param elementLocation1
     * @param elementLocation2
     */
    public void inputTransId (String product,String cellphone,String value2,String value3,
    		String value4,String elementLocation1,String elementLocation2) {
        try { 
            InputTextOperation.inputText(InitialUtils.webDriver, ElementLocationUtils.elementLocation(elementLocation1), GetDataFromDataBase.getTransId(cellphone));
            LogUtils.info("app输入框成功输入"+GetDataFromDataBase.getTransId(cellphone));
        } catch (Exception e) {
        	TestCase.result = false;
	        LogUtils.info("往输入框里输入"+GetDataFromDataBase.getTransId(cellphone)+"出现异常，具体异常信息为"+e.getMessage());
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
            InputTextOperation.clearText(InitialUtils.webDriver, ElementLocationUtils.elementLocation(elementLocation1));
            LogUtils.info("web输入框成功清空");
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
            ClickOperation.buttonClick(InitialUtils.webDriver, ElementLocationUtils.elementLocation(elementLocation1));
            LogUtils.info("web端单击页面元素"+elementLocation1+"成功");
        } catch (Exception e) {
        	TestCase.result = false;
	        LogUtils.info("单击页面元素"+elementLocation1+"失败，具体异常信息为"+e.getMessage());
	        e.printStackTrace();
        } 
    }
    
    public void buttonClickContainParams (String product,String value1,String value2,String value3,
    		String value4,String elementLocation1,String elementLocation2) {
        try {
            ClickOperation.buttonClickContainParams(InitialUtils.webDriver,value1,elementLocation1);
            LogUtils.info("web端单击页面元素"+elementLocation1+"成功");
        } catch (Exception e) {
        	TestCase.result = false;
	        LogUtils.info("单击页面元素"+elementLocation1+"失败，具体异常信息为"+e.getMessage());
	        e.printStackTrace();
        } 
    }
    /**
     * 双击
     * @param product
     * @param value1
     * @param value2
     * @param value3
     * @param value4
     * @param elementLocation1
     * @param elementLocation2
     */
    public void doubleClick(String product,String value1,String value2,String value3,
    		String value4,String elementLocation1,String elementLocation2) {
    	try {
            ClickOperation.doubleClick(InitialUtils.webDriver, ElementLocationUtils.elementLocation(elementLocation1), InitialUtils.actions);
            LogUtils.info("web端双击页面元素"+elementLocation1+"成功");
        } catch (Exception e) {
        	TestCase.result = false;
	        LogUtils.info("双击页面元素"+elementLocation1+"失败，具体异常信息为"+e.getMessage());
	        e.printStackTrace();
        } 
    }
    
    /**
     * javaScript单击,适用于某个按钮被另外一个弹层遮挡时的场景，目前仅写了web端
     * 的javaScript单击，app端的遇到后再补充
     * @param product
     * @param value1
     * @param value2
     * @param value3
     * @param value4
     * @param elementLocation1
     * @param elementLocation2
     */
    public void javaScriptClick (String product,String value1,String value2,String value3,
    		String value4,String elementLocation1,String elementLocation2) { 
    	try {
            ClickOperation.javaScriptClick(InitialUtils.webDriver, ElementLocationUtils.elementLocation(elementLocation1));
            LogUtils.info("web端单击页面元素"+elementLocation1+"成功");		
    	} catch (Exception e) {
    		TestCase.result = false;
        	LogUtils.info("单击页面元素"+elementLocation1+"失败，具体异常信息为"+e.getMessage());
        	e.printStackTrace();
    	}
    }
    
    /**
     * 移动至元素位置后再单击,适用于子菜单的单击，目前仅写了web端
     * 的单击，app端的遇到后再补充
   	 * @param product
     * @param value1
     * @param value2
     * @param value3
     * @param value4
     * @param elementLocation1
     * @param elementLocation2
    */
    public void moveToElementClick (String product,String value1,String value2,String value3,
    		String value4,String elementLocation1,String elementLocation2) {
    	try {
            ClickOperation.moveToElementClick(InitialUtils.webDriver,InitialUtils.actions,ElementLocationUtils.elementLocation(elementLocation1));
            LogUtils.info("web端单击页面元素"+elementLocation1+"成功");		
    	} catch (Exception e) {
    		TestCase.result = false;
        	LogUtils.info("单击页面元素"+elementLocation1+"失败，具体异常信息为"+e.getMessage());
        	e.printStackTrace();
    	}
    }	
    
    /**
     * 根据工单id多表达式单击
     * @param product
     * @param attribute
     * @param key
     * @param cellphone
     * @param value4
     * @param elementLocation1
     * @param elementLocation2
     */
    public void transIdMultipleExpressionClick (String product,String attribute,String key,String cellphone,
    		String value4,String elementLocation1,String elementLocation2) {
        try {
        	ClickOperation.multipleExpressionClick(InitialUtils.webDriver, attribute, key, GetDataFromDataBase.getTransId(cellphone), 
        			ElementLocationUtils.elementLocation(elementLocation1), ElementLocationUtils.elementLocation(elementLocation2));
            LogUtils.info("根据工单id多表达式单击成功");		
        } catch (Exception e) {
        	TestCase.result = false;
        	LogUtils.info("根据工单id多表达式单击失败，具体异常信息为"+e.getMessage());
        	e.printStackTrace();
        }       
    }
    
    /**
     * 根据竞价单id多表达式单击
     * @param product
     * @param attribute
     * @param key
     * @param cellphone
     * @param value4
     * @param elementLocation1
     * @param elementLocation2
     */
    public void auctionIdMultipleExpressionClick (String product,String attribute,String key,String cellphone,
    		String value4,String elementLocation1,String elementLocation2) {
        try {
        	ClickOperation.multipleExpressionClick(InitialUtils.webDriver, attribute, key, GetDataFromDataBase.getAuctionId(cellphone), 
        			ElementLocationUtils.elementLocation(elementLocation1), ElementLocationUtils.elementLocation(elementLocation2));
            LogUtils.info("根据竞价单id多表达式单击成功");		
        } catch (Exception e) {
        	TestCase.result = false;
        	LogUtils.info("根据竞价单id多表达式单击失败，具体异常信息为"+e.getMessage());
        	e.printStackTrace();
        }       
    }
    
    /**
     * 根据托运单id多表达式单击
     * @param product
     * @param attribute
     * @param key
     * @param cellphone
     * @param value4
     * @param elementLocation1
     * @param elementLocation2
     */
    public void itemIdMultipleExpressionClick (String product,String attribute,String key,String cellphone,
    		String value4,String elementLocation1,String elementLocation2) {
        try {
        	ClickOperation.multipleExpressionClick(InitialUtils.webDriver, attribute, key, GetDataFromDataBase.getItemId(cellphone), 
        			ElementLocationUtils.elementLocation(elementLocation1), ElementLocationUtils.elementLocation(elementLocation2));
            LogUtils.info("根据托运单id多表达式单击成功");		
        } catch (Exception e) {
        	TestCase.result = false;
        	LogUtils.info("根据托运单id多表达式单击失败，具体异常信息为"+e.getMessage());
        	e.printStackTrace();
        }       
    }
    
    /**
     * 根据工单id单击，元素的唯一标志需要从数据库里取出工单id
     * @param product
     * @param attribute
     * @param key
     * @param cellphone
     * @param value4
     * @param elementLocation1
     * @param elementLocation2
     */
    public void transIdClick (String product,String cellphone,String value2,String value3,
    		String value4,String elementLocation1,String elementLocation2) {
        try {
        	ClickOperation.buttonClickContainParams(InitialUtils.webDriver, GetDataFromDataBase.getTransId(cellphone), elementLocation1); 
            LogUtils.info("根据工单id单击成功");		
        } catch (Exception e) {
        	TestCase.result = false;
        	LogUtils.info("根据工单id单击失败，具体异常信息为"+e.getMessage());
        	e.printStackTrace();
        }       
    }
    
    /**
     * 根据竞价单id单击，元素的唯一标志需要从数据库里取出竞价单id
     * @param product
     * @param attribute
     * @param key
     * @param cellphone
     * @param value4
     * @param elementLocation1
     * @param elementLocation2
     */
    public void auctionIdClick (String product,String cellphone,String value2,String value3,
    		String value4,String elementLocation1,String elementLocation2) {
        try {
        	ClickOperation.buttonClickContainParams(InitialUtils.webDriver, GetDataFromDataBase.getAuctionId(cellphone), elementLocation1); 
            LogUtils.info("根据竞价单id单击成功");		
        } catch (Exception e) {
        	TestCase.result = false;
        	LogUtils.info("根据竞价单id单击失败，具体异常信息为"+e.getMessage());
        	e.printStackTrace();
        }       
    }
    
    /**
     * 根据托运单id单击，元素的唯一标志需要从数据库里取出托运单id
     * @param product
     * @param attribute
     * @param key
     * @param cellphone
     * @param value4
     * @param elementLocation1
     * @param elementLocation2
     */
    public void itemIdClick (String product,String cellphone,String value2,String value3,
    		String value4,String elementLocation1,String elementLocation2) {
        try {
        	ClickOperation.buttonClickContainParams(InitialUtils.webDriver, GetDataFromDataBase.getItemId(cellphone), elementLocation1); 
            LogUtils.info("根据托运单id单击成功");		
        } catch (Exception e) {
        	TestCase.result = false;
        	LogUtils.info("根据托运单id单击失败，具体异常信息为"+e.getMessage());
        	e.printStackTrace();
        }       
    }
    
    public void switchWindow (String product,String windowPage,String value2,String value3,
    		String value4,String elementLocation1,String elementLocation2) {
    	try {
        	SwitchWindowOperation.switchWindow(InitialUtils.webDriver, windowPage);
            LogUtils.info("切换窗口成功");		
        } catch (Exception e) {
        	TestCase.result = false;
        	LogUtils.info("切换窗口失败，具体异常信息为"+e.getMessage());
        	e.printStackTrace();
        } 
    }
    
    /**
     * 切入iframe
     * @param product
     * @param iframe
     * @param value2
     * @param value3
     * @param value4
     * @param elementLocation1
     * @param elementLocation2
     */
    public void intoIframe (String product,String iframe,String value2,String value3,
    		String value4,String elementLocation1,String elementLocation2) {
    	try {
    		IframOperation.intoIframe(InitialUtils.webDriver, iframe);
            LogUtils.info("成功切入iframe"+iframe);		
        } catch (Exception e) {
        	TestCase.result = false;
        	LogUtils.info("切入"+iframe+"iframe失败，具体异常信息为"+e.getMessage());
        	e.printStackTrace();
        } 
    }
    
    /**
     * 切入含工单id的iframe,待切入的iframe名称中含有工单id，工单id需要从数据库中查出
     * @param product
     * @param iframe
     * @param value2
     * @param value3
     * @param value4
     * @param elementLocation1
     * @param elementLocation2
     */
    public void intoIframeContainsTransId (String product,String iframe,String cellphone,String value3,
    		String value4,String elementLocation1,String elementLocation2) {
    	try {
    		IframOperation.intoIframeContainsParams(InitialUtils.webDriver, iframe, GetDataFromDataBase.getTransId(cellphone));
            LogUtils.info("成功切入iframe");		
        } catch (Exception e) {
        	TestCase.result = false;
        	LogUtils.info("切入iframe失败，具体异常信息为"+e.getMessage());
        	e.printStackTrace();
        } 
    }
    
    /**
     * 切出iframe
     * @param product
     * @param iframe
     * @param value2
     * @param value3
     * @param value4
     * @param elementLocation1
     * @param elementLocation2
     */
    public void outIframe (String product,String value1,String value2,String value3,
    		String value4,String elementLocation1,String elementLocation2) {
    	try {
    		IframOperation.outIframe(InitialUtils.webDriver);
            LogUtils.info("成功切出iframe");		
        } catch (Exception e) {
        	TestCase.result = false;
        	LogUtils.info("切出iframe失败，具体异常信息为"+e.getMessage());
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
    		AssertOperation.assertString(InitialUtils.webDriver, assertString);
            LogUtils.info("成功断言关键字“"+assertString+"”");
    	} catch (Exception e) {
    		TestCase.result = false;
        	LogUtils.info("出现断言失败，具体断言败信息："+e.getMessage());
        	e.printStackTrace();
    	}
	}
    
    /**
     * 系统弹出的确认、取消框处理
     * @param product
     * @param assertString
     * @param value2
     * @param value3
     * @param value4
     * @param elementLocation1
     * @param elementLocation2
     */
    public void confirmHander(String product,String message,String value2,String value3,
    		String value4,String elementLocation1,String elementLocation2) {
    	try {
    		AlertOperation.confirmHander(InitialUtils.webDriver, message);
            LogUtils.info("网页弹出确认、取消按钮框，已点击“"+message+"”按钮");
    	} catch (Exception e) {
    		TestCase.result = false;
        	LogUtils.info("网页弹出框点击失败，具体异常信息为："+e.getMessage());
        	e.printStackTrace();
    	}
	}
    
    /**
     * 使用index选择数据
     * @param product
     * @param index
     * @param text
     * @param value3
     * @param value4
     * @param elementLocation1
     * @param elementLocation2
     */
    public static void getSelectDataByIndex(String product,String index,String text,String value3,
    		String value4,String elementLocation1,String elementLocation2) {
    	try {
    		SelectOperation.getSelectDataByIndex(InitialUtils.webDriver, ElementLocationUtils.elementLocation(elementLocation1),Integer.parseInt(index), text);
            LogUtils.info("选择的下拉框数据为"+text);
    	} catch (Exception e) {
    		TestCase.result = false;
        	LogUtils.info("下拉框数据选择失败，具体异常信息为："+e.getMessage());
        	e.printStackTrace();
    	}
    }
    /**
     * 使用select的value属性选择数据
     * @param product
     * @param index
     * @param text
     * @param value3
     * @param value4
     * @param elementLocation1
     * @param elementLocation2
     */
    public static void getSelectDataByValue(String product,String value,String text,String value3,
    		String value4,String elementLocation1,String elementLocation2) {
    	try {
    		SelectOperation.getSelectDataByValue(InitialUtils.webDriver, ElementLocationUtils.elementLocation(elementLocation1),value, text);
            LogUtils.info("选择的下拉框数据为"+text);
    	} catch (Exception e) {
    		TestCase.result = false;
        	LogUtils.info("下拉框数据选择失败，具体异常信息为："+e.getMessage());
        	e.printStackTrace();
    	}
    }
    /**
     * 使用select的选项的文字选择数据
     * @param product
     * @param index
     * @param text
     * @param value3
     * @param value4
     * @param elementLocation1
     * @param elementLocation2
     */
    public static void getSelectDataByText(String product,String text,String value2,String value3,
    		String value4,String elementLocation1,String elementLocation2) {
    	try {
    		SelectOperation.getSelectDataByVisibleText(InitialUtils.webDriver, ElementLocationUtils.elementLocation(elementLocation1), text);
            LogUtils.info("选择的下拉框数据为"+text);
    	} catch (Exception e) {
    		TestCase.result = false;
        	LogUtils.info("下拉框数据选择失败，具体异常信息为："+e.getMessage());
        	e.printStackTrace();
    	}
    }
}
