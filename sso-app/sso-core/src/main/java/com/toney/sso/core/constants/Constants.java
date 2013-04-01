package com.toney.sso.core.constants;

/**
 * @author toney.li
 * 
 */
public class Constants {
	/**
	 * 用户TOKENID的随机加密码. TODO 将移入配置文件.
	 */
	public static final String LOGIN_RANDOM_ENCODE = "lyxmq.ljh";

	/**
	 * 用户存活时间.
	 */
	public static final long SESSION_LIVE_TIME = 30 * 60 * 1000;

	/**
	 * 注册类型：0:系统保留用户，1：系统用户 2：普通用户.
	 */
	public static final int USER_TYPE_0 = 0;
	/**
	 * 注册类型：0:系统保留用户，1：系统用户 2：普通用户.
	 */
	public static final int USER_TYPE_1 = 1;
	/**
	 * 注册类型：0:系统保留用户，1：系统用户 2：普通用户.
	 */
	public static final int USER_TYPE_2 = 2;
}
