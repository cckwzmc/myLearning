package com.toney.istyle.module;

import java.util.Date;

import com.toney.istyle.commons.BaseObject;

/**
 * 
 *************************************************************** 
 * <p>
 * 
 * @CLASS :IstylePlatformModule
 * @DESCRIPTION :平台商信息
 * @AUTHOR :toney.li
 * @VERSION :v1.0
 * @DATE :Mar 10, 2013
 *       </p>
 **************************************************************** 
 */
public class PlatformModule extends BaseObject {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5201007836896482302L;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column T_PLATFORM.ID
	 * 
	 */
	private Integer id;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column T_PLATFORM.PLATFORM_CODE
	 * 
	 */
	private String platformCode;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column T_PLATFORM.PLATFORM_NAME
	 * 
	 */
	private String platformName;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column T_PLATFORM.CREATE_DATE
	 * 
	 */
	private Date createDate;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column T_PLATFORM.OFFICE_WEBSITE
	 * 
	 */
	private String officeWebsite;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column T_PLATFORM.ENABLED
	 * 
	 */
	private Short enabled;

	/**
	 * This method was generated by MyBatis Generator. This method returns the
	 * value of the database column T_PLATFORM.ID
	 * 
	 * @return the value of T_PLATFORM.ID
	 * 
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column T_PLATFORM.ID
	 * 
	 * @param id
	 *            the value for T_PLATFORM.ID
	 * 
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the
	 * value of the database column T_PLATFORM.PLATFORM_CODE
	 * 
	 * @return the value of T_PLATFORM.PLATFORM_CODE
	 * 
	 */
	public String getPlatformCode() {
		return platformCode;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column T_PLATFORM.PLATFORM_CODE
	 * 
	 * @param platformCode
	 *            the value for T_PLATFORM.PLATFORM_CODE
	 * 
	 */
	public void setPlatformCode(String platformCode) {
		this.platformCode = platformCode;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the
	 * value of the database column T_PLATFORM.PLATFORM_NAME
	 * 
	 * @return the value of T_PLATFORM.PLATFORM_NAME
	 * 
	 */
	public String getPlatformName() {
		return platformName;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column T_PLATFORM.PLATFORM_NAME
	 * 
	 * @param platformName
	 *            the value for T_PLATFORM.PLATFORM_NAME
	 * 
	 */
	public void setPlatformName(String platformName) {
		this.platformName = platformName;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the
	 * value of the database column T_PLATFORM.CREATE_DATE
	 * 
	 * @return the value of T_PLATFORM.CREATE_DATE
	 * 
	 */
	public Date getCreateDate() {
		return createDate;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column T_PLATFORM.CREATE_DATE
	 * 
	 * @param createDate
	 *            the value for T_PLATFORM.CREATE_DATE
	 * 
	 */
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the
	 * value of the database column T_PLATFORM.OFFICE_WEBSITE
	 * 
	 * @return the value of T_PLATFORM.OFFICE_WEBSITE
	 * 
	 */
	public String getOfficeWebsite() {
		return officeWebsite;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column T_PLATFORM.OFFICE_WEBSITE
	 * 
	 * @param officeWebsite
	 *            the value for T_PLATFORM.OFFICE_WEBSITE
	 * 
	 * 
	 */
	public void setOfficeWebsite(String officeWebsite) {
		this.officeWebsite = officeWebsite;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the
	 * value of the database column T_PLATFORM.ENABLED
	 * 
	 * @return the value of T_PLATFORM.ENABLED
	 * 
	 * 
	 */
	public Short getEnabled() {
		return enabled;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column T_PLATFORM.ENABLED
	 * 
	 * @param enabled
	 *            the value for T_PLATFORM.ENABLED
	 * 
	 * 
	 */
	public void setEnabled(Short enabled) {
		this.enabled = enabled;
	}

	@Override
	public String toString() {
		return "PlatformModule [id=" + id + ", platformCode=" + platformCode + ", platformName=" + platformName + ", createDate=" + createDate + ", officeWebsite=" + officeWebsite
				+ ", enabled=" + enabled + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((createDate == null) ? 0 : createDate.hashCode());
		result = prime * result + ((enabled == null) ? 0 : enabled.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((officeWebsite == null) ? 0 : officeWebsite.hashCode());
		result = prime * result + ((platformCode == null) ? 0 : platformCode.hashCode());
		result = prime * result + ((platformName == null) ? 0 : platformName.hashCode());
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
		PlatformModule other = (PlatformModule) obj;
		if (createDate == null) {
			if (other.createDate != null)
				return false;
		} else if (!createDate.equals(other.createDate))
			return false;
		if (enabled == null) {
			if (other.enabled != null)
				return false;
		} else if (!enabled.equals(other.enabled))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (officeWebsite == null) {
			if (other.officeWebsite != null)
				return false;
		} else if (!officeWebsite.equals(other.officeWebsite))
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
		return true;
	}

}