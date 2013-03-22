/**
 * 
 */
package com.toney.istyle.bo;

import java.io.Serializable;
import java.util.Date;

/**
 *************************************************************** 
 * <p>
 * 
 * @CLASS :ProductBO.java
 * @DESCRIPTION :商品业务层实体类
 * @AUTHOR :ly_zy_ljh
 * @VERSION :v1.0
 * @DATE :2013-3-19
 *       </p>
 **************************************************************** 
 */
public class ProductBO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4892623931421592724L;

	private Long id;

	private String produceCode;

	private String platformCode;

	private String productName;

	private Short onsale;
	private Long uploadUid;

	private Date lastUpdate;

	private Date createDate;

	private Short status;

	private String description;
	private Integer cityId;

	/**
	 * 商品的URL
	 */
	private String productUrl;

	/**
	 * 类目
	 */
	private Integer catCode;
	

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getCityId() {
		return cityId;
	}

	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the produceCode
	 */
	public String getProduceCode() {
		return produceCode;
	}

	/**
	 * @param produceCode
	 *            the produceCode to set
	 */
	public void setProduceCode(String produceCode) {
		this.produceCode = produceCode;
	}

	/**
	 * @return the platformCode
	 */
	public String getPlatformCode() {
		return platformCode;
	}

	/**
	 * @param platformCode
	 *            the platformCode to set
	 */
	public void setPlatformCode(String platformCode) {
		this.platformCode = platformCode;
	}

	/**
	 * @return the productName
	 */
	public String getProductName() {
		return productName;
	}

	/**
	 * @param productName
	 *            the productName to set
	 */
	public void setProductName(String productName) {
		this.productName = productName;
	}

	/**
	 * @return the onsale
	 */
	public Short getOnsale() {
		return onsale;
	}

	/**
	 * @param onsale
	 *            the onsale to set
	 */
	public void setOnsale(Short onsale) {
		this.onsale = onsale;
	}

	/**
	 * @return the uploadUid
	 */
	public Long getUploadUid() {
		return uploadUid;
	}

	/**
	 * @param uploadUid
	 *            the uploadUid to set
	 */
	public void setUploadUid(Long uploadUid) {
		this.uploadUid = uploadUid;
	}

	/**
	 * @return the lastUpdate
	 */
	public Date getLastUpdate() {
		return lastUpdate;
	}

	/**
	 * @param lastUpdate
	 *            the lastUpdate to set
	 */
	public void setLastUpdate(Date lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	/**
	 * @return the createDate
	 */
	public Date getCreateDate() {
		return createDate;
	}

	/**
	 * @param createDate
	 *            the createDate to set
	 */
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	/**
	 * @return the status
	 */
	public Short getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(Short status) {
		this.status = status;
	}

	/**
	 * @return the productUrl
	 */
	public String getProductUrl() {
		return productUrl;
	}

	/**
	 * @param productUrl
	 *            the productUrl to set
	 */
	public void setProductUrl(String productUrl) {
		this.productUrl = productUrl;
	}

	/**
	 * @return the catCode
	 */
	public Integer getCatCode() {
		return catCode;
	}

	/**
	 * @param catCode
	 *            the catCode to set
	 */
	public void setCatCode(Integer catCode) {
		this.catCode = catCode;
	}

	@Override
	public String toString() {
		return "ProductBO [id=" + id + ", produceCode=" + produceCode + ", platformCode=" + platformCode + ", productName=" + productName + ", onsale=" + onsale + ", uploadUid="
				+ uploadUid + ", lastUpdate=" + lastUpdate + ", createDate=" + createDate + ", status=" + status + ", description=" + description + ", cityId=" + cityId
				+ ", productUrl=" + productUrl + ", catCode=" + catCode + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((catCode == null) ? 0 : catCode.hashCode());
		result = prime * result + ((cityId == null) ? 0 : cityId.hashCode());
		result = prime * result + ((createDate == null) ? 0 : createDate.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((lastUpdate == null) ? 0 : lastUpdate.hashCode());
		result = prime * result + ((onsale == null) ? 0 : onsale.hashCode());
		result = prime * result + ((platformCode == null) ? 0 : platformCode.hashCode());
		result = prime * result + ((produceCode == null) ? 0 : produceCode.hashCode());
		result = prime * result + ((productName == null) ? 0 : productName.hashCode());
		result = prime * result + ((productUrl == null) ? 0 : productUrl.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + ((uploadUid == null) ? 0 : uploadUid.hashCode());
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
		ProductBO other = (ProductBO) obj;
		if (catCode == null) {
			if (other.catCode != null)
				return false;
		} else if (!catCode.equals(other.catCode))
			return false;
		if (cityId == null) {
			if (other.cityId != null)
				return false;
		} else if (!cityId.equals(other.cityId))
			return false;
		if (createDate == null) {
			if (other.createDate != null)
				return false;
		} else if (!createDate.equals(other.createDate))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (lastUpdate == null) {
			if (other.lastUpdate != null)
				return false;
		} else if (!lastUpdate.equals(other.lastUpdate))
			return false;
		if (onsale == null) {
			if (other.onsale != null)
				return false;
		} else if (!onsale.equals(other.onsale))
			return false;
		if (platformCode == null) {
			if (other.platformCode != null)
				return false;
		} else if (!platformCode.equals(other.platformCode))
			return false;
		if (produceCode == null) {
			if (other.produceCode != null)
				return false;
		} else if (!produceCode.equals(other.produceCode))
			return false;
		if (productName == null) {
			if (other.productName != null)
				return false;
		} else if (!productName.equals(other.productName))
			return false;
		if (productUrl == null) {
			if (other.productUrl != null)
				return false;
		} else if (!productUrl.equals(other.productUrl))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		if (uploadUid == null) {
			if (other.uploadUid != null)
				return false;
		} else if (!uploadUid.equals(other.uploadUid))
			return false;
		return true;
	}

}
