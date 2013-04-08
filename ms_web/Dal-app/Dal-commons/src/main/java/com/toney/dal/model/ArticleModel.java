package com.toney.dal.model;

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
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((arcRank == null) ? 0 : arcRank.hashCode());
		result = prime * result + ((author == null) ? 0 : author.hashCode());
		result = prime * result + ((badPostTotal == null) ? 0 : badPostTotal.hashCode());
		result = prime * result + ((channel == null) ? 0 : channel.hashCode());
		result = prime * result + ((clickTotal == null) ? 0 : clickTotal.hashCode());
		result = prime * result + ((color == null) ? 0 : color.hashCode());
		result = prime * result + ((createDate == null) ? 0 : createDate.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((dutyadmin == null) ? 0 : dutyadmin.hashCode());
		result = prime * result + ((filename == null) ? 0 : filename.hashCode());
		result = prime * result + ((flag == null) ? 0 : flag.hashCode());
		result = prime * result + ((goodPostTotal == null) ? 0 : goodPostTotal.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((ismake == null) ? 0 : ismake.hashCode());
		result = prime * result + ((keywords == null) ? 0 : keywords.hashCode());
		result = prime * result + ((lastPost == null) ? 0 : lastPost.hashCode());
		result = prime * result + ((litpic == null) ? 0 : litpic.hashCode());
		result = prime * result + ((mid == null) ? 0 : mid.hashCode());
		result = prime * result + ((mtype == null) ? 0 : mtype.hashCode());
		result = prime * result + ((notPostTotal == null) ? 0 : notPostTotal.hashCode());
		result = prime * result + ((payMoney == null) ? 0 : payMoney.hashCode());
		result = prime * result + ((pubDate == null) ? 0 : pubDate.hashCode());
		result = prime * result + ((scores == null) ? 0 : scores.hashCode());
		result = prime * result + ((shorttitle == null) ? 0 : shorttitle.hashCode());
		result = prime * result + ((sortRank == null) ? 0 : sortRank.hashCode());
		result = prime * result + ((source == null) ? 0 : source.hashCode());
		result = prime * result + ((tackid == null) ? 0 : tackid.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		result = prime * result + ((typeId == null) ? 0 : typeId.hashCode());
		result = prime * result + ((typeId2 == null) ? 0 : typeId2.hashCode());
		result = prime * result + ((voteId == null) ? 0 : voteId.hashCode());
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
		ArticleModel other = (ArticleModel) obj;
		if (arcRank == null) {
			if (other.arcRank != null)
				return false;
		} else if (!arcRank.equals(other.arcRank))
			return false;
		if (author == null) {
			if (other.author != null)
				return false;
		} else if (!author.equals(other.author))
			return false;
		if (badPostTotal == null) {
			if (other.badPostTotal != null)
				return false;
		} else if (!badPostTotal.equals(other.badPostTotal))
			return false;
		if (channel == null) {
			if (other.channel != null)
				return false;
		} else if (!channel.equals(other.channel))
			return false;
		if (clickTotal == null) {
			if (other.clickTotal != null)
				return false;
		} else if (!clickTotal.equals(other.clickTotal))
			return false;
		if (color == null) {
			if (other.color != null)
				return false;
		} else if (!color.equals(other.color))
			return false;
		if (createDate == null) {
			if (other.createDate != null)
				return false;
		} else if (!createDate.equals(other.createDate))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (dutyadmin == null) {
			if (other.dutyadmin != null)
				return false;
		} else if (!dutyadmin.equals(other.dutyadmin))
			return false;
		if (filename == null) {
			if (other.filename != null)
				return false;
		} else if (!filename.equals(other.filename))
			return false;
		if (flag == null) {
			if (other.flag != null)
				return false;
		} else if (!flag.equals(other.flag))
			return false;
		if (goodPostTotal == null) {
			if (other.goodPostTotal != null)
				return false;
		} else if (!goodPostTotal.equals(other.goodPostTotal))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (ismake == null) {
			if (other.ismake != null)
				return false;
		} else if (!ismake.equals(other.ismake))
			return false;
		if (keywords == null) {
			if (other.keywords != null)
				return false;
		} else if (!keywords.equals(other.keywords))
			return false;
		if (lastPost == null) {
			if (other.lastPost != null)
				return false;
		} else if (!lastPost.equals(other.lastPost))
			return false;
		if (litpic == null) {
			if (other.litpic != null)
				return false;
		} else if (!litpic.equals(other.litpic))
			return false;
		if (mid == null) {
			if (other.mid != null)
				return false;
		} else if (!mid.equals(other.mid))
			return false;
		if (mtype == null) {
			if (other.mtype != null)
				return false;
		} else if (!mtype.equals(other.mtype))
			return false;
		if (notPostTotal == null) {
			if (other.notPostTotal != null)
				return false;
		} else if (!notPostTotal.equals(other.notPostTotal))
			return false;
		if (payMoney == null) {
			if (other.payMoney != null)
				return false;
		} else if (!payMoney.equals(other.payMoney))
			return false;
		if (pubDate == null) {
			if (other.pubDate != null)
				return false;
		} else if (!pubDate.equals(other.pubDate))
			return false;
		if (scores == null) {
			if (other.scores != null)
				return false;
		} else if (!scores.equals(other.scores))
			return false;
		if (shorttitle == null) {
			if (other.shorttitle != null)
				return false;
		} else if (!shorttitle.equals(other.shorttitle))
			return false;
		if (sortRank == null) {
			if (other.sortRank != null)
				return false;
		} else if (!sortRank.equals(other.sortRank))
			return false;
		if (source == null) {
			if (other.source != null)
				return false;
		} else if (!source.equals(other.source))
			return false;
		if (tackid == null) {
			if (other.tackid != null)
				return false;
		} else if (!tackid.equals(other.tackid))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		if (typeId == null) {
			if (other.typeId != null)
				return false;
		} else if (!typeId.equals(other.typeId))
			return false;
		if (typeId2 == null) {
			if (other.typeId2 != null)
				return false;
		} else if (!typeId2.equals(other.typeId2))
			return false;
		if (voteId == null) {
			if (other.voteId != null)
				return false;
		} else if (!voteId.equals(other.voteId))
			return false;
		if (weight == null) {
			if (other.weight != null)
				return false;
		} else if (!weight.equals(other.weight))
			return false;
		return true;
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
