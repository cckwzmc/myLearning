package com.lyxmq.lottery.football.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.Node;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lyxmq.lottery.football.dao.LotteryFootballDao;
import com.myfetch.service.http.util.HttpHtmlService;

/**
 * 媒体推荐的处理
 * 
 * @author Administrator
 */
public class LotteryFootballCustomer500WanService extends Thread {
	private static Logger log = LoggerFactory.getLogger(LotteryFootballCustomer500WanService.class);
	private LotteryFootballDao footballLotteryDao = null;

	public void setFootballLotteryDao(LotteryFootballDao footballLotteryDao) {
		this.footballLotteryDao = footballLotteryDao;
	}

	private void initConfig() {
		new LotteryFootballConifgService();
	}

	/**
	 * 解析XML返回原始数据
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<String> parserFtMediaXmlData(String xmlData) {
		if (StringUtils.isBlank(xmlData)) {
			return null;
		}
		Document doc = null;
		try {
			doc = DocumentHelper.parseText(xmlData);
		} catch (DocumentException e) {
			log.error("xml parse erro =============" + e.getMessage());
			log.error(xmlData);
		}
		List<String> result = new ArrayList<String>();
		List list = doc.selectNodes("//xml/media/tjcode");
		for (Iterator iterator = list.iterator(); iterator.hasNext();) {
			Node node = (Node) iterator.next();
			List codeList = node.selectNodes("code");
			String code = "";
			for (Iterator iterator2 = codeList.iterator(); iterator2.hasNext();) {
				Node n = (Node) iterator2.next();
				Element e = (Element) n;
				if ("".equals(code)) {
					code = e.getText();
				} else {
					code += "," + e.getText();
				}
			}
			result.add(code);
		}
		return result;
	}

	/**
	 * 从数据库中读取当期的500WAN媒体推荐胜负彩
	 * 
	 * @param expect
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String getCurrentExpectXmlDataFromDb(String expect) {
		if (StringUtils.isBlank(expect)) {
			expect = LotteryFootballConifgService.getFootballExpect();
		}
		Map map = this.footballLotteryDao.getFtLotteryMedia(expect, "0");
		if (MapUtils.isEmpty(map)) {
			return null;
		}
		return (String) map.get("content");
	}

	/**
	 * 从网络读取当期的500WAN媒体推荐胜负彩
	 * 
	 * @param expect
	 * @return
	 */
	public String getCurrentExpectXmlDataFromNet(String expect) {
		if (StringUtils.isBlank(expect)) {
			expect = LotteryFootballConifgService.getFootballExpect();
		}
		String content = HttpHtmlService.getHtmlContent(LotteryFootballConifgService.getWww500wanFootballMedia(), "UTF-8");
		return content;
	}

	// ~~~~~~~~~~~~~~~~~~下面是抓取程序
	public void run() {
		collectCustomer500WanData();
	}

	private void collectCustomer500WanData() {
		this.initConfig();
		List<Map<String, String>> list = this.fetchMediaHtmlData();
		if (CollectionUtils.isNotEmpty(list)) {
			this.saveCurrentExpectFootballCustom(list);
		}
	}

	/**
	 * 保存抓取的数据
	 * 
	 * @param list
	 */
	public void saveCurrentExpectFootballCustom(List<Map<String, String>> list) {
		if (CollectionUtils.isEmpty(list)) {
			return;
		}
		List<String> resultList = new ArrayList<String>();
		for (Map<String, String> code : list) {
			String tmpCode = StringUtils.remove(code.get("code"), "'");
			tmpCode = StringUtils.remove(tmpCode, "\"");
			tmpCode = StringUtils.remove(tmpCode, "[");
			tmpCode = StringUtils.remove(tmpCode, "]");
			// String[][] tjCode = FootballLotteryUtils.splitFootballCode(tmpCode);
			// List<String> aCode = FootballLotteryUtils.doCallAllCode(tjCode);
			if (StringUtils.isNotBlank(tmpCode)) {
				resultList.add(tmpCode);
			}
			if (resultList.size() > 2000) {
				this.footballLotteryDao.batchSaveFootballCollectionFetch(resultList, 0, LotteryFootballConifgService.getFootballExpect());
				resultList.clear();
			}
		}
		if (CollectionUtils.isNotEmpty(resultList)) {
			this.footballLotteryDao.batchSaveFootballCollectionFetch(resultList, 0, LotteryFootballConifgService.getFootballExpect());
			resultList.clear();
		}
	}

