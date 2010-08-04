package com.lottery.ssq.filter;

import java.util.List;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;

import com.lottery.ssq.Algorithm.LotterySsqAlgorithm;
import com.lottery.ssq.config.LotterySsqFilterConfig;

/**
 * 使用各大网站抓取的媒体对号码进行过滤.
 * 
 * @author ly.zy.ljh
 */
public class LotterySsqWebMediaFilterService {
	/**
	 * 500万媒体过滤
	 * 
	 * @param filterConfig
	 * 
	 * @param methodName
	 * @param arg
	 * @param lValues
	 * @param webRedCodeList 
	 * @param webDanList 
	 * @param wan500RedCodeList
	 * @return
	 */
	public boolean webFilterRedCode(LotterySsqFilterConfig filterConfig, 
			String[] lValues, Set<String> webRedCodeList, List<String> webDanList) {
		if (filterConfig.getIsFilterWebFourCode() > 0) {
			if (!this.isFilterWebFourCode(lValues, webRedCodeList)) {
				return false;
			}
		}
		if (!LotterySsqAlgorithm.isRedTogethorCode(lValues, webDanList)) {
			return false;
		}
		return true;
	}
	/**
	 * 从网站收集的红球不能中4各号码以上 .
	 * @param lValues
	 * @param webRedCodeList
	 * @return
	 */
	private boolean isFilterWebFourCode(String[] lValues, Set<String> webRedCodeList) {
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