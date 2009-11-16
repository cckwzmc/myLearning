package com.lyxmq.novel.model.novel.system;

import com.lyxmq.novel.model.BaseModel;

/**
 * 系统变量
 * 
 * @author Administrator
 * 
 */
public class CmsSystemVarConfig extends BaseModel {

	private static final long serialVersionUID = -1186823116719702800L;
	private long id;
	private String varname;
	private String desc;
	// 分组，变量的类型，入系统变量，模版变量，自定义变量等
	private int groupid;
	// 变量类型，如：boolean，String
	private String type;
	private String value;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getVarname() {
		return varname;
	}

	public void setVarname(String varname) {
		this.varname = varname;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public int getGroupid() {
		return groupid;
	}

	public void setGroupid(int groupid) {
		this.groupid = groupid;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
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
