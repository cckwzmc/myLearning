package com.myfetch.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.slf4j.LoggerFactory;

import com.myfetch.myfetch.dao.MyFetchDao;
import com.myfetch.service.http.HttpHtmlService;
import com.myfetch.service.parse.ParseHtml;
import com.myfetch.util.DateUtils;
import com.myfetch.util.HttpResourceUtils;
import com.myfetch.util.XMLUtils;

public class MyFetchService {
	private static final org.slf4j.Logger logger = LoggerFactory.getLogger(MyFetchService.class);
	MyFetchDao dao = null;
	private final static String bootPath = "D:\\xiaoshuoimg";
	public final static String[] bookstatus = new String[] { "连载,", "完成,w", "完结,w" };

	public MyFetchDao getDao() {
		return dao;
	}

	public void setDao(MyFetchDao dao) {
		this.dao = dao;
	}

	/**
	 * 未采集完成列表 staus==0表示未完成 status==1表示完成
	 * badbook =1的书籍不要采集，可能是收费书籍
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List unFetchList() {
		List list = this.dao.getUrlList();
		List retList = new ArrayList();
		if (CollectionUtils.isNotEmpty(list)) {
			for (Iterator iterator = list.iterator(); iterator.hasNext();) {
				Map map = (Map) iterator.next();
				if("0".equals(ObjectUtils.toString(map.get("badbook")))){
					if ("0".equals(ObjectUtils.toString(map.get("isfetch"))) || "".equals(ObjectUtils.toString(map.get("isfetch")))) {
						retList.add(map);
					}
				}
			}
		}
		return retList;
	}

	/**
	 * 采集完成列表 staus==0表示未完成 status==1表示完成
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List fetchList() {
		List list = this.dao.getUrlList();
		List retList = new ArrayList();
		if (org.apache.commons.collections.CollectionUtils.isNotEmpty(list)) {
			for (Iterator iterator = list.iterator(); iterator.hasNext();) {
				Map map = (Map) iterator.next();
				if ("1".equals(ObjectUtils.toString(map.get("isfetch")))) {
					retList.add(map);
				}
			}
		}
		return retList;
	}

	@SuppressWarnings("unchecked")
	public List getUnConverList() {
		List list = this.dao.getCoverList();
		List retList = new ArrayList();
		if (org.apache.commons.collections.CollectionUtils.isNotEmpty(list)) {
			for (Iterator iterator = list.iterator(); iterator.hasNext();) {
				Map map = (Map) iterator.next();
				if ("0".equals(ObjectUtils.toString(map.get("isfetch")))) {
					retList.add(map);
				}
			}
		}
		return retList;
	}

	@SuppressWarnings("unchecked")
	public List getConverList() {
		List list = this.dao.getCoverList();
		List retList = new ArrayList();
		if (org.apache.commons.collections.CollectionUtils.isNotEmpty(list)) {
			for (Iterator iterator = list.iterator(); iterator.hasNext();) {
				Map map = (Map) iterator.next();
				if ("1".equals(ObjectUtils.toString(map.get("isfetch")))) {
					retList.add(map);
				}
			}
		}
		return retList;
	}

	public void disposeBookList(String files) {
		List<String> list = XMLUtils.getXmlFileName(files);
		for (String filename : list) {
			Map<String, String> map = XMLUtils.parseSiteList(XMLUtils.getDocumentXml(filename));
			String[] burls = StringUtils.split(map.get("burl"), "|");
			for (int i = 0; i < burls.length; i++) {
				String html = HttpHtmlService.getHtmlContent(burls[i]);
				List<Map<String, String>> bMap = ParseHtml.parseSiteInfo(html, map);
				for (Map<String, String> map2 : bMap) {
					if (this.dao.getFetchurlsUrl(map2.get("bookurl")) == 0) {
						String type=this.dao.getBookTypeByName(map2.get("bookname"));
						if("".equals(type)){
							type=map2.get("type");
						}
						this.dao.saveBookUrl(map2.get("bookurl"), map2.get("status"), type, filename, map2.get("lastarc"),map2.get("bookname"));
					} else {
						logger.info("该书的地址已经存在" + map2.get("bookurl"));
					}
				}
			}
		}
	}

	/**
	 * 采集书籍类型分类关系表，，针对分类混乱的采集站使用 分类已"爱书者"的分类为准
	 * 
	 * @param files
	 */
	public void disposeBookMap(String files) {
		List<String> list = XMLUtils.getXmlFileName(files);
		for (String filename : list) {
			Map<String, String> map = XMLUtils.parseBookTypeList(XMLUtils.getDocumentXml(filename));
			Map<String, String> typeMap = XMLUtils.parseSiteList(XMLUtils.getDocumentXml(filename));
			String[] splitstr = null;
			if (StringUtils.indexOf(map.get("url"), ",") != -1) {
				splitstr = StringUtils.split(map.get("url"), ",");
				splitstr[0] = StringUtils.substring(splitstr[0], (StringUtils.lastIndexOf(splitstr[0], "/") + 1));
				splitstr[1] = StringUtils.substring(splitstr[1], 0, (StringUtils.indexOf(splitstr[1], ".")));
			}
			String[] burls = new String[NumberUtils.toInt(splitstr[1])];// StringUtils.split(map.get("burl"),"|");
			String preStr = StringUtils.substring(map.get("url"), 0, StringUtils.lastIndexOf(map.get("url"), "/") + 1);
			for (int i = 0; i < burls.length; i++) {
				burls[i] = preStr + (i + 1) + ".htm";
				typeMap.remove("burl");
				typeMap.put("burl", burls[i]);
			}
			for (int i = 0; i < burls.length; i++) {
				String html = HttpHtmlService.getHtmlContent(burls[i]);
				List<Map<String, String>> bMap = ParseHtml.parseSiteInfo(html, typeMap);
				for (Map<String, String> map2 : bMap) {
					this.dao.saveBookType(map2.get("bookname"), map2.get("type"), map2.get("bookurl"));
				}
			}
		}
	}

