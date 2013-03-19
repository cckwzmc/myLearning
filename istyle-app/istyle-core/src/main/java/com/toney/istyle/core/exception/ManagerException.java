package com.toney.istyle.core.exception;

/**
 *************************************************************** 
 * <p>
 * 
 * @CLASS :ManagerException.java
 * @DESCRIPTION : 业务组合层
 * @AUTHOR :toney.li
 * @VERSION :v1.0
 * @DATE :Mar 19, 2013
 *       </p>
 **************************************************************** 
 */
public class ManagerException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6980156868763523022L;

	public ManagerException() {
		super();
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
