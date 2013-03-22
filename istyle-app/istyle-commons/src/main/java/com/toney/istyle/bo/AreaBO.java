package com.toney.istyle.bo;

import java.io.Serializable;

public class AreaBO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9106490996109366188L;
	private Integer id;
	private Integer parentId;
	private String name;
	private Integer sequence;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getSequence() {
		return sequence;
	}

	public void setSequence(Integer sequence) {
		this.sequence = sequence;
	}

}
