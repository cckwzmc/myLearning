package com.lottery.ssq.service;

import java.util.ArrayList;
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
public class LotterySsqCustomerBuyService extends Thread {
	private static final Logger logger = LoggerFactory.getLogger(LotterySsqCustomerBuyService.class);
	private LotteryDao dao = null;

	public void setDao(LotteryDao dao) {
		this.dao = dao;
	}

	public void run() {
		this.fetchBetzcProjectCode();
	}

	/**
	 * 获取用户的各种方案
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Map<String, String>> getBetzcProject() {
		new LotterySsqConifgService();
		String url = LotterySsqFetchConfig.buywanUrl;
		List<Map<String, String>> retList = new ArrayList<Map<String, String>>();
		int k = 0;
		for (int i = 1; i < 100; i++) {
			if (k > 3) {
				break;
			}
			String betzcHtmlData = HttpHtmlService.getHtmlContent(StringUtils.replace(StringUtils.replace(url,
					"@page@", i + ""), "@random@", this.genRandom()), "utf-8");
			logger.info(StringUtils.replace(StringUtils.replace(url, "@page@", i + ""), "@random@", this.genRandom()));
			Source source = new Source(betzcHtmlData);
			List<Element> betzc = source.getAllElementsByClass("tablegraybg");
			if (betzc == null || betzc.size() != 1) {
				return null;
			}
			for (Element el : betzc) {
				List<Element> tr = el.getAllElements("tr");
				for (Element etr : tr.subList(1, tr.size())) {
					List<Element> tdList = etr.getAllElements("td");
					Element codeTd = tdList.get(8);
					Element aE = codeTd.getFirstElement("a");
					if (aE == null || aE.length() == 0 || aE.isEmpty()) {
						continue;
					}
					String href = aE.getAttributeValue("href");
					String projectUrl = href;
				}
				break;
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
		String url = StringUtils.replace(StringUtils.replace(LotterySsqFetchConfig.buywanUrl, "@id@", id),
				"@playtype@", playtype);
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
		content = StringUtils.replace(content, " | ", "+");
		content = StringUtils.replace(content, "|", "+");
		content = StringUtils.replace(content, "=", "+");
		content = StringUtils.replace(content, " \n", "\n");
		content = StringUtils.replace(content, "\t", ",");
		content = StringUtils.replace(content, ".", ",");
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
		while (CollectionUtils.isNotEmpty(list)) {
			for (Iterator iterator = list.iterator(); iterator.hasNext();) {
				Map map = (Map) iterator.next();
				String code = ObjectUtils.toString(map.get("code"));
				code = StringUtils.replace(code, "|", "+");
				String[] codes = StringUtils.split(code, "@@");
				for (String ssq : codes) {
					String redCode = StringUtils.split(ssq, "+")[0];
					String[] redCodes = StringUtils.split(redCode, ",");
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
	 * 盈彩网用户投注抓取
	 */
	public void fetchBetzcProjectCode() {
		this.dao.clearHisFetchProjectCode(LotterySsqConfig.expect, "1");
		List<Map<String, String>> list = null;// this.getBetzcProject();
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
		logger.info("========" + "盈彩网抓取完成..............................................");
	}

	public static void main(String[] args) {
		LotterySsqCustomerBuyService test = new LotterySsqCustomerBuyService();
		new LotterySsqFetchConfig().buywanUrl = "http://buy.betzc.com/ssq/hemai.html?page.pageNo=@page@";
		test.getBetzcProject();
	}
}
