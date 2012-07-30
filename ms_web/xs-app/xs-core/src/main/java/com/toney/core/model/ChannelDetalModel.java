package com.toney.core.model;

/**
 * @author toney.li 判断明细信息。
 */
public class ChannelDetalModel extends BaseObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3699329276723584160L;
	
	private ChannelDetalModel childrens;
	
	private Integer id;
	/**
	 * 上一级ID
	 */
	private Integer reId;
	/**
	 * 是否顶级Id 0:否，1：是
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
	private Integer defaultName;
	/**
	 * 是否支持用户投稿。
	 */
	private Integer isSend;
	private Integer channelTypeId;
	/**
	 * 最大页数：-1：不限制
	 */
	private Integer maxpage;
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

	
	public ChannelDetalModel getChildrens() {
		return childrens;
	}

	public void setChildrens(ChannelDetalModel childrens) {
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

	public Integer getDefaultName() {
		return defaultName;
	}

	public void setDefaultName(Integer defaultName) {
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

	public Integer getMaxpage() {
		return maxpage;
	}

	public void setMaxpage(Integer maxpage) {
		this.maxpage = maxpage;
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
		return "ChannelDetalModel [id=" + id + ", reId=" + reId + ", topId="
				+ topId + ", sortRank=" + sortRank + ", typeName=" + typeName
				+ ", typeDir=" + typeDir + ", isDefault=" + isDefault
				+ ", defaultName=" + defaultName + ", isSend=" + isSend
				+ ", channelTypeId=" + channelTypeId + ", maxpage=" + maxpage
				+ ", isPart=" + isPart + ", coRank=" + coRank + ", tempIndex="
				+ tempIndex + ", templist=" + templist + ", tempArticle="
				+ tempArticle + ", nameRule=" + nameRule + ", nameRule2="
				+ nameRule2 + ", modName=" + modName + ", description="
				+ description + ", keywords=" + keywords + ", seoTitle="
				+ seoTitle + ", moreSite=" + moreSite + ", sitePath="
				+ sitePath + ", siteUrl=" + siteUrl + ", isHidden=" + isHidden
				+ ", cross=" + cross + ", crossId=" + crossId + ", content="
				+ content + ", smallTypes=" + smallTypes + "]";
	}

}
