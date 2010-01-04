package com.lyxmq.lottery.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
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
	private static String url_prex = "http://www.500wan.com/static/info/ssq/mediadetail/";
	private static String testUrl = "http://www.500wan.com/static/info/ssq/mediadetail/09154.xml";
	String[] xmlData = new String[100];
	private static int avRedNum = 0, avRedFen = 0, avCount = 0;
	private static int count = 0;
	private static List<String> redResultList = new ArrayList<String>();
	private static Map<String, String> redStatMap = new HashMap<String, String>();
	private static Map<String, Integer> rsNumMap = new HashMap<String, Integer>();
	private static Map<String, Integer> rsFenMap = new HashMap<String, Integer>();
	private static String path = "D:/xml/media";
	private static String[] redBall = { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21",
			"22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33" };
	// private static String[] blueBall = { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16" };
	// private static String[] redBall = null;
	private static String[] blueBall = null;
	private static String pXmlData = "";
	private static int oneQu=2;
	private static int twoQu=3;
	private static int threeQu=1;

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		TestMain test = new TestMain();
		// select(5);
		String xmlData = getXmlData(testUrl);
		pXmlData = xmlData;
		// System.out.println(xmlData);
		// List<Map<String, List<String>>> list = test.disposeXmlData(xmlData);
		// test.disposeResultData(list);
		test.disposeCurrentData();
		System.out.println(isStatHistoryData);
		try {
			// 历史数据统计
			// disposeHistoryData();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void disposeCurrentData() {
		disposeXmlStatData(pXmlData);
		select(6);
	}

	private static void disposeHistoryData() throws FileNotFoundException, IOException {
		TestMain test = new TestMain();
		File file = new File(path);
		File[] files = file.listFiles();
		String content = "";
		for (int i = 0; i < files.length; i++) {
			if (files[i].getName().startsWith("07")) {
				avCount++;
				continue;
			}
			content = "";
			FileReader reader = new FileReader(files[i]);
			BufferedReader br = new BufferedReader(reader);
			String tmp = "";
			while ((tmp = br.readLine()) != null) {
				content += tmp;
			}
			br.close();
			reader.close();
			test.disposeMediaAll(content);
		}
		for (Iterator iterator = rsNumMap.keySet().iterator(); iterator.hasNext();) {
			String key = (String) iterator.next();
			System.out.println(key + "\t" + rsNumMap.get(key));
		}
		System.out.println("---\t---");
		for (Iterator iterator = rsFenMap.keySet().iterator(); iterator.hasNext();) {
			String key = (String) iterator.next();
			System.out.println(key + "\t" + rsFenMap.get(key));
		}
		System.out.println(avRedNum / (files.length - avCount));
		System.out.println(avRedFen / (files.length - avCount));
	}

	// public static void main(String[] args) {
	// saveYuceXml();
	// }

	private void disposeResultData(List<Map<String, List<String>>> list) {
		Map<String, Integer> resultMap = new HashMap<String, Integer>();
		for (int i = 0; i < list.size(); i++) {
			Map<String, List<String>> map = list.get(i);
			for (Iterator<String> it = map.keySet().iterator(); it.hasNext();) {
				List<String> mapList = map.get(it.next());
				for (Iterator<String> iterator = mapList.iterator(); iterator.hasNext();) {
					String value = (String) iterator.next();
					if (resultMap.containsKey(value)) {
						Integer rValue = resultMap.remove(value);
						resultMap.put(value, rValue + 1);
					} else {
						resultMap.put(value, new Integer("1"));
					}
				}
			}
		}
		int max = 0;
		for (Iterator<String> iterator = resultMap.keySet().iterator(); iterator.hasNext();) {
			String key = iterator.next();
			if (max == 0) {
				max = resultMap.get(key).intValue();
			} else if (resultMap.get(key).intValue() > max) {
				max = resultMap.get(key).intValue();
			}
		}
		for (Iterator<String> iterator = resultMap.keySet().iterator(); iterator.hasNext();) {
			String key = iterator.next();
			if (resultMap.get(key).intValue() == max) {
				System.out.println(key + "  " + resultMap.get(key));
			}
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
				// System.out.print(i + "===");
				// System.out.println(StringUtils.join(r, ","));
				// redResultList.add(StringUtils.join(r, ","));
				String str=printStatData(StringUtils.join(r, ","));
				if(StringUtils.isNotBlank(str)){
					System.out.println(str);
				}
				subselect(i + 1, index + 1, r, k);
				count++;
			} else {
				return;
			}

		}
	}

	private static String printStatData(String redResult) {
		isStatHistoryData++;
		int totalNum = 0;
		int totalFen = 0;
		String[] red = StringUtils.split(redResult, ",");
		int oneTmp=0;
		int twoTmp=0;
		int threeTmp=0;
		for (int i = 0; i < red.length; i++) {
			int tmp=NumberUtils.toInt(red[i]);
			if(tmp>0&&tmp<=11){
				oneTmp++;
			}
			if(tmp>11&&tmp<=22){
				twoTmp++;
			}
			if(tmp>21&&tmp<=33){
				threeTmp++;
			}
		}
		if(oneTmp!=oneQu||twoTmp!=twoQu||threeTmp!=threeQu)
			return null;
		for (int i = 0; i < red.length; i++) {
			if(StringUtils.equals(redResult,"1,7,12,14,18,25")){
				System.out.println();
			}
			if (redStatMap.containsKey(red[i])) {
				String value = redStatMap.get(red[i]);
				String[] values = StringUtils.split(value,"|");
				totalNum += NumberUtils.toInt(values[0]);
				totalFen += NumberUtils.toInt(values[1].replace("%", ""));
			}
		}

		int avNum=65;
		int avFen=15;
		if (totalNum <= (avNum + 10) && totalNum >= (avNum - 10) && totalFen <= (avFen + 5) && totalFen >= (avFen - 5)) {
			
			return redResult;
		}
		return null;
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
						resultList.add(redResult + "+" + blueCodeArr[j]);
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

	@SuppressWarnings("unchecked")
	private static Map<String, String> disposeXmlStatData(String xmlData) {
		try {
			Document document = DocumentHelper.parseText(xmlData);
			List redCode = document.selectNodes("//xml/red/code");
			Map<String, String> redMap = new HashMap<String, String>();
			for (int i = 0; i < redCode.size(); i++) {
				Node redRs = (Node) redCode.get(i);
				String flag = ((Element) redRs).attributeValue("flag");
				String code = redRs.getText();
				String num = ((Element) redRs).attributeValue("num");
				String fen = ((Element) redRs).attributeValue("fen");
				redMap.put(code, num + "|" + fen);
			}
			redStatMap = redMap;
			return redMap;
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	private void disposeMediaAll(String xmlData) {
		Document document = null;
		try {
			document = DocumentHelper.parseText(xmlData);
		} catch (DocumentException e) {
		}
		List rsRedNode = null;
		try {
			rsRedNode = document.selectNodes("//xml/red/code");
		} catch (Exception e) {
			System.out.println(rsRedNode);
			avCount++;
			return;
		}
		int num = 0;
		int fen = 0;
		for (int i = 0; i < rsRedNode.size(); i++) {
			Node redRs = (Node) rsRedNode.get(i);
			String flag = ((Element) redRs).attributeValue("flag");
			if ("1".equals(flag)) {
				String redNum = ((Element) redRs).attributeValue("num");
				String redFen = ((Element) redRs).attributeValue("fen");
				num += NumberUtils.toInt(redNum);
				fen += NumberUtils.toInt(StringUtils.remove(redFen, "%"));
			}
		}
		avRedNum += num;
		avRedFen += fen;
		if (rsNumMap.containsKey(String.valueOf(num))) {
			Integer value = rsNumMap.get(String.valueOf(num));
			rsNumMap.put(String.valueOf(num), value + 1);
		} else {
			rsNumMap.put(String.valueOf(num), 1);
		}
		if (rsFenMap.containsKey(String.valueOf(fen))) {
			Integer value = rsFenMap.get(String.valueOf(fen));
			rsFenMap.put(String.valueOf(fen), value + 1);
		} else {
			rsFenMap.put(String.valueOf(fen), 1);
		}
		// System.out.println(num);
		// System.out.println(fen);
		// System.out.println("~~~~~~~~~~~~~~~~~~~~~~~");
	}

	private static String getXmlData(String url) {
		return HttpHtmlService.getXmlContent(url);
	}

	private static void saveYuceXml() {
		TestMain test = new TestMain();
		String xmlData = "";
		int tmpCurrentExpert = currentExpert;
		int year = 7;
		for (int j = year; j > 6; j--) {
			String expert = String.valueOf(tmpCurrentExpert);
			for (int i = 0; i < 154; i++) {
				if ("100".equals(expert) || "011".equals(expert) || "010".equals(expert) || "009".equals(expert)) {
					tmpCurrentExpert--;
					if (tmpCurrentExpert < 100 && tmpCurrentExpert >= 10) {
						expert = "0" + tmpCurrentExpert;
					} else if (tmpCurrentExpert > 0 && tmpCurrentExpert < 10) {
						expert = "00" + tmpCurrentExpert;
					} else {
						expert = String.valueOf(tmpCurrentExpert);
					}
					continue;
				}
				if (tmpCurrentExpert < 100 && tmpCurrentExpert >= 10) {
					expert = "0" + tmpCurrentExpert;
				} else if (tmpCurrentExpert > 0 && tmpCurrentExpert < 10) {
					expert = "00" + tmpCurrentExpert;
				} else {
					expert = String.valueOf(tmpCurrentExpert);
				}
				String url = url_prex + "0" + year + expert + ".xml";
				try {
					xmlData = getXmlData(url);
				} catch (Exception e) {
					tmpCurrentExpert--;
					if (tmpCurrentExpert < 100 && tmpCurrentExpert >= 10) {
						expert = "0" + tmpCurrentExpert;
					} else if (tmpCurrentExpert > 0 && tmpCurrentExpert < 10) {
						expert = "00" + tmpCurrentExpert;
					} else {
						expert = String.valueOf(tmpCurrentExpert);
					}
					continue;
				}
				try {
					File file = new File("d:/xml/" + "0" + year + expert + ".xml");
					if (!file.exists()) {
						file.createNewFile();
					} else {
					}
					FileWriter writer = new FileWriter(file);
					writer.write(xmlData);
					writer.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
				tmpCurrentExpert--;
				if (tmpCurrentExpert < 100 && tmpCurrentExpert > 10) {
					expert = "0" + tmpCurrentExpert;
				} else if (tmpCurrentExpert > 0 && tmpCurrentExpert < 10) {
					expert = "00" + tmpCurrentExpert;
				} else {
					expert = String.valueOf(tmpCurrentExpert);
				}
			}
		}
	}
}
