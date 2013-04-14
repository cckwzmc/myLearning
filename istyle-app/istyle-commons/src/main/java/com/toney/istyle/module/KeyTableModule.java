package com.toney.istyle.module;

import com.toney.istyle.commons.BaseObject;

public class KeyTableModule extends BaseObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5086224329477764160L;

	private String keyName;
	private Long keyValue;
	private String keyDescription;

	public String getKeyName() {
		return keyName;
	}

	public void setKeyName(String keyName) {
		this.keyName = keyName;
	}

	public Long getKeyValue() {
		return keyValue;
	}

	public void setKeyValue(Long keyValue) {
		this.keyValue = keyValue;
	}

	public String getKeyDescription() {
		return keyDescription;
	}

	public void setKeyDescription(String keyDescription) {
		this.keyDescription = keyDescription;
	}

	@Override
	public String toString() {
		return "KeyTableModule [keyName=" + keyName + ", keyValue=" + keyValue + ", keyDescription=" + keyDescription + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((keyDescription == null) ? 0 : keyDescription.hashCode());
		result = prime * result + ((keyName == null) ? 0 : keyName.hashCode());
		result = prime * result + ((keyValue == null) ? 0 : keyValue.hashCode());
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
		KeyTableModule other = (KeyTableModule) obj;
		if (keyDescription == null) {
			if (other.keyDescription != null)
				return false;
		} else if (!keyDescription.equals(other.keyDescription))
			return false;
		if (keyName == null) {
			if (other.keyName != null)
				return false;
		} else if (!keyName.equals(other.keyName))
			return false;
		if (keyValue == null) {
			if (other.keyValue != null)
				return false;
		} else if (!keyValue.equals(other.keyValue))
			return false;
		return true;
	}

}
