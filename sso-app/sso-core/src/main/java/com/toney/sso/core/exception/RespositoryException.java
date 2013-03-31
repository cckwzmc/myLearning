/**
 * 
 */
package com.toney.sso.core.exception;

/**
 *************************************************************** 
 * <p>
 * 
 * @CLASS :RespositoryException.java
 * @DESCRIPTION :Respository Exception
 * @AUTHOR :ly_zy_ljh
 * @VERSION :v1.0
 * @DATE :2013-3-26
 *       </p>
 **************************************************************** 
 */
public class RespositoryException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7359022791745776183L;

	public RespositoryException() {
		super();
	};

	public RespositoryException(String msg) {
		super(msg);
	}

	public RespositoryException(Throwable t) {
		super(t);
	}

	public RespositoryException(String msg, Throwable t) {
		super(msg, t);
	}
}
