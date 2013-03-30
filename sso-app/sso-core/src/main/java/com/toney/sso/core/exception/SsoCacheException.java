package com.toney.sso.core.exception;

public class SsoCacheException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6351272008829700404L;
	
	public SsoCacheException(){super();}
	public SsoCacheException(String msg){super(msg);}
	public SsoCacheException(String msg,Throwable t){super(msg, t);}
	public SsoCacheException(Throwable t){super(t);}
	
}
