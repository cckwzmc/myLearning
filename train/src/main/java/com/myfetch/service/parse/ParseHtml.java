package com.myfetch.service.parse;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.slf4j.LoggerFactory;

import com.myfetch.service.http.HttpHtmlService;

public class ParseHtml {
	private static final org.slf4j.Logger logger = LoggerFactory
			.getLogger(ParseHtml.class);

	public static String parseContent(String url) {
		HttpHtmlService httpService = new HttpHtmlService();
		String html = httpService.getHtmlContent(url);
		return html;
	}

	/**
	 * 读取xml文件，文件格式为 <sitename></sitename> <listurl></listurl>
	 * 
	 * @return
	 */
	public String getFetchUrls() {
		String url = "";

		return url;
	}

	public static List<Map<String, String>> parseSiteInfo(String html,
			Map<String, String> map) {
		List<Map<String, String>> bookInfoList = new ArrayList<Map<String, String>>();

		String bookURLEx = convertRegex(map.get("bookurl"));
		String bookLastEx = convertRegex(map.get("lastarc"));
		String bookStatusEx = convertRegex(map.get("status"));
		String bookTypeEx = convertRegex(map.get("type"));
		String pageBlockStart;
		String pageBlockEnd;
		List<String> typeList = new ArrayList<String>();
		List<String> urlList = new ArrayList<String>();
		List<String> statusList = new ArrayList<String>();
		List<String> lastList = new ArrayList<String>();

		Pattern pt = Pattern.compile(bookTypeEx);
		Matcher matcher = pt.matcher(html);
		try {
			while (matcher.find()) {
				typeList.add(matcher.group(1));
			}
		} catch (Exception e) {

		}
		
		pt = Pattern.compile(bookURLEx);
		matcher = pt.matcher(html);
		try {
			while (matcher.find()) {
				urlList.add(matcher.group(1));
			}
		} catch (Exception e) {

		}
		
		pt = Pattern.compile(bookStatusEx);
		matcher = pt.matcher(html);
		try {
			while (matcher.find()) {
				statusList.add(matcher.group(1));
			}
		} catch (Exception e) {

		}
		
		pt = Pattern.compile(bookLastEx);
		matcher = pt.matcher(html);
		try {
			while (matcher.find()) {
				lastList.add(matcher.group(1));
			}
		} catch (Exception e) {

		}
		for (int i=0;typeList!=null&&i<typeList.size();i++) {
			Map<String,String> m=new HashMap<String, String>();
			m.put("type", typeList.get(i));
			m.put("bookurl", urlList.get(i));
			m.put("lastarc", lastList.get(i));
			m.put("status", statusList.get(i));
			bookInfoList.add(m);
		}
		return bookInfoList;
	}

	public static String convertRegex(String str) {
		String regex = "";
//		int start = StringUtils.indexOf(str, "<");
//		int end = StringUtils.indexOf(str, " ");
//		int temp = StringUtils.indexOf(str, ">");
//		String exclude = "";
//		if(StringUtils.contains(StringUtils.substring(str, start,end), "/"))
//		{
//			start = StringUtils.indexOf(str, "[args1]");
//			end = StringUtils.indexOf(str, "[*]");
//			if (start > end) {
//				exclude = StringUtils.substring(str, start + 7);
//			} else {
//				exclude = StringUtils.substring(str, end + 3);
//			}
//		}else{
//			exclude=StringUtils.substring(str, 0, end>temp?temp:end);
//			exclude="<[^a-zA-Z]"+exclude;
//		}	
//		exclude=StringUtils.replace(exclude, "/", "[^a-zA-Z]");
		if (StringUtils.contains(str, "[args1]")) {
			str = StringUtils.replace(str, "[args1]", "([^<]*)");
		}
		if (StringUtils.contains(str, "[*]")) {
			str = StringUtils.replace(str, "[*]", ".*");
		}
		str = ".*?" + str + ".*?";
		return str;
	}
	
	public static List<String> getPageLink(String start,String end){
		List<String> list=new ArrayList<String>();
		return list;
	}
}
