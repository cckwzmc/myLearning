package com.toney.istyle.form;

import java.io.Serializable;
import java.util.List;

import com.toney.istyle.commons.LabelValue;

/**
 *************************************************************** 
 * <p>
 * 
 * @CLASS :SortForm.java
 * @DESCRIPTION : 排序查询,label/value id/desc,id/asc, etc.
 * @AUTHOR :toney.li
 * @VERSION :v1.0
 * @DATE :Mar 20, 2013
 *       </p>
 **************************************************************** 
 */
public class SortForm implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8094344232298588142L;

	private List<LabelValue> orderBy;

	public List<LabelValue> getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(List<LabelValue> orderBy) {
		this.orderBy = orderBy;
	}

}
