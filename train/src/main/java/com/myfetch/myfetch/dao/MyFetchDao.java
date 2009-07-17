package com.myfetch.myfetch.dao;

import java.util.List;

public class MyFetchDao extends JdbcBaseDao {

	@SuppressWarnings("unchecked")
	public List getUrlList(){
		String sql="select t.id,t.url,t.status,t.type,t.fromsitename,t.lasterarticle,t.isfetch  from fetchurls t";
		return this.getJdbcTemplate().queryForList(sql);
	}
	//book cover url
	//isfetch是否立即采集
	@SuppressWarnings("unchecked")
	public List getBookList(){
		String sql="select t.bookid,t.url,t.finishstatus,t.booktype,t.bookstatus,t.lasterarticle,t.picurl,t.desc,t.chinesenum,t.author,t.keyword,t.isfetch  from booklisturls t";
		return this.getJdbcTemplate().queryForList(sql);
	}
	//book table list
	/**
	 * isnewadd 是否新增章节
	 */
	@SuppressWarnings("unchecked")
	public List getCoverList(){
		String sql="select `id`,`bookid`,`bookname`,`booklisturl`,`chinesenum`,`bookdesc`,`isfetch`,`keyword`,`author`  from fetchbookconver t";
		return this.getJdbcTemplate().queryForList(sql);
	}
	//book article list
	@SuppressWarnings("unchecked")
	public List getTableList(){
		String sql="select t.id,t.bookid,t.url,t.finishstatus,t.title,t.content,t.desc where from tablelisturls t";
		return this.getJdbcTemplate().queryForList(sql);
	}
	
	public void saveBookUrl(String url,String status,String type,String fromsitename,String lasterarticle){
		String sql="insert into fetchurls (url,status,type,fromsitename,lasterarticle ) values (?,?,?,?,?)";
		this.getJdbcTemplate().update(sql, new Object[]{url,status,type,fromsitename,lasterarticle});
	}
	public void saveConverInfo(String bookListUrl, String litpic, String chinesenum, String bookdesc, String author,String bookname,String keyword,Integer bookid) {
		String sql="insert into fetchbookconver (bookid,bookname,booklisturl,litpic,chinesenum,bookdesc,isfetch,keyword,author) values (?,?,?,?,?,?,?,?,?)";
		this.getJdbcTemplate().update(sql,new Object[]{bookid,bookname,bookListUrl,litpic,chinesenum,bookdesc,"0",keyword,author});
	}
	public void saveChapterInfo(String chaptername, String chapterurl,Integer bookid) {
		String sql="insert into fetchchapterurls (`bookid`,`chaptername`,`chapterurl`,`isfetch`) values (?,?,?,?)";
		this.getJdbcTemplate().update(sql,new Object[]{bookid,chaptername,chapterurl,"0"});
	}
	public void saveChapterListHtml(Integer bookid,String html) {
		String sql="insert into fetchchapterlist_html (`id`,`html`) values (?,?)";
		this.getJdbcTemplate().update(sql,new Object[]{bookid,html});
	}
	public List getContentList() {
		String sql="select t.bookid,t.id,isfetch,chapterurl  from fetchchapterurls t";
		return this.getJdbcTemplate().queryForList(sql);
	}
	public void saveContentInfo(Integer bookid,String id,String content,String title) {
		String sql="insert into fetchchaptercontent (`bookid`,`chapterid`,`body`,`title`) values (?,?,?,?)";
		this.getJdbcTemplate().update(sql,new Object[]{bookid,id,content,title});
	}
	public void saveContentListHtml(Integer chapterid,String html) {
		String sql="insert into fetchcontentlist_html (`id`,`html`) values (?,?)";
		this.getJdbcTemplate().update(sql,new Object[]{chapterid,html});
	}
}
