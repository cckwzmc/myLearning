package com.toney.dal.model;

import java.util.Date;

/**
 * 
 *************************************************************** 
 * <p>
 * 
 * @CLASS :TemplateMappingModel
 * @DESCRIPTION :模板映射管理
 * @AUTHOR :toney.li
 * @VERSION :v1.0
 * @DATE :Dec 29, 2012
 *       </p>
 **************************************************************** 
 */
public class TemplateMappingModel extends BaseObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = -712108272570723599L;

	private Integer id;
	private Integer channelId;
	private String pageTypeCode;
	private String isDetailPage;
	private Integer headId;
	private Integer footerId;
	private String tplPath;
	private Date createDate;
	private Date lastModifyDate;
	private Integer isEnabled;
	private String outputFilename;
	private String tplPrefix;
	private String description;
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getTplPrefix() {
		return tplPrefix;
	}

	public void setTplPrefix(String tplPrefix) {
		this.tplPrefix = tplPrefix;
	}

	public String getOutputFilename() {
		return outputFilename;
	}

	public void setOutputFilename(String outputFilename) {
		this.outputFilename = outputFilename;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getLastModifyDate() {
		return lastModifyDate;
	}

	public void setLastModifyDate(Date lastModifyDate) {
		this.lastModifyDate = lastModifyDate;
	}

	public Integer getIsEnabled() {
		return isEnabled;
	}

	public void setIsEnabled(Integer isEnabled) {
		this.isEnabled = isEnabled;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getChannelId() {
		return channelId;
	}

	public void setChannelId(Integer channelId) {
		this.channelId = channelId;
	}

	public String getPageTypeCode() {
		return pageTypeCode;
	}

	public void setPageTypeCode(String pageTypeCode) {
		this.pageTypeCode = pageTypeCode;
	}

	public String getIsDetailPage() {
		return isDetailPage;
	}

	public void setIsDetailPage(String isDetailPage) {
		this.isDetailPage = isDetailPage;
	}

	public Integer getHeadId() {
		return headId;
	}

	public void setHeadId(Integer headId) {
		this.headId = headId;
	}

	public Integer getFooterId() {
		return footerId;
	}

	public void setFooterId(Integer footerId) {
		this.footerId = footerId;
	}

	public String getTplPath() {
		return tplPath;
	}

	public void setTplPath(String tplPath) {
		this.tplPath = tplPath;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((channelId == null) ? 0 : channelId.hashCode());
		result = prime * result + ((createDate == null) ? 0 : createDate.hashCode());
		result = prime * result + ((footerId == null) ? 0 : footerId.hashCode());
		result = prime * result + ((headId == null) ? 0 : headId.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((isDetailPage == null) ? 0 : isDetailPage.hashCode());
		result = prime * result + ((isEnabled == null) ? 0 : isEnabled.hashCode());
		result = prime * result + ((lastModifyDate == null) ? 0 : lastModifyDate.hashCode());
		result = prime * result + ((outputFilename == null) ? 0 : outputFilename.hashCode());
		result = prime * result + ((pageTypeCode == null) ? 0 : pageTypeCode.hashCode());
		result = prime * result + ((tplPath == null) ? 0 : tplPath.hashCode());
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
		TemplateMappingModel other = (TemplateMappingModel) obj;
		if (channelId == null) {
			if (other.channelId != null)
				return false;
		} else if (!channelId.equals(other.channelId))
			return false;
		if (createDate == null) {
			if (other.createDate != null)
				return false;
		} else if (!createDate.equals(other.createDate))
			return false;
		if (footerId == null) {
			if (other.footerId != null)
				return false;
		} else if (!footerId.equals(other.footerId))
			return false;
		if (headId == null) {
			if (other.headId != null)
				return false;
		} else if (!headId.equals(other.headId))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (isDetailPage == null) {
			if (other.isDetailPage != null)
				return false;
		} else if (!isDetailPage.equals(other.isDetailPage))
			return false;
		if (isEnabled == null) {
			if (other.isEnabled != null)
				return false;
		} else if (!isEnabled.equals(other.isEnabled))
			return false;
		if (lastModifyDate == null) {
			if (other.lastModifyDate != null)
				return false;
		} else if (!lastModifyDate.equals(other.lastModifyDate))
			return false;
		if (outputFilename == null) {
			if (other.outputFilename != null)
				return false;
		} else if (!outputFilename.equals(other.outputFilename))
			return false;
		if (pageTypeCode == null) {
			if (other.pageTypeCode != null)
				return false;
		} else if (!pageTypeCode.equals(other.pageTypeCode))
			return false;
		if (tplPath == null) {
			if (other.tplPath != null)
				return false;
		} else if (!tplPath.equals(other.tplPath))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "TemplateMappingModel [id=" + id + ", channelId=" + channelId + ", pageTypeCode=" + pageTypeCode + ", isDetailPage=" + isDetailPage + ", headId=" + headId
				+ ", footerId=" + footerId + ", tplPath=" + tplPath + ", createDate=" + createDate + ", lastModifyDate=" + lastModifyDate + ", isEnabled=" + isEnabled
				+ ", outputFilename=" + outputFilename + "]";
	}

}
