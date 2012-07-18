package com.toney.mvc.web.utils;

import java.util.Random;

import com.toney.core.constants.Constants;
import com.toney.core.model.SMS;

/**
 *************************************************************** 
 * <p>
 * 
 * @DESCRIPTION :
 * @AUTHOR : Liang Yongxu@xiu.com
 * @DATE :2012-4-14 下午4:44:34
 *       </p>
 **************************************************************** 
 */
public class SMSUtil {

	/**
	 * 
	 * @Title: getRandomNum
	 * @Description: 随机产生4位数字
	 * @return
	 * @return String
	 * @throws
	 */
	public static String getRandomNum() {
		Random random = new Random(System.currentTimeMillis());
		StringBuilder sbf = new StringBuilder();
		for (int i = 0; i < 4; i++) {
			sbf.append(random.nextInt(10));
		}
		return sbf.toString();
	}

	/**
	 * 
	 * @Title: genTemplatePath
	 * @Description: 返回短信模板路径
	 * @param sms
	 * @return
	 * @return String
	 * @throws
	 */
	public static String genTemplatePath(SMS sms) {
		int type = sms.getType();

		String path = "";

		switch (type) {
		case Constants.SMS_TYPE_REGUSER:
			path = "template/sms_submsg.ftl";
			break;
		case Constants.SMS_TYPE_RETRIEVEPW:
			path = "template/sms_submsg.ftl";
			break;
		case Constants.SMS_TYPE_DRAWAPPLY:
			path = "template/sms_submsg.ftl";
			break;
		case Constants.SMS_TYPE_SUBMESSAGE:
			path = "template/sms_submsg.ftl";
			break;
		case Constants.SMS_TYPE_LOTTERY:
			path = "template/sms_submsg.ftl";
			break;
		case Constants.SMS_TYPE_OTHER:
			path = "template/sms_submsg.ftl";
			break;

		default:
			break;
		}
  
		return path;

	}

}