	/**
	 * file，name bookconversite 参考aishuzhe.xml 加上直接采集书籍URL
	 * 不建议直接采集书籍封面
	 * 这样会采集不到是否连载，最后更新章节等
	 * 实在要使用封面直接采集建议可以在数据库配置
	 * @param files
	 */
	@SuppressWarnings("unchecked")
	public void disposeBookConver(String files) {
		List<String> list = XMLUtils.getXmlFileName(files);
		for (String filename : list) {
			Map<String, String> map = XMLUtils.parseConverXml(XMLUtils.getDocumentXml(filename));
			if (!"".equals(ObjectUtils.toString(map.get("urls")))) {
				String[] urls = StringUtils.split(map.get("urls"), "|");
				for (int i = 0; i < urls.length; i++) {
					int id=this.dao.saveBookUrl(urls[i],null,null, filename, null,null);
					String html = HttpHtmlService.getHtmlContent(urls[i]);
					logger.info("开始抓取封面地址为：" + urls[i]);
					List<Map<String, String>> converMap = ParseHtml.parseBookConverInfo(html, map);
					for (Map<String, String> map2 : converMap) {
						if (this.dao.getFetchbookconverUrl(map2.get("chapterlisturl")) == 0) {
							this.dao.saveConverInfo(ObjectUtils.toString(map2.get("chapterlisturl")), ObjectUtils.toString(map2.get("litpic")), ObjectUtils.toString(map2.get("chinesenum")), ObjectUtils.toString(map2.get("bookdesc")), ObjectUtils.toString(map2.get("author")),
									ObjectUtils.toString(map2.get("bookname")), ObjectUtils.toString(map2.get("keyword")),id);
						} else {
							logger.info("该封面地址列表地址已存在:;" + map2.get("chapterlisturl"));
						}
					}
				}

			} else {
				List bList = unFetchList();
				for (Iterator iterator = bList.iterator(); iterator.hasNext();) {
					Map bMap = (Map) iterator.next();
					if (!"".equals(ObjectUtils.toString(bMap.get("url"))) && StringUtils.contains(ObjectUtils.toString(bMap.get("url")), files)) {
						String html = HttpHtmlService.getHtmlContent(ObjectUtils.toString(bMap.get("url")));
						logger.info("开始抓取封面地址为：" + bMap.get("url"));
						List<Map<String, String>> converMap = ParseHtml.parseBookConverInfo(html, map);
						for (Map<String, String> map2 : converMap) {
							if (this.dao.getFetchbookconverUrl(map2.get("chapterlisturl")) == 0) {
								this.dao.saveConverInfo(ObjectUtils.toString(map2.get("chapterlisturl")), ObjectUtils.toString(map2.get("litpic")), ObjectUtils.toString(map2.get("chinesenum")), ObjectUtils.toString(map2.get("bookdesc")), ObjectUtils.toString(map2.get("author")),
										ObjectUtils.toString(map2.get("bookname")), ObjectUtils.toString(map2.get("keyword")), NumberUtils.toInt(ObjectUtils.toString(bMap.get("id"))));
							} else {
								logger.info("该封面地址列表地址已存在:;" + map2.get("chapterlisturl"));
							}
						}
					}
				}
			}

		}
	}

