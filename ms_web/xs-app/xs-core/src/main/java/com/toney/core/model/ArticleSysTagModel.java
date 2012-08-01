package com.toney.core.model;

/**
 * @author toney.li
 *
 */
public class ArticleSysTagModel extends BaseObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7103579703765533997L;

	private int sortId;

	/**
	 * 标签缩写
	 */
	private String att;
	/**
	 * 标签名称
	 */
	private String attName;


	public String getAtt() {
		return att;
	}

	public void setAtt(String att) {
		this.att = att;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((att == null) ? 0 : att.hashCode());
		result = prime * result + ((attName == null) ? 0 : attName.hashCode());
		result = prime * result + sortId;
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
		ArticleSysTagModel other = (ArticleSysTagModel) obj;
		if (att == null) {
			if (other.att != null)
				return false;
		} else if (!att.equals(other.att))
			return false;
		if (attName == null) {
			if (other.attName != null)
				return false;
		} else if (!attName.equals(other.attName))
			return false;
		if (sortId != other.sortId)
			return false;
		return true;
	}

	public int getSortId() {
		return sortId;
	}

	public void setSortId(int sortId) {
		this.sortId = sortId;
	}

	public String getAttName() {
		return attName;
	}

	public void setAttName(String attName) {
		this.attName = attName;
	}

	@Override
	public String toString() {
		return "ArticleSysTagModel [sortId=" + sortId + ", att=" + att
				+ ", attName=" + attName + "]";
	}
}
