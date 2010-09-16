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
import org.slf4j.LoggerFactory;

import com.lottery.ssq.Algorithm.LotterySsqAlgorithm;
import com.lottery.ssq.Algorithm.LotterySsqFirstFilterAlgorithm;
import com.lottery.ssq.config.LotterySsqConfig;
import com.lottery.ssq.config.LotterySsqFilterConfig;
import com.lottery.ssq.dao.LotteryDao;
import com.lottery.ssq.filter.LotterySsq500WanMediaFilterService;
import com.lottery.ssq.filter.LotterySsqConsumerFilterService;
import com.lottery.ssq.filter.LotterySsqHisRedCodeFilterService;
import com.lottery.ssq.filter.LotterySsqSinaMediaFilterService;
import com.lottery.ssq.filter.LotterySsqStantardFilterService;
import com.lottery.ssq.filter.LotterySsqWebMediaFilterService;

@SuppressWarnings("unchecked")
public class LotterySsqService {
	private static final org.slf4j.Logger logger = LoggerFactory.getLogger(LotterySsqService.class);
	private LotteryDao dao = null;
	private LotterySsqFileService lotterySsqFileService = null;
	private LotterySsqWebCollectService lotterySsqWebCollectService = null;
	private LotterySsqHisRedCodeFilterService lotterySsqHisRedCodeFilterService;
	private LotterySsqStantardFilterService lotterySsqStantardFilterService;
	private LotterySsqSinaMediaFilterService lotterySsqSinaMediaFilterService;
	private LotterySsqWebMediaFilterService lotterySsqWebMediaFilterService;
	private LotterySsq500WanMediaFilterService lotterySsq500WanMediaFilterService;
	private LotterySsqConsumerFilterService lotterySsqConsumerFilterService;

	public void setLotterySsq500WanMediaFilterService(
			LotterySsq500WanMediaFilterService lotterySsq500WanMediaFilterService) {
		this.lotterySsq500WanMediaFilterService = lotterySsq500WanMediaFilterService;
	}

	public void setLotterySsqConsumerFilterService(LotterySsqConsumerFilterService lotterySsqConsumerFilterService) {
		this.lotterySsqConsumerFilterService = lotterySsqConsumerFilterService;
	}

	public void setLotterySsqSinaMediaFilterService(LotterySsqSinaMediaFilterService lotterySsqSinaMediaFilterService) {
		this.lotterySsqSinaMediaFilterService = lotterySsqSinaMediaFilterService;
	}

	public void setLotterySsqWebMediaFilterService(LotterySsqWebMediaFilterService lotterySsqWebMediaFilterService) {
		this.lotterySsqWebMediaFilterService = lotterySsqWebMediaFilterService;
	}

	public void setLotterySsqStantardFilterService(LotterySsqStantardFilterService lotterySsqStantardFilterService) {
		this.lotterySsqStantardFilterService = lotterySsqStantardFilterService;
	}

	public void setLotterySsqHisRedCodeFilterService(LotterySsqHisRedCodeFilterService lotterySsqHisRedCodeFilterService) {
		this.lotterySsqHisRedCodeFilterService = lotterySsqHisRedCodeFilterService;
	}

	public void setLotterySsqWebCollectService(LotterySsqWebCollectService lotterySsqWebCollectService) {
		this.lotterySsqWebCollectService = lotterySsqWebCollectService;
	}

	public void setLotterySsqFileService(LotterySsqFileService lotterySsqFileService) {
		this.lotterySsqFileService = lotterySsqFileService;
	}

	public void setDao(LotteryDao dao) {
		this.dao = dao;
	}

