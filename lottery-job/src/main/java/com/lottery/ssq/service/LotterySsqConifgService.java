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
		// List ssqList = this.fetchDao.getLotterySsqExpectConfig(0, 0, "10076");
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
	public LotterySsqFilterConfig initFilterConfig() {
		if (StringUtils.isBlank(LotterySsqConfig.expect)) {
			return null;
		}
		List list = this.fetchDao.getLotterySsqFilterConfig();
		LotterySsqFilterConfig config=new LotterySsqFilterConfig();
		for (Iterator iterator = list.iterator(); iterator.hasNext();) {
			Map iMap = (Map) iterator.next();
			String cfgName = (String) iMap.get("config_name");
			String cfgValue = (String) iMap.get("config_value");
			if (StringUtils.equals(cfgName, "firstMinCode")) {
				config.setFirstMinCode(NumberUtils.toInt(cfgValue));
			}
			if (StringUtils.equals(cfgName, "firstMaxCode")) {
				config.setFirstMaxCode(NumberUtils.toInt(cfgValue));
			}
			if (StringUtils.equals(cfgName, "lastMinCode")) {
				config.setLastMinCode(NumberUtils.toInt(cfgValue));
			}
			if (StringUtils.equals(cfgName, "lastMaxCode")) {
				config.setLastMaxCode(NumberUtils.toInt(cfgValue));
			}
			if (StringUtils.equals(cfgName, "secondMinCode")) {
				config.setSecondMinCode(NumberUtils.toInt(cfgValue));
			}
			if (StringUtils.equals(cfgName, "secondMaxCode")) {
				config.setSecondMaxCode(NumberUtils.toInt(cfgValue));
			}
			if (StringUtils.equals(cfgName, "thirdMinCode")) {
				config.setThirdMinCode(NumberUtils.toInt(cfgValue));
			}
			if (StringUtils.equals(cfgName, "thirdMaxCode")) {
				config.setThirdMaxCode(NumberUtils.toInt(cfgValue));
			}
			if (StringUtils.equals(cfgName, "fourthMinCode")) {
				config.setFourthMinCode(NumberUtils.toInt(cfgValue));
			}
			if (StringUtils.equals(cfgName, "fourthMaxCode")) {
				config.setFourthMaxCode(NumberUtils.toInt(cfgValue));
			}
			if (StringUtils.equals(cfgName, "quOne")) {
				config.setQuOne(NumberUtils.toInt(cfgValue));
			}
			if (StringUtils.equals(cfgName, "quTwo")) {
				config.setQuTwo(NumberUtils.toInt(cfgValue));
			}
			if (StringUtils.equals(cfgName, "quThree")) {
				config.setQuThree(NumberUtils.toInt(cfgValue));
			}
			if (StringUtils.equals(cfgName, "excludeRed")) {
				// 不包含
				config.setExcludeRed(StringUtils.isNotBlank(cfgValue) ? cfgValue.split(",") : null);
			}
			if (StringUtils.equals(cfgName, "musthavered")) {
				// 必须有的
				config.setMusthavered(StringUtils.isNotBlank(cfgValue) ? cfgValue.split(",") : null);
			}
			if (StringUtils.equals(cfgName, "ishaveexclude")) {
				// 是否从 文件中读取排除号码
				config.setIshaveexclude(NumberUtils.toInt(cfgValue));
			}
			if (StringUtils.equals(cfgName, "haveTwoSeries")) {
				// 有几个两连号
				config.setHaveTwoSeries(NumberUtils.toInt(cfgValue));
			}
			if (StringUtils.equals(cfgName, "haveThreeSeries")) {
				// 有几个三连号
				config.setHaveThreeSeries(NumberUtils.toInt(cfgValue));
			}
			if (StringUtils.equals(cfgName, "haveOnediffer")) {
				// 有几个差值为1的，，如1，3，5 算两个
				config.setHaveOnediffer(NumberUtils.toInt(cfgValue));
			}
			if (StringUtils.equals(cfgName, "selectCode")) {
				// 在这些号码中选择6个
				config.setSelectCode(StringUtils.isNotBlank(cfgValue) ? cfgValue.split(",") : null);
			}
			if (StringUtils.equals(cfgName, "cannotSelectedTogethor")) {
				// 不能同时出现的号码
				config.setCannotSelectedTogethor(StringUtils.isNotBlank(cfgValue) ? StringUtils.split(
						cfgValue, "|") : null);
			}
			if (StringUtils.equals(cfgName, "haveSideCode")) {
				// 是否有边号
				config.setHaveSideCode(NumberUtils.toInt(cfgValue));
			}
			// 最多中其中一个
			if (StringUtils.equals(cfgName, "zuiduoSelectedOneCode")) {
				config.setZuiduoSelectedOneCode(StringUtils.isNotBlank(cfgValue) ? StringUtils.split(
						cfgValue, "|") : null);
			}
			// 至少选择一个
			if (StringUtils.equals(cfgName, "leastSelectedOneCode")) {
				config.setLeastSelectedOneCode(StringUtils.isNotBlank(cfgValue) ? StringUtils.split(
						cfgValue, "|") : null);
			}
			// 必须选择其中的一个
			if (StringUtils.equals(cfgName, "mustSelectedOneCode")) {
				config.setMustSelectedOneCode(StringUtils.isNotBlank(cfgValue) ? StringUtils.split(
						cfgValue, "|") : null);
			}
			// 必须选其中的两个
			if (StringUtils.equals(cfgName, "mustSelectedTwoCode")) {
				config.setMustSelectedTwoCode(StringUtils.isNotBlank(cfgValue) ? StringUtils.split(
						cfgValue, "|") : null);
			}
			// 至少选其中的两个
			if (StringUtils.equals(cfgName, "leastSelectedTwoCode")) {
				config.setLeastSelectedTwoCode(StringUtils.isNotBlank(cfgValue) ? StringUtils.split(
						cfgValue, "|") : null);
			}
			// 至少选其中的三个
			if (StringUtils.equals(cfgName, "leastSelectedThreeCode")) {
				config.setLeastSelectedThreeCode(StringUtils.isNotBlank(cfgValue) ? StringUtils.split(
						cfgValue, "|") : null);
			}
			// 三个尾数相同的号码
			if (StringUtils.equals(cfgName, "mantissaThreeSame")) {
				config.setMantissaThreeSame(NumberUtils.toInt(cfgValue));
			}
			// 三个2倍数的号码
			if (StringUtils.equals(cfgName, "haveThree2Multiple")) {
				config.setHaveThree2Multiple(NumberUtils.toInt(cfgValue));
			}
			// 三个3倍数的号码
			if (StringUtils.equals(cfgName, "haveThree3Multiple")) {
				config.setHaveThree3Multiple(NumberUtils.toInt(cfgValue));
			}
			// 连续4个奇数或偶数
			if (StringUtils.equals(cfgName, "continueFourOddOreven")) {
				config.setContinueFourOddOreven(NumberUtils.toInt(cfgValue));
			}
			// 5个以上奇数或偶数
			if (StringUtils.equals(cfgName, "geFiveOddOrEven")) {
				config.setGeFiveOddOrEven(NumberUtils.toInt(cfgValue));
			}
			if (StringUtils.equals(cfgName, "is_reFilter")) {
				config.setIs_reFilter(NumberUtils.toInt(cfgValue));
			}
			if (StringUtils.equals(cfgName, "customerLeCount3RedList")) {
				config.setCustomerLeCount3RedList(NumberUtils.toInt(cfgValue));
			}
			// 用户有5人以上投注的号码，都不可能中四个
			if (StringUtils.equals(cfgName, "customerGtCount5RedList")) {
				config.setCustomerGtCount5RedList(NumberUtils.toInt(cfgValue));
			}
			// 是否计算从抓取号码中过滤号码
			if (StringUtils.equals(cfgName, "genFilterRedCodeFromCollectResult")) {
				config.setGenFilterRedCodeFromCollectResult(NumberUtils.toInt(cfgValue));
			}
//			private int selectedSeriOrDiffOrPreCode;
//			private int selectedSeriCodeOrDiffCode;
//			private int selectedPreCodeOrDiffCode;
//			private int isSinaRedCodeNodeSelected;
			if (StringUtils.equals(cfgName, "selectedSeriOrDiffOrPreCode")) {
				config.setSelectedSeriOrDiffOrPreCode(NumberUtils.toInt(cfgValue));
			}
			if (StringUtils.equals(cfgName, "selectedSeriCodeOrDiffCode")) {
				config.setSelectedSeriCodeOrDiffCode(NumberUtils.toInt(cfgValue));
			}
			if (StringUtils.equals(cfgName, "selectedPreCodeOrDiffCode")) {
				config.setSelectedPreCodeOrDiffCode(NumberUtils.toInt(cfgValue));
			}
			if (StringUtils.equals(cfgName, "isSinaRedCodeNodeSelected")) {
				config.setIsSinaRedCodeNodeSelected(NumberUtils.toInt(cfgValue));
			}
		}
		return config;
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
