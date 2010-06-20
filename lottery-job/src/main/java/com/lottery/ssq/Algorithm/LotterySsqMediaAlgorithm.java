package com.lottery.ssq.Algorithm;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;

import com.lottery.ssq.LotterySsqFetchConfig;
import com.lottery.ssq.config.LotterySsqFilterConfig;

/**
 * 针对sina媒体使用的算法
 * @author 
 */
public class LotterySsqMediaAlgorithm {
	/**
	 * 与不能同时出现的号码类似，只是这是通过sina擂台网上收集而来的。
	 * 
	 * @param lValues
	 * @param sinaDanList
	 * @return
	 */
	public static boolean isSinaDanTogethorFilter(String[] lValues, List<String> sinaDanList) {
		int tempSelect = 0;
		if (CollectionUtils.isNotEmpty(sinaDanList)) {
			for (int j = 0; j < sinaDanList.size(); j++) {
				String[] tmp = StringUtils.split(ObjectUtils.toString(sinaDanList.get(j)), ",");
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
	 * 其中至少有num注以上不会中一个号码
	 * 
	 * @param lValues
	 * @param sinaDanList
	 * @return
	 */
	public static boolean isSinaDanNoneFilter(String[] lValues, List<String> sinaDanList, int num) {
		int noneNum = sinaDanList.size() - num;
		int tempSelect = 0;
		if (CollectionUtils.isEmpty(sinaDanList)) {
			return true;
		}
		if (CollectionUtils.isNotEmpty(sinaDanList)) {
			tempSelect = 0;
			for (int j = 0; j < sinaDanList.size(); j++) {
				boolean breakFlag = false;
				String[] tmp = StringUtils.split(ObjectUtils.toString(sinaDanList.get(j)), ",");
				for (int k = 0; k < tmp.length; k++) {
					for (int i = 0; i < lValues.length; i++) {
						if (StringUtils.equals(lValues[i], ObjectUtils.toString(tmp[k]).trim())) {
							tempSelect++;
							breakFlag = true;
						}
					}
					if (breakFlag) {
						break;
					}
				}
			}
		}
		if (tempSelect >= noneNum) {
			return false;
		}
		return true;
	}

	/**
	 * 与不能同时出现的号码类似，只是这是通过网上用户收集而来的。 1、一个胆不考虑 2、三个胆的不能全中 3、大于三个的最多只能中3个
	 * 
	 * @param lValues
	 * @param sinaDanList
	 * @return
	 */
	public static boolean isCustomerDanFilter(String[] lValues, List<String> danList) {
		int tempSelect = 0;
		if (CollectionUtils.isNotEmpty(danList)) {
			boolean breakFlag = false;
			for (int j = 0; j < danList.size(); j++) {
				String[] tmp = StringUtils.split(ObjectUtils.toString(danList.get(j)), ",");
				if (tmp.length < 2) {
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
	 * sina 媒体推荐的红胆总和中不能超过4个<=4
	 * 
	 * @param lValues
	 * @param sinaDanList
	 * @return
	 */
	public static boolean isSinaDanAllFilter(String[] lValues, List<String> sinaDanList) {
		Set<String> danSet = new HashSet<String>();
		for (Iterator<String> iterator = sinaDanList.iterator(); iterator.hasNext();) {
			String[] dans = iterator.next().split(",");
			for (int i = 0; i < dans.length; i++) {
				danSet.add(dans[i]);
			}
		}
		String[] danRedCode = danSet.toArray(new String[danSet.size()]);
		int tempSelect = 0;
		for (int i = 0; i < danRedCode.length; i++) {
			for (int j = 0; j < lValues.length; j++) {
				if (StringUtils.equals(danRedCode[i], lValues[j])) {
					tempSelect++;
				}
			}
		}
		if (tempSelect > 4) {
			return false;
		}

		return true;
	}

	/**
	 * 新浪推荐最多有3个推荐会中4个或4个以上
	 * 
	 * @param lValues
	 * @param sinaRedCodeList
	 * @return
	 */
	public static boolean isRedIncludeMediaThreeCode(String[] lValues, List<String[]> sinaRedCodeList) {
		int count = 0;
		for (Iterator<String[]> iterator2 = sinaRedCodeList.iterator(); iterator2.hasNext();) {
			int tempSelect = 0;
			String[] mediaRedCode = (String[]) iterator2.next();
			for (int i = 0; i < mediaRedCode.length; i++) {
				for (int j = 0; j < lValues.length; j++) {
					if (StringUtils.equals(mediaRedCode[i], lValues[j])) {
						tempSelect++;
					}
				}
			}
			if (tempSelect > 3) {
				count++;
			}

		}
		if (count > 1) {
			return false;
		}
		return true;
	}

	/**
	 * 最多只能中其中的一个///只要是新浪推荐的号码中大于等于6的号码
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
		int tempSelect = 0;
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
	 * 必须要中其中的一个号码 选且只选其中一个号码
	 * 
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
			if (tempSelect == 1) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 新浪媒体每注中4个的<3
	 * 
	 * @param lValues
	 * @param mediaSinaList
	 * @return
	 */
	public static boolean isSinaRedCodeXiaoFourFilter(String[] lValues, List<String> mediaSinaList) {
		int count = 0;
		for (String redCode : mediaSinaList) {
			int tempSelect = 0;
			String[] sinaRedCodes = redCode.split(",");
			for (int i = 0; i < sinaRedCodes.length; i++) {
				for (int j = 0; j < lValues.length; j++) {
					if (StringUtils.equals(sinaRedCodes[i], lValues[j])) {
						tempSelect++;
					}
				}
			}
			if (tempSelect > 4) {
				return false;
			}
			if (tempSelect == 4) {
				count++;
			}
		}
		if (count > 2) {
			return false;
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

	/**
	 * 媒体一般情况下不会中4个以上的红球，这里排除一下
	 * 
	 * @param lValues
	 * @param redCodeList
	 * @return
	 */
	public static boolean isRedIncludeFourCode(String[] lValues, List<String[]> redCodeList) {
		if(CollectionUtils.isEmpty(redCodeList)){
			return true;
		}
		for (Iterator<String[]> iterator2 = redCodeList.iterator(); iterator2.hasNext();) {
			int tempSelect = 0;
			String[] mediaRedCode = (String[]) iterator2.next();
			for (int i = 0; i < mediaRedCode.length; i++) {
				for (int j = 0; j < lValues.length; j++) {
					if (StringUtils.equals(mediaRedCode[i], lValues[j])) {
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
}
