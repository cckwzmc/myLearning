package com.toney.core.model;

public class ArticleModel extends BaseObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3893473101310195928L;

	private Long id;

	/**
	 * 一级频道
	 */
	private Integer typeId;

	/**
	 * 二级频道.
	 */
	private String typeId2;

	private Integer sortRank;
	/**
	 * 系统标签缩写
	 */
	private String flag;
	/**
	 * 是否隐藏
	 */
	private Integer ismake;
	/**
	 * 频道ID
	 */
	private Integer channel;
	private Integer arcRank;
	/**
	 * 看该文章需要的钱，以分为单位。
	 */
	private Long payMoney;

	/**
	 * 文章标题
	 */
	private String title;
	/**
	 * 文章小标题(由大标题截取)
	 */
	private String shorttitle;
	private String color;
	private String author;
	/**
	 * 文章来源
	 */
	private String source;

	/**
	 * 缩略图
	 */
	private String litpic;
	/**
	 * 发布时间
	 */
	private Long pubDate;
	/**
	 * 创建时间
	 */
	private Long createDate;
	/**
	 * memberId创建用户ID
	 */
	private Long mid;
	private String keywords;

	private String description;
	private String filename;
	private Long dutyadmin;
	private Integer tackid;
	private Long mtype;
	private Integer weight;
	/**
	 * 最后发帖时间
	 */
	private Long lastPost;

	private Long scores;
	/**
	 * 投好贴数量
	 */
	private Long goodPostTotal;
	/**
	 * 投坏贴数量
	 */
	private Long badPostTotal;
	private Long notPostTotal;
	private Long voteId;
	/**
	 * 点击数量.
	 */
	private Long clickTotal;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getTypeId() {
		return typeId;
	}

	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}

	public String getTypeId2() {
		return typeId2;
	}

	public void setTypeId2(String typeId2) {
		this.typeId2 = typeId2;
	}

	public Integer getSortRank() {
		return sortRank;
	}

	public void setSortRank(Integer sortRank) {
		this.sortRank = sortRank;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public Integer getIsmake() {
		return ismake;
	}

	public void setIsmake(Integer ismake) {
		this.ismake = ismake;
	}

	public Integer getChannel() {
		return channel;
	}

	public void setChannel(Integer channel) {
		this.channel = channel;
	}

	public Integer getArcRank() {
		return arcRank;
	}

	public void setArcRank(Integer arcRank) {
		this.arcRank = arcRank;
	}

	public Long getPayMoney() {
		return payMoney;
	}

	public void setPayMoney(Long payMoney) {
		this.payMoney = payMoney;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getShorttitle() {
		return shorttitle;
	}

	public void setShorttitle(String shorttitle) {
		this.shorttitle = shorttitle;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
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

	public String getLitpic() {
		return litpic;
	}

	public void setLitpic(String litpic) {
		this.litpic = litpic;
	}

	public Long getPubDate() {
		return pubDate;
	}

	public void setPubDate(Long pubDate) {
		this.pubDate = pubDate;
	}

	public Long getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Long createDate) {
		this.createDate = createDate;
	}

	public Long getMid() {
		return mid;
	}

	public void setMid(Long mid) {
		this.mid = mid;
	}

	public String getKeywords() {
		return keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public Long getDutyadmin() {
		return dutyadmin;
	}

	public void setDutyadmin(Long dutyadmin) {
		this.dutyadmin = dutyadmin;
	}

	public Integer getTackid() {
		return tackid;
	}

	public void setTackid(Integer tackid) {
		this.tackid = tackid;
	}

	public Long getMtype() {
		return mtype;
	}

	public void setMtype(Long mtype) {
		this.mtype = mtype;
	}

	public Integer getWeight() {
		return weight;
	}

	public void setWeight(Integer weight) {
		this.weight = weight;
	}

	public Long getLastPost() {
		return lastPost;
	}

	public void setLastPost(Long lastPost) {
		this.lastPost = lastPost;
	}

	public Long getScores() {
		return scores;
	}

	public void setScores(Long scores) {
		this.scores = scores;
	}

	public Long getGoodPostTotal() {
		return goodPostTotal;
	}

	public void setGoodPostTotal(Long goodPostTotal) {
		this.goodPostTotal = goodPostTotal;
	}

	public Long getBadPostTotal() {
		return badPostTotal;
	}

	public void setBadPostTotal(Long badPostTotal) {
		this.badPostTotal = badPostTotal;
	}

	public Long getNotPostTotal() {
		return notPostTotal;
	}

	public void setNotPostTotal(Long notPostTotal) {
		this.notPostTotal = notPostTotal;
	}

	public Long getVoteId() {
		return voteId;
	}

	public void setVoteId(Long voteId) {
		this.voteId = voteId;
	}

	public Long getClickTotal() {
		return clickTotal;
	}

	public void setClickTotal(Long clickTotal) {
		this.clickTotal = clickTotal;
	}

	@Override
	public String toString() {
		return "ArticleModel [id=" + id + ", typeId=" + typeId + ", typeId2=" + typeId2 + ", sortRank=" + sortRank + ", flag=" + flag
				+ ", ismake=" + ismake + ", channel=" + channel + ", arcRank=" + arcRank + ", payMoney=" + payMoney + ", title=" + title
				+ ", shorttitle=" + shorttitle + ", color=" + color + ", author=" + author + ", source=" + source + ", litpic=" + litpic
				+ ", pubDate=" + pubDate + ", createDate=" + createDate + ", mid=" + mid + ", keywords=" + keywords + ", description="
				+ description + ", filename=" + filename + ", dutyadmin=" + dutyadmin + ", tackid=" + tackid + ", mtype=" + mtype
				+ ", weight=" + weight + ", lastPost=" + lastPost + ", scores=" + scores + ", goodPostTotal=" + goodPostTotal
				+ ", badPostTotal=" + badPostTotal + ", notPostTotal=" + notPostTotal + ", voteId=" + voteId + ", clickTotal=" + clickTotal
				+ "]";
	}

}
