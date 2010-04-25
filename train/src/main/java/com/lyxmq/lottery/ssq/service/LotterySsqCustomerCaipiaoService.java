package com.lyxmq.lottery.ssq.service;

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

import com.lyxmq.lottery.ssq.dao.LotteryDao;
import com.lyxmq.lottery.ssq.utils.LotteryUtils;
import com.myfetch.service.http.util.HtmlParseUtils;
import com.myfetch.service.http.util.HttpHtmlService;

/**
 * 大赢家用户购买/收集的数据处理
 * 
 * @author LIYI
 */
public class LotterySsqCustomerCaipiaoService extends Thread {
	private static final Logger logger = LoggerFactory.getLogger(LotterySsqCustomerCaipiaoService.class);
	private LotteryDao dao = null;

	public void setDao(LotteryDao dao) {
		this.dao = dao;
	}
	public void run(){
		this.fetchCaipiaoProjectCode();
	}
	/**
	 * 获取用户的各种方案
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Map<String, String>> getCaipiaoProject() {
		new LotterySsqConifgService();
		String url = LotterySsqConifgService.getCaipiaoUrl();
		List<Map<String, String>> retList = new ArrayList<Map<String, String>>();
		int k = 0;
		for (int i = 1; i < 100; i++) {
			if (k > 3) {
				break;
			}
			String caipiaoHtmlData = HttpHtmlService.getHtmlContent(StringUtils.replace(StringUtils.replace(url, "@page@", i + ""), "@random@", this.genRandom()), "GB2312");
			logger.info(StringUtils.replace(StringUtils.replace(url, "@page@", i + ""), "@random@", this.genRandom()));
			Source source = new Source(caipiaoHtmlData);
			List<Element> gd04List=source.getAllElementsByClass("gd_f_l_04");
			List<Element> gd05List=source.getAllElementsByClass("gd_f_l_05");
			List<Element> list=new ArrayList<Element>();
			if(CollectionUtils.isNotEmpty(gd04List))
			{
				list.addAll(gd04List);
			}
			if(CollectionUtils.isNotEmpty(gd05List))
			{
				list.addAll(gd05List);
			}
			if (CollectionUtils.isEmpty(list)) {
				k++;
			}
			for (Element e:list) {
				List<Element> aList=e.getAllElements("a");
				for(Element ea:aList)
				{
					if(ea.toString().indexOf("查看")!=-1)
					{
						Map<String, String> map = new HashMap<String, String>();
						String href=ea.getAttributeValue("href");
						String id=StringUtils.substringBetween(href,"playNum=", "\"");
						map.put("id", id);
						map.put("proid",id);
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
	 * 下载用户方案
	 * 
	 * @param id
	 * @param playtype
	 * @return
	 */
	public List<String> downloadCaipiaoProject(String id, String playtype) {
		List<String> list = new ArrayList<String>();
		String url = StringUtils.replace(StringUtils.replace(LotterySsqConifgService.getDyjDowload(), "@playNum@", id), "@playtype@", playtype);
		logger.info(url);
		String content = HttpHtmlService.getHtmlContent(url, "GB2312");
		try {
			sleep(5000);
		} catch (InterruptedException e) {
			notify();
		}
		Source source=new Source(content);
		List<Element> project=source.getAllElementsByClass("textarea2");
		if(CollectionUtils.isEmpty(project)){
			list.add("-1");
			return null;
		}
		String codeContent=project.get(0).toString();
		if (codeContent.indexOf("该方案") != -1||codeContent.indexOf("尚未截止")!=-1) {
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
				code=StringUtils.replace(code, "|", "+");
				String[] codes = StringUtils.split(code, "@@");
				for (String ssq : codes) {
					String redCode = StringUtils.split(ssq, "+")[0];
					String[] redCodes = StringUtils.split(redCode, ",");
					if (redCodes.length < 6 || redCodes.length > 20) {
						logger.error("方案解析失败==" + ssq);
						continue;
					}
					LotteryUtils.selectArray(6, redCodes, resultList);
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
	 * 爱彩网用户投注抓取
	 */
	public void fetchCaipiaoProjectCode() {
		this.dao.clearHisFetchProjectCode(LotterySsqConifgService.getExpect(), "3");
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
			if(CollectionUtils.isNotEmpty(pList)&&"-1".equals(pList.get(0))){
				tmpMap.put("proid", map.get("proid"));
				tmpMap.put("net", "1");
				tmpMap.put("expect", LotterySsqConifgService.getExpect());
				tmpMap.put("code", "-1");
				tmpMap.put("isfail", "1");
				continue;
			}
			String[] codes = pList.toArray(new String[pList.size()]);
			tmpMap.put("proid", map.get("proid"));
			tmpMap.put("net", "3");
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
		logger.info("========"+"大赢家抓取完成..............................................");
	}
}
