package com.lottery.ssq.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
 * 盈彩网用户购买/收集的数据处理
 * 
 * @author LIYI
 */
public class LotterySsqCustomerBetzcService extends Thread {
	private static final Logger logger = LoggerFactory.getLogger(LotterySsqCustomerBetzcService.class);
	private LotteryDao dao = null;
	private String buyUrlPrefix = "http://buy.betzc.com";

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
	public List<String> getBetzcProject() {
		new LotterySsqConifgService();
		String url = LotterySsqFetchConfig.buywanUrl;
		List<String> retList = new ArrayList<String>();
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
			if (betzc.toString().indexOf("无记录") > 1) {
				k++;
				continue;
			}
			for (Element el : betzc) {
				List<Element> tr = el.getAllElements("tr");
				for (Element etr : tr.subList(1, tr.size())) {
					List<Element> tdList = etr.getAllElements("td");
					if (tdList == null || tdList.size() < 9) {
						continue;
					}
					Element codeTd = tdList.get(8);
					Element aE = codeTd.getFirstElement("a");
					if (aE == null || aE.length() == 0 || aE.isEmpty()) {
						continue;
					}
					String href = aE.getAttributeValue("href");
					String projectUrl = buyUrlPrefix + href;
					retList.add(projectUrl);
				}
				break;
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
	 * 下载用户方案
	 * 
	 * @param id
	 * @param playtype
	 * @return
	 */
	public List<String> downloadBetzcProject(String url) {
		List<String> list = new ArrayList<String>();
		String content = HttpHtmlService.getHtmlContent(url, "utf-8");
		try {
			sleep(1000);
		} catch (InterruptedException e) {
			notify();
		}
		Source source = new Source(content);
		List<Element> ssq = source.getAllElementsByClass("ssq_scroll");
		Element ssqElement = ssq.get(0);
		String codes = ssqElement.getContent().getTextExtractor().toString();
		codes = StringUtils.remove(codes, "[双色球]：										");
		codes = StringUtils.replace(codes, "[双色球]： ", "@@");
		codes = StringUtils.replace(codes, " + ", "+");
		codes = codes.replace(" ", ",");
		codes = codes.replace(",@@", "@@");
		codes = codes.substring(2);
		// List<Element> code=ssqElement.getAllElements("span");
		// int i=0;
		// String redCode="";
		// String blueCode="";
		// for(Element ce:code){
		//			
		// if(i%2==0){n
		// redCode=ce.getAllStartTagsByClass("redchar");
		// redCode=redCode.replace(" ", ",");
		// if(redCode.length()<12){
		// continue;
		// }
		// }else{
		// blueCode=ce.getContent().getTextExtractor().toString();
		// blueCode=blueCode.replace(" ", ",");
		// if(blueCode.length()<2){
		// continue;
		// }
		// }
		// i++;
		// if(redCode.length()>10&&blueCode.length()>1)
		// {
		// list.add(redCode+"+"+blueCode);
		// redCode="";
		// blueCode="";
		// }
		// }
		list.add(codes);
		return list;
	}

	@SuppressWarnings("unchecked")
	public void saveBetzcProjectRedCode() {
		List<String[]> resultList = new ArrayList<String[]>();
		int last = 0;
		int page = 200;
		List list = this.dao.getSsqLotteryCollectFetchLimit(last, page, "6");
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
	 * 盈彩网用户投注抓取
	 */
	public void fetchBetzcProjectCode() {
		this.dao.clearHisFetchProjectCode(LotterySsqConfig.expect, "6");
		List<String> list = this.getBetzcProject();
		List<Map<String, String>> resultList = new ArrayList<Map<String, String>>();
		Set<String> danList = new HashSet<String>();
		if (CollectionUtils.isEmpty(list)) {
			return;
		}
		for (String url : list) {
			List<String> pList = this.downloadBetzcProject(url);
			if (CollectionUtils.isEmpty(pList)) {
				continue;
			}
			Map<String, String> tmpMap = new HashMap<String, String>();
			for (String code : pList) {
				if (StringUtils.indexOf(code, ")") > 1) {
					String ssqCode = "";
					String[] tmpCode = StringUtils.split(code, "@@");
					for (String ssq : tmpCode) {
						if (StringUtils.indexOf(code, ")") > 1) {
							List<String[]> tmp = new ArrayList<String[]>();
							String dan = StringUtils.substringBetween(ssq, "(", ")");
							danList.add(dan);
							LotterySsqUtils.selectDanArray(6, dan, StringUtils.substringBetween(code, "),", "+"), tmp);
							logger.info("");
							String blueCode = StringUtils.substring(code, StringUtils.indexOf(code, "+"));
							for (String[] danCode : tmp) {
								String[] temp = (dan + "," + StringUtils.join(danCode, ",")).split(",");
								Arrays.sort(temp);
								if ("".equals(ssqCode)) {
									ssqCode = (StringUtils.join(temp, ",") + blueCode);
								} else {
									ssqCode = "@@" + (StringUtils.join(temp, ",") + blueCode);
								}
							}

						} else {
							if ("".equals(ssqCode)) {
								ssqCode = ssq;
							} else {
								ssqCode += "@@" + ssq;
							}
						}
					}
					tmpMap.put("proid", StringUtils.substring(StringUtils.substring(url, url.lastIndexOf("/") + 1, url
							.length()), 0, StringUtils.substring(url, url.lastIndexOf("/") + 1, url.length())
							.lastIndexOf(".")));
					tmpMap.put("net", "6");
					tmpMap.put("expect", LotterySsqConfig.expect);
					tmpMap.put("code", ssqCode);
					tmpMap.put("isfail", "0");
				} else {
					tmpMap.put("proid", StringUtils.substring(StringUtils.substring(url, url.lastIndexOf("/") + 1, url
							.length()), 0, StringUtils.substring(url, url.lastIndexOf("/") + 1, url.length())
							.lastIndexOf(".")));
					tmpMap.put("net", "6");
					tmpMap.put("expect", LotterySsqConfig.expect);
					tmpMap.put("code", code);
					tmpMap.put("isfail", "0");
				}
			}
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
		if (CollectionUtils.isNotEmpty(danList)) {
			this.dao.batchSqqLotteryDanResult(new ArrayList<String>(danList), "1");
		}
		logger.info("========" + "盈彩网抓取完成..............................................");
	}

	public static void main(String[] args) {
		LotterySsqCustomerBetzcService test = new LotterySsqCustomerBetzcService();
		new LotterySsqFetchConfig().buywanUrl = "http://buy.betzc.com/ssq/hemai.html?page.pageNo=7";
		test.fetchBetzcProjectCode();
	}
}
