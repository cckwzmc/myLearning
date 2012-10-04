package com.toney.core.model;

/**
 * @author toney.li 模版管理配置.<br/>
 *         对应表dede_templage_manager
 */
public class TemplateManagerModel extends BaseObject {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2514631034462708103L;

	private Integer id;
	private String templateName;
	private Integer groupId;
	private String groupName;
	private String desc;
	private Long createDate;
	private String publishPath;
	private String value;
	private Integer isEnable;

	public String getPublishPath() {
		return publishPath;
	}

	public void setPublishPath(String publishPath) {
		this.publishPath = publishPath;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTemplateName() {
		return templateName;
	}

	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}

	public Integer getGroupId() {
		return groupId;
	}

	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Long getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Long createDate) {
		this.createDate = createDate;
	}

	public Integer getIsEnable() {
		return isEnable;
	}

	public void setIsEnable(Integer isEnable) {
		this.isEnable = isEnable;
	}

	@Override
	public String toString() {
		return "TemplateManagerModel [id=" + id + ", templateName=" + templateName + ", groupId=" + groupId + ", groupName=" + groupName
				+ ", desc=" + desc + ", createDate=" + createDate + ", value=" + value + ", isEnable=" + isEnable + "]";
	}

}
