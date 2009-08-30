package com.myfetch.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpException;
import org.apache.http.NameValuePair;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.apache.http.message.BasicNameValuePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.webservice.client.WebClient;

public class DedePublisherUtils {
	private static final Logger logger = LoggerFactory.getLogger(DedePublisherUtils.class);
	private static final String DIRECTSTR = "如果你的浏览器没反应，请点击这里...";
	private static final String REGEX = ".*?<a href='([^<]*)'>.*?";
	private static final NameValuePair nullData[]={new BasicNameValuePair("null", "")};
	private static String DEFAULTCHAR="UTF-8";
	public static String login(WebClient client, Properties pro) {
		if(!"".equals(pro.getProperty("defaultChar"))){
			DEFAULTCHAR=pro.getProperty("defaultChar");
		}
		BasicClientCookie cookie = new BasicClientCookie("", "");
		client.getHttpClient().getCookieStore().addCookie(cookie);
		String loginPageUrl = pro.getProperty("loginPage");
		NameValuePair data[] = { new BasicNameValuePair("userid", pro.getProperty("loginUsername")), new BasicNameValuePair("pwd", pro.getProperty("loginPassword")), new BasicNameValuePair("adminstyle", "newdedecms"), new BasicNameValuePair("dopost", "login") };
		String content = "";
		try {
			content = readInputStream(client.doPost(loginPageUrl, data, ""));
			logger.info(content);
		} catch (IOException e) {
			logger.error("登录DEDECMS错误" + e.getMessage());
		} catch (HttpException e) {
			logger.error("登录DEDECMS错误" + e.getMessage());
		} catch (InterruptedException e) {
			logger.error("登录DEDECMS错误" + e.getMessage());
		} catch (URISyntaxException e) {
			logger.error("登录DEDECMS错误" + e.getMessage());
		}
		return content;
	}

	/**
	 * 主页发布
	 * 
	 * @param client
	 * @return
	 */
	public static String publishHomePage(WebClient client, Properties pro) {
		String makeHomepage = pro.getProperty("makeHomePage");
		NameValuePair homeData[] = { new BasicNameValuePair("position", "../index.html"), new BasicNameValuePair("templet", "default/index.htm"), new BasicNameValuePair("dopost", "make") };
		String content = "";
		try {
			content = readInputStream(client.doPost(makeHomepage, homeData, ""));
		} catch (IOException e) {
			logger.error("发布首页DEDECMS错误" + e.getMessage());
		} catch (HttpException e) {
			logger.error("发布首页DEDECMS错误" + e.getMessage());
		} catch (InterruptedException e) {
			logger.error("发布首页DEDECMS错误" + e.getMessage());
		} catch (URISyntaxException e) {
			logger.error("发布首页DEDECMS错误" + e.getMessage());
		}
		return content;
	}

	/**
	 * 更新DEDE缓存 比较特殊 包含成果清理时可以退出 location='sys_cache_up.php?dopost=ok&step=-1&uparc=0';
	 * 
	 * @param client
	 * @return
	 */
	public static String updateDedeCache(WebClient client, Properties pro) {
		String content = "";
		String cacheEx = ".*?location='([^<]*)'.*?";
		String okStr = "成功更新所有缓存";
		String updateCachePage = pro.getProperty("updateCachePage");
		NameValuePair cacheData[] = { new BasicNameValuePair("dopost", "ok") };
		try {
			int i = 0;
			while (true && i < 9) {
				if(StringUtils.contains(updateCachePage, "dopost"))
				{
					content = readInputStream(client.doPost(updateCachePage, nullData, ""));
				}else{
					content = readInputStream(client.doPost(updateCachePage, cacheData, ""));
				}
				if (StringUtils.contains(content, DIRECTSTR)) {
					Pattern pt = Pattern.compile(REGEX);
					Matcher matcher = pt.matcher(content);
					if (matcher.find()) {
						updateCachePage = matcher.group(1);
						if (!updateCachePage.startsWith("http:") && !updateCachePage.startsWith("/")) {
							updateCachePage = pro.getProperty("rootPage") + updateCachePage;
						} else if (updateCachePage.startsWith("/")) {
							updateCachePage = pro.getProperty("systemRoot") + updateCachePage;
						}
					}
				} else if (StringUtils.contains(content, "location='sys_cache_up.php")) {
					Pattern pt = Pattern.compile(cacheEx);
					Matcher matcher = pt.matcher(content);
					if (matcher.find()) {
						updateCachePage = matcher.group(1);
						if (!updateCachePage.startsWith("http:") && !updateCachePage.startsWith("/")) {
							updateCachePage = pro.getProperty("rootPage") + updateCachePage;
						} else if (updateCachePage.startsWith("/")) {
							updateCachePage = pro.getProperty("systemRoot") + updateCachePage;
						}
					}
				}
				logger.info(content);
				if (StringUtils.contains(content, okStr)) {
					break;
				}
				i++;
			}
		} catch (IOException e) {
			logger.error("更新缓存DEDECMS错误" + e.getMessage());
		} catch (HttpException e) {
			logger.error("更新缓存DEDECMS错误" + e.getMessage());
		} catch (InterruptedException e) {
			logger.error("更新缓存DEDECMS错误" + e.getMessage());
		} catch (URISyntaxException e) {
			logger.error("更新缓存DEDECMS错误" + e.getMessage());
		}
		return content;
	}

