package com.lottery.ssq.job;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lottery.ssq.service.LotterySsqCustomer500WanService;
import com.lottery.ssq.service.LotterySsqCustomerDyjService;

public class LotterySsqJob {
	private static final Logger logger = LoggerFactory.getLogger(LotterySsqJob.class);
	LotterySsqCustomerDyjService lotterySsqCustomerDyjService = null;
	LotterySsqCustomer500WanService lotterySsqCustomer500WanService = null;

	public void setLotterySsqCustomerDyjService(LotterySsqCustomerDyjService lotterySsqCustomerDyjService) {
		this.lotterySsqCustomerDyjService = lotterySsqCustomerDyjService;
	}

	public void setLotterySsqCustomer500WanService(LotterySsqCustomer500WanService lotterySsqCustomer500WanService) {
		this.lotterySsqCustomer500WanService = lotterySsqCustomer500WanService;
	}

	public void fetchLotterySsqService() {
		logger.info("后台抓取任务开始...........");
		this.lotterySsqCustomer500WanService.fetch500WanProjectRedCode();
		this.lotterySsqCustomerDyjService.fetchDyjProjectCode();
	}
	
	/**
	 * 每天下午4点开始
	 */
	public void parserFetchLotterySsqData(){
		this.lotterySsqCustomer500WanService.save500WanProjectRedCode();
		this.lotterySsqCustomerDyjService.saveDyjProjectRedCode();
	}
}
