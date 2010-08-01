package com.lottery.ssq.Algorithm;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.StringUtils;

import com.lottery.ssq.config.LotterySsqFilterConfig;

/**
 * 针对sina媒体使用的算法
 * 
 * @author
 */
public class LotterySsqMediaAlgorithm {
	/**
	 * 与不能同时出现的号码类似，只是这是通过sina擂台网上收集而来的。
	 * 
	 * @param filterConfig
	 * 
	 * @param lValues
	 * @param sinaDanList
	 * @return
	 */
	public static boolean isSinaDanTogethorFilter(LotterySsqFilterConfig filterConfig, String[] lValues,
			List<String> sinaDanList) {
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
	 * 新浪媒体擂台，媒体推荐的红胆中，中号的不能超过的num个数
	 * 
	 * @param filterConfig
	 * 
	 * @param lValues
	 * @param sinaDanList
	 * @return
	 */
	public static boolean isSinaDanNoneFilter(LotterySsqFilterConfig filterConfig, String[] lValues,
			List<String> sinaDanList, int num) {

		if (CollectionUtils.isEmpty(sinaDanList)) {
			return true;
		}
		int selectedCode = 0;
		if (CollectionUtils.isNotEmpty(sinaDanList)) {
			for (int j = 0; j < sinaDanList.size(); j++) {
				int tempSelect = 0;
				String[] tmp = StringUtils.split(ObjectUtils.toString(sinaDanList.get(j)), ",");
				if(tmp.length<2){
					continue;
				}
				for (int k = 0; k < tmp.length; k++) {
					for (int i = 0; i < lValues.length; i++) {
						if (StringUtils.equals(lValues[i], ObjectUtils.toString(tmp[k]).trim())) {
							tempSelect++;
							break;
						}
					}
					if (tempSelect > 0) {
						break;
					}
				}
				if (tempSelect > 0) {
					selectedCode++;
				}
			}
		}
		if (selectedCode >= num) {
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
	 * sina 媒体推荐的红胆总和中不能超过num个<=num
	 * 
	 * @param filterConfig
	 * 
	 * @param lValues
	 * @param sinaDanList
	 * @param num
	 * @return
	 */
	public static boolean isSinaDanAllFilter(LotterySsqFilterConfig filterConfig, String[] lValues,
			List<String> sinaDanList, int num) {
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
		if (tempSelect > num) {
			return false;
		}

		return true;
	}

	// /**
	// * 最多只能中其中的一个///只要是新浪推荐的号码中大于等于6的号码
	// *
	// * @param lValues
	// * @param sinaRedCodeList
	// * @return
	// * 已验证
	// */
	// public static boolean isSelectOneCode(String[] lValues) {
	// if (LotterySsqFilterConfig.zuiduoSelectedOneCode == null
	// || LotterySsqFilterConfig.zuiduoSelectedOneCode.length < 1) {
	// return true;
	// }
	// for (int i = 0; i < LotterySsqFilterConfig.zuiduoSelectedOneCode.length; i++) {
	// int tempSelect = 0;
	// String[] code = LotterySsqFilterConfig.zuiduoSelectedOneCode[i].split(",");
	// for (int k = 0; k < code.length; k++) {
	// for (int j = 0; j < lValues.length; j++) {
	// if (StringUtils.equals(code[k], lValues[j])) {
	// tempSelect++;
	// }
	// }
	// }
	// if (tempSelect > 1) {
	// return false;
	// }
	// }
	// return true;
	// }
	//
	// /**
	// * 至少要中其中的一个号码
	// *
	// * @param lValues
	// * @return
	// * 已验证
	// */
	// public static boolean isLeastSelectedOneCode(String[] lValues) {
	// if (LotterySsqFilterConfig.leastSelectedOneCode == null
	// || LotterySsqFilterConfig.leastSelectedOneCode.length < 1) {
	// return true;
	// }
	// for (int i = 0; i < LotterySsqFilterConfig.leastSelectedOneCode.length; i++) {
	// int tempSelect = 0;
	// String[] code = LotterySsqFilterConfig.leastSelectedOneCode[i].split(",");
	// for (int k = 0; k < code.length; k++) {
	// for (int j = 0; j < lValues.length; j++) {
	// if (StringUtils.equals(code[k], lValues[j])) {
	// tempSelect++;
	// }
	// }
	// }
	// if (tempSelect < 1) {
	// return false;
	// }
	// }
	// return true;
	// }

	/**
	 * 新浪媒体 推荐红球中4个的媒体不能超过num个
	 * 
	 * @param filterConfig
	 * 
	 * @param lValues
	 * @param mediaSinaList
	 * @return 已验证
	 */
	public static boolean isSinaRedCodeXiaoFourFilter(LotterySsqFilterConfig filterConfig, String[] lValues,
			List<String[]> mediaSinaList, int num) {
		int count = 0;
		for (String[] redCode : mediaSinaList) {
			int tempSelect = 0;
			for (int i = 0; i < redCode.length; i++) {
				for (int j = 0; j < lValues.length; j++) {
					if (StringUtils.equals(redCode[i], lValues[j])) {
						tempSelect++;
					}
				}
			}
			if (tempSelect == 4) {
				count++;
			}
		}
		if (count > num) {
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
	@Deprecated
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
	@Deprecated
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
	 * 用户投注的前40个不回中超过4个。
	 * 
	 * @param lValues
	 * @param cRedList
	 * @return
	 */
	@Deprecated
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
	@Deprecated
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
	@Deprecated
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
	 * @return 已验证
	 */
	public static boolean isRedIncludeFourCode(String[] lValues, Set<String[]> redCodeList) {
		if (CollectionUtils.isEmpty(redCodeList)) {
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

	/**
	 * 新浪擂台媒体不能中4个以上的红球,包括4个. num通常等于=4
	 * 
	 * @param filterConfig
	 * 
	 * @param lValues
	 * @param redCodeList
	 * @return 已验证
	 */
	public static boolean isSinaRedIncludeFourCode(LotterySsqFilterConfig filterConfig, String[] lValues,
			HashSet<String[]> redCodeList, int num) {
		if (CollectionUtils.isEmpty(redCodeList)) {
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
			if (tempSelect > num) {
				return false;
			}

		}
		return true;
	}

	/**
	 * 500万媒体不能中4个以上的红球,包括4个. num通常等于=4
	 * 
	 * @param lValues
	 * @param wan500RedCodeList
	 * @param num
	 * @return 已验证
	 */
	public static boolean isWan500RedIncludeFourCode(String[] lValues, Set<String[]> wan500RedCodeList, int num) {
		if (CollectionUtils.isEmpty(wan500RedCodeList)) {
			return true;
		}
		for (Iterator<String[]> iterator2 = wan500RedCodeList.iterator(); iterator2.hasNext();) {
			int tempSelect = 0;
			String[] mediaRedCode = (String[]) iterator2.next();
			for (int i = 0; i < mediaRedCode.length; i++) {
				for (int j = 0; j < lValues.length; j++) {
					if (StringUtils.equals(mediaRedCode[i], lValues[j])) {
						tempSelect++;
					}
				}
			}
			if (tempSelect > num) {
				return false;
			}

		}
		return true;
	}

	/**
	 * 新浪媒体推荐中至少有num注会一个号码也不中.
	 * 
	 * @param filterConfig
	 * @param lValues
	 * @param sinaRedCodeList
	 * @param int1
	 * @return
	 */
	public static boolean isSinaRedCodeNodeSelected(LotterySsqFilterConfig filterConfig, String[] lValues,
			List<String[]> sinaRedCodeList, int num) {
		if (num < 1) {
			return true;
		}
		int zeroSelected = 0;
		for (String[] sinaRedCode : sinaRedCodeList) {
			int selected = 0;
			for (String sinaSredCode : sinaRedCode) {
				for (String sRedCode : lValues) {
					if (StringUtils.equals(sinaSredCode, sRedCode)) {
						selected++;
					}
				}
			}
			if (selected == 0) {
				zeroSelected++;
			}
		}
		if (zeroSelected >= num) {
			return true;
		}
		return false;
	}

	/**
	 * 从网站收集的红球不能中4各号码以上 .
	 * @param lValues
	 * @param webRedCodeList
	 * @return
	 */
	public static boolean isFilterWebFourCode(String[] lValues, Set<String> webRedCodeList) {
		if(CollectionUtils.isEmpty(webRedCodeList)){
			return true;
		}
		for(String redCode:webRedCodeList){
			int selected=0;
			String[] redCodes=StringUtils.split(redCode,",");
			for(String code:redCodes)
			{
				for(String value:lValues){
					if(StringUtils.equals(code, value)){
						selected++;
					}
				}
			}
			if(selected>4){
				return false;
			}
		}
		return true;
	}
}
