package com.lottery.ssq.fetch.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import net.htmlparser.jericho.Element;
import net.htmlparser.jericho.Source;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.HttpVersion;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.protocol.BasicHttpProcessor;
import org.apache.http.protocol.RequestConnControl;
import org.apache.http.protocol.RequestContent;
import org.apache.http.protocol.RequestExpectContinue;
import org.apache.http.protocol.RequestTargetHost;
import org.apache.http.protocol.RequestUserAgent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lottery.ssq.config.LotterySsqConfig;
import com.lottery.ssq.dao.LotteryDao;
import com.lottery.ssq.fetch.dao.LotteryFetchDao;
import com.lottery.ssq.httpclient.WebClient;
import com.lottery.ssq.httpclient.WrapLotteryHttpClient;
import com.lottery.util.html.HttpHtmlService;

public class LotterySsqCustomerTaobaoFetchService extends Thread {
	private static final Logger logger = LoggerFactory.getLogger(LotterySsqCustomerTaobaoFetchService.class);
	private LotteryDao lotteryDao;
	private LotteryFetchDao lotteryFetchDao;

	public void setLotteryFetchDao(LotteryFetchDao lotteryFetchDao) {
		this.lotteryFetchDao = lotteryFetchDao;
	}

	public void setLotteryDao(LotteryDao lotteryDao) {
		this.lotteryDao = lotteryDao;
	}

	static final String taobaoSsq = "http://caipiao.taobao.com/lottery/order/united_hall.htm?lottery_type=SSQ";
	static String taobaoProjectUrl = "http://caipiao.taobao.com/lottery/order/united_hall.htm?_tb_token_=@token@&page=@page@&lottery_type=SSQ&sort_obj=totalfee&sort=asc&change_sort=true&lowAmount=&highAmount=&issue=@issue@&is_not_full=0&amountSec=0-0&commissionRate=-1&creator=";
	static String taobaoDetailUrl = "http://caipiao.taobao.com/lottery/order/united_order_detail.htm?_tb_token_=@token@&page=@page@&tb_united_id=@unitedId@&is_history=false";
	String token = "";
	String issue = "";
	int maxpage = 1;
	private WebClient webClient;

	public void run() {

	}

	public void initTaobaoUrlData() {
		try {
			HttpParams params = new BasicHttpParams();
			HttpProtocolParams.setVersion(params, HttpVersion.HTTP_1_1);
			HttpProtocolParams.setUserAgent(params, "Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 5.1)");
			HttpProtocolParams.setUseExpectContinue(params, true);
			HttpConnectionParams.setConnectionTimeout(params, 5000);
			HttpConnectionParams.setSoTimeout(params, 5000);
			BasicHttpProcessor httpproc = new BasicHttpProcessor();
			httpproc.addInterceptor(new RequestContent());
			httpproc.addInterceptor(new RequestTargetHost());
			httpproc.addInterceptor(new RequestConnControl());
			httpproc.addInterceptor(new RequestUserAgent());
			httpproc.addInterceptor(new RequestExpectContinue());
			this.token = "";
			this.issue = "";
			webClient = new WebClient("www.taobao.com");
			HttpUriRequest request = new HttpGet(taobaoSsq);
			webClient.getHttpClient().getConnectionManager().closeExpiredConnections();
			webClient.getHttpClient().getConnectionManager().closeIdleConnections(0, TimeUnit.MILLISECONDS);
			request.setParams(params);
			HttpResponse response = webClient.getHttpClient().execute(request);
			// 打印响应信息
			String html = HttpHtmlService.inputStreamConvertString(response.getEntity().getContent(), "GB2312");
			Source source = new Source(html);
			List<Element> list = source.getAllElements("input");
			if (CollectionUtils.isNotEmpty(list)) {
				for (Element e : list) {
					if ("_tb_token_".equalsIgnoreCase(e.getAttributeValue("name"))) {
						this.token = e.getAttributeValue("value");
					}
					if ("issue_id".equalsIgnoreCase(e.getAttributeValue("name"))) {
						this.issue = e.getAttributeValue("value");
					}
					if (this.token != "" && this.issue != "") {
						break;
					}
				}
			}
		} catch (ClientProtocolException e) {
			logger.error(e.getMessage() + e);
		} catch (IOException e) {
			logger.error(e.getMessage());
		}
	}

