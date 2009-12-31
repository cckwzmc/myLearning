package com.lyxmq.lottery.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.Node;

import com.myfetch.service.http.HttpHtmlService;

public class TestMain {
	// http://www.500wan.com/static/info/ssq/mediadetail/09154.xml
	// 是否统计历史数据 0不统计 1 统计
	private static int isStatHistoryData = 0;
	private static String pre_defix = "09";
	private static int currentExpert = 154;
	private static int historyData = 150;
	private static String testUrl = "http://www.500wan.com/static/info/ssq/mediadetail/09153.xml";
	String[] xmlData = new String[100];
	private static int count = 0;
	private static List<String> redResultList = new ArrayList<String>();

	public static void main(String[] args) {
		TestMain test = new TestMain();
		// select(5);
		String xmlData = getXmlData(testUrl);
		System.out.println(xmlData);
		List<Map<String, List<String>>> list = test.disposeXmlData(xmlData);
		test.disposeResultData(list);
	}

	private void disposeResultData(List<Map<String, List<String>>> list) {
		Map<String, Integer> resultMap = new HashMap<String, Integer>();
		for (int i = 0; i < list.size(); i++) {
			Map<String, List<String>> map = list.get(i);
			for (Iterator<String> it = map.keySet().iterator(); it.hasNext();) {
				List<String> mapList = map.get(it.next());
				for (Iterator<String> iterator = mapList.iterator(); iterator.hasNext();) {
					String value = (String) iterator.next();
					if(resultMap.containsKey(value)){
						Integer rValue=resultMap.remove(value);
						resultMap.put(value, rValue+1);
					}else{
						resultMap.put(value, new Integer("1"));
					}
				}
			}
		}
		for (Iterator<String> iterator = resultMap.keySet().iterator(); iterator.hasNext();) {
			String key = iterator.next();
			System.out.println(key+"  "+resultMap.get(key));
		}
	}

	private static void select(int k) {
		String[] result = new String[k];
		subselect(0, 1, result, k);

	}

	private static void subselect(int head, int index, String[] r, int k) {
		for (int i = head; i < redBall.length + index - k; i++) {
			if (index < k) {
				r[index - 1] = redBall[i];
				subselect(i + 1, index + 1, r, k);
				count++;
			} else if (index == k) {
				r[index - 1] = redBall[i];
//				System.out.print(i + "===");
//				System.out.println(StringUtils.join(r, ","));
				redResultList.add(StringUtils.join(r, ","));
				subselect(i + 1, index + 1, r, k);
				count++;
			} else {
				return;
			}

		}
	}

	@SuppressWarnings("unchecked")
	private List disposeXmlData(String xmlData) {
		try {
			Document document = DocumentHelper.parseText(xmlData);
			Node kjjg = document.selectSingleNode("//xml/head/kjflag");
			String kjflag = ((Element) kjjg).getText();
			if (StringUtils.isNotBlank(kjflag) && "1".equals(kjflag)) {

			}
			List<Map<String, List<String>>> list = new ArrayList<Map<String, List<String>>>();
			List media = document.selectNodes("//xml/media");
			for (Iterator iterator = media.iterator(); iterator.hasNext();) {
				Node node = (Node) iterator.next();
				Node nameNode = node.selectSingleNode("name");
				String name = nameNode.getText();
				String id = ((Element) nameNode).attributeValue("ID");
				List redNode = node.selectNodes("tjcode/redcode/code");
				String[] redCodeArr = null;
				if (CollectionUtils.isNotEmpty(redNode)) {
					redCodeArr = new String[redNode.size()];
				}
				for (int i = 0; i < redNode.size(); i++) {
					Node red = (Node) redNode.get(i);
					redCodeArr[i] = red.getText();
				}
				List blueNode = node.selectNodes("tjcode/bluecode/code");
				String[] blueCodeArr = null;
				if (CollectionUtils.isNotEmpty(blueNode)) {
					blueCodeArr = new String[blueNode.size()];
				}
				for (int i = 0; i < blueNode.size(); i++) {
					Node blue = (Node) blueNode.get(i);
					blueCodeArr[i] = blue.getText();
				}
				redBall = redCodeArr;
				blueBall = blueCodeArr;

				select(6);
				List<String> resultList = new ArrayList<String>();
				for (int i = 0; i < redResultList.size(); i++) {
					String redResult = redResultList.get(i);
					for (int j = 0; j < blueCodeArr.length; j++) {
						resultList.add(redResult+"+" + blueCodeArr[j]);
					}
					
				}
				Map<String, List<String>> map = new HashMap<String, List<String>>();
				map.put(id + "|" + name, resultList);
				list.add(map);
				redResultList = new ArrayList<String>();
			}
			return list;
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		return null;
	}

	private static String getXmlData(String url) {
		return HttpHtmlService.getXmlContent(url);
	}

	// private static String[] redBall = { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21",
	// "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33" };
	// private static String[] blueBall = { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16" };
	private static String[] redBall = null;
	private static String[] blueBall = null;

}
