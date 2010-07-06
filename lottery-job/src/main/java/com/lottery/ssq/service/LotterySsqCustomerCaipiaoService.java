package com.lottery.ssq.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.htmlparser.jericho.Element;
import net.htmlparser.jericho.Source;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.RandomUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.lottery.ssq.config.LotterySsqConfig;
import com.lottery.ssq.config.LotterySsqFetchConfig;
import com.lottery.ssq.dao.LotteryDao;
import com.lottery.ssq.utils.LotterySsqUtils;
import com.lottery.util.html.HttpHtmlService;

/**
 * 爱彩网用户购买/收集的数据处理
 * 
 * @author LIYI
 */
public class LotterySsqCustomerCaipiaoService extends Thread {
	private static final Logger logger = LoggerFactory.getLogger(LotterySsqCustomerCaipiaoService.class);
	@Autowired
	private LotteryDao dao = null;

	public void setDao(LotteryDao dao) {
		this.dao = dao;
	}

	public void run() {
		this.fetchCaipiaoProjectCode();
	}

	/**
	 * 获取用户的各种方案
	 * 
	 * @return
	 */
	public List<Map<String, String>> getCaipiaoProject() {
		String url = LotterySsqFetchConfig.caipiaoUrl;
		List<Map<String, String>> retList = new ArrayList<Map<String, String>>();
		int k = 0;
		for (int i = 1; i < 100; i++) {
			if (k > 3) {
				break;
			}
			String caipiaoHtmlData = HttpHtmlService.getHtmlContent(StringUtils.replace(StringUtils.replace(url,
					"@page@", i + ""), "@random@", this.genRandom()), "GB2312");
			logger.info(StringUtils.replace(StringUtils.replace(url, "@page@", i + ""), "@random@", this.genRandom()));
			Source source = new Source(caipiaoHtmlData);
			List<Element> gd04List = source.getAllElementsByClass("gd_f_l_04");
			List<Element> gd05List = source.getAllElementsByClass("gd_f_l_05");
			List<Element> list = new ArrayList<Element>();
			if (CollectionUtils.isNotEmpty(gd04List)) {
				list.addAll(gd04List);
			}
			if (CollectionUtils.isNotEmpty(gd05List)) {
				list.addAll(gd05List);
			}
			if (CollectionUtils.isEmpty(gd05List)) {
				k++;
			}
			for (Element e : list) {
				List<Element> aList = e.getAllElements("a");
				for (Element ea : aList) {
					if (ea.toString().indexOf("查看") != -1) {
						Map<String, String> map = new HashMap<String, String>();
						String href = ea.getAttributeValue("href");
						String id = StringUtils.substringAfter(href, "planNum=");
						map.put("id", id);
						map.put("proid", id);
						retList.add(map);
					}
				}
			}
			try {
				sleep(1000);
			} catch (InterruptedException e) {
				notify();
			}
		}
		return retList;
	}

	/**
	 * 下载用户方案 对胆的方案要另想办法
	 * 
	 * @param id
	 * @param playtype
	 * @return
	 */
	public List<String> downloadCaipiaoProject(String id, String playtype) {
		List<String> list = new ArrayList<String>();
		String url = StringUtils.replace(StringUtils.replace(LotterySsqFetchConfig.caipiaoDowload, "@playNum@", id),
				"@playtype@", playtype);
		logger.info(url);
		String content = HttpHtmlService.getHtmlContent(url, "GB2312");
		try {
			sleep(3000);
		} catch (InterruptedException e) {
			notify();
		}
		Source source = new Source(content);
		List<Element> project = source.getAllElementsByClass("textarea2");
		if (CollectionUtils.isEmpty(project)) {
			list.add("-1");
			return null;
		}
		String codeContent = project.get(0).getContent().getTextExtractor().toString();
		if (codeContent.indexOf("该方案") != -1 || codeContent.indexOf("尚未截止") != -1 || codeContent.indexOf("截止后公开") != -1) {
			list.add("-1");
			return null;
		}
		codeContent = StringUtils.replace(codeContent, " + ", "+");
		codeContent = StringUtils.replace(codeContent, " = ", "+");
		codeContent = StringUtils.replace(codeContent, "<br><br>", "\n");
		codeContent = StringUtils.replace(codeContent, "<br>", "\n");
		codeContent = StringUtils.replace(codeContent, " | ", "+");
		codeContent = StringUtils.replace(codeContent, "|", "+");
		codeContent = StringUtils.replace(codeContent, "=", "+");
		codeContent = StringUtils.replace(codeContent, " \n", "\n");
		codeContent = StringUtils.replace(codeContent, "\t", ",");
		codeContent = StringUtils.replace(codeContent, ".", ",");
		codeContent = parserdownloadProjectHtml(codeContent);
		if (StringUtils.isBlank(codeContent)) {
			return null;
		}
		String[] contents = StringUtils.split(codeContent, "\n");
		for (int i = 0; i < contents.length; i++) {
			if (contents[i].length() < 10) {
				continue;
			}
			list.add(StringUtils.replace(contents[i], " ", ",").trim());
		}
		return list;
	}

