package com.toney.crawler.http;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.DefaultHttpRequestRetryHandler;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;

/**
 * @author toney.li 使用httpclient获取抓取网页数据
 */
public class HttpContentUtils {

	private static final XLogger LOGGER = XLoggerFactory
			.getXLogger(HttpContentUtils.class);
	/**
	 * 重试次数
	 */
	private static Integer retryCount = 3;
	private static String setChar = "UTF-8";
	private static HttpParams httpParams;
	private static int connTimeOut=20 * 1000;
	private static int soTimeOut=20 * 1000;
	private static int buffSize=8192;
	private static String userAgent="Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/535.19 (KHTML, like Gecko) Chrome/18.0.1025.168 Safari/535.19";

	static{
		httpParams = new BasicHttpParams();
		// 设置连接超时和 Socket 超时，以及 Socket 缓存大小
		HttpConnectionParams.setConnectionTimeout(httpParams, connTimeOut);
		HttpConnectionParams.setSoTimeout(httpParams, soTimeOut);
		HttpConnectionParams.setSocketBufferSize(httpParams, buffSize);
		HttpProtocolParams.setUserAgent(httpParams, userAgent);
	}

	public String getSetChar() {
		return setChar;
	}

	public void setSetChar(String setChar) {
		HttpContentUtils.setChar = setChar;
	}


	public Integer getRetryCount() {
		return retryCount;
	}

	public void setRetryCount(Integer retryCount) {
		HttpContentUtils.retryCount = retryCount;
	}

	/**
	 * 使用get请求的方式获取页面数据。
	 * 
	 * @param url
	 * @return
	 */
	public static String getContentByGET(String url) {
		DefaultHttpClient httpClient = new DefaultHttpClient();
		try {
			HttpGet httpGet = new HttpGet(url);
			httpGet.setParams(httpParams);
			HttpRequestRetryHandler retryHandler=new DefaultHttpRequestRetryHandler(retryCount, true);
			httpClient.setHttpRequestRetryHandler(retryHandler);
			ResponseHandler<String> responseHandler = new BasicResponseHandler();
			String resBody = httpClient.execute(httpGet, responseHandler);
			LOGGER.debug("~~~~~~~~~~~~~~start crawler~~~~~~~~~~~~~~~~~~");
			LOGGER.debug(resBody);
			LOGGER.debug("~~~~~~~~~~~~~~end crawler~~~~~~~~~~~~~~~~~~");
			return resBody;
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			httpClient.getConnectionManager().shutdown();
		}
		return null;
	}

	/**
	 * 使用post请求的方式获取页面数据。
	 * 
	 * @param url
	 * @param entity
	 * @return
	 */
	public static String getContentByPost(String url, MultipartEntity entity) {
		DefaultHttpClient httpClient = new DefaultHttpClient();
		try {
			HttpPost httpPost = new HttpPost(url);
			httpPost.setParams(httpParams);
			httpPost.setEntity(entity);
			HttpRequestRetryHandler retryHandler=new DefaultHttpRequestRetryHandler(retryCount, true);
			httpClient.setHttpRequestRetryHandler(retryHandler);
			ResponseHandler<String> responseHandler = new BasicResponseHandler();
			String resBody = httpClient.execute(httpPost, responseHandler);
			LOGGER.debug("~~~~~~~~~~~~~~start crawler~~~~~~~~~~~~~~~~~~");
			LOGGER.debug(resBody);
			LOGGER.debug("~~~~~~~~~~~~~~end crawler~~~~~~~~~~~~~~~~~~");
			return resBody;
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			httpClient.getConnectionManager().shutdown();
		}
		return null;
	}

}
