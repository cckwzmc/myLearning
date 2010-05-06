package com.lottery.ssq.service;

import java.util.Map;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;

import com.lottery.ssq.LotterySsqFetchConfig;
import com.lottery.ssq.LotterySsqFilterConfig;
import com.lottery.ssq.dao.LotteryDao;

/**
 * 媒体推荐的处理
 * 
 * @author Administrator
 */
public class LotterySsqConifgService {
	private LotteryDao dao = null;

	public void setDao(LotteryDao dao) {
		this.dao = dao;
	}

	@SuppressWarnings("unchecked")
	public boolean initFetchConfig() {
		String expect = this.dao.getLotterySsqExpectConfig(0, 0);
		if (StringUtils.isBlank(expect)) {
			return false;
		}
		Map map = this.dao.getLotterySsqFetchConfig("init_filter_data");
		LotterySsqFetchConfig.expect = expect;
		LotterySsqFetchConfig.preRedCode = StringUtils.split(ObjectUtils.toString(map.get("preRedCode")), ",");
		if (ArrayUtils.isNotEmpty(LotterySsqFetchConfig.preRedCode)) {
			String temp = "";
			for (int i = 0; i < LotterySsqFetchConfig.preRedCode.length; i++) {
				int tmpInt = NumberUtils.toInt(LotterySsqFetchConfig.preRedCode[i]);
				if ("".equals(temp)) {
					temp = tmpInt == 1 ? "02" : (((tmpInt - 1) < 10 ? "0" + (tmpInt - 1) : (tmpInt - 1)) + "," + ((tmpInt + 1) < 10 ? "0" + (tmpInt + 1) : (tmpInt + 1)));
				} else {
					String tmpSideCode = tmpInt == 1 ? "02" : (((tmpInt - 1) < 10 ? "0" + (tmpInt - 1) : (tmpInt - 1)) + "," + ((tmpInt + 1) < 10 ? "0" + (tmpInt + 1) : (tmpInt + 1)));
					if(temp.indexOf(tmpSideCode)==-1) {
						temp += "," + tmpSideCode;
					}
				}
			}
			LotterySsqFetchConfig.preSideCode = StringUtils.split(temp, ",");
		}
		LotterySsqFetchConfig.firstMinCode = NumberUtils.toInt(ObjectUtils.toString(map.get("firstMinCode")));
		LotterySsqFetchConfig.firstMaxCode = NumberUtils.toInt(ObjectUtils.toString(map.get("firstMaxCode")));
		LotterySsqFetchConfig.lastMinCode = NumberUtils.toInt(ObjectUtils.toString(map.get("lastMinCode")));
		LotterySsqFetchConfig.lastMaxCode = NumberUtils.toInt(ObjectUtils.toString(map.get("lastMaxCode")));
		LotterySsqFetchConfig.secondMinCode = NumberUtils.toInt(ObjectUtils.toString(map.get("secondMinCode")));
		LotterySsqFetchConfig.secondMaxCode = NumberUtils.toInt(ObjectUtils.toString(map.get("secondMaxCode")));
		LotterySsqFetchConfig.thirdMinCode = NumberUtils.toInt(ObjectUtils.toString(map.get("thirdMinCode")));
		LotterySsqFetchConfig.thirdMaxCode = NumberUtils.toInt(ObjectUtils.toString(map.get("thirdMaxCode")));
		LotterySsqFetchConfig.fourthMinCode = NumberUtils.toInt(ObjectUtils.toString(map.get("fourthMinCode")));
		LotterySsqFetchConfig.fourthMaxCode = NumberUtils.toInt(ObjectUtils.toString(map.get("fourthMaxCode")));

		LotterySsqFetchConfig.media500WanUrl = ObjectUtils.toString(map.get("media500WanUrl"));
		LotterySsqFetchConfig.media500WanUrl = StringUtils.replace(LotterySsqFetchConfig.media500WanUrl, "@expect@", expect);
		LotterySsqFetchConfig.mediaSinaUrl = ObjectUtils.toString(map.get("mediaSinaUrl"));
		LotterySsqFetchConfig.mediaSinaUrl = StringUtils.replace(LotterySsqFetchConfig.mediaSinaUrl, "@expect@", expect);

		LotterySsqFetchConfig.dyjUrl = ObjectUtils.toString(map.get("dyjUrl"));
		LotterySsqFetchConfig.dyjUrl = StringUtils.replace(LotterySsqFetchConfig.dyjUrl, "@expect@", expect);
		LotterySsqFetchConfig.dyjDowload = ObjectUtils.toString(map.get("dyjDowload"));
		LotterySsqFetchConfig.dyjDowload = StringUtils.replace(LotterySsqFetchConfig.dyjDowload, "@expect@", expect);

		LotterySsqFetchConfig.www500wanUrl = ObjectUtils.toString(map.get("www500wanUrl"));
		LotterySsqFetchConfig.www500wanUrl = StringUtils.replace(LotterySsqFetchConfig.www500wanUrl, "@expect@", expect);
		LotterySsqFetchConfig.www500wanDowload = ObjectUtils.toString(map.get("www500wanDowload"));
		LotterySsqFetchConfig.www500wanDowload = StringUtils.replace(LotterySsqFetchConfig.www500wanDowload, "@expect@", expect);

		LotterySsqFetchConfig.caipiaoUrl = ObjectUtils.toString(map.get("caipiaoUrl"));
		LotterySsqFetchConfig.caipiaoUrl = StringUtils.replace(LotterySsqFetchConfig.caipiaoUrl, "@expect@", expect);
		LotterySsqFetchConfig.caipiaoDowload = ObjectUtils.toString(map.get("caipiaoDowload"));
		LotterySsqFetchConfig.caipiaoDowload = StringUtils.replace(LotterySsqFetchConfig.caipiaoDowload, "@expect@", expect);
		return true;
	}

