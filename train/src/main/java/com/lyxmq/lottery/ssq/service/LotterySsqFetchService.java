package com.lyxmq.lottery.ssq.service;

import com.lyxmq.lottery.ssq.dao.LotteryDao;

/**
 * 抓取500万/大赢家 用户投注号码
 * 
 * @author lyxmq
 */
public class LotterySsqFetchService {
	LotteryDao dao = null;
	LotterySsqCustomerDyjService lotterySsqCustomerDyjService = null;
	LotterySsqCustomer500WanService lotterySsqCustomer500WanService = null;

	public void setLotterySsqCustomerDyjService(LotterySsqCustomerDyjService lotterySsqCustomerDyjService) {
		this.lotterySsqCustomerDyjService = lotterySsqCustomerDyjService;
	}

	public void setLotterySsqCustomer500WanService(LotterySsqCustomer500WanService lotterySsqCustomer500WanService) {
		this.lotterySsqCustomer500WanService = lotterySsqCustomer500WanService;
	}

	public void setDao(LotteryDao dao) {
		this.dao = dao;
	}
	static{
		new LotterySsqConifgService();
	}
	/**
	 * 大赢家用户投注抓取/由于大赢家的访问有一定的限制，所以在抓取时要休眠一定的时间
	 */
	public void fetchDyjCustomerProject() {
		this.lotterySsqCustomerDyjService.start();
	}

	/**
	 * 500wan用户投注抓取
	 */
	public void fetch500WanCustomerProject() {
		this.lotterySsqCustomer500WanService.start();
	}

}
