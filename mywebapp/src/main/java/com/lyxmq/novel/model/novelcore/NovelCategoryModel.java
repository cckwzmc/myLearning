package com.lyxmq.novel.model.novelcore;

import com.lyxmq.novel.model.BaseModel;

/**
 * 
 * 网站频道、菜单管理
 * @author Administrator
 *
 */
public class NovelCategoryModel extends BaseModel {
	private static final long serialVersionUID = 6764609517138235910L;
	private long id;
	private long superId;
	private String name;
	private boolean isChannel;
	private boolean isHidden;
	private String urlName;
	private String domainUrlName;
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

	public String getDomainUrlName() {
		return domainUrlName;
	}

	public void setDomainUrlName(String domainUrlName) {
		this.domainUrlName = domainUrlName;
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
