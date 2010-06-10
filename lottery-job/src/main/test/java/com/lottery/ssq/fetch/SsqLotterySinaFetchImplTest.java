package com.lottery.ssq.fetch;

import junit.framework.TestCase;

import com.lottery.ssq.LotterySsqFetchConfig;

public class SsqLotterySinaFetchImplTest extends TestCase{
	public void testGetSsqLotteryIndexList(){
		ISsqLotteryFetch test=new SsqLotterySinaFetchImpl();
		new LotterySsqFetchConfig().expect="10066"; 
		test.getSsqLotteryIndexList();
	}
}
