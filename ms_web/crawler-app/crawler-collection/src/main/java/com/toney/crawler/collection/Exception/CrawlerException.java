package com.toney.crawler.collection.Exception;

public class CrawlerException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4011289563167407878L;
	public CrawlerException(){}
	public CrawlerException(String s){super(s);}
	public CrawlerException(Throwable t ){super(t);}
	public CrawlerException(String s,Throwable t){super(s,t);}
}
