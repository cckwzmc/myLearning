package com.lyxmq.novel.model.novelcore;

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
	private int goodBook;
	private int badBook;
	//评分星级
	private int start;
	private int click;
	private int monthClick;
	private int weekClick;
	//收藏
	
	//推荐给其他人（如：朋友等）；
	private int recommend;
	//推荐给书友（所有人）
	private int recommendTickets;
	//投月票
	private int monthTickets;
	//最后更新时间
	private Date lastPubDate;
	//最新章节，URL
	private String lastChapter;
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
