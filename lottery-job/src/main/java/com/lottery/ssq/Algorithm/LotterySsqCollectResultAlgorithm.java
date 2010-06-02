package com.lottery.ssq.Algorithm;

import java.util.List;

import org.apache.commons.lang.StringUtils;

/**
 * 针对采集结果的总体算法
 * @author 
 */
public class LotterySsqCollectResultAlgorithm {
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
}
