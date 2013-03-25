package com.toney.istyle.form;

import java.io.Serializable;
import java.util.Date;

/**
 *************************************************************** 
 * <p>
 * 
 * @CLASS :PlatFormForm.java
 * @DESCRIPTION : paltform view form
 * @AUTHOR :toney.li
 * @VERSION :v1.0
 * @DATE :Mar 25, 2013
 *       </p>
 **************************************************************** 
 */
public class PlatFormForm implements Serializable {

	private static final long serialVersionUID = -2834538809724168528L;
	private Integer id;

	private String platformCode;

	private String platformName;

	private Date createDate;

	private String officeWebsite;

	private Short enabled;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getOfficeWebsite() {
		return officeWebsite;
	}

	public void setOfficeWebsite(String officeWebsite) {
		this.officeWebsite = officeWebsite;
	}

	public Short getEnabled() {
		return enabled;
	}

	public void setEnabled(Short enabled) {
		this.enabled = enabled;
	}

}
