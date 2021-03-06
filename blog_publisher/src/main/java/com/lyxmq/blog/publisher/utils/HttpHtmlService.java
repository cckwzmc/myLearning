package com.lyxmq.blog.publisher.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.httpclient.Cookie;
import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.slf4j.LoggerFactory;

public class HttpHtmlService {
	private static final org.slf4j.Logger logger = LoggerFactory
	.getLogger(HttpHtmlService.class);
	public static String getHtmlContent(String url,String charset,List<org.apache.http.cookie.Cookie> listC) {
		String html = "";
		/* 1 生成 HttpClinet 对象并设置参�?*/
		HttpClient httpClient = new HttpClient();
		// 设置 Http 连接超时�?�?
		httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(
				5000);

		/* 2 生成 GetMethod 对象并设置参�?*/
		GetMethod getMethod = new GetMethod(url);
		getMethod.setFollowRedirects(true);  
		getMethod.getParams().setParameter(HttpMethodParams.SINGLE_COOKIE_HEADER, true);  
		// 设置 get 请求超时�?5 �?
		getMethod.getParams().setParameter(HttpMethodParams.SO_TIMEOUT, 60000);
		// 设置请求重试处理，用的是默认的重试处理：请求三次
		getMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,
				new DefaultHttpMethodRetryHandler());
		if(CollectionUtils.isNotEmpty(listC))
		{
			for (Iterator iterator = listC.iterator(); iterator.hasNext();) {
				org.apache.http.cookie.Cookie cookie = (org.apache.http.cookie.Cookie) iterator.next();
				if("SPHPSESSID".equalsIgnoreCase(cookie.getName()))
				{
					getMethod.setRequestHeader("Cookie", "SPHPSESSID=" + cookie.getValue());
				}	
				if("sessionid".equalsIgnoreCase(cookie.getName())){
					getMethod.setRequestHeader("Cookie", "SessionID=" + cookie.getValue());
				}
				if("domain".equalsIgnoreCase(cookie.getName())){
					getMethod.setRequestHeader("Cookie", "domain=" +"sina.com.cn");
				}
			}
			
		}	
		/* 3 执行 HTTP GET 请求 */
		try {
			int statusCode = httpClient.executeMethod(getMethod);
			/* 4 判断访问的状态码 */
			if (statusCode != HttpStatus.SC_OK) {
				System.err.println("Method failed: "
						+ getMethod.getStatusLine());
			}

			/* 5 处理 HTTP 响应内容 */
			// HTTP响应头部信息，这里简单打�?
			Header[] headers = getMethod.getResponseHeaders();
			for (Header h : headers)
				logger.info(h.getName() + " " + h.getValue());
			// 读取 HTTP 响应内容，这里简单打印网页内�?
//			byte[] responseBody = getMethod.getResponseBody();// 读取为字节数�?
//			this.convertToGBK(new String(responseBody));
//			html = new String(responseBody);
			
			// System.out.println(new String(responseBody));
			// 读取�?InputStream，在网页内容数据量大时�?推荐使用(没有解决乱码问题)
			 InputStream response = getMethod.getResponseBodyAsStream();//
			// this.inputStream2String(response);
			 html=inputStream2String(response,charset);
			 return html;

		} catch (HttpException e) {
			// 发生致命的异常，可能是协议不对或者返回的内容有问�?
			System.out.println("Please check your provided http address!");
			logger.error(e.getMessage()+"采集地址为："+url);
		} catch (IOException e) {
			// 发生网络异常
			logger.error(e.getMessage()+"采集地址为："+url);
		} finally {
			/* 6 .释放连接 */
			getMethod.releaseConnection();
		}
		return html;
	}

	public static String inputStream2String(InputStream is,String charset) {
		String ret = "";

		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(is,
					charset));

			String tempbf="";
			StringBuffer html = new StringBuffer(100);
			while ((tempbf = br.readLine()) != null) {  
				html.append(tempbf +"\n");  
			} 
			// String ret=this.convertToGBK(buffer.toString());

			ret = new String(html.toString());

		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return ret;
	}

}
