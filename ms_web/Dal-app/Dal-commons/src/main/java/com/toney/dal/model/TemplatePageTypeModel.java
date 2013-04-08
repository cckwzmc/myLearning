package com.toney.dal.model;

public class TemplatePageTypeModel extends BaseObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6449471415089310745L;

	private Integer id;
	private String typeCode;
	private String pageDesc;
	private Integer isEnabled;

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

	public String getTypeCode() {
		return typeCode;
	}

	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}

	public String getPageDesc() {
		return pageDesc;
	}

	public void setPageDesc(String pageDesc) {
		this.pageDesc = pageDesc;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((isEnabled == null) ? 0 : isEnabled.hashCode());
		result = prime * result + ((pageDesc == null) ? 0 : pageDesc.hashCode());
		result = prime * result + ((typeCode == null) ? 0 : typeCode.hashCode());
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
		TemplatePageTypeModel other = (TemplatePageTypeModel) obj;
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
		if (pageDesc == null) {
			if (other.pageDesc != null)
				return false;
		} else if (!pageDesc.equals(other.pageDesc))
			return false;
		if (typeCode == null) {
			if (other.typeCode != null)
				return false;
		} else if (!typeCode.equals(other.typeCode))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "TemplatePageTypeModel [id=" + id + ", typeCode=" + typeCode + ", pageDesc=" + pageDesc + ", isEnabled=" + isEnabled + "]";
	}

}
