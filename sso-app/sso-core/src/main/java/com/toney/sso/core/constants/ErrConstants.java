package com.toney.sso.core.constants;

import java.util.HashMap;
import java.util.Map;

/**
 *************************************************************** 
 * <p>
 * 
 * @CLASS :ErrConstants.java
 * @DESCRIPTION : error code constants <br/>
 *              负数：系统错误<br/>
 *              整数：业务错误
 * @AUTHOR :toney.li
 * @VERSION :v1.0
 * @DATE :Mar 30, 2013
 *       </p>
 **************************************************************** 
 */
public class ErrConstants {

	/**
	 * 
	 */
	public static final String GENERAL_ERR_CODE = "-99999";
	public static final String GENERAL_COMM_ERR_MSG = "系统繁忙，请稍侯！！";

	// 80开头的留给http status用.
	public static class HttpErrorCode {

		// httpsatus
		public final static String HTTP_INTERNAL_SERVER_ERROR = "80500";
		// 错误且没信息
		public final static String HTTP_NOMSG_SERVER_ERROR = "80510";
		// 错误且脚本报错
		public final static String HTTP_SCRIPT_ERROR_SERVER_ERROR = "80511";
		// 错误且没有数据返回
		public final static String HTTP_NO_DATA_SERVER_ERROR = "80512";

		// 路径不存在
		public final static String HTTP_NOTFOUND_ERROR = "80404";
		// 错误请求
		public final static String HTTP_BADREQUEST_ERROR = "80400";
		// 未实现的功能
		public final static String HTTP_NOTIMPLEMENTED_ERROR = "80501";
		// 非应用异常，可能是服务器自身异常
		public final static String HTTP_NOT_APP_ERROR = "80500#001";

		public final static Map<String, String> errorCodeMap = new HashMap<String, String>();
		static {
			errorCodeMap.put("HTTP_INTERNAL_SERVER_ERROR", HTTP_INTERNAL_SERVER_ERROR);
			errorCodeMap.put("HTTP_NOMSG_SERVER_ERROR", HTTP_NOMSG_SERVER_ERROR);
			errorCodeMap.put("HTTP_SCRIPT_ERROR_SERVER_ERROR", HTTP_SCRIPT_ERROR_SERVER_ERROR);
			errorCodeMap.put("HTTP_NO_DATA_SERVER_ERROR", HTTP_NO_DATA_SERVER_ERROR);
		}
	}
}
