package com.lyxmq.novel.model.cms.core;

import com.lyxmq.novel.model.BaseModel;

/**
 * Cms 内容基本信息管理.(文章、图片)
 * @author Administrator
 *
 */
public class CmsBaseArticle extends BaseModel {
	private static final long serialVersionUID = 622095055858174296L;
	private long id;
	private int typeId;
	private int superTypeId;
	private String title;
	private String desc;
	private String shortTitle;
	private String flag;
	private String keywords;
	private long authorId;
	private String author;
	private String source;
	private String litpic;
	private long sortrank;
	private long publishDate;
	private long createDate;
	private long money;
	private CmsLastInfo cmsLastInfo;
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getTypeId() {
		return typeId;
	}

	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}

	public int getSuperTypeId() {
		return superTypeId;
	}

	public void setSuperTypeId(int superTypeId) {
		this.superTypeId = superTypeId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getShortTitle() {
		return shortTitle;
	}

	public void setShortTitle(String shortTitle) {
		this.shortTitle = shortTitle;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getKeywords() {
		return keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	public long getAuthorId() {
		return authorId;
	}

	public void setAuthorId(long authorId) {
		this.authorId = authorId;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getLitpic() {
		return litpic;
	}

	public void setLitpic(String litpic) {
		this.litpic = litpic;
	}

	public long getSortrank() {
		return sortrank;
	}

	public void setSortrank(long sortrank) {
		this.sortrank = sortrank;
	}

	public long getPublishDate() {
		return publishDate;
	}

	public void setPublishDate(long publishDate) {
		this.publishDate = publishDate;
	}

	public long getCreateDate() {
		return createDate;
	}

	public void setCreateDate(long createDate) {
		this.createDate = createDate;
	}

	public long getMoney() {
		return money;
	}

	public void setMoney(long money) {
		this.money = money;
	}

	public CmsLastInfo getCmsLastInfo() {
		return cmsLastInfo;
	}

	public void setCmsLastInfo(CmsLastInfo cmsLastInfo) {
		this.cmsLastInfo = cmsLastInfo;
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
