package com.toney.core.constants;
/** 
 *************************************************************** 
 * <p>
 * @DESCRIPTION : 
 * @AUTHOR : xiu@xiu.com 
 * @DATE :2012-4-18 下午3:02:11
 * </p>
 **************************************************************** 
 */
public final class ErrConstants {
    public class GeneralErrorCode {
        public final static String BIZ_ADDRESS_LIMIT_FORBIDDEN = "10001"; //超过允许添加的地址条数
        public final static String BIZ_NOTRECORD_ERR = "10002"; //未找到对应记录
        
    }
    //EI的错误编码
    // 9AABB
    // AA: 00 - PORTAL, 01 - Trade, 02 - PUC, 03 - UUC, 04 - CSP, 05 - SSO, 06 - RM, 07 - SMS, 08 - EMAIL
    // BB: 00 ~ 19 系统预留，20以上用于自定义
    //     01 - General Error, 02 - Biz Error
    public class EIErrorCode {
        //TRADE - 01
        public final static String EI_TRADE_GENERAL_ERR = "90101";
        public final static String EI_TRADE_BIZ_ERR = "90102";      
        //PUC - 02
        public final static String EI_PUC_GENERAL_ERR = "90201";
        public final static String EI_PUC_BIZ_ERR = "90202";        
        //UUC - 03
        public final static String EI_UUC_GENERAL_ERR = "90301";
        public final static String EI_UUC_BIZ_ERR = "90302";        
        //CSP - 04
        public final static String EI_CSP_GENERAL_ERR = "90401";
        public final static String EI_CSP_BIZ_ERR = "90402";
        //SSO - 05
        public final static String EI_SSO_GENERAL_ERR = "90501";
        public final static String EI_SSO_BIZ_ERR = "90502";
        //RM - 06
        public final static String EI_RM_GENERAL_ERR = "90601";
        public final static String EI_RM_BIZ_ERR = "90602";
        //SMS - 07
        public final static String EI_SMS_GENERAL_ERR = "90701";
        public final static String EI_SMS_BIZ_ERR = "90702";
        //EMAIL - 08
        public final static String EI_EMAIL_GENERAL_ERR = "90801";
        public final static String EI_EMAIL_BIZ_ERR = "90802";
    }
}
