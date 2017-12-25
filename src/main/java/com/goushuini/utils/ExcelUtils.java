package com.goushuini.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * 对excel操作的相关方法
 * @author 邹元
 * @date 2017年12月14
 */

public class ExcelUtils {
    
    private static XSSFWorkbook workbook;
    private static XSSFSheet sheet;
    private static XSSFRow row;
    private static XSSFCell cell;
    
    /**
     * 从excel表存储路径中读取excel
     * @param path
     */
    public static void readExcel(String path) {
        try {
            FileInputStream in = new FileInputStream(path);
            workbook = new XSSFWorkbook (in); 
            in.close();
        } catch (Exception e) {
            System.out.println("");
            e.printStackTrace();
        }         
    }
        
    public static int getRowCount(String sheetName) {
        sheet = workbook.getSheet(sheetName);
        //getLastRowNum()返回值是n-1，去掉表头，正文数据正好是n-1
        return sheet.getLastRowNum();        
    }
    
    public static void setCell(int rownum,int cellnum,String result,String sheetName,String path) {
        sheet = workbook.getSheet(sheetName);
        row = sheet.getRow(rownum);
        cell = row.getCell(cellnum);
        if (cell == null) {
            cell = row.createCell(cellnum);
            cell.setCellValue(result);
        }else {
            cell.setCellValue(result);
        }
        //值设置好后再存入excel中
        try {
            FileOutputStream out = new FileOutputStream(path);
            workbook.write(out);
            out.close();
        } catch (FileNotFoundException e) {
            System.out.println("文档不存在");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("文档正在被使用");
            e.printStackTrace();
        }       
    }
    
    public static String getCell(String sheetName,int rownum,int cellnum) {
        sheet = workbook.getSheet(sheetName);
        row = sheet.getRow(rownum);
        cell = row.getCell(cellnum);
        if (cell != null) {
            return cell.getStringCellValue();
        } else {
            return null;
        }    
    }
    
    public static List<String> getMultipleCell(String sheetName,int rownum) {
        String suiteCaseId = null;
        String testCaseId = null;
        String testDevices = null;
        String production = null;
        String function = null;
        String operation = null;
        String keyWords = null;
        String keyWordsFunction = null;
        String value1 = null;
        String value2 = null;
        String value3 = null;
        String value4 = null;
        String elementLocation1 = null;
        String elementLocation2 = null;
        List<String> list = new ArrayList();
        suiteCaseId = getCell(sheetName,rownum,0);
        testCaseId = getCell(sheetName,rownum,1);
        testDevices = getCell(sheetName,rownum,2);
        production = getCell(sheetName,rownum,3);
        function = getCell(sheetName,rownum,4);
        operation = getCell(sheetName,rownum,5);
        keyWords = getCell(sheetName,rownum,6);
        keyWordsFunction = getCell(sheetName,rownum,7);
        value1 = getCell(sheetName,rownum,8);
        value2 = getCell(sheetName,rownum,9);      
        value3 = getCell(sheetName,rownum,10);        
        value4 = getCell(sheetName,rownum,11);
        elementLocation1 = getCell(sheetName,rownum,12);
        elementLocation2 = getCell(sheetName,rownum,13);
        list.add(suiteCaseId);
        list.add(testCaseId);
        list.add(testDevices);
        list.add(production);
        list.add(function);
        list.add(operation);
        list.add(keyWords);
        list.add(keyWordsFunction);
        list.add(value1);
        list.add(value2);
        list.add(value3);
        list.add(value4);
        list.add(elementLocation1);
        list.add(elementLocation2); 
        return list;
    }

}