	/**
	 * 解析html
	 * 
	 * @param content
	 * @return
	 */
	private String parserdownloadProjectHtml(String code) {
		List<String> list = new ArrayList<String>();
		boolean isHaveDan = false;
		String retStr = "";
		// 红球胆码:06,09,20,27 红球拖码:07,10,18,22,23,25,26,32 蓝球号码:03,05,07,11,13
		if (code.indexOf("红球胆码") != -1) {
			String[] dans = StringUtils.substringsBetween(code, "红球胆码:", "红球拖码:");
			for (int i = 0; i < dans.length; i++) {
				String dan = dans[i].replace(" ", "");
				if (StringUtils.isNotBlank(dan)) {
					list.add("--1," + dan);
					isHaveDan = true;
				}
			}
		} else if (code.indexOf("红球") != -1) {
			code = StringUtils.replace(code, "红球:", "\n");
			code = StringUtils.replace(code, " ", "");
			code = StringUtils.replace(code, "蓝球:", "+");
			code = StringUtils.replace(code, "红球:", "");
			code = StringUtils.replace(code, "蓝球", "+");
			code = StringUtils.replace(code, "红球", "");
			return code;
		} else {

		}
		return retStr;
	}

	/**
	 * 给红球排序
	 * 
	 * @param code
	 * @return
	 */
	private String sortCode(String code) {
		String[] codes = StringUtils.split(code, "+");
		String[] redCode = StringUtils.split(codes[0], ",");
		Arrays.sort(redCode);
		code = StringUtils.join(redCode, ",") + "+" + codes[1];
		return code;
	}

	@SuppressWarnings("unchecked")
	public void saveCaipiaoProjectRedCode() {
		List<String[]> resultList = new ArrayList<String[]>();
		int last = 0;
		int page = 200;
		List list = this.dao.getSsqLotteryCollectFetchLimit(last, page, "5");
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
							if (blue.length() > 2) {
								continue;
							} else if (blue.length() == 1) {
								blue = "0" + blue;
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
					Arrays.sort(redCodes);
					LotterySsqUtils.selectArray(6, redCodes, resultList);
					if (resultList.size() > 2000) {
						this.dao.saveSsqLotteryCollectRedCod(resultList);
						logger.info("看看是否拆分了爱彩网的数据.....");
						resultList.clear();
					}
				}
			}
			last += page;
			list = this.dao.getSsqLotteryCollectFetchLimit(last, page, "5");
		}
		if (CollectionUtils.isNotEmpty(resultList)) {
			this.dao.saveSsqLotteryCollectRedCod(resultList);
			logger.info("看看是否拆分了爱彩网的数据.....");
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
	 * 爱彩网用户投注抓取
	 */
	public void fetchCaipiaoProjectCode() {
		this.dao.clearHisFetchProjectCode(LotterySsqConfig.expect, "5");
		List<Map<String, String>> list = this.getCaipiaoProject();
		List<Map<String, String>> resultList = new ArrayList<Map<String, String>>();
		for (Map<String, String> map : list) {
			if (this.dao.getCountSsqLotteryCollectFetchByProid(map.get("proid"), "3") > 0) {
				continue;
			}
			List<String> pList = this.downloadCaipiaoProject(map.get("id"), map.get("playtype"));
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
			tmpMap.put("net", "5");
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
		logger.info("========" + "爱彩网抓取完成..............................................");
	}
}
