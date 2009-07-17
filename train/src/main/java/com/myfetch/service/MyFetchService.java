package com.myfetch.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;

import com.myfetch.myfetch.dao.MyFetchDao;
import com.myfetch.service.http.HttpHtmlService;
import com.myfetch.service.parse.ParseHtml;
import com.myfetch.util.XMLUtils;

public class MyFetchService {
	MyFetchDao dao = null;

	public MyFetchDao getDao() {
		return dao;
	}

	public void setDao(MyFetchDao dao) {
		this.dao = dao;
	}

	/**
	 * 未采集完成列表 staus==0表示未完成 status==1表示完成
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
	public List getUnConverList(){
		List list=this.dao.getCoverList();
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
	public List getConverList(){
		List list=this.dao.getCoverList();
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
	 * @param files
	 */
	public void disposeBookConver(String files) {
		List<String> list = XMLUtils.getXmlFileName(files);
		for (String filename : list) {
			Map<String, String> map = XMLUtils.parseConverXml(XMLUtils.getDocumentXml(filename));
			List bList = unFetchList();
			for (Iterator iterator = bList.iterator(); iterator.hasNext();) {
				Map bMap = (Map) iterator.next();
				if (!"".equals(ObjectUtils.toString(bMap.get("url")))&&StringUtils.contains(ObjectUtils.toString(bMap.get("url")),"www."+files)) {
					String html = HttpHtmlService.getHtmlContent(ObjectUtils.toString(bMap.get("url")));
					List<Map<String, String>> converMap = ParseHtml.parseBookConverInfo(html, map);
					for (Map<String, String> map2 : converMap) {
						this.dao.saveConverInfo(ObjectUtils.toString(map2.get("chapterlisturl")), ObjectUtils.toString(map2.get("litpic")), ObjectUtils.toString(map2.get("chinesenum")), ObjectUtils.toString(map2.get("bookdesc")), ObjectUtils.toString(map2
								.get("author")), ObjectUtils.toString(map2.get("bookname")), ObjectUtils.toString(map2.get("keyword")), NumberUtils.toInt(ObjectUtils.toString(bMap.get("id"))));
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
				if (!"".equals(ObjectUtils.toString(bMap.get("booklisturl")))&&StringUtils.contains(ObjectUtils.toString(bMap.get("booklisturl")),"www."+files)) {
					String html = HttpHtmlService.getHtmlContent(ObjectUtils.toString(bMap.get("booklisturl")));
					List<Map<String, String>> chapterurls = ParseHtml.parseChapterList(html, map,ObjectUtils.toString(bMap.get("booklisturl")));
					if(CollectionUtils.isNotEmpty(chapterurls)){
						this.dao.saveChapterListHtml(NumberUtils.toInt(ObjectUtils.toString(bMap.get("bookid"))),html);
					}
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
				if (!"".equals(ObjectUtils.toString(bMap.get("chapterurl")))&&StringUtils.contains(ObjectUtils.toString(bMap.get("chapterurl")),"www."+files)) {
					String html = HttpHtmlService.getHtmlContent(ObjectUtils.toString(bMap.get("chapterurl")));
					List<Map<String, String>> contentList = ParseHtml.parseChapterContent(html, map);
					if(CollectionUtils.isNotEmpty(contentList)){
						this.dao.saveContentListHtml(NumberUtils.toInt(ObjectUtils.toString(bMap.get("id"))),html);
					}
					for (Map<String, String> map2 : contentList) {
						this.dao.saveContentInfo( NumberUtils.toInt(ObjectUtils.toString(bMap.get("bookid"))),ObjectUtils.toString(bMap.get("id")), map2.get("contentbody"), map2.get("title"));
					}
				}
			}
		}
	}
	@SuppressWarnings("unchecked")
	private List getUnContentList() {
		List list=this.dao.getContentList();
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
		List list=this.dao.getContentList();
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
	public void testDao() {
		this.dao.getBookList();
	}

	public static void main(String[] args) {
	}
}
