package com.lyxmq.novel.model.novel.system;

import com.lyxmq.novel.model.BaseModel;

/**
 * 
 * @author Administrator
 *
 */
public class CmsSystemConstants extends BaseModel{
	private static final long serialVersionUID = 7793150616988854737L;
	private long id;
	private String key;
	private String value;
	private String desc;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
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
