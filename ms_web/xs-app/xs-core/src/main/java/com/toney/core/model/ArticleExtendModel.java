package com.toney.core.model;


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
	public String toString() {
		return "ArticleExtendModel [lastPost=" + lastPost + ", scores="
				+ scores + ", goodPostTotal=" + goodPostTotal
				+ ", badPostTotal=" + badPostTotal + ", notPostTotal="
				+ notPostTotal + ", voteId=" + voteId + ", clickTotal="
				+ clickTotal + "]";
	}

}
