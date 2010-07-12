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
import com.lottery.ssq.config.LotterySsqConfig;
import com.lottery.ssq.config.LotterySsqFilterConfig;
import com.lottery.ssq.dao.LotteryDao;
import com.lottery.ssq.filter.LotterySsqFilterUtils;

@SuppressWarnings("unchecked")
public class LotterySsqService {
	private static final org.slf4j.Logger logger = LoggerFactory.getLogger(LotterySsqService.class);
	private LotteryDao dao = null;
	private boolean isSaveToDatabase = true;
	private static List<String> customerDanList = new ArrayList<String>();
	private static List<String> sinaDanList = new ArrayList<String>();
	private List<String[]> sinaRedCodeList = new ArrayList<String[]>();
	private Set<String[]> wan500RedCodeList = new HashSet<String[]>();
	private List<String> customerMaxSelected = new ArrayList<String>();
	private List methodList = new ArrayList();
	private LotterySsqMediaSinaService lotterySsqMediaSinaService = null;
	private LotterySsqFileService lotterySsqFileService = null;
	private List customerGtCount5RedList = new ArrayList();
	private List customerLeCount3RedList = new ArrayList();

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

	/**
	 * 媒体推荐号码 新浪和500万
	 * 
	 * @param sinaRedCodeList
	 * @param redCodeList
	 */
	private void initFilterMediaRedCode() {
		List wanList = this.dao.getSsqLotteryCollectFetchByType("3");
		List sinaList = this.dao.getSsqLotteryCollectFetchByType("4");
		if (CollectionUtils.isNotEmpty(wanList)) {
			Map map = (Map) wanList.get(0);
			String[] wan = ObjectUtils.toString(map.get("code")).split("@@");
			for (String code : wan) {
				wan500RedCodeList.add(StringUtils.split(code, "+")[0].split(","));
			}
		}
		if (CollectionUtils.isNotEmpty(sinaList)) {
			Map map = (Map) sinaList.get(0);
			String[] sina = ObjectUtils.toString(map.get("code")).split("@@");
			for (String code : sina) {
				sinaRedCodeList.add(StringUtils.split(code, "+")[0].split(","));
			}
		}
	}

