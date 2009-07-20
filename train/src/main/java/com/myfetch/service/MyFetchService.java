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
	private final static String bootPath="D:\\xiaoshuoimg";
	public final static String[] bookstatus=new String[]{"连载,","完成,w"};
	public MyFetchDao getDao() {
		return dao;
	}

	public void setDao(MyFetchDao dao) {
		this.dao = dao;
	}

	/**
	 * 未采集完成列表 staus==0表示未完成 status==1表示完成
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List unFetchList() {
		List list = this.dao.getUrlList();
		List retList = new ArrayList();
		if (CollectionUtils.isNotEmpty(list)) {
			for (Iterator iterator = list.iterator(); iterator.hasNext();) {
				Map map = (Map) iterator.next();
				if ("0".equals(ObjectUtils.toString(map.get("isfetch"))) || "".equals(ObjectUtils.toString(map.get("isfetch")))) {
					retList.add(map);
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
			String html = HttpHtmlService.getHtmlContent(map.get("burl"));
			List<Map<String, String>> bMap = ParseHtml.parseSiteInfo(html, map);
			for (Map<String, String> map2 : bMap) {
				this.dao.saveBookUrl(map2.get("bookurl"), map2.get("status"), map2.get("type"), null, map2.get("lastarc"));
			}
		}
	}

	/**
	 * file，name bookconversite
	 * 
	 * @param files
	 */
	public void disposeBookConver(String files) {
		List<String> list = XMLUtils.getXmlFileName(files);
		for (String filename : list) {
			Map<String, String> map = XMLUtils.parseConverXml(XMLUtils.getDocumentXml(filename));
			List bList = unFetchList();
			for (Iterator iterator = bList.iterator(); iterator.hasNext();) {
				Map bMap = (Map) iterator.next();
				if (!"".equals(ObjectUtils.toString(bMap.get("url"))) && StringUtils.contains(ObjectUtils.toString(bMap.get("url")), "www." + files)) {
					String html = HttpHtmlService.getHtmlContent(ObjectUtils.toString(bMap.get("url")));
					List<Map<String, String>> converMap = ParseHtml.parseBookConverInfo(html, map);
					for (Map<String, String> map2 : converMap) {
						this.dao.saveConverInfo(ObjectUtils.toString(map2.get("chapterlisturl")), ObjectUtils.toString(map2.get("litpic")), ObjectUtils.toString(map2.get("chinesenum")), ObjectUtils.toString(map2.get("bookdesc")), ObjectUtils.toString(map2.get("author")),
								ObjectUtils.toString(map2.get("bookname")), ObjectUtils.toString(map2.get("keyword")), NumberUtils.toInt(ObjectUtils.toString(bMap.get("id"))));
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
				if (!"".equals(ObjectUtils.toString(bMap.get("booklisturl"))) && StringUtils.contains(ObjectUtils.toString(bMap.get("booklisturl")), "www." + files)) {
					String html = HttpHtmlService.getHtmlContent(ObjectUtils.toString(bMap.get("booklisturl")));
					logger.info("开始抓取封面地址为：" + bMap.get("booklisturl"));
					List<Map<String, String>> chapterurls = ParseHtml.parseChapterList(html, map, ObjectUtils.toString(bMap.get("booklisturl")));
					// if(CollectionUtils.isNotEmpty(chapterurls)){
					// this.dao.saveChapterListHtml(NumberUtils.toInt(ObjectUtils.toString(bMap.get("bookid"))),html);
					// }
					for (Map<String, String> map2 : chapterurls) {
						this.dao.saveChapterInfo(map2.get("chaptername"), map2.get("chaptercontenturl"), NumberUtils.toInt(ObjectUtils.toString(bMap.get("bookid"))));
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
				if (!"".equals(ObjectUtils.toString(bMap.get("chapterurl"))) && StringUtils.contains(ObjectUtils.toString(bMap.get("chapterurl")), "www." + files)) {
					String html = HttpHtmlService.getHtmlContent(ObjectUtils.toString(bMap.get("chapterurl")));
					List<Map<String, String>> contentList = ParseHtml.parseChapterContent(html, map);
					logger.info("开始抓取内容地址为：" + bMap.get("chapterurl"));
					// if(CollectionUtils.isNotEmpty(contentList)){
					// this.dao.saveContentListHtml(NumberUtils.toInt(ObjectUtils.toString(bMap.get("id"))),html);
					// }
					for (Map<String, String> map2 : contentList) {
						this.dao.saveContentInfo(NumberUtils.toInt(ObjectUtils.toString(bMap.get("bookid"))), ObjectUtils.toString(bMap.get("id")), map2.get("contentbody"), map2.get("title"));
					}
				}
			}
		}
	}

	@SuppressWarnings("unchecked")
	private List getUnContentList() {
		List list = this.dao.getContentList();
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
		List list = this.dao.getContentList();
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
	 * @throws IOException 
	 */
	@SuppressWarnings("unchecked")
	public void saveDataToDedecms(String sitePattern) throws IOException {

		List list = this.dao.getSiteData();
		for (Iterator iterator = list.iterator(); iterator.hasNext();) {
			
			Map bookBasic = (Map) iterator.next();
			if(!StringUtils.contains(ObjectUtils.toString(bookBasic.get("url")), sitePattern)){
				continue;
			}
			Integer bookid = NumberUtils.toInt(ObjectUtils.toString(bookBasic.get("id")));
			// 保存小说基本信息
			Integer typeid = this.dao.getTypeidByfetchId(ObjectUtils.toString(bookBasic.get("type")));
			Map map = this.dao.getBookInfoById(bookid);
//			String litpic=ObjectUtils.toString(map.get("litpic"));
			
			logger.info("开始转移抓取数据，书名为：："+ObjectUtils.toString(map.get("bookname")));
			String filename=ObjectUtils.toString(map.get("litpic"));
			if(!"".equals(filename)){
				String dir=DateUtils.getDate();
				String fileName=StringUtils.substring(filename, filename.lastIndexOf("/")+1);
				String savePath=this.bootPath+"\\"+dir;
				File filepath=new File(savePath);
				if(!filepath.exists()){
					filepath.mkdirs();
				}
				savePath=savePath+"\\"+fileName;
				filepath=new File(savePath);
				if(filepath.exists()){
					filepath.delete();
				}
				HttpResourceUtils.saveToFile(filename, savePath);
				map.put("litpic", "/uploads/"+dir+"/"+fileName);
			}
			Integer parentArcId = this.dao.saveConverForDede(typeid, bookid, map);
			//保存图片

			// 保存章节
			// 抓取的章节列表
			List arcList = this.dao.getFetchArcListByBookId(bookid);
//			List tableList = this.dao.getFetchTableListByBookId(bookid);
			int i=0;
			for (Iterator iterator2 = arcList.iterator(); iterator2.hasNext();) {
				Map arcMap = (Map) iterator2.next();
//				for (Iterator iterator3 = tableList.iterator(); iterator3.hasNext();) {
//					Map tableMap = (Map) iterator3.next();
//					Integer arcCid=NumberUtils.toInt(ObjectUtils.toString(arcMap.get("chapterid")));
//					Integer arcTid=NumberUtils.toInt(ObjectUtils.toString(tableMap.get("id")));
//					if(arcCid>0&&arcTid>0&&arcCid==arcTid)
//					{
//						Map articleMap=new HashMap();
//						articleMap.put("body",arcMap.get("body"));
//						articleMap.put("chaptername", tableMap.get("chaptername"));
				i++;
				boolean isAddLastId=false;
				if(arcList.size()==i)
				{
					isAddLastId=true;
				}
						this.dao.saveArticleForDede(typeid, bookid, map, arcMap, parentArcId,isAddLastId);
//					}	
//				}
			}
		}
	}

	public void testDao() {
		this.dao.getBookList();
	}
}
