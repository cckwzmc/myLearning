package com.toney.dal.cms.model;

import java.util.List;

import com.toney.dal.base.BaseObject;

/**
 * 
 *************************************************************** 
 * <p>
 * 
 * @CLASS :AreaModel
 * @DESCRIPTION :Area Model
 * @AUTHOR :toney.li
 * @VERSION :v1.0
 * @DATE :Apr 24, 2013
 *       </p>
 **************************************************************** 
 */
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
	private Integer parentId;
	/**
	 * 第二排序
	 */
	private Integer seqence;

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

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public Integer getSeqence() {
		return seqence;
	}

	public void setSeqence(Integer seqence) {
		this.seqence = seqence;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((childrens == null) ? 0 : childrens.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((parentId == null) ? 0 : parentId.hashCode());
		result = prime * result + ((seqence == null) ? 0 : seqence.hashCode());
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
		if (parentId == null) {
			if (other.parentId != null)
				return false;
		} else if (!parentId.equals(other.parentId))
			return false;
		if (seqence == null) {
			if (other.seqence != null)
				return false;
		} else if (!seqence.equals(other.seqence))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "AreaModel [childrens=" + childrens + ", id=" + id + ", name=" + name + ", parentId=" + parentId + ", seqence=" + seqence + "]";
	}
}
