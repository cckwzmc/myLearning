package com.toney.core.model;

/**
 * @author toney.li
 * 系统配置参数。
 */
public class SysConfigModel extends BaseObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1634318614847668772L;
	private Integer id;
	private String cfgName;
	private String info;
	private Integer groupId;
	private String objType;
	private String cfgValue;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCfgName() {
		return cfgName;
	}

	public void setCfgName(String cfgName) {
		this.cfgName = cfgName;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public Integer getGroupId() {
		return groupId;
	}

	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}

	public String getObjType() {
		return objType;
	}

	public void setObjType(String objType) {
		this.objType = objType;
	}

	public String getCfgValue() {
		return cfgValue;
	}

	public void setCfgValue(String cfgValue) {
		this.cfgValue = cfgValue;
	}

	@Override
	public String toString() {
		return "SysConfigModel [id=" + id + ", cfgName=" + cfgName + ", info=" + info + ", groupId=" + groupId + ", objType=" + objType
				+ ", cfgValue=" + cfgValue + "]";
	}

}