	// <div class="errorTips">
	// <h3>发生错误</h3>
	// </div>
	public List<String> fetchTaobaoSsqData() {
		List<String> prjDetailUrl = new ArrayList<String>();
		int currentPage = 1;
		for (int i = currentPage; i <= this.maxpage; i++) {
			this.initTaobaoUrlData();
			try {
				String uri = StringUtils.replace(taobaoProjectUrl, "@token@", token);
				uri = StringUtils.replace(uri, "@issue@", this.issue);
				uri = StringUtils.replace(uri, "@page@", String.valueOf(i));
				logger.info(uri);
				HttpGet request = new HttpGet(uri);
				HttpResponse response = webClient.getHttpClient().execute(request);
				// 打印响应信息
				String html = HttpHtmlService.inputStreamConvertString(response.getEntity().getContent(), "gb2312");
				Source source = new Source(html);
				if (this.maxpage <= 1) {
					List<Element> mList = source.getAllElementsByClass("maxpage");
					if (CollectionUtils.isNotEmpty(mList)) {
						for (Element e : mList) {
							if (StringUtils.isNotBlank(e.getTextExtractor().toString()) && StringUtils.isNumeric(e.getTextExtractor().toString())) {
								maxpage = NumberUtils.toInt(e.getTextExtractor().toString());
								break;
							}
						}
					}
					if (this.maxpage > 1) {
						logger.info("淘宝网双色球最大抓取页数为" + this.maxpage);
					}
				}
				List<Element> eList = source.getAllElementsByClass("sm-btn");
				for (Element e : eList) {
					if (e.getTextExtractor().toString().indexOf("详情") > -1) {
						String unitedId = StringUtils.substring(e.getAttributeValue("href"), e.getAttributeValue("href").indexOf("united_id=") + 10);
						if (this.lotteryDao.getCountSsqLotteryCollectFetchByProid(unitedId, "10") > 0) {
							continue;
						}
						prjDetailUrl.add(e.getAttributeValue("href"));
					}
				}
				fecthProjectCode(prjDetailUrl);
				prjDetailUrl.clear();
			} catch (ClientProtocolException e) {
				logger.error(e.getMessage() + e);
			} catch (IOException e) {
				logger.error(e.getMessage());
			}
			try {
				sleep(2000);
			} catch (InterruptedException e) {
				notifyAll();
			}

		}
		return prjDetailUrl;
	}

