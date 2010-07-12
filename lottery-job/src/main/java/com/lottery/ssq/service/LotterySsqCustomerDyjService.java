package com.lottery.ssq.service;

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

import com.lottery.ssq.config.LotterySsqConfig;
import com.lottery.ssq.config.LotterySsqFetchConfig;
import com.lottery.ssq.dao.LotteryDao;
import com.lottery.ssq.utils.LotterySsqUtils;
import com.lottery.util.html.HttpHtmlService;

/**
 * 大赢家用户购买/收集的数据处理
 * 
 * @author LIYI
 */
public class LotterySsqCustomerDyjService extends Thread {
	private static final Logger logger = LoggerFactory.getLogger(LotterySsqCustomerDyjService.class);
	private LotteryDao dao = null;

	public void setDao(LotteryDao dao) {
		this.dao = dao;
	}

	public void run() {
		this.fetchDyjProjectCode();
	}

	/**
	 * 获取用户的各种方案
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Map<String, String>> getDyjProject() {
		new LotterySsqConifgService();
		String url = LotterySsqFetchConfig.dyjUrl;
		List<Map<String, String>> retList = new ArrayList<Map<String, String>>();
		int k = 0;
		for (int i = 1; i < 100; i++) {
			if (k > 3) {
				break;
			}
			String dyjXmlData = HttpHtmlService.getXmlContent(StringUtils.replace(StringUtils.replace(url, "@pageno@",
					i + ""), "@random@", this.genRandom()), "GB2312");
			logger
					.info(StringUtils.replace(StringUtils.replace(url, "@pageno@", i + ""), "@random@", this
							.genRandom()));
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
				sleep(6000);
			} catch (InterruptedException e) {
				notify();
			}
		}
		return retList;
	}

	/**
	 * 下载用户方案
	 * 
	 * @param id
	 * @param playtype
	 * @return
	 */
	public List<String> downloadDyjProject(String id, String playtype) {
		List<String> list = new ArrayList<String>();
		String url = StringUtils.replace(StringUtils.replace(LotterySsqFetchConfig.dyjDowload, "@id@", id),
				"@playtype@", playtype);
		if (!"3".equals(playtype)) {
			// download.asp/downcode.asp
			url = StringUtils.replace(url, "download.asp", "downcode.asp");
		}
		logger.info(url);
		String content = HttpHtmlService.getXmlContent(url, "GB2312");
		try {
			sleep(5000);
		} catch (InterruptedException e) {
			notify();
		}
		if (content.indexOf("该方案") != -1 || content.indexOf("尚未截止") != -1) {
			list.add("-1");
			return null;
		}
		content = StringUtils.replace(content, " + ", "+");
		content = StringUtils.replace(content, " = ", "+");
		content = StringUtils.replace(content, "<br><br>", "\n");
		content = StringUtils.replace(content, "<br>", "\n");
		content = StringUtils.replace(content, "  ", " ");
		content = StringUtils.replace(content, "  ", " ");
		content = StringUtils.replace(content, "  ", " ");
		content = StringUtils.replace(content, "  ", " ");
		content = StringUtils.replace(content, " | ", "+");
		content = StringUtils.replace(content, "|", "+");
		content = StringUtils.replace(content, "=", "+");
		content = StringUtils.replace(content, " \n", "\n");
		content = StringUtils.replace(content, "\t", ",");
		content = StringUtils.replace(content, ".", ",");
		content = StringUtils.replace(content, " + ", "+");
		content = StringUtils.replace(content, " +", "+");
		content = StringUtils.replace(content, "+ ", "+");
		String[] contents = StringUtils.split(content, "\n");
		for (int i = 0; i < contents.length; i++) {
			list.add(StringUtils.replace(contents[i], " ", ",").trim());
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	public void saveDyjProjectRedCode() {
		List<String[]> resultList = new ArrayList<String[]>();
		int last = 0;
		int page = 200;
		List list = this.dao.getSsqLotteryCollectFetchLimit(last, page, "1");
		Map<String, Integer> blueMap = new HashMap<String, Integer>();
		blueMap.put("01", 0);
		blueMap.put("02", 0);
		blueMap.put("03", 0);
		blueMap.put("04", 0);
		blueMap.put("05", 0);
		blueMap.put("06", 0);
		blueMap.put("07", 0);
		blueMap.put("08", 0);
		blueMap.put("09", 0);
		blueMap.put("10", 0);
		blueMap.put("11", 0);
		blueMap.put("12", 0);
		blueMap.put("13", 0);
		blueMap.put("14", 0);
		blueMap.put("15", 0);
		blueMap.put("16", 0);
		while (CollectionUtils.isNotEmpty(list)) {
			for (Iterator iterator = list.iterator(); iterator.hasNext();) {
				Map map = (Map) iterator.next();
				String code = ObjectUtils.toString(map.get("code"));
				code = StringUtils.replace(code, "|", "+");
				String[] codes = StringUtils.split(code, "@@");
				for (String ssq : codes) {
					String[] redCode = StringUtils.split(ssq, "+");
					String[] blueCode = null;
					if (redCode.length == 2) {
						blueCode = StringUtils.split(redCode[1], ",");
						for (String blue : blueCode) {
							if (blue.length() > 2||!StringUtils.isNumeric(blue)) {
								continue;
							} else if (blue.length() == 1) {
								blue = "0" + blue;
							}
							if(!blueMap.containsKey(blue)){
								continue;
							}
							Integer tmp = blueMap.get(blue) + 1;
							blueMap.put(blue, tmp);
						}
					}
					String[] redCodes = StringUtils.split(redCode[0], ",");
					if (redCodes.length < 6 || redCodes.length > 20) {
						logger.error("方案解析失败==" + ssq);
						continue;
					}
					LotterySsqUtils.selectArray(6, redCodes, resultList);
					if (resultList.size() > 2000) {
						this.dao.saveSsqLotteryCollectRedCod(resultList);
						resultList.clear();
					}
				}
			}
			last += page;
			list = this.dao.getSsqLotteryCollectFetchLimit(last, page, "1");
		}
		if (CollectionUtils.isNotEmpty(resultList)) {
			this.dao.saveSsqLotteryCollectRedCod(resultList);
			resultList.clear();
		}
		this.dao.saveCollectBlueCodeResult(blueMap, LotterySsqConfig.expect);
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
	 * 大赢家用户投注抓取
	 */
	public void fetchDyjProjectCode() {
		this.dao.clearHisFetchProjectCode(LotterySsqConfig.expect, "1");
		List<Map<String, String>> list = this.getDyjProject();
		List<Map<String, String>> resultList = new ArrayList<Map<String, String>>();
		for (Map<String, String> map : list) {
			if ("0".equals(map.get("isupload"))) {
				continue;
			}
			if (this.dao.getCountSsqLotteryCollectFetchByProid(map.get("proid"), "1") > 0) {
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
				tmpMap.put("expect", LotterySsqConfig.expect);
				tmpMap.put("code", "-1");
				tmpMap.put("isfail", "1");
				continue;
			}
			String[] codes = pList.toArray(new String[pList.size()]);
			tmpMap.put("proid", map.get("proid"));
			tmpMap.put("net", "1");
			tmpMap.put("expect", LotterySsqConfig.expect);
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
		logger.info("========" + "大赢家抓取完成..............................................");
	}
}
