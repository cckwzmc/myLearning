package com.toney.web.commons.utils;

import com.toney.core.constants.Constants;

/** 
 *************************************************************** 
 * <p>
 * @DESCRIPTION : 
 * @AUTHOR : Liang Yongxu@xiu.com 
 * @DATE :2012-5-15 下午5:57:56             
 * </p>
 **************************************************************** 
 */
public class EmailUtil {
	
	/**
	 * 
	 * @Title: genTemplatePath
	 * @Description: 返回短信模板路径
	 * @param sms
	 * @return
	 * @return String
	 * @throws
	 */
	public static String genTemplatePath(int type) {
		 
		String path = "";

		switch (type) {
		case Constants.EMAIL_TYPE_REGUSER:
			path = "template/email_reguser.ftl";
			break;
		case Constants.EMAIL_TYPE_RETRIEVEPW:
			path = "template/email_retrievepw.ftl";
			break;
		case Constants.EMAIL_TYPE_INVITE:
			path = "template/email_invite.ftl";
			break;
		case Constants.EMAIL_TYPE_SHARE:
			path = "template/email_share.ftl";
			break;
		case Constants.EMAIL_TYPE_OTHER:
			path = "template/email_ohter.ftl";
			break;
		 
		default:
			break;
		}
  
		return path;
	}
	
	public static String genEmailTitle(int type){
		
		String title = "";
		
		switch (type) {
		case Constants.EMAIL_TYPE_REGUSER:
			title = "欢迎加入走秀网";
			break;
		case Constants.EMAIL_TYPE_RETRIEVEPW:
			title = "走秀网提醒您，请重置密码";
			break;
		case Constants.EMAIL_TYPE_INVITE:
			title = "您的好友邀请您注册走秀网";
			break;
		case Constants.EMAIL_TYPE_SHARE:
			title = "分享邮件";
			break;
		case Constants.EMAIL_TYPE_OTHER:
			title = "走秀网邮件";
			break;
		 
		default:
			break;
		}
  
		return title;
		
	}

}