	/**
	 * 从ssq_lottery_filter_result中过滤号码
	 * 
	 * @param filterConfig
	 */
	public void getCurrentExpertSingleResult(LotterySsqFilterConfig filterConfig, Set<String> filterRedcode) {
		/**
		 * SINA媒体数据
		 */
		List<String> sinaDanList = new ArrayList<String>();
		List<String[]> sinaRedCodeList = new ArrayList<String[]>();
		/* 新浪过滤列表数据 */
		this.initSinaFilterData(sinaDanList, sinaRedCodeList);
		/* 500万过滤数据 */
		Set<String[]> wan500RedCodeList = new HashSet<String[]>();
		this.init500WanFilterData(wan500RedCodeList);
		/**
		 * 各大网站抓取数据.
		 */
		List<String> webDanList = new ArrayList<String>();
		this.initWebFilterData(webDanList);
		/**
		 * 用户投注数据
		 */
		List<String> customerDanList = new ArrayList<String>();
		// 大于5个以上投注.
		List customerGtCount5RedList = new ArrayList();
		// 总投注数的前30注.
		List customerLeCount30RedList = new ArrayList();
		this.initFilterCustomerCode(filterConfig, customerDanList, customerGtCount5RedList, customerLeCount30RedList);
		// 指定媒体的胆号.
		Set<String> webRedCodeList = this.lotterySsqWebCollectService.getWebRedCoeByIds(filterConfig);

		List<String> redList = new ArrayList<String>();

		int last = 0;
		int page = 50000;
		logger.info("开始生成过滤号码了.............");
		List list = new ArrayList();
		boolean start1 = true;
		boolean isBreak = false;
		boolean isMap = true;
		while (start1 || CollectionUtils.isNotEmpty(list)) {
			start1 = false;
			if (CollectionUtils.isNotEmpty(filterRedcode)) {
				list.addAll(filterRedcode);
				isBreak = true;
				isMap = false;
			} else {
				list = this.dao.getSsqLottoryFilterResultLimit(last, page);
				last += page;
				logger.info("已经计算了" + last + "个号码了");
			}
			for (Iterator iterator = list.iterator(); iterator.hasNext();) {
				String[] lValues = null;
				if (isMap) {
					Map lValue = (Map) iterator.next();
					lValues = StringUtils.split(ObjectUtils.toString(lValue.get("value")), ",");
				} else {
					lValues = StringUtils.split((String) iterator.next(), ",");
				}
				if (!this.doFilter(filterConfig, lValues, sinaDanList, sinaRedCodeList, webDanList, webRedCodeList,
						wan500RedCodeList, customerDanList, customerGtCount5RedList, customerLeCount30RedList)) {
					continue;
				}
				redList.add(StringUtils.join(lValues, ","));
			}
			if (isBreak) {
				break;
			}
		}
		String f = System.currentTimeMillis() + "_filterResult_" + LotterySsqConfig.expect + "_" + redList.size()
				+ ".txt";
		String rsFileName = "D:/Apache2.2/htdocs/lottery_rs/txt/" + f;
		writeFile(redList, rsFileName, false);
		String lotteryHtml = "D:/Apache2.2/htdocs/lottery_rs/index_" + LotterySsqConfig.expect + ".htm";
		String tmp = "<li><a href='txt/" + f + "'>" + f + "</a></li>";
		writeFile(tmp, lotteryHtml, true);
	}

	private boolean doFilter(LotterySsqFilterConfig filterConfig, String[] lValues, List<String> sinaDanList,
			List<String[]> sinaRedCodeList, List<String> webDanList, Set<String> webRedCodeList,
			Set<String[]> wan500RedCodeList, List<String> customerDanList, List customerGtCount5RedList,
			List customerLeCount30RedList) {
		/*
		 * 标准过滤方法
		 */
		if (!this.lotterySsqStantardFilterService.stantardFilter(filterConfig, lValues)) {
			return false;
		}
		/*
		 * 新浪媒体过滤方法
		 */
		if (!lotterySsqSinaMediaFilterService.sinaFilterMethod(filterConfig, lValues, sinaDanList, sinaRedCodeList)) {
			return false;
		}

		/*
		 * 各大网站媒体过滤方法
		 */
		if (!this.lotterySsqWebMediaFilterService.webFilterRedCode(filterConfig, lValues, webRedCodeList, webDanList)) {
			return false;
		}
		/*
		 * 历史号码过滤方法
		 */
		if (!this.lotterySsqHisRedCodeFilterService.filterHistoryRedCode(lValues, filterConfig)) {
			return false;
		}
		/*
		 * 500万媒体过滤
		 */
		if (!this.lotterySsq500WanMediaFilterService.wan500FilterRedCode(filterConfig, lValues, wan500RedCodeList)) {
			return false;
		}
		/*
		 * 用户投注过滤方法
		 */
		if (!this.lotterySsqConsumerFilterService.customerFilterRedcode(filterConfig, lValues, customerDanList,
				customerGtCount5RedList, customerLeCount30RedList)) {
			return false;
		}
		/*
		 * 是否符合在区域的号码分布
		 */
		if (!this.lotterySsqStantardFilterService.stantardFilterQu(lValues, filterConfig)) {
			return false;
		}
		return true;
	}

