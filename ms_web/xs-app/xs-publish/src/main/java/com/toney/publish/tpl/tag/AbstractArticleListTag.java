package com.toney.publish.tpl.tag;

import org.springframework.beans.factory.annotation.Autowired;

import com.toney.core.biz.ArticleManager;
import com.toney.core.biz.ArticleTinyManager;
import com.toney.core.biz.ChannelManager;

import freemarker.template.TemplateDirectiveModel;

public abstract class AbstractArticleListTag implements TemplateDirectiveModel {

	@Autowired
	protected ChannelManager channelManager;
	@Autowired
	protected ArticleTinyManager articleTinyManager;
	@Autowired
	protected ArticleManager articleManager;

	// "排序/click/recommend"
	private String orderby;
	// "行数"
	private Integer row = 6;
	// "文字长度"
	private String titlelen;
	// "文章标签"可以混合使用.
	private String arctag;
	private Integer imgwidth = 0;
	private Integer imgheight = 0;
	// 1,2,3
	private String channelIds;

	public String getChannelIds() {
		return channelIds;
	}

	public void setChannelIds(String channelIds) {
		this.channelIds = channelIds;
	}

	public String getOrderby() {
		return orderby;
	}

	public void setOrderby(String orderby) {
		this.orderby = orderby;
	}

	public Integer getRow() {
		return row;
	}

	public void setRow(Integer row) {
		this.row = row;
	}

	public String getTitlelen() {
		return titlelen;
	}

	public void setTitlelen(String titlelen) {
		this.titlelen = titlelen;
	}

	public String getArctag() {
		return arctag;
	}

	public void setArctag(String arctag) {
		this.arctag = arctag;
	}

	public Integer getImgwidth() {
		return imgwidth;
	}

	public void setImgwidth(Integer imgwidth) {
		this.imgwidth = imgwidth;
	}

	public Integer getImgheight() {
		return imgheight;
	}

	public void setImgheight(Integer imgheight) {
		this.imgheight = imgheight;
	}

}