	private void fecthProjectCode(List<String> prjDetailUrl) {
		List<Map<String, String>> codeList = new ArrayList<Map<String, String>>();
		String unitedId = "";
		if (CollectionUtils.isEmpty(prjDetailUrl)) {
			return;
		}
		for (int i = 0; i < prjDetailUrl.size(); i++) {
			try {
				String uri = prjDetailUrl.get(i);
				if (i > 0 && i % 5 == 0) {
					this.initTaobaoUrlData();
				}
				unitedId = StringUtils.substring(uri, uri.indexOf("united_id=") + 10);
				uri = StringUtils.replace(taobaoDetailUrl, "@token@", token);
				uri = StringUtils.replace(uri, "@page@", "1");
				uri = StringUtils.replace(uri, "@unitedId@", unitedId);
				HttpGet request = new HttpGet(uri);
				logger.info("号码详情" + uri);

				HttpResponse response = webClient.getHttpClient().execute(request);
				if(response.getStatusLine().getStatusCode()!=HttpStatus.SC_OK){
					logger.info("223--连接失败....");
					continue;
				}
				String html = HttpHtmlService.inputStreamConvertString(response.getEntity().getContent(), "gb2312");
				Source source = new Source(html);
				List<Element> pageInfo = source.getAllElementsByClass("page-info");
				logger.info("199号码详情不动了====响应不动了出错");
				if (CollectionUtils.isEmpty(pageInfo)) {
					Map<String, String> tmpMap = new HashMap<String, String>();
					tmpMap.put("proid", unitedId);
					tmpMap.put("net", "10");
					tmpMap.put("expect", LotterySsqConfig.expect);
					tmpMap.put("code", "-1");
					tmpMap.put("isfail", "1");
					codeList.add(tmpMap);
				} else if (CollectionUtils.isNotEmpty(pageInfo)) {
					String pinfo = pageInfo.get(0).getTextExtractor().toString();
					int totalPage = NumberUtils.toInt(StringUtils.substring(StringUtils.trim(pinfo), StringUtils.indexOf(pinfo, "/") + 1).trim());
					if (totalPage > 1) {
						Map<String, String> tmpMap = new HashMap<String, String>();
						tmpMap.put("proid", unitedId);
						tmpMap.put("net", "10");
						tmpMap.put("expect", LotterySsqConfig.expect);
						tmpMap.put("code", "");
						for (int page = 1; page <= totalPage; page++) {
							if (page % 5 == 0) {
								this.initTaobaoUrlData();
							}
							if(response.getStatusLine().getStatusCode()!=HttpStatus.SC_OK){
								logger.info("223--连接失败....");
								continue;
							}
							uri = StringUtils.replace(taobaoDetailUrl, "@token@", token);
							uri = StringUtils.replace(uri, "@page@", "" + page);
							uri = StringUtils.replace(uri, "@unitedId@", unitedId);
							logger.info("号码详情" + uri);
							request = new HttpGet(uri);
							response = webClient.getHttpClient().execute(request);
							html = HttpHtmlService.inputStreamConvertString(response.getEntity().getContent(), "gb2312");
							List<String> detaiList = new ArrayList<String>();
							logger.info("233号码详情不动了====响应不动了出错");
							detaiList = this.getCodeFromDetail(html);
							if (CollectionUtils.isNotEmpty(detaiList)) {
								if ("".equals(tmpMap.get("code"))) {
									tmpMap.put("code", StringUtils.join(detaiList.toArray(), "@@"));
								} else {
									tmpMap.put("code", StringUtils.join(detaiList.toArray(), "@@") + "@@" + tmpMap.get("code"));
								}
							}
							try {
								sleep(2000);
							} catch (InterruptedException e) {
								notifyAll();
							}
						}
						tmpMap.put("isfail", "0");
						codeList.add(tmpMap);
					} else {
						List<String> detaiList = new ArrayList<String>();
						detaiList = this.getCodeFromDetail(html);
						if (CollectionUtils.isNotEmpty(detaiList)) {
							Map<String, String> tmpMap = new HashMap<String, String>();
							tmpMap.put("proid", unitedId);
							tmpMap.put("net", "10");
							tmpMap.put("expect", LotterySsqConfig.expect);
							tmpMap.put("code", StringUtils.join(detaiList.toArray(), "@@"));
							tmpMap.put("isfail", "0");
							codeList.add(tmpMap);
						}
					}
				}

			} catch (ClientProtocolException e) {
				logger.error(e.getMessage() + e);
				continue;
			} catch (IOException e) {
				logger.error(e.getMessage() + e);
				continue;
			} catch (Exception e) {
				e.printStackTrace();
				continue;
			}
			try {
				sleep(2000);
			} catch (InterruptedException e) {
				notifyAll();
			}
		}
		this.lotteryDao.batchSaveSsqLotteryCollectFetch(codeList);
		codeList.clear();
	}

	public void saveFecthTaobaoData() {
		this.lotteryDao.clearHisFetchProjectCode(LotterySsqConfig.expect, "10");
		this.fetchTaobaoSsqData();
	}

	public List<String> getCodeFromDetail(String html) {
		Source source = new Source(html);
		List<String> retList = new ArrayList<String>();
		List<Element> list = source.getAllElementsByClass("td2");
		if (CollectionUtils.isNotEmpty(list)) {
			for (Element e : list) {
				String code = e.getTextExtractor().toString();
				int index = 0;
				if (code.indexOf("注") > -1) {
					if (code.indexOf("单式") > -1) {
						index = code.indexOf("单式");
					}
					if (code.indexOf("复式") > -1) {
						index = code.indexOf("复式");
					}
					if (code.indexOf("胆拖") > -1) {
						index = code.indexOf("胆拖");
					}
					code = StringUtils.substring(code, 0, index);
				}
				code = this.replaceCodeChar(code);
				if (StringUtils.isNotBlank(code)) {
					retList.add(code);
				}
			}
		}
		return retList;
	}

	private String replaceCodeChar(String code) {
		code = StringUtils.trim(code);
		code = StringUtils.replace(code, ":", "+");
		code = StringUtils.replace(code, " ", ",");
		return code;
	}

