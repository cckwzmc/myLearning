package com.toney.core.model;

public class AreaModel extends BaseObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3926478162746385584L;

	private Long id;         
	private String name; 
	/**
	 * 第一排序
	 */
	private Integer reid;         
	/**
	 * 第二排序
	 */
	private Integer disorder;    
	
	
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


	public Integer getReid() {
		return reid;
	}


	public void setReid(Integer reid) {
		this.reid = reid;
	}


	public Integer getDisorder() {
		return disorder;
	}

	public void setDisorder(Integer disorder) {
		this.disorder = disorder;
	}

	@Override
	public String toString() {
		return "AreaModel [id=" + id + ", name=" + name + ", reid=" + reid
				+ ", disorder=" + disorder + "]";
	}
}
