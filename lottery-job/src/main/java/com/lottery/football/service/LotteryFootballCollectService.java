package com.lottery.football.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LotteryFootballCollectService {
	private static final Logger logger = LoggerFactory.getLogger(LotteryFootballCollectService.class);
	LotteryFootballCustomer500WanService lotteryFootballCustomer500WanService = null;
	LotteryFootballCustomerDyjService lotteryFootballCustomerDyjService = null;

	LotteryFootballFileService fileLotteryService = null;

	public void setLotteryFootballCustomerDyjService(LotteryFootballCustomerDyjService lotteryFootballCustomerDyjService) {
		this.lotteryFootballCustomerDyjService = lotteryFootballCustomerDyjService;
	}

	public void setLotteryFootballCustomer500WanService(LotteryFootballCustomer500WanService lotteryFootballCustomer500WanService) {
		this.lotteryFootballCustomer500WanService = lotteryFootballCustomer500WanService;
	}

	public void setFileLotteryService(LotteryFootballFileService fileLotteryService) {
		this.fileLotteryService = fileLotteryService;
	}

	public void collect500WanSfc() {
		this.lotteryFootballCustomer500WanService.start();
	}

	public void collectCaipiaoSfc() {

	}

	public void collectDyjSfc() {
		this.lotteryFootballCustomerDyjService.start();
	}

}
