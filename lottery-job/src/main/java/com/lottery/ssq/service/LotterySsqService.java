package com.lottery.ssq.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.slf4j.LoggerFactory;

import com.lottery.ssq.LotterySsqAlgorithm;
import com.lottery.ssq.LotterySsqFetchConfig;
import com.lottery.ssq.LotterySsqFilterConfig;
import com.lottery.ssq.dao.LotteryDao;
import com.lottery.ssq.utils.LotteryServiceUtils;
import com.lottery.ssq.utils.LotterySsqMedia500WanUtils;
import com.lottery.ssq.utils.LotteryUtils;

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

	public void getCurrentExpertMergeResult() {
		String xmlData = this.dao.getSsqLotteryMediaContentByExpect(LotterySsqFetchConfig.expect, "0");
		if (StringUtils.isBlank(xmlData)) {
			return;
		}
	}


	@SuppressWarnings("unchecked")
	public void getCurrentExpertSingleResult() {
		/* 媒体推荐号码 */
		String media500Wan = this.dao.getSsqLotteryMediaContentByExpect(LotterySsqFetchConfig.expect, "0");
		String mediaSina = this.dao.getSsqLotteryMediaContentByExpect(LotterySsqFetchConfig.expect, "1");
		if (StringUtils.isBlank(media500Wan)) {
			return;
		}
		List<String[]> redCodeList = new ArrayList<String[]>();
		List<String[]> sinaRedCodeList = new ArrayList<String[]>();
		if (StringUtils.isNotBlank(media500Wan) && media500Wan.length() > 100) {
			try {
				Document document = DocumentHelper.parseText(media500Wan);
				redCodeList = LotterySsqMedia500WanUtils.getMediaRedCode(document);
			} catch (DocumentException e) {
				e.printStackTrace();
			}
		}
		List<String> mediaSinaList = new ArrayList<String>();
		if (StringUtils.isNotBlank(mediaSina) && mediaSina.length() > 100) {
			mediaSinaList = this.lotterySsqMediaSinaService.getCurrentMediaRedCode(mediaSina);
			if (CollectionUtils.isNotEmpty(mediaSinaList)) {
				for (String ssq : mediaSinaList) {
					sinaRedCodeList.add(ssq.split(","));
				}
				redCodeList.addAll(sinaRedCodeList);
			}
		}
		for (int i = 0; CollectionUtils.isNotEmpty(redCodeList) && i < redCodeList.size(); i++) {
			String[] ssq = redCodeList.get(i);
			if (ssq.length > 15) {
				redCodeList.remove(i);
				i--;
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
		//用户投注最多的前30排行 --组号
		List cRedList=this.dao.getSsqLotteryCollectResultTopN(30);
		String[] customerRedTop40 =LotteryServiceUtils.mergeRedCode(cRedList);
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
				if (!LotterySsqAlgorithm.isRedNumericInRange(lValues)) {
					continue;
				}
				if (!LotterySsqAlgorithm.isRedIncludeRequiredCode(lValues)) {
					continue;
				}
				if (!LotterySsqAlgorithm.isRedIncludeAnyOneCode(lValues)) {
					continue;
				}
				if (!LotterySsqAlgorithm.isRedIncludeSideCode(lValues)) {
					continue;
				}
				if (!LotterySsqAlgorithm.isRedNotIncludeTheCode(lValues)) {
					continue;
				}
				if (!LotterySsqAlgorithm.isRedInTheCodes(lValues)) {
					continue;
				}
				if (!LotterySsqAlgorithm.isRedTogethorCode(lValues)) {
					continue;
				}
				if (!LotterySsqAlgorithm.isSinaDanTogethorFilter(lValues, sinaDanList)) {
					continue;
				}
				if (!LotterySsqAlgorithm.isSinaDanAllFilter(lValues, sinaDanList)) {
					continue;
				}
				if (!LotterySsqAlgorithm.isSinaDanNoneFilter(lValues, sinaDanList, 5)) {
					continue;
				}
				if (!LotterySsqAlgorithm.isSinaRedCodeXiaoFourFilter(lValues, mediaSinaList)) {
					continue;
				}
				if (!LotterySsqAlgorithm.isCustomerDanFilter(lValues, danList)) {
					continue;
				}
				if (!LotterySsqAlgorithm.isCustomerRedCodeTop10Filter(lValues, customerMaxSelected.subList(0, 10))) {
					continue;
				}
//				if (!LotterySsqAlgorithm.isCustomerRedCodeTop20Filter(lValues, customerMaxSelected.subList(0, 20))) {
//					continue;
//				}
//				if (!LotterySsqAlgorithm.isFileRedCodeFourFilter(lValues, otherRedCodeList)) {
//					continue;
//				}
				if (!LotterySsqAlgorithm.isRedIncludeEvenIn(lValues)) {
					continue;
				}
				if (!LotterySsqAlgorithm.isRedIncludeThreeEvenIn(lValues)) {
					continue;
				}
				if (!LotterySsqAlgorithm.isRedIncludeDifferCode(lValues)) {
					continue;
				}
				if (!LotterySsqAlgorithm.isRedIncludeMediaFourCode(lValues, redCodeList)) {
					continue;
				}
				if (!LotterySsqAlgorithm.isRedIncludeMediaThreeCode(lValues, sinaRedCodeList)) {
					continue;
				}
				if (!LotterySsqAlgorithm.isSelectOneCode(lValues)) {
					continue;
				}
//				if (!LotterySsqAlgorithm.isRedFourCodeInCustomerResult(lValues,cRedList)) {
//					continue;
//				}
				if (!LotterySsqAlgorithm.isRedTwoCodeInCustomerResult(lValues,cRedList.subList(0, 10))) {
					continue;
				}
				if (!LotterySsqAlgorithm.isRedThreeCodeInCustomerResult(lValues,cRedList)) {
					continue;
				}
				if(!LotterySsqAlgorithm.isRedFourCodeResult(lValues,customerRedTop40)){
					continue;
				}
				if(!LotterySsqAlgorithm.isLeastSelectedTwoCode(lValues,customerRedTop40)){
					continue;
				}
				if (!LotterySsqAlgorithm.isLeastSelectedOneCode(lValues)) {
					continue;
				}
				if (!LotterySsqAlgorithm.isMustSelectedOneCode(lValues)) {
					continue;
				}
				if (!LotterySsqAlgorithm.isRedFourCodeInCustomerResult(lValues,cRedList)) {
					continue;
				}
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
		writeFile(redList, "d:/myproject/current.xml", false);
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

	private List<String> fromFileMediaData(File qsFile) throws IOException {
		FileReader rd = new FileReader(qsFile);
		BufferedReader br = new BufferedReader(rd);
		String line = "";
		List<String> list = new ArrayList<String>();
		while (line != null) {
			line = br.readLine();
			if (line != null) {
				list.add(line);
			}
		}
		rd.close();
		return list;
	}

	/**
	 * 从头开始，处理媒体号码及自己收集的号码 第一次过滤
	 */
	@SuppressWarnings("unchecked")
	public void filterCurrentRedCodeFirst() {
//		logger.info("开始从过滤号码中删除抓取的号码.............");
//		if(this.dao.getTotalLotteryFilterResult()==0)
//		{
//			logger.info("开始初始化过滤号码的最大集合(把AllResult的都转过来)........");
////			this.lotteryInitService.saveAllRedResult("1");
//			this.initFilterResult();
//		}	
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
		this.dao.saveLotteryGenLog("1", LotterySsqFetchConfig.expect, "1");
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
	 */
	@SuppressWarnings("unchecked")
	@Deprecated
	public void filterCurrentRedCodeFromFile() {
		// 媒体预测号码
		List<String> redMedia = new ArrayList<String>();
		// 本人添加的过滤号码
		List<String> redFile = new ArrayList<String>();
		File qsFile = new File("d:/myproject/ssq_media_red_" + LotterySsqFetchConfig.expect + ".xml");
		try {
			redMedia = this.fromFileMediaData(qsFile);
			qsFile = new File("d:/myproject/ssq_file_red_" + LotterySsqFetchConfig.expect + ".xml");
			redFile = this.fromFileMediaData(qsFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (this.dao.isGenLotteryResult("1", LotterySsqFetchConfig.expect)) {
			this.dao.clearSsqLotteryFilterResult();
			this.dao.clearSsqLotteryCollectResult();
		}
		int count = this.dao.getTotalLotteryAllResult();
		int last = 0;
		int page = 60000;
		List list = null;
		while (last < count) {
			list = this.dao.getLottoryAllResultLimit(last, page);
			if (list.size() == count) {
				last = list.size();
			} else {
				last += page;
			}
			// filterRedCode(redMedia, redFile, list);
		}
		this.dao.saveLotteryGenLog("1", LotterySsqFetchConfig.expect, "1");
	}

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

			if (!LotterySsqAlgorithm.isFilterRedNumericInRange(lValues)) {
				redList.add(lValue);
			}
			if (!LotterySsqAlgorithm.isFilterRedIncludeSideCode(lValues, LotterySsqFetchConfig.preSideCode) && !LotterySsqAlgorithm.isFilterRedIncludeEvenIn(lValues) && !LotterySsqAlgorithm.isFilterIncludePreCode(lValues, LotterySsqFetchConfig.preRedCode)) {
				redList.add(lValue);
			}

			if (isSaveToDatabase && redList.size() > 2000) {
				this.dao.deleteSsqLotteryFilterResult(redList);
				redList.clear();
			} else if (!isSaveToDatabase && redList.size() > 2000) {
				this.writeFile(redList, "d:/myproject/ssq_red_" + LotterySsqFetchConfig.expect + ".xml", true);
				redList.clear();
			}
		}
		if (isSaveToDatabase && CollectionUtils.isNotEmpty(redList)) {
			this.dao.deleteSsqLotteryFilterResult(redList);
			redList.clear();
		} else if (!isSaveToDatabase && CollectionUtils.isNotEmpty(redList)) {
			this.writeFile(redList, "d:/myproject/ssq_red_" + LotterySsqFetchConfig.expect + ".xml", true);
			redList.clear();
		}
	}

	public void filterCurrentRedCode() {
		String media500Wan = this.dao.getSsqLotteryMediaContentByExpect(LotterySsqFetchConfig.expect, "0");
		if (StringUtils.isBlank(media500Wan)) {
			logger.error("这一期的数据还没入库呢!");
//			return;
		}
		boolean isExist = this.dao.isGenLotteryResult("1", LotterySsqFetchConfig.expect);
		if (isExist) {
			// 以追加的方式过滤号码，即在原来的基础上删除号码,只能用于文本收集方式
			logger.info("第" + LotterySsqFetchConfig.expect + "期，双色球再次开始过滤号码(使用追加的方式,即在原来的基础上删除号码,只能用于文本收集方式)........");
			this.filterCurrentRedCodeAppend();
		} else {
			logger.info("第" + LotterySsqFetchConfig.expect + "期，双色球第一次开始过滤号码........");
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
		if (this.dao.isGenLotteryResult("1", LotterySsqFetchConfig.expect)) {
			return true;
		}
		return false;
	}

	public static void main(String[] args) {
		File file = new File("d:/myproject/current.tt");
		FileReader fr;
		try {
			fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);
			List<String> list = new ArrayList<String>();
			String line = br.readLine();
			while (line != null) {
				list.add(line);
				line = br.readLine();
			}
			List<String> redList = new ArrayList<String>();
			for (int i = 0; i < list.size(); i++) {
				String[] redCode = list.get(i).split(",");
				for (int j = 0; j < redCode.length; j++) {
					if (!redList.contains(redCode[j])) {
						redList.add(redCode[j]);
					}
				}
			}
			String out = "";
			for (Iterator<String> iterator = redList.iterator(); iterator.hasNext();) {
				String code = iterator.next();
				if ("".equals(out)) {
					out = code;
				} else {
					out += "," + code;
				}
			}
			System.out.println(out + "            " + redList.size());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// try {
		// // ClassLoader classLoader=new ClassLoader();
		// ClassLoader classLoader = ClassUtils.getDefaultClassLoader();
		// Enumeration urls = null;
		// try {
		// urls = classLoader.getResources("lottery/ssq/excluderedfile.txt");
		// } catch (IOException e) {
		// }
		// while (urls.hasMoreElements()) {
		// URL url = (URL) urls.nextElement();
		// InputStream is = null;
		// try {
		// URLConnection con = url.openConnection();
		// is = con.getInputStream();
		//
		// BufferedInputStream bis = new BufferedInputStream(is);
		// byte[] bs = new byte[bis.available()];
		// int ch = 0;
		// // java.nio.ByteBuffer bb=java.nio.ByteBuffer.allocate(2048);
		// // CharBuffer cb=CharBuffer.allocate(bis.available());
		// // while(ch!=-1){
		// // // bb.put(bs);
		// // cb.append((char)ch);
		// // ch=bis.read();
		// //
		// // }
		// bis.read(bs);
		// System.out.println(new String(bs, "GBK"));
		// // String redCode=new String(bb.array(),"GBK");
		// // String[] redCodes=StringUtils.split(redCode, '\n');
		// // System.out.println(redCodes+"\n"+redCode);
		// } finally {
		// if (is != null) {
		// is.close();
		// }
		// }
		// }
		// } catch (FileNotFoundException e) {
		// e.printStackTrace();
		// }
	}

	public void completCurrentGenCode() {
		this.dao.updateLotterySsqConfig("gen_data");
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
