package com.lyxmq.novel.model.cms.core;

import com.lyxmq.novel.model.BaseModel;

/**
 * Cms 文字为主的内容管理，文章内容。
 * @author Administrator
 *
 */
public class CmsArticleContent extends BaseModel {

	private static final long serialVersionUID = 2986758375828015035L;
	private long id;
	private String body;
	

	//文章内容类型，如；0:自己编辑的内容、1:直接跳转内容,
	private int typeId;
	private String redirectUrl;
	private int templateId;
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public int getTypeId() {
		return typeId;
	}

	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}

	public String getRedirectUrl() {
		return redirectUrl;
	}

	public void setRedirectUrl(String redirectUrl) {
		this.redirectUrl = redirectUrl;
	}

	public int getTemplateId() {
		return templateId;
	}

	public void setTemplateId(int templateId) {
		this.templateId = templateId;
	}
	@Override
	public boolean equals(Object o) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}

}
