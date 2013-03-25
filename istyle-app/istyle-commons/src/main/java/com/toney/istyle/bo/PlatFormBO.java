package com.toney.istyle.bo;

import java.io.Serializable;
import java.util.Date;

import com.toney.istyle.commons.BaseObject;

public class PlatFormBO extends BaseObject {
	private Integer id;

	private String platformCode;

	private String platformName;

	private Date createDate;

	private Short enabled;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPlatformCode() {
		return platformCode;
	}

	public void setPlatformCode(String platformCode) {
		this.platformCode = platformCode;
	}

	public String getPlatformName() {
		return platformName;
	}

	public void setPlatformName(String platformName) {
		this.platformName = platformName;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getOfficeWebsite() {
		return officeWebsite;
	}

	public void setOfficeWebsite(String officeWebsite) {
		this.officeWebsite = officeWebsite;
	}

	public Short getEnabled() {
		return enabled;
	}

	public void setEnabled(Short enabled) {
		this.enabled = enabled;
	}

	@Override
	public String toString() {
		return "PaltFormBO [id=" + id + ", platformCode=" + platformCode + ", platformName=" + platformName + ", createDate=" + createDate + ", officeWebsite=" + officeWebsite
				+ ", enabled=" + enabled + "]";
	}

	private String officeWebsite;

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((createDate == null) ? 0 : createDate.hashCode());
		result = prime * result + ((enabled == null) ? 0 : enabled.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((officeWebsite == null) ? 0 : officeWebsite.hashCode());
		result = prime * result + ((platformCode == null) ? 0 : platformCode.hashCode());
		result = prime * result + ((platformName == null) ? 0 : platformName.hashCode());
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
		PlatFormBO other = (PlatFormBO) obj;
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
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (officeWebsite == null) {
			if (other.officeWebsite != null)
				return false;
		} else if (!officeWebsite.equals(other.officeWebsite))
			return false;
		if (platformCode == null) {
			if (other.platformCode != null)
				return false;
		} else if (!platformCode.equals(other.platformCode))
			return false;
		if (platformName == null) {
			if (other.platformName != null)
				return false;
		} else if (!platformName.equals(other.platformName))
			return false;
		return true;
	}

}
