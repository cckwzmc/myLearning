package com.lottery.ssq.service;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;

import com.lottery.ssq.config.LotterySsqConfig;
import com.lottery.ssq.config.LotterySsqFetchConfig;
import com.lottery.ssq.config.LotterySsqFilterConfig;
import com.lottery.ssq.fetch.dao.LotteryFetchDao;

/**
 * 媒体推荐的处理
 * 
 * @author Administrator
 */
public class LotterySsqConifgService {
	private LotteryFetchDao fetchDao = null;

	public void setFetchDao(LotteryFetchDao fetchDao) {
		this.fetchDao = fetchDao;
	}

	@SuppressWarnings("unchecked")
	public boolean initSsqConfig() {
		List ssqList = this.fetchDao.getLotterySsqExpectConfig(0, 0);
		if (CollectionUtils.isEmpty(ssqList)) {
			return false;
		}
		Map map = (Map) ssqList.get(0);
		String expect = ObjectUtils.toString(map.get("expect"));

		if (this.fetchDao.isGenLotteryResult("0", expect)) {
			return false;
		}
		LotterySsqConfig.expect = expect;
		LotterySsqConfig.preRedCode = StringUtils.split(ObjectUtils.toString(map.get("preRedCode")), ",");
		if (ArrayUtils.isNotEmpty(LotterySsqConfig.preRedCode)) {
			String temp = "";
			for (int i = 0; i < LotterySsqConfig.preRedCode.length; i++) {
				int tmpInt = NumberUtils.toInt(LotterySsqConfig.preRedCode[i]);
				if ("".equals(temp)) {
					temp = tmpInt == 1 ? "02"
							: (((tmpInt - 1) < 10 ? "0" + (tmpInt - 1) : (tmpInt - 1)) + "," + ((tmpInt + 1) < 10 ? "0"
									+ (tmpInt + 1) : (tmpInt + 1)));
				} else {
					String tmpSideCode = tmpInt == 1 ? "02" : (((tmpInt - 1) < 10 ? "0" + (tmpInt - 1) : (tmpInt - 1))
							+ "," + ((tmpInt + 1) < 10 ? "0" + (tmpInt + 1) : (tmpInt + 1)));
					if (temp.indexOf(tmpSideCode) == -1) {
						temp += "," + tmpSideCode;
					}
				}
			}
			LotterySsqConfig.preSideCode = StringUtils.split(temp, ",");
		}
		return true;
	}

