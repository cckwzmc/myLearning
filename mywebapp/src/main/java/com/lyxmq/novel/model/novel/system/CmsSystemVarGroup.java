package com.lyxmq.novel.model.novel.system;

import com.lyxmq.novel.model.BaseModel;
/**
 * 系统变量，分组类型
 * @author Administrator
 *
 */
public class CmsSystemVarGroup extends BaseModel {

	private static final long serialVersionUID = 7101461464671109812L;
	private int id;
	private String name;
	private String desc;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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
