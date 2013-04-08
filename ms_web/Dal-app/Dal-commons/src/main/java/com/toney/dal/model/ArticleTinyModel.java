package com.toney.dal.model;

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
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((arcRank == null) ? 0 : arcRank.hashCode());
		result = prime * result + ((channelId == null) ? 0 : channelId.hashCode());
		result = prime * result + ((createDate == null) ? 0 : createDate.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((mid == null) ? 0 : mid.hashCode());
		result = prime * result + ((sortRank == null) ? 0 : sortRank.hashCode());
		result = prime * result + ((typeId == null) ? 0 : typeId.hashCode());
		result = prime * result + ((typeId2 == null) ? 0 : typeId2.hashCode());
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
		ArticleTinyModel other = (ArticleTinyModel) obj;
		if (arcRank == null) {
			if (other.arcRank != null)
				return false;
		} else if (!arcRank.equals(other.arcRank))
			return false;
		if (channelId == null) {
			if (other.channelId != null)
				return false;
		} else if (!channelId.equals(other.channelId))
			return false;
		if (createDate == null) {
			if (other.createDate != null)
				return false;
		} else if (!createDate.equals(other.createDate))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (mid == null) {
			if (other.mid != null)
				return false;
		} else if (!mid.equals(other.mid))
			return false;
		if (sortRank == null) {
			if (other.sortRank != null)
				return false;
		} else if (!sortRank.equals(other.sortRank))
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
		return true;
	}
	@Override
	public String toString() {
		return "ArticleTinyModel [id=" + id + ", typeId=" + typeId
				+ ", typeId2=" + typeId2 + ", arcRank=" + arcRank
				+ ", channelId=" + channelId + ", createDate=" + createDate
				+ ", sortRank=" + sortRank + ", mid=" + mid + "]";
	}  

}
