package com.lottery.ssq.httpclient;

import java.io.IOException;
import java.net.Socket;

import org.apache.commons.lang.StringUtils;
import org.apache.http.ConnectionReuseStrategy;
import org.apache.http.HttpException;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.ProtocolVersion;
import org.apache.http.RequestLine;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.DefaultConnectionReuseStrategy;
import org.apache.http.impl.DefaultHttpClientConnection;
import org.apache.http.message.BasicHttpRequest;
import org.apache.http.message.BasicRequestLine;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.BasicHttpProcessor;
import org.apache.http.protocol.ExecutionContext;
import org.apache.http.protocol.HttpContext;
import org.apache.http.protocol.HttpRequestExecutor;
import org.apache.http.protocol.RequestConnControl;
import org.apache.http.protocol.RequestContent;
import org.apache.http.protocol.RequestExpectContinue;
import org.apache.http.protocol.RequestTargetHost;
import org.apache.http.protocol.RequestUserAgent;
import org.apache.http.util.EntityUtils;

import com.lottery.util.html.HttpHtmlService;

public class WrapLotteryHttpClient {
	private DefaultHttpClientConnection conn;
	private String host;
	private Integer port;
	private HttpParams params = new BasicHttpParams();
	private BasicHttpProcessor httpproc = new BasicHttpProcessor();
	private HttpContext context = new BasicHttpContext(null);
	private HttpHost httpHost = null;
	private ConnectionReuseStrategy connStrategy = new DefaultConnectionReuseStrategy();
	private HttpRequestExecutor httpexecutor = new HttpRequestExecutor();
	private HttpGet request;
	private HttpResponse response;
	public Integer getPort() {
		return port;
	}

	public void setPort(Integer port) {
		this.port = port;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public WrapLotteryHttpClient() throws Exception {
		this.settingParams();
		settingProcessor();
		if (StringUtils.isBlank(this.host) || this.port == null) {
			throw new Exception("host and port is not blank.");
		}
		httpHost = new HttpHost(this.host, this.port);
		initHttpClient();
	}

	public WrapLotteryHttpClient(String host, Integer port) {
		this.host = host;
		this.port = port;
		this.settingParams();
		settingProcessor();
		httpHost = new HttpHost(this.host, this.port);
		initHttpClient();
	}

	public DefaultHttpClientConnection getHttpClientConnection() {
		return conn;
	}

	private void initHttpClient() {
		conn = new DefaultHttpClientConnection();
		context.setAttribute(ExecutionContext.HTTP_CONNECTION, conn);
		context.setAttribute(ExecutionContext.HTTP_TARGET_HOST, httpHost);
		if (!conn.isOpen()) {
			Socket socket = null;
			try {
				socket = new Socket(httpHost.getHostName(), httpHost.getPort());
				conn.bind(socket, params);
			} catch (Exception e) {
				try {
					socket = new Socket(httpHost.getHostName(), httpHost.getPort());
					conn.bind(socket, params);
				} catch (IOException e1) {
					e.printStackTrace();
				}
			}
		}
	}

	private void settingProcessor() {
		// Required protocol interceptors
		httpproc.addInterceptor(new RequestContent());
		httpproc.addInterceptor(new RequestTargetHost());
		// Recommended protocol interceptors
		httpproc.addInterceptor(new RequestConnControl());
		httpproc.addInterceptor(new RequestUserAgent());
		httpproc.addInterceptor(new RequestExpectContinue());
	}

	private HttpParams settingParams() {
		HttpProtocolParams.setVersion(params, HttpVersion.HTTP_1_1);
		HttpProtocolParams.setContentCharset(params, "UTF-8");
		HttpProtocolParams.setUserAgent(params, "Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 5.1)");
		HttpConnectionParams.setConnectionTimeout(params, 60000);
		HttpConnectionParams.setSoTimeout(params, 60000);
		HttpProtocolParams.setUseExpectContinue(params, true);
		return params;
	}

	public String getHtmlContent(String uri) {
		try {
			RequestLine requestline=new BasicRequestLine("GET", uri, new ProtocolVersion("HTTP",1, 1));
			// HttpUriRequest request = new HttpGet(uri);
			request=new HttpGet(uri);
			request.setParams(params);
			httpexecutor.preProcess(request, httpproc, context);
			response = httpexecutor.execute(request, conn, context);
			response.setParams(params);
			httpexecutor.postProcess(response, httpproc, context);

			if (!connStrategy.keepAlive(response, context)) {
				conn.close();
			} else {
				System.out.println("Connection kept alive...");
			}
			return HttpHtmlService.inputStreamConvertString(response.getEntity().getContent(), "GB2312");
		} catch (HttpException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public void closeConn() throws IOException {
		try {
			if (conn != null) {
				this.conn.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws Exception {
//		HttpParams params = new BasicHttpParams();
//		HttpProtocolParams.setVersion(params, HttpVersion.HTTP_1_1);
//		HttpProtocolParams.setContentCharset(params, "UTF-8");
//		HttpProtocolParams.setUserAgent(params, "HttpComponents/1.1");
//		HttpProtocolParams.setUseExpectContinue(params, true);
//
//		BasicHttpProcessor httpproc = new BasicHttpProcessor();
//		// Required protocol interceptors
//		httpproc.addInterceptor(new RequestContent());
//		httpproc.addInterceptor(new RequestTargetHost());
//		// Recommended protocol interceptors
//		httpproc.addInterceptor(new RequestConnControl());
//		httpproc.addInterceptor(new RequestUserAgent());
//		httpproc.addInterceptor(new RequestExpectContinue());
//
//		HttpRequestExecutor httpexecutor = new HttpRequestExecutor();
//
//		HttpContext context = new BasicHttpContext(null);
//		HttpHost host = new HttpHost("caipiao.taobao.com", 80);
//
//		DefaultHttpClientConnection conn = new DefaultHttpClientConnection();
//		ConnectionReuseStrategy connStrategy = new DefaultConnectionReuseStrategy();
//
//		context.setAttribute(ExecutionContext.HTTP_CONNECTION, conn);
//		context.setAttribute(ExecutionContext.HTTP_TARGET_HOST, host);
//
//		try {
//			if (!conn.isOpen()) {
//				Socket socket = new Socket(host.getHostName(), host.getPort());
//				conn.bind(socket, params);
//			}
//			BasicHttpRequest request = new BasicHttpRequest("GET", "/lottery/order/united_hall.htm?lottery_type=SSQ");
//			System.out.println(">> Request URI: " + request.getRequestLine().getUri());
//
//			request.setParams(params);
//			httpexecutor.preProcess(request, httpproc, context);
////			response = httpexecutor.execute(request, conn, context);
////			response.setParams(params);
////			httpexecutor.postProcess(response, httpproc, context);
////
////			System.out.println("<< Response: " + response.getStatusLine());
////			System.out.println(EntityUtils.toString(response.getEntity()));
////			System.out.println("==============");
////			if (!connStrategy.keepAlive(response, context)) {
//				conn.close();
//			} else {
//				System.out.println("Connection kept alive...");
//			}
//		} finally {
//			conn.close();
//		}

	}
}
