package com.toney.dal.cms.model;

import java.util.Date;

import com.toney.dal.base.BaseObject;

/**
 * 
 *************************************************************** 
 * <p>
 * 
 * @CLASS :ChannelTypeModel
 * @DESCRIPTION :T_CHANNEL
 * @AUTHOR :toney.li
 * @VERSION :v1.0
 * @DATE :Jun 29, 2013
 *       </p>
 **************************************************************** 
 */
public class ChannelTypeModel extends BaseObject {
	private static final long serialVersionUID = -6151578569209107713L;
	private Integer id;
	private String typeCode;
	private String typeName;
	private String mainTable;
	private String detailTable;
	private Date createDate;

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

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getMainTable() {
		return mainTable;
	}

	public void setMainTable(String mainTable) {
		this.mainTable = mainTable;
	}

	public String getDetailTable() {
		return detailTable;
	}

	public void setDetailTable(String detailTable) {
		this.detailTable = detailTable;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	@Override
	public String toString() {
		return "ChannelTypeModel [id=" + id + ", typeCode=" + typeCode + ", typeName=" + typeName + ", mainTable=" + mainTable + ", detailTable=" + detailTable + ", createDate="
				+ createDate + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((createDate == null) ? 0 : createDate.hashCode());
		result = prime * result + ((detailTable == null) ? 0 : detailTable.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((mainTable == null) ? 0 : mainTable.hashCode());
		result = prime * result + ((typeCode == null) ? 0 : typeCode.hashCode());
		result = prime * result + ((typeName == null) ? 0 : typeName.hashCode());
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
		ChannelTypeModel other = (ChannelTypeModel) obj;
		if (createDate == null) {
			if (other.createDate != null)
				return false;
		} else if (!createDate.equals(other.createDate))
			return false;
		if (detailTable == null) {
			if (other.detailTable != null)
				return false;
		} else if (!detailTable.equals(other.detailTable))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (mainTable == null) {
			if (other.mainTable != null)
				return false;
		} else if (!mainTable.equals(other.mainTable))
			return false;
		if (typeCode == null) {
			if (other.typeCode != null)
				return false;
		} else if (!typeCode.equals(other.typeCode))
			return false;
		if (typeName == null) {
			if (other.typeName != null)
				return false;
		} else if (!typeName.equals(other.typeName))
			return false;
		return true;
	}

}
