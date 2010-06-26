package com.lottery.ssq.Algorithm;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lottery.ssq.utils.LotterySsqUtils;

/**
 * 针对采集结果的总体算法
 * 
 * @author
 */
public class LotterySsqCollectResultAlgorithm {
	private static final Logger logger = LoggerFactory.getLogger(LotterySsqCollectResultAlgorithm.class);

	/**
	 * 对用户选择的前20个号码进行过滤 不能存在前20中的4以上号码
	 * 
	 * @param lValues
	 * @param customerMaxSelected
	 * @return
	 */
	public static boolean isCustomerRedCodeTop20Filter(String[] lValues, List<String> customerMaxSelected) {
		int selected = 0;
		for (String redCode : customerMaxSelected) {
			for (int i = 0; i < lValues.length; i++) {
				if (StringUtils.equals(lValues[i], redCode)) {
					selected++;
				}
			}
		}
		if (selected > 4) {
			return false;
		}
		return true;
	}

	/**
	 * 
	 * redcode 必须在 tmp数组中
	 * 
	 * @param redcode
	 * @param tmp
	 * @return
	 */
	public static boolean isIncludeLocationRedCode(String redcode, String[] tmp) {
		if (tmp == null || tmp.length == 0) {
			return true;
		}
		boolean isExist = false;
		for (String rd : tmp) {
			if (StringUtils.equals(rd, redcode)) {
				isExist = true;
			}
		}
		if (!isExist) {
			return false;
		}
		return true;
	}

	/**
	 * @param firstRedCode
	 * @param secondRedCode
	 * @param thirdRedCode
	 * @param fourthRedCode
	 * @param firthRedCode
	 * @param sixthRedCode
	 * @param lValues
	 * @return
	 */
	public static boolean isIncludeLocationRedCode(String[] firstRedCode, String[] secondRedCode,
			String[] thirdRedCode, String[] fourthRedCode, String[] firthRedCode, String[] sixthRedCode,
			String[] lValues) {
		boolean locationFlag = true;
		for (int j = 2; j < 6; j++) {
			String[] tmp = null;
			if (j == 2) {
				tmp = thirdRedCode;
			}
			if (j == 3) {
				tmp = fourthRedCode;
			}
			if (j == 4) {
				tmp = firthRedCode;
			}
			if (j == 5) {
				tmp = sixthRedCode;
			}
			if (!LotterySsqCollectResultAlgorithm.isIncludeLocationRedCode(lValues[j], tmp)) {
				locationFlag = false;
				break;
			}
		}
		return locationFlag;
	}

