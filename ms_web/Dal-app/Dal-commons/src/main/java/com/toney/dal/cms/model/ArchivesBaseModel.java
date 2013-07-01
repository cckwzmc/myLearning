package com.toney.dal.cms.model;

import com.toney.dal.base.BaseObject;

public class ArchivesBaseModel extends BaseObject {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4704894048709024056L;
	private Long id;
	private Integer channelTypeId; 
	private Integer channelId; 
	private String title; 
	private String titleStyle; 
	/**
	 * 文章简要
	 */
	private String summary; 
	private String label;
	private Integer isMake;
	private Integer isHide; 
	private Long createTime; 
	private Long lastModifyTime;
	/**
	 * 排序使用
	 */
	private Long sequnce; 
	private String author; 
	private String source; 
	private String litPic; 
	private Long publishDate; 
	private Long publisherId; 
	private String keywords; 
	private Integer weight; 
	private String redirectUrl; 
	private String more_ur; 
	private String url_1; 
	private String url_2; 
	private String pic_1; 
	private String pic_2; 

	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getChannelTypeId() {
		return channelTypeId;
	}

	public void setChannelTypeId(Integer channelTypeId) {
		this.channelTypeId = channelTypeId;
	}

	public Integer getChannelId() {
		return channelId;
	}

	public void setChannelId(Integer channelId) {
		this.channelId = channelId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTitleStyle() {
		return titleStyle;
	}

	public void setTitleStyle(String titleStyle) {
		this.titleStyle = titleStyle;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public Integer getIsMake() {
		return isMake;
	}

	public void setIsMake(Integer isMake) {
		this.isMake = isMake;
	}

	public Integer getIsHide() {
		return isHide;
	}

	public void setIsHide(Integer isHide) {
		this.isHide = isHide;
	}

	public Long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}

	public Long getLastModifyTime() {
		return lastModifyTime;
	}

	public void setLastModifyTime(Long lastModifyTime) {
		this.lastModifyTime = lastModifyTime;
	}

	public Long getSequnce() {
		return sequnce;
	}

	public void setSequnce(Long sequnce) {
		this.sequnce = sequnce;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getLitPic() {
		return litPic;
	}

	public void setLitPic(String litPic) {
		this.litPic = litPic;
	}

	public Long getPublishDate() {
		return publishDate;
	}

	public void setPublishDate(Long publishDate) {
		this.publishDate = publishDate;
	}

	public Long getPublisherId() {
		return publisherId;
	}

	public void setPublisherId(Long publisherId) {
		this.publisherId = publisherId;
	}

	public String getKeywords() {
		return keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	public Integer getWeight() {
		return weight;
	}

	public void setWeight(Integer weight) {
		this.weight = weight;
	}

	public String getRedirectUrl() {
		return redirectUrl;
	}

	public void setRedirectUrl(String redirectUrl) {
		this.redirectUrl = redirectUrl;
	}

	public String getMore_ur() {
		return more_ur;
	}

	public void setMore_ur(String more_ur) {
		this.more_ur = more_ur;
	}

	public String getUrl_1() {
		return url_1;
	}

	public void setUrl_1(String url_1) {
		this.url_1 = url_1;
	}

	public String getUrl_2() {
		return url_2;
	}

	public void setUrl_2(String url_2) {
		this.url_2 = url_2;
	}

	public String getPic_1() {
		return pic_1;
	}

	public void setPic_1(String pic_1) {
		this.pic_1 = pic_1;
	}

	public String getPic_2() {
		return pic_2;
	}

	public void setPic_2(String pic_2) {
		this.pic_2 = pic_2;
	}

	@Override
	public String toString() {
		return "ArchivesBaseModel [id=" + id + ", channelTypeId=" + channelTypeId + ", channelId=" + channelId + ", title=" + title + ", titleStyle=" + titleStyle + ", summary="
				+ summary + ", label=" + label + ", isMake=" + isMake + ", isHide=" + isHide + ", createTime=" + createTime + ", lastModifyTime=" + lastModifyTime + ", sequnce="
				+ sequnce + ", author=" + author + ", source=" + source + ", litPic=" + litPic + ", publishDate=" + publishDate + ", publisherId=" + publisherId + ", keywords="
				+ keywords + ", weight=" + weight + ", redirectUrl=" + redirectUrl + ", more_ur=" + more_ur + ", url_1=" + url_1 + ", url_2=" + url_2 + ", pic_1=" + pic_1
				+ ", pic_2=" + pic_2 + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((author == null) ? 0 : author.hashCode());
		result = prime * result + ((channelId == null) ? 0 : channelId.hashCode());
		result = prime * result + ((channelTypeId == null) ? 0 : channelTypeId.hashCode());
		result = prime * result + ((createTime == null) ? 0 : createTime.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((isHide == null) ? 0 : isHide.hashCode());
		result = prime * result + ((isMake == null) ? 0 : isMake.hashCode());
		result = prime * result + ((keywords == null) ? 0 : keywords.hashCode());
		result = prime * result + ((label == null) ? 0 : label.hashCode());
		result = prime * result + ((lastModifyTime == null) ? 0 : lastModifyTime.hashCode());
		result = prime * result + ((litPic == null) ? 0 : litPic.hashCode());
		result = prime * result + ((more_ur == null) ? 0 : more_ur.hashCode());
		result = prime * result + ((pic_1 == null) ? 0 : pic_1.hashCode());
		result = prime * result + ((pic_2 == null) ? 0 : pic_2.hashCode());
		result = prime * result + ((publishDate == null) ? 0 : publishDate.hashCode());
		result = prime * result + ((publisherId == null) ? 0 : publisherId.hashCode());
		result = prime * result + ((redirectUrl == null) ? 0 : redirectUrl.hashCode());
		result = prime * result + ((sequnce == null) ? 0 : sequnce.hashCode());
		result = prime * result + ((source == null) ? 0 : source.hashCode());
		result = prime * result + ((summary == null) ? 0 : summary.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		result = prime * result + ((titleStyle == null) ? 0 : titleStyle.hashCode());
		result = prime * result + ((url_1 == null) ? 0 : url_1.hashCode());
		result = prime * result + ((url_2 == null) ? 0 : url_2.hashCode());
		result = prime * result + ((weight == null) ? 0 : weight.hashCode());
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
		ArchivesBaseModel other = (ArchivesBaseModel) obj;
		if (author == null) {
			if (other.author != null)
				return false;
		} else if (!author.equals(other.author))
			return false;
		if (channelId == null) {
			if (other.channelId != null)
				return false;
		} else if (!channelId.equals(other.channelId))
			return false;
		if (channelTypeId == null) {
			if (other.channelTypeId != null)
				return false;
		} else if (!channelTypeId.equals(other.channelTypeId))
			return false;
		if (createTime == null) {
			if (other.createTime != null)
				return false;
		} else if (!createTime.equals(other.createTime))
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
		if (isMake == null) {
			if (other.isMake != null)
				return false;
		} else if (!isMake.equals(other.isMake))
			return false;
		if (keywords == null) {
			if (other.keywords != null)
				return false;
		} else if (!keywords.equals(other.keywords))
			return false;
		if (label == null) {
			if (other.label != null)
				return false;
		} else if (!label.equals(other.label))
			return false;
		if (lastModifyTime == null) {
			if (other.lastModifyTime != null)
				return false;
		} else if (!lastModifyTime.equals(other.lastModifyTime))
			return false;
		if (litPic == null) {
			if (other.litPic != null)
				return false;
		} else if (!litPic.equals(other.litPic))
			return false;
		if (more_ur == null) {
			if (other.more_ur != null)
				return false;
		} else if (!more_ur.equals(other.more_ur))
			return false;
		if (pic_1 == null) {
			if (other.pic_1 != null)
				return false;
		} else if (!pic_1.equals(other.pic_1))
			return false;
		if (pic_2 == null) {
			if (other.pic_2 != null)
				return false;
		} else if (!pic_2.equals(other.pic_2))
			return false;
		if (publishDate == null) {
			if (other.publishDate != null)
				return false;
		} else if (!publishDate.equals(other.publishDate))
			return false;
		if (publisherId == null) {
			if (other.publisherId != null)
				return false;
		} else if (!publisherId.equals(other.publisherId))
			return false;
		if (redirectUrl == null) {
			if (other.redirectUrl != null)
				return false;
		} else if (!redirectUrl.equals(other.redirectUrl))
			return false;
		if (sequnce == null) {
			if (other.sequnce != null)
				return false;
		} else if (!sequnce.equals(other.sequnce))
			return false;
		if (source == null) {
			if (other.source != null)
				return false;
		} else if (!source.equals(other.source))
			return false;
		if (summary == null) {
			if (other.summary != null)
				return false;
		} else if (!summary.equals(other.summary))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		if (titleStyle == null) {
			if (other.titleStyle != null)
				return false;
		} else if (!titleStyle.equals(other.titleStyle))
			return false;
		if (url_1 == null) {
			if (other.url_1 != null)
				return false;
		} else if (!url_1.equals(other.url_1))
			return false;
		if (url_2 == null) {
			if (other.url_2 != null)
				return false;
		} else if (!url_2.equals(other.url_2))
			return false;
		if (weight == null) {
			if (other.weight != null)
				return false;
		} else if (!weight.equals(other.weight))
			return false;
		return true;
	}

}
