package com.myfetch.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class ServiceUtils {
	@SuppressWarnings("unchecked")
	public static List getLastFetchchapterurlsList(List bookList,List chapterList){
		List lastList=new ArrayList();
		for (Iterator iterator = bookList.iterator(); iterator.hasNext();) {
			Map lastMap=new HashMap();
			Map bMap = (Map) iterator.next();
			Integer bookid=(Integer) bMap.get("bookid");
			for (Iterator iterator2 = chapterList.iterator(); iterator2.hasNext();) {
				Map cMap = (Map) iterator2.next();
				
			}
		}
		return null;
	}
	
}
