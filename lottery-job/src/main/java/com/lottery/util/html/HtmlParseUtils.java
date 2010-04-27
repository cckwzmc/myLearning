package com.lottery.util.html;

import java.util.ArrayList;
import java.util.List;

import net.htmlparser.jericho.Attribute;
import net.htmlparser.jericho.Attributes;
import net.htmlparser.jericho.Element;
import net.htmlparser.jericho.Source;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * nekoHtml不知道为什么没用了， 这个htmlparser用的net.htmlparser.jericho
 * 
 * @author 李毅
 * 
 */
public class HtmlParseUtils {
	private static Logger logger = LoggerFactory.getLogger(HtmlParseUtils.class);

	public static String getElementById(String html, String id) {
		Source source = new Source(html);
		Element element = source.getElementById(id);
		return !element.isEmpty() ? element.toString() : "";
	}

	public static NameValuePair[] getElementsPostData(String html, String charset) {
		Source source = new Source(html);
		// 设置网页的默认编码
		List<Element> input = source.getAllElements("input");
		List<Element> select = source.getAllElements("select");
		List<Element> textarea = source.getAllElements("textarea");
		List<Element> list = new ArrayList<Element>();
		for (int i = 0; i < input.size(); i++) {
			list.add(input.get(i));
		}
		for (int i = 0; i < select.size(); i++) {
			list.add(select.get(i));
		}
		for (int i = 0; i < textarea.size(); i++) {
			list.add(textarea.get(i));
		}
		NameValuePair[] data = new BasicNameValuePair[input.size() + select.size() + textarea.size()];
		for (int i = 0; i < list.size(); i++) {
			Element element = list.get(i);
			Attributes attributes = element.getAttributes();
			String name = "";
			String value = "";
			for (int j = 0; j < attributes.size(); j++) {
				Attribute attribute = attributes.get(j);
				if ("name".equals(attribute.getName())) {
					name = attribute.getValue();
				}
				if ("value".equals(attribute.getName())) {
					value =attribute.getValue();
				}
			}
			if ("blog_body".equals(name)) {
				value = "kkkkkkkkkkkkkkk";
			}
			data[i] = new BasicNameValuePair(name, value);
			// }
		}
		return data;
	}

	public static String getElementByTagName(String html, String tagName) {
		Source source = new Source(html);
		List<Element> list = source.getAllElements(tagName);
		String retStr = "";
		if (CollectionUtils.isNotEmpty(list)) {
			for (Element e : list) {
				if (StringUtils.isBlank(retStr)) {
					retStr = e.toString();
				} else {
					retStr += e.toString();
				}
			}
		}
		return retStr;
	}
	public static List<Element> getElementListByTagName(String html, String tagName) {
		Source source = new Source(html);
		List<Element> list = source.getAllElements(tagName);
		return list;
	}
	/**
	 * get list.get(i);
	 * start 0
	 * @param html
	 * @param tagName
	 * @param i
	 * @return
	 */
	public static String getElementByTagName(String html, String tagName,int i) {
		Source source = new Source(html);
		List<Element> list = source.getAllElements(tagName);
		String retStr = "";
		if (CollectionUtils.isNotEmpty(list)) {
			if(list.size()>=i){
				retStr=(list.get(i)).toString();
			}
			
		}
		return retStr;
	}
}
