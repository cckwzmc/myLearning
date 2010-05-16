package com.lottery.ssq;

import java.util.Scanner;

import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.lottery.ssq.service.LotteryInitService;
import com.lottery.ssq.service.LotterySsqCollectService;
import com.lottery.ssq.service.LotterySsqConifgService;
import com.lottery.ssq.service.LotterySsqService;

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
			LotterySsqConifgService lotterySsqConifgService = (LotterySsqConifgService) context.getBean("lotterySsqConifgService");
			lotterySsqConifgService.initFetchConfig();
			lotterySsqConifgService.initFilterConfig();
			
			LotteryInitService initService = (LotteryInitService) context.getBean("initLotteryService");
//			initService.tempDeleteMediaRedCodeFromFilter();
//			initService.initFilterResult();
			initService.hisDrawsRedcode("02,03,13,19,20,23");
			Scanner scanner = new Scanner(System.in);
			long currentTime = System.currentTimeMillis();
			logger.info("按y开始开始初始化操作，按n跳过一步，60秒钟后默认开始初始化操作.....");
			String in="";
			in = scanner.nextLine();
			while (true&&currentTime + 60000 > System.currentTimeMillis()) {
				if ("n".equalsIgnoreCase(in)) {
					break;
				}
				if ("y".equalsIgnoreCase(in) || currentTime + 60000 < System.currentTimeMillis()) {
					logger.info("开始抓取历史开奖号码..............");
					initService.initHistoryOpenCode();
					logger.info("开始抓取历史媒体推荐号码..............");
					initService.initHistoryMediaStat();
					logger.info("开始抓取本期500万媒体推荐号码..............");
					initService.fetchMedia500WanContent();
					logger.info("开始抓取本期sina媒体推荐号码..............");
					initService.fetchMediaSinaContent();
					logger.info("开始抓取本期sina媒体推荐红球胆..............");
					initService.fetchMediaSinaDan();
					break;
				}
				Thread.sleep(2000);
			}
			LotterySsqCollectService collectservice = (LotterySsqCollectService) context.getBean("lotteryCollectService");
			currentTime = System.currentTimeMillis();
			logger.info("按y开始开始抓取大赢家的用户投注，按n跳过这一步，60秒钟后默认开始抓取大赢家用户投注.....");
			in = scanner.nextLine();
			while (true) {
				if ("n".equalsIgnoreCase(in)) {
					break;
				}
				if ("y".equalsIgnoreCase(in) || currentTime + 60000 < System.currentTimeMillis()) {
					collectservice.fetchDyjCustomerProject();
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
					collectservice.fetch500WanCustomerProject();
					break;
				}
				Thread.sleep(2000);
			}
			logger.info("按y开始读取文本格式的手工收集信息，按n跳过这一步，60秒钟后默认自动开始.....");
			in = scanner.nextLine();
			while (true) {
				if ("n".equalsIgnoreCase(in)) {
					break;
				}
				if ("y".equalsIgnoreCase(in) || currentTime + 60000 < System.currentTimeMillis()) {
					collectservice.collectFileProject();
					break;
				}
				Thread.sleep(2000);
			}
			logger.info("按y开始计算采集数据，按n跳过这一步，60秒钟后默认自动开始.....");
			in = scanner.nextLine();
			while (true) {
				if ("n".equalsIgnoreCase(in)) {
					break;
				}
				if ("y".equalsIgnoreCase(in)) {
					collectservice.collectResultDispose();
					break;
				}
				Thread.sleep(2000);
			}
//			collectservice.deleteFilterResultFromCollect();
			LotterySsqService service = (LotterySsqService) context.getBean("lotteryService");
			// service.getCurrentExpertMergeResult();
			currentTime = System.currentTimeMillis();
			logger.info("开始生成过滤号码，按y开始，按n跳过这一步，60秒钟后默认自动开始.....");
			in = scanner.nextLine();
			while (true) {
				if ("n".equalsIgnoreCase(in)) {
					break;
				}
				if ("y".equalsIgnoreCase(in)) {
					service.filterCurrentRedCode();
				}
			}
			logger.info("开始生成投注号码...。。。。。。。。。。。。..");
			service.getCurrentExpertSingleResult();
			// service.filterCurrentRedCodeFromFile();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
//class TimeScanner extends Thread{
//	Scanner scanner=null;
//	public TimeScanner(Scanner scanner){
//		this.scanner=scanner;
//		start();
//	}
//	public void run(){
//		long currentTime = System.currentTimeMillis();
//		try{
//			while(true){
//				if(currentTime + 5000 < System.currentTimeMillis())
//				{
//					break;
//				}
//			}
//			scanner.reclose();
//		}catch(Exception e){
//			scanner.close();
//		}
//	}
//}
