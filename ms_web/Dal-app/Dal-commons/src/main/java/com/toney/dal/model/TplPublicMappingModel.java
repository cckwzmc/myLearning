package com.toney.dal.model;

import java.util.Date;

import com.toney.dal.base.BaseObject;

/**
 * 
 *************************************************************** 
 * <p>
 * 
 * @CLASS :TplPublicModel
 * @DESCRIPTION :功能描述
 * @AUTHOR :toney.li
 * @VERSION :v1.0
 * @DATE :Jan 10, 2013
 *       </p>
 **************************************************************** 
 */
/**
 * 
 *************************************************************** 
 * <p>
 * @CLASS :TplPublicMappingModel
 * @DESCRIPTION :功能描述
 * @AUTHOR :toney.li
 * @VERSION :v1.0
 * @DATE :Jan 24, 2013
 *       </p>
 **************************************************************** 
 */
public class TplPublicMappingModel extends BaseObject {
	private static final long serialVersionUID = -4153734217439042183L;
	private Integer id;
	private Integer headFooter;
	private String tplPath;
	private Date createDate;
	private Date lastModifyDate;
	private Integer isEnabled;
	private String outputFilename;
	private String tplPrefix;
	private String description;

	public String getTplPrefix() {
		return tplPrefix;
	}

	public void setTplPrefix(String tplPrefix) {
		this.tplPrefix = tplPrefix;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getHeadFooter() {
		return headFooter;
	}

	public void setHeadFooter(Integer headFooter) {
		this.headFooter = headFooter;
	}

	public String getTplPath() {
		return tplPath;
	}

	public void setTplPath(String tplPath) {
		this.tplPath = tplPath;
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

	public String getOutputFilename() {
		return outputFilename;
	}

	public void setOutputFilename(String outputFilename) {
		this.outputFilename = outputFilename;
	}

	@Override
	public String toString() {
		return "TplPublicMappingModel [id=" + id + ", headFooter=" + headFooter + ", tplPath=" + tplPath + ", createDate=" + createDate + ", lastModifyDate=" + lastModifyDate
				+ ", isEnabled=" + isEnabled + ", outputFilename=" + outputFilename + ", description=" + description + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((createDate == null) ? 0 : createDate.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((headFooter == null) ? 0 : headFooter.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((isEnabled == null) ? 0 : isEnabled.hashCode());
		result = prime * result + ((lastModifyDate == null) ? 0 : lastModifyDate.hashCode());
		result = prime * result + ((outputFilename == null) ? 0 : outputFilename.hashCode());
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
		TplPublicMappingModel other = (TplPublicMappingModel) obj;
		if (createDate == null) {
			if (other.createDate != null)
				return false;
		} else if (!createDate.equals(other.createDate))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (headFooter == null) {
			if (other.headFooter != null)
				return false;
		} else if (!headFooter.equals(other.headFooter))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
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
		if (tplPath == null) {
			if (other.tplPath != null)
				return false;
		} else if (!tplPath.equals(other.tplPath))
			return false;
		return true;
	}
}