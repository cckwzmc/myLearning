package com.lyxmq.novel.model.novel.core;

import java.util.Date;

import com.lyxmq.novel.model.BaseModel;

/**
 * 
 * 文章内容的精简对象
 * @author Administrator
 *
 */
public class NovelArticleTiny extends BaseModel {
	private static final long serialVersionUID = -5290624427573952157L;
	private long id;
	private long bookId;
	private long typeId;
	private String title;
	private long sortrank;
	private Date sendDate;

	public long getBookId() {
		return bookId;
	}

	public void setBookId(long bookId) {
		this.bookId = bookId;
	}
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getTypeId() {
		return typeId;
	}

	public void setTypeId(long typeId) {
		this.typeId = typeId;
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

	public Date getSendDate() {
		return sendDate;
	}

	public void setSendDate(Date sendDate) {
		this.sendDate = sendDate;
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
