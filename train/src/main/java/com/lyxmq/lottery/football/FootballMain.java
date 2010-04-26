package com.lyxmq.lottery.football;

import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.lyxmq.lottery.football.service.LotteryFootballCollectService;
import com.lyxmq.lottery.football.service.LotteryFootballInitService;
import com.lyxmq.lottery.football.service.LotteryFootballService;

public class FootballMain {
	private static Logger logger = LoggerFactory.getLogger(FootballMain.class);

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext(new String[] { "classpath:/spring/applicationContext.xml", "classpath:/lottery/football/applicationContext-database.xml", "classpath:/lottery/football/applicationContext-dao.xml",
				"classpath:/lottery/football/applicationContext-service.xml" });
		try {
			Scanner scanner = new Scanner(System.in);
			LotteryFootballInitService initService = (LotteryFootballInitService) context.getBean("initLotteryService");
			// initService.genAllCode();
			logger.info("开始抓取媒体数据，按n下一步，按任意键开始....................");
			String in = scanner.nextLine();
			if (!"n".equalsIgnoreCase(in)) {
				initService.collectMediaSfc();
			}
			LotteryFootballCollectService collectService = (LotteryFootballCollectService) context.getBean("footballCollectService");
			logger.info("开始抓取500wan，按n下一步，按任意键开始---足彩..................");
			in = scanner.nextLine();
			if (!"n".equalsIgnoreCase(in)) {
				collectService.collect500WanSfc();
			}
			logger.info("开始抓取2caiwan，按n下一步，按任意键开始---足彩..................");
			in = scanner.nextLine();
			if (!"n".equalsIgnoreCase(in)) {
				collectService.collectCaipiaoSfc();
			}
			logger.info("开始抓取大赢家，按n下一步，按任意键开始---足彩..................");
			in = scanner.nextLine();
			if (!"n".equalsIgnoreCase(in)) {
				collectService.collectDyjSfc();
			}

			LotteryFootballService service = (LotteryFootballService) context.getBean("ftLotteryService");
			service.filterFootballCode();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
