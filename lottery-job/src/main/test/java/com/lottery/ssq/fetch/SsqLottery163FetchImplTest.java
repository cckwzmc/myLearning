package com.lottery.ssq.fetch;

import com.lottery.ssq.config.LotterySsqConfig;
import com.lottery.ssq.service.BaseTestCase;

public class SsqLottery163FetchImplTest extends BaseTestCase{
	public void testGetSsqLotteryIndexList(){
		SsqLottery163FetchImpl test=new SsqLottery163FetchImpl();
		new LotterySsqConfig().expect="10065"; 
		test.getSsqLotteryIndexList();
	}
}
