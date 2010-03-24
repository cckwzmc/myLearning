package com.lyxmq.lottery.ssq;

import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;

/**
 * @author Administrator
 *
 */
public class LotterySsqAlgorithm {
	/**
	 * 数字在指定的范围内
	 * 
	 * @param redCode
	 * @return
	 */
	public static boolean isRedNumericInRange(String[] redCode) {
		if (NumberUtils.toInt(redCode[0]) > LotterySsqConifgService.getFirstMaxCode()) {
			return false;
		}
		if (NumberUtils.toInt(redCode[0]) < LotterySsqConifgService.getFirstMinCode()) {
			return false;
		}
		if (NumberUtils.toInt(redCode[1]) > LotterySsqConifgService.getSecondMaxCode()) {
			return false;
		}
		if (NumberUtils.toInt(redCode[1]) < LotterySsqConifgService.getSecondMinCode()) {
			return false;
		}
		if (NumberUtils.toInt(redCode[2]) > LotterySsqConifgService.getThirdMaxCode()) {
			return false;
		}
		if (NumberUtils.toInt(redCode[2]) < LotterySsqConifgService.getThirdMinCode()) {
			return false;
		}
		if (NumberUtils.toInt(redCode[3]) > LotterySsqConifgService.getFourthMaxCode()) {
			return false;
		}
		if (NumberUtils.toInt(redCode[3]) < LotterySsqConifgService.getFourthMinCode()) {
			return false;
		}
		if (NumberUtils.toInt(redCode[5]) < LotterySsqConifgService.getLastMinCode()) {
			return false;
		}
		if (NumberUtils.toInt(redCode[5]) > LotterySsqConifgService.getLastMaxCode()) {
			return false;
		}
		return true;
	}

	/**
	 * 必须至少包含其中的任意一个数字
	 * 
	 * @param lValues
	 * @return
	 */
	public static boolean isRedIncludeAnyOneCode(String[] lValues) {
		int tempSelect = 0;

		for (int j = 0; j < lValues.length; j++) {
			for (int k = 0; k < LotterySsqConifgService.getIncludeRed().length; k++) {
				if (StringUtils.equals(lValues[j], LotterySsqConifgService.getIncludeRed()[k])) {
					tempSelect++;
				}
			}
		}
		if (tempSelect < LotterySsqConifgService.getIncludeRedNum()) {
			return false;
		}
		return true;
	}

	/**
	 * // 必须包含其中的任意一个数字(边号)
	 * 
	 * @param lValues
	 * @return
	 */
	public static boolean isRedIncludeSideCode(String[] lValues) {
		int tempSelect = 0;
		if (LotterySsqConifgService.getPreSideCode() != null && LotterySsqConifgService.getPreSideCode().length > 0) {
			for (int j = 0; j < lValues.length; j++) {
				for (int k = 0; k < LotterySsqConifgService.getPreSideCode().length; k++) {
					if (StringUtils.equals(lValues[j], LotterySsqConifgService.getPreSideCode()[k])) {
						tempSelect++;
					}
				}
			}
		}
		if (tempSelect == 0) {
			return false;
		}
		return false;
	}

	/**
	 * // 至少有一个两连号
	 * 
	 * @param lValues
	 * @return
	 */
	public static boolean isRedIncludeEvenIn(String[] lValues) {
		int tempSelect2 = 0;
		for (int j = 0; j < lValues.length; j++) {
			int rValue = NumberUtils.toInt(lValues[j]);
			int nextValue = (j + 1) < lValues.length ? NumberUtils.toInt(lValues[j + 1]) : -1;
			if (nextValue != -1 && nextValue - rValue == 1) {
				tempSelect2++;
			}
		}
		if (tempSelect2 <LotterySsqConifgService.getHaveTwoSeries()) {
			return false;
		}
		return true;
	}

	/**
	 * // 胆
	 * 
	 * @param lValues
	 * @return
	 */
	public static boolean isRedIncludeRequiredCode(String[] lValues) {
		int tempSelect = 0;
		if (LotterySsqConifgService.getMusthavered() != null && LotterySsqConifgService.getMusthavered().length > 0) {
			for (int i = 0; i < lValues.length; i++) {
				for (int j = 0; j < LotterySsqConifgService.getMusthavered().length; j++) {
					if (StringUtils.equals(lValues[i], ObjectUtils.toString(LotterySsqConifgService.getMusthavered()[j]).trim())) {
						tempSelect++;
					}
				}
			}
			if (tempSelect != LotterySsqConifgService.getMusthavered().length) {
				return false;
			}
		}
		return true;
	}

