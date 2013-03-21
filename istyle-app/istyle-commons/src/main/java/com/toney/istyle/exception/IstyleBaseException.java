package com.toney.istyle.exception;

import org.apache.commons.lang.StringUtils;

/**
 *************************************************************** 
 * <p>
 * 
 * @CLASS :IstyleBaseException.java
 * @DESCRIPTION : 全站Exception基类
 * @AUTHOR :toney.li
 * @VERSION :v1.0
 * @DATE :Mar 21, 2013
 *       </p>
 **************************************************************** 
 */
public class IstyleBaseException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8786562084801763654L;
	
	private String errCode;

	public IstyleBaseException() {
	};

	public IstyleBaseException(String msg, Throwable t) {
		super(msg, t);
	}

	public IstyleBaseException(String msg) {
		super(msg);
	}

	public IstyleBaseException(Throwable t) {
		super(t);
	}

	/**
	 * 
	 * @param msg 异常消息
	 * @param errCode 错误码
	 * 
	 */
	public IstyleBaseException(String msg, String errCode) {
		super(msg);
		this.errCode=errCode;
	}
	/**
	 * @param msg
	 * @param errCode 错误码
	 * @param throwable
	 */
	public IstyleBaseException(String msg, String errCode,Throwable throwable) {
		super(msg,throwable);
		this.errCode=errCode;
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
