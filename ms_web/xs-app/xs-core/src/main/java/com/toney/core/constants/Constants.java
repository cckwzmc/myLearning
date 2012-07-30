package com.toney.core.constants;

public class Constants {

	/**
	 * 收货地址类型编码
	 */
	public static final String PORTAL_ADDRESS_TYPE_PROVINCE = "1";// 省份
	public static final String PORTAL_ADDRESS_TYPE_CITY = "2";// 市区
	public static final String PORTAL_ADDRESS_TYPE_AREA = "3";// 县区
	public static final String PORTAL_ADDRESS_TYPE_POST_CODE = "4";// 邮编
	public static final String PORTAL_ADDRESS_TYPE_AREA_CODE = "5";// 区号

	public static final String PORTAL_PARAM_SEX = "sex";// 性别
	public static final String PORTAL_PARAM_WORK = "work";// 身份
	public static final String PORTAL_PARAM_DEGREE = "degree";// 学历
	public static final String PORTAL_PARAM_MONTH_INCOME = "monthIncome";// 月收入
	public static final String PORTAL_PARAM_MARRIAGE = "marriage";// 婚姻状态
	public static final String PORTAL_PARAM_BANK = "bank";// 银行

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
	public static final String RM_RESOURCE_USEFUL_GIFT_ALL = "1";

	public static final String RM_RESOURCE_TYPE_GIFTCARD = "2";

	public static final String RM_RESOURCE_TYPE_RECHARGECARD = "1";

	public static final String RM_RESOURCE_USEFUL_GIFT_USEABLE = "2";

	/**
	 * order 相关
	 */
	public static final String EI_ORDER_DETAIL_MODEL = "bizOrderDO";
	public static final String EI_ORDER_DETAIL_PAGE = "pagingList";
	public static final String EI_ORDER_DETAIL_STATUS = "statBizOrderDO";

	/**
	 * refund 退换货相关的
	 */
	public static final String EI_REFUND_MODEL = "refundItemFlow";
	public static final String EI_REFUND_DO = "refundItemFlowDO";
	public static final String EI_REFUND_MODEL_LIST = "refundItemFlowList";
	public static final int REFUND_TYPE_RETURN = 1;// 退货申请标志
	public static final int REFUND_TYPE_EXCHANGE = 2;// 换货申请标志
	public static final String REFUND_CODE_1 = "1";// 申请的退换必要参数校验失败
	public static final String REFUND_CODE_2 = "2";// 有申请的退换货数据时候
	public static final String REFUND_ORDERSTATUS = "-1";// 退换货的申请状态
	public static final String REFUND_DATA_VAL_FAIL = "数据校验失败!";
	public static final String REFUND_PARA_TEST_FAIL = "必要参数校验失败!!";
	public static final String REFUND_SYS_ERROR_MSG = "系统发生异常!";
	public static final String REFUND_ERROR_MSG = "不好意思，当前用户没有可以做退换货的数据!";
	public static final String REFUND_DATA_REPEA_FORBID = "你不能够重复提交该退换货数据!!";
	public static final String REFUND_GOODS_APPLY = "退换货申请";
	public static final String REFUND_NOT_ACCEPT = "未受理";
	public static final String REFUND_OPRETIONVAL = "0";
	public static final String EI_REFUND_INPUT_GOOD_NAME = "输入商品名称";
	public static final String REFUND_SUUCESS_FLAG = "0";
	public static final Integer REFUND_QUANTITY = 0;

	public static final String EI_REFUND_QUERY_COMPANY_GROUP = "XIU2.0";
	public static final String REFUND_EDIT_OPERATION_NAME = "用户更新申请单信息";
	public static final String REFUND_EDIT_OPERATION_OBJECT = "用户更新申请单信息成功";

