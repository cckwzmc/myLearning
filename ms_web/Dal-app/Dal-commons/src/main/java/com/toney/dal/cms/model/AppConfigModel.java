package com.toney.dal.cms.model;

import com.toney.dal.base.BaseObject;

/**
 * @author toney.li
 * 系统配置参数。
 */
public class AppConfigModel extends BaseObject {

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
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cfgName == null) ? 0 : cfgName.hashCode());
		result = prime * result + ((cfgValue == null) ? 0 : cfgValue.hashCode());
		result = prime * result + ((groupId == null) ? 0 : groupId.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((info == null) ? 0 : info.hashCode());
		result = prime * result + ((objType == null) ? 0 : objType.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AppConfigModel other = (AppConfigModel) obj;
		if (cfgName == null) {
			if (other.cfgName != null)
				return false;
		} else if (!cfgName.equals(other.cfgName))
			return false;
		if (cfgValue == null) {
			if (other.cfgValue != null)
				return false;
		} else if (!cfgValue.equals(other.cfgValue))
			return false;
		if (groupId == null) {
			if (other.groupId != null)
				return false;
		} else if (!groupId.equals(other.groupId))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (info == null) {
			if (other.info != null)
				return false;
		} else if (!info.equals(other.info))
			return false;
		if (objType == null) {
			if (other.objType != null)
				return false;
		} else if (!objType.equals(other.objType))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "SysConfigModel [id=" + id + ", cfgName=" + cfgName + ", info=" + info + ", groupId=" + groupId + ", objType=" + objType
				+ ", cfgValue=" + cfgValue + "]";
	}

}
