package com.myfetch.myfetch.dao;

import java.util.ArrayList;
import java.util.List;

public class MyFetchDao extends JdbcBaseDao {

	@SuppressWarnings("unchecked")
	public List getUrlList(){
		String sql="select t.id,t.url,t.status,t.type,t.fromsitename where from fetchurls t";
		return this.getJdbcTemplate().queryForList(sql);
	}
	//book cover url
	@SuppressWarnings("unchecked")
	public List getBookList(){
		String sql="select t.bookid,t.url,t.finishstatus,t.booktype,t.bookstatus,t.lasterarticle,t.picurl,t.desc,t.chinesenum,t.author,t.keyword,t. where from booklisturls t";
		return this.getJdbcTemplate().queryForList(sql);
	}
	//book table list
	/**
	 * isnewadd 是否新增章节
	 */
	@SuppressWarnings("unchecked")
	public List getCoverList(){
		String sql="select t.id,t.url,t.finishstatus,t.booktype,t.part,t.partdesc,t.bookid,t.isnewadd where from coverlisturls t";
		return this.getJdbcTemplate().queryForList(sql);
	}
	//book article list
	@SuppressWarnings("unchecked")
	public List getTableList(){
		String sql="select t.id,t.bookid,t.url,t.finishstatus,t.title,t.content,t.desc where from tablelisturls t";
		return this.getJdbcTemplate().queryForList(sql);
	}
	
}
