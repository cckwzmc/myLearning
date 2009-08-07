package com.lyxmq.blog.publisher.sina;

import java.io.IOException;
import java.net.URISyntaxException;

import org.apache.http.HttpException;
import org.apache.http.NameValuePair;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.apache.http.message.BasicNameValuePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lyxmq.blog.publisher.utils.PublisherUtils;
import com.lyxmq.blog.publisher.utils.WebClient;
import com.mysql.jdbc.log.Log;

public class SinaBlog {
	Logger logger=LoggerFactory.getLogger(SinaBlog.class);
	private static final String sinaCharset="UTF8";
	private static SinaBlog sina=null;
	public static SinaBlog getInstance(){
		if(sina==null){
			return new SinaBlog();
		}else{
			return sina;
		}
	}
	
	public SinaBlog(){
		
	}
	private void loginSina(WebClient client){
		BasicClientCookie cookie = new BasicClientCookie("", "");
		client.getHttpClient().getCookieStore().addCookie(cookie);
		String loginPageUrl = "http://my.blog.sina.com.cn/login.php?url=%2F";
		NameValuePair data[] = { new BasicNameValuePair("loginname", "artmm@sina.com"), new BasicNameValuePair("passwd", "yb654321")};
		String content="";
		try {
			content = PublisherUtils.readInputStream(client.doPost(loginPageUrl, data, ""),sinaCharset);
		} catch (IOException e) {
			logger.error("µÇÂ¼sina blog Ê§°Ü"+e.getMessage());
		} catch (HttpException e) {
			logger.error("µÇÂ¼sina blog Ê§°Ü"+e.getMessage());
		} catch (InterruptedException e) {
			logger.error("µÇÂ¼sina blog Ê§°Ü"+e.getMessage());
		} catch (URISyntaxException e) {
			logger.error("µÇÂ¼sina blog Ê§°Ü"+e.getMessage());
		}
		logger.info("sina login ::: :" + content);
	}
	private void publishBlog(WebClient client){
		String loginPageUrl = "http://control.blog.sina.com.cn/admin/article/article_post.php";
		NameValuePair data[] = { new BasicNameValuePair("blog_title", "²âÊÔ²âºÀË¹")
		, new BasicNameValuePair("blog_body", "²âÊÔ")
		, new BasicNameValuePair("articleTime", "00:00:00")
		, new BasicNameValuePair("blog_class", "1")
		, new BasicNameValuePair("x_cms_flag", "1")
		, new BasicNameValuePair("sina_sort_id", "134")
		};
		String content="";
		try {
			content = PublisherUtils.readInputStream(client.doPost(loginPageUrl, data, ""),sinaCharset);
		} catch (IOException e) {
			logger.error("µÇÂ¼sina blog Ê§°Ü"+e.getMessage());
		} catch (HttpException e) {
			logger.error("µÇÂ¼sina blog Ê§°Ü"+e.getMessage());
		} catch (InterruptedException e) {
			logger.error("µÇÂ¼sina blog Ê§°Ü"+e.getMessage());
		} catch (URISyntaxException e) {
			logger.error("µÇÂ¼sina blog Ê§°Ü"+e.getMessage());
		}
		logger.info("sina login ::: :" + content);
	}
	public static void main(String[] args) {
		WebClient client=new WebClient();
		getInstance().loginSina(client);
		getInstance().publishBlog(client);
	}
}
