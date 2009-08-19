package com.lyxmq.blog.publisher.sohu;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Map;
import java.util.Properties;

import org.apache.http.HttpException;
import org.apache.http.NameValuePair;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.apache.http.message.BasicNameValuePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import com.lyxmq.blog.publisher.dao.CommonsServiceDao;
import com.lyxmq.blog.publisher.sohu.SohuBlogDao;
import com.lyxmq.blog.publisher.utils.HtmlParseUtils;
import com.lyxmq.blog.publisher.utils.PublisherUtils;
import com.lyxmq.blog.publisher.utils.WebClient;

public class SohuBlog {
	private static Logger logger = LoggerFactory.getLogger(SohuBlog.class);
	private CommonsServiceDao commonsServiceDao = null;
	private SohuBlogDao sohuBlogDao = null;
	private static final String sohuCharset = "UTF8";
	private static SohuBlog sohu = null;
	private static WebClient webClient = null;
	private Properties sohuPro=null;
	public Properties getSohuPro() {
		if(sohuPro==null){
			Properties pro=null;
			try {
				pro = PropertiesLoaderUtils.loadAllProperties("blogsiteconfig/sohu_publisher.properties");
			} catch (IOException e) {
				logger.error(e.getMessage());
			}
			return pro;
		}else {
			return sohuPro;
		}
	}
	
	public WebClient getWebClient() {
		if(webClient==null){
			return new WebClient();
		}
		return webClient;
	}


	public static SohuBlog getInstance() {
		if (sohu == null) {
			return new SohuBlog();
		} else {
			return sohu;
		}
	}

	public CommonsServiceDao getCommonsServiceDao() {
		return commonsServiceDao;
	}

	public void setCommonsServiceDao(CommonsServiceDao commonsServiceDao) {
		this.commonsServiceDao = commonsServiceDao;
	}

	public SohuBlogDao getSinaBlogDao() {
		return sohuBlogDao;
	}

	public void setSinaBlogDao(SohuBlogDao sinaBlogDao) {
		this.sohuBlogDao = sinaBlogDao;
	}

	public SohuBlog() {
	}

	private void logoutSohu(WebClient webClient) {
		String loginoutPageUrl = this.getSohuPro().getProperty("login_page_url");
		try {
			webClient.doGet(loginoutPageUrl, "");
		} catch (URISyntaxException e) {
			logger.error("退出sina blog 失败" + e.getMessage());
		} catch (InterruptedException e) {
			logger.error("退出sina blog 失败" + e.getMessage());
		} catch (HttpException e) {
			logger.error("退出sina blog 失败" + e.getMessage());
		} catch (IOException e) {
			logger.error("退出sina blog 失败" + e.getMessage());
		}
	}