	public static String publishArchives(WebClient client, Properties pro,String minArcId,String maxArcId) {
		String content="";
		String okStr = "完成所有创建任务";
		String updateCachePage = pro.getProperty("makeArchivesPage");
		NameValuePair cacheData[] = { new BasicNameValuePair("pagesize", "20"),new BasicNameValuePair("endid", maxArcId),new BasicNameValuePair("typeid", "0"), new BasicNameValuePair("startid", ObjectUtils.toString(minArcId)) };
		try {
			int i = 0;
			while (true && i < 10009) {
				if(StringUtils.contains(updateCachePage, "startdd"))
				{
					content = readInputStream(client.doPost(updateCachePage, nullData, ""));
				}else{
					content = readInputStream(client.doPost(updateCachePage, cacheData, ""));
				}	
				if (StringUtils.contains(content, DIRECTSTR)) {
					Pattern pt = Pattern.compile(REGEX);
					Matcher matcher = pt.matcher(content);
					if (matcher.find()) {
						updateCachePage = matcher.group(1);
						if (!updateCachePage.startsWith("http:") && !updateCachePage.startsWith("/")) {
							updateCachePage = pro.getProperty("rootPage") + updateCachePage;
						} else if (updateCachePage.startsWith("/")) {
							updateCachePage = pro.getProperty("systemRoot") + updateCachePage;
						}
					}
				} 
				logger.info(content);
				if (StringUtils.contains(content, okStr)) {
					logger.info(content);
					break;
				}
				i++;
			}
		} catch (IOException e) {
			logger.error("生成文档DEDECMS错误" + e.getMessage());
		} catch (HttpException e) {
			logger.error("生成文档DEDECMS错误" + e.getMessage());
		} catch (InterruptedException e) {
			logger.error("生成文档DEDECMS错误" + e.getMessage());
		} catch (URISyntaxException e) {
			logger.error("生成文档DEDECMS错误" + e.getMessage());
		}
		return content;
	}

	public static String publishList(WebClient client, Properties pro,String typeid) {
		String content="";
		String okStr = "完成所有栏目列表更新";
		String updateCachePage = pro.getProperty("makeListPage");
		NameValuePair data[] = { new BasicNameValuePair("pagesize", "50"),new BasicNameValuePair("upnext", "1"),new BasicNameValuePair("typeid", typeid)};
		try {
			int i = 0;
			while (true && i < 10009) {
				if(StringUtils.contains(updateCachePage, "pagesize")){
					content = readInputStream(client.doPost(updateCachePage, nullData, ""));
				}else{
					content = readInputStream(client.doPost(updateCachePage, data, ""));
				}
				if (StringUtils.contains(content, DIRECTSTR)) {
					Pattern pt = Pattern.compile(REGEX);
					Matcher matcher = pt.matcher(content);
					if (matcher.find()) {
						updateCachePage = matcher.group(1);
						if (!updateCachePage.startsWith("http:") && !updateCachePage.startsWith("/")) {
							updateCachePage = pro.getProperty("rootPage") + updateCachePage;
						} else if (updateCachePage.startsWith("/")) {
							updateCachePage = pro.getProperty("systemRoot") + updateCachePage;
						}
					}
				} 
				
				if (StringUtils.contains(content, okStr)) {
					logger.info(content);
					break;
				}
				i++;
			
			}
		} catch (IOException e) {
			logger.error("生成列表DEDECMS错误" + e.getMessage());
		} catch (HttpException e) {
			logger.error("生成列表DEDECMS错误" + e.getMessage());
		} catch (InterruptedException e) {
			logger.error("生成列表DEDECMS错误" + e.getMessage());
		} catch (URISyntaxException e) {
			logger.error("生成列表DEDECMS错误" + e.getMessage());
		}
		return content;
	}

