package com.lottery.ssq.filter;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;

import com.lottery.ssq.Algorithm.LotterySsqAlgorithm;
import com.lottery.ssq.config.LotterySsqConfig;
import com.lottery.ssq.config.LotterySsqFilterConfig;

/**
 * 一些基本过滤方法.
 * 
 * @author ly.zy.ljh
 * 
 */
public class LotterySsqStantardFilterService {
	public boolean stantardFilter(LotterySsqFilterConfig filterConfig, String[] lValues) {

		// 数字的范围，红球是否在指定范围，必须过滤的方法.
		if (!LotterySsqAlgorithm.isRedNumericInRange(filterConfig, lValues)) {
			return false;
		}
		// 是否需要胆码过滤
		if (filterConfig.getMusthavered() != null && filterConfig.getMusthavered().length >= 1) {
			if (!LotterySsqAlgorithm.isRedIncludeRequiredCode(filterConfig, lValues)) {
				return false;
			}
		}
		if (filterConfig.getIncludePreRedNum() >= 0) {
			// 是否包含上一期的号码
			if (!LotterySsqAlgorithm.isRedIncludePreRedCode(filterConfig, lValues)) {
				return false;
			}
		}
		// 是否包括边号
		if (filterConfig.getHaveSideCode() > 0 && LotterySsqConfig.preSideCode.length > 0) {
			if (!LotterySsqAlgorithm.isRedIncludeSideCode(filterConfig, lValues)) {
				return false;
			}
		}
		// 不能出现的号码
		if (filterConfig.getExcludeRed() != null && filterConfig.getExcludeRed().length >= 1) {
			if (!LotterySsqAlgorithm.isRedNotIncludeTheCode(filterConfig, lValues)) {
				return false;
			}
		}
		// 在指定的一系列号码中选取6个
		if (filterConfig.getSelectCode() != null && filterConfig.getSelectCode().length >= 6) {
			if (!LotterySsqAlgorithm.isRedInTheCodes(filterConfig, lValues)) {
				return false;
			}
		}// 不能同时出现的号码多组用"|"分割
		if (filterConfig.getCannotSelectedTogethor() != null && filterConfig.getCannotSelectedTogethor().length >= 1) {
			if (!LotterySsqAlgorithm.isRedTogethorCode(filterConfig, lValues)) {
				return false;
			}
		}// 是否包含两连号
		if (filterConfig.getHaveTwoSeries() >= 0) {
			if (!LotterySsqAlgorithm.isRedIncludeEvenIn(filterConfig, lValues)) {
				return false;
			}
		}
		// 是否包含三连号
		if (filterConfig.getHaveThreeSeries() >= 0) {
			if (!LotterySsqAlgorithm.isRedIncludeThreeEvenIn(filterConfig, lValues)) {
				return false;
			}
		}
		// 是否包含隔号
		if (filterConfig.getHaveOnediffer() >= 0) {
			if (!LotterySsqAlgorithm.isRedIncludeDifferCode(filterConfig, lValues)) {
				return false;
			}
		}// 至少中其中的一个号码
		if (filterConfig.getLeastSelectedOneCode() != null && filterConfig.getLeastSelectedOneCode().length > 0) {
			if (!LotterySsqAlgorithm.isLeastSelectedOneCode(filterConfig, lValues)) {
				return false;
			}
		}// 必须选择一个/是并的关系而不是或得关系
		if (filterConfig.getMustSelectedOneCode() != null && filterConfig.getMustSelectedOneCode().length > 0) {

			if (!LotterySsqAlgorithm.isMustSelectedOneCode(filterConfig, lValues)) {
				return false;
			}
		}// 最多只能其中的一个号码
		if (filterConfig.getZuiduoSelectedOneCode() != null && filterConfig.getZuiduoSelectedOneCode().length > 0) {
			if (!LotterySsqAlgorithm.isSelectOneCode(filterConfig, lValues)) {
				return false;
			}
		}
		// 必须选其中的两个
		if (filterConfig.getMustSelectedTwoCode() != null && filterConfig.getMustSelectedTwoCode().length > 0) {
			if (!LotterySsqAlgorithm.mustSelectedTwoRedCode(filterConfig, lValues)) {
				return false;
			}
		}
		// 至少选其中的两个.
		if (filterConfig.getLeastSelectedTwoCode() != null && filterConfig.getLeastSelectedTwoCode().length > 0) {
			if (!LotterySsqAlgorithm.leastSelectedTwoRedCode(filterConfig, lValues)) {
				return false;
			}
		}
		// 至少选其中的三个
		if (filterConfig.getLeastSelectedThreeCode() != null && filterConfig.getLeastSelectedThreeCode().length > 0) {
			if (!LotterySsqAlgorithm.leastSelectedThreeRedCode(filterConfig, lValues)) {
				return false;
			}
		}
		// 过滤掉有三个尾数相同的号码，如2,12,32
		if (filterConfig.getMantissaThreeSame() > 0) {
			if (!LotterySsqAlgorithm.mantissaThreeSame(filterConfig, lValues)) {
				return false;
			}
		}
		// 过滤掉有三个2倍数的号码，如2,4,8
		if (filterConfig.getHaveThree2Multiple() > 0) {
			if (!LotterySsqAlgorithm.haveThree2Multiple(filterConfig, lValues)) {
				return false;
			}
		}
		// 过滤掉有三个3倍数的号码，如2,6,18
		if (filterConfig.getHaveThree3Multiple() > 0) {
			if (!LotterySsqAlgorithm.haveThree3Multiple(filterConfig, lValues)) {
				return false;
			}
		}
		// 连续4个奇数或偶数
		if (filterConfig.getContinueFourOddOreven() > 0) {
			if (!LotterySsqAlgorithm.continueFourOddOreven(filterConfig, lValues)) {
				return false;
			}
		}
		// 有5个以上的奇数或偶数包含5个.
		if (filterConfig.getGeFiveOddOrEven() > 0) {
			if (!LotterySsqAlgorithm.geFiveOddOrEven(filterConfig, lValues)) {
				return false;
			}
		}
		// 必须中上一期一个号码或有连号
		if (filterConfig.getSelectedPreCodeOrSeriCode() > 0) {
			if (!LotterySsqAlgorithm.isRedIncludePreRedCode(filterConfig, lValues, 1)
					&& !LotterySsqAlgorithm.isRedIncludeEvenIn(filterConfig, lValues)) {
				return false;
			}
		}
		// 必须中上一期一个号码或有隔号
		if (filterConfig.getSelectedPreCodeOrDiffCode() > 0) {
			if (!this.isRedIncludePreRedCode(filterConfig, lValues, 1)
					&& !this.isRedIncludeDifferCode(filterConfig, lValues,1)) {
				return false;
			}
		}
		// 必须中隔号或连号
		if (filterConfig.getSelectedSeriCodeOrDiffCode() > 0) {
			if (!this.isRedIncludeEvenIn(filterConfig, lValues,1)
					&& !this.isRedIncludeDifferCode(filterConfig, lValues,1)) {
				return false;
			}
		}
		// 必须中上一期一个号码或连号或隔号
		if (filterConfig.getSelectedSeriOrDiffOrPreCode() > 1) {
			if (!this.isRedIncludePreRedCode(filterConfig, lValues, 1)
					&& !this.isRedIncludeEvenIn(filterConfig, lValues,1)
					&& !this.isRedIncludeDifferCode(filterConfig, lValues,1)) {
				return false;
			}
		}
		if (filterConfig.getOneQuNum() > 0 || filterConfig.getTwoQuNum() > 0
				|| filterConfig.getThreeQuNum() > 0) {
			if (!LotterySsqAlgorithm.isQuNum(filterConfig, lValues)) {
				return false;
			}
		}
		// 对历史号码的研究
		// 1.1、2、3/4/5、差值的统计
		// 重号附近号码规律的统计
		return true;
	}
	/**
	 * 是否符合在区域的号码分布
	 * 
	 * @param lValues
	 * @param filterConfig 
	 * @return
	 */
	public boolean stantardFilterQu(String[] lValues, LotterySsqFilterConfig filterConfig){
		int qOne = 0;
		int qTwo = 0;
		int qThree = 0;
		for (int i = 0; i < lValues.length; i++) {
			if (filterConfig.getQuOne() != -1 && NumberUtils.toInt(lValues[i]) <= filterConfig.getQuOneNum()) {
				qOne++;
			}
			if (filterConfig.getQuTwo() != -1 && NumberUtils.toInt(lValues[i]) > filterConfig.getQuOneNum()
					&& NumberUtils.toInt(lValues[i]) <= filterConfig.getQuTwoNum()) {
				qTwo++;
			}
			if (filterConfig.getQuThree() != -1 && NumberUtils.toInt(lValues[i]) > filterConfig.getQuTwoNum()) {
				qThree++;
			}
		}
		return LotterySsqAlgorithm.isRedCoincidenceZone(filterConfig, lValues, qOne, qTwo, qThree);
	}
	private boolean isRedIncludeEvenIn(LotterySsqFilterConfig filterConfig, String[] lValues,int num) {
		if (num <= 0) {
			return true;
		}
		int tempSelect2=0;
		for (int j = 0; j < lValues.length; j++) {
			int rValue = NumberUtils.toInt(lValues[j]);
			int nextValue = (j + 1) < lValues.length ? NumberUtils.toInt(lValues[j + 1]) : -1;
			if (nextValue != -1 && nextValue - rValue == 1) {
				tempSelect2++;
			}
		}
		if (tempSelect2 >= num) {
			return true;
		}
		return false;
	}

	private boolean isRedIncludeDifferCode(LotterySsqFilterConfig filterConfig, String[] lValues, int num) {
		if (num<=0) {
			return true;
		}
		int tempSelect = 0;
		for (int j = 0; j < lValues.length; j++) {
			int rValue = NumberUtils.toInt(lValues[j]);
			int nextValue = (j + 1) < lValues.length ? NumberUtils.toInt(lValues[j + 1]) : -1;
			if (nextValue != -1 && nextValue - rValue == 2) {
				tempSelect++;
			}
		}
		if (tempSelect >= num) {
			return true;
		}
		return false;
	}

	private boolean isRedIncludePreRedCode(LotterySsqFilterConfig filterConfig, String[] lValues, int num) {
		if (num<=0) {
			return true;
		}
		int tempSelect = 0;
		for (int j = 0; j < lValues.length; j++) {
			for (int k = 0; k < LotterySsqConfig.preRedCode.length; k++) {
				if (StringUtils.equals(lValues[j], LotterySsqConfig.preRedCode[k])) {
					tempSelect++;
				}
			}
		}
		if (filterConfig.getIncludePreRedNum() >= 0 && tempSelect >= num) {
			return true;
		}
		return false;
	}
}
