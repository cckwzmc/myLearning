package com.toney.crawler.collection.model;

import com.toney.crawler.common.model.BaseObject;

/**
 * @author toney.li<br/>
 *         dede_crawler_listtask_log
 */
public class CrawlerListTaskLogModel extends BaseObject {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2367182491895670999L;
	private Long id;
	private Long clid;
	private Long createDate;
	private String result;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getClid() {
		return clid;
	}
	public void setClid(Long clid) {
		this.clid = clid;
	}
	public Long getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Long createDate) {
		this.createDate = createDate;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	@Override
	public String toString() {
		return "CrawlerListTaskLogModel [id=" + id + ", clid=" + clid + ", createDate=" + createDate + ", result=" + result + "]";
	}

}
