package com.toney.istyle.bo;

import java.util.Date;

import com.toney.istyle.commons.BaseObject;

/**
 *************************************************************** 
 * <p>
 * 
 * @CLASS :AppConfigBO.java
 * @DESCRIPTION : 系统配置业务对象
 * @AUTHOR :toney.li
 * @VERSION :v1.0
 * @DATE :Mar 19, 2013
 *       </p>
 **************************************************************** 
 */
public class AppConfigBO extends BaseObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5497855577806620213L;
	private Long id;
	private String groupCode;
	// '是否可用,0:不可用 1：可用',
	private Integer enabled;
	private String cfgKey;
	private String cfgValues;
	private Date createDate;
	private String cfgDescription;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getGroupCode() {
		return groupCode;
	}

	public void setGroupCode(String groupCode) {
		this.groupCode = groupCode;
	}

	public Integer getEnabled() {
		return enabled;
	}

	public void setEnabled(Integer enabled) {
		this.enabled = enabled;
	}

	public String getCfgKey() {
		return cfgKey;
	}

	public void setCfgKey(String cfgKey) {
		this.cfgKey = cfgKey;
	}

	public String getCfgValues() {
		return cfgValues;
	}

	public void setCfgValues(String cfgValues) {
		this.cfgValues = cfgValues;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getCfgDescription() {
		return cfgDescription;
	}

	public void setCfgDescription(String cfgDescription) {
		this.cfgDescription = cfgDescription;
	}

	@Override
	public String toString() {
		return "AppConfigBO [id=" + id + ", groupCode=" + groupCode + ", enabled=" + enabled + ", cfgKey=" + cfgKey + ", cfgValues=" + cfgValues + ", createDate=" + createDate
				+ ", cfgDescription=" + cfgDescription + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cfgDescription == null) ? 0 : cfgDescription.hashCode());
		result = prime * result + ((cfgKey == null) ? 0 : cfgKey.hashCode());
		result = prime * result + ((cfgValues == null) ? 0 : cfgValues.hashCode());
		result = prime * result + ((createDate == null) ? 0 : createDate.hashCode());
		result = prime * result + ((enabled == null) ? 0 : enabled.hashCode());
		result = prime * result + ((groupCode == null) ? 0 : groupCode.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		AppConfigBO other = (AppConfigBO) obj;
		if (cfgDescription == null) {
			if (other.cfgDescription != null)
				return false;
		} else if (!cfgDescription.equals(other.cfgDescription))
			return false;
		if (cfgKey == null) {
			if (other.cfgKey != null)
				return false;
		} else if (!cfgKey.equals(other.cfgKey))
			return false;
		if (cfgValues == null) {
			if (other.cfgValues != null)
				return false;
		} else if (!cfgValues.equals(other.cfgValues))
			return false;
		if (createDate == null) {
			if (other.createDate != null)
				return false;
		} else if (!createDate.equals(other.createDate))
			return false;
		if (enabled == null) {
			if (other.enabled != null)
				return false;
		} else if (!enabled.equals(other.enabled))
			return false;
		if (groupCode == null) {
			if (other.groupCode != null)
				return false;
		} else if (!groupCode.equals(other.groupCode))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
