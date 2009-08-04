package com.myfetch.util;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.util.ResourceUtils;

public class ServiceUtils {
	private static final String FILEPATH="myfetch/publisher/";
	private static final String FILENAME="publisher.properties";
	@SuppressWarnings("unchecked")
	public static List getLastFetchchapterurlsList(List bookList, List chapterList) {
		List lastList = new ArrayList();
		for (Iterator iterator = bookList.iterator(); iterator.hasNext();) {
			Map lastMap = new HashMap();
			Map bMap = (Map) iterator.next();
			Integer bookid = (Integer) bMap.get("bookid");
			for (Iterator iterator2 = chapterList.iterator(); iterator2.hasNext();) {
				Map cMap = (Map) iterator2.next();

			}
		}
		return null;
	}
	/**
	 * 清理自动发布文件
	 */
	public static void clearPublisherFile(){
		try {
//			Properties pro = PropertiesLoaderUtils.loadAllProperties(publisherFile);
//			Properties properties=new Properties();
//			URI uri=new URI(publisherFile);
		    String path=ClassLoader.getSystemResource("").getPath();
		    if(path.indexOf(":/")>-1){
		    	path=path.substring(1);
		    }
			File file=ResourceUtils.getFile(path+FILEPATH);
			if(!file.exists()){
				file.mkdirs();
			}
			file=ResourceUtils.getFile(path+FILEPATH+FILENAME);
			if(file.exists()){
				file.delete();
				file.createNewFile();
			}else{
				file.createNewFile();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}
	public static void main(String[] args) {
		try {
			Properties pro = PropertiesLoaderUtils.loadAllProperties("myfetch/publisher/publisherUrl.properties");
			System.setProperties(pro);
			System.out.println(System.getProperty("updateCachePage"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		clearPublisherFile();
	}
	
}
