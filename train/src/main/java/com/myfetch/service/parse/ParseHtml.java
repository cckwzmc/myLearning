package com.myfetch.service.parse;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.LoggerFactory;

import com.myfetch.service.http.HttpHtmlService;

public class ParseHtml {
	private static final org.slf4j.Logger logger = LoggerFactory.getLogger(ParseHtml.class);

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

	public static List<Map<String, String>> parseSiteInfo(String html, Map<String, String> map) {
		List<Map<String, String>> bookInfoList = new ArrayList<Map<String, String>>();

		String bookURLEx = convertRegex(map.get("bookurl"));
		String bookLastEx = convertRegex(map.get("lastarc"));
		String bookStatusEx = convertRegex(map.get("status"));
		String bookTypeEx = convertRegex(map.get("type"));
		String bookNameEx = convertRegex(map.get("bookname"));
		String patterstrEx = convertRegex(map.get("pagestr"));
		String replacebookurlsource = ObjectUtils.toString(map.get("replacebookurlsource"));
		String replacebookurldesc = ObjectUtils.toString(map.get("replacebookurldesc"));
		String replacesrc1 = ObjectUtils.toString(map.get("replacesrc1"));
		String replacedesc1 = ObjectUtils.toString(map.get("replacedesc1"));
		String patternType = ObjectUtils.toString(map.get("patternType"));
		// html=StringUtils.replace(html, "\n","");
		// html的整体替换
		if (!"".equals(replacesrc1)) {
			html = html.replaceAll(replacesrc1, replacedesc1);
			logger.info(html);
		}
		if ("1".equals(patternType)) {
			Pattern pt = Pattern.compile(patterstrEx);
			Matcher matcher = pt.matcher(html);
			try {
				while (matcher.find()) {
					Map<String, String> m = new HashMap<String, String>();
					String url = matcher.group(2);
					if (!"".equals(replacebookurlsource)) {
						url = StringUtils.replace(url, replacebookurlsource, replacebookurldesc);
					}
					m.put("type", matcher.group(1));
					m.put("bookurl", url);
					m.put("lastarc", matcher.group(4));
					m.put("status", matcher.group(5));
					m.put("bookname", matcher.group(3));
					bookInfoList.add(m);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			String pageBlockStart;
			String pageBlockEnd;
			List<String> typeList = new ArrayList<String>();
			List<String> urlList = new ArrayList<String>();
			List<String> statusList = new ArrayList<String>();
			List<String> lastList = new ArrayList<String>();
			List<String> booknameList = new ArrayList<String>();

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
					String url = matcher.group(1);
					if (!"".equals(replacebookurlsource)) {
						url = StringUtils.replace(url, replacebookurlsource, replacebookurldesc);
					}
					urlList.add(url);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			pt = Pattern.compile(bookNameEx);
			matcher = pt.matcher(html);
			try {
				while (matcher.find()) {
					booknameList.add(matcher.group(1));
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
				m.put("bookname", booknameList.get(i));
				bookInfoList.add(m);
			}
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

	public static List<Map<String, String>> parseBookConverInfo(String html, Map<String, String> map) {
		List<Map<String, String>> converInfoList = new ArrayList<Map<String, String>>();
		String litpicEx = convertRegex(map.get("litpic"));
		String bookdescEx = convertRegex(map.get("bookdesc"));
		String booknameEx = convertRegex(map.get("bookname"));
		String authorEx = convertRegex(map.get("author"));
		String chinesenumEx = convertRegex(map.get("chinesenum"));
		String chapterlisturlEx = convertRegex(map.get("chapterlisturl"));
		String keywordEx = convertRegex(map.get("keyword"));
		String bookdescfetchtype = map.get("bookdescfetchtype");
		String bookdescstart = map.get("bookdescstart");
		String bookdescend = map.get("bookdescend");
		String replaceWord = map.get("replaceword");
		String typeEx = convertRegex(map.get("type"));
		String[] rep = StringUtils.split(replaceWord, "|");
		String replacesrc1 = ObjectUtils.toString(map.get("replacesrc1"));
		String replacedesc1 = ObjectUtils.toString(map.get("replacedesc1"));
		String replacesrc2 = ObjectUtils.toString(map.get("replacesrc2"));
		String replacedesc2 = ObjectUtils.toString(map.get("replacedesc2"));
		String replacesrc3 = ObjectUtils.toString(map.get("replacesrc3"));
		String replacedesc3 = ObjectUtils.toString(map.get("replacedesc3"));
		String replacesrc4 = ObjectUtils.toString(map.get("replacesrc4"));
		String replacedesc4 = ObjectUtils.toString(map.get("replacedesc4"));
		Map<String, String> bookconver = new HashMap<String, String>();
		// html的整体替换
		if (!"".equals(replacesrc1)) {
			html = html.replaceAll(replacesrc1, replacedesc1);

		}
		if (!"".equals(replacesrc2)) {
			html = html.replaceAll(replacesrc2, replacedesc2);
		}
		if (!"".equals(replacesrc3)) {
			html = html.replaceAll(replacesrc3, replacedesc3);
		}
		if (!"".equals(replacesrc4)) {
			html = html.replaceAll(replacesrc4, replacedesc4);
		}
		System.out.println(html);
		//这里每项只匹配一次就够了
		int exInt=0;
		Pattern pt = Pattern.compile(litpicEx);
		Matcher matcher = pt.matcher(html);
		try {
			while (exInt<1&&matcher.find()) {
				bookconver.put("litpic", matcher.group(1));
				exInt++;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		if ("1".equals(bookdescfetchtype)) {
			try {
				String descReset = StringUtils.substring(html, StringUtils.indexOf(html, bookdescstart) + bookdescstart.length());
				int end = StringUtils.indexOf(descReset, bookdescend);
				String content = StringUtils.substring(descReset, 0, end);
				// 处理一下包含、、、域名、、站点名的
				if (rep.length > 0) {
					for (int i = 0; i < rep.length; i++) {
						String[] word = StringUtils.split(rep[i], ",");
						if (word.length == 2) {
							content = content.replaceAll("(?i)" + word[0], StringUtils.equals(word[1], "空") ? "" : word[1]);
						}
					}
				}
				bookconver.put("bookdesc", content);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if ("2".equals(bookdescfetchtype)) {
			exInt=0;
			pt = Pattern.compile(bookdescEx);
			matcher = pt.matcher(html);
			try {
				while (exInt<1&&matcher.find()) {
					bookconver.put("bookdesc", matcher.group(1));
					exInt++;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		exInt=0;
		pt = Pattern.compile(booknameEx);
		matcher = pt.matcher(html);
		try {
			while (exInt<1&&matcher.find()) {
				bookconver.put("bookname", matcher.group(1));
				exInt++;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (!"".equals(ObjectUtils.toString(map.get("author")))) {
			exInt=0;
			pt = Pattern.compile(authorEx);
			matcher = pt.matcher(html);
			try {
				while (exInt<1&&matcher.find()) {
					bookconver.put("author", matcher.group(1));
					exInt++;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if (!"".equals(ObjectUtils.toString(map.get("chinesenum")))) {
			exInt=0;
			pt = Pattern.compile(chinesenumEx);
			matcher = pt.matcher(html);
			try {
				while (exInt<1&&matcher.find()) {
					bookconver.put("chinesenum", matcher.group(1));
					exInt++;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if (!"".equals(ObjectUtils.toString(map.get("type")))) {
			exInt=0;
			pt = Pattern.compile(typeEx);
			matcher = pt.matcher(html);
			try {
				while (exInt<1&&matcher.find()) {
					bookconver.put("type", matcher.group(1));
					exInt++;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if (!"".equals(ObjectUtils.toString(map.get("chapterlisturl")))) {
			exInt=0;
			pt = Pattern.compile(chapterlisturlEx);
			matcher = pt.matcher(html);
			try {
				while (exInt<1&&matcher.find()) {
					bookconver.put("chapterlisturl", matcher.group(1));
					exInt++;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
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

	/**
	 * 详细解释请看XML文件
	 * 
	 * @param html
	 * @param map
	 * @param fetchurl
	 * @return
	 */
	public static List<Map<String, String>> parseChapterList(String html, Map<String, String> map, String fetchurl) {
		List<Map<String, String>> chapterInfoList = new ArrayList<Map<String, String>>();
		String columnnameEx = convertRegex(map.get("columnname"));
		String chapternameEx = convertRegex(map.get("chaptername"));
		String chaptercontenturlEx = convertRegex(map.get("chaptercontenturl"));
		String modurltypeEx = map.get("modurltype");
		String addurlEx = convertRegex(map.get("addurl"));
		String replacesourcEx = convertRegex(map.get("replacesourc"));
		String replacedeEx = convertRegex(map.get("replacede"));
		// 是否采用动态地址采集
		String isgendongtaiurl = ObjectUtils.toString(map.get("isgendongtaiurl"));
		// 是否采集分卷 1：采集分卷，0：不采集分卷
		String iscolumn = ObjectUtils.toString(map.get("iscolumn"));
		String columnContent = "";
		Pattern pt = null;
		Matcher matcher = null;
		if (StringUtils.equals(chaptercontenturlEx, chapternameEx)) {
			if ("1".equals(iscolumn)) {
				pt = Pattern.compile(columnnameEx);
				matcher = pt.matcher(html);
				Integer[] at = new Integer[40];
				for (int i = 0; i < at.length; i++) {
					at[i]=0;
				}
				int i = 0;
				while (matcher.find()) {
					Map<String, String> columnMap = new HashMap<String, String>();
					columnMap.put("uuid", java.util.UUID.randomUUID().toString());
					columnMap.put("columnname", ObjectUtils.toString(matcher.group(1)));
					at[i] = matcher.end();
					i++;
					chapterInfoList.add(columnMap);
				}
				if (CollectionUtils.isEmpty(chapterInfoList)) {
					at = null;
				}
				if (at != null && at[0] > 0) {
					// 如果at!=null
					for (int j = 1; j < at.length + 1; j++) {
						if (at[j] > 0) {
							columnContent = StringUtils.substring(html, at[j - 1], at[j]);
							parseChapterColumnList(columnContent, fetchurl, chapterInfoList, chaptercontenturlEx, modurltypeEx, isgendongtaiurl, chapterInfoList.get(i - 1).get("uuid"));
						} else {
							columnContent = StringUtils.substring(html, at[j - 1]);
							parseChapterColumnList(columnContent, fetchurl, chapterInfoList, chaptercontenturlEx, modurltypeEx, isgendongtaiurl, chapterInfoList.get(i - 1).get("uuid"));
							break;
						}
					}
				} else {
					parseChapterColumnList(html, fetchurl, chapterInfoList, chaptercontenturlEx, modurltypeEx, isgendongtaiurl, "");
				}
			} else {
				parseChapterColumnList(html, fetchurl, chapterInfoList, chaptercontenturlEx, modurltypeEx, isgendongtaiurl, "");
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

	private static void parseChapterColumnList(String html, String fetchurl, List<Map<String, String>> chapterInfoList, String chaptercontenturlEx, String modurltypeEx, String isgendongtaiurl, String columnNum) {
		Pattern pt;
		Matcher matcher;
		pt = Pattern.compile(chaptercontenturlEx);
		matcher = pt.matcher(html);
		try {
			while (matcher.find()) {
				Map<String, String> chapterMap = new HashMap<String, String>();
				String modrul = matcher.group(1);
				StringUtils.replace(modrul, "&nbsp;", "");
				if ("".equals(modrul.trim()) || (!StringUtils.contains(modrul, ".htm") && !StringUtils.contains(modrul, ".asp") && !StringUtils.contains(modrul, ".jsp")) && !StringUtils.contains(modrul, ".php")) {
					continue;
				}
				// chapterList.put("chaptercontenturl", modrul);
				chapterMap.put("chaptername", matcher.group(2));
				if ("local".equals(modurltypeEx.trim())) {
					// TODO 使用动态地址采集目前未完成
					if ("1".equals(isgendongtaiurl)) {
						String bookid = "", chapterid = "";
						String addurl = StringUtils.substring(fetchurl, 0, fetchurl.lastIndexOf("/"));
						bookid = StringUtils.substring(addurl, addurl.lastIndexOf("/") + 1);
						chapterid = StringUtils.substring(modrul, modrul.lastIndexOf("/") == -1 ? 0 : modrul.lastIndexOf("/"), modrul.lastIndexOf("."));

						// TODO 还未完成
						addurl = StringUtils.substring(addurl, 0, addurl.lastIndexOf("/") + 1) + bookid + "";
					} else {

						String addurl = StringUtils.substring(fetchurl, 0, fetchurl.lastIndexOf("/") + 1);
						chapterMap.put("chaptercontenturl", addurl + modrul);
					}
				} else {

					String addurl = StringUtils.substring(fetchurl, 0, fetchurl.lastIndexOf("/") + 1);
					chapterMap.put("chaptercontenturl", addurl + modrul);
				}
				if(!"".equals(columnNum)){
					chapterMap.put("uuid", columnNum);
				}
				chapterInfoList.add(chapterMap);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static List<Map<String, String>> parseChapterContent(String html, Map<String, String> map) {
		List<Map<String, String>> chapterInfoList = new ArrayList<Map<String, String>>();
		String startEx = map.get("contentstart");
		String endEx = map.get("contentend");
		String fetchTypeEx = map.get("fetchtype");
		String bodyEx = convertRegex(map.get("contentbody"));
		String titleEx = convertRegex(map.get("title"));
		String replaceWord = map.get("replaceword");
		String[] rep = StringUtils.split(replaceWord, "|");
		Pattern pt = null;
		Matcher matcher = null;
		Map<String, String> contentBody = new HashMap<String, String>();
		if (StringUtils.isNotBlank(fetchTypeEx) && "1".equals(fetchTypeEx)) {
			try {
				int start = StringUtils.indexOf(html, startEx) + startEx.length();
				int end = StringUtils.indexOf(html, endEx);
				String content = StringUtils.substring(html, start, end);
				// 处理一下包含、、、域名、、站点名的
				if (rep.length > 0) {
					for (int i = 0; i < rep.length; i++) {
						String[] word = StringUtils.split(rep[i], ",");
						if (word.length == 2) {
							content = content.replaceAll("(?i)" + word[0], StringUtils.equals(word[1], "空") ? "" : word[1]);
						}
					}
				}
				contentBody.put("contentbody", content);
				if (!"".equals(map.get("title"))) {
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
			// 正则表达式方式
		}

		return chapterInfoList;
	}
}
