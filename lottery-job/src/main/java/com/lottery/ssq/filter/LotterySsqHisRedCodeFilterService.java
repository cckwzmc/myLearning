package com.lottery.ssq.filter;

import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.StringUtils;

import com.lottery.ssq.config.LotterySsqFilterConfig;
import com.lottery.ssq.dao.LotteryDao;

/**
 * 使用历史号码对号码进行过滤.
 * @author ly.zy.ljh
 *
 */
public class LotterySsqHisRedCodeFilterService{
	private LotteryDao dao=null;
	private LotterySsqFilterConfig localFilterConfig;
	
	public LotterySsqFilterConfig getLocalFilterConfig() {
		return localFilterConfig;
	}
	public void setLocalFilterConfig(LotterySsqFilterConfig localFilterConfig) {
		this.localFilterConfig = localFilterConfig;
	}
	public void setDao(LotteryDao dao) {
		this.dao = dao;
	}
	@SuppressWarnings("unchecked")
	public boolean filterHistoryRedCode(String[] lValues,LotterySsqFilterConfig filterConfig){
		this.setLocalFilterConfig(filterConfig);
		List list=this.dao.getLotteryHisCode(10);
		if(CollectionUtils.isEmpty(list)){
			return true;
		}
		if(filterConfig.getIsFilterHistoryFourCode()>0){
			if(!this.isFilterHistoryFourCode(lValues, list)){
				return false;
			}
		}
		if(filterConfig.getIsFilterHistoryFiveSelecte()>0){
			if(!this.isFilterHistoryFiveSelecte(lValues, list)){
				return false;
			}
		}
		if(filterConfig.getIsFilterHistoryOneSelecte()>0){
			if(!this.isFilterHistoryOneSelecte(lValues, list)){
				return false;
			}
		}
		if(filterConfig.getIsFilterHistoryThreeSelecte()>0){
			if(!this.isFilterHistoryThreeSelecte(lValues, list)){
				return false;
			}
		}
		return true;
	}
	@SuppressWarnings("unchecked")
	private boolean isFilterHistoryThreeSelecte(String[] lValues, List list) {
		int selected=0;
		for(int i=0;i<list.size();i++){
			Map map=(Map) list.get(i);
			String[] redCodes=StringUtils.split(ObjectUtils.toString(map.get("redcode")),",");
			int valueSlt=0;
			for(String value:lValues){
				for(String redcode:redCodes){
					if(StringUtils.equals(value, redcode)){
						valueSlt++;
					}
				}
			}
			if(valueSlt>=3){
				selected++;
			}
		}
		if(selected>this.localFilterConfig.getIsFilterHistoryThreeSelecte()){
			return false;
		}
		return true;
	}
	@SuppressWarnings("unchecked")
	private boolean isFilterHistoryOneSelecte(String[] lValues, List list) {
		int selected=0;
		for(int i=0;i<list.size();i++){
			Map map=(Map) list.get(i);
			String[] redCodes=StringUtils.split(ObjectUtils.toString(map.get("redcode")),",");
			int valueSlt=0;
			for(String value:lValues){
				for(String redcode:redCodes){
					if(StringUtils.equals(value, redcode)){
						valueSlt++;
					}
				}
			}
			if(valueSlt<=1){
				selected++;
			}
		}
		if(selected<this.localFilterConfig.getIsFilterHistoryOneSelecte()){
			return false;
		}
		return true;
	}
	@SuppressWarnings("unchecked")
	private boolean isFilterHistoryFiveSelecte(String[] lValues, List list) {
		int selected=0;
		for(int i=0;i<list.size();i++){
			Map map=(Map) list.get(i);
			String[] redCodes=StringUtils.split(ObjectUtils.toString(map.get("redcode")),",");
			int valueSlt=0;
			for(String value:lValues){
				for(String redcode:redCodes){
					if(StringUtils.equals(value, redcode)){
						valueSlt++;
					}
				}
			}
			if(valueSlt>0){
				selected++;
			}
		}
		if(selected<this.localFilterConfig.getIsFilterHistoryFiveSelecte()){
			return false;
		}
		return true;
	}
	@SuppressWarnings("unchecked")
	private boolean isFilterHistoryFourCode(String[] lValues,List list){
		for(int i=0;i<list.size();i++){
			Map map=(Map) list.get(i);
			String[] redCodes=StringUtils.split(ObjectUtils.toString(map.get("redcode")),",");
			int valueSlt=0;
			for(String value:lValues){
				for(String redcode:redCodes){
					if(StringUtils.equals(value, redcode)){
						valueSlt++;
					}
				}
			}
			if(valueSlt>=4){
				return false;
			}
		}
		
		return true;
	}
}