package com.lyxmq.lottery.ssq;

import java.util.Scanner;

import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 反向处理，把不存在媒体预测的结果选出来，两种方式 1、把媒体的结果都合并处理。 2、把媒体的结果分开处理。
 * 
 * @author Administrator
 */
public class ReverseTestMain {
	private static org.slf4j.Logger logger = LoggerFactory.getLogger(ReverseTestMain.class);

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext(new String[] { "classpath:/spring/applicationContext.xml", "classpath:/lottery/ssq/applicationContext-database.xml", "classpath:/lottery/ssq/applicationContext-dao.xml",
				"classpath:/lottery/ssq/applicationContext-service.xml" });
		try {
			Scanner scanner = new Scanner(System.in);
			LotteryInitService initService = (LotteryInitService) context.getBean("initLotteryService");
			long currentTime = System.currentTimeMillis();
			logger.info("按y开始开始初始化操作，按n跳过一步，60秒钟后默认开始初始化操作.....");
			String in="";
			while (true&&currentTime + 60000 > System.currentTimeMillis()) {
				in = scanner.nextLine();
				if ("n".equalsIgnoreCase(in)) {
					break;
				}
				if ("y".equalsIgnoreCase(in) || currentTime + 60000 < System.currentTimeMillis()) {
					// initService.saveAllRedResult();
					// initService.initHistoryMediaStatForFile();
					logger.info("开始抓取历史开奖号码..............");
					initService.initHistoryOpenCode();
					logger.info("开始抓取历史媒体推荐号码..............");
					initService.initHistoryMediaStat();
					logger.info("开始抓取本期500万媒体推荐号码..............");
					initService.fetchMedia500WanContent();
					logger.info("开始抓取本期sina媒体推荐号码..............");
					initService.fetchMediaSinaContent();
					break;
				}
				Thread.sleep(2000);
			}
			LotterySsqFetchService fetchservice = (LotterySsqFetchService) context.getBean("lotteryFetchService");
			currentTime = System.currentTimeMillis();
			logger.info("按y开始开始抓取大赢家的用户投注，按n跳过这一步，60秒钟后默认开始抓取大赢家用户投注.....");
			in = scanner.nextLine();
			while (true) {
				if ("n".equalsIgnoreCase(in)) {
					break;
				}
				if ("y".equalsIgnoreCase(in) || currentTime + 60000 < System.currentTimeMillis()) {
					fetchservice.fetchDyjCustomerProject();
					break;
				}
				Thread.sleep(2000);
			}
			logger.info("按y开始抓取500万的用户投注，按n跳过这一步，60秒钟后默认开始抓取500万用户投注.....");
			in = scanner.nextLine();
			while (true) {
				if ("n".equalsIgnoreCase(in)) {
					break;
				}
				if ("y".equalsIgnoreCase(in) || currentTime + 60000 < System.currentTimeMillis()) {
					fetchservice.fetch500WanCustomerProject();
					break;
				}
				Thread.sleep(2000);
			}

			LotterySsqService service = (LotterySsqService) context.getBean("lotteryService");
			// service.getCurrentExpertMergeResult();
			currentTime = System.currentTimeMillis();
			logger.info("按y开始开始抓取大赢家的用户投注，按n跳过这一步，60秒钟后默认开始抓取大赢家用户投注.....");
			in = scanner.nextLine();
			while (true) {
				if ("n".equalsIgnoreCase(in)) {
					break;
				}
				if ("y".equalsIgnoreCase(in) || currentTime + 60000 < System.currentTimeMillis()) {
					service.filterCurrentRedCode();
				}
			}
			service.getCurrentExpertSingleResult();
			// service.filterCurrentRedCodeFromFile();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
