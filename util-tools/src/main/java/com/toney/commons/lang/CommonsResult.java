package com.toney.commons.lang;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.security.auth.login.FailedLoginException;

/**
 * 
 *************************************************************** 
 * <p>
 * 
 * @CLASS :CommonsResult
 * @DESCRIPTION : 通用结果返回类.
 * @AUTHOR :toney.li
 * @VERSION :v1.0
 * @DATE :Jan 8, 2013
 *       </p>
 **************************************************************** 
 */
public class CommonsResult<T> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4666323072399818655L;

	private T data;
	private List<Map<String, String>> errorMessage;
	/**
	 * 0：失败 1:成功 作为保留编码 ，其他的自定义.
	 */
	private String resultCode;

	public List<Map<String, String>> getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(List<Map<String, String>> errorMessage) {
		this.errorMessage = errorMessage;
	}

	public void setData(T data) {
		this.data = data;
	}

	/**
	 * @see com.toney.commons.constants.CommonsConstants 对统一结果码的定义
	 * @param resultCode
	 */
	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}

	public T getData() {
		return this.data;
	}

	public List<Map<String, String>> getErroMessage() {
		return this.errorMessage;
	}

	/**
	 * @see com.toney.commons.constants.CommonsConstants 对统一结果码的定义
	 * @return
	 */
	public String getResultCode() {
		return resultCode;
	}

}
