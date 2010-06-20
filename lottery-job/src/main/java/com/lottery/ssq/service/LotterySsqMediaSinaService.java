package com.lottery.ssq.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import net.htmlparser.jericho.Element;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lottery.ssq.config.LotterySsqConfig;
import com.lottery.ssq.dao.LotteryDao;
import com.lottery.ssq.utils.LotteryUtils;
import com.lottery.util.html.HtmlParseUtils;

/**
 * 媒体推荐的处理
 * 
 * @author Administrator
 */
public class LotterySsqMediaSinaService {
	private static Logger log = LoggerFactory.getLogger(LotterySsqMediaSinaService.class);
	LotteryDao dao = null;

	public void setDao(LotteryDao dao) {
		this.dao = dao;
	}

	/**
	 * html格式
	 * 
	 * @param mediaSina
	 * @return
	 */
	public List<String> parseCurrentMediaRedCode(String mediaSina) {
		List<String> redCode = this.getCurrentMediaRedCode(mediaSina);
		List<String> resultList = new ArrayList<String>();
		for (Iterator<String> iterator = redCode.iterator(); iterator.hasNext();) {
			String[] red = iterator.next().split(",");
			LotteryUtils.select(6, red, resultList);
		}
		return resultList;
	}

	public List<String> getCurrentMediaRedCode(String mediaSina) {
		List<String> redList = new ArrayList<String>();
		List<Element> list = HtmlParseUtils.getElementListByTagName(mediaSina, "tr");
		for (Element e : list.subList(1, list.size())) {
			List<Element> tdList = HtmlParseUtils.getElementListByTagName(e.toString(), "td");
			Element tdE = tdList.get(3);
			List<Element> divList = tdE.getAllElements("div");
			String redCode = "";
			for (Element eDiv : divList) {
				if (StringUtils.isBlank(redCode)) {
					redCode = eDiv.getContent().toString().length() < 2 ? "0" + eDiv.getContent().toString() : eDiv.getContent().toString().substring(0, 2);
				} else {
					redCode += "," + (eDiv.getContent().toString().length() < 2 ? "0" + eDiv.getContent().toString() : eDiv.getContent().toString().substring(0, 2));
				}
			}
			redList.add(redCode);
		}
		return redList;
	}

	public List<String> getCurrentMediaDanRedCode(String mediaSina) {
		List<String> redList = new ArrayList<String>();
		List<Element> list = HtmlParseUtils.getElementListByTagName(mediaSina, "tr");
		for (Element e : list.subList(1, list.size())) {
			List<Element> tdList = HtmlParseUtils.getElementListByTagName(e.toString(), "td");
			Element tdE = tdList.get(2);
			List<Element> divList = tdE.getAllElements("div");
			String redCode = "";
			for (Element eDiv : divList) {
				if (StringUtils.isBlank(redCode)) {
					redCode = eDiv.getContent().toString().length() < 2 ? "0" + eDiv.getContent().toString() : eDiv.getContent().toString().substring(0, 2);
				} else {
					redCode += "," + (eDiv.getContent().toString().length() < 2 ? "0" + eDiv.getContent().toString() : eDiv.getContent().toString().substring(0, 2));
				}
			}
			redList.add(redCode);
		}
		return redList;
	}

	public void saveCurrrentMediaDanRedCode(List<String> list) {
		this.dao.batchSqqLotteryDanResult(list, "0");
	}

	/**
	 * 保存当期媒体推荐
	 * 
	 * @param redMedia
	 * @param expect
	 */
	public void saveCurrentMediaRedCodeToDb(List<String> redMedia, String expect) {
		List<String[]> redCodeList = new ArrayList<String[]>();
		for (Iterator<String> iterator = redMedia.iterator(); iterator.hasNext();) {
			String redCodes = (String) iterator.next();
			redCodeList.add(redCodes.split(","));
		}
		this.dao.saveSsqLotteryCollectRedCod(redCodeList);
	}

	/**
	 * 保存到收集拆分表
	 */
	public void saveMediaSinaRedCode() {
		String mediaContent = this.dao.getSsqLotteryMediaContentByExpect(LotterySsqConfig.expect, "1");
		if (StringUtils.isNotBlank(mediaContent) && mediaContent.length() > 100) {
			this.saveCurrentMediaRedCodeToDb(this.getCurrentMediaRedCode(mediaContent), LotterySsqConfig.expect);
		}
	}
	/**
	 * 从结果集中删除sina媒体号码
	 */
	public void deleteMediaSinaRedCode(){
		String text=this.dao.getSsqLotteryMediaContentByExpect(LotterySsqConfig.expect, "1");
		if(StringUtils.isBlank(text)||text.length()<100){
			return;
		}
		List<String> list=this.parseCurrentMediaRedCode(text);
		this.dao.batchDelSsqLotteryFilterResult(list);
	}
}
