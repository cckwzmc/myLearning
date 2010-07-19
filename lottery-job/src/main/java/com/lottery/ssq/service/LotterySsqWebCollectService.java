package com.lottery.ssq.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lottery.ssq.config.LotterySsqConfig;
import com.lottery.ssq.dao.LotteryDao;
import com.lottery.ssq.fetch.dao.LotteryFetchDao;
import com.lottery.ssq.utils.LotterySsqUtils;
public class LotterySsqWebCollectService {
	private static final Logger logger = LoggerFactory.getLogger(LotterySsqWebCollectService.class);
	private LotteryFetchDao fetchDao;
	private LotteryDao lotteryDao;
	private LotterySsqThan20Service lotterySsqThan20Service;
	
	public void setFetchDao(LotteryFetchDao fetchDao) {
		this.fetchDao = fetchDao;
	}

	public void setLotteryDao(LotteryDao lotteryDao) {
		this.lotteryDao = lotteryDao;
	}

	public void setLotterySsqThan20Service(LotterySsqThan20Service lotterySsqThan20Service) {
		this.lotterySsqThan20Service = lotterySsqThan20Service;
	}

	@SuppressWarnings("unchecked")
	public void saveSsqWebCollect(){
		List<String[]> resultList = new ArrayList<String[]>();
		List list=this.fetchDao.getWebCollectList(LotterySsqConfig.expect);
		Map<String, Integer> blueMap = new HashMap<String, Integer>();
		blueMap.put("01", 0);
		blueMap.put("02", 0);
		blueMap.put("03", 0);
		blueMap.put("04", 0);
		blueMap.put("05", 0);
		blueMap.put("06", 0);
		blueMap.put("07", 0);
		blueMap.put("08", 0);
		blueMap.put("09", 0);
		blueMap.put("10", 0);
		blueMap.put("11", 0);
		blueMap.put("12", 0);
		blueMap.put("13", 0);
		blueMap.put("14", 0);
		blueMap.put("15", 0);
		blueMap.put("16", 0);
		if(CollectionUtils.isNotEmpty(list)){
			for(int i=0;i<list.size();i++){
				Map map=(Map) list.get(i);
				String code = ObjectUtils.toString(map.get("content"));
				String[] codes = StringUtils.split(code, "@@");
				for (String ssq : codes) {
					String[] redCode = StringUtils.split(ssq, "+");
					String[] blueCode = null;
					if (redCode.length == 2) {
						blueCode = StringUtils.split(redCode[1], ",");
						for (String blue : blueCode) {
							if (blue.length() > 2||!StringUtils.isNumeric(blue)) {
								continue;
							} else if (blue.length() == 1) {
								blue = "0" + blue;
							}
							if(!blueMap.containsKey(blue)){
								continue;
							}
							Integer tmp = blueMap.get(blue) + 1;
							blueMap.put(blue, tmp);
						}
					}
					String[] redCodes = StringUtils.split(redCode[0], ",");
					if (redCodes.length < 6 ) {
						logger.error("方案解析失败==" + ssq);
						continue;
					}
					if (redCodes.length > 20) {
						lotterySsqThan20Service.select(6, redCodes, false);
						continue;
					}
					LotterySsqUtils.selectArray(6, redCodes, resultList);
					if (resultList.size() > 2000) {
						this.lotteryDao.saveSsqLotteryCollectRedCod(resultList);
						resultList.clear();
					}
				}
			}
			if (CollectionUtils.isNotEmpty(resultList)) {
				this.lotteryDao.saveSsqLotteryCollectRedCod(resultList);
				resultList.clear();
			}
			this.lotteryDao.saveCollectBlueCodeResult(blueMap, LotterySsqConfig.expect);
		}
	}
}
