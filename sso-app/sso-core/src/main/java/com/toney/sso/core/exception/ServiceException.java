/**
 * 
 */
package com.toney.sso.core.exception;

/**
 *************************************************************** 
 * <p>
 * 
 * @CLASS :ServiceException.java
 * @DESCRIPTION :SSO Service Exception
 * @AUTHOR :ly_zy_ljh
 * @VERSION :v1.0
 * @DATE :2013-3-25
 *       </p>
 **************************************************************** 
 */
public class ServiceException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2278985747389692901L;

	public ServiceException() {
	}

	public ServiceException(String msg) {
		super(msg);
	}

	public ServiceException(Throwable t) {
		super(t);
	}

	public ServiceException(String msg, Throwable t) {
		super(msg, t);
	}

}
