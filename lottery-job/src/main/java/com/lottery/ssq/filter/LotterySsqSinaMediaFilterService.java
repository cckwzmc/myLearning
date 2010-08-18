package com.lottery.ssq.filter;

import java.util.HashSet;
import java.util.List;

import com.lottery.ssq.Algorithm.LotterySsqMediaAlgorithm;
import com.lottery.ssq.config.LotterySsqFilterConfig;
import com.lottery.ssq.dao.LotteryDao;

/**
 * 使用SINA媒体对号码进行过滤.
 * 
 * @author ly.zy.ljh
 */
public class LotterySsqSinaMediaFilterService {
	private LotteryDao dao = null;
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

	public boolean sinaFilterMethod(LotterySsqFilterConfig filterConfig, String[] lValues, List<String> sinaDanList, List<String[]> sinaRedCodeList) {

		// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
		// 必须过滤的方法
		if (!LotterySsqMediaAlgorithm.isSinaDanTogethorFilter(filterConfig, lValues, sinaDanList)) {
			return false;
		}
		// 必须过滤的方法
		if (!LotterySsqMediaAlgorithm.isSinaRedIncludeFourCode(filterConfig, lValues, new HashSet<String[]>(sinaRedCodeList), 4)) {
			return false;
		}
		if (filterConfig.getIsSinaDanAllFilter() > 0) {

			if (!LotterySsqMediaAlgorithm.isSinaDanAllFilter(filterConfig, lValues, sinaDanList, filterConfig.getIsSinaDanAllFilter())) {
				return false;
			}
		}
		if (filterConfig.getIsSinaDanNoneFilter() > 0) {
			if (!LotterySsqMediaAlgorithm.isSinaDanNoneFilter(filterConfig, lValues, sinaDanList, filterConfig.getIsSinaDanNoneFilter())) {
				return false;
			}
		}

		if (filterConfig.getIsSinaRedCodeXiaoFourFilter() > 0) {
			if (!LotterySsqMediaAlgorithm.isSinaRedCodeXiaoFourFilter(filterConfig, lValues, sinaRedCodeList, filterConfig.getIsSinaRedCodeXiaoFourFilter())) {
				return false;
			}
		}
		if (filterConfig.getIsSinaRedCodeNodeSelected() > 0) {
			if (!LotterySsqMediaAlgorithm.isSinaRedCodeNodeSelected(filterConfig, lValues, sinaRedCodeList, filterConfig.getIsSinaRedCodeNodeSelected())) {
				return false;
			}
		}
		return true;
	}
}