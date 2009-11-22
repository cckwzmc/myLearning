package com.lyxmq.novel.model.cms.core;

import com.lyxmq.novel.model.BaseModel;

/**
 * CMS内容管理的快速检索。
 * 
 * @author Administrator
 */
public class CmsTinyArticle extends BaseModel {

	private static final long serialVersionUID = 8884505317848907564L;
	private long id;
	private int typeId;
	private int superTypeId;
	private String title;
	private long sortrank;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getTypeId() {
		return typeId;
	}

	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}

	public int getSuperTypeId() {
		return superTypeId;
	}

	public void setSuperTypeId(int superTypeId) {
		this.superTypeId = superTypeId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public long getSortrank() {
		return sortrank;
	}

	public void setSortrank(long sortrank) {
		this.sortrank = sortrank;
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
