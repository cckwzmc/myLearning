package com.lyxmq.novel.model.cms.core;

import com.lyxmq.novel.model.BaseModel;

/**
 * CMS频道、菜单目录管理
 * @author Administrator
 *
 */
public class CmsCategory extends BaseModel{

	private static final long serialVersionUID = -7681536207091965134L;
	private int id;
	private int superId;
	private String name;
	private String desc;
	private boolean isChannel;
	private long sortrank;
	private String typeDir;
	private boolean isDefault;
	private boolean isHidden;
	private String keywords;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getSuperId() {
		return superId;
	}

	public void setSuperId(int superId) {
		this.superId = superId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public boolean isChannel() {
		return isChannel;
	}

	public void setChannel(boolean isChannel) {
		this.isChannel = isChannel;
	}

	public long getSortrank() {
		return sortrank;
	}

	public void setSortrank(long sortrank) {
		this.sortrank = sortrank;
	}

	public String getTypeDir() {
		return typeDir;
	}

	public void setTypeDir(String typeDir) {
		this.typeDir = typeDir;
	}

	public boolean isDefault() {
		return isDefault;
	}

	public void setDefault(boolean isDefault) {
		this.isDefault = isDefault;
	}

	public boolean isHidden() {
		return isHidden;
	}

	public void setHidden(boolean isHidden) {
		this.isHidden = isHidden;
	}

	public String getKeywords() {
		return keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	@Override
	public boolean equals(Object o) {
		return false;
	}

	@Override
	public int hashCode() {
		return 0;
	}

	@Override
	public String toString() {
		return null;
	}

}
