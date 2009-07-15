package com.myfetch.service;



import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.ObjectUtils;

import com.myfetch.myfetch.dao.MyFetchDao;

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
	
	
}
