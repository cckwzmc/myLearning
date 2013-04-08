package com.toney.dal.bean;

import com.toney.dal.model.BaseObject;

/**
 * 
 *************************************************************** 
 * <p>
 * @CLASS :PublicSearchBean
 * @DESCRIPTION :公共页面发布管理.
 * @AUTHOR :toney.li
 * @VERSION :v1.0
 * @DATE :Jan 25, 2013
 *       </p>
 **************************************************************** 
 */
public class PublicSearchBean extends BaseObject {

	private static final long serialVersionUID = -7103762152583870248L;
	
	//描述.
	private String description;
	//模版名称.
	private String templateName;
	//是否可用.
	private Integer isEnabled;
	//是否页头页尾.
	private Integer headFooter;
	
	public Integer getHeadFooter() {
		return headFooter;
	}

	public void setHeadFooter(Integer headFooter) {
		this.headFooter = headFooter;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getTemplateName() {
		return templateName;
	}

	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}

	public Integer getIsEnabled() {
		return isEnabled;
	}

	public void setIsEnabled(Integer isEnabled) {
		this.isEnabled = isEnabled;
	}

	@Override
	public String toString() {
		return "PublicSearchBean [description=" + description + ", templateName=" + templateName + ", isEnabled=" + isEnabled + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((isEnabled == null) ? 0 : isEnabled.hashCode());
		result = prime * result + ((templateName == null) ? 0 : templateName.hashCode());
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
		PublicSearchBean other = (PublicSearchBean) obj;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (isEnabled == null) {
			if (other.isEnabled != null)
				return false;
		} else if (!isEnabled.equals(other.isEnabled))
			return false;
		if (templateName == null) {
			if (other.templateName != null)
				return false;
		} else if (!templateName.equals(other.templateName))
			return false;
		return true;
	}

}
