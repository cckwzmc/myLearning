package com.lottery.ssq.service.self;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lottery.ssq.config.LotterySsqConfig;
import com.lottery.ssq.config.LotterySsqFilterConfig;
import com.lottery.ssq.dao.LotteryDao;
import com.lottery.ssq.fetch.dao.LotteryFetchDao;
import com.lottery.ssq.filter.LotterySsq500WanMediaFilterService;
import com.lottery.ssq.filter.LotterySsqHisRedCodeFilterService;
import com.lottery.ssq.filter.LotterySsqSinaMediaFilterService;
import com.lottery.ssq.filter.LotterySsqStantardFilterService;
import com.lottery.ssq.filter.LotterySsqWebMediaFilterService;
import com.lottery.ssq.service.LotterySsqConifgService;
import com.lottery.ssq.service.LotterySsqCustomer500WanService;
import com.lottery.ssq.service.LotterySsqWebCollectService;

/**
 * 自定义的过滤方式.
 * 
 * @author ly.zy.ljh
 */
public class LotterySsqSelfService {
	private static final Logger logger = LoggerFactory.getLogger(LotterySsqCustomer500WanService.class);
	private LotteryDao dao = null;
	private LotteryFetchDao fetchDao=null;
	private LotterySsqWebCollectService lotterySsqWebCollectService = null;
	private LotterySsqHisRedCodeFilterService lotterySsqHisRedCodeFilterService;
	private LotterySsqStantardFilterService lotterySsqStantardFilterService;
	private LotterySsqSinaMediaFilterService lotterySsqSinaMediaFilterService;
	private LotterySsqWebMediaFilterService lotterySsqWebMediaFilterService;
	private LotterySsq500WanMediaFilterService lotterySsq500WanMediaFilterService;
	private LotterySsqConifgService lotterySsqConifgService = null;
	public void setDao(LotteryDao dao) {
		this.dao = dao;
	}

	public void setFetchDao(LotteryFetchDao fetchDao) {
		this.fetchDao = fetchDao;
	}

	public void setLotterySsqConifgService(LotterySsqConifgService lotterySsqConifgService) {
		this.lotterySsqConifgService = lotterySsqConifgService;
	}

	public void setLotterySsqHisRedCodeFilterService(LotterySsqHisRedCodeFilterService lotterySsqHisRedCodeFilterService) {
		this.lotterySsqHisRedCodeFilterService = lotterySsqHisRedCodeFilterService;
	}

	public void setLotterySsqStantardFilterService(LotterySsqStantardFilterService lotterySsqStantardFilterService) {
		this.lotterySsqStantardFilterService = lotterySsqStantardFilterService;
	}

	public void setLotterySsqSinaMediaFilterService(LotterySsqSinaMediaFilterService lotterySsqSinaMediaFilterService) {
		this.lotterySsqSinaMediaFilterService = lotterySsqSinaMediaFilterService;
	}

	public void setLotterySsqWebMediaFilterService(LotterySsqWebMediaFilterService lotterySsqWebMediaFilterService) {
		this.lotterySsqWebMediaFilterService = lotterySsqWebMediaFilterService;
	}

	public void setLotterySsq500WanMediaFilterService(LotterySsq500WanMediaFilterService lotterySsq500WanMediaFilterService) {
		this.lotterySsq500WanMediaFilterService = lotterySsq500WanMediaFilterService;
	}

	public void setLotterySsqWebCollectService(LotterySsqWebCollectService lotterySsqWebCollectService) {
		this.lotterySsqWebCollectService = lotterySsqWebCollectService;
	}

	@SuppressWarnings("unchecked")
	public void filterLastResult() {
		LotterySsqFilterConfig filterConfig=this.lotterySsqConifgService.initFilterConfig(this.fetchDao.getLotterySsqFilterConfig(1));
		if(filterConfig==null){
			return ;
		}
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
		Set<String> webRedCodeList = this.lotterySsqWebCollectService.getWebRedCoeByIds(filterConfig);

		List<String> redList = new ArrayList<String>();

		int last = 0;
		int page = 50000;
		logger.info("自定义过滤开始了.............");
		List list = new ArrayList();
		boolean start1 = true;
		boolean isBreak = false;
		boolean isMap = true;
		while (start1 || CollectionUtils.isNotEmpty(list)) {
			start1=false;
			list = this.dao.getSsqLottoryFilterLastResultLimit(last, page);
//			Map map=new HashMap();
//			map.put("value", "05,09,10,20,22,26");
//			list.add(map);
//			isBreak=true;
			last += page;
			logger.info("自定义过滤已经计算了" + last + "个号码了");
			for (Iterator iterator = list.iterator(); iterator.hasNext();) {
				String[] lValues = null;
				if (isMap) {
					Map lValue = (Map) iterator.next();
					lValues = StringUtils.split(ObjectUtils.toString(lValue.get("value")), ",");
				} else {
					lValues = StringUtils.split((String) iterator.next(), ",");
				}
				
				if (!this.doFilter(filterConfig, lValues, sinaDanList, sinaRedCodeList, webDanList, webRedCodeList, wan500RedCodeList, null, null,null)) {
					continue;
				}
				redList.add(StringUtils.join(lValues, ","));
			}
			if (isBreak) {
				break;
			}
		}
		String f = System.currentTimeMillis() + "_self_" + LotterySsqConfig.expect + "_" + redList.size() + ".txt";
		String rsFileName = "D:/Apache2.2/htdocs/lottery_rs/txt/" + f;
		writeFile(redList, rsFileName, false);
		String lotteryHtml = "D:/Apache2.2/htdocs/lottery_rs/index_" + LotterySsqConfig.expect + ".htm";
		String tmp = "<li><a href='txt/" + f + "'>" + f + "</a></li>";
		writeFile(tmp, lotteryHtml, true);
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
	 * sina媒体投注过滤号码.
	 * 
	 * @param sinaDanList
	 * @param sinaRedCodeList
	 */
	@SuppressWarnings("unchecked")
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
	 * 500万媒体投注过滤号码.
	 * 
	 * @param wan500RedCodeList
	 */
	@SuppressWarnings("unchecked")
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
	 * 各大网站的初始化过滤数据
	 * 
	 * @param webDanList
	 */
	@SuppressWarnings("unchecked")
	private void initWebFilterData(List<String> webDanList) {
		List list = this.dao.getSsqLotteryDanResult("2");
		List list9 = this.dao.getSsqLotteryDanResult("9");
		if (CollectionUtils.isNotEmpty(list)) {
			for (Iterator iterator = list.iterator(); iterator.hasNext();) {
				Map obj = (Map) iterator.next();
				webDanList.add(ObjectUtils.toString(obj.get("dan")));
			}
		}
		if(CollectionUtils.isNotEmpty(list9)){
			for(int i=0;i<list9.size();i++){
				String value=ObjectUtils.toString(list9.get(i));
				if(value.length()>7){
					webDanList.add(value);
				}
			}
		}
		
	}


	@SuppressWarnings("unchecked")
	private boolean doFilter(LotterySsqFilterConfig filterConfig, String[] lValues, List<String> sinaDanList, List<String[]> sinaRedCodeList, List<String> webDanList, Set<String> webRedCodeList, Set<String[]> wan500RedCodeList, List<String> customerDanList,
			List customerGtCount5RedList, List customerLeCount30RedList) {
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
		 * 是否符合在区域的号码分布
		 */
		if (!this.lotterySsqStantardFilterService.stantardFilterQu(lValues, filterConfig)) {
			return false;
		}
		return true;
	}
}
