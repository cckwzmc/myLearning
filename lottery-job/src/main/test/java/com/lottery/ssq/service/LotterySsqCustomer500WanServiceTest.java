package com.lottery.ssq.service;

import org.springframework.beans.factory.annotation.Autowired;

public class LotterySsqCustomer500WanServiceTest extends BaseTestCase{
	@Autowired
	private LotterySsqCustomer500WanService lotterySsqCustomer500WanService=null;
	@Autowired
	private LotterySsqConifgService lotterySsqConfigService=null;
	public void testSave500WanProjectRedCode(){
		this.lotterySsqConfigService.initFetchConfig();
		this.lotterySsqCustomer500WanService.save500WanProjectRedCode();
	}
}
