package com.lottery.ssq.filter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;

import com.lottery.ssq.Algorithm.LotterySsqAlgorithm;
import com.lottery.ssq.Algorithm.LotterySsqCollectResultAlgorithm;
import com.lottery.ssq.Algorithm.LotterySsqMediaAlgorithm;
import com.lottery.ssq.config.LotterySsqFilterConfig;
import com.lottery.ssq.dao.LotteryDao;

/**
 * 使用SINA媒体对号码进行过滤.
 * 
 * @author ly.zy.ljh
 */
public class LotterySsqConsumerFilterService {

	public boolean customerFilterRedcode(LotterySsqFilterConfig filterConfig, String[] lValues, List<String> customerDanList, List customerGtCount5RedList, List customerLeCount30RedList) {

		// if
		// (!LotterySsqCollectResultAlgorithm.isIncludeLocationRedCode(firstRedCode,secondRedCode,thirdRedCode,
		// fourthRedCode, firthRedCode, sixthRedCode, lValues)) {
		// continue;
		// }
		// 用户投注的胆
		if (filterConfig.getIsCustomerDanFilter() > 0) {
			if (!LotterySsqCollectResultAlgorithm.isCustomerDanFilter(lValues, customerDanList, filterConfig.getIsCustomerDanFilter())) {
				return false;
			}
		}
		// // 用户投注不能中4一个以上的号码
		// if (filterConfig.getCustomerGtCount5RedList() > 0) {
		// if (!LotterySsqCollectResultAlgorithm.isCumstomerRedIncludeFourCode(lValues, customerGtCount5RedList)) {
		// return false;
		// }
		// }
		if (CollectionUtils.isNotEmpty(customerLeCount30RedList)) {
			// 对收集号码的过滤
			//不可能中4个
			if (!LotterySsqCollectResultAlgorithm.isCumstomerRedIncludeFourCode(lValues, customerLeCount30RedList)) {
				return false;
			}
			// 至少3注中0个
			if (filterConfig.getCustomer30Selecte0() > 0) {
				if (this.isCustomer30Selecte0(lValues, customerLeCount30RedList, filterConfig.getCustomer30Selecte0())) {
					return false;
				}
			}
			// 至少10注以上中1个

			if (filterConfig.getCustomer30Selecte1() > 0) {
				if (this.isCustomer30Selecte1(lValues, customerLeCount30RedList, filterConfig.getCustomer30Selecte1())) {
					return false;
				}
			}
			// 最多3注中3个
			if (filterConfig.getCustomer30Selecte3() > 0) {
				if (this.isCustomer30Selecte3(lValues, customerLeCount30RedList, filterConfig.getCustomer30Selecte3())) {
					return false;
				}
			}
		}
		// if (filterConfig.getCustomerLeCount3RedList() == 1) {
		// int first = 0;
		// List customerLeCountRedList = null;
		// boolean start = true;
		// String mergeCode = "";
		// while (CollectionUtils.isNotEmpty(customerLeCountRedList) || start) {
		// customerLeCountRedList = this.dao.getSsqLotteryCollectResultCountLe1(first, page);
		// first += page;
		// mergeCode = LotterySsqCollectResultAlgorithm.isCumstomerRedIncludeFiveCode(lValues,
		// customerLeCountRedList, mergeCode);
		// start = false;
		// }
		// if (!LotterySsqFilterUtils.isCumstomerRedIncludeFiveCode(lValues, mergeCode)) {
		// customerLeCountRedList.clear();
		// continue;
		// }
		// }
		// 收集号码的排行中的前10个
		// if (!LotterySsqCollectResultAlgorithm.isCustomerRedCodeTop10Filter(lValues,
		// customerMaxSelected.subList(0, 10))) {
		// continue;
		// }
		// 收集号码的排行中的前20个
		// if (!LotterySsqCollectResultAlgorithm.isCustomerRedCodeTop20Filter(lValues,
		// customerMaxSelected.subList(0, 20))) {
		// continue;
		// }
		// if (!LotterySsqAlgorithm.isFileRedCodeFourFilter(lValues, otherRedCodeList)) {
		// continue;
		// }

		// if (!LotterySsqAlgorithm.isRedTwoCodeInCustomerResult(lValues, cRedList.subList(0, 10))) {
		// continue;
		// }
		// if (!LotterySsqAlgorithm.isRedThreeCodeInCustomerResult(lValues, cRedList)) {
		// continue;
		// }
		// if (!LotterySsqAlgorithm.isRedFourCodeResult(lValues, customerRedTop40)) {
		// continue;
		// }

		// if (!LotterySsqAlgorithm.isRedFourCodeInCustomerResult(lValues, cRedList)) {
		// continue;
		// }
		// 在用户投注中5个的不能超过10个
		// if(selectedRedCodeGtFive(lValues)){
		// continue;
		// }
		/*
		 * 即我认为这些号码是不可能中奖的， 推荐使用文本方式保存的号码使用
		 */
		// if(CollectionUtils.isNotEmpty(fileRedCode)&&!LotterySsqAlgorithm.isRedCodeHaveSix(fileRedCode,lValues)){
		// continue;
		// }
		return true;
	}

	private boolean isCustomer30Selecte0(String[] lValues, List customerLeCount30RedList, int customer30Selecte0) {
		int selected=0;
		for(int i=0;i<customerLeCount30RedList.size();i++){
			Map map=(Map) customerLeCount30RedList.get(i);
			String[] redcodes=ObjectUtils.toString(map.get("redcode")).split(",");
			int tmpselected=0;
			for(String redcode:redcodes){
				for(String value:lValues){
					if(StringUtils.equals(redcode, value)){
						tmpselected++;
					}
				}
			}
			if(tmpselected==0){
				selected++;
			}
		}
		if(selected<customer30Selecte0){
			return false;
		}
		return true;
		
	}

	private boolean isCustomer30Selecte1(String[] lValues, List customerLeCount30RedList, int customer30Selecte1) {
		int selected=0;
		for(int i=0;i<customerLeCount30RedList.size();i++){
			Map map=(Map) customerLeCount30RedList.get(i);
			String[] redcodes=ObjectUtils.toString(map.get("redcode")).split(",");
			int tmpselected=0;
			for(String redcode:redcodes){
				for(String value:lValues){
					if(StringUtils.equals(redcode, value)){
						tmpselected++;
					}
				}
			}
			if(tmpselected==1){
				selected++;
			}
		}
		if(selected<customer30Selecte1){
			return false;
		}
		return true;
	}

	private boolean isCustomer30Selecte3(String[] lValues, List customerLeCount30RedList, int customer30Selecte3) {
		int selected=0;
		for(int i=0;i<customerLeCount30RedList.size();i++){
			Map map=(Map) customerLeCount30RedList.get(i);
			String[] redcodes=ObjectUtils.toString(map.get("redcode")).split(",");
			int tmpselected=0;
			for(String redcode:redcodes){
				for(String value:lValues){
					if(StringUtils.equals(redcode, value)){
						tmpselected++;
					}
				}
			}
			if(tmpselected==3){
				selected++;
			}
		}
		if(selected>customer30Selecte3){
			return false;
		}
		return true;
	}

}