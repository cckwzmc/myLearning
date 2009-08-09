package com.lyxmq.blog.publisher.sina;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.http.HttpException;
import org.apache.http.NameValuePair;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.apache.http.message.BasicNameValuePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lyxmq.blog.publisher.dao.CommonsServiceDao;
import com.lyxmq.blog.publisher.utils.HtmlParseUtils;
import com.lyxmq.blog.publisher.utils.PublisherUtils;
import com.lyxmq.blog.publisher.utils.WebClient;

public class SinaBlog {
	private static Logger logger = LoggerFactory.getLogger(SinaBlog.class);
	private CommonsServiceDao commonsServiceDao = null;
	private SinaBlogDao sinaBlogDao = null;
	private static final String sinaCharset = "UTF8";
	private static SinaBlog sina = null;
	private static WebClient webClient = null;

	public WebClient getWebClient() {
		if(webClient==null){
			return new WebClient();
		}
		return webClient;
	}


	public static SinaBlog getInstance() {
		if (sina == null) {
			return new SinaBlog();
		} else {
			return sina;
		}
	}

	public CommonsServiceDao getCommonsServiceDao() {
		return commonsServiceDao;
	}

	public void setCommonsServiceDao(CommonsServiceDao commonsServiceDao) {
		this.commonsServiceDao = commonsServiceDao;
	}

	public SinaBlogDao getSinaBlogDao() {
		return sinaBlogDao;
	}

	public void setSinaBlogDao(SinaBlogDao sinaBlogDao) {
		this.sinaBlogDao = sinaBlogDao;
	}

	public SinaBlog() {

	}

	@SuppressWarnings("unchecked")
	public void sinaPublishService() {
		List pubList = this.commonsServiceDao.getPublishDataList("sina");
		String tempUsername="";
		if (CollectionUtils.isNotEmpty(pubList)) {
			for (Iterator iterator = pubList.iterator(); iterator.hasNext();) {
				String result="";
				Map map = (Map) iterator.next();
				if("".equals(tempUsername)){
					loginSina(getWebClient());
				}else if(!StringUtils.equals(tempUsername,ObjectUtils.toString(map.get("username")))){
					logoutSina(getWebClient());
					loginSina(getWebClient());
				}	
				tempUsername=ObjectUtils.toString(map.get("username"));
				try {
					result=publishBlog(getWebClient(),map);
				} catch (IOException e) {
					logger.error(e.getMessage());
					result=e.getMessage().substring(0,200);
				} catch (URISyntaxException e) {
					logger.error(e.getMessage());
					result=e.getMessage().substring(0,200);
				} catch (InterruptedException e) {
					logger.error(e.getMessage());
					result=e.getMessage().substring(0,200);
				} catch (HttpException e) {
					logger.error(e.getMessage());
					result=e.getMessage().substring(0,200);
				}
				if(!(StringUtils.contains(result, "B06001")||StringUtils.contains(result, "B06002")||StringUtils.contains(result, "B06003"))){
					this.commonsServiceDao.saveFailPublish(NumberUtils.toInt(ObjectUtils.toString(map.get("id"))),result);
				}
			}
		}
	}

	private void logoutSina(WebClient webClient2) {
		
	}


	private void loginSina(WebClient client) {
		BasicClientCookie cookie = new BasicClientCookie("", "");
		client.getHttpClient().getCookieStore().addCookie(cookie);
		String loginPageUrl = "http://my.blog.sina.com.cn/login.php?url=%2F";
		NameValuePair data[] = { new BasicNameValuePair("loginname", "artmm@sina.com"), new BasicNameValuePair("passwd", "yb654321"), new BasicNameValuePair("checkwd", ""), new BasicNameValuePair("logintype", "1"), new BasicNameValuePair("login.x", "0"),
				new BasicNameValuePair("login.y", "0")

		};
		String content = "";
		try {
			content = PublisherUtils.readInputStream(client.doPost(loginPageUrl, data, ""), sinaCharset);
		} catch (IOException e) {
			logger.error("登录sina blog 失败" + e.getMessage());
		} catch (HttpException e) {
			logger.error("登录sina blog 失败" + e.getMessage());
		} catch (InterruptedException e) {
			logger.error("登录sina blog 失败" + e.getMessage());
		} catch (URISyntaxException e) {
			logger.error("登录sina blog 失败" + e.getMessage());
		}
		logger.info("sina login ::: :" + content);
	}

