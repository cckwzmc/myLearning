package com.lyxmq.novel.model.novel.core;

import com.lyxmq.novel.model.BaseModel;

/**
 * 
 * 网站频道、菜单管理
 * 
 * @author Administrator
 * 
 */
public class NovelCategoryModel extends BaseModel {
	private static final long serialVersionUID = 6764609517138235910L;
	private long id;
	// 上级ID
	private long superId;
	// 名称
	private String name;
	// 是否是频道
	private boolean isChannel;
	// 是否有自己的首页
	private boolean isNeedSelfIndex;
	// 是否隐藏
	private boolean isHidden;
	// 相对首页的URL,用目录表示，如果domainUrl有记录，则是相当domainUrl的URL
	private String urlName;
	// 是频道，有首页，才有二级的单独域名。
	private String domainUrl;
	private String desc;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getSuperId() {
		return superId;
	}

	public void setSuperId(long superId) {
		this.superId = superId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isChannel() {
		return isChannel;
	}

	public void setChannel(boolean isChannel) {
		this.isChannel = isChannel;
	}

	public boolean isHidden() {
		return isHidden;
	}

	public void setHidden(boolean isHidden) {
		this.isHidden = isHidden;
	}

	public String getUrlName() {
		return urlName;
	}

	public void setUrlName(String urlName) {
		this.urlName = urlName;
	}

	public boolean isNeedSelfIndex() {
		return isNeedSelfIndex;
	}

	public void setNeedSelfIndex(boolean isNeedSelfIndex) {
		this.isNeedSelfIndex = isNeedSelfIndex;
	}

	public String getDomainUrl() {
		return domainUrl;
	}

	public void setDomainUrl(String domainUrl) {
		this.domainUrl = domainUrl;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
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
