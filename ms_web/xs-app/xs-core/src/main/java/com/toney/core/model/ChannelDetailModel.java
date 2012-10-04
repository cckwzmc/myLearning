package com.toney.core.model;

import java.util.List;

/**
 * @author toney.li 判断明细信息。
 */
public class ChannelDetailModel extends BaseObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3699329276723584160L;
	
	private List<ChannelDetailModel> childrens;
	
	private Integer id;
	/**
	 * 上一级ID
	 */
	private Integer reId;
	/**
	 * 是否顶级Id 0:是，1：否
	 */
	private Integer topId;
	/**
	 * 排序使用
	 */
	private Integer sortRank;
	private String typeName;
	/**
	 * 出版路径
	 */
	private String typeDir;
	/**
	 * 默认链接到那个页面 ;1:index.html 0:列表第一页
	 */
	private Integer isDefault;
	/**
	 * 出版默认名称
	 */
	private String defaultName;
	/**
	 * 是否支持用户投稿。
	 */
	private Integer isSend;
	private Integer channelTypeId;
	/**
	 * 最大页数：-1：不限制
	 */
	private Integer maxPage;
	/**
	 * 未知
	 */
	private Integer isPart;
	/**
	 * 未知
	 */
	private Integer coRank;
	/**
	 * 首页模版
	 */
	private String tempIndex;
	/**
	 * 列表页模版
	 */
	private String templist;
	/**
	 * 文章页模版。
	 */
	private String tempArticle;
	/**
	 * 详细页目录规则
	 */
	private String nameRule;
	/**
	 * 列表页命名规则
	 */
	private String nameRule2;
	/**
	 * 未知
	 */
	private String modName;
	/**
	 * 
	 */
	private String description;
	private String keywords;
	private String seoTitle;
	/**
	 * 是否支持多站点 0:不支持 1：支持
	 */
	private Integer moreSite;
	private String sitePath;
	private String siteUrl;
	/**
	 * 0:不隐藏 1：隐藏
	 */
	private Integer isHidden;
	private Integer cross;
	private String crossId;
	/**
	 * 简介
	 */
	private String content;
	private String smallTypes;

	public List<ChannelDetailModel> getChildrens() {
		return childrens;
	}

	public void setChildrens(List<ChannelDetailModel> childrens) {
		this.childrens = childrens;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getReId() {
		return reId;
	}

	public void setReId(Integer reId) {
		this.reId = reId;
	}

	public Integer getTopId() {
		return topId;
	}

	public void setTopId(Integer topId) {
		this.topId = topId;
	}

	public Integer getSortRank() {
		return sortRank;
	}

	public void setSortRank(Integer sortRank) {
		this.sortRank = sortRank;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getTypeDir() {
		return typeDir;
	}

	public void setTypeDir(String typeDir) {
		this.typeDir = typeDir;
	}

	public Integer getIsDefault() {
		return isDefault;
	}

	public void setIsDefault(Integer isDefault) {
		this.isDefault = isDefault;
	}


	public String getDefaultName() {
		return defaultName;
	}

	public void setDefaultName(String defaultName) {
		this.defaultName = defaultName;
	}

	public Integer getIsSend() {
		return isSend;
	}

	public void setIsSend(Integer isSend) {
		this.isSend = isSend;
	}

	public Integer getChannelTypeId() {
		return channelTypeId;
	}

	public void setChannelTypeId(Integer channelTypeId) {
		this.channelTypeId = channelTypeId;
	}


	public Integer getMaxPage() {
		return maxPage;
	}

	public void setMaxPage(Integer maxPage) {
		this.maxPage = maxPage;
	}

	public Integer getIsPart() {
		return isPart;
	}

	public void setIsPart(Integer isPart) {
		this.isPart = isPart;
	}

	public Integer getCoRank() {
		return coRank;
	}

	public void setCoRank(Integer coRank) {
		this.coRank = coRank;
	}

	public String getTempIndex() {
		return tempIndex;
	}

	public void setTempIndex(String tempIndex) {
		this.tempIndex = tempIndex;
	}

	public String getTemplist() {
		return templist;
	}

	public void setTemplist(String templist) {
		this.templist = templist;
	}

	public String getTempArticle() {
		return tempArticle;
	}

	public void setTempArticle(String tempArticle) {
		this.tempArticle = tempArticle;
	}

	public String getNameRule() {
		return nameRule;
	}

	public void setNameRule(String nameRule) {
		this.nameRule = nameRule;
	}

	public String getNameRule2() {
		return nameRule2;
	}

	public void setNameRule2(String nameRule2) {
		this.nameRule2 = nameRule2;
	}

	public String getModName() {
		return modName;
	}

	public void setModName(String modName) {
		this.modName = modName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getKeywords() {
		return keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	public String getSeoTitle() {
		return seoTitle;
	}

	public void setSeoTitle(String seoTitle) {
		this.seoTitle = seoTitle;
	}

	public Integer getMoreSite() {
		return moreSite;
	}

	public void setMoreSite(Integer moreSite) {
		this.moreSite = moreSite;
	}

	public String getSitePath() {
		return sitePath;
	}

	public void setSitePath(String sitePath) {
		this.sitePath = sitePath;
	}

	public String getSiteUrl() {
		return siteUrl;
	}

	public void setSiteUrl(String siteUrl) {
		this.siteUrl = siteUrl;
	}

	public Integer getIsHidden() {
		return isHidden;
	}

	public void setIsHidden(Integer isHidden) {
		this.isHidden = isHidden;
	}

	public Integer getCross() {
		return cross;
	}

	public void setCross(Integer cross) {
		this.cross = cross;
	}

	public String getCrossId() {
		return crossId;
	}

	public void setCrossId(String crossId) {
		this.crossId = crossId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getSmallTypes() {
		return smallTypes;
	}

	public void setSmallTypes(String smallTypes) {
		this.smallTypes = smallTypes;
	}

	@Override
	public String toString() {
		return "ChannelDetalModel [childrens=" + childrens + ", id=" + id
				+ ", reId=" + reId + ", topId=" + topId + ", sortRank="
				+ sortRank + ", typeName=" + typeName + ", typeDir=" + typeDir
				+ ", isDefault=" + isDefault + ", defaultName=" + defaultName
				+ ", isSend=" + isSend + ", channelTypeId=" + channelTypeId
				+ ", maxPage=" + maxPage + ", isPart=" + isPart + ", coRank="
				+ coRank + ", tempIndex=" + tempIndex + ", templist="
				+ templist + ", tempArticle=" + tempArticle + ", nameRule="
				+ nameRule + ", nameRule2=" + nameRule2 + ", modName="
				+ modName + ", description=" + description + ", keywords="
				+ keywords + ", seoTitle=" + seoTitle + ", moreSite="
				+ moreSite + ", sitePath=" + sitePath + ", siteUrl=" + siteUrl
				+ ", isHidden=" + isHidden + ", cross=" + cross + ", crossId="
				+ crossId + ", content=" + content + ", smallTypes="
				+ smallTypes + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((channelTypeId == null) ? 0 : channelTypeId.hashCode());
		result = prime * result
				+ ((childrens == null) ? 0 : childrens.hashCode());
		result = prime * result + ((coRank == null) ? 0 : coRank.hashCode());
		result = prime * result + ((content == null) ? 0 : content.hashCode());
		result = prime * result + ((cross == null) ? 0 : cross.hashCode());
		result = prime * result + ((crossId == null) ? 0 : crossId.hashCode());
		result = prime * result
				+ ((defaultName == null) ? 0 : defaultName.hashCode());
		result = prime * result
				+ ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((isDefault == null) ? 0 : isDefault.hashCode());
		result = prime * result
				+ ((isHidden == null) ? 0 : isHidden.hashCode());
		result = prime * result + ((isPart == null) ? 0 : isPart.hashCode());
		result = prime * result + ((isSend == null) ? 0 : isSend.hashCode());
		result = prime * result
				+ ((keywords == null) ? 0 : keywords.hashCode());
		result = prime * result + ((maxPage == null) ? 0 : maxPage.hashCode());
		result = prime * result + ((modName == null) ? 0 : modName.hashCode());
		result = prime * result
				+ ((moreSite == null) ? 0 : moreSite.hashCode());
		result = prime * result
				+ ((nameRule == null) ? 0 : nameRule.hashCode());
		result = prime * result
				+ ((nameRule2 == null) ? 0 : nameRule2.hashCode());
		result = prime * result + ((reId == null) ? 0 : reId.hashCode());
		result = prime * result
				+ ((seoTitle == null) ? 0 : seoTitle.hashCode());
		result = prime * result
				+ ((sitePath == null) ? 0 : sitePath.hashCode());
		result = prime * result + ((siteUrl == null) ? 0 : siteUrl.hashCode());
		result = prime * result
				+ ((smallTypes == null) ? 0 : smallTypes.hashCode());
		result = prime * result
				+ ((sortRank == null) ? 0 : sortRank.hashCode());
		result = prime * result
				+ ((tempArticle == null) ? 0 : tempArticle.hashCode());
		result = prime * result
				+ ((tempIndex == null) ? 0 : tempIndex.hashCode());
		result = prime * result
				+ ((templist == null) ? 0 : templist.hashCode());
		result = prime * result + ((topId == null) ? 0 : topId.hashCode());
		result = prime * result + ((typeDir == null) ? 0 : typeDir.hashCode());
		result = prime * result
				+ ((typeName == null) ? 0 : typeName.hashCode());
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
		ChannelDetailModel other = (ChannelDetailModel) obj;
		if (channelTypeId == null) {
			if (other.channelTypeId != null)
				return false;
		} else if (!channelTypeId.equals(other.channelTypeId))
			return false;
		if (childrens == null) {
			if (other.childrens != null)
				return false;
		} else if (!childrens.equals(other.childrens))
			return false;
		if (coRank == null) {
			if (other.coRank != null)
				return false;
		} else if (!coRank.equals(other.coRank))
			return false;
		if (content == null) {
			if (other.content != null)
				return false;
		} else if (!content.equals(other.content))
			return false;
		if (cross == null) {
			if (other.cross != null)
				return false;
		} else if (!cross.equals(other.cross))
			return false;
		if (crossId == null) {
			if (other.crossId != null)
				return false;
		} else if (!crossId.equals(other.crossId))
			return false;
		if (defaultName == null) {
			if (other.defaultName != null)
				return false;
		} else if (!defaultName.equals(other.defaultName))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
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
		if (isHidden == null) {
			if (other.isHidden != null)
				return false;
		} else if (!isHidden.equals(other.isHidden))
			return false;
		if (isPart == null) {
			if (other.isPart != null)
				return false;
		} else if (!isPart.equals(other.isPart))
			return false;
		if (isSend == null) {
			if (other.isSend != null)
				return false;
		} else if (!isSend.equals(other.isSend))
			return false;
		if (keywords == null) {
			if (other.keywords != null)
				return false;
		} else if (!keywords.equals(other.keywords))
			return false;
		if (maxPage == null) {
			if (other.maxPage != null)
				return false;
		} else if (!maxPage.equals(other.maxPage))
			return false;
		if (modName == null) {
			if (other.modName != null)
				return false;
		} else if (!modName.equals(other.modName))
			return false;
		if (moreSite == null) {
			if (other.moreSite != null)
				return false;
		} else if (!moreSite.equals(other.moreSite))
			return false;
		if (nameRule == null) {
			if (other.nameRule != null)
				return false;
		} else if (!nameRule.equals(other.nameRule))
			return false;
		if (nameRule2 == null) {
			if (other.nameRule2 != null)
				return false;
		} else if (!nameRule2.equals(other.nameRule2))
			return false;
		if (reId == null) {
			if (other.reId != null)
				return false;
		} else if (!reId.equals(other.reId))
			return false;
		if (seoTitle == null) {
			if (other.seoTitle != null)
				return false;
		} else if (!seoTitle.equals(other.seoTitle))
			return false;
		if (sitePath == null) {
			if (other.sitePath != null)
				return false;
		} else if (!sitePath.equals(other.sitePath))
			return false;
		if (siteUrl == null) {
			if (other.siteUrl != null)
				return false;
		} else if (!siteUrl.equals(other.siteUrl))
			return false;
		if (smallTypes == null) {
			if (other.smallTypes != null)
				return false;
		} else if (!smallTypes.equals(other.smallTypes))
			return false;
		if (sortRank == null) {
			if (other.sortRank != null)
				return false;
		} else if (!sortRank.equals(other.sortRank))
			return false;
		if (tempArticle == null) {
			if (other.tempArticle != null)
				return false;
		} else if (!tempArticle.equals(other.tempArticle))
			return false;
		if (tempIndex == null) {
			if (other.tempIndex != null)
				return false;
		} else if (!tempIndex.equals(other.tempIndex))
			return false;
		if (templist == null) {
			if (other.templist != null)
				return false;
		} else if (!templist.equals(other.templist))
			return false;
		if (topId == null) {
			if (other.topId != null)
				return false;
		} else if (!topId.equals(other.topId))
			return false;
		if (typeDir == null) {
			if (other.typeDir != null)
				return false;
		} else if (!typeDir.equals(other.typeDir))
			return false;
		if (typeName == null) {
			if (other.typeName != null)
				return false;
		} else if (!typeName.equals(other.typeName))
			return false;
		return true;
	}

}
