package com.toney.publish.factory;

import java.io.Serializable;

/**
 * 出版上下文.
 * @author toney.li
 * 
 */
public class PublishContext implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 上一级频道ID,没有ID该值为-1;
	 */
	private Integer parentChannelId;
	/**
	 * 各首页，频道页都有ID,没有ID该值为-1;<br/>
	 * 顶级频道该值为0。
	 */
	private Integer channelId;
	/**
	 * 每个前端发起的出版请求必须带一个编码，这个编码与数据库中的必须保持一致。<br/>
	 * 格式如下:_index,_list,_channel,_topic,_detail等.
	 */
	private String pageCode;
	/**
	 * 当前需出版页面对应的pageID。
	 */
	private Long pageId;
	/**
	 * 对应的模版ID
	 */
	private Long templateId;
	/**
	 * 对应的模版路径。
	 */
	private String templatePath;
	/**
	 * 出版路径
	 */
	private String publishPath;
	
	/**
	 * 是否预览
	 */
	private String isPreview;
	
	
	public String getIsPreview() {
		return isPreview;
	}
	public void setIsPreview(String isPreview) {
		this.isPreview = isPreview;
	}
	public Integer getParentChannelId() {
		return parentChannelId;
	}
	public void setParentChannelId(Integer parentChannelId) {
		this.parentChannelId = parentChannelId;
	}
	public Integer getChannelId() {
		return channelId;
	}
	public void setChannelId(Integer channelId) {
		this.channelId = channelId;
	}
	public String getPageCode() {
		return pageCode;
	}
	public void setPageCode(String pageCode) {
		this.pageCode = pageCode;
	}
	public Long getPageId() {
		return pageId;
	}
	public void setPageId(Long pageId) {
		this.pageId = pageId;
	}
	public Long getTemplateId() {
		return templateId;
	}
	public void setTemplateId(Long templateId) {
		this.templateId = templateId;
	}
	public String getTemplatePath() {
		return templatePath;
	}
	public void setTemplatePath(String templatePath) {
		this.templatePath = templatePath;
	}
	public String getPublishPath() {
		return publishPath;
	}
	public void setPublishPath(String publishPath) {
		this.publishPath = publishPath;
	}

}