	public static String publishPage(WebClient client, Properties pro,String arcid) {
		String content="";
		String okStr = "完成所有创建任务";
		String updateCachePage = pro.getProperty("makePagePage");
		NameValuePair data[] = { new BasicNameValuePair("xstype", "page"),new BasicNameValuePair("pagesize", "20"),new BasicNameValuePair("typeid", "0"),new BasicNameValuePair("startid", arcid),new BasicNameValuePair("endid", arcid)};
		try {
			int i = 0;
			while (true && i < 200) {
				if(StringUtils.contains(updateCachePage, "pagesize")){
					content = readInputStream(client.doPost(updateCachePage, nullData, ""));
				}else{
					content = readInputStream(client.doPost(updateCachePage, data, ""));
				}
				if (StringUtils.contains(content, DIRECTSTR)) {
					Pattern pt = Pattern.compile(REGEX);
					Matcher matcher = pt.matcher(content);
					if (matcher.find()) {
						updateCachePage = matcher.group(1);
						if (!updateCachePage.startsWith("http:") && !updateCachePage.startsWith("/")) {
							updateCachePage = pro.getProperty("rootPage") + updateCachePage;
						} else if (updateCachePage.startsWith("/")) {
							updateCachePage = pro.getProperty("systemRoot") + updateCachePage;
						}
					}
				} 
				logger.info(content);
				if (StringUtils.contains(content, okStr)) {
					logger.info(content);
					break;
				}
				i++;
				
			}
		} catch (IOException e) {
			logger.error("生成封面DEDECMS错误" + e.getMessage());
		} catch (HttpException e) {
			logger.error("生成封面DEDECMS错误" + e.getMessage());
		} catch (InterruptedException e) {
			logger.error("生成封面DEDECMS错误" + e.getMessage());
		} catch (URISyntaxException e) {
			logger.error("生成封面DEDECMS错误" + e.getMessage());
		}
		return content;
	}

	public static String publishTable(WebClient client, Properties pro,String typeid) {
		String content="";
		String okStr = "完成所有栏目列表更新";
		String updateCachePage = pro.getProperty("makeTablePage");
		NameValuePair data[] = { new BasicNameValuePair("xstype", "table"),new BasicNameValuePair("pagesize", "50"),new BasicNameValuePair("upnext", "1"),new BasicNameValuePair("typeid", typeid)};
		try {
			int i = 0;
			while (true && i < 200) {
				if(StringUtils.contains(updateCachePage, "pagesize")){
					content = readInputStream(client.doPost(updateCachePage, nullData, ""));
				}else{
					content = readInputStream(client.doPost(updateCachePage, data, ""));
				}
				if (StringUtils.contains(content, DIRECTSTR)) {
					Pattern pt = Pattern.compile(REGEX);
					Matcher matcher = pt.matcher(content);
					if (matcher.find()) {
						updateCachePage = matcher.group(1);
						if (!updateCachePage.startsWith("http:") && !updateCachePage.startsWith("/")) {
							updateCachePage = pro.getProperty("rootPage") + updateCachePage;
						} else if (updateCachePage.startsWith("/")) {
							updateCachePage = pro.getProperty("systemRoot") + updateCachePage;
						}
					}
				} 
				if (StringUtils.contains(content, okStr)) {
					logger.info(content);
					break;
				}
				i++;
				
			}
		} catch (IOException e) {
			logger.error("生成目录DEDECMS错误" + e.getMessage());
		} catch (HttpException e) {
			logger.error("生成目录DEDECMS错误" + e.getMessage());
		} catch (InterruptedException e) {
			logger.error("生成目录DEDECMS错误" + e.getMessage());
		} catch (URISyntaxException e) {
			logger.error("生成目录DEDECMS错误" + e.getMessage());
		}
		return content;
	}

	/**
	 * Reads an inputstream and converts it to a string. Note that this is rather memory intensive, if you do not need random access in the inputstream you should iterate sequentially over the lines using readLine()
	 * 
	 * @param is
	 *            the inputstream to convert
	 * @return the content of the input stream
	 * @throws IOException
	 *             if reading the inputstream fails
	 */
	protected static String readInputStream(InputStream is) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(is, DEFAULTCHAR));
		StringBuffer buffer = new StringBuffer();
		String line;
		while ((line = in.readLine()) != null) {
			buffer.append(line);
		}
		is.close();
		return buffer.toString();
	}

	public static void main(String[] args) {
		Properties pro;
		try {
			pro = PropertiesLoaderUtils.loadAllProperties("myfetch/publisher/publisherUrl.properties");
			WebClient client = new WebClient();
			String content = login(client, pro);
			logger.info(content);
			content = publishHomePage(client, pro);
			logger.info(content);
			content = updateDedeCache(client, pro);
			logger.info(content);
			content = publishArchives(client, pro, "1","0");
			logger.info(content);
			content = publishTable(client, pro, "0");
			logger.info(content);
			content = publishPage(client, pro, "0");
			logger.info(content);
			content = publishList(client, pro, "0");
			logger.info(content);
			
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
