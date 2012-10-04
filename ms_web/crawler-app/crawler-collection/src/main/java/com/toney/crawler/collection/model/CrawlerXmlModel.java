package com.toney.crawler.collection.model;

import com.toney.crawler.common.model.BaseObject;

/**
 * @author toney.li<br/>
 *         dede_crawler_xml
 */
public class CrawlerXmlModel extends BaseObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8627019790259203795L;
	private Integer id;
	private Integer cid;
	private String type;
	private Long createDate;
	private String crawlerXml;
	private Integer isEnable;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCid() {
		return cid;
	}

	public void setCid(Integer cid) {
		this.cid = cid;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Long getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Long createDate) {
		this.createDate = createDate;
	}

	public String getCrawlerXml() {
		return crawlerXml;
	}

	public void setCrawlerXml(String crawlerXml) {
		this.crawlerXml = crawlerXml;
	}

	public Integer getIsEnable() {
		return isEnable;
	}

	public void setIsEnable(Integer isEnable) {
		this.isEnable = isEnable;
	}

	@Override
	public String toString() {
		return "CrawlerXmlModel [id=" + id + ", cid=" + cid + ", type=" + type + ", createDate=" + createDate + ", crawlerXml="
				+ crawlerXml + ", isEnable=" + isEnable + "]";
	}

}
