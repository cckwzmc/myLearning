package com.lottery.ssq.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.commons.lang.math.RandomUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.slf4j.LoggerFactory;

import com.lottery.ssq.LotteryConstant;
import com.lottery.ssq.config.LotterySsqConfig;
import com.lottery.ssq.config.LotterySsqFetchConfig;
import com.lottery.ssq.dao.LotteryDao;
import com.lottery.ssq.utils.LotterySsqMedia500WanUtils;
import com.lottery.ssq.utils.LotteryUtils;
import com.lottery.util.html.HtmlParseUtils;
import com.lottery.util.html.HttpHtmlService;

public class LotteryInitService {
	private static final org.slf4j.Logger logger = LoggerFactory.getLogger(LotteryInitService.class);
	LotteryDao dao = null;
	LotterySsqMedia500WanService lotterySsqMedia500WanService = null;
	LotterySsqMediaSinaService lotterySsqMediaSinaService = null;
	private String filterResult = "";

	public void setLotterySsqMediaSinaService(LotterySsqMediaSinaService lotterySsqMediaSinaService) {
		this.lotterySsqMediaSinaService = lotterySsqMediaSinaService;
	}

	private List<String> redList = new ArrayList<String>();

	public void setDao(LotteryDao dao) {
		this.dao = dao;
	}

	public void setLotterySsqMedia500WanService(LotterySsqMedia500WanService lotterySsqMedia500WanService) {
		this.lotterySsqMedia500WanService = lotterySsqMedia500WanService;
	}

	public void saveAllRedResult(String filterResult) {
		logger.info("初始化数据................");
		this.filterResult = filterResult;
		select(6);
	}

	public void select(int k) {
		String[] result = new String[k];
		subselect(0, 1, result, k);
	}

	private void subselect(int head, int index, String[] r, int k) {
		for (int i = head; i < LotteryConstant.redBall.length + index - k; i++) {
			if (index < k) {
				r[index - 1] = LotteryConstant.redBall[i];
				subselect(i + 1, index + 1, r, k);
			} else if (index == k) {
				r[index - 1] = LotteryConstant.redBall[i];
				this.saveLottoryResult(StringUtils.join(r, ","));
				subselect(i + 1, index + 1, r, k);
			} else {
				return;
			}

		}
	}

	/**
	 * 一个区不能有超过4个的号码 不能有>4的连号 不能有3个三连号 不能同时存在三个差值为1或2的 如果号码分布在三个区， 那么三个区的号码差值不能相同 TODO 六个数之间的5差值不能相等有<=4个差值以上
	 * 
	 * @param redCode
	 * @param filterResult 如果=="0"保存到all_result =="1"保存到filter_result
	 */
	private void saveLottoryResult(String redCode) {
		String[] codeSix = redCode.split(",");

		int qOne = 0;
		int qTwo = 0;
		int qThree = 0;
		for (int i = 0; i < codeSix.length; i++) {
			if (NumberUtils.toInt(codeSix[i]) <= 11) {
				qOne++;
			}
			if (NumberUtils.toInt(codeSix[i]) > 11 && NumberUtils.toInt(codeSix[i]) <= 22) {
				qTwo++;
			}
			if (NumberUtils.toInt(codeSix[i]) > 22 && NumberUtils.toInt(codeSix[i]) <= 33) {
				qThree++;
			}
		}

		if (qOne == 0 || qTwo == 0 || qThree == 0) {
			return;
		}
		if (qOne >= 4 || qTwo >= 4 || qThree >= 4) {
			return;
		}
		boolean isReturn = false;
		int lhCode = 0;
		int one = NumberUtils.toInt(codeSix[0]);
		int two = NumberUtils.toInt(codeSix[1]);
		int three = NumberUtils.toInt(codeSix[2]);
		int four = NumberUtils.toInt(codeSix[3]);
		int five = NumberUtils.toInt(codeSix[4]);
		int six = NumberUtils.toInt(codeSix[5]);

		if (two - one == 1) {
			lhCode++;
		}
		if (three - two == 1) {
			lhCode++;
		}
		if (four - three == 1) {
			lhCode++;
		}
		if (five - four == 1) {
			lhCode++;
		}
		if (six - five == 1) {
			lhCode++;
		}
		if (lhCode >= 3) {
			return;
		}
		int czCode = 0;
		if (two - one == 2) {
			czCode++;
		}
		if (three - two == 2) {
			czCode++;
		}
		if (four - three == 2) {
			czCode++;
		}
		if (five - four == 2) {
			czCode++;
		}
		if (six - five == 2) {
			czCode++;
		}
		if (czCode >= 3) {
			return;
		}
		czCode = 0;
		if (two - one == 3) {
			czCode++;
		}
		if (three - two == 3) {
			czCode++;
		}
		if (four - three == 3) {
			czCode++;
		}
		if (five - four == 3) {
			czCode++;
		}
		if (six - five == 3) {
			czCode++;
		}
		if (czCode >= 3) {
			return;
		}
		czCode = 0;
		int cz1 = two - one;
		int cz2 = three - two;
		int cz3 = four - three;
		int cz4 = five - four;
		int cz5 = six - five;
		int[] czs = { cz1, cz2, cz3, cz4, cz5 };
		for (int i = 0; i < 5; i++) {
			int tmpCount = 0;
			int tmp = czs[i];
			for (int j = 0; j < 5; j++) {
				if (tmp == czs[j]) {
					tmpCount++;
				}
			}
			if (tmpCount >= 4) {
				return;
			}
		}
		if (czCode >= 3) {
			return;
		}
		if (qOne == 2 && qTwo == 2 && qThree == 2 && ((two - one) == (four - three) && (four - three) == (six - five))) {
			return;
		}
		if (isReturn) {
			return;
		}
		redList.add(redCode);

		if (redList.size() > 5000) {
			if ("0".equals(filterResult) || StringUtils.isBlank(filterResult)) {
				this.dao.batchSaveSsqLottoryResult(redList);
			} else if ("1".equals(filterResult)) {
				this.dao.batchSaveSsqLotteryFilterResult(redList);
			}
			redList.clear();
		}
		if (CollectionUtils.isNotEmpty(redList)) {
			if ("0".equals(filterResult) || StringUtils.isBlank(filterResult)) {
				this.dao.batchSaveSsqLottoryResult(redList);
			} else if ("1".equals(filterResult)) {
				this.dao.batchSaveSsqLotteryFilterResult(redList);
			}
			redList.clear();
		}
	}

