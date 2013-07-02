package com.toney.dal.cms.model;

import com.toney.dal.base.BaseObject;

public class ChannelBaseModel extends BaseObject {
	/**
	 * 
	 */
	private static final long serialVersionUID = -9213020311594797870L;
	private Integer id;
	private Integer parentId;
	private String channelName;
	private Integer isHide;
	/**
	 * '文章类型，参考T_CHANNELTYPE表'
	 */
	private Integer channelTypeId;
	/**
	 * 频道发布目录
	 */
	private Integer channelDir;
	/**
	 * DEFAULT '50' COMMENT '手工排序,按顶级频道区分，从小到大',
	 */
	private Integer seqSort;
	/**
	 * 'LIST最大页数',
	 */
	private Integer maxPage;
	/**
	 * '首页发布路径',
	 */
	private String publishIndex;
	/**
	 * 'LIST发布路径',
	 */
	private String publishList;
	/**
	 * 'ARTICLE发布路径',
	 */
	private String publishArticle;
	/**
	 * '直接跳转链接',
	 */
	private String redirectUrl;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public String getChannelName() {
		return channelName;
	}

	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}

	public Integer getIsHide() {
		return isHide;
	}

	public void setIsHide(Integer isHide) {
		this.isHide = isHide;
	}

	public Integer getChannelTypeId() {
		return channelTypeId;
	}

	public void setChannelTypeId(Integer channelTypeId) {
		this.channelTypeId = channelTypeId;
	}

	public Integer getChannelDir() {
		return channelDir;
	}

	public void setChannelDir(Integer channelDir) {
		this.channelDir = channelDir;
	}

	public Integer getSeqSort() {
		return seqSort;
	}

	public void setSeqSort(Integer seqSort) {
		this.seqSort = seqSort;
	}

	public Integer getMaxPage() {
		return maxPage;
	}

	public void setMaxPage(Integer maxPage) {
		this.maxPage = maxPage;
	}

	public String getPublishIndex() {
		return publishIndex;
	}

	public void setPublishIndex(String publishIndex) {
		this.publishIndex = publishIndex;
	}

	public String getPublishList() {
		return publishList;
	}

	public void setPublishList(String publishList) {
		this.publishList = publishList;
	}

	public String getPublishArticle() {
		return publishArticle;
	}

	public void setPublishArticle(String publishArticle) {
		this.publishArticle = publishArticle;
	}

	public String getRedirectUrl() {
		return redirectUrl;
	}

	public void setRedirectUrl(String redirectUrl) {
		this.redirectUrl = redirectUrl;
	}

	@Override
	public String toString() {
		return "ChannelBaseModel [id=" + id + ", parentId=" + parentId
				+ ", channelName=" + channelName + ", isHide=" + isHide
				+ ", channelTypeId=" + channelTypeId + ", channelDir="
				+ channelDir + ", seqSort=" + seqSort + ", maxPage=" + maxPage
				+ ", publishIndex=" + publishIndex + ", publishList="
				+ publishList + ", publishArticle=" + publishArticle
				+ ", redirectUrl=" + redirectUrl + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((channelDir == null) ? 0 : channelDir.hashCode());
		result = prime * result
				+ ((channelName == null) ? 0 : channelName.hashCode());
		result = prime * result
				+ ((channelTypeId == null) ? 0 : channelTypeId.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((isHide == null) ? 0 : isHide.hashCode());
		result = prime * result + ((maxPage == null) ? 0 : maxPage.hashCode());
		result = prime * result
				+ ((parentId == null) ? 0 : parentId.hashCode());
		result = prime * result
				+ ((publishArticle == null) ? 0 : publishArticle.hashCode());
		result = prime * result
				+ ((publishIndex == null) ? 0 : publishIndex.hashCode());
		result = prime * result
				+ ((publishList == null) ? 0 : publishList.hashCode());
		result = prime * result
				+ ((redirectUrl == null) ? 0 : redirectUrl.hashCode());
		result = prime * result + ((seqSort == null) ? 0 : seqSort.hashCode());
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
		ChannelBaseModel other = (ChannelBaseModel) obj;
		if (channelDir == null) {
			if (other.channelDir != null)
				return false;
		} else if (!channelDir.equals(other.channelDir))
			return false;
		if (channelName == null) {
			if (other.channelName != null)
				return false;
		} else if (!channelName.equals(other.channelName))
			return false;
		if (channelTypeId == null) {
			if (other.channelTypeId != null)
				return false;
		} else if (!channelTypeId.equals(other.channelTypeId))
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
		if (maxPage == null) {
			if (other.maxPage != null)
				return false;
		} else if (!maxPage.equals(other.maxPage))
			return false;
		if (parentId == null) {
			if (other.parentId != null)
				return false;
		} else if (!parentId.equals(other.parentId))
			return false;
		if (publishArticle == null) {
			if (other.publishArticle != null)
				return false;
		} else if (!publishArticle.equals(other.publishArticle))
			return false;
		if (publishIndex == null) {
			if (other.publishIndex != null)
				return false;
		} else if (!publishIndex.equals(other.publishIndex))
			return false;
		if (publishList == null) {
			if (other.publishList != null)
				return false;
		} else if (!publishList.equals(other.publishList))
			return false;
		if (redirectUrl == null) {
			if (other.redirectUrl != null)
				return false;
		} else if (!redirectUrl.equals(other.redirectUrl))
			return false;
		if (seqSort == null) {
			if (other.seqSort != null)
				return false;
		} else if (!seqSort.equals(other.seqSort))
			return false;
		return true;
	}

}
