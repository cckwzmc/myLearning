package com.toney.core.model;

/**
 * @author toney.li 频道类型，如：文章、图片、商店、
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
	private Integer userType;
	private Integer sendRank;
	private Integer isDefault;
	private Integer needDes;
	private Integer needPic;
	private Integer titleName;
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

	public Integer getUserType() {
		return userType;
	}

	public void setUserType(Integer userType) {
		this.userType = userType;
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

	public Integer getTitleName() {
		return titleName;
	}

	public void setTitleName(Integer titleName) {
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

}
