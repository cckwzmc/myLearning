package com.toney.dal.model;

import java.io.Serializable;

/**
 * @author toney.li
 * 用于 article 查询bean
 */
public class ArticleQueryModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6961235131147766724L;
	
	/**
	 * 排序条件
	 */
	private String orderBy;
	
	/**
	 * 查询的行数
	 */
	private Integer row;
	/**
	 * 栏目ids.1,2,34,5
	 */
	private String channelIds;
	/**
	 * 文章标签
	 */
	private String arctag;
	
	public String getOrderBy() {
		return orderBy;
	}
	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}
	public Integer getRow() {
		return row;
	}
	public void setRow(Integer row) {
		this.row = row;
	}
	public String getChannelIds() {
		return channelIds;
	}
	public void setChannelIds(String channelIds) {
		this.channelIds = channelIds;
	}
	public String getArctag() {
		return arctag;
	}
	public void setArctag(String arctag) {
		this.arctag = arctag;
	}
	

}
