package com.toney.crawler.http;

import org.junit.Test;

public class HttpContentUtilsTest {
	@Test
	public void testGetContentByGET() {
		String content=HttpContentUtils.getContentByGET("http://www.163.com");
		System.out.println(content);
	}
	@Test
	public void testGetContentByPOST() {
		String content=HttpContentUtils.getContentByPost("http://www.163.com",null);
		System.out.println(content);
	}
}
