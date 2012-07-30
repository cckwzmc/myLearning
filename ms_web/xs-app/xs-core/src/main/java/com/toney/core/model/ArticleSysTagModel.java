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

	private int sortid;

	/**
	 * 标签缩写
	 */
	private String att;
	/**
	 * 标签名称
	 */
	private String attname;

	public int getSortid() {
		return sortid;
	}

	public void setSortid(int sortid) {
		this.sortid = sortid;
	}

	public String getAtt() {
		return att;
	}

	public void setAtt(String att) {
		this.att = att;
	}

	public String getAttname() {
		return attname;
	}

	public void setAttname(String attname) {
		this.attname = attname;
	}

	@Override
	public String toString() {
		return "ArticleSysTagModel [sortid=" + sortid + ", att=" + att
				+ ", attname=" + attname + "]";
	}
}
