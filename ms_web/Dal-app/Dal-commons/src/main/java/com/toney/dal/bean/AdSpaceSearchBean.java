package com.toney.dal.bean;

import com.toney.dal.base.BaseObject;

/**
  * @ClassName: AdSpaceSearchBean
  * @Description: 广告位查询
  * @author toney.li
  * @date 2013-7-3 上午10:23:30
  *
  */
public class AdSpaceSearchBean extends BaseObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5551872916235784029L;

	private String title;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public String toString() {
		return "AdSpaceSearchBean [title=" + title + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((title == null) ? 0 : title.hashCode());
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
		AdSpaceSearchBean other = (AdSpaceSearchBean) obj;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}

}
