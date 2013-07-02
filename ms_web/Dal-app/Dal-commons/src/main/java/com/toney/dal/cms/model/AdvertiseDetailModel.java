package com.toney.dal.cms.model;

import com.toney.dal.base.BaseObject;

/**
 * 
 * 广告内容表
 * 
 * @author toney.li
 * 
 */
public class AdvertiseDetailModel extends BaseObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8706834355903820206L;
	private Integer id;
	/**
	 * 所属广告位
	 */
	private Integer adId;
	/**
	 * 广告名称
	 */
	private String title;
	/**
	 * 简要描述
	 */
	private String summary;
	private String detailDesc;
	private Long createDate;
	private Long startDate;
	private Long endDate;
	private Integer isHide;
	private String url;
	private String picPath;
	/**
	 * 出版状态,0:未出版，1：已出版，2：出版出错
	 */
	private Integer status;
	/**
	 * 广告创建者
	 */
	private Long createrId;
	private Long lastUpdateDate;
	/**
	 * 模版,如果不为空，就使用该模版
	 */
	private String templateContent;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getAdId() {
		return adId;
	}

	public void setAdId(Integer adId) {
		this.adId = adId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getDetailDesc() {
		return detailDesc;
	}

	public void setDetailDesc(String detailDesc) {
		this.detailDesc = detailDesc;
	}

	public Long getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Long createDate) {
		this.createDate = createDate;
	}

	public Long getStartDate() {
		return startDate;
	}

	public void setStartDate(Long startDate) {
		this.startDate = startDate;
	}

	public Long getEndDate() {
		return endDate;
	}

	public void setEndDate(Long endDate) {
		this.endDate = endDate;
	}

	public Integer getIsHide() {
		return isHide;
	}

	public void setIsHide(Integer isHide) {
		this.isHide = isHide;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getPicPath() {
		return picPath;
	}

	public void setPicPath(String picPath) {
		this.picPath = picPath;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Long getCreaterId() {
		return createrId;
	}

	public void setCreaterId(Long createrId) {
		this.createrId = createrId;
	}

	public Long getLastUpdateDate() {
		return lastUpdateDate;
	}

	public void setLastUpdateDate(Long lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
	}

	public String getTemplateContent() {
		return templateContent;
	}

	public void setTemplateContent(String templateContent) {
		this.templateContent = templateContent;
	}

	@Override
	public String toString() {
		return "AdvertiseDetailModel [id=" + id + ", adId=" + adId + ", title="
				+ title + ", summary=" + summary + ", detailDesc=" + detailDesc
				+ ", createDate=" + createDate + ", startDate=" + startDate
				+ ", endDate=" + endDate + ", isHide=" + isHide + ", url="
				+ url + ", picPath=" + picPath + ", status=" + status
				+ ", createrId=" + createrId + ", lastUpdateDate="
				+ lastUpdateDate + ", templateContent=" + templateContent + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((adId == null) ? 0 : adId.hashCode());
		result = prime * result
				+ ((createDate == null) ? 0 : createDate.hashCode());
		result = prime * result
				+ ((createrId == null) ? 0 : createrId.hashCode());
		result = prime * result
				+ ((detailDesc == null) ? 0 : detailDesc.hashCode());
		result = prime * result + ((endDate == null) ? 0 : endDate.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((isHide == null) ? 0 : isHide.hashCode());
		result = prime * result
				+ ((lastUpdateDate == null) ? 0 : lastUpdateDate.hashCode());
		result = prime * result + ((picPath == null) ? 0 : picPath.hashCode());
		result = prime * result
				+ ((startDate == null) ? 0 : startDate.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + ((summary == null) ? 0 : summary.hashCode());
		result = prime * result
				+ ((templateContent == null) ? 0 : templateContent.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		result = prime * result + ((url == null) ? 0 : url.hashCode());
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
		AdvertiseDetailModel other = (AdvertiseDetailModel) obj;
		if (adId == null) {
			if (other.adId != null)
				return false;
		} else if (!adId.equals(other.adId))
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
		if (detailDesc == null) {
			if (other.detailDesc != null)
				return false;
		} else if (!detailDesc.equals(other.detailDesc))
			return false;
		if (endDate == null) {
			if (other.endDate != null)
				return false;
		} else if (!endDate.equals(other.endDate))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (isHide == null) {
			if (other.isHide != null)
				return false;
		} else if (!isHide.equals(other.isHide))
			return false;
		if (lastUpdateDate == null) {
			if (other.lastUpdateDate != null)
				return false;
		} else if (!lastUpdateDate.equals(other.lastUpdateDate))
			return false;
		if (picPath == null) {
			if (other.picPath != null)
				return false;
		} else if (!picPath.equals(other.picPath))
			return false;
		if (startDate == null) {
			if (other.startDate != null)
				return false;
		} else if (!startDate.equals(other.startDate))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		if (summary == null) {
			if (other.summary != null)
				return false;
		} else if (!summary.equals(other.summary))
			return false;
		if (templateContent == null) {
			if (other.templateContent != null)
				return false;
		} else if (!templateContent.equals(other.templateContent))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		if (url == null) {
			if (other.url != null)
				return false;
		} else if (!url.equals(other.url))
			return false;
		return true;
	}

}
