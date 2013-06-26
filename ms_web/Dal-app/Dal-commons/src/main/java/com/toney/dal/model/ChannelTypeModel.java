package com.toney.dal.model;

import com.toney.dal.base.BaseObject;


/**
 * @author toney.li 频道类型，如：文章、图片、商店、
 * 对应dede_channeltype
 */
public class ChannelTypeModel extends BaseObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9173968497173486935L;

	private Integer id;

	/**
	 * name_id 如imgage /shop /article等
	 */
	private String nid;
	private String typeName;
	/**
	 * 对应的主表名称
	 */
	private String mainTable;
	/**
	 * 对应的内容表名称。
	 */
	private String addTable;
	/**
	 * 添加内容页面
	 */
	private String addCon;
	/**
	 * 列表管理页面
	 */
	private String manCon;
	/**
	 * 内容编辑页面
	 */
	private String editCon;
	/**
	 * 用户添加内容页面
	 */
	private String userAddCon;
	/**
	 * 用户列表管理页面
	 */
	private String userManCon;
	/**
	 * 用户内容编辑页面
	 */
	private String userEditCon;
	/**
	 * 区域标签
	 */
	private String fieldSet;
	/**
	 * 列表标签
	 */
	private String listFields;
	/**
	 * 未知
	 */
	private String allFields;
	/**
	 * 是否系统自带
	 */
	private Integer isSystem;
	/**
	 * 是否显示
	 */
	private Integer isShow;
	/**
	 * 未知
	 */
	private Integer isSend;
	private Integer arcSta;
	private String userType;
	private Integer sendRank;
	private Integer isDefault;
	private Integer needDes;
	private Integer needPic;
	private String titleName;
	private Integer onlyOne;
	private Integer dfcId;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNid() {
		return nid;
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

	public String getAddTable() {
		return addTable;
	}

	public void setAddTable(String addTable) {
		this.addTable = addTable;
	}

	public String getAddCon() {
		return addCon;
	}

	public void setAddCon(String addCon) {
		this.addCon = addCon;
	}

	public String getManCon() {
		return manCon;
	}

	public void setManCon(String manCon) {
		this.manCon = manCon;
	}

	public String getEditCon() {
		return editCon;
	}

	public void setEditCon(String editCon) {
		this.editCon = editCon;
	}

	public String getUserAddCon() {
		return userAddCon;
	}

	public void setUserAddCon(String userAddCon) {
		this.userAddCon = userAddCon;
	}

	public String getUserManCon() {
		return userManCon;
	}

	public void setUserManCon(String userManCon) {
		this.userManCon = userManCon;
	}

	public String getUserEditCon() {
		return userEditCon;
	}

	public void setUserEditCon(String userEditCon) {
		this.userEditCon = userEditCon;
	}

	public String getFieldSet() {
		return fieldSet;
	}

	public void setFieldSet(String fieldSet) {
		this.fieldSet = fieldSet;
	}

	public String getListFields() {
		return listFields;
	}

	public void setListFields(String listFields) {
		this.listFields = listFields;
	}

	public String getAllFields() {
		return allFields;
	}

	public void setAllFields(String allFields) {
		this.allFields = allFields;
	}

	public Integer getIsSystem() {
		return isSystem;
	}

	public void setIsSystem(Integer isSystem) {
		this.isSystem = isSystem;
	}

	public Integer getIsShow() {
		return isShow;
	}

	public void setIsShow(Integer isShow) {
		this.isShow = isShow;
	}

	public Integer getIsSend() {
		return isSend;
	}

	public void setIsSend(Integer isSend) {
		this.isSend = isSend;
	}

	public Integer getArcSta() {
		return arcSta;
	}

	public void setArcSta(Integer arcSta) {
		this.arcSta = arcSta;
	}

	public Integer getSendRank() {
		return sendRank;
	}

	public void setSendRank(Integer sendRank) {
		this.sendRank = sendRank;
	}

	public Integer getIsDefault() {
		return isDefault;
	}

	public void setIsDefault(Integer isDefault) {
		this.isDefault = isDefault;
	}

	public Integer getNeedDes() {
		return needDes;
	}

	public void setNeedDes(Integer needDes) {
		this.needDes = needDes;
	}

	public Integer getNeedPic() {
		return needPic;
	}

	public void setNeedPic(Integer needPic) {
		this.needPic = needPic;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getTitleName() {
		return titleName;
	}

	public void setTitleName(String titleName) {
		this.titleName = titleName;
	}

	public Integer getOnlyOne() {
		return onlyOne;
	}

	public void setOnlyOne(Integer onlyOne) {
		this.onlyOne = onlyOne;
	}

	public Integer getDfcId() {
		return dfcId;
	}

	public void setDfcId(Integer dfcId) {
		this.dfcId = dfcId;
	}

	public void setNid(String nid) {
		this.nid = nid;
	}

	@Override
	public String toString() {
		return "ChannelTypeModel [id=" + id + ", nid=" + nid + ", typeName="
				+ typeName + ", mainTable=" + mainTable + ", addTable="
				+ addTable + ", addCon=" + addCon + ", manCon=" + manCon
				+ ", editCon=" + editCon + ", userAddCon=" + userAddCon
				+ ", userManCon=" + userManCon + ", userEditCon=" + userEditCon
				+ ", fieldSet=" + fieldSet + ", listFields=" + listFields
				+ ", allFields=" + allFields + ", isSystem=" + isSystem
				+ ", isShow=" + isShow + ", isSend=" + isSend + ", arcSta="
				+ arcSta + ", userType=" + userType + ", sendRank=" + sendRank
				+ ", isDefault=" + isDefault + ", needDes=" + needDes
				+ ", needPic=" + needPic + ", titleName=" + titleName
				+ ", onlyOne=" + onlyOne + ", dfcId=" + dfcId + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((addCon == null) ? 0 : addCon.hashCode());
		result = prime * result
				+ ((addTable == null) ? 0 : addTable.hashCode());
		result = prime * result
				+ ((allFields == null) ? 0 : allFields.hashCode());
		result = prime * result + ((arcSta == null) ? 0 : arcSta.hashCode());
		result = prime * result + ((dfcId == null) ? 0 : dfcId.hashCode());
		result = prime * result + ((editCon == null) ? 0 : editCon.hashCode());
		result = prime * result
				+ ((fieldSet == null) ? 0 : fieldSet.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((isDefault == null) ? 0 : isDefault.hashCode());
		result = prime * result + ((isSend == null) ? 0 : isSend.hashCode());
		result = prime * result + ((isShow == null) ? 0 : isShow.hashCode());
		result = prime * result
				+ ((isSystem == null) ? 0 : isSystem.hashCode());
		result = prime * result
				+ ((listFields == null) ? 0 : listFields.hashCode());
		result = prime * result
				+ ((mainTable == null) ? 0 : mainTable.hashCode());
		result = prime * result + ((manCon == null) ? 0 : manCon.hashCode());
		result = prime * result + ((needDes == null) ? 0 : needDes.hashCode());
		result = prime * result + ((needPic == null) ? 0 : needPic.hashCode());
		result = prime * result + ((nid == null) ? 0 : nid.hashCode());
		result = prime * result + ((onlyOne == null) ? 0 : onlyOne.hashCode());
		result = prime * result
				+ ((sendRank == null) ? 0 : sendRank.hashCode());
		result = prime * result
				+ ((titleName == null) ? 0 : titleName.hashCode());
		result = prime * result
				+ ((typeName == null) ? 0 : typeName.hashCode());
		result = prime * result
				+ ((userAddCon == null) ? 0 : userAddCon.hashCode());
		result = prime * result
				+ ((userEditCon == null) ? 0 : userEditCon.hashCode());
		result = prime * result
				+ ((userManCon == null) ? 0 : userManCon.hashCode());
		result = prime * result
				+ ((userType == null) ? 0 : userType.hashCode());
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
		if (addCon == null) {
			if (other.addCon != null)
				return false;
		} else if (!addCon.equals(other.addCon))
			return false;
		if (addTable == null) {
			if (other.addTable != null)
				return false;
		} else if (!addTable.equals(other.addTable))
			return false;
		if (allFields == null) {
			if (other.allFields != null)
				return false;
		} else if (!allFields.equals(other.allFields))
			return false;
		if (arcSta == null) {
			if (other.arcSta != null)
				return false;
		} else if (!arcSta.equals(other.arcSta))
			return false;
		if (dfcId == null) {
			if (other.dfcId != null)
				return false;
		} else if (!dfcId.equals(other.dfcId))
			return false;
		if (editCon == null) {
			if (other.editCon != null)
				return false;
		} else if (!editCon.equals(other.editCon))
			return false;
		if (fieldSet == null) {
			if (other.fieldSet != null)
				return false;
		} else if (!fieldSet.equals(other.fieldSet))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (isDefault == null) {
			if (other.isDefault != null)
				return false;
		} else if (!isDefault.equals(other.isDefault))
			return false;
		if (isSend == null) {
			if (other.isSend != null)
				return false;
		} else if (!isSend.equals(other.isSend))
			return false;
		if (isShow == null) {
			if (other.isShow != null)
				return false;
		} else if (!isShow.equals(other.isShow))
			return false;
		if (isSystem == null) {
			if (other.isSystem != null)
				return false;
		} else if (!isSystem.equals(other.isSystem))
			return false;
		if (listFields == null) {
			if (other.listFields != null)
				return false;
		} else if (!listFields.equals(other.listFields))
			return false;
		if (mainTable == null) {
			if (other.mainTable != null)
				return false;
		} else if (!mainTable.equals(other.mainTable))
			return false;
		if (manCon == null) {
			if (other.manCon != null)
				return false;
		} else if (!manCon.equals(other.manCon))
			return false;
		if (needDes == null) {
			if (other.needDes != null)
				return false;
		} else if (!needDes.equals(other.needDes))
			return false;
		if (needPic == null) {
			if (other.needPic != null)
				return false;
		} else if (!needPic.equals(other.needPic))
			return false;
		if (nid == null) {
			if (other.nid != null)
				return false;
		} else if (!nid.equals(other.nid))
			return false;
		if (onlyOne == null) {
			if (other.onlyOne != null)
				return false;
		} else if (!onlyOne.equals(other.onlyOne))
			return false;
		if (sendRank == null) {
			if (other.sendRank != null)
				return false;
		} else if (!sendRank.equals(other.sendRank))
			return false;
		if (titleName == null) {
			if (other.titleName != null)
				return false;
		} else if (!titleName.equals(other.titleName))
			return false;
		if (typeName == null) {
			if (other.typeName != null)
				return false;
		} else if (!typeName.equals(other.typeName))
			return false;
		if (userAddCon == null) {
			if (other.userAddCon != null)
				return false;
		} else if (!userAddCon.equals(other.userAddCon))
			return false;
		if (userEditCon == null) {
			if (other.userEditCon != null)
				return false;
		} else if (!userEditCon.equals(other.userEditCon))
			return false;
		if (userManCon == null) {
			if (other.userManCon != null)
				return false;
		} else if (!userManCon.equals(other.userManCon))
			return false;
		if (userType == null) {
			if (other.userType != null)
				return false;
		} else if (!userType.equals(other.userType))
			return false;
		return true;
	}


}
