package com.lyxmq.novel.model.cms.core;

import com.lyxmq.novel.model.BaseModel;

/**
 * 最新信息统计，包括最后编辑时间等
 * 
 * @author lyxmq
 */
public class CmsLastInfo extends BaseModel {

	private static final long serialVersionUID = 1359277530122073489L;
	private long id;
	// 最新更新作者ID
	private long lastPostAuthorId;
	// 最新更新作者姓名
	private String lastPostAuthor;
	// 好文章统计
	private int goodArticle;
	// 拍砖
	private int badArticle;
	// 无聊文章
	private int wuliaoArticle;
	// 最新更新时间
	private long lastPostDate;
	private int clicks;

	public int getClicks() {
		return clicks;
	}

	public void setClicks(int clicks) {
		this.clicks = clicks;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getLastPostAuthorId() {
		return lastPostAuthorId;
	}

	public void setLastPostAuthorId(long lastPostAuthorId) {
		this.lastPostAuthorId = lastPostAuthorId;
	}

	public String getLastPostAuthor() {
		return lastPostAuthor;
	}

	public void setLastPostAuthor(String lastPostAuthor) {
		this.lastPostAuthor = lastPostAuthor;
	}

	public int getGoodArticle() {
		return goodArticle;
	}

	public void setGoodArticle(int goodArticle) {
		this.goodArticle = goodArticle;
	}

	public int getBadArticle() {
		return badArticle;
	}

	public void setBadArticle(int badArticle) {
		this.badArticle = badArticle;
	}

	public int getWuliaoArticle() {
		return wuliaoArticle;
	}

	public void setWuliaoArticle(int wuliaoArticle) {
		this.wuliaoArticle = wuliaoArticle;
	}

	public long getLastPostDate() {
		return lastPostDate;
	}

	public void setLastPostDate(long lastPostDate) {
		this.lastPostDate = lastPostDate;
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