	/**
	 * 各大网站的初始化过滤数据
	 * 
	 * @param webDanList
	 */
	private void initWebFilterData(List<String> webDanList) {
		List list = this.dao.getSsqLotteryDanResult("2");
		if (CollectionUtils.isNotEmpty(list)) {
			for (Iterator iterator = list.iterator(); iterator.hasNext();) {
				Map obj = (Map) iterator.next();
				webDanList.add(ObjectUtils.toString(obj.get("dan")));
			}
		}
	}

	/**
	 * 500万媒体投注过滤号码.
	 * 
	 * @param wan500RedCodeList
	 */
	private void init500WanFilterData(Set<String[]> wan500RedCodeList) {
		List wanList = this.dao.getSsqLotteryCollectFetchByType("3");
		if (CollectionUtils.isNotEmpty(wanList)) {
			Map map = (Map) wanList.get(0);
			String[] wan = ObjectUtils.toString(map.get("code")).split("@@");
			for (String code : wan) {
				wan500RedCodeList.add(StringUtils.split(code, "+")[0].split(","));
			}
		}
	}

	/**
	 * sina媒体投注过滤号码.
	 * 
	 * @param sinaDanList
	 * @param sinaRedCodeList
	 */
	private void initSinaFilterData(List<String> sinaDanList, List<String[]> sinaRedCodeList) {
		List sinaList = this.dao.getSsqLotteryCollectFetchByType("4");
		if (CollectionUtils.isNotEmpty(sinaList)) {
			Map map = (Map) sinaList.get(0);
			String[] sina = ObjectUtils.toString(map.get("code")).split("@@");
			for (String code : sina) {
				sinaRedCodeList.add(StringUtils.split(code, "+")[0].split(","));
			}
		}
		List list = this.dao.getSsqLotteryDanResult("0");
		if (CollectionUtils.isNotEmpty(list)) {
			for (Iterator iterator = list.iterator(); iterator.hasNext();) {
				Map obj = (Map) iterator.next();
				sinaDanList.add(ObjectUtils.toString(obj.get("dan")));
			}
		}
	}

	/**
	 * 从ssq_lottery_filter_result中过滤号码
	 * 
	 * @param filterConfig
	 */
	public void getCurrentExpertSingleResult(LotterySsqFilterConfig filterConfig) {
		this.getCurrentExpertSingleResult(filterConfig, null);
	}