	/**
	 * @param files
	 */
	@SuppressWarnings("unchecked")
	public void disposeBookChapter(String files) {
		List<String> list = XMLUtils.getXmlFileName(files);
		for (String filename : list) {
			Map<String, String> map = XMLUtils.parseChapterXml(XMLUtils.getDocumentXml(filename));
			List bList = this.getUnConverList();
			for (Iterator iterator = bList.iterator(); iterator.hasNext();) {
				Map bMap = (Map) iterator.next();
				if (!"".equals(ObjectUtils.toString(bMap.get("booklisturl"))) && StringUtils.contains(ObjectUtils.toString(bMap.get("booklisturl")), files)) {
					String html = HttpHtmlService.getHtmlContent(ObjectUtils.toString(bMap.get("booklisturl")));
					logger.info("开始抓取书籍章节列表地址为：" + bMap.get("booklisturl"));
					// 如果已经抓取过得处理
					//TODO 这里更行抓取有问题
					List<Map<String, String>> chapterurls = ParseHtml.parseChapterList(html, map, ObjectUtils.toString(bMap.get("booklisturl")));
					if (CollectionUtils.isNotEmpty(this.dao.getFetchchapterurlsByBookid(NumberUtils.toInt(ObjectUtils.toString(bMap.get("bookid")))))) {
						List lastList = this.dao.getFetchchapterurlsListByBookid(NumberUtils.toInt(ObjectUtils.toString(bMap.get("bookid"))));
						String lastarc = (String) ((Map) lastList.get(0)).get("chapterurl");
						boolean flag = false;
						for (Map<String, String> map2 : chapterurls) {
							if (StringUtils.equals(map2.get("chaptercontenturl"), lastarc)) {
								flag = true;

							}

							if (flag) {
								logger.info("新增书籍章节地址:;" + map2.get("chaptercontenturl"));
//								this.dao.saveChapterInfo(map2.get("chaptername"), map2.get("chaptercontenturl"), NumberUtils.toInt(ObjectUtils.toString(bMap.get("bookid"))));
							} else {
								logger.info("该列表地址书籍章节已存在:;" + map2.get("chaptercontenturl"));
							}
						}
					} else {
						Integer columnId=-1;
						Integer bookid=-1;
						Map<String,Integer> mapColumnId=new HashMap<String, Integer>();
						for (Map<String, String> map2 : chapterurls) {
							//TODO 涉及到更新章节的时候这里要注意查重
							if(!StringUtils.equals("",ObjectUtils.toString(map2.get("columnname")))){
								columnId=this.dao.saveFetchBookColumn(map2.get("columnname"),NumberUtils.toInt(ObjectUtils.toString(bMap.get("bookid"))));
								mapColumnId.put(map2.get("uuid"), columnId);
							}else {
								Integer columnid=0;
								if(columnId>0){
									columnid=mapColumnId.get(map2.get("uuid"));
									bookid=NumberUtils.toInt(ObjectUtils.toString(bMap.get("bookid")));
								}else{
									bookid=NumberUtils.toInt(ObjectUtils.toString(bMap.get("bookid")));
									columnid=-1;
								}
								this.dao.saveChapterInfo(map2.get("chaptername"), map2.get("chaptercontenturl"), bookid,columnid);
							}
						}
					}
				}
			}

		}
	}

	/**
	 * @param files
	 */
	@SuppressWarnings("unchecked")
	public void disposeChapterContent(String files) {
		List<String> list = XMLUtils.getXmlFileName(files);
		for (String filename : list) {
			Map<String, String> map = XMLUtils.parseContentXml(XMLUtils.getDocumentXml(filename));
			List bList = this.getUnContentList();
			for (Iterator iterator = bList.iterator(); iterator.hasNext();) {
				Map bMap = (Map) iterator.next();
				if (!"".equals(ObjectUtils.toString(bMap.get("chapterurl"))) && StringUtils.contains(ObjectUtils.toString(bMap.get("chapterurl")), files)) {
					String html = HttpHtmlService.getHtmlContent(ObjectUtils.toString(bMap.get("chapterurl")));
					List<Map<String, String>> contentList = ParseHtml.parseChapterContent(html, map);
					logger.info("开始抓取内容地址为：" + bMap.get("chapterurl"));
					for (Map<String, String> map2 : contentList) {
						this.dao.saveContentInfo(NumberUtils.toInt(ObjectUtils.toString(bMap.get("bookid"))), ObjectUtils.toString(bMap.get("id")), map2.get("contentbody"), map2.get("title"));
					}
				}
			}
		}
	}

	@SuppressWarnings("unchecked")
	private List getUnContentList() {
		List list = this.dao.getFetchchapterurlsList();
		List retList = new ArrayList();
		if (org.apache.commons.collections.CollectionUtils.isNotEmpty(list)) {
			for (Iterator iterator = list.iterator(); iterator.hasNext();) {
				Map map = (Map) iterator.next();
				if ("0".equals(ObjectUtils.toString(map.get("isfetch")))) {
					retList.add(map);
				}
			}
		}
		return retList;
	}

