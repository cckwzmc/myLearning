package com.lyxmq.novel.model.novel.core;

import java.util.Date;

import com.lyxmq.novel.model.BaseModel;

/**
 * 数据最新变更信息，用于经常变动信息.
 * 分月、周、总
 * @author Administrator
 *
 */
public class NovelBookLastInfo extends BaseModel {

	private static final long serialVersionUID = -7922970137557475033L;
	private long id;
	//顶次数
	private int goodBook;
	//踩次数
	private int badBook;
	//评分星级
	private int start;
	//总点击
	private int click;
	//周点击
	private int monthClick;
	//月点击
	private int weekClick;
	//收藏总次数
	private int favorites;
	//月收藏次数
	private int monthFavorites;
	//周收藏次数
	private int weekFavorites;
	//总推荐票，推荐给其他人（如：朋友等）；
	private int totalRecommend;
	//月推荐，推荐给书友（所有人）
	private int monthRecommendTickets;
	//总月票
	private int totalMonthTickets;
	//当前月，月票
	private int monthTickets;
	//月更新章节记录，只记ID
	private String monthUpdate;
	//周更新章节记录，只记ID
	private String weekUpdate;
	//最后更新时间
	private Date lastPubDate;
	//最新章节，URL
	private String lastChapter;
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getGoodBook() {
		return goodBook;
	}

	public void setGoodBook(int goodBook) {
		this.goodBook = goodBook;
	}

	public int getBadBook() {
		return badBook;
	}

	public void setBadBook(int badBook) {
		this.badBook = badBook;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getClick() {
		return click;
	}

	public void setClick(int click) {
		this.click = click;
	}

	public int getMonthClick() {
		return monthClick;
	}

	public void setMonthClick(int monthClick) {
		this.monthClick = monthClick;
	}

	public int getWeekClick() {
		return weekClick;
	}

	public void setWeekClick(int weekClick) {
		this.weekClick = weekClick;
	}

	public int getFavorites() {
		return favorites;
	}

	public void setFavorites(int favorites) {
		this.favorites = favorites;
	}

	public int getMonthFavorites() {
		return monthFavorites;
	}

	public void setMonthFavorites(int monthFavorites) {
		this.monthFavorites = monthFavorites;
	}

	public int getWeekFavorites() {
		return weekFavorites;
	}

	public void setWeekFavorites(int weekFavorites) {
		this.weekFavorites = weekFavorites;
	}

	public int getTotalRecommend() {
		return totalRecommend;
	}

	public void setTotalRecommend(int totalRecommend) {
		this.totalRecommend = totalRecommend;
	}

	public int getMonthRecommendTickets() {
		return monthRecommendTickets;
	}

	public void setMonthRecommendTickets(int monthRecommendTickets) {
		this.monthRecommendTickets = monthRecommendTickets;
	}

	public int getTotalMonthTickets() {
		return totalMonthTickets;
	}

	public void setTotalMonthTickets(int totalMonthTickets) {
		this.totalMonthTickets = totalMonthTickets;
	}

	public int getMonthTickets() {
		return monthTickets;
	}

	public void setMonthTickets(int monthTickets) {
		this.monthTickets = monthTickets;
	}

	public String getMonthUpdate() {
		return monthUpdate;
	}

	public void setMonthUpdate(String monthUpdate) {
		this.monthUpdate = monthUpdate;
	}

	public String getWeekUpdate() {
		return weekUpdate;
	}

	public void setWeekUpdate(String weekUpdate) {
		this.weekUpdate = weekUpdate;
	}

	public Date getLastPubDate() {
		return lastPubDate;
	}

	public void setLastPubDate(Date lastPubDate) {
		this.lastPubDate = lastPubDate;
	}

	public String getLastChapter() {
		return lastChapter;
	}

	public void setLastChapter(String lastChapter) {
		this.lastChapter = lastChapter;
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