	@SuppressWarnings("unchecked")
	public boolean initFetchConfig() {

		List list = this.fetchDao.getLotterySsqFetchConfig();
		for (Iterator iterator = list.iterator(); iterator.hasNext();) {
			Map fMap = (Map) iterator.next();
			String cfgName = (String) fMap.get("config_name");
			String cfgValue = (String) fMap.get("config_value");
			if (StringUtils.equals(cfgName, "firstMinCode")) {
				LotterySsqFetchConfig.firstMinCode = NumberUtils.toInt(cfgValue);
			}
			if (StringUtils.equals(cfgName, "firstMaxCode")) {
				LotterySsqFetchConfig.firstMaxCode = NumberUtils.toInt(cfgValue);
			}
			if (StringUtils.equals(cfgName, "lastMinCode")) {
				LotterySsqFetchConfig.lastMinCode = NumberUtils.toInt(cfgValue);
			}
			if (StringUtils.equals(cfgName, "lastMaxCode")) {
				LotterySsqFetchConfig.lastMaxCode = NumberUtils.toInt(cfgValue);
			}
			if (StringUtils.equals(cfgName, "secondMinCode")) {
				LotterySsqFetchConfig.secondMinCode = NumberUtils.toInt(cfgValue);
			}
			if (StringUtils.equals(cfgName, "secondMaxCode")) {
				LotterySsqFetchConfig.secondMaxCode = NumberUtils.toInt(cfgValue);
			}
			if (StringUtils.equals(cfgName, "thirdMinCode")) {
				LotterySsqFetchConfig.thirdMinCode = NumberUtils.toInt(cfgValue);
			}
			if (StringUtils.equals(cfgName, "thirdMaxCode")) {
				LotterySsqFetchConfig.thirdMaxCode = NumberUtils.toInt(cfgValue);
			}
			if (StringUtils.equals(cfgName, "fourthMinCode")) {
				LotterySsqFetchConfig.fourthMinCode = NumberUtils.toInt(cfgValue);
			}
			if (StringUtils.equals(cfgName, "fourthMaxCode")) {
				LotterySsqFetchConfig.fourthMaxCode = NumberUtils.toInt(cfgValue);
			}
			if (StringUtils.equals(cfgName, "media500WanUrl")) {
				LotterySsqFetchConfig.media500WanUrl = cfgValue;
				LotterySsqFetchConfig.media500WanUrl = StringUtils.replace(LotterySsqFetchConfig.media500WanUrl,
						"@expect@", LotterySsqConfig.expect);
			}
			if (StringUtils.equals(cfgName, "mediaSinaUrl")) {
				LotterySsqFetchConfig.mediaSinaUrl = cfgValue;
				LotterySsqFetchConfig.mediaSinaUrl = StringUtils.replace(LotterySsqFetchConfig.mediaSinaUrl,
						"@expect@", LotterySsqConfig.expect);
			}
			if (StringUtils.equals(cfgName, "dyjUrl")) {
				LotterySsqFetchConfig.dyjUrl = cfgValue;
				LotterySsqFetchConfig.dyjUrl = StringUtils.replace(LotterySsqFetchConfig.dyjUrl, "@expect@",
						LotterySsqConfig.expect);
			}
			if (StringUtils.equals(cfgName, "dyjDowload")) {
				LotterySsqFetchConfig.dyjDowload = cfgValue;
				LotterySsqFetchConfig.dyjDowload = StringUtils.replace(LotterySsqFetchConfig.dyjDowload, "@expect@",
						LotterySsqConfig.expect);
			}
			if (StringUtils.equals(cfgName, "www500wanUrl")) {
				LotterySsqFetchConfig.www500wanUrl = cfgValue;
				LotterySsqFetchConfig.www500wanUrl = StringUtils.replace(LotterySsqFetchConfig.www500wanUrl,
						"@expect@", LotterySsqConfig.expect);
			}
			if (StringUtils.equals(cfgName, "www500wanDowload")) {
				LotterySsqFetchConfig.www500wanDowload = cfgValue;
				LotterySsqFetchConfig.www500wanDowload = StringUtils.replace(LotterySsqFetchConfig.www500wanDowload,
						"@expect@", LotterySsqConfig.expect);
			}
			if (StringUtils.equals(cfgName, "caipiaoUrl")) {
				LotterySsqFetchConfig.caipiaoUrl = cfgValue;
				LotterySsqFetchConfig.caipiaoUrl = StringUtils.replace(LotterySsqFetchConfig.caipiaoUrl, "@expect@",
						LotterySsqConfig.expect);
			}
			if (StringUtils.equals(cfgName, "caipiaoDowload")) {
				LotterySsqFetchConfig.caipiaoDowload = cfgValue;
				LotterySsqFetchConfig.caipiaoDowload = StringUtils.replace(LotterySsqFetchConfig.caipiaoDowload,
						"@expect@", LotterySsqConfig.expect);
			}
			if (StringUtils.equals(cfgName, "buywanUrl")) {
				LotterySsqFetchConfig.buywanUrl = cfgValue;
				// LotterySsqFetchConfig. = StringUtils.replace(LotterySsqFetchConfig.caipiaoDowload, "@expect@",
				// LotterySsqConfig.expect);
			}
		}
		return true;
	}

