package com.toney.crawler.collection.model;

import com.toney.crawler.common.model.BaseObject;

/**
 * @author toney.li<br/>
 *         dede_crawler_task
 */
public class CrawlerTaskModel extends BaseObject {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2887714787710957262L;
	private Integer cid;
	private String siteName;
	private String url;
	private String description;
	private Long createDate;
	private String listWords;
	private String stopWords;
	private Integer isEnable;

	public Integer getCid() {
		return cid;
	}

	public void setCid(Integer cid) {
		this.cid = cid;
	}

	public String getSiteName() {
		return siteName;
	}

	public void setSiteName(String siteName) {
		this.siteName = siteName;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Long createDate) {
		this.createDate = createDate;
	}

	public String getListWords() {
		return listWords;
	}

	public void setListWords(String listWords) {
		this.listWords = listWords;
	}

	public String getStopWords() {
		return stopWords;
	}

	public void setStopWords(String stopWords) {
		this.stopWords = stopWords;
	}

	public Integer getIsEnable() {
		return isEnable;
	}

	public void setIsEnable(Integer isEnable) {
		this.isEnable = isEnable;
	}

	@Override
	public String toString() {
		return "CrawlerCollectionTaskModel [cid=" + cid + ", siteName=" + siteName + ", url=" + url + ", description=" + description
				+ ", createDate=" + createDate + ", listWords=" + listWords + ", stopWords=" + stopWords + ", isEnable=" + isEnable + "]";
	}
}
