package com.lottery.ssq.filter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;

import com.lottery.ssq.Algorithm.LotterySsqMediaAlgorithm;
import com.lottery.ssq.config.LotterySsqFilterConfig;
import com.lottery.ssq.dao.LotteryDao;

/**
 * 使用SINA媒体对号码进行过滤.
 * 
 * @author ly.zy.ljh
 */
public class LotterySsq500WanMediaFilterService {

	/**
	 * 500万媒体过滤
	 * 
	 * @param filterConfig
	 * 
	 * @param methodName
	 * @param arg
	 * @param lValues
	 * @param wan500RedCodeList 
	 * @param wan500RedCodeList
	 * @return
	 */
	public boolean wan500FilterRedCode(LotterySsqFilterConfig filterConfig, 
			String[] lValues, Set<String[]> wan500RedCodeList) {
		//必须过滤的方法.
		if (!this.isWan500RedIncludeFourCode(lValues, wan500RedCodeList,4)) {
			return false;
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
	private boolean isWan500RedIncludeFourCode(String[] lValues, Set<String[]> wan500RedCodeList, int num) {
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
}