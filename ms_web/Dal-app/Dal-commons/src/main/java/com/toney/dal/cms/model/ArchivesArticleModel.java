package com.toney.dal.cms.model;

import com.toney.dal.base.BaseObject;

public class ArchivesArticleModel extends BaseObject {
	/**
	 * 
	 */
	private static final long serialVersionUID = 516398107861910103L;
	private Long aid;
	private String CONTENT;

	public Long getAid() {
		return aid;
	}

	public void setAid(Long aid) {
		this.aid = aid;
	}

	public String getCONTENT() {
		return CONTENT;
	}

	public void setCONTENT(String cONTENT) {
		CONTENT = cONTENT;
	}

	@Override
	public String toString() {
		return "ArchivesArticleModel [aid=" + aid + ", CONTENT=" + CONTENT + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((CONTENT == null) ? 0 : CONTENT.hashCode());
		result = prime * result + ((aid == null) ? 0 : aid.hashCode());
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
		ArchivesArticleModel other = (ArchivesArticleModel) obj;
		if (CONTENT == null) {
			if (other.CONTENT != null)
				return false;
		} else if (!CONTENT.equals(other.CONTENT))
			return false;
		if (aid == null) {
			if (other.aid != null)
				return false;
		} else if (!aid.equals(other.aid))
			return false;
		return true;
	}

}
