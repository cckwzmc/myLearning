package com.toney.dal.cms.model;

import com.toney.dal.base.BaseObject;

public class ArchivesStatModel extends BaseObject {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7558304953233187402L;
	private Long aid; 
	private Long click; 
	private Double scores; 
	private Long goodPost; 
	private Long badPost; 
	private Long lastPoster; 
	private Long lastPostDate; 

	public Long getAid() {
		return aid;
	}

	public void setAid(Long aid) {
		this.aid = aid;
	}

	public Long getClick() {
		return click;
	}

	public void setClick(Long click) {
		this.click = click;
	}

	public Double getScores() {
		return scores;
	}

	public void setScores(Double scores) {
		this.scores = scores;
	}

	public Long getGoodPost() {
		return goodPost;
	}

	public void setGoodPost(Long goodPost) {
		this.goodPost = goodPost;
	}

	public Long getBadPost() {
		return badPost;
	}

	public void setBadPost(Long badPost) {
		this.badPost = badPost;
	}

	public Long getLastPoster() {
		return lastPoster;
	}

	public void setLastPoster(Long lastPoster) {
		this.lastPoster = lastPoster;
	}

	public Long getLastPostDate() {
		return lastPostDate;
	}

	public void setLastPostDate(Long lastPostDate) {
		this.lastPostDate = lastPostDate;
	}

	@Override
	public String toString() {
		return "ArchivesStatModel [aid=" + aid + ", click=" + click + ", scores=" + scores + ", goodPost=" + goodPost + ", badPost=" + badPost + ", lastPoster=" + lastPoster
				+ ", lastPostDate=" + lastPostDate + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((aid == null) ? 0 : aid.hashCode());
		result = prime * result + ((badPost == null) ? 0 : badPost.hashCode());
		result = prime * result + ((click == null) ? 0 : click.hashCode());
		result = prime * result + ((goodPost == null) ? 0 : goodPost.hashCode());
		result = prime * result + ((lastPostDate == null) ? 0 : lastPostDate.hashCode());
		result = prime * result + ((lastPoster == null) ? 0 : lastPoster.hashCode());
		result = prime * result + ((scores == null) ? 0 : scores.hashCode());
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
		ArchivesStatModel other = (ArchivesStatModel) obj;
		if (aid == null) {
			if (other.aid != null)
				return false;
		} else if (!aid.equals(other.aid))
			return false;
		if (badPost == null) {
			if (other.badPost != null)
				return false;
		} else if (!badPost.equals(other.badPost))
			return false;
		if (click == null) {
			if (other.click != null)
				return false;
		} else if (!click.equals(other.click))
			return false;
		if (goodPost == null) {
			if (other.goodPost != null)
				return false;
		} else if (!goodPost.equals(other.goodPost))
			return false;
		if (lastPostDate == null) {
			if (other.lastPostDate != null)
				return false;
		} else if (!lastPostDate.equals(other.lastPostDate))
			return false;
		if (lastPoster == null) {
			if (other.lastPoster != null)
				return false;
		} else if (!lastPoster.equals(other.lastPoster))
			return false;
		if (scores == null) {
			if (other.scores != null)
				return false;
		} else if (!scores.equals(other.scores))
			return false;
		return true;
	}

}
