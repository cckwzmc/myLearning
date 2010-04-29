package com.lottery.ssq.job;

import org.apache.commons.collections.MapUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.lottery.ssq.service.LotteryInitService;
import com.lottery.ssq.service.LotterySsqCollectService;
import com.lottery.ssq.service.LotterySsqConifgService;
import com.lottery.ssq.service.LotterySsqCustomer500WanService;
import com.lottery.ssq.service.LotterySsqCustomerDyjService;
import com.lottery.ssq.service.LotterySsqFileService;
import com.lottery.ssq.service.LotterySsqService;

public class LotterySsqJob {
	private static final Logger logger = LoggerFactory.getLogger(LotterySsqJob.class);
	LotterySsqCustomerDyjService lotterySsqCustomerDyjService = null;
	LotterySsqCustomer500WanService lotterySsqCustomer500WanService = null;
	LotterySsqFileService lotterySsqFileService = null;
	LotterySsqService lotterySsqService = null;
	LotteryInitService lotteryInitService = null;
	private LotterySsqCollectService lotteryCollectService = null;

	public void setLotteryCollectService(LotterySsqCollectService lotteryCollectService) {
		this.lotteryCollectService = lotteryCollectService;
	}

	public void setLotterySsqFileService(LotterySsqFileService lotterySsqFileService) {
		this.lotterySsqFileService = lotterySsqFileService;
	}

	public void setLotteryInitService(LotteryInitService lotteryInitService) {
		this.lotteryInitService = lotteryInitService;
	}

	public void setLotterySsqCustomerDyjService(LotterySsqCustomerDyjService lotterySsqCustomerDyjService) {
		this.lotterySsqCustomerDyjService = lotterySsqCustomerDyjService;
	}

	public void setLotterySsqService(LotterySsqService lotterySsqService) {
		this.lotterySsqService = lotterySsqService;
	}

	public void setLotterySsqCustomer500WanService(LotterySsqCustomer500WanService lotterySsqCustomer500WanService) {
		this.lotterySsqCustomer500WanService = lotterySsqCustomer500WanService;
	}

	public void initService() {
		ApplicationContext context = new ClassPathXmlApplicationContext(new String[] { "classpath:/spring/applicationContext.xml", "classpath:/lottery/ssq/applicationContext-database.xml", "classpath:/lottery/ssq/applicationContext-dao.xml",
				"classpath:/lottery/ssq/applicationContext-service.xml" });
		this.lotterySsqCustomer500WanService = (LotterySsqCustomer500WanService) context.getBean("lotterySsqCustomer500WanService");
		this.lotterySsqCustomerDyjService = (LotterySsqCustomerDyjService) context.getBean("lotterySsqCustomerDyjService");
		this.lotterySsqService = (LotterySsqService) context.getBean("lotteryService");
		this.lotteryInitService = (LotteryInitService) context.getBean("initLotteryService");
		this.lotterySsqFileService = (LotterySsqFileService) context.getBean("lotterySsqFileService");
		this.lotteryCollectService=(LotterySsqCollectService) context.getBean("lotteryCollectService");
		new LotterySsqConifgService();
	}

	public void fetchLotterySsqService() {
		initService();
		logger.info("后台抓取任务开始...........");
//		this.lotterySsqCustomer500WanService.start();
//		this.lotterySsqCustomerDyjService.start();
//		this.lotterySsqFileService.start();
		this.lotterySsqFileService.clearCollectFile();
		this.lotteryCollectService.fetch500WanCustomerProject();
		this.lotteryCollectService.fetchDyjCustomerProject();
		this.lotteryCollectService.backHisSsqCollectResult();
	}

	/**
	 * 计算抓取的数据，并完成初步过滤。 每天下午4点开始
	 */
	public void parserFetchLotterySsqData() {
		initService();
		this.lotterySsqFileService.collectFileCode();
		this.lotteryInitService.fetchMedia500WanContent();
		this.lotteryInitService.fetchMediaSinaContent();
		this.lotteryInitService.fetchMediaSinaDan();
		this.lotteryCollectService.collectResultDispose();
//		this.lotterySsqCustomer500WanService.save500WanProjectRedCode();
//		this.lotterySsqCustomerDyjService.saveDyjProjectRedCode();
		this.lotterySsqService.filterCurrentRedCode();
//		this.lotterySsqFileService.clearFileContent();
	}
	/**
	 *	生成过滤号码 
	 */
	public void genFilterRedCode(){
		if (this.lotterySsqService.isGenLotteryResult("1", LotterySsqConifgService.getExpect())) {
			this.lotterySsqService.getCurrentExpertSingleResult();
		}
	}
}