	public boolean initFilterConfig() {
		if (StringUtils.isBlank(LotterySsqFetchConfig.expect)) {
			return false;
		}
		Map map = this.dao.getLotterySsqFetchConfig("gen_data");
		if(MapUtils.isEmpty(map)){
			return false;
		}
		LotterySsqFilterConfig.quOne = NumberUtils.toInt(ObjectUtils.toString(map.get("quOne")));
		LotterySsqFilterConfig.quTwo = NumberUtils.toInt(ObjectUtils.toString(map.get("quTwo")));
		LotterySsqFilterConfig.quThree =  NumberUtils.toInt(ObjectUtils.toString(map.get("quThree")));
		LotterySsqFilterConfig.quOneNum = NumberUtils.toInt(ObjectUtils.toString(map.get("quOneNum")));
		LotterySsqFilterConfig.quTwoNum =  NumberUtils.toInt(ObjectUtils.toString(map.get("quTwoNum")));
		// 不包含
		LotterySsqFilterConfig.excludeRed = StringUtils.isNotBlank( ObjectUtils.toString(map.get("excludeRed")))?ObjectUtils.toString(map.get("excludeRed")).split(","):null;
		// 必须有的
		LotterySsqFilterConfig.musthavered = StringUtils.isNotBlank(ObjectUtils.toString(map.get("musthavered")))?ObjectUtils.toString(map.get("musthavered")).split(","):null;
		// 是否从 文件中读取排除号码
		LotterySsqFilterConfig.ishaveexclude = NumberUtils.toInt(ObjectUtils.toString(map.get("ishaveexclude")));
		// 有几个两连号
		LotterySsqFilterConfig.haveTwoSeries = NumberUtils.toInt(ObjectUtils.toString(map.get("haveTwoSeries")));
		// 有几个三连号
		LotterySsqFilterConfig.haveThreeSeries = NumberUtils.toInt(ObjectUtils.toString(map.get("haveThreeSeries")));
		// 有几个差值为1的，，如1，3，5 算两个
		LotterySsqFilterConfig.haveOnediffer = NumberUtils.toInt(ObjectUtils.toString(map.get("haveOnediffer")));
		// 在这些号码中选择6个
		LotterySsqFilterConfig.selectCode =StringUtils.isNotBlank(ObjectUtils.toString(map.get("selectCode")))?ObjectUtils.toString(map.get("selectCode")).split(","):null;
		// 不能同时出现的号码
		LotterySsqFilterConfig.cannotSelectedTogethor = StringUtils.isNotBlank(ObjectUtils.toString(map.get("cannotSelectedTogethor")))?StringUtils.split(ObjectUtils.toString(map.get("cannotSelectedTogethor")),"|"):null;
		// 是否有边号
		LotterySsqFilterConfig.haveSideCode = NumberUtils.toInt(ObjectUtils.toString(map.get("haveSideCode")));
		// 包含includeRed中的几个数字
		LotterySsqFilterConfig.firstMinCode = NumberUtils.toInt(ObjectUtils.toString(map.get("firstMinCode")));
		LotterySsqFilterConfig.firstMaxCode = NumberUtils.toInt(ObjectUtils.toString(map.get("firstMaxCode")));
		LotterySsqFilterConfig.lastMinCode = NumberUtils.toInt(ObjectUtils.toString(map.get("lastMinCode")));
		LotterySsqFilterConfig.lastMaxCode = NumberUtils.toInt(ObjectUtils.toString(map.get("lastMaxCode")));
		LotterySsqFilterConfig.secondMinCode = NumberUtils.toInt(ObjectUtils.toString(map.get("secondMinCode")));
		LotterySsqFilterConfig.secondMaxCode = NumberUtils.toInt(ObjectUtils.toString(map.get("secondMaxCode")));
		LotterySsqFilterConfig.thirdMinCode = NumberUtils.toInt(ObjectUtils.toString(map.get("thirdMinCode")));
		LotterySsqFilterConfig.thirdMaxCode = NumberUtils.toInt(ObjectUtils.toString(map.get("thirdMaxCode")));
		LotterySsqFilterConfig.fourthMinCode = NumberUtils.toInt(ObjectUtils.toString(map.get("fourthMinCode")));
		LotterySsqFilterConfig.fourthMaxCode = NumberUtils.toInt(ObjectUtils.toString(map.get("fourthMaxCode")));

		LotterySsqFilterConfig.selectedOneCode =StringUtils.isNotBlank(ObjectUtils.toString(map.get("selectedOneCode")))?StringUtils.split(ObjectUtils.toString(map.get("selectedOneCode")),"|"):null;
		LotterySsqFilterConfig.leastSelectedOneCode =StringUtils.isNotBlank(ObjectUtils.toString(map.get("leastSelectedOneCode")))?StringUtils.split(ObjectUtils.toString(map.get("leastSelectedOneCode")),"|"):null;
		LotterySsqFilterConfig.mustSelectedOneCode =StringUtils.isNotBlank(ObjectUtils.toString(map.get("mustSelectedOneCode")))?StringUtils.split(ObjectUtils.toString(map.get("mustSelectedOneCode")),"|"):null;
		return true;
	}
}