	public static void main(String[] args) {
		String issue = "";
		String token = "";
		WrapLotteryHttpClient client = new WrapLotteryHttpClient("caipiao.taobao.com", 80);
		String html = client.getHtmlContent("/lottery/order/united_hall.htm?lottery_type=SSQ");
		System.out.println(html);
		Source source = new Source(html);
		List<Element> list = source.getAllElements("input");
		if (CollectionUtils.isNotEmpty(list)) {
			for (Element e : list) {
				if ("_tb_token_".equalsIgnoreCase(e.getAttributeValue("name"))) {
					token = e.getAttributeValue("value");
				}
				if ("issue_id".equalsIgnoreCase(e.getAttributeValue("name"))) {
					issue = e.getAttributeValue("value");
				}
				if (StringUtils.isNotBlank(token) && StringUtils.isNotBlank(issue)) {
					break;
				}
			}
		}
		int currentPage = 1;
		int maxpage = 1;
		for (int i = currentPage; i <= maxpage; i++) {
			try {
				String uri = StringUtils.replace(taobaoProjectUrl, "@token@", token);
				uri = StringUtils.replace(uri, "@issue@", issue);
				uri = StringUtils.replace(uri, "@page@", String.valueOf(i));
				uri = StringUtils.replace(uri, "http://caipiao.taobao.com", "");
				html = client.getHtmlContent(uri);
				Source source1 = new Source(html);
				logger.info(html);
				if (maxpage <= 1) {
					List<Element> mList = source1.getAllElementsByClass("maxpage");
					if (CollectionUtils.isNotEmpty(mList)) {
						for (Element e : mList) {
							if (StringUtils.isNotBlank(e.getTextExtractor().toString()) && StringUtils.isNumeric(e.getTextExtractor().toString())) {
								maxpage = NumberUtils.toInt(e.getTextExtractor().toString());
								break;
							}
						}
					}
					if (maxpage > 1) {
						logger.info("淘宝网双色球最大抓取页数为" + maxpage);
					}
				}
				List<Element> eList = source.getAllElementsByClass("sm-btn");
				for (Element e : eList) {
					if (e.getTextExtractor().toString().indexOf("详情") > -1) {
						logger.info(e.getAttributeValue("href"));
					}
				}
			} catch (Exception e) {
				logger.error(e.getMessage());
			}
		}
		// LotterySsqCustomerTaobaoFetchService service = new LotterySsqCustomerTaobaoFetchService();
		// service.saveFecthTaobaoData();
		// String url = service.getTaobaoSsqTemplateUrl();
		// logger.info(url);
		// logger.info(getHtmlContent(url, "gb2312"));
		// WebClient client = new WebClient("www.taobao.com");
		// HttpUriRequest request = new HttpGet(taobaoSsq);
		// HttpParams params = new BasicHttpParams();
		// HttpConnectionParams.setConnectionTimeout(params, 6000);
		// HttpConnectionParams.setSoTimeout(params, 60000);
		// request.setParams(params);
		//
		// // 发送请求，返回响应
		//
		// try {
		// HttpResponse response = client.getHttpClient().execute(request);
		// // 打印响应信息
		// String html = HttpHtmlService.inputStreamConvertString(response.getEntity().getContent(), "GB2312");
		// System.out.println(html);
		// Source source = new Source(html);
		// List<Element> list = source.getAllElements("input");
		// if (CollectionUtils.isNotEmpty(list)) {
		// for (Element e : list) {
		// if ("_tb_token_".equalsIgnoreCase(e.getAttributeValue("name"))) {
		// String token = e.getAttributeValue("value");
		// String uri = StringUtils.replace(taobaoProjectUrl, "@token@", token);
		// request = new HttpGet(uri);
		// response = client.getHttpClient().execute(request);
		// // 打印响应信息
		// html = HttpHtmlService.inputStreamConvertString(response.getEntity().getContent(), "gb2312");
		// System.out.println(html);
		// break;
		// }
		// }
		// }
		// } catch (ClientProtocolException e) {
		// // Auto-generated catch block
		// e.printStackTrace();
		// } catch (IOException e) {
		// // Auto-generated catch block
		// e.printStackTrace();
		// }

	}
}
