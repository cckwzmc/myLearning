package com.myfetch.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.util.ResourceUtils;

public class ServiceUtils {
	private static final String FILEPATH = "myfetch/publisher/";
	private static final String FILENAME = "publisher.properties";

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
	public static void clearPublisherFile() {
		try {
			// Properties pro = PropertiesLoaderUtils.loadAllProperties(publisherFile);
			// Properties properties=new Properties();
			// URI uri=new URI(publisherFile);
			String path = ClassLoader.getSystemResource("").getPath();
			if (path.indexOf(":/") > -1) {
				path = path.substring(1);
			}
			File file = ResourceUtils.getFile(path + FILEPATH);
			if (!file.exists()) {
				file.mkdirs();
			}
			file = ResourceUtils.getFile(path + FILEPATH + FILENAME);
			if (file.exists()) {
				file.delete();
				file.createNewFile();
			} else {
				file.createNewFile();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void writePublish(Map<String, String> typeMap, Map<String, String> bookMap, int minArcId) {
		// 清理之前的发布缓存
		ServiceUtils.clearPublisherFile();
		String path = ClassLoader.getSystemResource("").getPath();
		File file =null;
		FileWriter writer=null;
		if (path.indexOf(":/") > -1) {
			path = path.substring(1);
		}
		try {
			file = ResourceUtils.getFile(path + FILEPATH + FILENAME);
			writer = new FileWriter(file);
//			BufferedWriter bw =new BufferedWriter(writer);
			PrintWriter bw = new PrintWriter(writer);
			String typeIds = "";
			String bookIds = "";
			for (Iterator<String> iterator = typeMap.keySet().iterator(); iterator.hasNext();) {
				String type = iterator.next();
				if ("".equals(typeIds)) {
					typeIds = type;
				} else {
					typeIds += "," + type;
				}
			}
			for (Iterator<String> iterator = bookMap.keySet().iterator(); iterator.hasNext();) {
				String bookid = iterator.next();
				if ("".equals(bookIds)) {
					bookIds = bookid;
				} else {
					bookIds += "," + bookid;
				}
			}
			bw.write("typeids=" + typeIds);
			bw.write("\r\n");
			bw.write("bookids=" + bookIds);
			bw.write("\r\n");
			bw.write("minarcid=" + minArcId);
			bw.flush();
			writer.flush();
			//为什么不能close;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
		}
	}

	public static void main(String[] args) {
		try {
			Map<String, String> map=new HashMap<String, String>();
			map.put("123", "123");
			writePublish(map,map,123);
		} catch (Exception e) {
			e.printStackTrace();
		}
		clearPublisherFile();
	}

}
