package com.lottery.ssq.Algorithm;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;

import com.lottery.ssq.config.LotterySsqConfig;
import com.lottery.ssq.config.LotterySsqFetchConfig;
import com.lottery.ssq.config.LotterySsqFilterConfig;

/**
 * @author Administrator
 */
public class LotterySsqAlgorithm {
	/**
	 * 数字在指定的范围内
	 * 
	 * @param redCode
	 * @return
	 */
	public static boolean isRedNumericInRange(String[] redCode) {
		if (NumberUtils.toInt(redCode[0]) > LotterySsqFilterConfig.firstMaxCode) {
			return false;
		}
		if (NumberUtils.toInt(redCode[0]) < LotterySsqFilterConfig.firstMinCode) {
			return false;
		}
		if (NumberUtils.toInt(redCode[1]) > LotterySsqFilterConfig.secondMaxCode) {
			return false;
		}
		if (NumberUtils.toInt(redCode[1]) < LotterySsqFilterConfig.secondMinCode) {
			return false;
		}
		if (NumberUtils.toInt(redCode[2]) > LotterySsqFilterConfig.thirdMaxCode) {
			return false;
		}
		if (NumberUtils.toInt(redCode[2]) < LotterySsqFilterConfig.thirdMinCode) {
			return false;
		}
		if (NumberUtils.toInt(redCode[3]) > LotterySsqFilterConfig.fourthMaxCode) {
			return false;
		}
		if (NumberUtils.toInt(redCode[3]) < LotterySsqFilterConfig.fourthMinCode) {
			return false;
		}
		if (NumberUtils.toInt(redCode[5]) < LotterySsqFilterConfig.lastMinCode) {
			return false;
		}
		if (NumberUtils.toInt(redCode[5]) > LotterySsqFilterConfig.lastMaxCode) {
			return false;
		}
		return true;
	}

