package com.lottery.ssq.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.Node;

import com.lottery.util.html.HttpHtmlService;

public class LotterySsqMediaUtils {
	/**
	 * 解析红球
	 * 
	 * @param document
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<String[]> getMediaRedCode(Document document) {
		List<String[]> list = new ArrayList<String[]>();
		List media = document.selectNodes("//xml/media");
		for (Iterator iterator = media.iterator(); iterator.hasNext();) {
			Node node = (Node) iterator.next();
			List redNode = node.selectNodes("tjcode/redcode/code");
			String[] redCodeArr = null;
			if (CollectionUtils.isNotEmpty(redNode)) {
				redCodeArr = new String[redNode.size()];
			}
			for (int i = 0; i < redNode.size(); i++) {
				Node red = (Node) redNode.get(i);
				redCodeArr[i] = red.getText().length()==1?"0"+red.getText():red.getText();
			}
			list.add(redCodeArr);
		}
		return list;
	}

	/**
	 * 解析蓝球
	 * 
	 * @param document
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<String[]> getMediaBlueCode(Document document) {
		List media = document.selectNodes("//xml/media");
		List<String[]> list = new ArrayList<String[]>();
		for (Iterator iterator = media.iterator(); iterator.hasNext();) {
			Node node = (Node) iterator.next();
			List blueNode = node.selectNodes("tjcode/bluecode/code");
			String[] blueCodeArr = null;
			if (CollectionUtils.isNotEmpty(blueNode)) {
				blueCodeArr = new String[blueNode.size()];
			}
			for (int i = 0; i < blueNode.size(); i++) {
				Node blue = (Node) blueNode.get(i);
				blueCodeArr[i] = blue.getText().length()==1?"0"+blue.getText():blue.getText();
			}
			list.add(blueCodeArr);
		}
		return list;
	}

	/**
	 * 历史开奖红球
	 * 
	 * @param document
	 * @return
	 */
	public static String[] getHistoryOpenRedcode(Document document) {
		Node kjjg = document.selectSingleNode("//xml/head/kjflag");
		String kjflag = ((Element) kjjg).getText();
		String[] openRedCode = new String[6];
		if (StringUtils.isNotBlank(kjflag) && "1".equals(kjflag)) {
			List redCode = document.selectNodes("//xml/head/opencode/redcode/code");
			for (int i = 0; i < redCode.size(); i++) {
				Node node = (Node) redCode.get(i);
				openRedCode[i] = node.getText();
			}
		}
		return openRedCode;
	}

	/**
	 * 历史蓝球开奖
	 * 
	 * @param document
	 * @return
	 */
	public static String getHistoryOpenBlueCode(Document document) {
		Node kjjg = document.selectSingleNode("//xml/head/kjflag");
		String kjflag = ((Element) kjjg).getText();
		String openBlueCode = "";
		if (StringUtils.isNotBlank(kjflag) && "1".equals(kjflag)) {
			Node blueCode = document.selectSingleNode("//xml/head/opencode/bluecode");
			openBlueCode = blueCode.getText();
		}
		return openBlueCode;
	}

	/**
	 * 获得期数
	 * 
	 * @param document
	 * @return
	 */
	public static String getMediaExpect(Document document) {
		Node expect = document.selectSingleNode("//xml/head/expect");
		return ((Element) expect).getText();
	}

	/**
	 * 按单注方式生成红球号码
	 * 
	 * @param document
	 */
	public static List<String> parseCurrentMediaRedCode(List<String[]> redList) {
		List<String> resultList = new ArrayList<String>();
		for (Iterator<String[]> iterator = redList.iterator(); iterator.hasNext();) {
			String[] red = (String[]) iterator.next();
			LotteryUtils.select(6, red, resultList);
		}
		return resultList;
	}

	/**
	 * 媒体的红球推荐统计
	 * 
	 * @param xmlData
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static Map<String, String> getMediaRedCodeStat(Document document) {
		List redCode = document.selectNodes("//xml/red/code");
		Map<String, String> redMap = new HashMap<String, String>();
		for (int i = 0; i < redCode.size(); i++) {
			Node redRs = (Node) redCode.get(i);
			String code =  redRs.getText().length()==1?"0"+redRs.getText():redRs.getText();
			String num = ((Element) redRs).attributeValue("num");
			String fen = ((Element) redRs).attributeValue("fen");
			redMap.put(code, num + "|" + StringUtils.replace(fen,"%",""));
		}
		return redMap;
	}

	/**
	 * 媒体的蓝球推荐统计
	 * 
	 * @param xmlData
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static Map<String, String> getMediaBlueCodeStat(Document document) {
		List redCode = document.selectNodes("//xml/blue/code");
		Map<String, String> redMap = new HashMap<String, String>();
		for (int i = 0; i < redCode.size(); i++) {
			Node redRs = (Node) redCode.get(i);
			String code = redRs.getText().length()==1?"0"+redRs.getText():redRs.getText();
			String num = ((Element) redRs).attributeValue("num");
			String fen = ((Element) redRs).attributeValue("fen");
			redMap.put(code, num + "|" + StringUtils.replace(fen,"%",""));
		}
		return redMap;
	}

	/**
	 * 获得单期的媒体推荐的XML内容
	 * @param expect
	 * @return
	 */
	public static String getHistoryMediaXml(String expect) {
		String url = "http://www.500wan.com/static/info/ssq/mediadetail/";// 10001.xml
		String content = "";
		url += expect + ".xml";
		try {
			content = HttpHtmlService.getXmlContent(url);
		} catch (Exception e) {

		}
		return content;
	}
}