	/**
	 * 用户投注号码
	 * 
	 * @param filterConfig
	 * @param customerLeCount30RedList
	 * @param customerGtCount5RedList
	 * @param customerDanList
	 */
	private void initFilterCustomerCode(LotterySsqFilterConfig filterConfig, List<String> customerDanList,
			List customerGtCount5RedList, List customerLeCount30RedList) {
		List tmpList = null;
		if (filterConfig.getCustomerGtCount5RedList() >= 1) {
			tmpList = this.dao.getSsqLotteryCollectResultCountLessThan5();
			customerGtCount5RedList.addAll(tmpList);
		}
		tmpList = this.dao.getSsqLotteryCollectResult30(0, 30);
		if (CollectionUtils.isNotEmpty(tmpList)) {
			customerLeCount30RedList.addAll(tmpList);
		}
		List list = this.dao.getSsqLotteryDanResult("1");
		if (CollectionUtils.isNotEmpty(list)) {
			for (Iterator iterator = list.iterator(); iterator.hasNext();) {
				Map obj = (Map) iterator.next();
				customerDanList.add(ObjectUtils.toString(obj.get("dan")));
			}
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
				String redCode = ObjectUtils.toString(map.get("first")) + "," + ObjectUtils.toString(map.get("second"))
						+ "," + ObjectUtils.toString(map.get("third")) + "," + ObjectUtils.toString(map.get("fourth"))
						+ "," + ObjectUtils.toString(map.get("firth")) + "," + ObjectUtils.toString(map.get("sixth"));
				redCodeList.add(redCode);
			}
			this.dao.batchDelSsqLotteryFilterResult(redCodeList);
			redCodeList.clear();
		}
	}

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
			if (!LotterySsqFirstFilterAlgorithm.isFilterRedIncludeSideCode(lValues, LotterySsqConfig.preSideCode)
					&& !LotterySsqFirstFilterAlgorithm.isFilterIncludePreCode(lValues, LotterySsqConfig.preRedCode)) {
				redList.add(lValue);
			}

			if (redList.size() > 2000) {
				this.dao.deleteSsqLotteryFilterResult(redList);
				redList.clear();
			}
		}
		if (CollectionUtils.isNotEmpty(redList)) {
			this.dao.deleteSsqLotteryFilterResult(redList);
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
	 * @param filterConfig
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
	public void genFilterRedCodeFromCollectResult(LotterySsqFilterConfig filterConfig) {
		genFilterRedCodeFromCollectResult(filterConfig, null);

	}

	// ~~~~~~~~~~~~~~~~~~~~~从抓取号码中生产过滤号码~~~~~~~~~~~~~~~~~
	/**
	 * 使用从文件中读取
	 * 
	 * @param filterConfig
	 * @param codes
	 */
	public void genFilterRedCodeFromCollectResult(LotterySsqFilterConfig filterConfig, Set<String> codes) {
		/**
		 * SINA媒体数据
		 */
		List<String> sinaDanList = new ArrayList<String>();
		List<String[]> sinaRedCodeList = new ArrayList<String[]>();
		/**
		 * 用户投注数据
		 */
		List<String> customerDanList = new ArrayList<String>();
		// 大于5个以上投注.
		List customerGtCount5RedList = new ArrayList();
		// 总投注数的前30注.
		List customerLeCount30RedList = new ArrayList();
		/* 500万过滤数据 */
		Set<String[]> wan500RedCodeList = new HashSet<String[]>();
		/**
		 * 各大网站抓取数据.
		 */
		List<String> webDanList = new ArrayList<String>();
		/* 新浪过滤列表数据 */
		this.initSinaFilterData(sinaDanList, sinaRedCodeList);

		this.init500WanFilterData(wan500RedCodeList);

		this.initWebFilterData(webDanList);

		this.initFilterCustomerCode(filterConfig, customerDanList, customerGtCount5RedList, customerLeCount30RedList);

		List<String> redList = new ArrayList<String>();
		Set<String> webRedCodeList = this.lotterySsqWebCollectService.getWebRedCoeByIds(filterConfig);
		logger.info("开始从抓取号码中生产过滤号码.............");
		List<String> list = new ArrayList<String>();
		int last = 0;
		int page = 50000;
		boolean isStart = true;
		boolean isBreak = false;
		while (isStart || CollectionUtils.isNotEmpty(list)) {
			isStart = false;
			if (CollectionUtils.isNotEmpty(codes)) {
				list.addAll(codes);
				isBreak = true;
			} else {
				list = this.dao.getSsqLotteryCollectResultCountLe2(last, page);
				last += page;
				logger.info("已经计算从抓取号码中生产过滤号码" + last + "个号码了");
			}
			for (Iterator iterator = list.iterator(); iterator.hasNext();) {
				String[] lValues = null;
				if (codes == null) {
					Map lValue = (Map) iterator.next();
					lValues = StringUtils.split(ObjectUtils.toString(lValue.get("value")), ",");
				} else {
					lValues = StringUtils.split((String) iterator.next(), ",");
				}
				if (lValues == null || lValues.length != 6) {
					continue;
				}
				/*
				 * 基本过滤要求
				 */
				if (StringUtils.isBlank(LotterySsqAlgorithm.filterFromCollectRedCode(StringUtils.join(lValues, ",")))) {
					continue;
				}
				if (!this.doFilter(filterConfig, lValues, sinaDanList, sinaRedCodeList, webDanList, webRedCodeList,
						wan500RedCodeList, customerDanList, customerGtCount5RedList, customerLeCount30RedList)) {
					continue;
				}
				redList.add(StringUtils.join(lValues, ","));
			}
			if (isBreak) {
				break;
			}
		}
		String f = System.currentTimeMillis() + "_collectResult_" + LotterySsqConfig.expect + "_" + redList.size()
				+ ".txt";
		String rsFileName = "D:/Apache2.2/htdocs/lottery_rs/txt/" + f;
		writeFile(redList, rsFileName, false);
		String lotteryHtml = "D:/Apache2.2/htdocs/lottery_rs/index_" + LotterySsqConfig.expect + ".htm";
		String tmp = "<li><a href='txt/" + f + "'>" + f + "</a></li>";
		writeFile(tmp, lotteryHtml, true);

	}

	public void finishFilterRedCodeFromCollectResult() {
		this.dao.updateLotterySsqFilterConfig("1", "genFilterRedCodeFromCollectResult");

	}
}
