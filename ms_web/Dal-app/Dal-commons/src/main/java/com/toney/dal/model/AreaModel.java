package com.toney.dal.model;

import java.util.List;

public class AreaModel extends BaseObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3926478162746385584L;

	private List<AreaModel> childrens;
	private Long id;         
	private String name; 
	/**
	 * 第一排序
	 */
	private Integer reId;         
	/**
	 * 第二排序
	 */
	private Integer disOrder;    
	
	
	public List<AreaModel> getChildrens() {
		return childrens;
	}


	public void setChildrens(List<AreaModel> childrens) {
		this.childrens = childrens;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public Integer getReId() {
		return reId;
	}


	public void setReId(Integer reId) {
		this.reId = reId;
	}


	public Integer getDisOrder() {
		return disOrder;
	}


	public void setDisOrder(Integer disOrder) {
		this.disOrder = disOrder;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((childrens == null) ? 0 : childrens.hashCode());
		result = prime * result
				+ ((disOrder == null) ? 0 : disOrder.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((reId == null) ? 0 : reId.hashCode());
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
		AreaModel other = (AreaModel) obj;
		if (childrens == null) {
			if (other.childrens != null)
				return false;
		} else if (!childrens.equals(other.childrens))
			return false;
		if (disOrder == null) {
			if (other.disOrder != null)
				return false;
		} else if (!disOrder.equals(other.disOrder))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (reId == null) {
			if (other.reId != null)
				return false;
		} else if (!reId.equals(other.reId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "AreaModel [childrens=" + childrens + ", id=" + id + ", name="
				+ name + ", reId=" + reId + ", disOrder=" + disOrder + "]";
	}
}
