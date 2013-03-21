package com.toney.istyle.constants;

import java.util.HashMap;
import java.util.Map;

/**
 *************************************************************** 
 * <p>
 * 
 * @CLASS :ErrConstants.java
 * @DESCRIPTION : 全站错误代码constants
 * @AUTHOR :toney.li
 * @VERSION :v1.0
 * @DATE :Mar 21, 2013
 *       </p>
 **************************************************************** 
 */
public class ErrConstants {

	/**
	 * 一般性错误
	 */
	public static final String GERNERAL_ERR_CODE = "-9999";

	public final static String GENERAL_UNKNOWN_ERR_MSG = "未知错误";

	public final static String GENERAL_COMM_ERR_MSG = "很抱歉，您刚才的操作出了点问题哦！";
	public final static String GENERAL_NOTFOUND_ERR_MSG = "您要访问的页面没有找到哦！";


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
