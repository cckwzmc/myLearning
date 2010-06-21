package com.lottery.ssq.service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.slf4j.LoggerFactory;

import com.lottery.ssq.Algorithm.LotterySsqAlgorithm;
import com.lottery.ssq.Algorithm.LotterySsqCollectResultAlgorithm;
import com.lottery.ssq.Algorithm.LotterySsqFirstFilterAlgorithm;
import com.lottery.ssq.Algorithm.LotterySsqMediaAlgorithm;
import com.lottery.ssq.config.LotterySsqConfig;
import com.lottery.ssq.config.LotterySsqFilterConfig;
import com.lottery.ssq.dao.LotteryDao;
import com.lottery.ssq.utils.LotteryServiceUtils;

public class LotterySsqService {
	private static final org.slf4j.Logger logger = LoggerFactory.getLogger(LotterySsqService.class);
	private LotteryDao dao = null;
	private boolean isSaveToDatabase = true;
	private static List<String> danList = new ArrayList<String>();
	private static List<String> sinaDanList = new ArrayList<String>();
	private LotterySsqMediaSinaService lotterySsqMediaSinaService = null;
	private LotterySsqFileService lotterySsqFileService = null;

	public void setLotterySsqMediaSinaService(LotterySsqMediaSinaService lotterySsqMediaSinaService) {
		this.lotterySsqMediaSinaService = lotterySsqMediaSinaService;
	}

	public void setLotterySsqFileService(LotterySsqFileService lotterySsqFileService) {
		this.lotterySsqFileService = lotterySsqFileService;
	}

	public void setDao(LotteryDao dao) {
		this.dao = dao;
	}

	public void saveLottoryResult(String redResult) {
		this.dao.saveSsqLottoryResult(redResult);
	}

	public void saveTempLottoryResult(String redResult) {
		this.dao.saveTempLottoryResult(redResult);
	}

