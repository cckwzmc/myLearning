package com.myfetch.service;



import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.ObjectUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.myfetch.myfetch.dao.MyFetchDao;
import com.myfetch.service.http.HttpHtmlService;
import com.myfetch.service.parse.ParseHtml;
import com.myfetch.util.XMLUtils;

public class MyFetchService {
	MyFetchDao dao=null;
	public MyFetchDao getDao() {
		return dao;
	}
	public void setDao(MyFetchDao dao) {
		this.dao = dao;
	}
	/**
	 * 未采集完成列表
	 * staus==0表示未完成
	 * status==1表示完成
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List unFetchList(){
		List list=this.dao.getUrlList();
		List retList=new ArrayList();
		if(org.apache.commons.collections.CollectionUtils.isNotEmpty(list)){
			for (Iterator iterator = list.iterator(); iterator.hasNext();) {
				Map map = (Map) iterator.next();
				if("0".equals(ObjectUtils.toString(map.get("status")))||"".equals(ObjectUtils.toString(map.get("status")))){
					retList.add(map);
				}
			}
		}
		return retList;
	}
	/**
	 * 采集完成列表
	 * staus==0表示未完成
	 * status==1表示完成
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List fetchList(){
		List list=this.dao.getUrlList();
		List retList=new ArrayList();
		if(org.apache.commons.collections.CollectionUtils.isNotEmpty(list)){
			for (Iterator iterator = list.iterator(); iterator.hasNext();) {
				Map map = (Map) iterator.next();
				if("1".equals(ObjectUtils.toString(map.get("status")))){
					retList.add(map);
				}
			}
		}
		return retList;
	}
	
	public void disposeBookList(){
		List<String> list=XMLUtils.getXmlFileName();
		for (String filename:list) {
			Map<String,String> map =XMLUtils.parseSiteList(XMLUtils.getDocumentXml(filename));
			String html=HttpHtmlService.getHtmlContent(map.get("burl"));
			List<Map<String,String>> bMap=ParseHtml.parseSiteInfo(html, map);
			for (Map<String,String> map2: bMap) {
				this.dao.saveBookUrl(map2.get("bookurl"),map2.get("status"),map2.get("type"),null, map2.get("lastarc"));	
			}
			
		}
		
	}
	public void testDao(){
		this.dao.getBookList();
	}
	public static void main(String[] args) {
	}
}