	/**
	 * 从当前配置URL补充媒体预测号码
	 */
	@SuppressWarnings("unchecked")
	public void initHistoryMediaStat() {
		new LotterySsqConifgService();
		String xmlData = HttpHtmlService.getXmlContent(LotterySsqFetchConfig.media500WanUrl);
		if (StringUtils.isBlank(xmlData)) {
			return;
		}
		Document document = null;
		try {
			document = DocumentHelper.parseText(xmlData);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		int expectInt = NumberUtils.toInt(LotterySsqConfig.expect);
		String xmlContent = "";
		Map map = this.dao.getSsqLotteryHisMediaStatMaxExpect();
		String hisExpect = "";
		if (MapUtils.isNotEmpty(map)) {
			hisExpect = (String) map.get("expect");
		} else {
			hisExpect = "08088";
		}
		expectInt--;
		for (int i = expectInt; i > NumberUtils.toInt(hisExpect); i--) {
			xmlContent = LotterySsqMedia500WanUtils.getHistoryMediaXml(i < 10000 ? "0" + i : i + "");
			if (i % 1000 > 158) {
				i = i / 1000 * 1000 + 155;
				continue;
			}
			try {
				document = DocumentHelper.parseText(xmlContent);
			} catch (Exception e) {
				logger.error(e.getMessage());
				document = null;
			}
			if (document == null) {
				continue;
			}
			if (StringUtils.isNotBlank(xmlContent)) {
				this.lotterySsqMedia500WanService.saveHistoryMediaStat(xmlContent, i < 10000 ? "0" + i : i + "");
			}
		}

	}

	/**
	 * 从当前配置URL补充历史开奖号码
	 */
	@SuppressWarnings("unchecked")
	public void initHistoryOpenCode() {
		new LotterySsqConifgService();
		String xmlData = HttpHtmlService.getXmlContent(LotterySsqFetchConfig.media500WanUrl);
		if (StringUtils.isBlank(xmlData)) {
			return;
		}
		Document document = null;
		try {
			document = DocumentHelper.parseText(xmlData);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		int expectInt = NumberUtils.toInt(LotterySsqConfig.expect);
		String xmlContent = "";
		Map map = this.dao.getSsqLotteryHisOpenCodeMaxExpect();
		String hisExpect = "";
		if (MapUtils.isNotEmpty(map)) {
			hisExpect = (String) map.get("expect");
		} else {
			hisExpect = "08088";
		}
		expectInt--;
		for (int i = expectInt; i > NumberUtils.toInt(hisExpect); i--) {
			xmlContent = LotterySsqMedia500WanUtils.getHistoryMediaXml(i < 10000 ? "0" + i : i + "");
			if (StringUtils.isNotBlank(xmlContent)) {
				int sum = 0;
				try {
					document = DocumentHelper.parseText(xmlContent);
				} catch (Exception e) {
					logger.error(e.getMessage());
					document = null;
				}
				if (i % 1000 > 158) {
					i = i / 1000 * 1000 + 155;
					continue;
				}
				if (document == null) {
					continue;
				}

				String[] redCode = LotterySsqMedia500WanUtils.getHistoryOpenRedcode(document);
				if (redCode == null || redCode.length != 6) {
					continue;
				}
				for (int j = 0; j < redCode.length; j++) {
					sum += NumberUtils.toInt(redCode[j]);
				}
				this.dao.saveSsqLotteryHisOpenCode(LotterySsqMedia500WanUtils.getHistoryOpenBlueCode(document), StringUtils.join(redCode, ","), sum, i < 10000 ? "0" + i : i + "");
			}
		}

	}

	/**
	 * 从文件中读取媒体历史预测号码
	 */
	public void initHistoryMediaStatForFile() {
		String path = "D:/myproject/myselflearning/lottery/ssq_media/";
		File file = new File(path);
		File[] listFile = null;
		if (file.isDirectory()) {
			listFile = file.listFiles();
		}
		if (listFile != null) {
			for (int i = 0; i < listFile.length; i++) {
				try {
					String xmlContent = LotteryUtils.getFileContent(listFile[i]);
					this.lotterySsqMedia500WanService.saveHistoryMediaStat(xmlContent, LotterySsqConfig.expect);
				} catch (IOException e) {
					logger.error(listFile[i].getName() + "===" + e.getMessage());
				}
			}
		}
	}

	/**
	 * 从文件中读取媒体历史开奖号码
	 */
	public void initHistoryOpenCodeFromFile() {
		String path = "D:/myproject/myselflearning/lottery/ssq_media/";
		File file = new File(path);
		File[] listFile = null;
		if (file.isDirectory()) {
			listFile = file.listFiles();
		}
		if (listFile != null) {
			for (int i = 0; i < listFile.length; i++) {
				try {
					String xmlContent = LotteryUtils.getFileContent(listFile[i]);
					Document document = null;
					try {
						document = DocumentHelper.parseText(xmlContent);
					} catch (Exception e) {
						logger.error(e.getMessage());
						e.printStackTrace();
						logger.error(xmlContent);
					}
					int sum = 0;
					String[] redCode = LotterySsqMedia500WanUtils.getHistoryOpenRedcode(document);
					for (int j = 0; j < redCode.length; j++) {
						sum += NumberUtils.toInt(redCode[j]);
					}
					this.dao.saveSsqLotteryHisOpenCode(LotterySsqMedia500WanUtils.getHistoryOpenBlueCode(document), StringUtils.join(redCode, ","), sum, LotterySsqConfig.expect);
				} catch (IOException e) {
					logger.error(listFile[i].getName() + "===" + e.getMessage());
				}
			}
		}
	}

	/**
	 * 抓取媒体推荐html内容--sina
	 */
	public void fetchMediaSinaContent() {
		this.dao.clearHisFetchProjectCode(LotterySsqConfig.expect, "4");
		String mediaContent = this.dao.getSsqLotteryMediaContentByExpect(LotterySsqConfig.expect, "1");
		String htmlData = "";
		if (StringUtils.isBlank(mediaContent) || mediaContent.length() < 100) {
			htmlData = HttpHtmlService.getHtmlContent(LotterySsqFetchConfig.mediaSinaUrl, "GB2312");
			if (StringUtils.isBlank(htmlData) || htmlData.length() < 100) {
				return;
			}
			mediaContent = htmlData;
			String existStr = "第" + LotterySsqConfig.expect + "期双色球";
			String firstTr = HtmlParseUtils.getElementByTagName(HtmlParseUtils.getElementById(mediaContent, "table1"), "tr", 0);
			if (StringUtils.indexOf((StringUtils.remove(firstTr, " ")), existStr) != -1) {
				this.dao.saveSsqLotteryMedia("1", LotterySsqConfig.expect, HtmlParseUtils.getElementById(mediaContent, "table1"));
				mediaContent = HtmlParseUtils.getElementById(mediaContent, "table1");
			}
		}
		List<String> sinaList = this.lotterySsqMediaSinaService.getCurrentMediaRedCode(mediaContent);
		List<Map<String, String>> rsList = new ArrayList<Map<String, String>>();
		String code = "";
		for (String redCode : sinaList) {
			if ("".equals(code)) {
				code = redCode;
			} else {
				code+= "@@" + redCode;
			}
		}
		Map<String, String> map = new HashMap<String, String>();
		map.put("code", code);
		map.put("net", "4");
		map.put("expect", LotterySsqConfig.expect);
		map.put("proid", RandomUtils.nextInt(999999999) + "");
		rsList.add(map);
		this.dao.batchSaveSsqLotteryCollectFetch(rsList);

	}

	/**
	 * 抓取媒体推荐html内容--500wan
	 */
	public void fetchMedia500WanContent() {

		this.dao.clearHisFetchProjectCode(LotterySsqConfig.expect, "3");
		String mediaContent = this.dao.getSsqLotteryMediaContentByExpect(LotterySsqConfig.expect, "0");
		String xmlData = "";
		if (StringUtils.isBlank(mediaContent) || mediaContent.length() < 100) {
			xmlData = HttpHtmlService.getXmlContent(LotterySsqFetchConfig.media500WanUrl);
			if (StringUtils.isBlank(xmlData) || xmlData.length() < 100) {
				return;
			}
			mediaContent = xmlData;
		}

		if (StringUtils.isNotBlank(mediaContent) && mediaContent.length() > 100) {
			xmlData = mediaContent;
			// xmlData = HttpHtmlService.getXmlContent(LotterySsqFetchConfig.media500WanUrl);
			// if(StringUtils.isNotBlank(xmlData)&&xmlData.length()>100&&StringUtils.isBlank(mediaContent))
			// {
			this.dao.saveSsqLotteryMedia("0", LotterySsqConfig.expect, xmlData);
			Document doc = null;
			try {
				doc = DocumentHelper.parseText(xmlData);
			} catch (DocumentException e) {
				logger.error(e.getMessage());
			}
			if (doc != null) {
				List<String> sinaList = this.lotterySsqMedia500WanService.getCurrentMediaRedCode(doc);
				List<Map<String, String>> rsList = new ArrayList<Map<String, String>>();
				String code = "";
				for (String redCode : sinaList) {
					if ("".equals(code)) {
						code = redCode;
					} else {
						code += "@@" + redCode;
					}
				}
				Map<String, String> map = new HashMap<String, String>();
				map.put("code", code);
				map.put("net", "3");
				map.put("expect", LotterySsqConfig.expect);
				map.put("proid", RandomUtils.nextInt(999999999) + "");
				rsList.add(map);
				this.dao.batchSaveSsqLotteryCollectFetch(rsList);
			}
			// }
		}
	}

	/**
	 * 新浪媒体擂台胆保存
	 */
	public void fetchMediaSinaDan() {
		String mediaSina = this.dao.getSsqLotteryMediaContentByExpect(LotterySsqConfig.expect, "1");
		if (StringUtils.isBlank(mediaSina) || mediaSina.length() < 100) {
			return;
		}
		List<String> list = this.lotterySsqMediaSinaService.getCurrentMediaDanRedCode(mediaSina);
		if (CollectionUtils.isEmpty(list)) {
			return;
		}
		this.lotterySsqMediaSinaService.saveCurrrentMediaDanRedCode(list);
	}
	public void tempDeleteMediaRedCodeFromFilter(){
		this.lotterySsqMedia500WanService.deleteMedia500WanRedCode();
		this.lotterySsqMediaSinaService.deleteMediaSinaRedCode();
	}
	public static void main(String[] args) {
		LotteryInitService init=new LotteryInitService();
		init.saveLottoryResult("04,17,23,27,28,32");
	}
	/**
	 * 把双色球的所有可能集合初始化如Filter
	 */
	@SuppressWarnings("unchecked")
	public void initFilterResult() {
		int count = this.dao.getTotalLotteryAllResult();
		int last = 0;
		int page = 40000;
		while (last < count) {
			List list = this.dao.getLottoryAllResultLimit(last, page);
			last += page;
			if(CollectionUtils.isNotEmpty(list)){
				this.dao.batchInitSaveSsqLotteryFilterResult(list);
			}
		}	
	}

	/**
	 * 用户投注的历史号码中奖情况
	 */
	@SuppressWarnings("unchecked")
	public void hisDrawsRedcode(String redCode,String expect) {
		logger.info("第"+expect+"期开始计算...");
		String[] redcodes=redCode.split(",");
		int first=0;
		int page=40000;
		
//		int count=this.dao.getTotalLotteryCollectResult(expect);
		List list=null;
		while(true){
			list=this.dao.getSsqLotteryCollectResultLimit(first, page,expect);
			first+=page;
			if(CollectionUtils.isEmpty(list)){
				break;
			}
			for (Iterator iterator = list.iterator(); iterator.hasNext();) {
				int tCount=0;
				Map map = (Map) iterator.next();
				//s.first,s.second,s.third,s.fourth,firth,sixth
				String[] tmp=ObjectUtils.toString(map.get("redcode")).split(",");//new String[]{ObjectUtils.toString(map.get("first")),ObjectUtils.toString(map.get("second")),ObjectUtils.toString(map.get("third")),ObjectUtils.toString(map.get("fourth")),ObjectUtils.toString(map.get("firth")),ObjectUtils.toString(map.get("sixth"))};
				for (int i = 0; i < redcodes.length; i++) {
					for (int j = 0; j < tmp.length; j++) {
						if(StringUtils.equals(redcodes[i], tmp[j])){
							tCount++;
						}
					}
				}
				if(tCount>4){
					logger.info(StringUtils.join(tmp,",")+"("+ObjectUtils.toString(map.get("c"))+")"+"中了"+tCount);
				}
			}
		}
		
		
	}
	
	/**
	 * 遗留问题
	 */
	public void deleteMediaFetch(){
		String mediaContent = this.dao.getSsqLotteryMediaContentByExpect(LotterySsqConfig.expect, "0");
		String xmlData = "";
		List<String> resultList=new ArrayList<String>();
		if (StringUtils.isBlank(mediaContent) || mediaContent.length() < 100) {
			xmlData = HttpHtmlService.getXmlContent(LotterySsqFetchConfig.media500WanUrl);
			if (StringUtils.isBlank(xmlData) || xmlData.length() < 100) {
				return;
			}
			mediaContent = xmlData;
		}

		if (StringUtils.isNotBlank(mediaContent) && mediaContent.length() > 100) {
			xmlData = mediaContent;
			// xmlData = HttpHtmlService.getXmlContent(LotterySsqFetchConfig.media500WanUrl);
			// if(StringUtils.isNotBlank(xmlData)&&xmlData.length()>100&&StringUtils.isBlank(mediaContent))
			// {
			this.dao.saveSsqLotteryMedia("0", LotterySsqConfig.expect, xmlData);
			Document doc = null;
			try {
				doc = DocumentHelper.parseText(xmlData);
			} catch (DocumentException e) {
				logger.error(e.getMessage());
			}
			if (doc != null) {
				List<String> wList = this.lotterySsqMedia500WanService.getCurrentMediaRedCode(doc);
				for(String redCode:wList){
					LotteryUtils.select(6, redCode, resultList);
					if(resultList.size()>2000){
						this.dao.deleteSsqLotteryFilterResult(resultList);
						resultList.clear();
					}
				}
			}
		}
		
		mediaContent = this.dao.getSsqLotteryMediaContentByExpect(LotterySsqConfig.expect, "1");
		String htmlData = "";
		if (StringUtils.isBlank(mediaContent) || mediaContent.length() < 100) {
			htmlData = HttpHtmlService.getHtmlContent(LotterySsqFetchConfig.mediaSinaUrl, "GB2312");
			if (StringUtils.isBlank(htmlData) || htmlData.length() < 100) {
				return;
			}
			mediaContent = htmlData;
			String existStr = "第" + LotterySsqConfig.expect + "期双色球";
			String firstTr = HtmlParseUtils.getElementByTagName(HtmlParseUtils.getElementById(mediaContent, "table1"), "tr", 0);
			if (StringUtils.indexOf((StringUtils.remove(firstTr, " ")), existStr) != -1) {
				this.dao.saveSsqLotteryMedia("1", LotterySsqConfig.expect, HtmlParseUtils.getElementById(mediaContent, "table1"));
				mediaContent = HtmlParseUtils.getElementById(mediaContent, "table1");
			}
		}
		List<String> sinaList = this.lotterySsqMediaSinaService.getCurrentMediaRedCode(mediaContent);
		
		for(String redCode:sinaList){
			LotteryUtils.select(6, redCode, resultList);
			if(resultList.size()>2000){
				this.dao.deleteSsqLotteryFilterResult(resultList);
				resultList.clear();
			}
		}
		if(resultList.size()>0){
			this.dao.deleteSsqLotteryFilterResult(resultList);
			resultList.clear();
		}
	}
}