	@SuppressWarnings("unchecked")
	private List getContentList() {
		List list = this.dao.getFetchchapterurlsList();
		List retList = new ArrayList();
		if (org.apache.commons.collections.CollectionUtils.isNotEmpty(list)) {
			for (Iterator iterator = list.iterator(); iterator.hasNext();) {
				Map map = (Map) iterator.next();
				if ("1".equals(ObjectUtils.toString(map.get("isfetch")))) {
					retList.add(map);
				}
			}
		}
		return retList;
	}

	/**
	 * 数据转移到dedecms数据库
	 * 
	 * @throws IOException
	 */
	@SuppressWarnings("unchecked")
	public void saveDataToDedecms(String sitePattern) throws IOException {

		List list = this.dao.getSiteData();
		for (Iterator iterator = list.iterator(); iterator.hasNext();) {

			Map bookBasic = (Map) iterator.next();
			if (!StringUtils.contains(ObjectUtils.toString(bookBasic.get("url")), sitePattern)) {
				continue;
			}
			Integer bookid = NumberUtils.toInt(ObjectUtils.toString(bookBasic.get("id")));
			// 保存小说基本信息
			Integer typeid = this.dao.getTypeidByfetchId(ObjectUtils.toString(bookBasic.get("type")));
			Map map = this.dao.getBookInfoById(bookid);
			// String litpic=ObjectUtils.toString(map.get("litpic"));

			logger.info("开始转移抓取数据，书名为：：" + ObjectUtils.toString(map.get("bookname")));
			String filename = ObjectUtils.toString(map.get("litpic"));
			if (!"".equals(filename)) {
				String dir = DateUtils.getDate();
				String fileName = StringUtils.substring(filename, filename.lastIndexOf("/") + 1);
				String savePath = bootPath + "\\" + dir;
				File filepath = new File(savePath);
				if (!filepath.exists()) {
					filepath.mkdirs();
				}
				savePath = savePath + "\\" + fileName;
				filepath = new File(savePath);
				if (filepath.exists()) {
					filepath.delete();
				}
				int retDown=HttpResourceUtils.saveToFile(filename, savePath);
				if(retDown==1)
				{
					map.put("litpic", "/uploads/" + dir + "/" + fileName);
				}else{
					map.put("litpic", "/uploads/noImage.gif");
				}
			}
			int retPublish=this.dao.getIntPublishBook(bookid);
			Integer parentArcId =0;
			if(retPublish==0){
				parentArcId = this.dao.saveConverForDede(typeid, bookid, map);
			}else{
				if("".equals(ObjectUtils.toString(bookBasic.get("bookname")))){
					logger.info("要求注意的地方"+ObjectUtils.toString(bookBasic.get("url"))+"发布失败!!!!");
					continue;
				}
				parentArcId=this.dao.getPublishBookIdByBookId(ObjectUtils.toString(bookBasic.get("bookname")),typeid);
				if(parentArcId<1){
					logger.info("要求注意的地方"+ObjectUtils.toString(bookBasic.get("url"))+"发布失败!!!!");
					continue;
				}
			}
			// 保存图片

			// 保存章节
			// 抓取的章节列表
			List arcList = this.dao.getFetchArcListByBookId(bookid);
			// List tableList = this.dao.getFetchTableListByBookId(bookid);
			int i = 0;
			
			for (Iterator iterator2 = arcList.iterator(); iterator2.hasNext();) {
				Map arcMap = (Map) iterator2.next();
				// for (Iterator iterator3 = tableList.iterator();
				// iterator3.hasNext();) {
				// Map tableMap = (Map) iterator3.next();
				// Integer
				// arcCid=NumberUtils.toInt(ObjectUtils.toString(arcMap.get("chapterid")));
				// Integer
				// arcTid=NumberUtils.toInt(ObjectUtils.toString(tableMap.get("id")));
				// if(arcCid>0&&arcTid>0&&arcCid==arcTid)
				// {
				// Map articleMap=new HashMap();
				// articleMap.put("body",arcMap.get("body"));
				// articleMap.put("chaptername", tableMap.get("chaptername"));
				i++;
				boolean isAddLastId = false;
				if (arcList.size() == i) {
					isAddLastId = true;
				}
				this.dao.saveArticleForDede(typeid, bookid, map, arcMap, parentArcId, isAddLastId);
				this.dao.updateFetch((Integer) arcMap.get("id"));
				// }
				// }
			}
		}
	}

	public Boolean isHaveLastUpdate(String lastarcName, String url) {
		int i = this.dao.getFetchurlsLastUpdate(lastarcName, url);
		return i > 0 ? false : true;
	}

	public void testDao() {
	}
}
