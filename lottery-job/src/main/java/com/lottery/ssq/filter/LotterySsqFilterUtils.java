package com.lottery.ssq.filter;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;

import com.lottery.ssq.Algorithm.LotterySsqAlgorithm;

public class LotterySsqFilterUtils {
	/**
	 * 基本过滤方法
	 * 
	 * @param methodName
	 * @param arg
	 * @param lValues
	 * @return
	 */
	public static boolean standardFilterMethod(String methodName, String arg, String[] lValues) {

		String[] args = StringUtils.split(arg, "|");
		if ("isRedNumericInRange".equals(methodName)) {
			// 数字的范围
			if (!LotterySsqAlgorithm.isRedNumericInRange(lValues)) {
				return false;
			}
		}
		if ("isRedIncludeRequiredCode".equals(methodName)) {
			// 是否需要胆
			if (!LotterySsqAlgorithm.isRedIncludeRequiredCode(lValues)) {
				return false;
			}
		}
		if ("isRedIncludePreRedCode".equals(methodName)) {
			// 是否包含上一期的号码
			if (!LotterySsqAlgorithm.isRedIncludePreRedCode(lValues, NumberUtils.toInt(args[0]))) {
				return false;
			}
		}
		if ("isRedIncludeSideCode".equals(methodName)) {
			// 是否包括边号
			if (!LotterySsqAlgorithm.isRedIncludeSideCode(lValues, NumberUtils.toInt(args[0]))) {
				return false;
			}
		}
		if ("isRedNotIncludeTheCode".equals(methodName)) {
			// 不能出现的号码
			if (!LotterySsqAlgorithm.isRedNotIncludeTheCode(lValues)) {
				return false;
			}
		}
		if ("isRedInTheCodes".equals(methodName)) {
			// 在指定的一系列号码中选取6个
			if (!LotterySsqAlgorithm.isRedInTheCodes(lValues)) {
				return false;
			}
		}
		if ("isRedTogethorCode".equals(methodName)) {
			// 不能同时出现的号码多组用"|"分割
			if (!LotterySsqAlgorithm.isRedTogethorCode(lValues)) {
				return false;
			}
		}
		if ("isRedIncludeEvenIn".equals(methodName)) {
			// 是否包含两连号
			if (!LotterySsqAlgorithm.isRedIncludeEvenIn(lValues)) {
				return false;
			}
		}
		if ("isRedIncludeThreeEvenIn".equals(methodName)) {
			// 是否包含三连号
			if (!LotterySsqAlgorithm.isRedIncludeThreeEvenIn(lValues)) {
				return false;
			}
		}
		if ("isRedIncludeDifferCode".equals(methodName)) {
			// 是否包含隔号
			if (!LotterySsqAlgorithm.isRedIncludeDifferCode(lValues)) {
				return false;
			}
		}
		if ("isLeastSelectedOneCode".equals(methodName)) {
			// 至少中其中的一个号码
			if (!LotterySsqAlgorithm.isLeastSelectedOneCode(lValues)) {
				return false;
			}
		}
		if ("isMustSelectedOneCode".equals(methodName)) {
			// 必须选择一个/是并的关系而不是或得关系
			if (!LotterySsqAlgorithm.isMustSelectedOneCode(lValues)) {
				return false;
			}
		}
		if ("isSelectOneCode".equals(methodName)) {
			// 最多只能其中的一个号码
			if (!LotterySsqAlgorithm.isSelectOneCode(lValues)) {
				return false;
			}
		}
		if ("isSelectOneCode".equals(methodName)) {
			// 最多只能其中的一个号码
			if (!LotterySsqAlgorithm.isSelectOneCode(lValues)) {
				return false;
			}
		}
		// 必须选其中的两个
		if ("mustSelectedTwoRedCode".equals(methodName)) {
			if (!LotterySsqAlgorithm.mustSelectedTwoRedCode(lValues)) {
				return false;
			}
		}
		// 至少选其中的两个.
		if ("leastSelectedTwoRedCode".equals(methodName)) {
			if (!LotterySsqAlgorithm.leastSelectedTwoRedCode(lValues)) {
				return false;
			}
		}
		// 至少选其中的三个
		if ("leastSelectedThreeRedCode".equals(methodName)) {
			if (!LotterySsqAlgorithm.leastSelectedThreeRedCode(lValues)) {
				return false;
			}
		}
		// 过滤掉有三个尾数相同的号码，如2,12,32
		if ("mantissaThreeSame".equals(methodName)) {
			if (!LotterySsqAlgorithm.mantissaThreeSame(lValues)) {
				return false;
			}
		}
		// 过滤掉有三个2倍数的号码，如2,4,8
		if ("haveThree2Multiple".equals(methodName)) {
			if (!LotterySsqAlgorithm.haveThree2Multiple(lValues)) {
				return false;
			}
		}
		// 过滤掉有三个3倍数的号码，如2,6,18
		if ("haveThree3Multiple".equals(methodName)) {
			if (!LotterySsqAlgorithm.haveThree3Multiple(lValues)) {
				return false;
			}
		}
		// 连续4个奇数或偶数
		// 有5个以上的奇数或偶数包含5个.
		// 对历史号码的研究
		// 1.1、2、3/4/5、差值的统计
		// 重号附近号码规律的统计
		return true;
	}
}
