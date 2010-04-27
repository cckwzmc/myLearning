package com.lottery.ssq.service;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lottery.ssq.dao.LotteryDao;

/**
 * 抓取500万/大赢家 用户投注号码
 * 
 * @author lyxmq
 */
public class LotterySsqCollectService {
	private static final Logger logger=LoggerFactory.getLogger(LotterySsqCollectService.class);
	LotteryDao dao = null;
	LotterySsqCustomerDyjService lotterySsqCustomerDyjService = null;
	LotterySsqCustomer500WanService lotterySsqCustomer500WanService = null;
	LotterySsqFileService lotterySsqFileService=null;
	public void setLotterySsqFileService(LotterySsqFileService lotterySsqFileService) {
		this.lotterySsqFileService = lotterySsqFileService;
	}

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
	
	/**
	 * 从文本中读取
	 */
	public void collectFileProject() {
		this.lotterySsqFileService.start();
	}
	
	/**
	 * 开始处理采集数据n
	 * 
	 */
	public void collectResultDispose()
	{
		logger.info("每一期的第一次必须在抓取完成时使用这个方法............");
		if (MapUtils.isEmpty(this.dao.isGenLotteryResult("1", LotterySsqConifgService.getExpect()))) {
			this.dao.clearSsqLotteryFilterResult();
			this.dao.clearSsqLotteryCollectResult();
		}
		this.lotterySsqCustomer500WanService.save500WanProjectRedCode();
		this.lotterySsqCustomerDyjService.saveDyjProjectRedCode();
		this.lotterySsqFileService.saveFileProjectRedCode();
	}
	public void deleteFilterResultFromCollect(){
		int page=0;
		int pagesize=2000;
		List list=this.dao.getSsqLotteryCollectResultLimit(page, pagesize);
		List<String> rList=new ArrayList<String>();
		for (Iterator iterator = list.iterator(); iterator.hasNext();) {
			Map map = (Map) iterator.next();
			rList.add(ObjectUtils.toString(map.get("value")));
		}
		while(CollectionUtils.isNotEmpty(list)){
			this.dao.deleteSsqLotteryFilterResult(rList);
			page+=pagesize;
			list=this.dao.getSsqLotteryCollectResultLimit(page, pagesize);
			rList.clear();
			for (Iterator iterator = list.iterator(); iterator.hasNext();) {
				Map map = (Map) iterator.next();
				rList.add(ObjectUtils.toString(map.get("value")));
			}
		}
	}
}
