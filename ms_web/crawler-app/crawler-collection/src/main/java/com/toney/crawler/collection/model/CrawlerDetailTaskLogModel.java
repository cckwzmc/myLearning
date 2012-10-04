package com.toney.crawler.collection.model;

import com.toney.crawler.common.model.BaseObject;

/**
 * @author toney.li<br/>
 *         dede_crawler_dttask_log
 */
public class CrawlerDetailTaskLogModel extends BaseObject {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4754807332241938211L;
	private Long did;
	private Long cdid;
	private String result;
	private Long createDate;
	public Long getDid() {
		return did;
	}
	public void setDid(Long did) {
		this.did = did;
	}
	public Long getCdid() {
		return cdid;
	}
	public void setCdid(Long cdid) {
		this.cdid = cdid;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public Long getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Long createDate) {
		this.createDate = createDate;
	}
	@Override
	public String toString() {
		return "CrawlerDetailTaskLogModel [did=" + did + ", cdid=" + cdid + ", result=" + result + ", createDate=" + createDate + "]";
	}

}
