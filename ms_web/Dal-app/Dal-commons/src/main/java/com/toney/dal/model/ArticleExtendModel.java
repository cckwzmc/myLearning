package com.toney.dal.model;


/**
 * @author toney.li
 * 文章model扩展类，主要是用于经常变化的一些数据
 */
public class ArticleExtendModel extends BaseObject{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3277587455924423247L;
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
		result = prime * result + ((badPostTotal == null) ? 0 : badPostTotal.hashCode());
		result = prime * result + ((clickTotal == null) ? 0 : clickTotal.hashCode());
		result = prime * result + ((goodPostTotal == null) ? 0 : goodPostTotal.hashCode());
		result = prime * result + ((lastPost == null) ? 0 : lastPost.hashCode());
		result = prime * result + ((notPostTotal == null) ? 0 : notPostTotal.hashCode());
		result = prime * result + ((scores == null) ? 0 : scores.hashCode());
		result = prime * result + ((voteId == null) ? 0 : voteId.hashCode());
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
		ArticleExtendModel other = (ArticleExtendModel) obj;
		if (badPostTotal == null) {
			if (other.badPostTotal != null)
				return false;
		} else if (!badPostTotal.equals(other.badPostTotal))
			return false;
		if (clickTotal == null) {
			if (other.clickTotal != null)
				return false;
		} else if (!clickTotal.equals(other.clickTotal))
			return false;
		if (goodPostTotal == null) {
			if (other.goodPostTotal != null)
				return false;
		} else if (!goodPostTotal.equals(other.goodPostTotal))
			return false;
		if (lastPost == null) {
			if (other.lastPost != null)
				return false;
		} else if (!lastPost.equals(other.lastPost))
			return false;
		if (notPostTotal == null) {
			if (other.notPostTotal != null)
				return false;
		} else if (!notPostTotal.equals(other.notPostTotal))
			return false;
		if (scores == null) {
			if (other.scores != null)
				return false;
		} else if (!scores.equals(other.scores))
			return false;
		if (voteId == null) {
			if (other.voteId != null)
				return false;
		} else if (!voteId.equals(other.voteId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ArticleExtendModel [lastPost=" + lastPost + ", scores="
				+ scores + ", goodPostTotal=" + goodPostTotal
				+ ", badPostTotal=" + badPostTotal + ", notPostTotal="
				+ notPostTotal + ", voteId=" + voteId + ", clickTotal="
				+ clickTotal + "]";
	}

}