	/**
	 * // 不能包含其中的任意一个数字
	 * 
	 * @param lValues
	 * @return
	 */
	public static boolean isRedNotIncludeTheCode(String[] lValues) {

		int tempSelect = 0;
		if (LotterySsqConifgService.getExcludeRed() != null && LotterySsqConifgService.getExcludeRed().length > 0) {
			for (int j = 0; j < lValues.length; j++) {
				for (int k = 0; k < LotterySsqConifgService.getExcludeRed().length; k++) {
					if (StringUtils.equals(lValues[j], LotterySsqConifgService.getExcludeRed()[k])) {
						tempSelect++;
						break;
					}
				}
				if (tempSelect > 0) {
					break;
				}
			}
			if (tempSelect > 0) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 在指定的一系列号码中选取6个
	 * @param lValues
	 * @return
	 */
	public static boolean isRedInTheCodes(String[] lValues) {
		if (LotterySsqConifgService.getSelectCode() != null && LotterySsqConifgService.getSelectCode().length > 0) {
			int tempSelect=0;
			for (int i = 0; i < lValues.length; i++) {
				for (int j = 0; j < LotterySsqConifgService.getSelectCode().length; j++) {
					if (StringUtils.equals(lValues[i], ObjectUtils.toString(LotterySsqConifgService.getSelectCode()[j]).trim())) {
						tempSelect++;
					}
				}
			}
			if (tempSelect != 6) {
				return false;
			}
		}
		return true;
	}

	/**
	 * // 不能同时存在的号码
	 * @param lValues
	 * @return
	 */
	public static boolean isRedTogethorCode(String[] lValues) {
		int tempSelect = 0;
		if (LotterySsqConifgService.getCannotSelectedTogethor() != null && LotterySsqConifgService.getCannotSelectedTogethor().length > 0) {
			boolean breakFlag = false;
			for (int j = 0; j < LotterySsqConifgService.getCannotSelectedTogethor().length; j++) {
				String[] tmp = StringUtils.split(LotterySsqConifgService.getCannotSelectedTogethor()[j], ",");
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
			}
			if (breakFlag) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 是否要求包含三连号
	 * @param lValues
	 * @return
	 */
	public static boolean isRedIncludeThreeEvenIn(String[] lValues) {
		if (LotterySsqConifgService.getHaveThreeSeries() > 0) {
			int tempSelect = 0;
			for (int j = 0; j < lValues.length; j++) {
				int rValue = NumberUtils.toInt(lValues[j]);
				int nextValue = (j + 1) < lValues.length ? NumberUtils.toInt(lValues[j + 1]) : -1;
				int nNextValue = (j + 2) < lValues.length ? NumberUtils.toInt(lValues[j + 2]) : -1;
				if (nextValue != -1 && nNextValue != -1 && nextValue - rValue == 1 && nNextValue - nextValue == 1) {
					tempSelect++;
				}
			}
			if (tempSelect < LotterySsqConifgService.getHaveThreeSeries()) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 有几个以上差值为1的
	 * @param lValues
	 * @return
	 */
	public static boolean isRedIncludeDifferCode(String[] lValues) {
		if (LotterySsqConifgService.getHaveOnediffer() > 0) {
			int tempSelect = 0;
			for (int j = 0; j < lValues.length; j++) {
				int rValue = NumberUtils.toInt(lValues[j]);
				int nextValue = (j + 1) < lValues.length ? NumberUtils.toInt(lValues[j + 1]) : -1;
				if (nextValue != -1 && nextValue - rValue == 2) {
					tempSelect++;
				}
			}
			if (tempSelect < LotterySsqConifgService.getHaveOnediffer()) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 媒体一般情况下不会中4个以上的红球，这里排除一下
	 * @param lValues
	 * @param redCodeList
	 * @return
	 */
	public static boolean isRedIncludeMediaFourCode(String[] lValues, List<String[]> redCodeList) {
		int tempSelect = 0;
		for (Iterator<String[]> iterator2 = redCodeList.iterator(); iterator2.hasNext();) {
			String[] mediaRedCode = (String[]) iterator2.next();
			for (int i = 0; i < mediaRedCode.length; i++) {
				for (int j = 0; j < lValues.length; j++) {
					if (StringUtils.equals(mediaRedCode[i].startsWith("0") ? mediaRedCode[i].substring(1) : mediaRedCode[i], lValues[j])) {
						tempSelect++;
					}
				}
			}
			if (tempSelect > 4) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 是否符合在区域的号码分布
	 * @param lValues
	 * @param qOne 
	 * @param qTwo 
	 * @param qThree 
	 * @return
	 */
	public static boolean isRedCoincidenceZone(String[] lValues, int qOne, int qTwo, int qThree) {
		if (LotterySsqConifgService.getQuOne() != -1 && LotterySsqConifgService.getQuTwo() != -1 && LotterySsqConifgService.getQuThree() != -1
				&& (LotterySsqConifgService.getQuOne() + LotterySsqConifgService.getQuTwo() + LotterySsqConifgService.getQuThree()) == 6) {
			if (qOne == LotterySsqConifgService.getQuOne() && qTwo == LotterySsqConifgService.getQuTwo() && qThree == LotterySsqConifgService.getQuThree()) {
				return true;
			}
		} else {
			int[] quTemp = { LotterySsqConifgService.getQuOne(), LotterySsqConifgService.getQuTwo(), LotterySsqConifgService.getQuThree() };
			boolean[] flag = { false, false, false };
			for (int i = 0; i < quTemp.length; i++) {
				if (quTemp[i] != -1) {
					if (i == 0 && quTemp[i] == qOne) {
						flag[i] = true;
					}
					if (i == 1 && quTemp[i] == qTwo) {
						flag[i] = true;
					}
					if (i == 2 && quTemp[i] == qThree) {
						flag[i] = true;
					}
				} else {
					flag[i] = true;
				}
			}
			if (flag[0] && flag[1] && flag[2]) {
				return true;
			}
		}
		return false;
	}
}