	@SuppressWarnings("unchecked")
	public void getCurrentExpertSingleResult() {
		/* 媒体推荐号码 */
		List wanList=this.dao.getSsqLotteryCollectFetchByType("3");
		List sinaList=this.dao.getSsqLotteryCollectFetchByType("4");
		List<String[]> sinaRedCodeList = new ArrayList<String[]>();
		Set<String[]> redCodeList = new HashSet<String[]>();
		if(CollectionUtils.isNotEmpty(wanList))
		{	
			Map map=(Map) wanList.get(0);
			String[] wan=ObjectUtils.toString(map.get("code")).split("@@");
			for(String code:wan){
				redCodeList.add(StringUtils.split(code,"+")[0].split(","));
			}
		}
		if(CollectionUtils.isNotEmpty(sinaList))
		{
			Map map=(Map) sinaList.get(0);
			String[] sina=ObjectUtils.toString(map.get("code")).split("@@");
			for(String code:sina){
				sinaRedCodeList.add(StringUtils.split(code,"+")[0].split(","));
				redCodeList.add(StringUtils.split(code,"+")[0].split(","));
			}
		}
		/** 文本收集的号码 ***/
		// Set<String> fileRedCode=this.lotterySsqFileService.getRedCodeFromFile();
		// ****胆的查询***/
		List list = new ArrayList();
		list = this.dao.getSsqLotteryDanResult("0");
		for (Iterator iterator = list.iterator(); iterator.hasNext();) {
			Map obj = (Map) iterator.next();
			sinaDanList.add(ObjectUtils.toString(obj.get("dan")));
		}
		list = this.dao.getSsqLotteryDanResult("1");
		for (Iterator iterator = list.iterator(); iterator.hasNext();) {
			Map obj = (Map) iterator.next();
			danList.add(ObjectUtils.toString(obj.get("dan")));
		}
		// 用户投注过滤 --个号
		List selectedList = this.dao.getSsqLotteryFetchResultSort();
		List<String> customerMaxSelected = new ArrayList<String>();
		for (Iterator iterator = selectedList.iterator(); iterator.hasNext();) {
			Map map = (Map) iterator.next();
			customerMaxSelected.add(ObjectUtils.toString(map.get("redcode")));
		}
		// 网上媒体其他收集
		Set<String> otherRedCodeSet = this.lotterySsqFileService.getRedCodeFromFile();
		List<String> otherRedCodeList = new ArrayList<String>();
		otherRedCodeList.addAll(otherRedCodeSet);
		for (int i = 0; CollectionUtils.isNotEmpty(otherRedCodeList) && i < otherRedCodeList.size(); i++) {
			String[] tmp = otherRedCodeList.get(i).split(",");
			if (tmp.length >= 15 && tmp.length > 6) {
				otherRedCodeList.remove(i);
				i--;
				continue;
			}
		}
		// 用户投注最多的前30排行 --组号
		List cRedList = this.dao.getSsqLotteryCollectResultCountLessThan5();// this.dao.getSsqLotteryCollectResultTopN(30);
		String[] customerRedTop40 = LotteryServiceUtils.mergeRedCode(cRedList);
		List firstRedCodeList = this.dao.getSsqLotteryCollectResult("first", 0, 20);
		String[] firstRedCode = LotteryServiceUtils.mergeRedCode(firstRedCodeList);
		List secondRedCodeList = this.dao.getSsqLotteryCollectResult("second", 0, 20);
		String[] secondRedCode = LotteryServiceUtils.mergeRedCode(secondRedCodeList);
		List thirdRedCodeList = this.dao.getSsqLotteryCollectResult("third", 0, 20);
		String[] thirdRedCode = LotteryServiceUtils.mergeRedCode(thirdRedCodeList);
		List fourthRedCodeList = this.dao.getSsqLotteryCollectResult("fourth", 0, 20);
		String[] fourthRedCode = LotteryServiceUtils.mergeRedCode(fourthRedCodeList);
		List firthRedCodeList = this.dao.getSsqLotteryCollectResult("firth", 0, 20);
		String[] firthRedCode = LotteryServiceUtils.mergeRedCode(firthRedCodeList);
		List sixthRedCodeList = this.dao.getSsqLotteryCollectResult("sixth", 0, 20);
		String[] sixthRedCode = LotteryServiceUtils.mergeRedCode(sixthRedCodeList);
		List<String> redList = new ArrayList<String>();
		int count = this.dao.getTotalLotteryFilterResult();
		int last = 0;
		int page = 50000;
		logger.info("开始生成过滤号码了.............");
		while (last < count) {
			list = this.dao.getSsqLottoryFilterResultLimit(last, page);
			last += page;

			for (Iterator iterator = list.iterator(); iterator.hasNext();) {
				Map lValue = (Map) iterator.next();
				String[] lValues = StringUtils.split(ObjectUtils.toString(lValue.get("value")), ",");
				int qOne = 0;
				int qTwo = 0;
				int qThree = 0;

				// ~~~~~~~~~~~~~~~~~~~~~基本过滤的一些方法~~~~~~~~~~~~~~~
				// 数字的范围
				if (!LotterySsqAlgorithm.isRedNumericInRange(lValues)) {
					continue;
				}
				// 是否需要胆
				if (!LotterySsqAlgorithm.isRedIncludeRequiredCode(lValues)) {
					continue;
				}
				// 是否包含上一期的号码
				if (!LotterySsqAlgorithm.isRedIncludePreRedCode(lValues)) {
					continue;
				}
				// 是否包括边号
				if (!LotterySsqAlgorithm.isRedIncludeSideCode(lValues)) {
					continue;
				}
				// 不能出现的号码
				if (!LotterySsqAlgorithm.isRedNotIncludeTheCode(lValues)) {
					continue;
				}
				// 在指定的一系列号码中选取6个
				if (!LotterySsqAlgorithm.isRedInTheCodes(lValues)) {
					continue;
				}
				// 不能同时出现的号码多组用"|"分割
				if (!LotterySsqAlgorithm.isRedTogethorCode(lValues)) {
					continue;
				}
				// 是否包含两连号
				if (!LotterySsqAlgorithm.isRedIncludeEvenIn(lValues)) {
					continue;
				}
				// 是否包含三连号
				if (!LotterySsqAlgorithm.isRedIncludeThreeEvenIn(lValues)) {
					continue;
				}
				// 是否包含隔号
				if (!LotterySsqAlgorithm.isRedIncludeDifferCode(lValues)) {
					continue;
				}
				// 至少中其中的一个号码
				if (!LotterySsqAlgorithm.isLeastSelectedOneCode(lValues)) {
					continue;
				}
				// 必须选择一个/是并的关系而不是或得关系
				if (!LotterySsqAlgorithm.isMustSelectedOneCode(lValues)) {
					continue;
				}
				// 最多只能其中的一个号码
				if (!LotterySsqAlgorithm.isSelectOneCode(lValues)) {
					continue;
				}
				/** 新浪媒体擂台 **/
				// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
				if (!LotterySsqMediaAlgorithm.isSinaDanTogethorFilter(lValues, sinaDanList)) {
					continue;
				}
				if (!LotterySsqMediaAlgorithm.isSinaDanAllFilter(lValues, sinaDanList)) {
					continue;
				}
				if (!LotterySsqMediaAlgorithm.isSinaDanNoneFilter(lValues, sinaDanList, 5)) {
					continue;
				}
				if (!LotterySsqMediaAlgorithm.isSinaRedCodeXiaoFourFilter(lValues, sinaRedCodeList)) {
					continue;
				}
				if (!LotterySsqMediaAlgorithm.isRedIncludeFourCode(lValues, redCodeList)) {
					continue;
				}
				/** 用户投注号码 **/
				boolean locationFlag = true;
				for (int j = 2; j < 6; j++) {
					String[] tmp = null;
					if (j == 2) {
						tmp = thirdRedCode;
					}
					if (j == 3) {
						tmp = fourthRedCode;
					}
					if (j == 4) {
						tmp = firthRedCode;
					}
					if (j == 5) {
						tmp = sixthRedCode;
					}
					if (!LotterySsqCollectResultAlgorithm.isIncludeLocationRedCode(lValues[j], tmp)) {
						locationFlag = false;
						break;
					}
				}
				if (!locationFlag) {
					continue;
				}
				// 用户投注的胆
				if (!LotterySsqCollectResultAlgorithm.isCustomerDanFilter(lValues, danList)) {
					continue;
				}
				// 用户投注不能中4一个以上的号码
				if (!LotterySsqAlgorithm.isRedFourCodeInCustomerResult(lValues, cRedList)) {
					continue;
				}
				// 收集号码的排行中的前10个
				// if (!LotterySsqCollectResultAlgorithm.isCustomerRedCodeTop10Filter(lValues, customerMaxSelected.subList(0, 10))) {
				// continue;
				// }
				// 收集号码的排行中的前20个
				// if (!LotterySsqCollectResultAlgorithm.isCustomerRedCodeTop20Filter(lValues, customerMaxSelected.subList(0, 20))) {
				// continue;
				// }
				// if (!LotterySsqAlgorithm.isFileRedCodeFourFilter(lValues, otherRedCodeList)) {
				// continue;
				// }

				// if (!LotterySsqAlgorithm.isRedTwoCodeInCustomerResult(lValues, cRedList.subList(0, 10))) {
				// continue;
				// }
				// if (!LotterySsqAlgorithm.isRedThreeCodeInCustomerResult(lValues, cRedList)) {
				// continue;
				// }
				// if (!LotterySsqAlgorithm.isRedFourCodeResult(lValues, customerRedTop40)) {
				// continue;
				// }
				// if (!LotterySsqAlgorithm.isLeastSelectedTwoCode(lValues, customerRedTop40)) {
				// continue;
				// }
				// if (!LotterySsqAlgorithm.isRedFourCodeInCustomerResult(lValues, cRedList)) {
				// continue;
				// }
				// 在用户投注中5个的不能超过10个
				// if(selectedRedCodeGtFive(lValues)){
				// continue;
				// }
				/*
				 * 即我认为这些号码是不可能中奖的， 推荐使用文本方式保存的号码使用
				 */
				// if(CollectionUtils.isNotEmpty(fileRedCode)&&!LotterySsqAlgorithm.isRedCodeHaveSix(fileRedCode,lValues)){
				// continue;
				// }
				for (int i = 0; i < lValues.length; i++) {
					if (LotterySsqFilterConfig.quOne != -1 && NumberUtils.toInt(lValues[i]) <= LotterySsqFilterConfig.quOneNum) {
						qOne++;
					}
					if (LotterySsqFilterConfig.quTwo != -1 && NumberUtils.toInt(lValues[i]) > LotterySsqFilterConfig.quOneNum && NumberUtils.toInt(lValues[i]) <= LotterySsqFilterConfig.quTwoNum) {
						qTwo++;
					}
					if (LotterySsqFilterConfig.quThree != -1 && NumberUtils.toInt(lValues[i]) > LotterySsqFilterConfig.quTwoNum) {
						qThree++;
					}
				}
				if (LotterySsqAlgorithm.isRedCoincidenceZone(lValues, qOne, qTwo, qThree)) {
					redList.add(ObjectUtils.toString(lValue.get("value")));
				}
			}
		}
		String f = System.currentTimeMillis() + "_" + LotterySsqConfig.expect + ".txt";
		String rsFileName = "D:/Apache2.2/htdocs/lottery_rs/" + f;
		writeFile(redList, rsFileName, false);
		String lotteryHtml = "D:/Apache2.2/htdocs/lottery_rs/index_" + LotterySsqConfig.expect + ".htm";
		String tmp = "<li><a href='" + f + "'>" + f + "</a></li>";
		writeFile(tmp, lotteryHtml, true);
	}

