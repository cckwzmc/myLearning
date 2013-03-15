package com.toney.istyle.form;

import java.util.Date;

import com.toney.istyle.commons.BaseObject;

/**
 *************************************************************** 
 * <p>
 * 
 * @CLASS :ProductForm
 * @DESCRIPTION :Web Form
 * @AUTHOR :toney.li
 * @VERSION :v1.0
 * @DATE :Mar 10, 2013
 *       </p>
 **************************************************************** 
 */
public class ProductForm extends BaseObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6441184400541427944L;

	private Long id;

	private String produceCode;

	private String platformCode;

	private String platformName;

	private String productName;

	private Short onsale;

	private String userName;

	private Date lastUpdate;

	private Short status;
	
	private String mainImage;

	/**
	 * 商品的URL
	 */
	private String productUrl;

	/**
	 * 类目
	 */
	private Integer catCode;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getProduceCode() {
		return produceCode;
	}

	public void setProduceCode(String produceCode) {
		this.produceCode = produceCode;
	}

	public String getPlatformCode() {
		return platformCode;
	}

	public void setPlatformCode(String platformCode) {
		this.platformCode = platformCode;
	}

	public String getPlatformName() {
		return platformName;
	}

	public void setPlatformName(String platformName) {
		this.platformName = platformName;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Short getOnsale() {
		return onsale;
	}

	public void setOnsale(Short onsale) {
		this.onsale = onsale;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Date getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(Date lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public Short getStatus() {
		return status;
	}

	public void setStatus(Short status) {
		this.status = status;
	}

	public String getProductUrl() {
		return productUrl;
	}

	public void setProductUrl(String productUrl) {
		this.productUrl = productUrl;
	}

	public Integer getCatCode() {
		return catCode;
	}

	public void setCatCode(Integer catCode) {
		this.catCode = catCode;
	}
	
	public String getMainImage() {
		return mainImage;
	}

	public void setMainImage(String mainImage) {
		this.mainImage = mainImage;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((catCode == null) ? 0 : catCode.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((lastUpdate == null) ? 0 : lastUpdate.hashCode());
		result = prime * result + ((mainImage == null) ? 0 : mainImage.hashCode());
		result = prime * result + ((onsale == null) ? 0 : onsale.hashCode());
		result = prime * result + ((platformCode == null) ? 0 : platformCode.hashCode());
		result = prime * result + ((platformName == null) ? 0 : platformName.hashCode());
		result = prime * result + ((produceCode == null) ? 0 : produceCode.hashCode());
		result = prime * result + ((productName == null) ? 0 : productName.hashCode());
		result = prime * result + ((productUrl == null) ? 0 : productUrl.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + ((userName == null) ? 0 : userName.hashCode());
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
		ProductForm other = (ProductForm) obj;
		if (catCode == null) {
			if (other.catCode != null)
				return false;
		} else if (!catCode.equals(other.catCode))
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
		if (mainImage == null) {
			if (other.mainImage != null)
				return false;
		} else if (!mainImage.equals(other.mainImage))
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
		if (platformName == null) {
			if (other.platformName != null)
				return false;
		} else if (!platformName.equals(other.platformName))
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
		if (userName == null) {
			if (other.userName != null)
				return false;
		} else if (!userName.equals(other.userName))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ProductForm [id=" + id + ", produceCode=" + produceCode + ", platformCode=" + platformCode + ", platformName=" + platformName + ", productName=" + productName
				+ ", onsale=" + onsale + ", userName=" + userName + ", lastUpdate=" + lastUpdate + ", status=" + status + ", mainImage=" + mainImage + ", productUrl=" + productUrl
				+ ", catCode=" + catCode + "]";
	}

}
