package com.toney.sso.core.exception;

/**
 *************************************************************** 
 * <p>
 * 
 * @CLASS :ManagerException.java
 * @DESCRIPTION : Manager Exception
 * @AUTHOR :toney.li
 * @VERSION :v1.0
 * @DATE :Apr 1, 2013
 *       </p>
 **************************************************************** 
 */
public class ManagerException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6718812474562336209L;

	public ManagerException() {
	}

	public ManagerException(String msg) {
		super(msg);
	}

	public ManagerException(Throwable t) {
		super(t);
	}

	public ManagerException(String msg, Throwable t) {
		super(msg, t);
	}
}