	// 物流公司-联邦name
	public static final String DELIVER_FEDEX_NAME = "联邦快递";
	// 物流公司-EMS-GN-name
	public static final String DELIVER_EMS_GN_NAME = "邮政EMS-国内特快专递";
	// 物流公司-EMS-JJ-name
	public static final String DELIVER_EMS_JJ_NAME = "邮政EMS-经济快递";
	// 物流公司-EMS-SN-name
	public static final String DELIVER_EMS_SN_NAME = "邮政EMS-省内";
	// 物流公司-EMS-TC-name
	public static final String DELIVER_EMS_TC_NAME = "邮政EMS-同城速递";
	// 物流公司-EMS-YB-name
	public static final String DELIVER_EMS_YB_NAME = "邮政EMS-E邮宝";
	// 物流公司-STARS-name
	public static final String DELIVER_STARS_NAME = "星辰急便";
	// 物流公司-ZJS-name
	public static final String DELIVER_ZJS_NAME = "宅急送";
	// 物流公司-YTO-name
	public static final String DELIVER_YTO_NAME = "圆通速递";
	// 物流公司-SF-YB-name
	public static final String DELIVER_YB_NAME = "圆邦物流";
	// 物流公司-SF-name
	public static final String DELIVER_SF_NAME = "顺丰速运";
	// 物流公司-SF-GJ-name
	public static final String DELIVER_SF_GJ_NAME = "顺风速运高价";
	// 物流公司-联邦id
	public static final String DELIVER_FEDEX_ID = "0101";
	// 物流公司-EMS-id
	public static final String DELIVER_EMS_ID = "0102";
	// 物流公司-STARS-id
	public static final String DELIVER_STARS_ID = "0103";
	// 物流公司-ZJS-id
	public static final String DELIVER_ZJS_ID = "0104";
	// 物流公司-YTO-id
	public static final String DELIVER_YTO_ID = "0105";
	// 物流公司-SF-id
	public static final String DELIVER_SF_ID = "0106";

	public static final String REFUND_OPERATION_NAME = "用户取消单个商品申请信息";
	public static final String REFUND_OPERATION_OBJECT = "用户取消单个商品申请信息成功";
	public static final String REFUND_OPERATION_BEGIN_VALUE = "用户取消单个商品申请信息成功";
	public static final int REFUND_OPERATION_TYPE = 10;
	public static final int REFUND_OPERATION_ID = 0;
	public static final int REFUND_CANCEL_STATUS = 3;
	public static final String REFUND_OPERATION_BEGIN = "0";
	public static final String REFUND_OPERATION_END = "0";
	public static final int REFUND_SUCCESS_RETURN = 1;
	public static final int REFUND_LOST_RETURN = 0;
	public static final int REFUND_EDIT_SUCCESS_RETURN = 0;
	public static final int REFUND_EDIT_LOST_RETURN = 1;

	public static final String REFUND_CODE_ITEMS = "codeItems";
	public static final String REFUND_KEY = "key";

	/**
	 * PORTAL邮件类型
	 */
	public static final int EMAIL_TYPE_REGUSER = 1;// 注册邮件
	public static final int EMAIL_TYPE_RETRIEVEPW = 2;// 重置密码邮件
	public static final int EMAIL_TYPE_SHARE = 3;// 分享商品邮件
	public static final int EMAIL_TYPE_INVITE = 4;// 好友邀请邮件
	public static final int EMAIL_TYPE_OTHER = 99;// 其他邮件

	public static final int EMAIL_STATUS_VALID = 1;// 有效
	public static final int EMAIL_STATUS_NOVALID = 0;// 无效
	
	public static final String EMAIL_CREATOR = "portal";// 邮件创建者
	public static final String EMAIL_SENDER = "走秀网";// 邮件创建者

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
	public static final String SMS_CREATOR = "portal";// 短信创建者

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
	public static final int FEEDBACK_PERSON = 0; // 用户反馈：0用户 ，1客服
	public static final int FEEDBACK_STATUS = 0; // 用户反馈：等待处理
	public static final String FEEDBACK_TYPE_COMPLAINTS_NAME = "投诉";
	public static final String FEEDBACK_TYPE_PROPOSA_NAME = "用户体验建议";
	public static final String FEEDBACK_TYPE_ADVISORY_NAME = "购买咨询";
	public static final Integer FEEDBACK_TYPE_COMPLAINTS = 1;
	public static final Integer FEEDBACK_TYPE_PROPOSAL = 2;
	public static final Integer FEEDBACK_TYPE_ADVISORY = 3;

	/**
	 * 站内信相关
	 */
	public static final String ADMINISTRATOR_ID = "0";
	public static final String ADMINISTRATOR_NAME = "走秀网管理员";
	public static final String LETTER_TYPE = "all";

	// 走秀网渠道编号
	public static final long CHANNELID_XIU = 11L;
	// 优惠卡券 有效标识
	public static final int COUPON_VALID = 1;
	// 优惠卡券 默认类型
	public static final String COUPON_TYPE_DEFAULT = "0";
	// 优惠卡券 终端用户标识
	public static final String COUPON_TERMINAL_USER = "2";
	// 优惠卡券 赠品类型
	public static final int COUPON_CARD_TYPE_GIFT = 3;

	// STOREID
	public static final int STOREID = 10154;

	// MD5加密时的附加串
	public static final String ENCRYPTION_AUTH_MD5CODE = "encryption.auth_md5code";

	//hession批量请求最大数量
	public static final int HESSION_BATCH_MAX_NUMBER=30;
	
	//商品下架
	public static final int PRODUCT_SOLDOUT=0;
	//商品上架
	public static final int PRODUCT_ONSALE=1;
}
