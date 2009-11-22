package com.lyxmq.novel.model.cms.core;

import com.lyxmq.novel.model.BaseModel;

/**
 * Cms 文字为主的内容管理，文章内容。
 * 
 * @author Administrator
 */
public class CmsPhotoContent extends BaseModel {

	private static final long serialVersionUID = 2986758375828015035L;
	private long id;
	private String imgBody;

	// 文章内容类型，如；0:自己编辑的内容、1:直接跳转内容,
	private int typeId;
	private String redirectUrl;
	private int templateId;
	// 图片显示风格,1页显示多个，还是一页一张.
	private int pagestyle;
	// 图片最宽长度
	private int maxWidth;
	private int maxHeight;
	// 每页最多张数
	private int pagepicNum;
	// 图片总数
	private int totalPicNum;
	//每页行数
	private int row;
	//不建议使用，每页列数.
	private int col;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getImgBody() {
		return imgBody;
	}

	public void setImgBody(String imgBody) {
		this.imgBody = imgBody;
	}

	public int getPagestyle() {
		return pagestyle;
	}

	public void setPagestyle(int pagestyle) {
		this.pagestyle = pagestyle;
	}

	public int getMaxWidth() {
		return maxWidth;
	}

	public void setMaxWidth(int maxWidth) {
		this.maxWidth = maxWidth;
	}

	public int getMaxHeight() {
		return maxHeight;
	}

	public void setMaxHeight(int maxHeight) {
		this.maxHeight = maxHeight;
	}

	public int getPagepicNum() {
		return pagepicNum;
	}

	public void setPagepicNum(int pagepicNum) {
		this.pagepicNum = pagepicNum;
	}

	public int getTotalPicNum() {
		return totalPicNum;
	}

	public void setTotalPicNum(int totalPicNum) {
		this.totalPicNum = totalPicNum;
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getCol() {
		return col;
	}

	public void setCol(int col) {
		this.col = col;
	}

	public int getTypeId() {
		return typeId;
	}

	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}

	public String getRedirectUrl() {
		return redirectUrl;
	}

	public void setRedirectUrl(String redirectUrl) {
		this.redirectUrl = redirectUrl;
	}

	public int getTemplateId() {
		return templateId;
	}

	public void setTemplateId(int templateId) {
		this.templateId = templateId;
	}

	@Override
	public boolean equals(Object o) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}

}
