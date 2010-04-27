package com.lottery.football.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.Node;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lottery.football.dao.LotteryFootballDao;
import com.lottery.football.util.FootballLotteryUtils;
import com.lottery.util.html.HttpHtmlService;

/**
 * 媒体推荐的处理
 * 
 * @author Administrator
 */
public class LotteryFootballMedia500WanService {
	private static Logger log = LoggerFactory.getLogger(LotteryFootballMedia500WanService.class);
	private LotteryFootballDao footballLotteryDao = null;

	public void setFootballLotteryDao(LotteryFootballDao footballLotteryDao) {
		this.footballLotteryDao = footballLotteryDao;
	}

	private void initConfig() {
		new LotteryFootballConifgService();
	}

	public void saveCurrentExpectFootballMedia() {
		this.initConfig();
		String xmlData = this.fetchMediaXmlData(LotteryFootballConifgService.getFootballExpect());
		List<String> list = this.parserFtMediaXmlData(xmlData);
		if (list == null) {
			return;
		}
		List<String> resultList=new ArrayList<String>();
		for(String code:list)
		{
			String[][] tjCode=FootballLotteryUtils.splitFootballCode(code);
			List<String> aCode=FootballLotteryUtils.doCallAllCode(tjCode);
			if(CollectionUtils.isNotEmpty(aCode)){
				resultList.addAll(aCode);
			}
			if(resultList.size()>2000){
				this.footballLotteryDao.batchSaveFootballCollectionResult(resultList);
				resultList.clear();
			}
		}
		if(CollectionUtils.isNotEmpty(resultList)){
			this.footballLotteryDao.batchSaveFootballCollectionResult(resultList);
			resultList.clear();
		}
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
			log.error("xml parse erro ============="+e.getMessage());
			log.error(xmlData);
		}
		List<String> result=new ArrayList<String>();
		List list = doc.selectNodes("//xml/media/tjcode");
		for (Iterator iterator = list.iterator(); iterator.hasNext();) {
			Node node = (Node) iterator.next();
			List codeList = node.selectNodes("code");
			String code="";
			for (Iterator iterator2 = codeList.iterator(); iterator2.hasNext();) {
				Node n = (Node) iterator2.next();
				Element e = (Element) n;
				if("".equals(code)){
					code=e.getText();
				}else{
					code+=","+e.getText();
				}
			}
			result.add(code);
		}
		return result;
	}

	/**
	 * 抓取XML页面
	 * 
	 * @param expect
	 * @return
	 */
	public String fetchMediaXmlData(String expect) {
		String url = LotteryFootballConifgService.getWww500wanFootballMedia();
		url = StringUtils.replace(url, "@footballExpect@", expect);
		String xmlData = "";
		try{
			xmlData=HttpHtmlService.getXmlContent(url, "utf-8");
		}catch(Exception e){
			log.error(e.getMessage());
			log.error(url);
		}
		if (StringUtils.isBlank(xmlData) || xmlData.length() < 200) {
			return null;
		}
		return xmlData;
	}
}
