package com.lottery.ssq.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lottery.ssq.dao.LotteryDao;
import com.lottery.ssq.fetch.ISsqLotteryFetch;

/**
 * 抓取500万/大赢家 用户投注号码
 * 
 * @author lyxmq
 */
public class LotterySsqCollectService {
	private static final Logger logger = LoggerFactory.getLogger(LotterySsqCollectService.class);
	LotteryDao dao = null;
	LotterySsqCustomerDyjService lotterySsqCustomerDyjService = null;
	LotterySsqCustomer500WanService lotterySsqCustomer500WanService = null;
	LotterySsqCustomerCaipiaoService lotterySsqCustomerCaipiaoService = null;
	LotterySsqFileService lotterySsqFileService = null;
	LotterySsqMedia500WanService lotterySsqMedia500WanService = null;
	LotterySsqMediaSinaService lotterySsqMediaSinaService = null;
	ISsqLotteryFetch ssqLottery163FetchImpl;
	ISsqLotteryFetch ssqLotterySohuFetchImpl;
	ISsqLotteryFetch ssqLotterySinaFetchImpl;
	private LotterySsqCustomerBetzcService lotterySsqCustomerBetzcService = null;
	private LotterySsqWebCollectService lotterySsqWebCollectService=null;


	public void setLotterySsqWebCollectService(LotterySsqWebCollectService lotterySsqWebCollectService) {
		this.lotterySsqWebCollectService = lotterySsqWebCollectService;
	}

	public void setSsqLottery163FetchImpl(ISsqLotteryFetch ssqLottery163FetchImpl) {
		this.ssqLottery163FetchImpl = ssqLottery163FetchImpl;
	}

	public void setSsqLotterySohuFetchImpl(ISsqLotteryFetch ssqLotterySohuFetchImpl) {
		this.ssqLotterySohuFetchImpl = ssqLotterySohuFetchImpl;
	}

	public void setSsqLotterySinaFetchImpl(ISsqLotteryFetch ssqLotterySinaFetchImpl) {
		this.ssqLotterySinaFetchImpl = ssqLotterySinaFetchImpl;
	}

	public void setLotterySsqMedia500WanService(LotterySsqMedia500WanService lotterySsqMedia500WanService) {
		this.lotterySsqMedia500WanService = lotterySsqMedia500WanService;
	}

	public void setLotterySsqMediaSinaService(LotterySsqMediaSinaService lotterySsqMediaSinaService) {
		this.lotterySsqMediaSinaService = lotterySsqMediaSinaService;
	}

	public void setLotterySsqFileService(LotterySsqFileService lotterySsqFileService) {
		this.lotterySsqFileService = lotterySsqFileService;
	}

	public void setLotterySsqCustomerDyjService(LotterySsqCustomerDyjService lotterySsqCustomerDyjService) {
		this.lotterySsqCustomerDyjService = lotterySsqCustomerDyjService;
	}

	public void setLotterySsqCustomer500WanService(LotterySsqCustomer500WanService lotterySsqCustomer500WanService) {
		this.lotterySsqCustomer500WanService = lotterySsqCustomer500WanService;
	}

	public void setLotterySsqCustomerBetzcService(LotterySsqCustomerBetzcService lotterySsqCustomerBetzcService) {
		this.lotterySsqCustomerBetzcService = lotterySsqCustomerBetzcService;
	}

	public void setLotterySsqCustomerCaipiaoService(LotterySsqCustomerCaipiaoService lotterySsqCustomerCaipiaoService) {
		this.lotterySsqCustomerCaipiaoService = lotterySsqCustomerCaipiaoService;
	}

	public void setDao(LotteryDao dao) {
		this.dao = dao;
	}

	static {
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
	 * 备份历史收集的号码
	 */
	public void backHisSsqCollectResult() {
		String expect = this.dao.getGenLotteryMaxExpect("1");
		if (StringUtils.isNotBlank(expect)) {
			this.dao.backupSsqLotteryCollectResult(expect);
		}
	}

	/**
	 * 开始处理采集数据n
	 */
	public void collectResultDispose() {
		logger.info("每一期的第一次必须在抓取完成时使用这个方法............");
		this.dao.clearSsqLotteryCollectResult();
		this.lotterySsqCustomer500WanService.save500WanProjectRedCode();
		this.lotterySsqCustomerDyjService.saveDyjProjectRedCode();
		this.lotterySsqFileService.saveFileProjectRedCode();
		this.lotterySsqMedia500WanService.saveMedia500WanRedCode();
		this.lotterySsqMediaSinaService.saveMediaSinaRedCode();
		this.lotterySsqCustomerCaipiaoService.saveCaipiaoProjectRedCode();
		this.lotterySsqCustomerBetzcService.saveBetzcProjectRedCode();
		this.lotterySsqWebCollectService.saveSsqWebCollect();
	}

	/**
	 * 抓取各大网站的推荐
	 */
	public void fetchWebList() {
		this.ssqLottery163FetchImpl.getSsqLotteryDetail("", "");
		this.ssqLotterySohuFetchImpl.getSsqLotteryDetail("", "");
		this.ssqLotterySinaFetchImpl.getSsqLotteryDetail("", "");
	}

	@SuppressWarnings("unchecked")
	public void deleteFilterResultFromCollect() {
		int page = 0;
		int pagesize = 2000;
		List list = this.dao.getSsqLotteryCollectResultLimit(page, pagesize);
		List<String> rList = new ArrayList<String>();
		for (Iterator iterator = list.iterator(); iterator.hasNext();) {
			Map map = (Map) iterator.next();
			rList.add(ObjectUtils.toString(map.get("value")));
		}
		while (CollectionUtils.isNotEmpty(list)) {
			this.dao.deleteSsqLotteryFilterResult(rList);
			page += pagesize;
			list = this.dao.getSsqLotteryCollectResultLimit(page, pagesize);
			rList.clear();
			for (Iterator iterator = list.iterator(); iterator.hasNext();) {
				Map map = (Map) iterator.next();
				rList.add(ObjectUtils.toString(map.get("value")));
			}
		}
	}
}
