package com.lottery.ssq.Algorithm;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;

import com.lottery.ssq.config.LotterySsqFetchConfig;

/**
 * @author Administrator
 */
public class LotterySsqFirstFilterAlgorithm {

	// ~~~~~~~~~~~~~~~~~~~~以下是初步过滤的方法~~~~~~~~~~~~~~~~~~~~~~~~~~~

	/**
	 * 与配置文件无关，只要包含一个边号，即，返回True
	 * 
	 * @param lValues
	 * @param preSideCode
	 *            边号集合
	 * @return
	 */
	public static boolean isFilterRedIncludeSideCode(String[] lValues, String[] preSideCode) {
		int tempSelect = 0;
		if (preSideCode == null || preSideCode.length < 1) {
			return true;
		}
		for (int j = 0; j < lValues.length; j++) {
			for (int k = 0; k < preSideCode.length; k++) {
				if (StringUtils.equals(lValues[j], preSideCode[k])) {
					tempSelect++;
				}
			}
		}
		if (tempSelect == 0) {
			return false;
		}
		return true;
	}

	/**
	 * 与配置文件无关，只要包含一个上一期的号码，即，返回True
	 * 
	 * @param lValues
	 * @return
	 */
	public static boolean isFilterIncludePreCode(String[] lValues, String[] preRedCode) {
		int tempSelect = 0;

		for (int j = 0; j < lValues.length; j++) {
			for (int k = 0; k < preRedCode.length; k++) {
				if (StringUtils.equals(lValues[j], preRedCode[k])) {
					tempSelect++;
				}
			}
		}
		if (tempSelect < 1) {
			return false;
		}
		return true;
	}

	/**
	 * 第一步过滤使用
	 * 
	 * @param lValues
	 * @return
	 */
	public static boolean isFilterRedNumericInRange(String[] redCode) {
		if (NumberUtils.toInt(redCode[0]) > LotterySsqFetchConfig.firstMaxCode) {
			return false;
		}
		if (NumberUtils.toInt(redCode[0]) < LotterySsqFetchConfig.firstMinCode) {
			return false;
		}
		if (NumberUtils.toInt(redCode[1]) > LotterySsqFetchConfig.secondMaxCode) {
			return false;
		}
		if (NumberUtils.toInt(redCode[1]) < LotterySsqFetchConfig.secondMinCode) {
			return false;
		}
		if (NumberUtils.toInt(redCode[2]) > LotterySsqFetchConfig.thirdMaxCode) {
			return false;
		}
		if (NumberUtils.toInt(redCode[2]) < LotterySsqFetchConfig.thirdMinCode) {
			return false;
		}
		if (NumberUtils.toInt(redCode[3]) > LotterySsqFetchConfig.fourthMaxCode) {
			return false;
		}
		if (NumberUtils.toInt(redCode[3]) < LotterySsqFetchConfig.fourthMinCode) {
			return false;
		}
		if (NumberUtils.toInt(redCode[5]) < LotterySsqFetchConfig.lastMinCode) {
			return false;
		}
		if (NumberUtils.toInt(redCode[5]) > LotterySsqFetchConfig.lastMaxCode) {
			return false;
		}
		return true;
	}
}
