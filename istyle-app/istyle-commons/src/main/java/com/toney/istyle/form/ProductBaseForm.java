package com.toney.istyle.form;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

/**
 *************************************************************** 
 * <p>
 * 
 * @CLASS :ProductBaseForm.java
 * @DESCRIPTION : 商品基本信息
 * @AUTHOR :toney.li
 * @VERSION :v1.0
 * @DATE :Apr 7, 2013
 *       </p>
 **************************************************************** 
 */
public class ProductBaseForm implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6343830591458042014L;

	private Long productId;
	private String productCode;
	@NotBlank(message = "商品名不能为空") 
	@Length(min=4,max=50,message="商品名称必须在4～50个字符之间")
	private String productName;
	@NotNull(message = "平台商不能为空")
	private String platFormCode;
	@NotNull(message = "类目不能为空")
	private String catCode;
	private int cityId;
	private String urlDetail;
	private Long uploadUid;

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getPlatFormCode() {
		return platFormCode;
	}

	public void setPlatFormCode(String platFormCode) {
		this.platFormCode = platFormCode;
	}

	public String getCatCode() {
		return catCode;
	}

	public void setCatCode(String catCode) {
		this.catCode = catCode;
	}

	public int getCityId() {
		return cityId;
	}

	public void setCityId(int cityId) {
		this.cityId = cityId;
	}

	public String getUrlDetail() {
		return urlDetail;
	}

	public void setUrlDetail(String urlDetail) {
		this.urlDetail = urlDetail;
	}

	public Long getUploadUid() {
		return uploadUid;
	}

	public void setUploadUid(Long uploadUid) {
		this.uploadUid = uploadUid;
	}

}
