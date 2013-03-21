package com.toney.istyle.core.exception;

/**
 *************************************************************** 
 * <p>
 * 
 * @CLASS :RespositoryException.java
 * @DESCRIPTION : Respository层 Exception
 * @AUTHOR :toney.li
 * @VERSION :v1.0
 * @DATE :Mar 21, 2013
 *       </p>
 **************************************************************** 
 */
public class RespositoryException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1969611673802355748L;

	public RespositoryException() {
	}

	public RespositoryException(String msg) {
		super(msg);
	}

	public RespositoryException(String msg, Throwable t) {
		super(msg, t);
	}

	public RespositoryException(Throwable t) {
		super(t);
	}

}
