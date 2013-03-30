/**
 * 
 */
package com.toney.sso.commons;

import java.io.Serializable;
import java.util.List;

/**
 *************************************************************** 
 * <p>
 * 
 * @CLASS :Result.java
 * @DESCRIPTION :接口返回数据
 * @AUTHOR :ly_zy_ljh
 * @VERSION :v1.0
 * @DATE :2013-3-25
 *       </p>
 **************************************************************** 
 */
public class Result<T> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3349987589091252008L;

	private Boolean isSuccess;
	private String errCode;
	private String errMessage;
	private String bizCode;
	private String bizMessage;

	private List<T> result;

	/**
	 * @return the isSuccess
	 */
	public Boolean getIsSuccess() {
		return isSuccess;
	}

	/**
	 * @param isSuccess
	 *            the isSuccess to set
	 */
	public void setIsSuccess(Boolean isSuccess) {
		this.isSuccess = isSuccess;
	}

	/**
	 * @return the errCode
	 */
	public String getErrCode() {
		return errCode;
	}

	/**
	 * @param errCode
	 *            the errCode to set
	 */
	public void setErrCode(String errCode) {
		this.errCode = errCode;
	}

	/**
	 * @return the errMessage
	 */
	public String getErrMessage() {
		return errMessage;
	}

	/**
	 * @param errMessage
	 *            the errMessage to set
	 */
	public void setErrMessage(String errMessage) {
		this.errMessage = errMessage;
	}

	/**
	 * @return the bizCode
	 */
	public String getBizCode() {
		return bizCode;
	}

	/**
	 * @param bizCode
	 *            the bizCode to set
	 */
	public void setBizCode(String bizCode) {
		this.bizCode = bizCode;
	}

	/**
	 * @return the bizMessage
	 */
	public String getBizMessage() {
		return bizMessage;
	}

	/**
	 * @param bizMessage
	 *            the bizMessage to set
	 */
	public void setBizMessage(String bizMessage) {
		this.bizMessage = bizMessage;
	}

	/**
	 * @return the result
	 */
	public List<T> getResult() {
		return result;
	}

	/**
	 * @param result
	 *            the result to set
	 */
	public void setResult(List<T> result) {
		this.result = result;
	}

}
