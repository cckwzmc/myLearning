package com.lottery.util.html;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.slf4j.LoggerFactory;

public class HttpHtmlService {
	private static final org.slf4j.Logger logger = LoggerFactory.getLogger(HttpHtmlService.class);

	/**
	 * 默认字符集UTF-8
	 * 
	 * @param url
	 * @param charset
	 * @return
	 */
	public static String getHtmlContent(String url) {
		String html = "";
		/* 1 生成 HttpClinet 对象并设置参数 */
		HttpClient httpClient = new HttpClient();
		// 设置 Http 连接超时为5秒
		httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(5000);

		/* 2 生成 GetMethod 对象并设置参数 */
		GetMethod getMethod = new GetMethod(url);
		// 设置 get 请求超时为 5 秒
		getMethod.getParams().setParameter(HttpMethodParams.SO_TIMEOUT, 60000);
		// 设置请求重试处理，用的是默认的重试处理：请求三次
		getMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler(6, true));

		/* 3 执行 HTTP GET 请求 */
		try {
			int statusCode = httpClient.executeMethod(getMethod);
			/* 4 判断访问的状态码 */
			if (statusCode != HttpStatus.SC_OK) {
				System.err.println("Method failed: " + getMethod.getStatusLine());
			}

			/* 5 处理 HTTP 响应内容 */
			// HTTP响应头部信息，这里简单打印
			Header[] headers = getMethod.getResponseHeaders();
			for (Header h : headers)
				System.out.println(h.getName() + " " + h.getValue());
			// 读取 HTTP 响应内容，这里简单打印网页内容
			// byte[] responseBody = getMethod.getResponseBody();// 读取为字节数组
			// this.convertToGBK(new String(responseBody));
			// html = new String(responseBody);

			// System.out.println(new String(responseBody));
			// 读取为 InputStream，在网页内容数据量大时候推荐使用(没有解决乱码问题)
			InputStream response = getMethod.getResponseBodyAsStream();//
			// this.inputStream2String(response);
			html = inputStreamConvertString(response, "UTF-8");
			return html;

		} catch (HttpException e) {
			// 发生致命的异常，可能是协议不对或者返回的内容有问题
			// System.out.println("Please check your provided http address!");
			logger.error(e.getMessage() + "采集地址为：" + url);
		} catch (IOException e) {
			// 发生网络异常
			logger.error(e.getMessage() + "采集地址为：" + url);
		} finally {
			/* 6 .释放连接 */
			getMethod.releaseConnection();
		}
		return html;
	}

	/**
	 * @param url
	 * @param charset
	 *            设置字符集
	 * @return
	 */
	public static String getHtmlContent(String url, String charset) {
		String html = "";
		/* 1 生成 HttpClinet 对象并设置参数 */
		HttpClient httpClient = new HttpClient();
		// 设置 Http 连接超时为5秒
		httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(5000);

		/* 2 生成 GetMethod 对象并设置参数 */
		GetMethod getMethod = new GetMethod(url);
		// 设置 get 请求超时为 5 秒
		getMethod.getParams().setParameter(HttpMethodParams.SO_TIMEOUT, 60000);
		// 设置请求重试处理，用的是默认的重试处理：请求三次
		getMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler());

		/* 3 执行 HTTP GET 请求 */
		try {
			int statusCode = httpClient.executeMethod(getMethod);
			/* 4 判断访问的状态码 */
			if (statusCode != HttpStatus.SC_OK) {
				System.err.println("Method failed: " + getMethod.getStatusLine());
			}

			/* 5 处理 HTTP 响应内容 */
			// HTTP响应头部信息，这里简单打印
			Header[] headers = getMethod.getResponseHeaders();
			for (Header h : headers)
				logger.debug(h.getName() + " " + h.getValue());
			// 读取 HTTP 响应内容，这里简单打印网页内容
			// byte[] responseBody = getMethod.getResponseBody();// 读取为字节数组
			// this.convertToGBK(new String(responseBody));
			// html = new String(responseBody);

			// System.out.println(new String(responseBody));
			// 读取为 InputStream，在网页内容数据量大时候推荐使用(没有解决乱码问题)
			InputStream response = getMethod.getResponseBodyAsStream();//
			// this.inputStream2String(response);
			html = inputStreamConvertString(response, charset);
			return html;

		} catch (HttpException e) {
			// 发生致命的异常，可能是协议不对或者返回的内容有问题
			// System.out.println("Please check your provided http address!");
			logger.error(e.getMessage() + "采集地址为：" + url);
		} catch (IOException e) {
			// 发生网络异常
			logger.error(e.getMessage() + "采集地址为：" + url);
		} finally {
			/* 6 .释放连接 */
			getMethod.releaseConnection();
		}
		return html;
	}

	/**
	 * 获取流方式的html内容
	 * 
	 * @param url
	 * @return
	 */
	public static InputStream getHtmlContentInputStream(String url) {
		InputStream response = null;
		/* 1 生成 HttpClinet 对象并设置参数 */
		HttpClient httpClient = new HttpClient();
		// 设置 Http 连接超时为5秒
		httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(5000);

		/* 2 生成 GetMethod 对象并设置参数 */
		GetMethod getMethod = new GetMethod(url);
		// 设置 get 请求超时为 5 秒
		getMethod.getParams().setParameter(HttpMethodParams.SO_TIMEOUT, 60000);
		// 设置请求重试处理，用的是默认的重试处理：请求三次
		getMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler());

		/* 3 执行 HTTP GET 请求 */
		try {
			int statusCode = httpClient.executeMethod(getMethod);
			/* 4 判断访问的状态码 */
			if (statusCode != HttpStatus.SC_OK) {
				System.err.println("Method failed: " + getMethod.getStatusLine());
			}

			/* 5 处理 HTTP 响应内容 */
			// HTTP响应头部信息，这里简单打印
			Header[] headers = getMethod.getResponseHeaders();
			for (Header h : headers)
				System.out.println(h.getName() + " " + h.getValue());
			// 读取 HTTP 响应内容，这里简单打印网页内容
			// byte[] responseBody = getMethod.getResponseBody();// 读取为字节数组
			// this.convertToGBK(new String(responseBody));
			// html = new String(responseBody);

			// System.out.println(new String(responseBody));
			// 读取为 InputStream，在网页内容数据量大时候推荐使用(没有解决乱码问题)
			response = getMethod.getResponseBodyAsStream();//
		} catch (HttpException e) {
			// 发生致命的异常，可能是协议不对或者返回的内容有问题
			// System.out.println("Please check your provided http address!");
			logger.error(e.getMessage() + "采集地址为：" + url);
		} catch (IOException e) {
			// 发生网络异常
			logger.error(e.getMessage() + "采集地址为：" + url);
		} finally {
			/* 6 .释放连接 */
			getMethod.releaseConnection();
		}
		return response;
	}

	/**
	 * 默认字符集-UTF-8
	 * 
	 * @param url
	 * @return
	 */
	public static String getXmlContent(String url) {
		String html = "";
		/* 1 生成 HttpClinet 对象并设置参数 */
		HttpClient httpClient = new HttpClient();
		// 设置 Http 连接超时为5秒
		httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(5000);

		/* 2 生成 GetMethod 对象并设置参数 */
		GetMethod getMethod = new GetMethod(url);
		// 设置 get 请求超时为 5 秒
		getMethod.getParams().setParameter(HttpMethodParams.SO_TIMEOUT, 60000);
		// 设置请求重试处理，用的是默认的重试处理：请求三次
		getMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler());

		/* 3 执行 HTTP GET 请求 */
		try {
			int statusCode = httpClient.executeMethod(getMethod);
			/* 4 判断访问的状态码 */
			if (statusCode != HttpStatus.SC_OK) {
				logger.error("Method failed: " + getMethod.getStatusLine());
				return statusCode + "";
			}
			/* 5 处理 HTTP 响应内容 */
			// HTTP响应头部信息，这里简单打印
			Header[] headers = getMethod.getResponseHeaders();
			// for (Header h : headers)
			// System.out.println(h.getName() + " " + h.getValue());
			InputStream response = getMethod.getResponseBodyAsStream();//
			html = inputStreamConvertString(response, "UTF-8");
			// byte[] result=getMethod.getResponseBody();
			// html=new String(response,"UTF-8");
			return html;

		} catch (HttpException e) {
			// 发生致命的异常，可能是协议不对或者返回的内容有问题
			// System.out.println("Please check your provided http address!");
			logger.error(e.getMessage() + "采集地址为：" + url);
		} catch (IOException e) {
			// 发生网络异常
			logger.error(e.getMessage() + "采集地址为：" + url);
		} finally {
			/* 6 .释放连接 */
			getMethod.releaseConnection();
		}
		return html;
	}

	/**
	 * @param url
	 * @param charset
	 *            设置字符集
	 * @return
	 */
	public static String getXmlContent(String url, String charset) {
		String html = "";
		/* 1 生成 HttpClinet 对象并设置参数 */
		HttpClient httpClient = new HttpClient();
		// 设置 Http 连接超时为5秒
		httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(5000);

		/* 2 生成 GetMethod 对象并设置参数 */
		GetMethod getMethod = new GetMethod(url);
		// 设置 get 请求超时为 5 秒
		getMethod.getParams().setParameter(HttpMethodParams.SO_TIMEOUT, 60000);
		// 设置请求重试处理，用的是默认的重试处理：请求三次
		getMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler());

		/* 3 执行 HTTP GET 请求 */
		try {
			int statusCode = httpClient.executeMethod(getMethod);
			/* 4 判断访问的状态码 */
			if (statusCode != HttpStatus.SC_OK) {
				logger.error("Method failed: " + getMethod.getStatusLine());
				return statusCode + "";
			}
			/* 5 处理 HTTP 响应内容 */
			// HTTP响应头部信息，这里简单打印
			Header[] headers = getMethod.getResponseHeaders();
			// for (Header h : headers)
			// System.out.println(h.getName() + " " + h.getValue());
			InputStream response = getMethod.getResponseBodyAsStream();//
			html = inputStreamConvertString(response, charset);
			// byte[] result=getMethod.getResponseBody();
			// html=new String(response,"UTF-8");
			return html;

		} catch (HttpException e) {
			// 发生致命的异常，可能是协议不对或者返回的内容有问题
			// System.out.println("Please check your provided http address!");
			logger.error(e.getMessage() + "采集地址为：" + url);
		} catch (IOException e) {
			// 发生网络异常
			logger.error(e.getMessage() + "采集地址为：" + url);
		} finally {
			/* 6 .释放连接 */
			getMethod.releaseConnection();
		}
		return html;
	}

	private static String inputStreamConvertString(InputStream response, String charset) {
		String ret = "";

		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(response, charset));

			String tempbf = "";
			StringBuffer html = new StringBuffer(100);
			while ((tempbf = br.readLine()) != null) {
				html.append(tempbf + "\n");
			}
			ret = new String(html.toString());

		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return ret;
	}

}