	public void getCurrentExpertSingleResult() {
		/* 媒体推荐号码 */
		this.initFilterMediaRedCode();
		// ****胆的查询***/
		this.initFilterDanRedCode();
		// 用户投注--投注号码的总统计（不分号码位置）
		initFilterCustomerSingleRedCodeStat();
		// 过滤方法的动态加载
		this.initFilterDynamicMethod();
		// 用户投注的加载
		this.initFilterCustomerCode();
		List<String> redList = new ArrayList<String>();
		int count = this.dao.getTotalLotteryFilterResult();
		int last = 0;
		int page = 50000;
		logger.info("开始生成过滤号码了.............");
		while (last < count) {
			List list = this.dao.getSsqLottoryFilterResultLimit(last, page);
			last += page;
			logger.info("已经计算了" + last + "个号码了");
			for (Iterator iterator = list.iterator(); iterator.hasNext();) {
				Map lValue = (Map) iterator.next();
				String[] lValues = StringUtils.split(ObjectUtils.toString(lValue.get("value")), ",");
				int qOne = 0;
				int qTwo = 0;
				int qThree = 0;

				// ~~~~~~~~~~~~~~~~~~~~~基本过滤的一些方法~~~~~~~~~~~~~~~
				if (CollectionUtils.isEmpty(methodList)) {
					continue;
				}
				boolean isContinue = true;
				for (int i = 0; i < methodList.size(); i++) {
					Map methodMap = (Map) methodList.get(i);
					String methodName = (String) methodMap.get("method_name");
					String arg = ObjectUtils.toString(methodMap.get("args1"));
					if (!LotterySsqFilterUtils.standardFilterMethod(methodName, arg, lValues)) {
						isContinue = false;
						break;
					}
					if (!LotterySsqFilterUtils.sinaFilterMethod(methodName, arg, lValues, sinaDanList, sinaRedCodeList)) {
						isContinue = false;
						break;
					}
					if (!LotterySsqFilterUtils.wan500FilterMethod(methodName, arg, lValues, wan500RedCodeList)) {
						isContinue = false;
						break;
					}
					if (!LotterySsqFilterUtils.customerFilterMethod(methodName, arg, lValues, customerDanList, customerLeCount3RedList, customerGtCount5RedList)) {
						isContinue = false;
						break;
					}
				}
				if (!isContinue) {
					continue;
				}
				if (LotterySsqFilterConfig.customerLeCount3RedList == 1) {
					int first = 0;
					List customerLeCountRedList = null;
					boolean start = true;
					String mergeCode = "";
					while (CollectionUtils.isNotEmpty(customerLeCountRedList) || start) {
						customerLeCountRedList = this.dao.getSsqLotteryCollectResultCountLe1(first, page);
						first += page;
						mergeCode = LotterySsqCollectResultAlgorithm.isCumstomerRedIncludeFiveCode(lValues, customerLeCountRedList, mergeCode);
						start = false;
					}
					if (!LotterySsqFilterUtils.isCumstomerRedIncludeFiveCode(lValues, mergeCode)) {
						customerLeCountRedList.clear();
						continue;
					}
				}

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

	/**
	 * 用户投注号码
	 */
	private void initFilterCustomerCode() {
		if (LotterySsqFilterConfig.customerGtCount5RedList == 1) {
			customerGtCount5RedList = this.dao.getSsqLotteryCollectResultCountLessThan5();
		}
		// if (LotterySsqFilterConfig.customerLeCount3RedList == 1) {
		// customerLeCount3RedList = this.dao.getSsqLotteryCollectResultCountLe3();
		// }
		// List firstRedCodeList = this.dao.getSsqLotteryCollectResult("first", 0, 20);
		// String[] firstRedCode = LotterySsqUtils.mergeRedCode(firstRedCodeList);
		// List secondRedCodeList = this.dao.getSsqLotteryCollectResult("second", 0, 20);
		// String[] secondRedCode = LotterySsqUtils.mergeRedCode(secondRedCodeList);
		// List thirdRedCodeList = this.dao.getSsqLotteryCollectResult("third", 0, 20);
		// String[] thirdRedCode = LotterySsqUtils.mergeRedCode(thirdRedCodeList);
		// List fourthRedCodeList = this.dao.getSsqLotteryCollectResult("fourth", 0, 20);
		// String[] fourthRedCode = LotterySsqUtils.mergeRedCode(fourthRedCodeList);
		// List firthRedCodeList = this.dao.getSsqLotteryCollectResult("firth", 0, 20);
		// String[] firthRedCode = LotterySsqUtils.mergeRedCode(firthRedCodeList);
		// List sixthRedCodeList = this.dao.getSsqLotteryCollectResult("sixth", 0, 20);
		// String[] sixthRedCode = LotterySsqUtils.mergeRedCode(sixthRedCodeList);
	}

	/**
	 * 动态过滤方法的加载
	 */
	private void initFilterDynamicMethod() {
		methodList = this.dao.getSsqLotteryDynamicFilterMethod();
	}

	/**
	 * 用户投注--投注号码的总统计（不分号码位置）
	 */
	private void initFilterCustomerSingleRedCodeStat() {
		List selectedList = this.dao.getSsqLotteryFetchResultSort();
		for (Iterator iterator = selectedList.iterator(); iterator.hasNext();) {
			Map map = (Map) iterator.next();
			customerMaxSelected.add(ObjectUtils.toString(map.get("redcode")));
		}
	}

	/**
	 * 胆号 新浪和用户投注号码
	 */

	private void initFilterDanRedCode() {
		List list = this.dao.getSsqLotteryDanResult("0");
		for (Iterator iterator = list.iterator(); iterator.hasNext();) {
			Map obj = (Map) iterator.next();
			sinaDanList.add(ObjectUtils.toString(obj.get("dan")));
		}
		list = this.dao.getSsqLotteryDanResult("1");
		for (Iterator iterator = list.iterator(); iterator.hasNext();) {
			Map obj = (Map) iterator.next();
			customerDanList.add(ObjectUtils.toString(obj.get("dan")));
		}
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

	public void filterCurrentRedCodeFirst() {
		logger.info("开始从过滤号码中删除抓取号码........");
		this.deleteSsqLotteryAllFilterResult();
		int count = this.dao.getTotalLotteryAllResult();
		int last = 0;
		int page = 40000;
		logger.info("开始从过滤号码中删除不合理的号码(必须有一个上一期的号码或有边号或连号).............");
		while (last < count) {
			List list = this.dao.getLottoryAllResultLimit(last, page);
			// List list = this.dao.getLottoryAllResultLimit(last, page,"01,12,14,29,31,32");
			last += page;
			filterRedCode(list);
		}
		this.dao.saveLotteryGenLog("1", LotterySsqConfig.expect, "1");
		logger.info("第一步过滤号码完成...");
	}

	/**
	 * 删除过滤号码
	 */

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
				String redCode = ObjectUtils.toString(map.get("first")) + "," + ObjectUtils.toString(map.get("second")) + "," + ObjectUtils.toString(map.get("third")) + "," + ObjectUtils.toString(map.get("fourth")) + "," + ObjectUtils.toString(map.get("firth")) + ","
						+ ObjectUtils.toString(map.get("sixth"));
				redCodeList.add(redCode);
			}
			this.dao.batchDelSsqLotteryFilterResult(redCodeList);
			redCodeList.clear();
		}
	}

	/**
	 * 从文件中读取数据过滤不再使用
	 * 
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
		this.dao.updateLotterySsqFilterConfig("1", "is_reFilter");
	}

	public void clearHisSsqData() {
		this.lotterySsqFileService.clearCollectFile();
		this.clearHisDanResult();
		this.dao.deleteSsqLotteryFilterResult();
	}

	private void clearHisDanResult() {
		this.dao.clearLotterySsqDanResult();
	}

	// ~~~~~~~~~~~~~~~~~~~~~从抓取号码中生产过滤号码~~~~~~~~~~~~~~~~~
	public void genFilterRedCodeFromCollectResult() {
		/* 媒体推荐号码 */
		this.initFilterMediaRedCode();
		// ****胆的查询***/
		this.initFilterDanRedCode();
		// 用户投注--投注号码的总统计（不分号码位置）
		initFilterCustomerSingleRedCodeStat();
		// 过滤方法的动态加载
		this.initFilterDynamicMethod();
		// 用户投注的加载
		this.initFilterCustomerCode();
		List<String> redList = new ArrayList<String>();
		int last = 0;
		int page = 50000;
		logger.info("开始从抓取号码中生产过滤号码.............");
		List list = null;
		boolean start = true;
		while (CollectionUtils.isNotEmpty(list) || start) {
			start = false;
			list = this.dao.getSsqLotteryCollectResultCountLe2(last, page);
			last += page;
			logger.info("已经计算从抓取号码中生产过滤号码" + last + "个号码了");
			for (Iterator iterator = list.iterator(); iterator.hasNext();) {
				Map lValue = (Map) iterator.next();
				String[] lValues = StringUtils.split(ObjectUtils.toString(lValue.get("value")), ",");
				int qOne = 0;
				int qTwo = 0;
				int qThree = 0;

				// ~~~~~~~~~~~~~~~~~~~~~基本过滤的一些方法~~~~~~~~~~~~~~~
				if (CollectionUtils.isEmpty(methodList)) {
					continue;
				}
				if (StringUtils.isBlank(LotterySsqAlgorithm.initFilterRedCode(ObjectUtils.toString(lValue.get("value"))))) {
					continue;
				}
				boolean isContinue = true;
				for (int i = 0; i < methodList.size(); i++) {
					Map methodMap = (Map) methodList.get(i);
					String methodName = (String) methodMap.get("method_name");
					String arg = ObjectUtils.toString(methodMap.get("args1"));
					if (!LotterySsqFilterUtils.standardFilterMethod(methodName, arg, lValues)) {
						isContinue = false;
						break;
					}
					if (!LotterySsqFilterUtils.sinaFilterMethod(methodName, arg, lValues, sinaDanList, sinaRedCodeList)) {
						isContinue = false;
						break;
					}
					if (!LotterySsqFilterUtils.wan500FilterMethod(methodName, arg, lValues, wan500RedCodeList)) {
						isContinue = false;
						break;
					}
					if (!LotterySsqFilterUtils.customerFilterMethod(methodName, arg, lValues, customerDanList, customerLeCount3RedList, customerGtCount5RedList)) {
						isContinue = false;
						break;
					}
				}
				if (!isContinue) {
					continue;
				}
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

	public void finishFilterRedCodeFromCollectResult() {
		this.dao.updateLotterySsqFilterConfig("1", "genFilterRedCodeFromCollectResult");

	}
}
