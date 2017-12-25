package com.goushuini.PracticeGouShuiNi171212;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.PropertyConfigurator;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.log4testng.Logger;

import com.goushuini.configuration.AnalyzeExcelKeyWords;
import com.goushuini.data.Contents;
import com.goushuini.utils.ExcelUtils;
import com.goushuini.utils.InitialUtils;
import com.goushuini.utils.LogUtils;

public class TestCase {
  AnalyzeExcelKeyWords analyze = new AnalyzeExcelKeyWords();
  public static boolean result;
  @Test
  public void testCase() {      
      for (int i = 1;i <= ExcelUtils.getRowCount(Contents.SHEETNAME);i++) {
          if ("yes".equals(ExcelUtils.getCell(Contents.SHEETNAME, i, 3).toLowerCase().trim())) {
              String mainSheetSuiteCaseId = ExcelUtils.getCell(Contents.SHEETNAME, i, 0); 
              String testCaseSheetName = ExcelUtils.getCell(Contents.SHEETNAME, i, 2); 
              System.out.println("将要执行的测试用例id为："+mainSheetSuiteCaseId);
              System.out.println(testCaseSheetName+"测试用例执行");
              //在日志中打印测试用例开始执行
              LogUtils.startTestCase(mainSheetSuiteCaseId);
              //设定测试用例的当前结果为true
              result = true;
              for (int j = 1;j <= ExcelUtils.getRowCount(testCaseSheetName);j++) {
                  analyze.analyzeExcel(mainSheetSuiteCaseId,testCaseSheetName,j);                 
                  if (result == false) {                      
                      ExcelUtils.setCell(i, 4, "测试用例执行失败", Contents.SHEETNAME, Contents.PATH);
                      //在日志中打印测试用例执行完毕
                      LogUtils.info(mainSheetSuiteCaseId+"测试用例执行失败");
                      LogUtils.endTestCase(mainSheetSuiteCaseId);
                      break;
                  } 
              }   
              if (result == true) {                      
                  ExcelUtils.setCell(i, 4, "测试用例执行成功", Contents.SHEETNAME, Contents.PATH);           
                  //在日志中打印测试用例执行完毕
                  LogUtils.info(mainSheetSuiteCaseId+"测试用例执行成功");
                  LogUtils.endTestCase(mainSheetSuiteCaseId);
              }
          } else {
              String mainSheetSuiteCaseId = ExcelUtils.getCell(Contents.SHEETNAME, i, 0);
              LogUtils.info(mainSheetSuiteCaseId+"测试用例不执行");
              System.out.println(mainSheetSuiteCaseId+"测试用例不执行");
          }
      }
      
  }
   
  @BeforeMethod
  public void beforeMethod() {
      //读取excel数据
      ExcelUtils.readExcel(Contents.PATH);
      //读取日志文件
      PropertyConfigurator.configure(Contents.LOGPATH);
  }
  
  @AfterMethod
  public void afterMethod() {
	  
  } 
}
