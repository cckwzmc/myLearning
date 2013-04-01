package com.toney.sso.core.exception;

import org.apache.commons.lang.StringUtils;

/**
 *************************************************************** 
 * <p>
 * 
 * @CLASS :SSOBaseException.java
 * @DESCRIPTION : SSO Base Exception
 * @AUTHOR :toney.li
 * @VERSION :v1.0
 * @DATE :Apr 1, 2013
 *       </p>
 **************************************************************** 
 */
public class SSOBaseException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2911548855469131163L;
	private String errCode;

	public SSOBaseException() {
	}

	public SSOBaseException(String msg) {
		super(msg);
	}

	public SSOBaseException(String msg, Throwable t) {
		super(msg, t);
	}

	public SSOBaseException(Throwable t) {
		super(t);
	}

	/**
	 * @param msg
	 * @param errCode
	 *            错误码
	 * @param throwable
	 */
	public SSOBaseException(String msg, String errCode, Throwable throwable) {
		super(msg, throwable);
		this.errCode = errCode;
	}

	public String getReadableMessage() {
		return getMessage();
	}

	public String getErrCode() {
		return errCode;
	}

	public String getMessageWithSupportCode() {
		if (StringUtils.isNotEmpty(getErrCode())) {
			StringBuilder builder = new StringBuilder();
			builder.append(getReadableMessage()).append("[").append(getErrCode()).append("]");
			return builder.toString();
		}
		return getReadableMessage();
	}
}
