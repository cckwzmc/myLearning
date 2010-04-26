package com.lyxmq.lottery.football.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.RandomUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.Node;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lyxmq.lottery.football.dao.LotteryFootballDao;
import com.lyxmq.lottery.ssq.service.LotterySsqConifgService;
import com.myfetch.service.http.util.HttpHtmlService;

public class LotteryFootballCustomerDyjService extends Thread {

	private static final Logger logger = LoggerFactory.getLogger(LotteryFootballCustomerDyjService.class);
	private LotteryFootballDao footballLotteryDao = null;

	public void setFootballLotteryDao(LotteryFootballDao footballLotteryDao) {
		this.footballLotteryDao = footballLotteryDao;
	}

	// ~~~~~~~~~~~~~抓取大赢家的用户投注~~~~~~~~~~~
	@Override
	public void run() {
		new LotteryFootballConifgService();
		this.collectDyjCustomerData();
	}

	public void collectDyjCustomerData() {
		this.footballLotteryDao.clearLotteryFootballCollectFetch(LotteryFootballConifgService.getFootballExpect(), "1");
		List<Map<String, String>> list = this.getDyjFootballProject();
		List<Map<String, String>> resultList = new ArrayList<Map<String, String>>();
		for (Map<String, String> map : list) {
			if ("0".equals(map.get("isupload"))) {
				continue;
			}
			if (this.footballLotteryDao.getCountSsqLotteryCollectFetchByProid(map.get("proid"), "1") > 0) {
				continue;
			}
			List<String> pList = this.downloadDyjProject(map.get("id"), map.get("playtype"));
			if (CollectionUtils.isEmpty(pList)) {
				continue;
			}
			Map<String, String> tmpMap = new HashMap<String, String>();
			if (CollectionUtils.isNotEmpty(pList) && "-1".equals(pList.get(0))) {
				tmpMap.put("proid", map.get("proid"));
				tmpMap.put("net", "1");
				tmpMap.put("expect", LotterySsqConifgService.getExpect());
				tmpMap.put("code", "-1");
				tmpMap.put("isfail", "1");
				continue;
			}
			String[] codes = pList.toArray(new String[pList.size()]);
			tmpMap.put("proid", map.get("proid"));
			tmpMap.put("net", "1");
			tmpMap.put("expect", LotterySsqConifgService.getExpect());
			tmpMap.put("code", StringUtils.join(codes, "@@"));
			tmpMap.put("isfail", "0");
			resultList.add(tmpMap);
			if (resultList.size() > 200) {
				this.dao.batchSaveSsqLotteryCollectFetch(resultList);
				resultList.clear();
			}
		}
		if (CollectionUtils.isNotEmpty(resultList)) {
			this.dao.batchSaveSsqLotteryCollectFetch(resultList);
			resultList.clear();
		}
		logger.info("========" + "大赢家胜负彩抓取完成..............................................");
	}

	/**
	 * 小数点后16位
	 * 
	 * @return
	 */
	public String genRandom() {
		double rnd = RandomUtils.nextDouble();
		String d = ObjectUtils.toString(rnd);
		if (d.length() >= 18) {
			d = d.substring(0, 18);
		} else {
			for (int j = 1; j <= 18; j++) {
				if (d.length() < j) {
					d += "0";
				}
			}
		}
		return d;
	}

	/**
	 * 分页下载大赢家胜负彩
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private List<Map<String, String>> getDyjFootballProject() {
		String url = LotteryFootballConifgService.getDyjFootballUrl();
		List<Map<String, String>> retList = new ArrayList<Map<String, String>>();
		int k = 0;
		for (int i = 1; i < 100; i++) {
			if (k > 3) {
				break;
			}
			String dyjXmlData = HttpHtmlService.getXmlContent(StringUtils.replace(StringUtils.replace(url, "@pageno@", i + ""), "@random@", this.genRandom()), "GB2312");
			logger.info(StringUtils.replace(StringUtils.replace(url, "@pageno@", i + ""), "@random@", this.genRandom()));
			Document document = null;
			try {
				document = DocumentHelper.parseText(dyjXmlData);
			} catch (DocumentException e) {
				logger.error("parser xml error===" + e.getMessage());
				logger.error(dyjXmlData);
			}
			List list = new ArrayList();
			try {
				list = document.selectNodes("//xml/row");
			} catch (Exception e) {
			}
			if (CollectionUtils.isEmpty(list)) {
				k++;
			}
			for (Iterator iterator = list.iterator(); iterator.hasNext();) {
				Node node = (Node) iterator.next();
				Element e = (Element) node;
				Map<String, String> map = new HashMap<String, String>();
				map.put("id", e.attributeValue("id"));
				map.put("proid", e.attributeValue("proid"));
				map.put("playtype", e.attributeValue("playtype"));
				map.put("isupload", e.attributeValue("isupload"));
				retList.add(map);
			}
			try {
				sleep(5000);
			} catch (InterruptedException e) {
				notify();
			}
		}
		return retList;
	}
}
