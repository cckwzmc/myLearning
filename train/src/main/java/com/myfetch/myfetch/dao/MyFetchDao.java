package com.myfetch.myfetch.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;

import com.myfetch.service.MyFetchService;

public class MyFetchDao extends JdbcBaseDao {

	@SuppressWarnings("unchecked")
	public List getUrlList() {
		String sql = "select t.id,t.url,t.status,t.type,t.fromsitename,t.lasterarticle,t.isfetch,t.badfetch  from fetchurls t";
		return this.getJdbcTemplate().queryForList(sql);
	}

	// book cover url
	// isfetch是否立即采集
//	@SuppressWarnings("unchecked")
//	public List getBookList() {
//		String sql = "select t.bookid,t.url,t.finishstatus,t.booktype,t.bookstatus,t.lasterarticle,t.picurl,t.desc,t.chinesenum,t.author,t.keyword,t.isfetch  from booklisturls t";
//		return this.getJdbcTemplate().queryForList(sql);
//	}

	// book table list
	/**
	 * isnewadd 是否新增章节
	 */
	@SuppressWarnings("unchecked")
	public List getCoverList() {
		String sql = "select `id`,`bookid`,`bookname`,`booklisturl`,`chinesenum`,`bookdesc`,`isfetch`,`keyword`,`author`  from fetchbookconver t order by id;";
		return this.getJdbcTemplate().queryForList(sql);
	}

	// book article list
	@SuppressWarnings("unchecked")
	public List getTableList() {
		String sql = "select t.id,t.bookid,t.url,t.finishstatus,t.title,t.content,t.desc where from tablelisturls t";
		return this.getJdbcTemplate().queryForList(sql);
	}

	public int saveBookUrl(String url, String status, String type, String fromsitename, String lasterarticle,String bookname,String isfetch) {
		String sql ="select max(id) from fetchurls";
		int id=this.getJdbcTemplate().queryForInt(sql)+1;
		sql="insert into fetchurls (id,url,status,type,fromsitename,lasterarticle,bookname,isfetch) values (?,?,?,?,?,?,?,?)";
		this.getJdbcTemplate().update(sql, new Object[] { id,url, status, type, fromsitename, lasterarticle ,bookname,isfetch});
		return id;
	}

	public void saveConverInfo(String bookListUrl, String litpic, String chinesenum, String bookdesc, String author, String bookname, String keyword, Integer bookid) {
		String sql = "insert into fetchbookconver (bookid,bookname,booklisturl,litpic,chinesenum,bookdesc,isfetch,keyword,author) values (?,?,?,?,?,?,?,?,?)";
		this.getJdbcTemplate().update(sql, new Object[] { bookid, bookname, bookListUrl, litpic, chinesenum, bookdesc, "0", keyword, author });
	}

