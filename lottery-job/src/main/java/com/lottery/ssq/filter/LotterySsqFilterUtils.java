package com.lottery.ssq.filter;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;

import com.lottery.ssq.Algorithm.LotterySsqAlgorithm;
import com.lottery.ssq.Algorithm.LotterySsqCollectResultAlgorithm;
import com.lottery.ssq.Algorithm.LotterySsqMediaAlgorithm;
import com.lottery.ssq.config.LotterySsqFilterConfig;

public class LotterySsqFilterUtils {

	/** 新浪媒体擂台 **/
	/**
	 * @param filterConfig
	 * @param methodName
	 * @param arg
	 * @param lValues
	 * @param sinaDanList
	 * @param sinaRedCodeList
	 * @return
	 */
	public static boolean sinaFilterMethod(LotterySsqFilterConfig filterConfig, String methodName, String arg,
			String[] lValues, List<String> sinaDanList, List<String[]> sinaRedCodeList) {
		String[] args = arg.split(",");
		// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
		if ("isSinaDanTogethorFilter".equals(methodName)) {
			if (!LotterySsqMediaAlgorithm.isSinaDanTogethorFilter(filterConfig, lValues, sinaDanList)) {
				return false;
			}
		}
		if ("isSinaDanAllFilter".equals(methodName)) {

			if (!LotterySsqMediaAlgorithm.isSinaDanAllFilter(filterConfig, lValues, sinaDanList, NumberUtils
					.toInt(args[0]))) {
				return false;
			}
		}
		if ("isSinaDanNoneFilter".equals(methodName)) {
			if (!LotterySsqMediaAlgorithm.isSinaDanNoneFilter(filterConfig, lValues, sinaDanList, NumberUtils
					.toInt(args[0]))) {
				return false;
			}
		}
		if ("isSinaRedIncludeFourCode".equals(methodName)) {
			if (!LotterySsqMediaAlgorithm.isSinaRedIncludeFourCode(filterConfig, lValues, new HashSet<String[]>(
					sinaRedCodeList), NumberUtils.toInt(args[0]))) {
				return false;
			}
		}
		if ("isSinaRedCodeXiaoFourFilter".equals(methodName)) {
			if (!LotterySsqMediaAlgorithm.isSinaRedCodeXiaoFourFilter(filterConfig, lValues, sinaRedCodeList,
					NumberUtils.toInt(args[0]))) {
				return false;
			}
		}
		if ("isSinaRedCodeNodeSelected".equals(methodName) && filterConfig.getIsSinaRedCodeNodeSelected() == 1) {
			if (!LotterySsqMediaAlgorithm.isSinaRedCodeNodeSelected(filterConfig, lValues, sinaRedCodeList, NumberUtils
					.toInt(args[0]))) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 500万媒体过滤
	 * 
	 * @param filterConfig
	 * 
	 * @param methodName
	 * @param arg
	 * @param lValues
	 * @param wan500RedCodeList
	 * @return
	 */
	public static boolean wan500FilterMethod(LotterySsqFilterConfig filterConfig, String methodName, String arg,
			String[] lValues, Set<String[]> wan500RedCodeList) {
		String[] args = arg.split(",");
		if ("isWan500RedIncludeFourCode".equals(methodName)) {
			if (!LotterySsqMediaAlgorithm.isWan500RedIncludeFourCode(lValues, wan500RedCodeList, NumberUtils
					.toInt(args[0]))) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 用户选号过滤
	 * 
	 * @param filterConfig
	 * 
	 * @param methodName
	 * @param arg
	 * @param lValues
	 * @param customerDanList
	 * @param customerGtCount5RedList
	 * @param customerEqCount1RedList
	 * @return
	 */
	public static boolean customerFilterMethod(LotterySsqFilterConfig filterConfig, String methodName, String arg,
			String[] lValues, List<String> customerDanList, List customerLeCount3RedList, List customerGtCount5RedList) {
		String[] args = arg.split(",");
		// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

		// if
		// (!LotterySsqCollectResultAlgorithm.isIncludeLocationRedCode(firstRedCode,secondRedCode,thirdRedCode,
		// fourthRedCode, firthRedCode, sixthRedCode, lValues)) {
		// continue;
		// }
		// 用户投注的胆
		if ("isCustomerDanFilter".equals(methodName)) {
			if (!LotterySsqCollectResultAlgorithm.isCustomerDanFilter(lValues, customerDanList, NumberUtils
					.toInt(args[0]))) {
				return false;
			}
		}
		// 用户投注不能中4一个以上的号码
		if ("isCumstomerRedIncludeFourCode".equals(methodName)) {
			if (!LotterySsqCollectResultAlgorithm.isCumstomerRedIncludeFourCode(lValues, customerGtCount5RedList)) {
				return false;
			}
		}
		// 只有一个用户投注的号码
		if ("isCumstomerRedIncludeFiveCode".equals(methodName)) {
			if (!LotterySsqCollectResultAlgorithm.isCumstomerRedIncludeFiveCode(lValues, customerLeCount3RedList)) {
				return false;
			}
		}
		if ("leastSelectedTwoCode".equals(methodName)) {
			if (!LotterySsqAlgorithm.isLeastSelectedTwoCode(lValues, filterConfig.getLeastSelectedTwoCode())) {
				return false;
			}
		}

		// 收集号码的排行中的前10个
		// if (!LotterySsqCollectResultAlgorithm.isCustomerRedCodeTop10Filter(lValues,
		// customerMaxSelected.subList(0, 10))) {
		// continue;
		// }
		// 收集号码的排行中的前20个
		// if (!LotterySsqCollectResultAlgorithm.isCustomerRedCodeTop20Filter(lValues,
		// customerMaxSelected.subList(0, 20))) {
		// continue;
		// }
		// if (!LotterySsqAlgorithm.isFileRedCodeFourFilter(lValues, otherRedCodeList)) {
		// continue;
		// }

		// if (!LotterySsqAlgorithm.isRedTwoCodeInCustomerResult(lValues, cRedList.subList(0, 10))) {
		// continue;
		// }
		// if (!LotterySsqAlgorithm.isRedThreeCodeInCustomerResult(lValues, cRedList)) {
		// continue;
		// }
		// if (!LotterySsqAlgorithm.isRedFourCodeResult(lValues, customerRedTop40)) {
		// continue;
		// }

		// if (!LotterySsqAlgorithm.isRedFourCodeInCustomerResult(lValues, cRedList)) {
		// continue;
		// }
		// 在用户投注中5个的不能超过10个
		// if(selectedRedCodeGtFive(lValues)){
		// continue;
		// }
		/*
		 * 即我认为这些号码是不可能中奖的， 推荐使用文本方式保存的号码使用
		 */
		// if(CollectionUtils.isNotEmpty(fileRedCode)&&!LotterySsqAlgorithm.isRedCodeHaveSix(fileRedCode,lValues)){
		// continue;
		// }
		return true;
	}

	/**
	 * 必须有6个号码在mergeCode中
	 * 
	 * @param lValues
	 * @param mergeCode
	 * @return
	 */
	public static boolean isCumstomerRedIncludeFiveCode(String[] lValues, String mergeCode) {
		if (StringUtils.isBlank(mergeCode)) {
			return true;
		}
		String[] mergeCodes = mergeCode.split(",");
		int selectedCode = 0;
		for (String code : mergeCodes) {
			for (String value : lValues) {
				if (StringUtils.equals(code, value)) {
					selectedCode++;
				}
			}
		}
		if (selectedCode < 6) {
			return false;
		}
		return true;
	}
}
