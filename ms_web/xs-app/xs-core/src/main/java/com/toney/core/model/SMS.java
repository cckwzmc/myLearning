package com.toney.core.model;

import java.util.Date;

/**
 *************************************************************** 
 * <p>
 * 
 * @DESCRIPTION : Portal 短信实体类
 * @DATE :2012-4-10 上午11:49:15
 *       </p>
 **************************************************************** 
 */
public class SMS extends BaseObject {

	/**
	 * @serialField
	 */
	private static final long serialVersionUID = 6972459607595253226L;

	private Long id;// id
	private Long userId; // 用户id
	private Integer activeStatus;// 短信状态
	private Integer type;// 短信状态
	private String body;// 短信内容
	private String telephone; // 接收方手机号码
	private String subject; // 短信标题
	private String creator; // 短信创建者
	private Date sendTime;// 进入本功能的时间
	private Date lastUpdateTime;// 调用接口的时间

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Integer getActiveStatus() {
		return activeStatus;
	}

	public void setActiveStatus(Integer activeStatus) {
		this.activeStatus = activeStatus;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Date getSendTime() {
		return sendTime;
	}

	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
	}

	public Date getLastUpdateTime() {
		return lastUpdateTime;
	}

	public void setLastUpdateTime(Date lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	@Override
	public String toString() {
		return "SMS [id=" + id + ", userId=" + userId + ", activeStatus="
				+ activeStatus + ", type=" + type + ", body=" + body
				+ ", telephone=" + telephone + ", subject=" + subject
				+ ", creator=" + creator + ", sendTime=" + sendTime
				+ ", lastUpdateTime=" + lastUpdateTime + "]";
	}

}
