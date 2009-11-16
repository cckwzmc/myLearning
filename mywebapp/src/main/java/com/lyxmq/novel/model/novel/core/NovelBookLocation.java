package com.lyxmq.novel.model.novel.core;

import com.lyxmq.novel.model.BaseModel;

/**
 * 小说在首页的位置（包括频道首页）
 * 
 * @author Administrator
 * 
 */
public class NovelBookLocation extends BaseModel {
	private static final long serialVersionUID = 3455915664695658265L;

	private int id;
	private String flag;
	private String desc;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
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
		return null;
	}
}
