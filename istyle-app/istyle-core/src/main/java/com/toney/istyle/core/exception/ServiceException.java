package com.toney.istyle.core.exception;

/**
 *************************************************************** 
 * <p>
 * 
 * @CLASS :ServiceException.java
 * @DESCRIPTION : 原子业务层异常
 * @AUTHOR :toney.li
 * @VERSION :v1.0
 * @DATE :Mar 19, 2013
 *       </p>
 **************************************************************** 
 */
public class ServiceException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3755232687446197368L;

	public ServiceException() {
		super();
	}

	public ServiceException(String msg) {
		super(msg);
	}

	public ServiceException(String msg, Throwable t) {
		super(msg, t);
	}

	public ServiceException(Throwable t) {
		super(t);
	}

}
