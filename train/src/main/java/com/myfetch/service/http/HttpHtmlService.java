package com.myfetch.service.http;

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

import com.myfetch.util.ChangeCharset;
import com.myfetch.util.Encoding;
import com.myfetch.util.StringEncoding;

public class HttpHtmlService {

	public String getHtmlContent(String url) {
		String html = "";
		/* 1 生成 HttpClinet 对象并设置参数 */
		HttpClient httpClient = new HttpClient();
		// 设置 Http 连接超时为5秒
		httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(
				5000);

		/* 2 生成 GetMethod 对象并设置参数 */
		GetMethod getMethod = new GetMethod(url);
		// 设置 get 请求超时为 5 秒
		getMethod.getParams().setParameter(HttpMethodParams.SO_TIMEOUT, 20000);
		// 设置请求重试处理，用的是默认的重试处理：请求三次
		getMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,
				new DefaultHttpMethodRetryHandler());

		/* 3 执行 HTTP GET 请求 */
		try {
			int statusCode = httpClient.executeMethod(getMethod);
			/* 4 判断访问的状态码 */
			if (statusCode != HttpStatus.SC_OK) {
				System.err.println("Method failed: "
						+ getMethod.getStatusLine());
			}

			/* 5 处理 HTTP 响应内容 */
			// HTTP响应头部信息，这里简单打印
			Header[] headers = getMethod.getResponseHeaders();
			for (Header h : headers)
				System.out.println(h.getName() + " " + h.getValue());
			// 读取 HTTP 响应内容，这里简单打印网页内容
//			byte[] responseBody = getMethod.getResponseBody();// 读取为字节数组
//			this.convertToGBK(new String(responseBody));
//			html = new String(responseBody);
			
			// System.out.println(new String(responseBody));
			// 读取为 InputStream，在网页内容数据量大时候推荐使用(没有解决乱码问题)
			 InputStream response = getMethod.getResponseBodyAsStream();//
			// this.inputStream2String(response);
			 html=this.inputStream2String(response);
			 return html;

		} catch (HttpException e) {
			// 发生致命的异常，可能是协议不对或者返回的内容有问题
			System.out.println("Please check your provided http address!");
			e.printStackTrace();
		} catch (IOException e) {
			// 发生网络异常
			e.printStackTrace();
		} finally {
			/* 6 .释放连接 */
			getMethod.releaseConnection();
		}
		return html;
	}

	public String inputStream2String(InputStream is) {
		String ret = "";

		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(is,
					"GB2312"));

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

	public String convertToGBK(String str) {
		String retStr = "";
		StringEncoding encode = new StringEncoding();
		Encoding encoding = encode.getEncoding(str.getBytes());
		ChangeCharset charset = new ChangeCharset();
		try {
			charset.changeCharset(retStr, encoding.getEncoding(), "GBK");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(encoding.getEncoding());
		return retStr = "";
	}
}
