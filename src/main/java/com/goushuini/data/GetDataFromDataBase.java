package com.goushuini.data;

import com.goushuini.utils.LogUtils;
import com.goushuini.utils.MySqlDataBaseUtils;

/**
 * 从数据库中读取数据
 * @author PC
 * @date 2017年12月19日
 */

public class GetDataFromDataBase {
	//返回用户id
    public static String getUserId (String cellphone) {
        String userId = null;
        //要连接数据库哪个库UIC库
        String userIDSql="SELECT id FROM uic.`user` WHERE cellphone='"
                  +cellphone+ "'";
        userId = MySqlDataBaseUtils.querySql(Contents.URL_UIC, userIDSql); 
        System.out.println("获取到的用户ID是："+userId);
        LogUtils.info("获取到的用户ID是："+userId);
        return userId;       
    }
    //返回工单id
    public static String getTransId (String cellphone) {
        String transId = null;
        String userID = getUserId(cellphone);
        //要连接数据库哪个库goodsowner        
        String transIDSql="SELECT id FROM goodsowner.`trans_req_order` WHERE "
                  + "user_id='"+userID+ "'";
        transId = MySqlDataBaseUtils.querySql(Contents.URL_GOODSOWNER, transIDSql); 
        System.out.println("获取到的工单ID是："+transId);
        LogUtils.info("获取到的工单ID是："+transId);
        return transId;       
    }
    //返回竞价单id
    public static String getAuctionId (String cellphone) {
        String auctionId = null;
        String transId = getTransId(cellphone);
        //要连接数据库哪个库goodsowner
        String auctionIDSql="SELECT auction_id FROM goodsowner.`trans_req_order` WHERE "
                  + "id='"+transId+ "'";
        auctionId = MySqlDataBaseUtils.querySql(Contents.URL_GOODSOWNER, auctionIDSql);        
        System.out.println("获取到的竞价单ID是："+auctionId);
        LogUtils.info("获取到的竞价单ID是："+auctionId);
        return auctionId;       
    }
    
    //返回托运单id
    public static String getItemId (String cellphone) {
        String itemId = null;
        String auctionId = getAuctionId(cellphone);
        //要连接数据库哪个库carrier
        String itemIDSql="SELECT id FROM carrier.`bid_item` WHERE "
                  + "auction_id='"+auctionId+ "'";
        itemId = MySqlDataBaseUtils.querySql(Contents.URL_CARRIER, itemIDSql);        
        System.out.println("获取到的托运单ID是："+itemId);
        LogUtils.info("获取到的托运单ID是："+itemId);
        return itemId;       
    }
}
