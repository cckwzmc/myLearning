package com.toney.crawler.collection.model;

import com.toney.crawler.common.model.BaseObject;

/**
 * @author toney.li<br/>
 *         dede_crawler_listtask
 */
public class CrawlerListTaskModel extends BaseObject {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4144413310382261563L;
	private Integer cid;
	private Long clid;
	private String url;
	private String title;
	private Long createDate;
	private Integer status;
	private Integer isEnable;
	public Integer getCid() {
		return cid;
	}
	public void setCid(Integer cid) {
		this.cid = cid;
	}
	public Long getClid() {
		return clid;
	}
	public void setClid(Long clid) {
		this.clid = clid;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
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
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getIsEnable() {
		return isEnable;
	}
	public void setIsEnable(Integer isEnable) {
		this.isEnable = isEnable;
	}
	@Override
	public String toString() {
		return "CrawlerListTaskModel [cid=" + cid + ", clid=" + clid + ", url=" + url + ", title=" + title + ", createDate=" + createDate
				+ ", status=" + status + ", isEnable=" + isEnable + "]";
	}

}
