package com.toney.mvc.web.exception;

/**
 * @author toney.li
 * 页面出版时的异常处理.
 */
public class PublishException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2705358906732630385L;
	
	public PublishException(){super();}
	public PublishException(String message){super(message);}
	public PublishException(Throwable t ){super(t);}
	public PublishException(String message,Throwable t){super(message, t);}

}
