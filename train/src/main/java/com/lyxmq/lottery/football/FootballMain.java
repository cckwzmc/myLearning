package com.lyxmq.lottery.football;

import java.util.Scanner;

import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.lyxmq.lottery.football.service.LotteryFootballCollectService;
import com.lyxmq.lottery.football.service.LotteryFootballInitService;
import com.lyxmq.lottery.football.service.LotteryFootballService;

public class FootballMain {
	private static org.slf4j.Logger logger = LoggerFactory.getLogger(FootballMain.class);

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext(new String[] { "classpath:/spring/applicationContext.xml", "classpath:/lottery/football/applicationContext-database.xml", "classpath:/lottery/football/applicationContext-dao.xml",
				"classpath:/lottery/football/applicationContext-service.xml" });
		try {
			Scanner scanner=new Scanner(System.in);
			String in=scanner.nextLine();
			LotteryFootballInitService initService = (LotteryFootballInitService) context.getBean("initLotteryService");
//			initService.genAllCode();
			logger.info("开始抓取媒体数据....................");
			if(!"n".equalsIgnoreCase(in))
			{
				initService.collectMediaSfc();
			}
			LotteryFootballCollectService collectService= (LotteryFootballCollectService) context.getBean("footballCollectService");
			logger.info("开始抓取500wan---足彩..................");
			if(!"n".equalsIgnoreCase(in)){
				collectService.collect500WanSfc();
			}
			logger.info("开始抓取2caiwan---足彩..................");
			if(!"n".equalsIgnoreCase(in)){
				collectService.collect500WanSfc();
			}
			
			LotteryFootballService service = (LotteryFootballService) context.getBean("ftLotteryService");
			service.filterFootballCode();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
