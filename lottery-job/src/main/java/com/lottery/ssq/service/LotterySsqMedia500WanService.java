package com.lottery.ssq.service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lottery.ssq.config.LotterySsqConfig;
import com.lottery.ssq.dao.LotteryDao;
import com.lottery.ssq.utils.LotterySsqMedia500WanUtils;
import com.lottery.ssq.utils.LotteryUtils;
import com.lottery.util.html.HttpHtmlService;

/**
 * 媒体推荐的处理
 * 
 * @author Administrator
 */
public class LotterySsqMedia500WanService {
	private static Logger log = LoggerFactory.getLogger(LotterySsqMedia500WanService.class);
	LotteryDao dao = null;

	public void setDao(LotteryDao dao) {
		this.dao = dao;
	}
	LotterySsqThan20Service lotterySsqThan20Service=null;
	
	public void setLotterySsqThan20Service(LotterySsqThan20Service lotterySsqThan20Service) {
		this.lotterySsqThan20Service = lotterySsqThan20Service;
	}
	/**
	 * 按单注方式生成红球号码
	 * 
	 * @param document
	 */
	public List<String> parseCurrentMediaRedCode(Document document) {
		List<String[]> redCode = LotterySsqMedia500WanUtils.getMediaRedCode(document);
		List<String> resultList = new ArrayList<String>();
		for (Iterator<String[]> iterator = redCode.iterator(); iterator.hasNext();) {
			String[] red = (String[]) iterator.next();
			if(red.length>20){
				this.lotterySsqThan20Service.select(6, red, true);
				continue;
			}
			LotteryUtils.select(6, red, resultList);
		}
		return resultList;
	}
	/**
	 * 500wan媒体推荐红球
	 * @param document
	 * @return
	 */
	public List<String> getCurrentMediaRedCode(Document document) {
		List<String[]> redCode = LotterySsqMedia500WanUtils.getMediaRedCode(document);
		if(CollectionUtils.isEmpty(redCode)){
			return null;
		}
		List<String> resultList = new ArrayList<String>();
		for(String[] ssq:redCode){
			resultList.add(StringUtils.join(ssq, ","));
		}
		return resultList;
	}

	/**
	 * 按单注方式生成红球号码
	 * 
	 * @param document
	 */
	public List<String> parseCurrentMediaRedCode(List<String[]> redList) {
		List<String> resultList = new ArrayList<String>();
		for (Iterator<String[]> iterator = redList.iterator(); iterator.hasNext();) {
			String[] red = (String[]) iterator.next();
			LotteryUtils.select(6, red, resultList);
		}
		return resultList;
	}