	@SuppressWarnings("unchecked")
	private String publishBlog(WebClient client,Map dataMap) throws IOException, URISyntaxException, InterruptedException, HttpException {
		String postPageUrl = "http://control.blog.sina.com.cn/admin/article/article_post.php";
		String pubBlogPage = "http://control.blog.sina.com.cn/admin/article/article_add.php";
		String publishHtml = PublisherUtils.readInputStream(client.doGet(pubBlogPage, ""), sinaCharset);
		NameValuePair[] data = HtmlParseUtils.getElementsPostData(publishHtml, sinaCharset);
		data = initSinaBlogData(data);
		String content = "";
		try {
			content = PublisherUtils.readInputStream(client.doPost(postPageUrl, data, ""), sinaCharset);
		} catch (IOException e) {
			logger.error("sina blog 发布失败" + e.getMessage());
		} catch (HttpException e) {
			logger.error("sina blog 发布失败" + e.getMessage());
		} catch (InterruptedException e) {
			logger.error("sina blog 发布失败" + e.getMessage());
		} catch (URISyntaxException e) {
			logger.error("sina blog 发布失败" + e.getMessage());
		}
		return content;
	}

	private NameValuePair[] initSinaBlogData(NameValuePair[] data) {
		NameValuePair[] retData = data;

		for (int i = 0; i < retData.length; i++) {

		}
		return retData;
	}

	public static void main(String[] args) {
		WebClient client = new WebClient();
		getInstance().loginSina(client);
		try {
			getInstance().publishBlog(client,null);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (HttpException e) {
			e.printStackTrace();
		}
	}
}
/**
 * 
 * 新浪博客的返回值列表 星期五, 03/27/2009 - 10:16 — 月白风清 "A00001":"操作失败，可能系统繁忙或系统遇到未知错误，请稍后再试。也可联系新浪客服：致电95105670。" "A00002":"此帐号暂时被封，如有疑问也可联系新浪客服：致电95105670。" "A00003":"无权限进行此操作，请重新登录。" "A00004":"无权限进行此操作，请重新登录。" "A00005":"操作失败，可能系统繁忙或系统遇到未知错误，请稍后再试。也可联系新浪客服：致电95105670。" "A00006":"成功了"
 * "A00007":"无权限进行此操作，请重新登录。" "B00007":"跳转到自动开通页" "B00008":"自动开通用户(跳转到假页)" "B00009":"跳转到自动开通页" "B00010":"错误页(未开通页)" "B00011":"抱歉，Blog ID 错误，操作无法完成。" "B00012":"抱歉，可能因为重复操作而无法执行，请刷新本页再试。" "B00013":"用户ID被封杀""B00014":"很抱歉，您输入的验证码不正确。" "B04101":"请输入您的昵称！" "B04102":"请正确输入您的邮件地址！"
 * "B04103":"请选择您举报信息的位置！" "B04104":"请选择您举报的不良信息的类型！" "B04105":"简单描述最多只能输入500个字符！" "B04106":"再次感谢您帮助我们进行不良信息的验证工作，我们将认真仔细地处理您提交的内容。" "B01001":"标题必须是25个中文或50个字符以内，请重新输入。" "B02001":"请输入内容。" "B02002":"内容必须是20000个中文或40000个字符以内，请重新编辑。" "B02003":"是否要清除格式？粘帖的内容中含有冗余的格式，会影响在博客中的排版。"
 * "B02004":"上次撰写的博文未进行保存，要恢复内容继续编辑吗？" "B03001":"标签总字数必须是30个中文或60个字符以内，请重新输入。" "B03002":"标签个数必须是36个以内，请重新输入。" "B03003":"每个标签字数必须是7个中文或14个字符以内，请重新输入。" "B03105":"无法提取到有效标签，请手动输入些标签：如“体育、生活”。" "B03106":"博文内容必须大于20个中文或40个字符，才能提取有效标签。" "B03107":"博文内容必须大于20个中文或40个字符，才能提取有效标签。"
 * "B04001":"发布时间不能超出当前时间，请重新输入。" "B04002":"抱歉,请选择“我记录”活动投稿类别。" "B04022":"标签总字数必须是30个中文或60个字符以内，请重新输入。" "B06001":"博文已发布成功。" "B06002":"博文已发布，需要审核后才能显示，请稍候…" "B06003":"博文已成功保存到草稿箱。" "B07001":"博文发布失败，请梢后再试。建议先将内容拷贝到本地硬盘备份。" "B07003":"博文保存失败，请梢后再试。建议先将内容拷贝到本地硬盘备份。"
 * "B07004":"博文保存失败，请梢后再试。建议先将内容拷贝到本地硬盘备份。" "B08001":"不能一分钟内连续发博文，请梢后再试。" "B08002":"发布中，请稍候…" "B08003":"保存中，请稍候…"
 */
