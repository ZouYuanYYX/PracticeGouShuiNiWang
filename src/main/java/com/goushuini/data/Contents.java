package com.goushuini.data;

/**
 * 存放固定值，如excel文件路径等
 * @author joy
 * @date 2017年12月14日
 */

public class Contents {
	//excel测试用例表相关数据配置
    public static final String PATH = "F:\\Users\\maven\\workspace\\PracticeGouShuiNi171212\\data\\testdata.xlsx";
    public static final String LOGPATH = "F:\\Users\\maven\\workspace\\PracticeGouShuiNi171212\\data\\log4j.properties";
    public static final String SHEETNAME = "Suite";
   
    //数据库连接相关配置
    public static final String DB_URL = "jdbc:mysql:192.168.158.206:3307/";
    public static final String DB_USER = "dbadmin";
    public static final String DB_PASSWORD = "hello1234";
    public static final String URL_UIC="jdbc:mysql://192.168.158.206:3307/uic?"
            + "user=dbadmin&password=hello1234&use"
            + "Unicode=true&characterEncoding=UTF8";
    public static final String URL_GOODSOWNER="jdbc:mysql://192.168.158.206:3307/goodsowner?"
            + "user=dbadmin&password=hello1234&use"
            + "Unicode=true&characterEncoding=UTF8";
    public static final String URL_CARRIER="jdbc:mysql://192.168.158.206:3307/carrier?"
            + "user=dbadmin&password=hello1234&use"
            + "Unicode=true&characterEncoding=UTF8";
    
    //app相关配置
    //要安装的app包名
    public static final String SHIPPER = "huoYunZhan-release.apk";
    public static final String CARRIER = "wuliudidi-2.4.0-http.apk";
    //APP存放的路径
    public static final String SHIPPER_PATH = "F:\\Users\\maven\\workspace\\PracticeGouShuiNi171212\\apps\\huoYunZhan-release.apk";
    public static final String CARRIER_PATH = "F:\\Users\\maven\\workspace\\PracticeGouShuiNi171212\\apps\\wuliudidi-2.4.0-http.apk";
    //使用cmd命令开启appium服务
    public static final String SHIPPER_CMD="cmd /c appium -a 127.0.0.1 -p 4723 --session-override -U 127.0.0.1:62001 >F:\\SeleniumUiAutom"
    		+ "ation\\workspace\\PracticeGouShuiNi171212\\logs\\Appium_shipper.txt";
    public static final String CARRIER_CMD="cmd /c appium -a 127.0.0.1 -p 4725 --session-override -U 127.0.0.1:6555 >F:\\SeleniumUiAutom"
    		+ "ation\\workspace\\PracticeGouShuiNi171212\\logs\\Appium_carrier.txt";
    //要连接的设备地址
    public static final String SHIPPER_DEVICE = "adb connect 127.0.0.1:62001";
    public static final String CARRIER_DEVICE = "adb connect 127.0.0.1:6555";
    //要连接的URL地址
    public static final String SHIPPER_URL = "http://127.0.0.1:4723/wd/hub";
    public static final String CARRIER_URL = "http://127.0.0.1:4725/wd/hub";
    //要连接的设备名
    public static final String SHIPPER_DEVICE_NAME = "127.0.0.1:62001";
    public static final String CARRIER_DEVICE_NAME = "127.0.0.1:6555";

}
