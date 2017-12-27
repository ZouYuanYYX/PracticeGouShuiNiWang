package com.goushuini.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.goushuini.data.Contents;

public class MySqlDataBaseUtils {
    private static Connection conn = null;
    private static PreparedStatement stmt = null;
    public static String queryResult = null;
    /**
     * 连接数据库的方法封装
     * @param url
     * @return
     */
	private static Connection createConnection(String url) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, Contents.DB_USER, Contents.DB_PASSWORD);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("MySqlDriver not found");
        } catch (SQLException e1) {
            e1.printStackTrace();
            System.out.println("user or password is not correct");
        }       
        return conn;        
    }
	
	/**查询sql并将查询的值返回
     * @param url  要连接的数据库地址
     * @param sql  要查询的sql语句
     * @return
     */
    public static String querySql (String url,String sql) {
        try {
            conn = createConnection(url);
            stmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet result = stmt.executeQuery();
            //获取列数
            int colNum = result.getMetaData().getColumnCount();
            //获取行数
            result.last();
            int rowNum = result.getRow();
            String[][] idSet = new String[rowNum][colNum];
            System.out.println("查询结果有"+rowNum+"行  "+colNum+"列");
            result.first();
            for (int i=0;i<rowNum;i++) {
                for (int j=0;j<colNum;j++){
                    idSet[i][j] = result.getString(j+1);
                }
                result.next();
            }
            //获取最后一个数据
            queryResult=idSet[rowNum-1][colNum-1];            
        } catch (SQLException e) {
        	LogUtils.info("读取数据库数据出现异常，具体异常信息为："+e.getMessage());
            e.printStackTrace();
        }
        return queryResult;        
    }
}
