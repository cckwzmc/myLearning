package com.lottery.ssq.fetch;

import junit.framework.TestCase;

import com.lottery.ssq.config.LotterySsqConfig;

public class SsqLotterySinaFetchImplTest extends TestCase{
	public void testGetSsqLotteryIndexList(){
		ISsqLotteryFetch test=new SsqLotterySinaFetchImpl();
		new LotterySsqConfig().expect="10066"; 
		test.getSsqLotteryIndexList();
	}
}
