package com.toney.istyle.form;

import java.util.Date;
import java.util.List;

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

	private String lastUpdate;
	private String description;
	private Short status;
	private String cityName;
	private List<ProductPicForm> productPicForm;
	private ProductStatForm productStatForm;

	/**
	 * 商品的URL
	 */
	private String productUrl;

	/**
	 * 类目
	 */
	private CategoryForm categoryForm;

	public Long getId() {
		return id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
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

	public String getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(String lastUpdate) {
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

	public CategoryForm getCategoryForm() {
		return categoryForm;
	}

	public void setCategoryForm(CategoryForm categoryForm) {
		this.categoryForm = categoryForm;
	}

	public List<ProductPicForm> getProductPicForm() {
		return productPicForm;
	}

	public void setProductPicForm(List<ProductPicForm> productPicForm) {
		this.productPicForm = productPicForm;
	}

	public ProductStatForm getProductStatForm() {
		return productStatForm;
	}

	public void setProductStatForm(ProductStatForm productStatForm) {
		this.productStatForm = productStatForm;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((categoryForm == null) ? 0 : categoryForm.hashCode());
		result = prime * result + ((cityName == null) ? 0 : cityName.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((lastUpdate == null) ? 0 : lastUpdate.hashCode());
		result = prime * result + ((onsale == null) ? 0 : onsale.hashCode());
		result = prime * result + ((platformCode == null) ? 0 : platformCode.hashCode());
		result = prime * result + ((platformName == null) ? 0 : platformName.hashCode());
		result = prime * result + ((produceCode == null) ? 0 : produceCode.hashCode());
		result = prime * result + ((productName == null) ? 0 : productName.hashCode());
		result = prime * result + ((productPicForm == null) ? 0 : productPicForm.hashCode());
		result = prime * result + ((productStatForm == null) ? 0 : productStatForm.hashCode());
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
		if (categoryForm == null) {
			if (other.categoryForm != null)
				return false;
		} else if (!categoryForm.equals(other.categoryForm))
			return false;
		if (cityName == null) {
			if (other.cityName != null)
				return false;
		} else if (!cityName.equals(other.cityName))
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
		if (productPicForm == null) {
			if (other.productPicForm != null)
				return false;
		} else if (!productPicForm.equals(other.productPicForm))
			return false;
		if (productStatForm == null) {
			if (other.productStatForm != null)
				return false;
		} else if (!productStatForm.equals(other.productStatForm))
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
				+ ", onsale=" + onsale + ", userName=" + userName + ", lastUpdate=" + lastUpdate + ", description=" + description + ", status=" + status + ", cityName=" + cityName
				+ ", productPicForm=" + productPicForm + ", productStatForm=" + productStatForm + ", productUrl=" + productUrl + ", categoryForm=" + categoryForm + "]";
	}

}