	/**
	 * 把媒体红球生产的单注保存到文件 d:/myproject/ssq_media_red_" + qh + ".xml
	 * 
	 * @param redMedia
	 * @param qh
	 */
	public void saveCurrentMediaRedCode(List<String> redMedia, String qh) {
		if (CollectionUtils.isNotEmpty(redMedia)) {
			try {
				File file = new File("d:/myproject/ssq_media_red_" + qh + ".xml");
				if (!file.exists()) {
					file.createNewFile();
				} else {
				}
				FileWriter writer = new FileWriter(file);
				StringBuffer filePrint = new StringBuffer();
				int temp = 0;
				for (Iterator<String> iterator = redMedia.iterator(); iterator.hasNext();) {
					temp++;
					String redCode = (String) iterator.next();
					if (temp < redMedia.size()) {
						filePrint.append(redCode + "\n");
					} else {
						filePrint.append(redCode);
					}
				}
				writer.write(filePrint.toString());
				writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 保存媒体推荐XML 到文件 D:/myproject/myselflearning/lottery/ssq_media/" + expect + ".xml
	 * 
	 * @param content
	 * @param expect
	 * @throws IOException
	 */
	public static void writerHistoryMediaXml(String content, String expect) throws IOException {
		if (StringUtils.isBlank(expect) || StringUtils.isBlank(content)) {
			return;
		}
		File file = new File("D:/myproject/myselflearning/lottery/ssq_media/" + expect + ".xml");
		if (!file.exists()) {
			file.createNewFile();
		} else {
		}
		FileWriter writer = new FileWriter(file);
		writer.write(content);
		writer.close();
	}

	/**
	 * 保存媒体推荐的历史xml
	 * 
	 * @param start
	 * @param end
	 */
	public static void saveHistoryMediaXml(int start, int end) {
		String url = "http://www.500wan.com/static/info/ssq/mediadetail/";// 10001.xml
		for (int i = start; i <= end; i++) {
			String content = "";
			try {
				content = HttpHtmlService.getXmlContent(url + i + ".xml");
				if (StringUtils.isBlank(content)) {
					continue;
				}
				writerHistoryMediaXml(content, i + "");
			} catch (Exception e) {
				log.error(e.getMessage());
			}
		}
	}

	/**
	 * 保存媒体推荐的历史xml
	 * 
	 * @param start
	 * @param end
	 */
	public static void saveHistoryMediaXml(String expect, String xmlData) {
		if (StringUtils.isBlank(xmlData)) {
			return;
		}
		try {
			writerHistoryMediaXml(xmlData, expect);
		} catch (IOException e) {
			log.error(e.getMessage());
		}
	}

	/**
	 * 保存历史的媒体推荐红球统计
	 * 
	 * @param expect
	 * @param redMap
	 * @param redCode
	 */
	public void saveHistoryMediaRedStat(String expect, Map<String, String> redMap, String redCode) {
		for (Iterator<String> iterator = redMap.keySet().iterator(); iterator.hasNext();) {
			String code = (String) iterator.next();
			redCode = "," + redCode + ",";
			String value = redMap.get(code);
			String[] stat = StringUtils.split(value, "|");
			if (StringUtils.indexOf(redCode, "," + code + ",") != -1) {
				try {
					this.dao.saveSsqLotteryHistoryStat(expect, "0", code, stat[0], stat[1], true);
				} catch (Exception e) {
					log.error(e.getMessage());
				}
			} else {
				try {
					this.dao.saveSsqLotteryHistoryStat(expect, "0", code, stat[0], stat[1], false);
				} catch (Exception e) {
					log.error(e.getMessage());
				}
			}
		}
	}

	/**
	 * 保存历史的媒体推荐蓝球统计
	 * 
	 * @param expect
	 * @param blueMap
	 * @param blueCode
	 */
	public void saveHistoryMediaBlueStat(String expect, Map<String, String> blueMap, String blueCode) {
		for (Iterator<String> iterator = blueMap.keySet().iterator(); iterator.hasNext();) {
			String code = (String) iterator.next();
			String value = blueMap.get(code);
			String[] stat = StringUtils.split(value, "|");
			if (StringUtils.equals(blueCode, code)) {
				try {
					this.dao.saveSsqLotteryHistoryStat(expect, "1", code, stat[0], stat[1], true);
				} catch (Exception e) {
					log.error(e.getMessage());
				}
			} else {
				try {
					this.dao.saveSsqLotteryHistoryStat(expect, "1", code, stat[0], stat[1], false);
				} catch (Exception e) {
					log.error(e.getMessage());
				}
			}
		}
	}

	/**
	 * 500wan媒体推荐统计
	 * @param xmlContent
	 * @param expect
	 */
	public void saveHistoryMediaStat(String xmlContent, String expect) {
		if (StringUtils.isBlank(xmlContent)) {
			return;
		}
		Document document = null;
		try {
			document = DocumentHelper.parseText(xmlContent);
		} catch (DocumentException e) {
			log.error("XML parse error");
		}
		this.saveHistoryMediaRedStat(expect, LotterySsqMedia500WanUtils.getMediaRedCodeStat(document), StringUtils.join(LotterySsqMedia500WanUtils
				.getHistoryOpenRedcode(document), ","));
		this.saveHistoryMediaBlueStat(expect, LotterySsqMedia500WanUtils.getMediaBlueCodeStat(document), LotterySsqMedia500WanUtils.getHistoryOpenBlueCode(document));
	}

	/**
	 * 保存当期媒体推荐
	 * @param redMedia
	 * @param expect
	 */
	public void saveCurrentMediaRedCodeToDb(List<String> redMedia, String expect) {
		List<String[]> redCodeList=new ArrayList<String[]>();
		for (Iterator<String> iterator = redMedia.iterator(); iterator.hasNext();) {
			String redCodes = (String) iterator.next();
			redCodeList.add(redCodes.split(","));
		}
		this.dao.saveSsqLotteryCollectRedCod(redCodeList);
	}
	/**
	 * 保存到收集号码表
	 */
	public void saveMedia500WanRedCode() {
		String mediaContent=this.dao.getSsqLotteryMediaContentByExpect(LotterySsqConfig.expect, "0");
		if(StringUtils.isNotBlank(mediaContent)&&mediaContent.length()>100){
			Document doc=null;
			try {
				doc=DocumentHelper.parseText(mediaContent);
			} catch (DocumentException e) {
				log.error(e.getMessage());
			}
			this.saveCurrentMediaRedCodeToDb(this.parseCurrentMediaRedCode(doc),LotterySsqConfig.expect);
		}
	}
	/**
	 * 从结果集中删除500wan媒体号码
	 */
	public void deleteMedia500WanRedCode(){
		String text=this.dao.getSsqLotteryMediaContentByExpect(LotterySsqConfig.expect, "0");
		if(StringUtils.isBlank(text)||text.length()<100){
			return;
		}
		Document document=null;
		try {
			document=DocumentHelper.parseText(text);
		} catch (DocumentException e) {
			log.error(e.getMessage());
		}
		List<String> list=this.parseCurrentMediaRedCode(document);
		this.dao.batchDelSsqLotteryFilterResult(list);
	}
}
