package com.toney.dal.cms.model;

import com.toney.dal.base.BaseObject;

/**
 * 广告位
 * 
 * @author toney.li
 * 
 */
public class AdvertiseSpaceModel extends BaseObject {
	/**
	 * 
	 */
	private static final long serialVersionUID = 877090806277543346L;

	private Integer id;
	/**
	 * 所属频道 目前只支持挂在一级频道上.
	 */
	private Long aid;
	/**
	 * 广告位名称
	 */
	private String spaceName;
	/**
	 * 0:不可用，1：可用
	 */
	private Integer enabled;
	/**
	 * 1:HTML,2:JS
	 */
	private Integer adType;
	/**
	 * 出版路径
	 */
	private String adDir;
	/**
	 * 广告位创建者
	 */
	private Long createrId;
	private Long createDate;
	private Long lastUpdateDate;
	private String description;
	private String templateContent;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Long getAid() {
		return aid;
	}

	public void setAid(Long aid) {
		this.aid = aid;
	}

	public String getSpaceName() {
		return spaceName;
	}

	public void setSpaceName(String spaceName) {
		this.spaceName = spaceName;
	}

	public Integer getEnabled() {
		return enabled;
	}

	public void setEnabled(Integer enabled) {
		this.enabled = enabled;
	}

	public Integer getAdType() {
		return adType;
	}

	public void setAdType(Integer adType) {
		this.adType = adType;
	}

	public String getAdDir() {
		return adDir;
	}

	public void setAdDir(String adDir) {
		this.adDir = adDir;
	}

	public Long getCreaterId() {
		return createrId;
	}

	public void setCreaterId(Long createrId) {
		this.createrId = createrId;
	}

	public Long getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Long createDate) {
		this.createDate = createDate;
	}

	public Long getLastUpdateDate() {
		return lastUpdateDate;
	}

	public void setLastUpdateDate(Long lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getTemplateContent() {
		return templateContent;
	}

	public void setTemplateContent(String templateContent) {
		this.templateContent = templateContent;
	}

	@Override
	public String toString() {
		return "AdvertiseSpaceModel [id=" + id + ", aid=" + aid
				+ ", spaceName=" + spaceName + ", enabled=" + enabled
				+ ", adType=" + adType + ", adDir=" + adDir + ", createrId="
				+ createrId + ", createDate=" + createDate
				+ ", lastUpdateDate=" + lastUpdateDate + ", description="
				+ description + ", templateContent=" + templateContent + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((adDir == null) ? 0 : adDir.hashCode());
		result = prime * result + ((adType == null) ? 0 : adType.hashCode());
		result = prime * result + ((aid == null) ? 0 : aid.hashCode());
		result = prime * result
				+ ((createDate == null) ? 0 : createDate.hashCode());
		result = prime * result
				+ ((createrId == null) ? 0 : createrId.hashCode());
		result = prime * result
				+ ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((enabled == null) ? 0 : enabled.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((lastUpdateDate == null) ? 0 : lastUpdateDate.hashCode());
		result = prime * result
				+ ((spaceName == null) ? 0 : spaceName.hashCode());
		result = prime * result
				+ ((templateContent == null) ? 0 : templateContent.hashCode());
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
		AdvertiseSpaceModel other = (AdvertiseSpaceModel) obj;
		if (adDir == null) {
			if (other.adDir != null)
				return false;
		} else if (!adDir.equals(other.adDir))
			return false;
		if (adType == null) {
			if (other.adType != null)
				return false;
		} else if (!adType.equals(other.adType))
			return false;
		if (aid == null) {
			if (other.aid != null)
				return false;
		} else if (!aid.equals(other.aid))
			return false;
		if (createDate == null) {
			if (other.createDate != null)
				return false;
		} else if (!createDate.equals(other.createDate))
			return false;
		if (createrId == null) {
			if (other.createrId != null)
				return false;
		} else if (!createrId.equals(other.createrId))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
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
		if (lastUpdateDate == null) {
			if (other.lastUpdateDate != null)
				return false;
		} else if (!lastUpdateDate.equals(other.lastUpdateDate))
			return false;
		if (spaceName == null) {
			if (other.spaceName != null)
				return false;
		} else if (!spaceName.equals(other.spaceName))
			return false;
		if (templateContent == null) {
			if (other.templateContent != null)
				return false;
		} else if (!templateContent.equals(other.templateContent))
			return false;
		return true;
	}

}
