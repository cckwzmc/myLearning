package com.lyxmq.novel.model.novel.core;

import java.util.Date;

import com.lyxmq.novel.model.BaseModel;

/**
 * 小说章节基本信息
 * @author Administrator
 *
 */
public class NovelArticle  extends BaseModel{
	private static final long serialVersionUID = 642629249849513250L;
	private long id;
	//书籍ID
	private long bookId;
	private String title;
	//章节字数
	private int charNum;
	//是否发布，默认不发布
	private boolean isPublish;
	//发布时间
	private Date publishDate;
	//小标题
	private String shorttitle;
	//收费，默认为0；
	private int money;
	//点击数.
	private int click;
	
	public int getMoney() {
		return money;
	}

	public void setMoney(int money) {
		this.money = money;
	}

	public int getClick() {
		return click;
	}

	public void setClick(int click) {
		this.click = click;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getBookId() {
		return bookId;
	}

	public void setBookId(long bookId) {
		this.bookId = bookId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getCharNum() {
		return charNum;
	}

	public void setCharNum(int charNum) {
		this.charNum = charNum;
	}

	public boolean isPublish() {
		return isPublish;
	}

	public void setPublish(boolean isPublish) {
		this.isPublish = isPublish;
	}

	public Date getPublishDate() {
		return publishDate;
	}

	public void setPublishDate(Date publishDate) {
		this.publishDate = publishDate;
	}

	public String getShorttitle() {
		return shorttitle;
	}

	public void setShorttitle(String shorttitle) {
		this.shorttitle = shorttitle;
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
