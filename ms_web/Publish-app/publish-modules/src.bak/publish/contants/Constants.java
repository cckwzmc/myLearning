package com.toney.publish.contants;

/**
 *************************************************************** 
 * <p>
 * 
 * @DESCRIPTION :
 * @AUTHOR : xiu@xiu.com
 * @DATE :2012-4-7 下午12:09:35
 *       </p>
 **************************************************************** 
 */
public final class Constants {
    public final static String USER_AUTH_INFO_ATTR = "USER_INFO";
    public final static String MDC_SSOUSERID_KEY = "ssoUserId";

    // 消息订阅
    public static final String SUB_MESSAGE = "1";

    // 取消消息订阅
    public static final String SUB_MESSAGE_CANCEL = "0";

    //分页记录数
    public static final int PAGE_SIZE = 15;
    
    //分页首页编号
    public static final int FIRST_PAGE = 1;
    
    //走秀网渠道编号
    public static final long CHANNELID_XIU = 11L;
    
    //优惠卡券 有效标识
    public static final int COUPON_VALID = 1; 
    
    //优惠卡券 默认类型
    public static final String COUPON_TYPE_DEFAULT = "0"; 
    
    
    //优惠卡券 终端用户标识
    public static final String COUPON_TERMINAL_USER = "2"; 
    
    //优惠卡券  赠品类型 
    public static final int COUPON_CARD_TYPE_GIFT = 3; 
    
    //STOREID
    public static final int STOREID = 10154; 
    
 	//日期格式
 	public static final String DATE_FORMAT_STYLE = "yyyy-MM-dd HH:mm:ss";
 	
 	//CPS_TYPE==1表示访问是从媒体渠道过来的
	public static final String MEDIA_CPS_TYPE = "1";
    
    // 走秀域名
    public static final String XIU_DOMAIN = ".xiu.com"; 
	/**
	 * json 数据
	 */
	public static final String JSON_MODEL_DATA = "data";
}
