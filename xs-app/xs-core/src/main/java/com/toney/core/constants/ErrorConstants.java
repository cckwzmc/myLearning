package com.toney.core.constants;

public final class ErrorConstants {
    public static class ErrorType {
        public final static int UNKNOWN = -1;
        public final static int GENERAL = 0;
        public final static int DAO = 100;
        public final static int EI = 101;
    }

    public static class SystemCode {
        public final static int UNKNOWN = -1;
        public final static int PORTAL = 100;
        public final static int TRADE = 101;
        public final static int PUC = 201;
        public final static int UUC = 202;
        public final static int CSP = 203;
        public final static int RM = 204;
        public static final int SSO = 205;
        public static final int POINT = 206;
        
        public final static int SMS = 301;
        public final static int EMAIL = 401;
		public static final int PRODUCT =206;
    }

    public class EIErrorCode {
        /**
         * 外部接口异常
         */
        public final static String SYSTEM_EXCEPTION = "00001";
        public final static String UUC_EXCEPTION = "01000";
        public final static String PUC_EXCEPTION = "01000";
        public final static String CSP_EXCEPTION = "02000";
        public final static String TRADE_EXCEPTION = "20000";
        public final static String UUC_SYS_PARAM = "01001";
        public final static String CSP_SYS_PARAM = "02001";
        public final static String SSO_EXCEPTION = "01000";
        public final static String SSO_SYS_PARAM = "02000";
        
        public final static String RM_EXCEPTION = "03000";
        
        public final static String SMS_EXCEPTION = "04000";
        public final static String EMAIL_EXCEPTION = "05000";
        public static final String PRODUCT_EXCEPTION = "00001";
        public final static String POINT_EXCEPTION = "06000";
         
        
        /**
         * TRADE 接口异常
         */
        public final static String TRADE_ORDER_201001 = "20100";//订单列表异常
        public final static String TRADE_ORDER_201002 = "201002";//订单详情异常
        public final static String TRADE_ORDER_201003 = "201003";//撤销订单
        
        
        public final static String TRADE_REFUND_20200 = "20200";//退换货列表的异常
        public final static String TRADE_REFUND_20201 = "20201";//创建退换货申请的异常
        public final static String TRADE_REFUND_20202 = "20202";//查询某个唯一的退换货数据的异常
        public final static String TRADE_REFUND_20203 = "20203";//查询当前退换货订单的可退换货数量
        
        /**
         * 返回结果为空
         */
        public final static String NOTRECORD_EXCEPTION = "00002";

        /**
         * 外部接口返回错误编码
         */
        public final static String EXISTERROR_EXCEPTION = "00003";
		
    }

    public class EIErrorCodeMessage{
    	public final static String PORTAL_SSO_01000="调用SSO接口异常";
    	public final static String PORTAL_SSO_02000="返回参数有错误";
    }
    
    public class BusinessErorrCode {
        public final static String PORTAL_00010 = "";
        public final static String PORTAL_01000 = "";

        public final static String PORTAL_00001 = "";
        public final static String PORTAL_00002 = "";
        public final static String PORTAL_00003 = "";// 只能创建10条收货地址
        public final static String PORTAL_00004 = "";
    }

}
