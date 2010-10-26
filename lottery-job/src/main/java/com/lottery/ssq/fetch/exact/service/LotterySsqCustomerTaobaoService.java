package com.lottery.ssq.fetch.exact.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lottery.ssq.config.LotterySsqConfig;
import com.lottery.ssq.dao.LotteryDao;
import com.lottery.ssq.utils.LotterySsqUtils;

/**
 * 拆分淘宝的双色球抓取。
 * 
 * @author ly.zy.ljh
 */
public class LotterySsqCustomerTaobaoService {
	private static final Logger logger = LoggerFactory.getLogger(LotterySsqCustomerTaobaoService.class);
	private LotteryDao dao;

	public void setDao(LotteryDao dao) {
		this.dao = dao;
	}

	@SuppressWarnings("unchecked")
	public void saveTaobaoProjectCode() {
		List<String[]> resultList = new ArrayList<String[]>();
		List<String[]> danList = new ArrayList<String[]>();
		List<String> dansList=new ArrayList<String>();
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
		List list = this.dao.getSsqLotteryCollectFetchLimit(0, 0, "10");
		Pattern p = Pattern.compile("[^\\x00-\\xff]");
		if (CollectionUtils.isNotEmpty(list)) {
			for (Iterator iterator = list.iterator(); iterator.hasNext();) {
				Map map = (Map) iterator.next();
				String id = (String) map.get("id");
				String fetchCode = ObjectUtils.toString(map.get("code"));
				// 胆拖
				String[] codes = StringUtils.split(fetchCode, "@@");
				for (String code : codes) {
					Matcher m = p.matcher(code);
					if (m.find()) {
						logger.error("内容有非法字符==" + code);
						continue;
					}
					// 胆拖
					String[] redCode = StringUtils.split(code, "+");
					String[] blueCode = null;
					if (redCode.length == 2) {
						blueCode = StringUtils.split(redCode[1], ",");
						for (String blue : blueCode) {
							if (blue.length() > 2 || !StringUtils.isNumeric(blue)) {
								continue;
							} else if (blue.length() == 1) {
								blue = "0" + blue;
							}
							if (!blueMap.containsKey(blue)) {
								continue;
							}
							Integer tmp = blueMap.get(blue) + 1;
							blueMap.put(blue, tmp);
						}
					}
					if (StringUtils.indexOf(redCode[0], "(") > -1 && StringUtils.indexOf(redCode[0], ")") > -1) {
						String dan = StringUtils.substringBetween(redCode[0], "(",")");
						String tuo = StringUtils.remove(redCode[0], "("+dan+")");
						tuo=StringUtils.replace(tuo,",,",",");
						String[] dans = dan.split(",");
						LotterySsqUtils.selectDanArray(6, dan, tuo, danList);
						for (String[] danCode : danList) {
							String[] rsDanCode = (StringUtils.join(dans, ",") + "," + StringUtils.join(danCode, ",")).split(",");
							if (rsDanCode.length != 6) {
								continue;
							}
							Arrays.sort(rsDanCode);
							resultList.add(rsDanCode);
						}
						dansList.add(dan);
						danList.clear();
					} else {
						String[] redCodes = StringUtils.split(redCode[0], ",");
						if (redCodes.length < 6 || redCodes.length > 20) {
							logger.error("方案解析失败=="+id+"==" + code);
							continue;
						}
						Arrays.sort(redCodes);
						if(redCode.length==6){
							resultList.add(redCodes);
						}else{
							LotterySsqUtils.selectArray(6, redCodes, resultList);
						}
					}
				}
				if (resultList.size() > 2000) {
					this.dao.saveSsqLotteryCollectRedCod(resultList);
					resultList.clear();
				}
			}
			if (CollectionUtils.isNotEmpty(resultList)) {
				this.dao.saveSsqLotteryCollectRedCod(resultList);
				resultList.clear();
			}
			this.dao.saveCollectBlueCodeResult(blueMap, LotterySsqConfig.expect);
			if (CollectionUtils.isNotEmpty(dansList)) {
				this.dao.batchSqqLotteryDanResult(dansList, "9");
			}
		}
	}
}
