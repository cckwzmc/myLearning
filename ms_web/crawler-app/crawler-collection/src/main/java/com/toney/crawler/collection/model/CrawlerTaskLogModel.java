package com.toney.crawler.collection.model;

import com.toney.crawler.common.model.BaseObject;

/**
 * @author toney.li<br/>
 *         dede_crawler_task_log
 */
public class CrawlerTaskLogModel extends BaseObject {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1198499186464734809L;
	private Long id;
	private Integer cid;
	private String title;
	private Long createDate;
	private String result;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Integer getCid() {
		return cid;
	}
	public void setCid(Integer cid) {
		this.cid = cid;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
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
		return "CrawlerTaskLogModel [id=" + id + ", cid=" + cid + ", title=" + title + ", createDate=" + createDate + ", result=" + result
				+ "]";
	}

}