	/**
	 * 是否包含上一期的号码
	 * LotterySsqFilterConfig.includePreRedNum==0号码中不能包含一个上一期的号码
	 * 包含 至少LotterySsqFilterConfig.includePreRedNum个以上的号码
	 * @param lValues
	 * 最多包含zuoduoNum个号码
	 * @return
	 */
	public static boolean isRedIncludePreRedCode(String[] lValues,int zuiduoNum) {
		if(LotterySsqFilterConfig.includePreRedNum==-1){
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
		if(LotterySsqFilterConfig.includePreRedNum==0&&tempSelect==0){
			return true;
		}
		if (LotterySsqFilterConfig.includePreRedNum>0&&tempSelect <= zuiduoNum) {
			return true;
		}
		return false;
	}


	/**
	 * // 必须包含其中的任意一个数字(边号) 并且不能超过2个
	 * 
	 * @param lValues
	 * @return
	 */
	public static boolean isRedIncludeSideCode(String[] lValues,int zuiduoNum) {
		int tempSelect = 0;
		if (LotterySsqConfig.preSideCode == null||LotterySsqFilterConfig.haveSideCode==-1) {
			return true;
		}
		if (LotterySsqConfig.preSideCode.length > 0&&LotterySsqFilterConfig.haveSideCode>0) {
			for (int j = 0; j < lValues.length; j++) {
				for (int k = 0; k < LotterySsqConfig.preSideCode.length; k++) {
					if (StringUtils.equals(lValues[j], LotterySsqConfig.preSideCode[k])) {
						tempSelect++;
					}
				}
			}
			if (tempSelect == 0 || tempSelect >zuiduoNum) {
				return false;
			}
		} else if (LotterySsqFilterConfig.haveSideCode == 0) {
			for (int j = 0; j < lValues.length; j++) {
				for (int k = 0; k < LotterySsqConfig.preSideCode.length; k++) {
					if (StringUtils.equals(lValues[j], LotterySsqConfig.preSideCode[k])) {
						tempSelect++;
					}
				}
			}
			if (tempSelect > 0) {
				return false;
			}
		}
		return true;
	}

	/**
	 * // 至少有一个两连号 0表示不能包含连号 <0表示有无边号都可以
	 * 
	 * @param lValues
	 * @return
	 */
	public static boolean isRedIncludeEvenIn(String[] lValues) {
		if (LotterySsqFilterConfig.haveTwoSeries < 0) {
			return true;
		}
		int tempSelect2 = 0;
		if (LotterySsqFilterConfig.haveTwoSeries == 0) {
			for (int j = 0; j < lValues.length; j++) {
				int rValue = NumberUtils.toInt(lValues[j]);
				int nextValue = (j + 1) < lValues.length ? NumberUtils.toInt(lValues[j + 1]) : -1;
				if (nextValue != -1 && nextValue - rValue == 1) {
					tempSelect2++;
				}
			}
			if (tempSelect2 > LotterySsqFilterConfig.haveTwoSeries) {
				return false;
			}
		} else if (LotterySsqFilterConfig.haveTwoSeries > 0) {
			for (int j = 0; j < lValues.length; j++) {
				int rValue = NumberUtils.toInt(lValues[j]);
				int nextValue = (j + 1) < lValues.length ? NumberUtils.toInt(lValues[j + 1]) : -1;
				if (nextValue != -1 && nextValue - rValue == 1) {
					tempSelect2++;
				}
			}
			if (tempSelect2 < LotterySsqFilterConfig.haveTwoSeries) {
				return false;
			}
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
		if (LotterySsqFilterConfig.musthavered != null && LotterySsqFilterConfig.musthavered.length >= 1) {
			for (int i = 0; i < lValues.length; i++) {
				for (int j = 0; j < LotterySsqFilterConfig.musthavered.length; j++) {
					if (StringUtils.equals(lValues[i], ObjectUtils.toString(LotterySsqFilterConfig.musthavered[j]).trim())) {
						tempSelect++;
					}
				}
			}
			if (tempSelect != LotterySsqFilterConfig.musthavered.length) {
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
		if (LotterySsqFilterConfig.excludeRed != null && LotterySsqFilterConfig.excludeRed.length >= 1) {
			for (int j = 0; j < lValues.length; j++) {
				for (int k = 0; k < LotterySsqFilterConfig.excludeRed.length; k++) {
					if (StringUtils.equals(lValues[j], LotterySsqFilterConfig.excludeRed[k])) {
						tempSelect++;
						break;
					}
				}
				if (tempSelect > 0) {
					return false;
				}
			}
		}
		return true;
	}

	/**
	 * 在指定的一系列号码中选取6个
	 * 
	 * @param lValues
	 * @return
	 */
	public static boolean isRedInTheCodes(String[] lValues) {
		if (LotterySsqFilterConfig.selectCode != null && LotterySsqFilterConfig.selectCode.length >=6) {
			int tempSelect = 0;
			for (int i = 0; i < lValues.length; i++) {
				for (int j = 0; j < LotterySsqFilterConfig.selectCode.length; j++) {
					if (StringUtils.equals(lValues[i], ObjectUtils.toString(LotterySsqFilterConfig.selectCode[j]).trim())) {
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
	 * 
	 * @param lValues
	 * @return
	 */
	public static boolean isRedTogethorCode(String[] lValues) {
		int tempSelect = 0;
		if (LotterySsqFilterConfig.cannotSelectedTogethor != null && LotterySsqFilterConfig.cannotSelectedTogethor.length >= 1) {
			for (int j = 0; j < LotterySsqFilterConfig.cannotSelectedTogethor.length; j++) {
				String[] tmp = StringUtils.split(LotterySsqFilterConfig.cannotSelectedTogethor[j], ",");
				tempSelect = 0;
				for (int k = 0; k < tmp.length; k++) {
					for (int i = 0; i < lValues.length; i++) {
						if (StringUtils.equals(lValues[i], ObjectUtils.toString(tmp[k]).trim())) {
							tempSelect++;
						}
					}
				}
				if (tmp.length == tempSelect) {
					return false;
				}
			}
		}
		return true;
	}

	/**
	 * 是否要求包含三连号 0表示没有,<0表示有没有都可以
	 * 
	 * @param lValues
	 * @return
	 */
	public static boolean isRedIncludeThreeEvenIn(String[] lValues) {
		if (LotterySsqFilterConfig.haveThreeSeries < 0) {
			return true;
		}
		if (LotterySsqFilterConfig.haveThreeSeries > 0) {
			int tempSelect = 0;
			for (int j = 0; j < lValues.length; j++) {
				int rValue = NumberUtils.toInt(lValues[j]);
				int nextValue = (j + 1) < lValues.length ? NumberUtils.toInt(lValues[j + 1]) : -1;
				int nNextValue = (j + 2) < lValues.length ? NumberUtils.toInt(lValues[j + 2]) : -1;
				if (nextValue != -1 && nNextValue != -1 && nextValue - rValue == 1 && nNextValue - nextValue == 1) {
					tempSelect++;
				}
			}
			if (tempSelect < LotterySsqFilterConfig.haveThreeSeries) {
				return false;
			}
		} else {
			int tempSelect = 0;
			for (int j = 0; j < lValues.length; j++) {
				int rValue = NumberUtils.toInt(lValues[j]);
				int nextValue = (j + 1) < lValues.length ? NumberUtils.toInt(lValues[j + 1]) : -1;
				int nNextValue = (j + 2) < lValues.length ? NumberUtils.toInt(lValues[j + 2]) : -1;
				if (nextValue != -1 && nNextValue != -1 && nextValue - rValue == 1 && nNextValue - nextValue == 1) {
					tempSelect++;
				}
			}
			if (tempSelect > LotterySsqFilterConfig.haveThreeSeries) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 有几个以上差值为1的,,,, LotterySsqFilterConfig.getHaveOnediffer()=0表示没有,<0表示有没有都可以
	 * 
	 * @param lValues
	 * @return
	 */
	public static boolean isRedIncludeDifferCode(String[] lValues) {
		if (LotterySsqFilterConfig.haveOnediffer < 0) {
			return true;
		}
		if (LotterySsqFilterConfig.haveOnediffer > 0) {
			int tempSelect = 0;
			for (int j = 0; j < lValues.length; j++) {
				int rValue = NumberUtils.toInt(lValues[j]);
				int nextValue = (j + 1) < lValues.length ? NumberUtils.toInt(lValues[j + 1]) : -1;
				if (nextValue != -1 && nextValue - rValue == 2) {
					tempSelect++;
				}
			}
			if (tempSelect < LotterySsqFilterConfig.haveOnediffer) {
				return false;
			}
		} else {
			int tempSelect = 0;
			for (int j = 0; j < lValues.length; j++) {
				int rValue = NumberUtils.toInt(lValues[j]);
				int nextValue = (j + 1) < lValues.length ? NumberUtils.toInt(lValues[j + 1]) : -1;
				if (nextValue != -1 && nextValue - rValue == 2) {
					tempSelect++;
				}
			}
			if (tempSelect > LotterySsqFilterConfig.haveOnediffer) {
				return false;
			}
		}
		return true;
	}


	/**
	 * 是否符合在区域的号码分布
	 * 
	 * @param lValues
	 * @param qOne
	 * @param qTwo
	 * @param qThree
	 * @return
	 */
	public static boolean isRedCoincidenceZone(String[] lValues, int qOne, int qTwo, int qThree) {
		if (LotterySsqFilterConfig.quOne != -1 && LotterySsqFilterConfig.quTwo != -1 && LotterySsqFilterConfig.quThree != -1 && (LotterySsqFilterConfig.quOne + LotterySsqFilterConfig.quTwo + LotterySsqFilterConfig.quThree) == 6) {
			if (qOne == LotterySsqFilterConfig.quOne && qTwo == LotterySsqFilterConfig.quTwo && qThree == LotterySsqFilterConfig.quThree) {
				return true;
			}
		} else {
			int[] quTemp = { LotterySsqFilterConfig.quOne, LotterySsqFilterConfig.quTwo, LotterySsqFilterConfig.quThree };
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


	/**
	 * 最多只能中其中的一个///如：新浪推荐的号码中大于等于6的号码
	 * 
	 * @param lValues
	 * @param sinaRedCodeList
	 * @return
	 */
	public static boolean isSelectOneCode(String[] lValues) {
		if (LotterySsqFilterConfig.zuiduoSelectedOneCode == null || LotterySsqFilterConfig.zuiduoSelectedOneCode.length < 1) {
			return true;
		}
		for (int i = 0; i < LotterySsqFilterConfig.zuiduoSelectedOneCode.length; i++) {
			int tempSelect = 0;
			String[] code = LotterySsqFilterConfig.zuiduoSelectedOneCode[i].split(",");
			for (int k = 0; k < code.length; k++) {
				for (int j = 0; j < lValues.length; j++) {
					if (StringUtils.equals(code[k], lValues[j])) {
						tempSelect++;
					}
				}
			}
			if (tempSelect > 1) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 至少要中其中的一个号码
	 * 
	 * @param lValues
	 * @return
	 */
	public static boolean isLeastSelectedOneCode(String[] lValues) {
		if (LotterySsqFilterConfig.leastSelectedOneCode == null || LotterySsqFilterConfig.leastSelectedOneCode.length < 1) {
			return true;
		}
		for (int i = 0; i < LotterySsqFilterConfig.leastSelectedOneCode.length; i++) {
			int tempSelect = 0;
			String[] code = LotterySsqFilterConfig.leastSelectedOneCode[i].split(",");
			for (int k = 0; k < code.length; k++) {
				for (int j = 0; j < lValues.length; j++) {
					if (StringUtils.equals(code[k], lValues[j])) {
						tempSelect++;
					}
				}
			}
			if (tempSelect < 1) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 至少要中其中的两个号码
	 * 
	 * @param lValues
	 * @return
	 */
	public static boolean isLeastSelectedTwoCode(String[] lValues, String[] redcodes) {
		if (redcodes == null || redcodes.length < 1) {
			return true;
		}
		int tempSelect=0;
		for (int k = 0; k < redcodes.length; k++) {
			for (int j = 0; j < lValues.length; j++) {
				if (StringUtils.equals(redcodes[k], lValues[j])) {
					tempSelect++;
				}
			}
		}
		if (tempSelect < 2) {
			return false;
		}
		return true;
	}

	/**
	 * 必须要中其中的一个号码
	 * 选且只选其中一个号码
	 * @param lValues
	 * @return
	 */
	public static boolean isMustSelectedOneCode(String[] lValues) {
		if (LotterySsqFilterConfig.mustSelectedOneCode == null || LotterySsqFilterConfig.mustSelectedOneCode.length < 1) {
			return true;
		}
		for (int i = 0; i < LotterySsqFilterConfig.mustSelectedOneCode.length; i++) {
			int tempSelect = 0;
			String[] code = LotterySsqFilterConfig.mustSelectedOneCode[i].split(",");
			for (int k = 0; k < code.length; k++) {
				for (int j = 0; j < lValues.length; j++) {
					if (StringUtils.equals(code[k], lValues[j])) {
						tempSelect++;
					}
				}
			}
			if (tempSelect != 1) {
				return false;
			}
		}
		return true;
	}




	/**
	 * 从文件中读取的号码中不能中四个以上的号码.
	 * 
	 * @param lValues
	 * @param otherRedCodeList
	 * @return
	 */
	public static boolean isFileRedCodeFourFilter(String[] lValues, List<String> otherRedCodeList) {
		if (CollectionUtils.isEmpty(otherRedCodeList)) {
			return true;
		}
		for (String ssq : otherRedCodeList) {
			String[] redCodes = ssq.split(",");
			int tempSelect = 0;
			for (int i = 0; i < redCodes.length; i++) {
				for (int j = 0; j < lValues.length; j++) {
					if (StringUtils.equals(redCodes[i], lValues[j])) {
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
	 * 用户投注的前40个不回中超过4个。
	 * 
	 * @param lValues
	 * @param cRedList
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static boolean isRedFourCodeInCustomerResult(String[] lValues, List cRedList) {
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
			if (selected > 4) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 在redcodes不能中超过4个以上的号码
	 * 
	 * @param lValues
	 * @param redcodes
	 * @return
	 */
	public static boolean isRedFourCodeResult(String[] lValues, String[] redcodes) {
		if (redcodes == null || redcodes.length < 1) {
			return true;
		}
		int selected = 0;
		for (int i = 0; i < redcodes.length; i++) {
			for (int j = 0; j < lValues.length; j++) {
				if (StringUtils.equals(redcodes[i], lValues[j])) {
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

	
}
