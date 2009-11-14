package com.lyxmq.novel.model.novel.core;

import java.util.Date;

import com.lyxmq.novel.model.BaseModel;

/**
 * 
 * 书籍信息； 1、书名 2、卷名（分部小说）
 * 
 * @author Administrator
 * 
 */
public class NovelBookInfo extends BaseModel {
	private static final long serialVersionUID = 7896745221750419895L;
	private long id;
	// 卷,默认值为0;书名为0；
	private long superId;
	// 卷，值等于id，用于卷的排序;
	private long seqNo;
	//数据显示位置(如：封推等)
	private String flag;
	
	private String title;
	// 排序使用，使用时间戳
	private long sortrank;
	private String desc;
	// 创建时间
	private Date createDate;
	// 发布时间
	private Date publishDate;
	//总字数
	private int charNum;
	//作者ID
	private long authorId;
	//作者
	private String author;
	//来源
	private String source;
	//是否完成
	private boolean isComplete;
	//缩略图url;
	private String litpic;
	//是否新书,未上架就算新书。
	private boolean isNew;

	private String keywords;
	
	public boolean isNew() {
		return isNew;
	}

	public void setNew(boolean isNew) {
		this.isNew = isNew;
	}
	
	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public int getCharNum() {
		return charNum;
	}

	public void setCharNum(int charNum) {
		this.charNum = charNum;
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

	public boolean isComplete() {
		return isComplete;
	}

	public void setComplete(boolean isComplete) {
		this.isComplete = isComplete;
	}

	public String getLitpic() {
		return litpic;
	}

	public void setLitpic(String litpic) {
		this.litpic = litpic;
	}

	public String getKeywords() {
		return keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getSuperId() {
		return superId;
	}

	public void setSuperId(long superId) {
		this.superId = superId;
	}

	public long getSeqNo() {
		return seqNo;
	}

	public void setSeqNo(long seqNo) {
		this.seqNo = seqNo;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public long getSortrank() {
		return sortrank;
	}

	public void setSortrank(long sortrank) {
		this.sortrank = sortrank;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getPublishDate() {
		return publishDate;
	}

	public void setPublishDate(Date publishDate) {
		this.publishDate = publishDate;
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
