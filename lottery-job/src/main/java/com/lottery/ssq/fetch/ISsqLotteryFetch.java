package com.lottery.ssq.fetch;

import java.util.List;

/**
 * @author ly.zy.ljh
 */
public interface ISsqLotteryFetch {
	/**
	 * 获取双色球URL列表
	 * 
	 * @param url
	 * @return List<String[2]> [0]="title" [1]="url"
	 */
	public List<String[]> getSsqLotteryIndexList();

	/**
	 * @param url 详细页面的URL
	 * @param id
	 * @return 红球+蓝球 的格式的号码
	 */
	public String getSsqLotteryDetail(String url, String title);
}
