package com.lottery.ssq.fetch;

import junit.framework.TestCase;

import com.lottery.ssq.LotterySsqFetchConfig;

public class SsqLotterySinaFetchImplTest extends TestCase{
	public void testGetSsqLotteryIndexList(){
		SsqLottery163FetchImpl test=new SsqLottery163FetchImpl();
		new LotterySsqFetchConfig().expect="10065"; 
		test.getSsqLotteryIndexList();
	}
}