	@SuppressWarnings("unchecked")
	public boolean initFilterConfig() {
		if (StringUtils.isBlank(LotterySsqConfig.expect)) {
			return false;
		}
		List list = this.fetchDao.getLotterySsqFilterConfig();
		if (CollectionUtils.isEmpty(list)) {
			return false;
		}
		for (Iterator iterator = list.iterator(); iterator.hasNext();) {
			Map iMap = (Map) iterator.next();
			String cfgName = (String) iMap.get("config_name");
			String cfgValue = (String) iMap.get("config_value");
			if (StringUtils.equals(cfgName, "firstMinCode")) {
				LotterySsqFilterConfig.firstMinCode = NumberUtils.toInt(cfgValue);
			}
			if (StringUtils.equals(cfgName, "firstMaxCode")) {
				LotterySsqFilterConfig.firstMaxCode = NumberUtils.toInt(cfgValue);
			}
			if (StringUtils.equals(cfgName, "lastMinCode")) {
				LotterySsqFilterConfig.lastMinCode = NumberUtils.toInt(cfgValue);
			}
			if (StringUtils.equals(cfgName, "lastMaxCode")) {
				LotterySsqFilterConfig.lastMaxCode = NumberUtils.toInt(cfgValue);
			}
			if (StringUtils.equals(cfgName, "secondMinCode")) {
				LotterySsqFilterConfig.secondMinCode = NumberUtils.toInt(cfgValue);
			}
			if (StringUtils.equals(cfgName, "secondMaxCode")) {
				LotterySsqFilterConfig.secondMaxCode = NumberUtils.toInt(cfgValue);
			}
			if (StringUtils.equals(cfgName, "thirdMinCode")) {
				LotterySsqFilterConfig.thirdMinCode = NumberUtils.toInt(cfgValue);
			}
			if (StringUtils.equals(cfgName, "thirdMaxCode")) {
				LotterySsqFilterConfig.thirdMaxCode = NumberUtils.toInt(cfgValue);
			}
			if (StringUtils.equals(cfgName, "fourthMinCode")) {
				LotterySsqFilterConfig.fourthMinCode = NumberUtils.toInt(cfgValue);
			}
			if (StringUtils.equals(cfgName, "fourthMaxCode")) {
				LotterySsqFilterConfig.fourthMaxCode = NumberUtils.toInt(cfgValue);
			}
			if (StringUtils.equals(cfgName, "fourthMaxCode")) {
				LotterySsqFilterConfig.fourthMaxCode = NumberUtils.toInt(cfgValue);
			}
			if (StringUtils.equals(cfgName, "quOne")) {
				LotterySsqFilterConfig.quOne = NumberUtils.toInt(cfgValue);
			}
			if (StringUtils.equals(cfgName, "quTwo")) {
				LotterySsqFilterConfig.quTwo = NumberUtils.toInt(cfgValue);
			}
			if (StringUtils.equals(cfgName, "quThree")) {
				LotterySsqFilterConfig.quThree = NumberUtils.toInt(cfgValue);
			}
			if (StringUtils.equals(cfgName, "quThree")) {
				LotterySsqFilterConfig.quThree = NumberUtils.toInt(cfgValue);
			}
			if (StringUtils.equals(cfgName, "quThree")) {
				LotterySsqFilterConfig.quThree = NumberUtils.toInt(cfgValue);
			}
			if (StringUtils.equals(cfgName, "excludeRed")) {
				// 不包含
				LotterySsqFilterConfig.excludeRed = StringUtils.isNotBlank(cfgValue) ? cfgValue.split(",") : null;
			}
			if (StringUtils.equals(cfgName, "musthavered")) {
				// 必须有的
				LotterySsqFilterConfig.musthavered = StringUtils.isNotBlank(cfgValue) ? cfgValue.split(",") : null;
			}
			if (StringUtils.equals(cfgName, "ishaveexclude")) {
				// 是否从 文件中读取排除号码
				LotterySsqFilterConfig.ishaveexclude = NumberUtils.toInt(cfgValue);
			}
			if (StringUtils.equals(cfgName, "haveTwoSeries")) {
				// 有几个两连号
				LotterySsqFilterConfig.haveTwoSeries = NumberUtils.toInt(cfgValue);
			}
			if (StringUtils.equals(cfgName, "haveThreeSeries")) {
				// 有几个三连号
				LotterySsqFilterConfig.haveThreeSeries = NumberUtils.toInt(cfgValue);
			}
			if (StringUtils.equals(cfgName, "haveOnediffer")) {
				// 有几个差值为1的，，如1，3，5 算两个
				LotterySsqFilterConfig.haveOnediffer = NumberUtils.toInt(cfgValue);
			}
			if (StringUtils.equals(cfgName, "selectCode")) {
				// 在这些号码中选择6个
				LotterySsqFilterConfig.selectCode = StringUtils.isNotBlank(cfgValue) ? cfgValue.split(",") : null;
			}
			if (StringUtils.equals(cfgName, "cannotSelectedTogethor")) {
				// 不能同时出现的号码
				LotterySsqFilterConfig.cannotSelectedTogethor = StringUtils.isNotBlank(cfgValue) ? StringUtils.split(
						cfgValue, "|") : null;
			}
			if (StringUtils.equals(cfgName, "haveSideCode")) {
				// 是否有边号
				LotterySsqFilterConfig.haveSideCode = NumberUtils.toInt(cfgValue);
			}
			// 最多中其中一个
			if (StringUtils.equals(cfgName, "zuiduoSelectedOneCode")) {
				LotterySsqFilterConfig.zuiduoSelectedOneCode = StringUtils.isNotBlank(cfgValue) ? StringUtils.split(
						cfgValue, "|") : null;
			}
			// 至少选择一个
			if (StringUtils.equals(cfgName, "leastSelectedOneCode")) {
				LotterySsqFilterConfig.leastSelectedOneCode = StringUtils.isNotBlank(cfgValue) ? StringUtils.split(
						cfgValue, "|") : null;
			}
			// 必须选择其中的一个
			if (StringUtils.equals(cfgName, "mustSelectedOneCode")) {
				LotterySsqFilterConfig.mustSelectedOneCode = StringUtils.isNotBlank(cfgValue) ? StringUtils.split(
						cfgValue, "|") : null;
			}
			// 必须选其中的两个
			if (StringUtils.equals(cfgName, "mustSelectedTwoCode")) {
				LotterySsqFilterConfig.mustSelectedTwoCode = StringUtils.isNotBlank(cfgValue) ? StringUtils.split(
						cfgValue, "|") : null;
			}
			// 至少选其中的两个
			if (StringUtils.equals(cfgName, "leastSelectedTwoCode")) {
				LotterySsqFilterConfig.leastSelectedTwoCode = StringUtils.isNotBlank(cfgValue) ? StringUtils.split(
						cfgValue, "|") : null;
			}
			// 至少选其中的三个
			if (StringUtils.equals(cfgName, "leastSelectedThreeCode")) {
				LotterySsqFilterConfig.leastSelectedThreeCode = StringUtils.isNotBlank(cfgValue) ? StringUtils.split(
						cfgValue, "|") : null;
			}
		}
		return true;
	}

	/**
	 * 过滤中各种条件的配置。 如：可以种胆中的几个号码等条件。
	 * 
	 * @return
	 */
	public boolean getLotterySsqFilterCondictionConfig() {
		return true;
	}
}
