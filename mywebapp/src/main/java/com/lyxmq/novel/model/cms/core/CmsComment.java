package com.lyxmq.novel.model.cms.core;

import java.util.Date;

import com.lyxmq.novel.model.BaseModel;

/**
 * 
 * 用户评论
 * 
 * @author Administrator
 * 
 */
public class CmsComment extends BaseModel {
	private static final long serialVersionUID = 3237173991803777108L;
	private long id;
	private long bookId;
	// 回复贴使用，默认为0
	private long superId;
	// 点击数
	private int clicks;
	// 回复数
	private int replys;
	// 支持数
	private int supports;
	// 反对数
	private int opposes;

	// 标题
	private String title;
	// 用户ID
	private long userId;
	// 用户名
	private String userName;
	// 发表内容
	private String content;
	// 发布时间
	private Date postDate;
	// 最后编辑者ID
	private long lastEditUserId;
	// 最后编辑者
	private String lastEditUserName;
	// 最后编辑时间
	private Date lastEditDate;

	public Date getLastEditDate() {
		return lastEditDate;
	}

	public void setLastEditDate(Date lastEditDate) {
		this.lastEditDate = lastEditDate;
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

	public long getSuperId() {
		return superId;
	}

	public void setSuperId(long superId) {
		this.superId = superId;
	}

	public int getClicks() {
		return clicks;
	}

	public void setClicks(int clicks) {
		this.clicks = clicks;
	}

	public int getReplys() {
		return replys;
	}

	public void setReplys(int replys) {
		this.replys = replys;
	}

	public int getSupports() {
		return supports;
	}

	public void setSupports(int supports) {
		this.supports = supports;
	}

	public int getOpposes() {
		return opposes;
	}

	public void setOpposes(int opposes) {
		this.opposes = opposes;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getPostDate() {
		return postDate;
	}

	public void setPostDate(Date postDate) {
		this.postDate = postDate;
	}

	public long getLastEditUserId() {
		return lastEditUserId;
	}

	public void setLastEditUserId(long lastEditUserId) {
		this.lastEditUserId = lastEditUserId;
	}

	public String getLastEditUserName() {
		return lastEditUserName;
	}

	public void setLastEditUserName(String lastEditUserName) {
		this.lastEditUserName = lastEditUserName;
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