	private void loginSina(WebClient client,String username,String password) {
		BasicClientCookie cookie = new BasicClientCookie("", "");
		client.getHttpClient().getCookieStore().addCookie(cookie);
		String loginPageUrl = this.getSohuPro().getProperty("login_page_url");
		NameValuePair data[] = { new BasicNameValuePair("email", username), new BasicNameValuePair("password", password), new BasicNameValuePair("checkwd", ""), new BasicNameValuePair("logintype", "1"), new BasicNameValuePair("login.x", "0"),
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
		String postPageUrl = this.getSohuPro().getProperty("article_blog_action");
		String pubBlogPage = this.getSohuPro().getProperty("article_blog_post");
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



}
//'blog.name' : '博客名称',
//4 'blog.domain' : '个性域名',
//5 'blog.desc' : '博客描述',
//6 'blog.detailDesc' : '主人档案',
//7 'comment.author.name' : '称呼',
//8 'comment.author.site' : '网站链接',
//9 'comment.author.email' : '邮箱',
//10 'comment.content' : '评论内容',
//11 'comment.vcode' : '验证码',
//12 'message.author.name' : '称呼',
//13 'message.author.site' : '网站链接',
//14 'message.author.email' : '邮箱',
//15 'message.content' : '评论内容',
//16 'message.vcode' : '验证码',
//17 'entry.title' : '日志标题',
//18 'entry.content' : '日志内容',
//19 'entry.excerpt' : '日志摘要',
//20 'entry.keywords' : '关键字',
//21 'category.name' : '分类名称',
//22 'category.desc' : '分类描述',
//23 'mobile.code' : '手机号',
//24 'mobile.vcode' : '验证码',
//25 'module.title' : '模块标题',
//26 'module.data' : '模块参数',
//27 'module.type' : '模块类型',
//28 'link.title' : '友情连接名称',
//29 'link.desc' : '友情连接描述',
//30 'link.url' : '友情连接地址',
//31 'errors.required' : '{0}不能为空.',
//32 'errors.minlength' : '{0}不能少于 {1} 个字符',
//33 'errors.maxlength' : '{0}不能大于 {1} 个字符',
//34 'errors.range' : '{0}必须在{1}个字符和{2}个字符之间',
//35 'errors.email' : '{0}不是一个有效的邮件地址',
//36 'errors.invalid' : '{0}无效.',
//37 'errors.comment.authorname' : ' 用户名不能为空.',
//38 'errors.comment.commentcontent' : '评论内容不能为空.',
//39 'errors.comment.maxcommentlength' : '评论内容过多，最多为1000字.',
//40 'errors.comment.maxauthorName' : '称呼过长',
//41 'errors.comment.maxauthorSite' : '网站链接过长',
//42 'errors.comment.maxcommentContent' : '评论内容过长',
//43 'errors.comment.publish.forbidden' : '作者不允许对此文进行评论',
//44 'errors.comment.publish.needlogin' : '作者只允许登录用户才可对此文评论。<a href="http://blog.sohu.com/login/logon.do">登录</a>',
//45 'errors.message.authorname' : ' 用户名不能为空.',
//46 'errors.message.commentcontent' : '留言内容不能为空.',
//47 'errors.message.maxcommentlength' : '留言内容过多，最多为1000字.',
//48 'errors.message.maxauthorName' : '称呼过长',
//49 'errors.message.maxauthorSite' : '网站链接过长',
//50 'errors.message.maxcommentContent' : '留言内容过长',
//51 'errors.message.publish.forbidden' : '作者不允许对留言',
//52 'errors.message.publish.needlogin' : '作者只允许登录用户才可留言。<a href="http://blog.sohu.com/login/logon.do">登录</a>',
//53 'errors.message.vcode' : ' 验证码不能为空.',
//54 'errors.entry.entrytitle' : '请填写日志标题.',
//55 'errors.entry.maxentrytitle' : '日志标题过长.',
//56 'errors.entry.entrycontent' : '请填写日志内容.',
//57 'errors.entry.maxentryexcerpt' : '摘要长度过长',
//58 'errors.entry.maxentrykeywords' : '关键字过长',
//59 'errors.link.unexist' : '友情连接不存在',
//60 'errors.link.reach.limit' : '您的好友数量已经达到系统上限',
//61 'errors.module.reach.limit' : '您的模块已经达到系统上限',
//62 'errors.module.attempt.delete.entry' : '不能删除日志模块',
//63 'errors.general' : '操作失败',
//64 'errors.operate.failure' : '数据操作失败',
//65 'errors.rpc' : '操作失败，该页暂时不可用',
//66 'errors.npe' : '系统内部错误',
//67 'errors.forbidden' : '操作失败，请检查是否有相应的权限',
//68 'errors.param' : '参数错误',
//69 'errors.vcode' : '验证码错误，请检查您的输入是否与图片中显示的字母一致',
//70 'errors.domain.format' : '个性域名必须是英文和数字',
//71 'errors.domain.exist' : '个性域名已被占用',
//72 'errors.number.formate' : '参数格式错误',
//73 'errors.blog.unexis' : '博客不存在',
//74 'errors.login.user.unexist' : '用户不存在',
//75 'errors.login.auth' : '认证错误。<ul style="margin:0px;padding:0px;"><li>请检查用户名和密码是否正确。</li>& lt;li>请检查您的域名是否正确。提示：如果是17173用户，请注意选择“17173用户”项。',
//76 'errors.passport.invalid' : '非法的用户名',
//77 'errors.login' : '系统内部错误，请稍后重试',
//78 'errors.activation' : '激活失败，请<a href="/login/activation.do">重试</a>',
//79 'errors.auto.activation' : '自动激活失败，请<a href="/passport">重试</a>',
//80 'errors.blog.unexist' : '博客不存在',
//81 'errors.you.have.not.blog' : '您还没有自己的博客,<a href="/login/reg.do">现在就去申请</a>',
//82 'errors.entry.unexist' : '该日志不存在',
//83 'errors.entry.private' : '该日志已被隐藏',
//84 'errors.archive.unexist' : '该归档不存在',
//85 'errors.category.unexist' : '该分类不存在',
//86 'errors.mobile.invalidvcode' : '您输入的验证码不正确！',
//87 'errors.mobile.mobilecode' : '手机号不能为空',
//88 'errors.mobile.binding' : '该手机号{0}已被绑定',
//89 'errors.keyword.rewrite' : '您发表的内容包含敏感关键字！请重新填写。',
//90 'errors.entry.contribute.duplicate' : '对不起,您已经投过稿了,换一篇试试吧',
//91 'errors.not.ppp' : '您还没有升级到玩弄版,无法进行下一步操作, 现在就<a href="http://blog.sohu.com/manage/upgrade.do">升级</a>?',
//92 'messages.general' : '操作成功',
//93 'messages.setting.saved' : '修改成功',
//94 'messages.theme.saved' : '主题修改成功，您可以<a href="{0}" target="_blank">点击这里</a>查看修改后的结果',
//95 'messages.flash.saved' : 'Flash特效修改成功，您可以<a href="{0}" target="_blank">点击这里</a>查看修改后的结果',
//96 'messages.layout.saved' : '页面布局修改成功，您可以<a href="{0}" target="_blank">点击这里</a>查看修改后的结果',
//97 'messages.entry.saved' : '<div class="noticeInfo"><h3>日志保存成功</h3><div id="autoDir"></div></div><div class="moreInfo"><p>或者您可以：</p><ul><li id="defautAction"><a href="/manage/entry.do?m=edit&id={0}">继续编辑</a></li>& lt;li><a href="/manage/entry.do?m=list&t=draft">转到草稿列表页面</a></li& gt;<li><a href="/manage/entry.do?m=add">撰写另一篇新日志</a></li></ul& gt;</div>',
//98 'messages.entry.published' : '<div class="noticeInfo"><h3>日志发布成功</h3><div id="autoDir"></div></div><div class="moreInfo"><p>我们推荐您：<ul><li><a href="http://q.sohu.com" target="_blank" style="font-size:14px;font-weight:bold;color:red">逛逛搜狐圈子</a>& amp;nbsp;<img src="http://js1.pp.sohu.com.cn/ppp/blog/themes_ppp/def/images/ico_new.gif" alt="新功能！" title="新功能！" /></li></ul></p><div class="clear"></div><p>或者您可以：</p><ul><li id="defautAction"><a href="/manage/entry.do">转到日志列表页面</a></li><li><a href="{0}">查看您刚发表的日志</a></li><li><a href="/manage/entry.do?m=add">撰写另一篇新日志</a></li></ul& gt;</div>',
//99 'messages.entry.shortcutpublished' : '<div class="noticeInfo"><h3>日志发布成功</h3><div id="autoDir"></div></div><div class="moreInfo"><p>我们推荐您：<ul><li><a href="http://q.sohu.com" target="_blank" style="font-size:14px;font-weight:bold;color:red">逛逛搜狐圈子</a>& amp;nbsp;<img src="http://js1.pp.sohu.com.cn/ppp/blog/themes_ppp/def/images/ico_new.gif" alt="新功能！" title="新功能！" /></li></ul></p><div class="clear"></div><p>或者您可以：</p><ul><li id="defautAction"><a href="javascript:closeWin();">关闭本页</a></li><li>< a href="{1}">查看我的博客首页</a></li><li><a href="{0}">查看您刚发表的日志</a></li><li><a href="/manage/entry.do?m=add&t=shortcut">撰写另一篇新日志</a>< /li></ul></div>',
//100 'messages.entry.shortcutsaved' : '<div class="noticeInfo"><h3>日志保存成功</h3><div id="autoDir"></div></div><div class="moreInfo"><p>或者您可以：</p><ul><li id="defautAction"><a href="/manage/entry.do?m=edit&id={0}&t=shortcut">继续编辑</a& gt;</li><li><a href="/manage/entry.do?m=list&t=draft">转到草稿列表页面</a></li& gt;<li><a href="/manage/entry.do?m=add&t=shortcut">撰写另一篇新日志</a>< /li></ul></div>',
//101 'messages.entry.updated' : '<div class="noticeInfo"><h3>日志修改成功</h3><div id="autoDir"></div></div><div class="moreInfo"><p>或者您可以：</p><ul><li id="defautAction"><a href="{0}">查看您刚修改的日志</a></li><li><a href="/manage/entry.do">转到日志列表页面</a></li></ul>< /div>',
//102 'messages.entry.shortcutupdated' : '<div class="noticeInfo"><h3>日志修改成功</h3><div id="autoDir"></div></div><div class="moreInfo"><p>或者您可以：</p><ul><li id="defautAction"><a href="javascript:closeWin();">关闭本页</a></li><li>< a href="{0}">查看您刚修改的日志</a></li><li><a href="{1}">查看我的博客</a></li></ul></div>',
//103 'messages.blog.upgraded' : '<div class="noticeInfo"><h3>恭喜您，升级成功！</h3><div id="autoDir"></div></div><div class="moreInfo"><p>或者您可以：</p><ul><li id="defautAction"><a href="{0}">查看我的博客</a></li><li><a href="javascript:closeWin();">关闭本页</a></li></ul>& lt;/div>',
//104 'messages.video.upgraded' : '<div class="noticeInfo"><h3>恭喜您，设置成功！</h3><div id="autoDir"></div></div><div class="moreInfo"><p>或者您可以：</p><ul><li id="defautAction"><a href="{0}">查看我的博客</a></li><li><a href="javascript:closeWin();">关闭本页</a></li></ul>& lt;/div>',
//105 'messages.blog.down' : '<div class="noticeInfo"><h3>降级成功，感谢您的试用</h3><div id="autoDir"></div></div><div class="moreInfo"><p>或者您可以：</p><ul><li id="defautAction"><a href="{0}">查看我的博客</a></li><li><a href="javascript:closeWin();">关闭本页</a></li></ul>& lt;/div>',
//106 'messages.entry.private' : '该日志已被隐藏,只有您自己才能看到',
//107 'messages.entry.deleted' : '日志删除成功',
//108 'messages.comment.published' : '评论发表成功',
//109 'messages.comment.deleted' : '评论删除成功',
//110 'messages.message.published' : '留言发表成功',
//111 'messages.message.deleted' : '留言删除成功',
//112 'messages.category.saved' : '<div class="noticeInfo"><h3>分类"{0}"保存成功</h3><div id="autoDir"></div></div><div class="moreInfo"><p>或者您可以：</p><ul><li id="defautAction"><a href="/manage/category.do">返回列表</a></li><li><a href="manage/category.do?m=add">创建新分类</a></li></ul& gt;</div>',
//113 'messages.category.updated' : '<div class="noticeInfo"><h3>分类"{0}"更新成功</h3><div id="autoDir"></div></div><div class="moreInfo"><p>或者您可以：</p><ul><li id="defautAction"><a href="/manage/category.do">返回列表</a></li><li><a href="manage/category.do?m=add">创建新分类</a></li></ul& gt;</div>',
//114 'messages.category.deleted' : '分类"{0}"删除成功',
//115 'messages.category.added' : '<div class="noticeInfo"><h3>分类"{0}"添加成功</h3><div id="autoDir"></div></div><div class="moreInfo"><p>或者您可以：</p><ul><li id="defautAction"><a href="/manage/category.do">返回列表</a></li><li><a href="manage/category.do?m=add">创建新分类</a></li></ul& gt;</div>',
//116 'messages.profile.saved' : '档案修改成功',
//117 'messages.entry.contributed' : '投稿成功。\n如果该日志被录用，将会被展示在相应频道栏目中。同时管理员会以留言方式通知您。',
//118 'messages.mobile.setsuccess' : '设置成功！您可以通过彩信发表博客日记了<br /><a href="/manage/mobile.do">返回</a>',
//119 'messages.mobile.cancelsuccess' : '您的手机博客服务已经取消!<br /><a href="/manage/mobile.do">返回</a>' 