	/**
	 * 与不能同时出现的号码类似，只是这是通过网上用户收集而来的。 1、一个胆不考虑 2、三个胆的不能全中 3、大于三个的最多只能中3个
	 * 
	 * @param lValues
	 * @param l
	 * @param sinaDanList
	 * @return
	 */
	public static boolean isCustomerDanFilter(String[] lValues, List<String> danList, int num) {
		int tempSelect = 0;
		if (CollectionUtils.isNotEmpty(danList)) {
			boolean breakFlag = false;
			for (int j = 0; j < danList.size(); j++) {
				String[] tmp = StringUtils.split(ObjectUtils.toString(danList.get(j)), ",");
				if (tmp.length < num) {
					continue;
				}
				if (tmp.length == 3) {
					tempSelect = 0;
					for (int k = 0; k < tmp.length; k++) {
						for (int i = 0; i < lValues.length; i++) {
							if (StringUtils.equals(lValues[i], ObjectUtils.toString(tmp[k]).trim())) {
								tempSelect++;
							}
						}
					}
					if (tmp.length == tempSelect) {
						breakFlag = true;
						break;
					}
				} else {
					tempSelect = 0;
					for (int k = 0; k < tmp.length; k++) {
						for (int i = 0; i < lValues.length; i++) {
							if (StringUtils.equals(lValues[i], ObjectUtils.toString(tmp[k]).trim())) {
								tempSelect++;
							}
						}
					}
					if (tempSelect > 3) {
						breakFlag = true;
						break;
					}
				}
			}
			if (breakFlag) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 对用户选择的前10个号码进行过滤 不能存在前2的号码 不能存在前10中的2以上号码
	 * 
	 * @param lValues
	 * @param customerMaxSelected
	 * @return
	 */
	public static boolean isCustomerRedCodeTop10Filter(String[] lValues, List<String> customerMaxSelected) {
		String no1 = customerMaxSelected.get(0);
		String no2 = customerMaxSelected.get(1);
		for (int i = 0; i < lValues.length; i++) {
			if (StringUtils.equals(lValues[i], no1) || StringUtils.equals(lValues[i], no2)) {
				return false;
			}
		}
		int selected = 0;
		for (String redCode : customerMaxSelected) {
			for (int i = 0; i < lValues.length; i++) {
				if (StringUtils.equals(lValues[i], redCode)) {
					selected++;
				}
			}
		}
		if (selected > 2) {
			return false;
		}
		return true;
	}

	/**
	 * 用户投注，5个用户投注以上的号码不能中4个号码
	 * 
	 * @param lValues
	 * @param customerGtCount5RedList
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static boolean isCumstomerRedIncludeFourCode(String[] lValues, List customerGtCount5RedList) {
		if (CollectionUtils.isEmpty(customerGtCount5RedList)) {
			return true;
		}
		for (Iterator iterator = customerGtCount5RedList.iterator(); iterator.hasNext();) {
			int selected = 0;
			Map map = (Map) iterator.next();
			String redCode = ObjectUtils.toString(map.get("redcode"));
			String[] redCodes = StringUtils.split(redCode, ",");
			for (int i = 0; i < redCodes.length; i++) {
				for (int j = 0; j < lValues.length; j++) {
					if (StringUtils.equals(redCodes[i], lValues[j])) {
						selected++;
					}
				}
			}
			if (selected > 4) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 用户投注的前20个不回中超过3个。
	 * 
	 * @param lValues
	 * @param cRedList
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static boolean isRedThreeCodeInCustomerResult(String[] lValues, List cRedList) {
		for (Iterator iterator = cRedList.iterator(); iterator.hasNext();) {
			int selected = 0;
			Map map = (Map) iterator.next();
			String redCode = ObjectUtils.toString(map.get("redcode"));
			String[] redCodes = StringUtils.split(redCode, ",");
			for (int i = 0; i < redCodes.length; i++) {
				for (int j = 0; j < lValues.length; j++) {
					if (StringUtils.equals(redCodes[i], lValues[j])) {
						selected++;
					}
				}
			}
			if (selected > 3) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 用户投注的前10个不回中超过2个。
	 * 
	 * @param lValues
	 * @param cRedList
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static boolean isRedTwoCodeInCustomerResult(String[] lValues, List cRedList) {
		for (Iterator iterator = cRedList.iterator(); iterator.hasNext();) {
			int selected = 0;
			Map map = (Map) iterator.next();
			String redCode = ObjectUtils.toString(map.get("redcode"));
			String[] redCodes = StringUtils.split(redCode, ",");
			for (int i = 0; i < redCodes.length; i++) {
				for (int j = 0; j < lValues.length; j++) {
					if (StringUtils.equals(redCodes[i], lValues[j])) {
						selected++;
					}
				}
			}
			if (selected > 2) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 用户投注，有3个用户投注以下（包括3个）的号码必有中五个号码.
	 * <p/>
	 * 如有3注以上的号码,在这些号码中，合并后必中6个.
	 * 
	 * @param lValues
	 * @param customerEqCount3RedList
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static boolean isCumstomerRedIncludeFiveCode(String[] lValues, List customerEqCount3RedList) {
		if (CollectionUtils.isEmpty(customerEqCount3RedList)) {
			return true;
		}
		List<String[]> tmpRedCodeList = new ArrayList<String[]>();
		for (Iterator iterator = customerEqCount3RedList.iterator(); iterator.hasNext();) {
			int selected = 0;
			Map map = (Map) iterator.next();
			String redCode = ObjectUtils.toString(map.get("redcode"));
			String[] redCodes = StringUtils.split(redCode, ",");
			for (int i = 0; i < redCodes.length; i++) {
				for (int j = 0; j < lValues.length; j++) {
					if (StringUtils.equals(redCodes[i], lValues[j])) {
						selected++;
					}
				}
			}
			if (selected == 5) {
				tmpRedCodeList.add(redCodes);
			}
		}
		if (CollectionUtils.isEmpty(tmpRedCodeList) || tmpRedCodeList.size() > 50) {
			return false;
		}
		if (tmpRedCodeList.size() > 2) {
			String[] mergeRedCode = LotterySsqUtils.mergeRedCode(tmpRedCodeList);
			if (mergeRedCode == null) {
				return false;
			}
			int selectedCode = 0;
			for (String cRed : lValues) {
				for (String red : mergeRedCode) {
					if (StringUtils.equals(cRed, red)) {
						selectedCode++;
						break;
					}
				}
			}
			if (selectedCode != lValues.length) {
				return false;
			}
			// logger.info("中5个的红球号码，号码合并==" + StringUtils.join(mergeRedCode, ","));
		}
		return true;
	}
}