	/**
	 * 抓取html页面 playid=1复式 playid=3单式
	 * 
	 * @param expect
	 * @return
	 */
	public List<Map<String, String>> fetchMediaHtmlData() {
		this.footballLotteryDao.clearLotteryFootballCollectFetch(LotteryFootballConifgService.getFootballExpect(),"0");
		List<Map<String, String>> retList = new ArrayList<Map<String, String>>();
		String url = LotteryFootballConifgService.getWww500wanFootballUrl();
		int k = 0;
		for (int i = 1; i < 120; i++) {
			String htmlData = HttpHtmlService.getHtmlContent(StringUtils.replace(StringUtils.replace(StringUtils.replace(url, "@footballExpect@", LotteryFootballConifgService.getFootballExpect()), "@page@", i + ""), "@playid@", "1"), "gb2312");
			log.info(StringUtils.replace(StringUtils.replace(StringUtils.replace(url, "@footballExpect@", LotteryFootballConifgService.getFootballExpect()), "@page@", i + ""), "@playid@", "1"));
			if (StringUtils.isBlank(htmlData) || htmlData.length() < 100) {
				k++;
				continue;
			}
			if (k > 5) {
				break;
			}
			JSONArray array = new JSONArray();
			JSONObject obj = new JSONObject();
			try {
				array = JSONArray.fromObject("[" + htmlData + "]");
				obj = JSONObject.fromObject(array.get(0));
				array = JSONArray.fromObject(obj.getString("data"));
			} catch (Exception e) {
				log.error(htmlData);
			}
			if (array.isEmpty()) {
				k++;
				continue;
			}
			for (int j = 0; j < array.size(); j++) {
				obj = JSONObject.fromObject(array.get(j));
				String fangan = "";
				try {
					fangan = ObjectUtils.toString(obj.getString("fangan"));
				} catch (Exception e) {

				}
				String isupload = obj.getString("isupload");
				if (!StringUtils.equals(isupload, "1")) {
					continue;
				}
				if (fangan.indexOf("保密") != -1) {
					continue;
				}
				if (fangan.indexOf("未提交") != -1) {
					continue;
				}
				if (fangan.indexOf("公开") != -1) {
					continue;
				}
				Map<String, String> map = new HashMap<String, String>();
				map.put("code", obj.getString("code"));
				retList.add(map);
			}
		}
		for (int i = 1; i < 120; i++) {
			String htmlData = HttpHtmlService.getHtmlContent(StringUtils.replace(StringUtils.replace(StringUtils.replace(url, "@footballExpect@", LotteryFootballConifgService.getFootballExpect()), "@page@", i + ""), "@playid@", "3"), "gb2312");
			log.info(StringUtils.replace(StringUtils.replace(StringUtils.replace(url, "@footballExpect@", LotteryFootballConifgService.getFootballExpect()), "@page@", i + ""), "@playid@", "3"));
			if (StringUtils.isBlank(htmlData) || htmlData.length() < 100) {
				k++;
				continue;
			}
			if (k > 5) {
				break;
			}
			JSONArray array = new JSONArray();
			JSONObject obj = new JSONObject();
			try {
				array = JSONArray.fromObject("[" + htmlData + "]");
				obj = JSONObject.fromObject(array.get(0));
				array = JSONArray.fromObject(obj.getString("data"));
			} catch (Exception e) {
				log.error(htmlData);
			}
			if (array.isEmpty()) {
				k++;
				continue;
			}
			for (int j = 0; j < array.size(); j++) {
				obj = JSONObject.fromObject(array.get(j));
				String fangan = "";
				try {
					fangan = ObjectUtils.toString(obj.getString("fangan"));
				} catch (Exception e) {

				}
				String isupload = obj.getString("isupload");
				if (!StringUtils.equals(isupload, "1")) {
					continue;
				}
				if (fangan.indexOf("保密") != -1) {
					continue;
				}
				if (fangan.indexOf("未提交") != -1) {
					continue;
				}
				if (fangan.indexOf("公开") != -1) {
					continue;
				}
				Map<String, String> map = new HashMap<String, String>();
				map.put("code", obj.getString("code"));
				retList.add(map);
			}
		}
		return retList;
	}
}
