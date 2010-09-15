package com.lottery.htmlparser;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import junit.framework.TestCase;
import net.htmlparser.jericho.Element;
import net.htmlparser.jericho.Source;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.http.ConnectionReuseStrategy;
import org.apache.http.HttpException;
import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.DefaultConnectionReuseStrategy;
import org.apache.http.impl.DefaultHttpClientConnection;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.DefaultHttpRequestRetryHandler;
import org.apache.http.message.BasicHttpRequest;
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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lottery.util.html.HttpHtmlService;

public class HtmlParserTest extends TestCase {
	private static final Logger logger = LoggerFactory.getLogger(HtmlParserTest.class);

	public void testParserHtml() {
		String html = HttpHtmlService
				.getHtmlContent(
						"http://trade.500wan.com/pages/trade/ssq/project_list_sz_in.php?key=allmoney&sort=desc&pages_num=1&page=4&type=&ticheng_term=-1&state_term=2&totalcount_term=0~0&permoney_term=0~0&plan_term=0~0&baodi_term=0&currentsort=desc&currentkey=renqi&lotid=3&playid=1&expect=10040&rnd=91050",
						"GB2312");

		String jsonStr = StringUtils.substringBetween(html, "data:[", "],pagestr");
		JSONArray array = JSONArray.fromObject("[" + jsonStr + "]");
		String download = "http://trade.500wan.com/pages/trade/ssq/project_ssqshow.php?pid=@pid@&@nowdate@";
		Date nowdate = new Date();
		download = StringUtils.replace(download, "@nowdate@", nowdate.getTime() + "");
		for (int i = 0; i < array.size(); i++) {
			JSONObject jsonObj = (JSONObject) array.get(i);
			String fangan = jsonObj.getString("fangan");
			if (fangan.indexOf("查看") == -1) {
				continue;
			}
			if (fangan.toLowerCase().indexOf("onclick") != -1) {
				download = StringUtils.replace(download, "@pid@", StringUtils.substringBetween(fangan, "list.showProject(", ")"));
			} else {
				download = "http://" + StringUtils.substringBetween(fangan, "http://", "'");
			}
			String ds = HttpHtmlService.getHtmlContent(download, "gb2312");

			if (ds.indexOf("red") != -1) {

				Source source = new Source(ds);
				Element projectDetailList = source.getElementById("projectDetailList");
				List<Element> detail = projectDetailList.getAllElementsByClass("num");
				for (Element e : detail) {
					String code = e.getContent().getTextExtractor().toString();
					if (code.indexOf("红球") != -1) {
						code = StringUtils.replace(code, " ", "");
						code = StringUtils.replace(code, "蓝球:", "+");
						code = StringUtils.replace(code, "红球:", "");
					} else {
						code = StringUtils.replace(code, " ", "");
						code = StringUtils.replace(code, "蓝球:", "+");
						code = StringUtils.replace(code, "胆:", "");
						code = StringUtils.replace(code, "拖:", ",");
						String[] codes = StringUtils.split(code, "+");
						String[] redCode = StringUtils.split(codes[0], ",");
						Arrays.sort(redCode);
						code = StringUtils.join(redCode, ",") + "+" + codes[1];
					}
					logger.info(code);
				}

			}
		}
		// logger.info(jsonStr);
		// logger.info("=="+JSONUtils.mayBeJSON(jsonStr));
		// if(JSONUtils.mayBeJSON(jsonStr)){
		// JSONObject jsonOop=new JSONObject();
		// // JSONArray array=jsonOop.getJSONArray(jsonStr);
		// //jsonOop=JSONObject.fromObject(jsonStr);
		// // JSONArray array=JSONArray.fromObject(jsonStr);
		// // jSon
		// logger.info(JSONUtils.isArray(html)+"");
		// Map map=JSONUtils.getProperties(jsonOop);
		// }
		// // input file
		// Source source = new Source(html);
		// Element element=source.getElementById("table1");
		// List<Element> eList=element.getAllElements("tr");
		// for(Element e:eList.subList(1, eList.size())){
		// List<Element> tdList=e.getAllElements("td");
		// Element tdE=tdList.get(3);
		// List<Element> divList=tdE.getAllElements("div");
		// for(Element eDiv:divList){
		// logger.info(eDiv.getContent().toString());
		// }
		// logger.info(tdE.getContent().toString());
		// }
	}

	public void testHttpClient() throws UnknownHostException, IOException, HttpException {
		HttpParams params = new BasicHttpParams();
		HttpProtocolParams.setVersion(params, HttpVersion.HTTP_1_1);
		HttpProtocolParams.setContentCharset(params, "GB2312");
		HttpProtocolParams.setUserAgent(params, "HttpComponents/1.1");
		HttpProtocolParams.setUseExpectContinue(params, true);

		BasicHttpProcessor httpproc = new BasicHttpProcessor();
		// Required protocol interceptors
		httpproc.addInterceptor(new RequestContent());
		httpproc.addInterceptor(new RequestTargetHost());
		// Recommended protocol interceptors
		httpproc.addInterceptor(new RequestConnControl());
		httpproc.addInterceptor(new RequestUserAgent());
		httpproc.addInterceptor(new RequestExpectContinue());

		HttpRequestExecutor requestExecutor = new HttpRequestExecutor();
		HttpContext context = new BasicHttpContext(null);

		DefaultHttpClientConnection conn = new DefaultHttpClientConnection();
		ConnectionReuseStrategy connStrategy = new DefaultConnectionReuseStrategy();

		context.setAttribute(ExecutionContext.HTTP_CONNECTION, conn);
		if (!conn.isOpen()) {
			Socket socket = new Socket("http://www.sina.com.cn", 80);
			conn.bind(socket, params);
		}
		BasicHttpRequest request = new BasicHttpRequest("GET", null);
		System.out.println(">> Request URI: " + request.getRequestLine().getUri());

		request.setParams(params);
		requestExecutor.preProcess(request, httpproc, context);
		HttpResponse response = requestExecutor.execute(request, conn, context);
		response.setParams(params);
		requestExecutor.postProcess(response, httpproc, context);

		System.out.println("<< Response: " + response.getStatusLine());
		System.out.println(EntityUtils.toString(response.getEntity()));
		System.out.println("==============");
		if (!connStrategy.keepAlive(response, context)) {
			conn.close();
		} else {
			System.out.println("Connection kept alive...");
		}

	}

	public void testBaseHttpClient() {
		
		// 核心应用类
		HttpClient httpClient = new DefaultHttpClient();
		// HTTP请求
		HttpUriRequest request = new HttpGet("http://www.sina.com.cn");
		// 打印请求信息
		System.out.println(request.getRequestLine());
		try {
			HttpParams params=new BasicHttpParams();
			HttpConnectionParams.setConnectionTimeout(params, 6000);
			HttpConnectionParams.setSoTimeout(params, 60000);
			request.setParams(params);
			
			// 发送请求，返回响应
			HttpResponse response = httpClient.execute(request);
			// 打印响应信息
			System.out.println(HttpHtmlService.inputStreamConvertString(response.getEntity().getContent(),"GB2312"));
		} catch (ClientProtocolException e) {
			// 协议错误
			e.printStackTrace();
		} catch (IOException e) {
			// 网络异常
			e.printStackTrace();

		}
	}
}
