package com.myfetch.service.parse;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.ObjectUtils;
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
			e.printStackTrace();
		}

		pt = Pattern.compile(bookURLEx);
		matcher = pt.matcher(html);
		try {
			while (matcher.find()) {
				urlList.add(matcher.group(1));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		pt = Pattern.compile(bookStatusEx);
		matcher = pt.matcher(html);
		try {
			while (matcher.find()) {
				statusList.add(matcher.group(1));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		pt = Pattern.compile(bookLastEx);
		matcher = pt.matcher(html);
		try {
			while (matcher.find()) {
				lastList.add(matcher.group(1));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		for (int i = 0; typeList != null && i < typeList.size(); i++) {
			Map<String, String> m = new HashMap<String, String>();
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
		// int start = StringUtils.indexOf(str, "<");
		// int end = StringUtils.indexOf(str, " ");
		// int temp = StringUtils.indexOf(str, ">");
		// String exclude = "";
		// if(StringUtils.contains(StringUtils.substring(str, start,end), "/"))
		// {
		// start = StringUtils.indexOf(str, "[args1]");
		// end = StringUtils.indexOf(str, "[*]");
		// if (start > end) {
		// exclude = StringUtils.substring(str, start + 7);
		// } else {
		// exclude = StringUtils.substring(str, end + 3);
		// }
		// }else{
		// exclude=StringUtils.substring(str, 0, end>temp?temp:end);
		// exclude="<[^a-zA-Z]"+exclude;
		// }
		// exclude=StringUtils.replace(exclude, "/", "[^a-zA-Z]");

		if (StringUtils.contains(str, "[args1]")) {
			str = StringUtils.replace(str, "[args1]", "([^<]*)");
		}
		if (StringUtils.contains(str, "[args2]")) {
			str = StringUtils.replace(str, "[args2]", "([^<]*)");
		}
		if (StringUtils.contains(str, "[args3]")) {
			str = StringUtils.replace(str, "[args3]", "([^<]*)");
		}
		if (StringUtils.contains(str, "[*]")) {
			str = StringUtils.replace(str, "[*]", ".*");
		}
		str = ".*?" + str + ".*?";
		return str;
	}

	public static List<String> getPageLink(String start, String end) {
		List<String> list = new ArrayList<String>();
		return list;
	}

	public static List<Map<String, String>> parseBookConverInfo(String html,
			Map<String, String> map) {
		List<Map<String, String>> converInfoList = new ArrayList<Map<String, String>>();
		String litpicEx = convertRegex(map.get("litpic"));
		String bookdescEx = convertRegex(map.get("bookdesc"));
		String booknameEx = convertRegex(map.get("bookname"));
		String authorEx = convertRegex(map.get("author"));
		String chinesenumEx = convertRegex(map.get("chinesenum"));
		String chapterlisturlEx = convertRegex(map.get("chapterlisturl"));
		String keywordEx = convertRegex(map.get("keyword"));

		Map<String, String> bookconver = new HashMap<String, String>();
		Pattern pt = Pattern.compile(litpicEx);
		Matcher matcher = pt.matcher(html);
		try {
			while (matcher.find()) {
				bookconver.put("litpic", matcher.group(1));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		pt = Pattern.compile(bookdescEx);
		matcher = pt.matcher(html);
		try {
			while (matcher.find()) {
				bookconver.put("bookdesc", matcher.group(1));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		pt = Pattern.compile(booknameEx);
		matcher = pt.matcher(html);
		try {
			while (matcher.find()) {
				bookconver.put("bookname", matcher.group(1));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		pt = Pattern.compile(authorEx);
		matcher = pt.matcher(html);
		try {
			while (matcher.find()) {
				bookconver.put("author", matcher.group(1));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		pt = Pattern.compile(chinesenumEx);
		matcher = pt.matcher(html);
		try {
			while (matcher.find()) {
				bookconver.put("chinesenum", matcher.group(1));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		pt = Pattern.compile(chapterlisturlEx);
		matcher = pt.matcher(html);
		try {
			while (matcher.find()) {
				bookconver.put("chapterlisturl", matcher.group(1));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (!"".equals(ObjectUtils.toString(map.get("keyword")))) {
			pt = Pattern.compile(keywordEx);
			matcher = pt.matcher(html);
			try {
				while (matcher.find()) {
					bookconver.put("keyword", matcher.group(1));
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		converInfoList.add(bookconver);
		return converInfoList;
	}

	public static List<Map<String, String>> parseChapterList(String html,
			Map<String, String> map, String fetchurl) {
		List<Map<String, String>> chapterInfoList = new ArrayList<Map<String, String>>();
		String partnameEx = convertRegex(map.get("partname"));
		String chapternameEx = convertRegex(map.get("chaptername"));
		String chaptercontenturlEx = convertRegex(map.get("chaptercontenturl"));
		String modurltypeEx = map.get("modurltype");
		String addurlEx = convertRegex(map.get("addurl"));
		String replacesourcEx = convertRegex(map.get("replacesourc"));
		String replacedeEx = convertRegex(map.get("replacede"));
		Pattern pt = null;
		Matcher matcher = null;
		if (StringUtils.equals(chaptercontenturlEx, chapternameEx)) {
			pt = Pattern.compile(chaptercontenturlEx);
			matcher = pt.matcher(html);
			try {
				while (matcher.find()) {
					Map<String, String> chapterList = new HashMap<String, String>();
					String modrul = matcher.group(1);
					// chapterList.put("chaptercontenturl", modrul);
					chapterList.put("chaptername", matcher.group(2));
					if ("local".equals(modurltypeEx.trim())) {
						String addurl = StringUtils.substring(fetchurl, 0,
								fetchurl.lastIndexOf("/") + 1);
						chapterList.put("chaptercontenturl", addurl + modrul);
					} else {
						String addurl = StringUtils.substring(fetchurl, 0,
								fetchurl.lastIndexOf("/") + 1);
						chapterList.put("chaptercontenturl", addurl + modrul);
					}
					chapterInfoList.add(chapterList);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {

		}
		// Map<String, String> bookconver=new HashMap<String, String>();
		// Pattern pt = Pattern.compile("chaptername");
		// Matcher matcher = pt.matcher(html);
		// try {
		// while (matcher.find()) {
		// bookconver.put("chaptername",matcher.group(1));
		// }
		// } catch (Exception e) {
		// e.printStackTrace();
		// }
		//		

		return chapterInfoList;
	}

	public static List<Map<String, String>> parseChapterContent(String html,
			Map<String, String> map) {
		List<Map<String, String>> chapterInfoList = new ArrayList<Map<String, String>>();
		String startEx = map.get("contentstart");
		String endEx = map.get("contentend");
		String fetchTypeEx = map.get("fetchtype");
		String bodyEx = convertRegex(map.get("contentbody"));
		String titleEx = convertRegex(map.get("title"));
		String replaceWord=map.get("replaceword");
		String[] rep=StringUtils.split(replaceWord,"|");
		Pattern pt = null;
		Matcher matcher = null;
		Map<String, String> contentBody = new HashMap<String, String>();
		if (StringUtils.isNotBlank(fetchTypeEx) && "1".equals(fetchTypeEx)) {
			try {
				int start=StringUtils.indexOf(html, startEx)+startEx.length();
				int end=StringUtils.indexOf(html, endEx)-endEx.length();
				String content=StringUtils.substring(html, start, end);
				//处理一下包含、、、域名、、站点名的
				if(rep.length>0){
					for (int i = 0; i < rep.length; i++) {
						String[] word=StringUtils.split(rep[i], ",");
						if(word.length==2){
							StringUtils.replace(content, word[0], StringUtils.equals(word[1], "空")?"":word[1]);
						}
					}
				}
				contentBody.put("contentbody",content);
				if(!"".equals(map.get("title"))){
					pt = Pattern.compile(titleEx);
					matcher = pt.matcher(html);
					while (matcher.find()) {
						contentBody.put("title", matcher.group(1));
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			chapterInfoList.add(contentBody);
		} else {
			//正则表达式方式
		}

		return chapterInfoList;
	}
}
