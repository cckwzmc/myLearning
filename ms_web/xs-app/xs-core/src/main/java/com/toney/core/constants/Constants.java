package com.toney.core.constants;

public class Constants {

    /**
     * 收货地址类型编码
     */
    public static final String PORTAL_ADDRESS_TYPE_PROVINCE = "1";// 省份
    public static final String PORTAL_ADDRESS_TYPE_CITY = "2";// 市区
    public static final String PORTAL_ADDRESS_TYPE_AREA = "3";// 县区
    public static final String PORTAL_ADDRESS_TYPE_POST = "4";// 邮编
    public static final String PORTAL_ADDRESS_TYPE_CODE = "5";// 区号

    public static final String PORTAL_PARAM_SEX = "sex";// 性别
    public static final String PORTAL_PARAM_WORK = "work";// 身份
    public static final String PORTAL_PARAM_DEGREE = "degree";// 学历
    public static final String PORTAL_PARAM_MONTH_INCOME = "monthIncome";// 月收入
    public static final String PORTAL_PARAM_MARRIAGE = "marriage";// 婚姻状态

    /**
     * json 数据
     */
    public static final String JSON_MODEL__DATA = "data";

    /**
     * 渠道ID 11：官网 12：无线 13：秀团
     */
    public static final int PORTAL_CHANNELID = 11;
    public static final int WIRELESS_CHANNELID = 12;
    public static final int XIUTUAN_CHANNELID = 13;

    /**
     * RM resourceType
     */

    public static final String RM_RESOURCE_TYPE_GIFTCARD = "2";

    public static final String RM_RESOURCE_TYPE_RECHARGECARD = "1";

    public static final String RM_RESOURCE_USEFUL_GIFT_USEABLE = "2";

    /**
     * order 相关
     */
    public static final String EI_ORDER__DETAIL_MODEL = "bizOrderDO";
    public static final String EI_ORDER__DETAIL_PAGE = "pagingList";
    public static final String EI_ORDER__DETAIL_STATUS = "statBizOrderDO";

    /**
     * refund 退换货相关的
     */
    public static final String EI_REFUND_MODEL = "refundItemFlow";
    public static final String EI_REFUND_MODEL_LIST = "refundItemFlowList";
    public static final String EI_REFUND_TYPE_RETURN = "1";// 退货申请标志
    public static final String EI_REFUND_TYPE_EXCHANGE = "2";// 换货申请标志
    public static final String EI_REFUND_CODE_1 = "1";// 申请的退换必要参数校验失败
    public static final String EI_REFUND_CODE_2 = "2";// 有申请的退换货数据时候
    public static final String EI_REFUND_ORDERSTATUS = "-1";//退换货的申请状态
    public static final String EI_REFUND_DATA_VAL_FAIL="数据校验失败!";
    public static final String EI_REFUND_PARA_TEST_FAIL="必要参数校验失败!!";
    public static final String EI_REFUND_SYS_ERROR_MSG ="系统发生异常!";
    public static final String EI_REFUND_ERROR_MSG ="不好意思，当前用户没有可以做退换货的数据!";
    public static final String EI_REFUND_DATA_REPEA_FORBID ="你不能够重复提交该退换货数据!!";
    public static final String EI_REFUND_GOODS_APPLY ="退换货申请";
    public static final String EI_REFUND_NOT_ACCEPT ="未受理";
    public static final String EI_REFUND_OPRETIONVAL="0";
    public static final String EI_REFUND_INPUT_GOOD_NAME="输入商品名称";
    public static final String EI_REFUND_SUUCESS_FLAG="0";
    public static final Integer EI_REFUND_QUANTITY=0;
    

    

    /**
     * PORTAL邮件类型
     */
    public static final int EMAIL_TYPE_REGUSER = 1;// 注册邮件
    public static final int EMAIL_TYPE_RETRIEVEPW = 2;// 重置密码邮件
    public static final int EMAIL_SHARE = 3;// 分享商品邮件
    public static final int EMAIL_INVITE = 4;// 好友邀请邮件
    public static final int EMAIL_OTHER = 99;// 其他邮件

    public static final int EMAIL_STATUS_VALID = 1;// 有效
    public static final int EMAIL_STATUS_NOVALID = 0;// 无效
    
    /**
     * 短信类型
     */
    public static final int SMS_TYPE_REGUSER = 1;// 注册短信
    public static final int SMS_TYPE_RETRIEVEPW = 2;// 重置密码短信
    public static final int SMS_TYPE_SUBMESSAGE = 3;// 订阅消息短信
    public static final int SMS_TYPE_DRAWAPPLY = 4;// 提现申请短信
    public static final int SMS_TYPE_LOTTERY = 5;// 抽奖短信
    public static final int SMS_TYPE_OTHER = 99;// 其他短信

    public static final int SMS_STATUS_VALID = 1;// 有效
    public static final int SMS_STATUS_NOVALID = 0;// 无效
    
    /**
     * 虚拟账户管理页面活动页类型
     */
    public static final String ACCOUNT_CHANGE_RECORD = "AccountChangeRecord";// 帐户变动记录
    public static final String WITH_DRAWLS_RECORD = "WithdrawalsRecord";// 提现记录
    public static final String REFUND_RECORD = "RefundRecord";// 在线退款记录
    public static final String VCARD_RECORD = "VCardRecord";// 充值记录
    
    /**
     * 用户反馈相关
     */
    // 用户反馈：0用户 ，1客服
    public static final int FEEDBACK_PERSON = 0;

    // 用户反馈：等待处理
    public static final int FEEDBACK_STATUS = 0;
    
    /**
     * 站内信相关
     */
    public static final String ADMINISTRATOR_ID = "0";
    public static final String ADMINISTRATOR_NAME = "走秀网管理员";
}
