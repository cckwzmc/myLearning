package com.toney.core.model;

/**
 * @author toney.li
 * 文章简表
 */
public class ArticleTinyModel extends BaseObject{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1580493763305454080L;
	private Long id;         
	private Integer typeId;         
	private String typeId2;         
	private Integer arcRank;         
	private Integer channelId;         
	private Long createDate;         
	private Long sortRank;         
	private Long mid;
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

	public Integer getArcRank() {
		return arcRank;
	}
	public void setArcRank(Integer arcRank) {
		this.arcRank = arcRank;
	}
	public Integer getChannelId() {
		return channelId;
	}
	public void setChannelId(Integer channelId) {
		this.channelId = channelId;
	}
	public Long getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Long createDate) {
		this.createDate = createDate;
	}
	public Long getSortRank() {
		return sortRank;
	}
	public void setSortRank(Long sortRank) {
		this.sortRank = sortRank;
	}
	public Long getMid() {
		return mid;
	}
	public void setMid(Long mid) {
		this.mid = mid;
	}
	@Override
	public String toString() {
		return "ArticleTinyModel [id=" + id + ", typeId=" + typeId
				+ ", typeId2=" + typeId2 + ", arcRank=" + arcRank
				+ ", channelId=" + channelId + ", createDate=" + createDate
				+ ", sortRank=" + sortRank + ", mid=" + mid + "]";
	}  

}
