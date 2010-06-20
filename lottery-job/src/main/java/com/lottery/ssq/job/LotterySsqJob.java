package com.lottery.ssq.job;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.lottery.ssq.config.LotterySsqConfig;
import com.lottery.ssq.service.LotteryInitService;
import com.lottery.ssq.service.LotterySsqCollectService;
import com.lottery.ssq.service.LotterySsqConifgService;
import com.lottery.ssq.service.LotterySsqCustomer500WanService;
import com.lottery.ssq.service.LotterySsqCustomerCaipiaoService;
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
	LotterySsqCustomerCaipiaoService lotterySsqCustomerCaipiaoService=null;
	
	private LotterySsqCollectService lotteryCollectService = null;
	private LotterySsqConifgService lotterySsqConifgService = null;

	
	public void setLotterySsqCustomerCaipiaoService(LotterySsqCustomerCaipiaoService lotterySsqCustomerCaipiaoService) {
		this.lotterySsqCustomerCaipiaoService = lotterySsqCustomerCaipiaoService;
	}

	public void setLotterySsqConifgService(LotterySsqConifgService lotterySsqConifgService) {
		this.lotterySsqConifgService = lotterySsqConifgService;
	}

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
		if (this.lotterySsqCustomer500WanService == null) {
			this.lotterySsqCustomer500WanService = (LotterySsqCustomer500WanService) context.getBean("lotterySsqCustomer500WanService");
		}
		if (this.lotterySsqCustomerDyjService == null) {
			this.lotterySsqCustomerDyjService = (LotterySsqCustomerDyjService) context.getBean("lotterySsqCustomerDyjService");
		}
		if (this.lotterySsqService == null) {
			this.lotterySsqService = (LotterySsqService) context.getBean("lotteryService");
		}
		if (this.lotteryInitService == null) {
			this.lotteryInitService = (LotteryInitService) context.getBean("initLotteryService");
		}
		if (this.lotterySsqFileService == null) {
			this.lotterySsqFileService = (LotterySsqFileService) context.getBean("lotterySsqFileService");
		}
		if (this.lotteryCollectService == null) {
			this.lotteryCollectService = (LotterySsqCollectService) context.getBean("lotteryCollectService");
		}
		if (this.lotterySsqConifgService == null) {
			this.lotterySsqConifgService = (LotterySsqConifgService) context.getBean("lotterySsqConifgService");
		}
		if (this.lotterySsqCustomerCaipiaoService == null) {
			this.lotterySsqCustomerCaipiaoService = (LotterySsqCustomerCaipiaoService) context.getBean("lotterySsqCustomerCaipiaoService");
		}
	}

	/**
	 * 本期双色球任务准备
	 */
	public void beforeLotterySsqService() {
		initService();
		logger.info("后台备份历史数据任务开始...........");
		if (!this.lotterySsqConifgService.initFetchConfig()) {
			logger.info("抓取配置初始化失败....................");
			return;
		}
		logger.info("清理历史数据(胆、文件收集、上期的过滤号码。)............................");
		this.lotterySsqService.clearHisSsqData();
		logger.info("清理历史数据结束............................");
		logger.info("初始化Filter数据开始............................");
		this.lotteryInitService.initFilterResult();
		logger.info("初始化Filter数据结束............................");
		logger.info("备份历史抓取结果开始....................");
		this.lotteryCollectService.backHisSsqCollectResult();
		logger.info("备份历史抓取结果结束....................");
	}
	/**
	 * 500wan用户投注抓取
	 */
	public void fetch500WanLotterySsqService() {
		initService();
		if (!this.lotterySsqConifgService.initFetchConfig()) {
			logger.info("抓取配置初始化失败....................");
			return;
		}
		logger.info("500wan用户投注后台抓取任务开始...........");
		logger.info("抓取500wan用户投注开始....................");
		this.lotterySsqCustomer500WanService.fetch500WanProjectRedCode();
		logger.info("抓取500wan用户投注结束....................");
	}
	/**
	 * 大赢家用户投注抓取
	 */
	public void fetchDyjLotterySsqService() {
		initService();
		if (!this.lotterySsqConifgService.initFetchConfig()) {
			logger.info("抓取配置初始化失败....................");
			return;
		}
		logger.info("大赢家用户后台抓取任务开始...........");
		logger.info("抓取大赢家用户投注开始....................");
		this.lotterySsqCustomerDyjService.fetchDyjProjectCode();
		logger.info("抓取大赢家用户投注结束....................");
	}
	/**
	 * 爱彩网用户投注抓取
	 */
	public void fetchCaipiaoLotterySsqService() {
		initService();
		if (!this.lotterySsqConifgService.initFetchConfig()) {
			logger.info("爱彩网抓取配置初始化失败....................");
			return;
		}
		logger.info("爱彩网用户后台抓取任务开始...........");
		logger.info("抓取爱彩网用户投注开始....................");
		this.lotterySsqCustomerCaipiaoService.fetchCaipiaoProjectCode();
		logger.info("抓取爱彩网用户投注结束....................");
	}
	/**
	 * 计算抓取的数据，并完成初步过滤。 每天下午4点开始
	 */
	public void parserFetchLotterySsqData() {
		initService();
		
		if (!this.lotterySsqConifgService.initFetchConfig()) {
			logger.info("抓取配置初始化失败....................");
			return;
		}
		logger.info("文件收集加入Collect_Fetch。。。。。。");
		this.lotterySsqFileService.collectFileCode();
		logger.info("文件收集加入Collect_Fetch结束。。。。。。");
		logger.info("500wan媒体推荐抓取开始。。。。。。");
		this.lotteryInitService.fetchMedia500WanContent();
		logger.info("500wan媒体推荐抓取结束。。。。。。");
		logger.info("Sina媒体推荐抓取开始。。。。。。");
		this.lotteryInitService.fetchMediaSinaContent();
		logger.info("Sina媒体推荐抓取结束。。。。。。");
		logger.info("Sina媒体推荐（（胆））抓取结束。。。。。。");
		this.lotteryInitService.fetchMediaSinaDan();
		logger.info("Sina媒体推荐（（胆））抓取结束。。。。。。");
		this.lotteryCollectService.collectResultDispose();
		logger.info("抓取号码拆分结束........");
		this.lotterySsqService.filterCurrentRedCode();
		logger.info("第一步号码过滤结束...........");
	}

	/**
	 * 生成过滤号码
	 */
	public void genFilterRedCode() {
		initService();
		if (!this.lotterySsqConifgService.initFetchConfig() || !this.lotterySsqConifgService.initFilterConfig()) {
			logger.info("抓取配置/过滤配置初始化失败....................");
			return;
		}
		if (this.lotterySsqService.isGenLotteryResult("1", LotterySsqConfig.expect)) {
			logger.info("后台任务生成过滤号码....");
			this.lotterySsqService.getCurrentExpertSingleResult();
			this.lotterySsqService.completCurrentGenCode();
			logger.info("生成过滤号码完成...........");
		}
	}
}
