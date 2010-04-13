package com.lyxmq.lottery.ssq.service;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lyxmq.lottery.ssq.dao.LotteryDao;

/**
 * 超过20个号码的过滤
 * 
 * @author Administrator
 * 
 */
public class LotterySsqThan20Service extends Thread {
	private static Logger logger = LoggerFactory.getLogger(LotterySsqThan20Service.class);

	private LotteryDao dao = null;

	public void setDao(LotteryDao dao) {
		this.dao = dao;
	}

	public void select(int k, String[] source, List<String> resultList) {
		String[] result = new String[k];
		subselect(0, 1, result, k, source, resultList);
		if(CollectionUtils.isNotEmpty(resultList)){
			this.dao.batchDelSsqLotteryFilterResult(resultList);
			resultList.clear();
		}
	}

	private void subselect(int head, int index, String[] r, int k, String[] source, List<String> resultList) {
		for (int i = head; i < source.length + index - k; i++) {
			if (index < k) {
				r[index - 1] = source[i];
				subselect(i + 1, index + 1, r, k, source, resultList);
			} else if (index == k) {
				r[index - 1] = source[i];
				String redCode = StringUtils.join(r, ",");
				if (!resultList.contains(redCode)) {
					resultList.add(redCode);
				}
				if(CollectionUtils.isNotEmpty(resultList)&&resultList.size()>2000){
					this.dao.batchDelSsqLotteryFilterResult(resultList);
					resultList.clear();
				}
				subselect(i + 1, index + 1, r, k, source, resultList);
			} else {
				return;
			}

		}
	}
}