	@SuppressWarnings("unchecked")
	private boolean selectedRedCodeGtFive(String[] lValues) {
		int first = 0;
		int page = 40000;
		int totalCount = 0;
		int count = this.dao.getTotalLotteryCollectResult();
		while (first < count) {
			List list = this.dao.getSsqLotteryCollectResultLimit(first, page);
			first += page;
			for (Iterator iterator = list.iterator(); iterator.hasNext();) {
				int tCount = 0;
				Map map = (Map) iterator.next();
				// s.first,s.second,s.third,s.fourth,firth,sixth
				String[] tmp = new String[] { ObjectUtils.toString(map.get("first")), ObjectUtils.toString(map.get("second")), ObjectUtils.toString(map.get("third")), ObjectUtils.toString(map.get("fourth")), ObjectUtils.toString(map.get("firth")),
						ObjectUtils.toString(map.get("sixth")) };
				for (int i = 0; i < lValues.length; i++) {
					for (int j = 0; j < tmp.length; j++) {
						if (StringUtils.equals(lValues[i], tmp[j])) {
							tCount++;
						}
					}
				}
				if (tCount > 4) {
					totalCount++;
				}
			}
		}
		if (totalCount > 10) {
			return true;
		}
		return false;
	}

	/**
	 * 以追加的方式写入文件
	 * 
	 * @param redList
	 * @param fileName
	 */
	private void writeFile(List<String> redList, String fileName, boolean isAppend) {
		try {
			File file = new File(fileName);
			if (!file.exists()) {
				file.createNewFile();
			} else {
			}
			FileWriter writer = new FileWriter(file, isAppend);
			for (int i = 0; i < redList.size(); i++) {
				String filerRed = (String) redList.get(i);
				filerRed = (i == redList.size() - 1) ? filerRed : filerRed + "\n";
				writer.write(filerRed);
			}
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void writeFile(String content, String fileName, boolean isAppend) {
		try {
			File file = new File(fileName);
			if (!file.exists()) {
				file.createNewFile();
			} else {
			}
			FileWriter writer = new FileWriter(file, isAppend);
			writer.write(content);
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * private List<String> fromFileMediaData(File qsFile) throws IOException { FileReader rd = new FileReader(qsFile); BufferedReader br = new BufferedReader(rd); String line = ""; List<String> list = new ArrayList<String>(); while (line != null) { line = br.readLine(); if (line
	 * != null) { list.add(line); } } rd.close(); return list; }
	 **/
	/**
	 * 从头开始，处理媒体号码及自己收集的号码 第一次过滤
	 */
	@SuppressWarnings("unchecked")
	public void filterCurrentRedCodeFirst() {
		logger.info("开始从过滤号码中删除抓取号码........");
		this.deleteSsqLotteryAllFilterResult();
		int count = this.dao.getTotalLotteryAllResult();
		int last = 0;
		int page = 40000;
		logger.info("开始从过滤号码中删除不合理的号码(必须有一个上一期的号码或有边号或连号).............");
		while (last < count) {
			List list = this.dao.getLottoryAllResultLimit(last, page);
			last += page;
			filterRedCode(list);
		}
		this.dao.saveLotteryGenLog("1", LotterySsqConfig.expect, "1");
		logger.info("第一步过滤号码完成...");
	}

	/**
	 * 删除过滤号码
	 */
	@SuppressWarnings("unchecked")
	private void deleteSsqLotteryAllFilterResult() {
		int count = this.dao.getTotalLotteryCollectResult();
		int page = 0;
		int pagesize = 40000;
		List<String> redCodeList = new ArrayList<String>();
		while (page < count) {
			List list = this.dao.getSsqLotteryCollectResultLimit(page, pagesize);
			page += pagesize;
			for (Iterator iterator = list.iterator(); iterator.hasNext();) {
				Map map = (Map) iterator.next();
				// first,second,third,fourth,firth,sixth
				redCodeList.add(ObjectUtils.toString(map.get("first")) + "," + ObjectUtils.toString(map.get("second")) + "," + ObjectUtils.toString(map.get("third")) + "," + ObjectUtils.toString(map.get("fourth")) + "," + ObjectUtils.toString(map.get("firth")) + ","
						+ ObjectUtils.toString(map.get("sixth")));
			}
			this.dao.batchDelSsqLotteryFilterResult(redCodeList);
			redCodeList.clear();
		}
	}

	/**
	 * 从文件中读取数据过滤不再使用
	 * 
	 * @SuppressWarnings("unchecked")
	 * @Deprecated public void filterCurrentRedCodeFromFile() { // 媒体预测号码 List<String> redMedia = new ArrayList<String>(); // 本人添加的过滤号码 List<String> redFile = new ArrayList<String>(); File qsFile = new File("d:/myproject/ssq_media_red_" + LotterySsqConfig.expect + ".xml"); try {
	 * redMedia = this.fromFileMediaData(qsFile); qsFile = new File("d:/myproject/ssq_file_red_" + LotterySsqConfig.expect + ".xml"); redFile = this.fromFileMediaData(qsFile); } catch (IOException e) { e.printStackTrace(); } if (this.dao.isGenLotteryResult("1",
	 * LotterySsqConfig.expect)) { this.dao.clearSsqLotteryFilterResult(); this.dao.clearSsqLotteryCollectResult(); } int count = this.dao.getTotalLotteryAllResult(); int last = 0; int page = 60000; List list = null; while (last < count) { list =
	 * this.dao.getLottoryAllResultLimit(last, page); if (list.size() == count) { last = list.size(); } else { last += page; } // filterRedCode(redMedia, redFile, list); } this.dao.saveLotteryGenLog("1", LotterySsqConfig.expect, "1"); }
	 **/
	/**
	 * 第一步过滤号码算法 必须包含边号、连号、上一期的号码之一，，是不是要加个隔号呢？？（差值为1）
	 * 
	 * @param redMedia
	 * @param redFile
	 * @param redList
	 * @param list
	 */
	@SuppressWarnings("unchecked")
	private void filterRedCode(List list) {
		List<String> redList = new ArrayList<String>();
		for (Iterator iterator = list.iterator(); iterator.hasNext();) {
			Map lMap = (Map) iterator.next();
			String lValue = (String) lMap.get("value");
			String[] lValues = StringUtils.split(lValue, ",");

			if (!LotterySsqFirstFilterAlgorithm.isFilterRedNumericInRange(lValues)) {
				redList.add(lValue);
			}
			if (!LotterySsqFirstFilterAlgorithm.isFilterRedIncludeSideCode(lValues, LotterySsqConfig.preSideCode) && !LotterySsqFirstFilterAlgorithm.isFilterIncludePreCode(lValues, LotterySsqConfig.preRedCode)) {
				redList.add(lValue);
			}

			if (isSaveToDatabase && redList.size() > 2000) {
				this.dao.deleteSsqLotteryFilterResult(redList);
				redList.clear();
			} else if (!isSaveToDatabase && redList.size() > 2000) {
				this.writeFile(redList, "d:/myproject/ssq_red_" + LotterySsqConfig.expect + ".xml", true);
				redList.clear();
			}
		}
		if (isSaveToDatabase && CollectionUtils.isNotEmpty(redList)) {
			this.dao.deleteSsqLotteryFilterResult(redList);
			redList.clear();
		} else if (!isSaveToDatabase && CollectionUtils.isNotEmpty(redList)) {
			this.writeFile(redList, "d:/myproject/ssq_red_" + LotterySsqConfig.expect + ".xml", true);
			redList.clear();
		}
	}

	public void filterCurrentRedCode() {
		String media500Wan = this.dao.getSsqLotteryMediaContentByExpect(LotterySsqConfig.expect, "0");
		if (StringUtils.isBlank(media500Wan)) {
			logger.error("这一期的数据还没入库呢!");
			// return;
		}
		boolean isExist = this.dao.isGenLotteryResult("1", LotterySsqConfig.expect);
		if (isExist) {
			// 以追加的方式过滤号码，即在原来的基础上删除号码,只能用于文本收集方式
			logger.info("第" + LotterySsqConfig.expect + "期，双色球再次开始过滤号码(使用追加的方式,即在原来的基础上删除号码,只能用于文本收集方式)........");
			this.filterCurrentRedCodeAppend();
		} else {
			logger.info("第" + LotterySsqConfig.expect + "期，双色球第一次开始过滤号码........");
			this.filterCurrentRedCodeFirst();
		}
	}

	/**
	 * 再次删除通常是指读取文本中的来过滤
	 */
	public void filterCurrentRedCodeAppend() {
		List<String> list = this.lotterySsqFileService.getCurrentFileRedCode();
		this.dao.deleteSsqLotteryFilterResult(list);
	}

	/**
	 * 是否已经完成第一步号码过滤
	 * 
	 * @param type
	 * @param lotteryQh
	 * @return
	 */
	public boolean isGenLotteryResult(String type, String lotteryQh) {
		if (this.dao.isGenLotteryResult("1", LotterySsqConfig.expect)) {
			return true;
		}
		return false;
	}

	/**
	 * public static void main(String[] args) { File file = new File("d:/myproject/current.tt"); FileReader fr; try { fr = new FileReader(file); BufferedReader br = new BufferedReader(fr); List<String> list = new ArrayList<String>(); String line = br.readLine(); while (line !=
	 * null) { list.add(line); line = br.readLine(); } List<String> redList = new ArrayList<String>(); for (int i = 0; i < list.size(); i++) { String[] redCode = list.get(i).split(","); for (int j = 0; j < redCode.length; j++) { if (!redList.contains(redCode[j])) {
	 * redList.add(redCode[j]); } } } String out = ""; for (Iterator<String> iterator = redList.iterator(); iterator.hasNext();) { String code = iterator.next(); if ("".equals(out)) { out = code; } else { out += "," + code; } } System.out.println(out + "            " +
	 * redList.size()); } catch (FileNotFoundException e) { e.printStackTrace(); } catch (IOException e) { // ClassUtils.getDefaultClassLoader(); // Enumeration urls = null; // try { // urls = classLoader.getResources("lottery/ssq/excluderedfile.txt"); // } catch (IOException e)
	 * { // } // while (urls.hasMoreElements()) { // URL url = (URL) urls.nextElement(); // InputStream is = null; // try { // URLConnection con = url.openConnection(); // is = con.getInputStream(); // // BufferedInputStream bis = new BufferedInputStream(is); // byte[] bs = new
	 * byte[bis.available()]; // int ch = 0; // // java.nio.ByteBuffer bb=java.nio.ByteBuffer.allocate(2048); // // CharBuffer cb=CharBuffer.allocate(bis.available()); // // while(ch!=-1){ // // // bb.put(bs); // // cb.append((char)ch); // // ch=bis.read(); // // // // } //
	 * bis.read(bs); // System.out.println(new String(bs, "GBK")); // // String redCode=new String(bb.array(),"GBK"); // // String[] redCodes=StringUtils.split(redCode, '\n'); // // System.out.println(redCodes+"\n"+redCode); // } finally { // if (is != null) { // is.close(); // }
	 * // } // } // } catch (FileNotFoundException e) { // e.printStackTrace(); // } }
	 **/
	public void completCurrentGenCode() {
		this.dao.updateLotterySsqFilterConfig("1", "gen_data");
	}

	public void clearHisSsqData() {
		this.lotterySsqFileService.clearCollectFile();
		this.clearHisDanResult();
		this.dao.deleteSsqLotteryFilterResult();
	}

	private void clearHisDanResult() {
		this.dao.clearLotterySsqDanResult();
	}
}
