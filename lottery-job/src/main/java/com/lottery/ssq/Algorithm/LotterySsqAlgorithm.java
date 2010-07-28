package com.lottery.ssq.Algorithm;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;

import com.lottery.ssq.config.LotterySsqConfig;
import com.lottery.ssq.config.LotterySsqFilterConfig;

/**
 * @author Administrator
 */
public class LotterySsqAlgorithm {
	/**
	 * 数字在指定的范围内
	 * 
	 * @param filterConfig
	 * @param redCode
	 * @return
	 */
	public static boolean isRedNumericInRange(LotterySsqFilterConfig filterConfig, String[] redCode) {
		if(redCode==null||redCode.length!=6){
			return false;
		}
		if (NumberUtils.toInt(redCode[0]) > filterConfig.getFirstMaxCode()) {
			return false;
		}
		if (NumberUtils.toInt(redCode[0]) < filterConfig.getFirstMinCode()) {
			return false;
		}
		if (NumberUtils.toInt(redCode[1]) > filterConfig.getSecondMaxCode()) {
			return false;
		}
		if (NumberUtils.toInt(redCode[1]) < filterConfig.getSecondMinCode()) {
			return false;
		}
		if (NumberUtils.toInt(redCode[2]) > filterConfig.getThirdMaxCode()) {
			return false;
		}
		if (NumberUtils.toInt(redCode[2]) < filterConfig.getThirdMinCode()) {
			return false;
		}
		if (NumberUtils.toInt(redCode[3]) > filterConfig.getFourthMaxCode()) {
			return false;
		}
		if (NumberUtils.toInt(redCode[3]) < filterConfig.getFourthMinCode()) {
			return false;
		}
		if (NumberUtils.toInt(redCode[5]) < filterConfig.getLastMinCode()) {
			return false;
		}
		if (NumberUtils.toInt(redCode[5]) > filterConfig.getLastMaxCode()) {
			return false;
		}
		return true;
	}
	/**
	 * 一区的最多个数不能超过<num个
	 * <p/>
	 * 二区的最多个数不能超过<num个
	 * <p/>
	 * 三区的最多个数不能超过<num个
	 * @return
	 */
	public static boolean isQuNum(LotterySsqFilterConfig filterConfig, String[] lValues){

		int qOne = 0;
		int qTwo = 0;
		int qThree = 0;
		for (int i = 0; i < lValues.length; i++) {
			if (NumberUtils.toInt(lValues[i]) <= 11) {
				qOne++;
			}
			if (NumberUtils.toInt(lValues[i]) > 11 && NumberUtils.toInt(lValues[i]) <= 22) {
				qTwo++;
			}
			if (NumberUtils.toInt(lValues[i]) > 22 && NumberUtils.toInt(lValues[i]) <= 33) {
				qThree++;
			}
		}
		if(filterConfig.getOneQuNum()>0){
			if(qOne>=filterConfig.getOneQuNum()){
				return false;
			}
		}
		if(filterConfig.getTwoQuNum()>0){
			if(qTwo>=filterConfig.getTwoQuNum()){
				return false;
			}
		}
		if(filterConfig.getThreeQuNum()>0){
			if(qThree>=filterConfig.getThreeQuNum()){
				return false;
			}
		}
		return true;
	}
	/**
	 * 是否包含上一期的号码 filterConfig.getIncludePreRedNum()==0号码中不能包含一个上一期的号码 包含 至少filterConfig.getIncludePreRedNum()个以上的号码
	 * 
	 * @param filterConfig
	 * @param lValues 最多包含zuoduoNum个号码
	 * @return
	 */
	public static boolean isRedIncludePreRedCode(LotterySsqFilterConfig filterConfig, String[] lValues, int zuiduoNum) {
		if (filterConfig.getIncludePreRedNum() == -1) {
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
		if (filterConfig.getIncludePreRedNum() == 0 && tempSelect == 0) {
			return true;
		}
		if (filterConfig.getIncludePreRedNum() > 0 && tempSelect <= zuiduoNum) {
			return true;
		}
		return false;
	}

	/**
	 * // 必须包含其中的任意一个数字(边号) 并且不能超过2个
	 * 
	 * @param filterConfig
	 * @param lValues
	 * @return
	 */
	public static boolean isRedIncludeSideCode(LotterySsqFilterConfig filterConfig, String[] lValues, int zuiduoNum) {
		int tempSelect = 0;
		if (LotterySsqConfig.preSideCode == null || filterConfig.getHaveSideCode() == -1) {
			return true;
		}
		if (LotterySsqConfig.preSideCode.length > 0 && filterConfig.getHaveSideCode() > 0) {
			for (int j = 0; j < lValues.length; j++) {
				for (int k = 0; k < LotterySsqConfig.preSideCode.length; k++) {
					if (StringUtils.equals(lValues[j], LotterySsqConfig.preSideCode[k])) {
						tempSelect++;
					}
				}
			}
			if (tempSelect == 0 || tempSelect > zuiduoNum) {
				return false;
			}
		} else if (filterConfig.getHaveSideCode() == 0) {
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
	 * @param filterConfig
	 * @param lValues
	 * @return
	 */
	public static boolean isRedIncludeEvenIn(LotterySsqFilterConfig filterConfig, String[] lValues) {
		if (filterConfig.getHaveTwoSeries() < 0) {
			return true;
		}
		int tempSelect2 = 0;
		if (filterConfig.getHaveTwoSeries() == 0) {
			for (int j = 0; j < lValues.length; j++) {
				int rValue = NumberUtils.toInt(lValues[j]);
				int nextValue = (j + 1) < lValues.length ? NumberUtils.toInt(lValues[j + 1]) : -1;
				if (nextValue != -1 && nextValue - rValue == 1) {
					tempSelect2++;
				}
			}
			if (tempSelect2 > filterConfig.getHaveTwoSeries()) {
				return false;
			}
		} else if (filterConfig.getHaveTwoSeries() > 0) {
			for (int j = 0; j < lValues.length; j++) {
				int rValue = NumberUtils.toInt(lValues[j]);
				int nextValue = (j + 1) < lValues.length ? NumberUtils.toInt(lValues[j + 1]) : -1;
				if (nextValue != -1 && nextValue - rValue == 1) {
					tempSelect2++;
				}
			}
			if (tempSelect2 < filterConfig.getHaveTwoSeries()) {
				return false;
			}
		}
		return true;
	}

	/**
	 * // 胆
	 * 
	 * @param filterConfig
	 * @param lValues
	 * @return
	 */
	public static boolean isRedIncludeRequiredCode(LotterySsqFilterConfig filterConfig, String[] lValues) {
		int tempSelect = 0;
		if (filterConfig.getMusthavered() != null && filterConfig.getMusthavered().length >= 1) {
			for (int i = 0; i < lValues.length; i++) {
				for (int j = 0; j < filterConfig.getMusthavered().length; j++) {
					if (StringUtils.equals(lValues[i], ObjectUtils.toString(filterConfig.getMusthavered()[j]).trim())) {
						tempSelect++;
					}
				}
			}
			if (tempSelect != filterConfig.getMusthavered().length) {
				return false;
			}
		}
		return true;
	}

	/**
	 * // 不能包含其中的任意一个数字
	 * 
	 * @param filterConfig
	 * @param lValues
	 * @return
	 */
	public static boolean isRedNotIncludeTheCode(LotterySsqFilterConfig filterConfig, String[] lValues) {

		int tempSelect = 0;
		if (filterConfig.getExcludeRed() != null && filterConfig.getExcludeRed().length >= 1) {
			for (int j = 0; j < lValues.length; j++) {
				for (int k = 0; k < filterConfig.getExcludeRed().length; k++) {
					if (StringUtils.equals(lValues[j], filterConfig.getExcludeRed()[k])) {
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
	 * @param filterConfig
	 * @param lValues
	 * @return
	 */
	public static boolean isRedInTheCodes(LotterySsqFilterConfig filterConfig, String[] lValues) {
		if (filterConfig.getSelectCode() != null && filterConfig.getSelectCode().length >= 6) {
			int tempSelect = 0;
			for (int i = 0; i < lValues.length; i++) {
				for (int j = 0; j < filterConfig.getSelectCode().length; j++) {
					if (StringUtils.equals(lValues[i], ObjectUtils.toString(filterConfig.getSelectCode()[j]).trim())) {
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
	 * @param filterConfig
	 * @param lValues
	 * @return
	 */
	public static boolean isRedTogethorCode(LotterySsqFilterConfig filterConfig, String[] lValues) {
		int tempSelect = 0;
		if (filterConfig.getCannotSelectedTogethor() != null && filterConfig.getCannotSelectedTogethor().length >= 1) {
			for (int j = 0; j < filterConfig.getCannotSelectedTogethor().length; j++) {
				String[] tmp = StringUtils.split(filterConfig.getCannotSelectedTogethor()[j], ",");
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
	 * @param filterConfig
	 * @param lValues
	 * @return
	 */
	public static boolean isRedIncludeThreeEvenIn(LotterySsqFilterConfig filterConfig, String[] lValues) {
		if (filterConfig.getHaveThreeSeries() < 0) {
			return true;
		}
		if (filterConfig.getHaveThreeSeries() > 0) {
			int tempSelect = 0;
			for (int j = 0; j < lValues.length; j++) {
				int rValue = NumberUtils.toInt(lValues[j]);
				int nextValue = (j + 1) < lValues.length ? NumberUtils.toInt(lValues[j + 1]) : -1;
				int nNextValue = (j + 2) < lValues.length ? NumberUtils.toInt(lValues[j + 2]) : -1;
				if (nextValue != -1 && nNextValue != -1 && nextValue - rValue == 1 && nNextValue - nextValue == 1) {
					tempSelect++;
				}
			}
			if (tempSelect < filterConfig.getHaveThreeSeries()) {
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
			if (tempSelect > filterConfig.getHaveThreeSeries()) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 有几个以上差值为1的,,,, LotterySsqFilterConfig.getHaveOnediffer()=0表示没有,<0表示有没有都可以
	 * 
	 * @param filterConfig
	 * @param lValues
	 * @return
	 */
	public static boolean isRedIncludeDifferCode(LotterySsqFilterConfig filterConfig, String[] lValues) {
		if (filterConfig.getHaveOnediffer() < 0) {
			return true;
		}
		if (filterConfig.getHaveOnediffer() > 0) {
			int tempSelect = 0;
			for (int j = 0; j < lValues.length; j++) {
				int rValue = NumberUtils.toInt(lValues[j]);
				int nextValue = (j + 1) < lValues.length ? NumberUtils.toInt(lValues[j + 1]) : -1;
				if (nextValue != -1 && nextValue - rValue == 2) {
					tempSelect++;
				}
			}
			if (tempSelect < filterConfig.getHaveOnediffer()) {
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
			if (tempSelect > filterConfig.getHaveOnediffer()) {
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
	public static boolean isRedCoincidenceZone(LotterySsqFilterConfig filterConfig, String[] lValues, int qOne, int qTwo, int qThree) {
		if (filterConfig.getQuOne() != -1 && filterConfig.getQuTwo() != -1 && filterConfig.getQuThree() != -1 && (filterConfig.getQuOne() + filterConfig.getQuTwo() + filterConfig.getQuThree()) == 6) {
			if (qOne == filterConfig.getQuOne() && qTwo == filterConfig.getQuTwo() && qThree == filterConfig.getQuThree()) {
				return true;
			}
		} else {
			int[] quTemp = { filterConfig.getQuOne(), filterConfig.getQuTwo(), filterConfig.getQuThree() };
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
	 * @param filterConfig
	 * @param lValues
	 * @param sinaRedCodeList
	 * @return
	 */
	public static boolean isSelectOneCode(LotterySsqFilterConfig filterConfig, String[] lValues) {
		if (filterConfig.getZuiduoSelectedOneCode() == null || filterConfig.getZuiduoSelectedOneCode().length < 1) {
			return true;
		}
		for (int i = 0; i < filterConfig.getZuiduoSelectedOneCode().length; i++) {
			int tempSelect = 0;
			String[] code = filterConfig.getZuiduoSelectedOneCode()[i].split(",");
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
	 * @param filterConfig
	 * @param lValues
	 * @return
	 */
	public static boolean isLeastSelectedOneCode(LotterySsqFilterConfig filterConfig, String[] lValues) {
		if (filterConfig.getLeastSelectedOneCode() == null || filterConfig.getLeastSelectedOneCode().length < 1) {
			return true;
		}
		for (int i = 0; i < filterConfig.getLeastSelectedOneCode().length; i++) {
			int tempSelect = 0;
			String[] code = filterConfig.getLeastSelectedOneCode()[i].split(",");
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
	 * @return 已验证
	 */
	public static boolean isLeastSelectedTwoCode(String[] lValues, String[] redcodes) {
		if (redcodes == null || redcodes.length < 1) {
			return true;
		}
		for (String code : redcodes) {
			String[] codes = StringUtils.split(code, ",");
			int tempSelect = 0;
			for (int k = 0; k < codes.length; k++) {
				for (int j = 0; j < lValues.length; j++) {
					if (StringUtils.equals(codes[k], lValues[j])) {
						tempSelect++;
					}
				}
			}
			if (tempSelect < 2) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 必须要中其中的一个号码 选且只选其中一个号码
	 * 
	 * @param filterConfig
	 * @param lValues
	 * @return 已验证
	 */
	public static boolean isMustSelectedOneCode(LotterySsqFilterConfig filterConfig, String[] lValues) {
		if (filterConfig.getMustSelectedOneCode() == null || filterConfig.getMustSelectedOneCode().length < 1) {
			return true;
		}
		for (int i = 0; i < filterConfig.getMustSelectedOneCode().length; i++) {
			int tempSelect = 0;
			String[] code = filterConfig.getMustSelectedOneCode()[i].split(",");
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
	 * 必须选择其中的两个号码
	 * 
	 * @param filterConfig
	 * @param lValues
	 * @return
	 */
	public static boolean mustSelectedTwoRedCode(LotterySsqFilterConfig filterConfig, String[] lValues) {
		if (filterConfig.getMustSelectedTwoCode() == null || filterConfig.getMustSelectedTwoCode().length == 0) {
			return true;
		}
		for (int j = 0; j < filterConfig.getMustSelectedTwoCode().length; j++) {
			int selectedCode = 0;
			String[] tmpCode = StringUtils.split(filterConfig.getMustSelectedTwoCode()[j], ",");
			for (String code : tmpCode) {
				for (int i = 0; i < lValues.length; i++) {
					if (StringUtils.equals(lValues[i], code)) {
						selectedCode++;
					}
				}
			}
			if (selectedCode != 2) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 至少选择其中的两个号码
	 * 
	 * @param filterConfig
	 * @param lValues
	 * @return
	 */
	public static boolean leastSelectedTwoRedCode(LotterySsqFilterConfig filterConfig, String[] lValues) {
		if (filterConfig.getLeastSelectedTwoCode() == null || filterConfig.getLeastSelectedTwoCode().length == 0) {
			return true;
		}
		for (int j = 0; j < filterConfig.getLeastSelectedTwoCode().length; j++) {
			int selectedCode = 0;
			String[] tmpTwoCode = StringUtils.split(filterConfig.getLeastSelectedTwoCode()[j], ",");
			for (String code : tmpTwoCode) {
				for (int i = 0; i < lValues.length; i++) {
					if (StringUtils.equals(lValues[i], code)) {
						selectedCode++;
					}
				}
			}
			if (selectedCode < 2) {
				return false;
			}
		}

		return true;
	}

	/**
	 * 至少选中其中三个号码
	 * 
	 * @param filterConfig
	 * @param lValues
	 * @return
	 */
	public static boolean leastSelectedThreeRedCode(LotterySsqFilterConfig filterConfig, String[] lValues) {
		if (filterConfig.getLeastSelectedThreeCode() == null || filterConfig.getLeastSelectedThreeCode().length == 0) {
			return true;
		}
		for (int j = 0; j < filterConfig.getLeastSelectedThreeCode().length; j++) {
			int selectedCode = 0;
			String[] tmpCode = StringUtils.split(filterConfig.getLeastSelectedThreeCode()[j], ",");
			for (String code : tmpCode) {
				for (int i = 0; i < lValues.length; i++) {
					if (StringUtils.equals(lValues[i], code)) {
						selectedCode++;
					}
				}
			}
			if (selectedCode < 2) {
				return false;
			}
		}

		return true;
	}

	/**
	 * 三个尾数相同的号码
	 * 
	 * @param filterConfig
	 * @param lValues
	 * @return
	 */
	public static boolean mantissaThreeSame(LotterySsqFilterConfig filterConfig, String[] lValues) {
		if (filterConfig.getMantissaThreeSame() == -1) {
			return true;
		}
		for (int i = 0; i < lValues.length; i++) {
			int selectedCode = 0;
			int tmp1 = NumberUtils.toInt(StringUtils.substring(lValues[i], 1));
			for (int j = 0; j < lValues.length; j++) {
				int tmp2 = NumberUtils.toInt(StringUtils.substring(lValues[j], 1));
				if (tmp1 == tmp2) {
					selectedCode++;
				}
			}
			if (selectedCode >= 3) {
				return false;
			}
		}

		return true;
	}

	/**
	 * 三个以上包含三个2倍数的号码
	 * 
	 * @param filterConfig
	 * @param lValues
	 * @return
	 */
	public static boolean haveThree2Multiple(LotterySsqFilterConfig filterConfig, String[] lValues) {
		if (filterConfig.getHaveThree2Multiple() == -1) {
			return true;
		}
		for (int i = 0; i < lValues.length; i++) {
			int selectedCode = 0;
			int tmp = NumberUtils.toInt(lValues[i]);
			int tmp1 = tmp * 2;
			int tmp2 = tmp * 4;
			for (int j = 0; j < lValues.length; j++) {
				if (tmp1 == NumberUtils.toInt(lValues[j])) {
					selectedCode++;
				}
				if (tmp2 == NumberUtils.toInt(lValues[j])) {
					selectedCode++;
				}
			}
			if (selectedCode >= 2) {
				return false;
			}
		}
		return true;

	}

	/**
	 * 三个以上包含三个3倍数的号码
	 * 
	 * @param filterConfig
	 * @param lValues
	 * @return
	 */
	public static boolean haveThree3Multiple(LotterySsqFilterConfig filterConfig, String[] lValues) {
		if (filterConfig.getHaveThree3Multiple() == -1) {
			return true;
		}
		for (int i = 0; i < lValues.length; i++) {
			int selectedCode = 0;
			int tmp = NumberUtils.toInt(lValues[i]);
			int tmp1 = tmp * 3;
			int tmp2 = tmp * 9;
			for (int j = 0; j < lValues.length; j++) {
				if (tmp1 == NumberUtils.toInt(lValues[j])) {
					selectedCode++;
				}
				if (tmp2 == NumberUtils.toInt(lValues[j])) {
					selectedCode++;
				}
			}
			if (selectedCode >= 2) {
				return false;
			}
		}
		return true;

	}

	/**
	 * 连续4个奇数或偶数
	 * 
	 * @param filterConfig
	 * @param lValues
	 * @return
	 */
	public static boolean continueFourOddOreven(LotterySsqFilterConfig filterConfig, String[] lValues) {
		if (filterConfig.getContinueFourOddOreven() == -1) {
			return true;
		}
		int selectedCode = 0;
		for (int i = 0; i < lValues.length; i++) {
			int tmp = NumberUtils.toInt(lValues[i]);
			if (tmp % 2 == 0) {
				selectedCode++;
			} else {
				selectedCode = 0;
			}
		}
		if (selectedCode >= 4) {
			return false;
		}
		selectedCode = 0;
		for (int i = 0; i < lValues.length; i++) {
			int tmp = NumberUtils.toInt(lValues[i]);
			if (tmp % 2 == 1) {
				selectedCode++;
			} else {
				selectedCode = 0;
			}
		}
		if (selectedCode >= 4) {
			return false;
		}
		return true;
	}

	/**
	 * 5个以上的奇数或偶数包括5个
	 * 
	 * @param filterConfig
	 * @param lValues
	 * @return
	 */
	public static boolean geFiveOddOrEven(LotterySsqFilterConfig filterConfig, String[] lValues) {
		if (filterConfig.getGeFiveOddOrEven() == -1) {
			return true;
		}
		int selectedCode = 0;
		for (int i = 0; i < lValues.length; i++) {
			int tmp = NumberUtils.toInt(lValues[i]);
			if (tmp % 2 == 0) {
				selectedCode++;
			}
		}
		if (selectedCode >= 5) {
			return false;
		}
		selectedCode = 0;
		for (int i = 0; i < lValues.length; i++) {
			int tmp = NumberUtils.toInt(lValues[i]);
			if (tmp % 2 == 1) {
				selectedCode++;
			}
		}
		if (selectedCode >= 5) {
			return false;
		}
		return true;
	}

	/**
	 * 最原始的号码过滤方法, 一个区不能有超过4个的号码 不能有>4的连号 不能有3个三连号 不能同时存在三个差值为1或2的 如果号码分布在三个区， 那么三个区的号码差值不能相同 TODO 六个数之间的5差值不能相等有<=4个差值以上
	 * 
	 * @param redCode
	 * @return
	 */
	public static String initFilterRedCode(String redCode) {
		String[] codeSix = redCode.split(",");

		int qOne = 0;
		int qTwo = 0;
		int qThree = 0;
		for (int i = 0; i < codeSix.length; i++) {
			if (NumberUtils.toInt(codeSix[i]) <= 11) {
				qOne++;
			}
			if (NumberUtils.toInt(codeSix[i]) > 11 && NumberUtils.toInt(codeSix[i]) <= 22) {
				qTwo++;
			}
			if (NumberUtils.toInt(codeSix[i]) > 22 && NumberUtils.toInt(codeSix[i]) <= 33) {
				qThree++;
			}
		}

		if (qOne == 0 || qTwo == 0 || qThree == 0) {
			return null;
		}
		if (qOne >= 4 || qTwo >= 4 || qThree >= 4) {
			return null;
		}
		boolean isReturn = false;
		int lhCode = 0;
		int one = NumberUtils.toInt(codeSix[0]);
		int two = NumberUtils.toInt(codeSix[1]);
		int three = NumberUtils.toInt(codeSix[2]);
		int four = NumberUtils.toInt(codeSix[3]);
		int five = NumberUtils.toInt(codeSix[4]);
		int six = NumberUtils.toInt(codeSix[5]);

		if (two - one == 1) {
			lhCode++;
		}
		if (three - two == 1) {
			lhCode++;
		}
		if (four - three == 1) {
			lhCode++;
		}
		if (five - four == 1) {
			lhCode++;
		}
		if (six - five == 1) {
			lhCode++;
		}
		if (lhCode >= 3) {
			return null;
		}
		int czCode = 0;
		if (two - one == 2) {
			czCode++;
		}
		if (three - two == 2) {
			czCode++;
		}
		if (four - three == 2) {
			czCode++;
		}
		if (five - four == 2) {
			czCode++;
		}
		if (six - five == 2) {
			czCode++;
		}
		if (czCode >= 3) {
			return null;
		}
		czCode = 0;
		if (two - one == 3) {
			czCode++;
		}
		if (three - two == 3) {
			czCode++;
		}
		if (four - three == 3) {
			czCode++;
		}
		if (five - four == 3) {
			czCode++;
		}
		if (six - five == 3) {
			czCode++;
		}
		if (czCode >= 3) {
			return null;
		}
		czCode = 0;
		int cz1 = two - one;
		int cz2 = three - two;
		int cz3 = four - three;
		int cz4 = five - four;
		int cz5 = six - five;
		int[] czs = { cz1, cz2, cz3, cz4, cz5 };
		for (int i = 0; i < 5; i++) {
			int tmpCount = 0;
			int tmp = czs[i];
			for (int j = 0; j < 5; j++) {
				if (tmp == czs[j]) {
					tmpCount++;
				}
			}
			if (tmpCount >= 4) {
				return null;
			}
		}
		if (czCode >= 3) {
			return null;
		}
		if (qOne == 2 && qTwo == 2 && qThree == 2 && ((two - one) == (four - three) && (four - three) == (six - five))) {
			return null;
		}
		if (isReturn) {
			return null;
		}
		return redCode;
	}

	/**
	 * @param redCode
	 * @return
	 */
	public static String filterFromCollectRedCode(String redCode) {
		String[] codeSix = redCode.split(",");

		int qOne = 0;
		int qTwo = 0;
		int qThree = 0;
		for (int i = 0; i < codeSix.length; i++) {
			if (NumberUtils.toInt(codeSix[i]) <= 11) {
				qOne++;
			}
			if (NumberUtils.toInt(codeSix[i]) > 11 && NumberUtils.toInt(codeSix[i]) <= 22) {
				qTwo++;
			}
			if (NumberUtils.toInt(codeSix[i]) > 22 && NumberUtils.toInt(codeSix[i]) <= 33) {
				qThree++;
			}
		}

		// if (qOne == 0 || qTwo == 0 || qThree == 0) {
		// return null;
		// }
		// if (qOne >= 4 || qTwo >= 4 || qThree >= 4) {
		// return null;
		// }
		boolean isReturn = false;
		int lhCode = 0;
		int one = NumberUtils.toInt(codeSix[0]);
		int two = NumberUtils.toInt(codeSix[1]);
		int three = NumberUtils.toInt(codeSix[2]);
		int four = NumberUtils.toInt(codeSix[3]);
		int five = NumberUtils.toInt(codeSix[4]);
		int six = NumberUtils.toInt(codeSix[5]);

		if (two - one == 1) {
			lhCode++;
		}
		if (three - two == 1) {
			lhCode++;
		}
		if (four - three == 1) {
			lhCode++;
		}
		if (five - four == 1) {
			lhCode++;
		}
		if (six - five == 1) {
			lhCode++;
		}
		if (lhCode >= 3) {
			return null;
		}
		int czCode = 0;
		if (two - one == 2) {
			czCode++;
		}
		if (three - two == 2) {
			czCode++;
		}
		if (four - three == 2) {
			czCode++;
		}
		if (five - four == 2) {
			czCode++;
		}
		if (six - five == 2) {
			czCode++;
		}
		if (czCode >= 3) {
			return null;
		}
		czCode = 0;
		if (two - one == 3) {
			czCode++;
		}
		if (three - two == 3) {
			czCode++;
		}
		if (four - three == 3) {
			czCode++;
		}
		if (five - four == 3) {
			czCode++;
		}
		if (six - five == 3) {
			czCode++;
		}
		if (czCode >= 3) {
			return null;
		}
		czCode = 0;
		int cz1 = two - one;
		int cz2 = three - two;
		int cz3 = four - three;
		int cz4 = five - four;
		int cz5 = six - five;
		int[] czs = { cz1, cz2, cz3, cz4, cz5 };
		for (int i = 0; i < 5; i++) {
			int tmpCount = 0;
			int tmp = czs[i];
			for (int j = 0; j < 5; j++) {
				if (tmp == czs[j]) {
					tmpCount++;
				}
			}
			if (tmpCount >= 4) {
				return null;
			}
		}
		if (czCode >= 3) {
			return null;
		}
		if (qOne == 2 && qTwo == 2 && qThree == 2 && ((two - one) == (four - three) && (four - three) == (six - five))) {
			return null;
		}
		if (isReturn) {
			return null;
		}
		return redCode;
	}
}
