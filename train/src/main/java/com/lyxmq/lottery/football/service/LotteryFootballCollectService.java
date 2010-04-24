package com.lyxmq.lottery.football.service;

import com.lyxmq.lottery.football.dao.LotteryFootballDao;

public class LotteryFootballCollectService {
	private LotteryFootballDao footballLotteryDao=null;
	private LotteryFootballCustomer500WanService customer500WanService=null;
	private LotteryFootballCustomerDyjService customerCpdyjService=null;
	private LotteryFootballFileService fileService=null;

	public void collect500WanSfc() {
		customer500WanService.start();
	}

	public void collectSinaSfc() {
		// TODO Auto-generated method stub
		
	}

	public void collectCpdyjSfc() {
		// TODO Auto-generated method stub
		
	}
}