	public void saveChapterInfo(String chaptername, String chapterurl, Integer bookid,Integer columnid) {
		try {
			String sql = "insert into fetchchapterurls (`bookid`,`chaptername`,`chapterurl`,`isfetch`,`fetchdate`,`columnid`) values (?,?,?,?,NOW(),?)";
			this.getJdbcTemplate().update(sql, new Object[] { bookid, chaptername, chapterurl, "0" ,columnid});
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}

	public void saveChapterListHtml(Integer bookid, String html) {
		String sql = "insert into fetchchapterlist_html (`id`,`html`) values (?,?)";
		this.getJdbcTemplate().update(sql, new Object[] { bookid, html });
	}

	public List getFetchchapterurlsList() {
		String sql = "select t.bookid,t.id,isfetch,chapterurl  from fetchchapterurls t";
		return this.getJdbcTemplate().queryForList(sql);
	}
	public List getFetchchapterurlsListByBookid(Integer bookid) {
		String sql = "select t.bookid,t.id,isfetch,chapterurl  from fetchchapterurls t where t.bookid=? order by id desc";
		return this.getJdbcTemplate().queryForList(sql,new Object[]{bookid});
	}
	public void saveContentInfo(Integer bookid, String id, String content, String title) {
		String sql = "insert into fetchchaptercontent (`bookid`,`chapterid`,`body`,`title`) values (?,?,?,?)";
		this.getJdbcTemplate().update(sql, new Object[] { bookid, id, content, title });
	}

	public void saveContentListHtml(Integer chapterid, String html) {
		String sql = "insert into fetchcontentlist_html (`id`,`html`) values (?,?)";
		this.getJdbcTemplate().update(sql, new Object[] { chapterid, html });
	}

	/**
	 * ``````````````````````数据转移``````````````````````````````
	 */
	/**
	 * 
	 * 数据转移条件查询
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List getSiteData() {
		String sql = "select t.id,t.lasterarticle,t.type,t.url,t.bookname,t.status from fetchurls t where t.isfetch=0";
		return this.getJdbcTemplate().queryForList(sql);
	}

	public int getIntPublishBook(int bookid){
		String sql="select count(*) from fetchchapterurls t where t.bookid=? and t.isfetch=1";
		return this.getJdbcTemplate().queryForInt(sql,new Object[]{bookid});
	}
	/**
	 * 抓取类别与cms类别的对应
	 * 
	 * @param typeName
	 * @return
	 */
	public int getTypeidByfetchId(String typeName) {
		String sql = "select typeid from fetchtypemap t where t.fetchtype=?";
		return this.getJdbcTemplate().queryForInt(sql, new Object[] { typeName });
	}

	/**
	 * TODO 从抓取的信息中查询章节内容
	 * 
	 * @param bookid
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Map getArticleInfoById(Integer bookid) {
		String sql = "select t.id,f.type,f.status,t.bookid,f.lasterarticle,t.bookname,t.chinesenum,t.bookdesc,t.keyword,t.author from fetchbookconver t left join fetchurls f on t.bookid=f.id where t.bookid=? ";
		List list = this.getJdbcTemplate().queryForList(sql, new Object[] { bookid });
		if (CollectionUtils.isNotEmpty(list)) {
			return (Map) list.get(0);
		}
		return null;
	}

	/**
	 * 从抓取的内容中查询章节信息
	 * 
	 * @param bookid
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List getFetchArcListByBookId(Integer bookid) {
		String sql = " select chaptername,body,t1.id from fetchchaptercontent t left join fetchchapterurls t1" + " on t.chapterid=t1.id where t.bookid=?  and isfetch=0";
		return this.getJdbcTemplate().queryForList(sql, new Object[] { bookid });
	}

	/**
	 * 只取章节名称
	 * 
	 * @param bookid
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List getFetchTableListByBookId(Integer bookid) {
		String sql = " select id,bookid,chaptername from fetchchapterurls where bookid=? and isfetch=0";
		return this.getJdbcTemplate().queryForList(sql, new Object[] { bookid });
	}

	/**
	 * 书籍基本信息
	 * 
	 * @param bookid
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Map getBookInfoById(Integer bookid) {
		String sql = "select t.id,f.type,f.status,t.bookid,f.lasterarticle,t.bookname,t.chinesenum,t.bookdesc,t.keyword,t.author,t.litpic from fetchbookconver t left join fetchurls f on t.bookid=f.id where t.bookid=? and t.isfetch=0";
		List list = this.getJdbcTemplate().queryForList(sql, new Object[] { bookid });
		if (CollectionUtils.isNotEmpty(list)) {
			return (Map) list.get(0);
		}
		return null;
	}

	/**
	 * 保存书籍的基本信息
	 * 
	 * @param typeid
	 * @param bookid
	 * @param bookInfo
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public int saveConverForDede(Integer typeid, Integer bookid, Map bookInfo) {
		String sql = "select max(id) from dede_arctiny";
		Integer arcid = this.getJdbcTemplate().queryForInt(sql);
		arcid += 1;
		Long lTime = (new Date()).getTime();
		String flag = "t";
		String status = ObjectUtils.toString(bookInfo.get("status"));
		if (!"".equals(status)) {
			for (int i = 0; i < MyFetchService.bookstatus.length; i++) {
				String[] st = StringUtils.split(MyFetchService.bookstatus[i], ",");
				if (st.length == 2) {
					if (StringUtils.equals(status.trim(), st[0])) {
						if (!"".equals(st[1])) {
							flag += "," + st[1];
							break;
						}
					}
				}
			}
		}
		String time = StringUtils.substring(ObjectUtils.toString(lTime), 0, ObjectUtils.toString(lTime).length() - 3);
		// sortrank=time
		// $arcrank=0
		// $pubdate=time
		// $senddate=time
		// adminid=1
		// $flag=t
		// $ismake=1
		// $channelid=1
		// $money=0
		// $notpost=0
		sql = "insert into dede_archives(id,typeid,typeid2,sortrank,flag,ismake,channel,arcrank,click,money,title,shorttitle," + " color,writer,source,litpic,pubdate,senddate,mid, " + "notpost,description,keywords,filename,isbookpage,chinesenum,lasterarticle) "
				+ " VALUES (?,?,0,?,?,1, " + "'1','0','0','0', ?,'','',?," + " '',?,?,?, '1','0',?," + "?,'',null,?,?)";
		this.getJdbcTemplate().update(
				sql,
				new Object[] { arcid, typeid, time, flag, ObjectUtils.toString(bookInfo.get("bookname")), ObjectUtils.toString(bookInfo.get("author")), ObjectUtils.toString(bookInfo.get("litpic")), time, time,
						StringUtils.substring(StringUtils.replace(StringUtils.replace(ObjectUtils.toString(bookInfo.get("bookdesc")), "&nbsp;", ""), "<br/>", ""), 0, 254), ObjectUtils.toString(bookInfo.get("keyword")), NumberUtils.toInt(ObjectUtils.toString(bookInfo.get("chinesenum"))),
						ObjectUtils.toString(bookInfo.get("lasterarticle")) });

		sql = "insert into dede_arctiny (id,typeid,typeid2,arcrank,channel,senddate,sortrank,mid)" + " values (?,?,0,0,1,?,?,1)";
		this.getJdbcTemplate().update(sql, new Object[] { arcid, typeid, time, time });
		sql = "insert into dede_addonarticle (aid,typeid,body) values (?,?,?)";
		this.getJdbcTemplate().update(sql, new Object[] { arcid, typeid, ObjectUtils.toString(bookInfo.get("bookdesc")) });
		return arcid;
	}

	/**
	 * 保存章节信息
	 * 
	 * @param typeid
	 * @param bookid
	 * @param bookInfo
	 *            只有body 和chaptername 字段
	 * @param converId
	 * @return TODO
	 */
	@SuppressWarnings("unchecked")
	public Integer saveArticleForDede(Integer typeid, Integer bookid, Map arcInfo, Map bookInfo, Integer converId, Boolean addLastId) {
		String sql = "select max(id) from dede_arctiny";
		Integer arcid = this.getJdbcTemplate().queryForInt(sql);
		arcid += 1;
		Long lTime = (new Date()).getTime();
		String flag = "x";
		String time = StringUtils.substring(ObjectUtils.toString(lTime), 0, ObjectUtils.toString(lTime).length() - 3);

		sql = "insert into dede_archives(id,typeid,typeid2,sortrank,flag,ismake,channel,arcrank,click,money,title,shorttitle," + " color,writer,source,litpic,pubdate,senddate,mid, " + "notpost,description,keywords,filename,isbookpage,chinesenum,lasterarticle) "
				+ " VALUES (?,?,0,?,?,1, " + "'1','0','0','0', ?,'','',?," + " '',?,?,?, '1','0',?," + "?,'',?,?,?)";
		this.getJdbcTemplate().update(
				sql,
				new Object[] { arcid, typeid, time, flag, ObjectUtils.toString(bookInfo.get("chaptername")), ObjectUtils.toString(arcInfo.get("author")), ObjectUtils.toString(arcInfo.get("litpic")), time, time,
						StringUtils.substring(StringUtils.replace(StringUtils.replace(ObjectUtils.toString(arcInfo.get("bookdesc")), "&nbsp;", ""), "<br/>", ""), 0, 254), ObjectUtils.toString(arcInfo.get("keyword")), converId, null, null });
		sql = "insert into dede_arctiny (id,typeid,typeid2,arcrank,channel,senddate,sortrank,mid)" + " values (?,?,0,0,1,?,?,1)";
		this.getJdbcTemplate().update(sql, new Object[] { arcid, typeid, time, time });
		sql = "insert into dede_addonarticle (aid,typeid,body) values (?,?,?)";
		this.getJdbcTemplate().update(sql, new Object[] { arcid, typeid, ObjectUtils.toString(bookInfo.get("body")) });
		if (addLastId) {
			sql = "update dede_archives set lasterartid=? where id=?";
			this.getJdbcTemplate().update(sql, new Object[] { arcid, converId });
		}
		return arcid;
	}

	public void saveBookType(String bookname, String type, String bookurl) {
		try {
			String sql = "insert into fetchbookmap(bookname,type,bookurl) values(?,?,?)";
			this.getJdbcTemplate().update(sql, new Object[] { bookname, type, bookurl });
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}

	public String getBookTypeByName(String bookname) {
		String sql = "select type from fetchbookmap t where lower(t.bookname)=?";
		List list = this.getJdbcTemplate().queryForList(sql, new Object[] { bookname.toLowerCase() });
		if (CollectionUtils.isNotEmpty(list)) {
			if (list.size() > 1) {
				return "";
			}
			return ObjectUtils.toString(((Map) list.get(0)).get("type"));
		}
		return "";
	}

	public int getFetchurlsLastUpdate(String lastarcName, String url) {
		String sql = "select count(*) c from fetchurls t where t.url=? and lasterarticle=?";
		return this.getJdbcTemplate().queryForInt(sql, new Object[] { url, lastarcName });
	}

	public int getFetchurlsUrl(String url) {
		String sql = "select count(*) c from fetchurls t where t.url=?";
		return this.getJdbcTemplate().queryForInt(sql, new Object[] { url });
	}

	public int getFetchbookconverUrl(String url) {
		String sql = "select count(*) c from fetchbookconver t where t.booklisturl=?";
		return this.getJdbcTemplate().queryForInt(sql, new Object[] { url });
	}

	public void updateFetchChapterUrls(Integer id) {
		String sql="update fetchchapterurls set isfetch=1 where id=?";
		this.getJdbcTemplate().update(sql, new Object[]{id});
	}

	public List getFetchchapterurlsByBookid(int bookid) {
//		String sql="select count(*) from fetchbookcolumn t where t.bookid=?";
//		int existColumn=this.getJdbcTemplate().queryForInt(sql,new Object[]{bookid});
//		if(existColumn>0){
//			sql="select ft.bookid,t.chapterurl,ft.id columnid from fetchchapterurls t left join fetchbookcolumn ft on t.bookid=ft.bookid where ft.bookid=?";	
//		}else{
			String sql="select t.* from fetchchapterurls t where t.bookid=?";
//		}
		
		return this.getJdbcTemplate().queryForList(sql,new Object[]{bookid});
	}

	public String getFetchBookMap(String bookname) {
		String sql="select type from fetchbookmap t where t.bookname=?";
		List list=this.getJdbcTemplate().queryForList(sql,new Object[]{bookname});
		if(CollectionUtils.isNotEmpty(list)){
			return (String) ((Map)list.get(0)).get("type");
		}
		return "";
	}

	@SuppressWarnings("unchecked")
	public int saveFetchBookColumn(String columnName, int bookid) {
		String sql="select id from  fetchbookcolumn t where t.bookid=? and t.columnname=? ";
		int id=0;
		List list=this.getJdbcTemplate().queryForList(sql,new Object[]{bookid,columnName});
		if(CollectionUtils.isNotEmpty(list))
		{
			id=NumberUtils.toInt(ObjectUtils.toString(((Map)list.get(0)).get("id")));
		}else{
			sql="select max(id) id from fetchbookcolumn t ";
			id=this.getJdbcTemplate().queryForInt(sql)+1;
			sql="insert into fetchbookcolumn (id,bookid,url,columnname) values(?,?,null,?)";
			this.getJdbcTemplate().update(sql,new Object[]{id,bookid,columnName});
		}
		return id;
	}

	public Integer getPublishBookIdByBookId(String bookname,Integer typeid) {
		String sql="select id from dede_archives t where t.title=? and t.typeid=? and (t.isbookpage='' or t.isbookpage is null)";
		List list = this.getJdbcTemplate().queryForList(sql,new Object[]{bookname,typeid});
		if(CollectionUtils.isEmpty(list)){
			return 0;
		}
		return (Integer)((Map)list.get(0)).get("id");
	}

	public int getFetchUrlsByBookName(String bookname) {
		String sql="select count(*) from fetchurls t where t.bookname=?";
		return this.getJdbcTemplate().queryForInt(sql,new Object[]{bookname});
	}
	
	public void updateFetchUrls(Integer bookid) {
		String sql="update fetchurls set isfetch=2 where id=?";
		this.getJdbcTemplate().update(sql,new Object[]{bookid});
	}
}
