package com.lottery.football.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lottery.football.dao.LotteryFootballDao;

public class LotteryFootballService {
	private static Logger logger = LoggerFactory.getLogger(LotteryFootballService.class);

	private LotteryFootballDao footballLotteryDao = null;
	private LotteryFootballFileService fileLotteryService = null;
	private LotteryFootballMedia500WanService lotteryFootballMedia500WanService = null;
	private LotteryFootballCustomer500WanService lotteryFootballCustomer500WanService = null;

	public void setLotteryFootballMedia500WanService(LotteryFootballMedia500WanService lotteryFootballMedia500WanService) {
		this.lotteryFootballMedia500WanService = lotteryFootballMedia500WanService;
	}

	public void setFootballLotteryDao(LotteryFootballDao footballLotteryDao) {
		this.footballLotteryDao = footballLotteryDao;
	}

	public void setFileLotteryService(LotteryFootballFileService fileLotteryService) {
		this.fileLotteryService = fileLotteryService;
	}

	public void setLotteryFootballCustomer500WanService(LotteryFootballCustomer500WanService lotteryFootballCustomer500WanService) {
		this.lotteryFootballCustomer500WanService = lotteryFootballCustomer500WanService;
	}

	public void filterFootballCode() {
//		this.fileLotteryService.saveFootballFileCode();
//		this.lotteryFootballMedia500WanService.saveCurrentExpectFootballMedia();
		this.lotteryFootballCustomer500WanService.saveCurrentExpectFootballCustom();
		// this.footballLotteryDao.saveFootballLottoryFilterResult();
	}
}
