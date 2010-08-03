package com.lottery.ssq;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.LinkedHashSet;
import java.util.Scanner;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.lottery.ssq.fetch.ISsqLotteryFetch;
import com.lottery.ssq.service.LotteryInitService;
import com.lottery.ssq.service.LotterySsqCollectService;
import com.lottery.ssq.service.LotterySsqConifgService;
import com.lottery.ssq.service.LotterySsqFileService;
import com.lottery.ssq.service.LotterySsqService;
import com.lottery.ssq.service.LotterySsqWebCollectService;

/**
 * 反向处理，把不存在媒体预测的结果选出来，两种方式 1、把媒体的结果都合并处理。 2、把媒体的结果分开处理。
 * 
 * @author Administrator
 */
public class ReverseTestMain {
	private static org.slf4j.Logger logger = LoggerFactory.getLogger(ReverseTestMain.class);

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext(new String[] {
				"classpath:/spring/applicationContext.xml", "classpath:/lottery/ssq/applicationContext-database.xml",
				"classpath:/lottery/ssq/applicationContext-dao.xml",
				"classpath:/lottery/ssq/applicationContext-service.xml" });
		try {
			LotterySsqConifgService lotterySsqConifgService = (LotterySsqConifgService) context
					.getBean("lotterySsqConifgService");
			LotterySsqCollectService collectservice = (LotterySsqCollectService) context
					.getBean("lotteryCollectService");
			LotterySsqService service = (LotterySsqService) context.getBean("lotteryService");
			LotteryInitService initService = (LotteryInitService) context.getBean("initLotteryService");
			LotterySsqFileService lotterySsqFileService = (LotterySsqFileService) context
					.getBean("lotterySsqFileService");
			lotterySsqConifgService.initSsqConfig();
			lotterySsqConifgService.initFetchConfig();
			lotterySsqConifgService.initFilterConfig();
			ISsqLotteryFetch ssqLotterySohuFetchImpl = (ISsqLotteryFetch) context.getBean("ssqLotterySohuFetchImpl");
			ISsqLotteryFetch ssqLottery163FetchImpl = (ISsqLotteryFetch) context.getBean("ssqLottery163FetchImpl");
			ISsqLotteryFetch ssqLotterySinaFetchImpl = (ISsqLotteryFetch) context.getBean("ssqLotterySinaFetchImpl");
			LotterySsqWebCollectService lotterySsqWebCollectService = (LotterySsqWebCollectService) context
					.getBean("lotterySsqWebCollectService");
			// initService.testHistoryRedCode();
			// ssqLotterySohuFetchImpl.getSsqLotteryDetail("", "");
			// initService.initFilterResult();
			// collectservice.collectResultDispose();
			// Set<String> codes = getCodeFromFile("D:/ttcode.txt");
			// if (CollectionUtils.isNotEmpty(codes)) {
			// service.genFilterRedCodeFromCollectResult(lotterySsqConifgService.initFilterConfig(), codes);
			// }
			Set<String> filterCode = getCodeFromFile("D:/ttcode_filter.txt");
			if (CollectionUtils.isNotEmpty(filterCode)) {
				service.getCurrentExpertSingleResult(lotterySsqConifgService.initFilterConfig(), filterCode);
			}
			// service.filterCurrentRedCodeFirst();
			// initService.initFilterResult();
			// service.getCurrentExpertSingleResult(lotterySsqConifgService.initFilterConfig());
			// ssqLotterySohuFetchImpl.getSsqLotteryDetail("", "");
			// ssqLottery163FetchImpl.getSsqLotteryDetail("", "");
			// ssqLotterySinaFetchImpl.getSsqLotteryDetail("", "");
			// lotterySsqWebCollectService.saveSsqWebCollect();
			// service.getCurrentExpertSingleResult();
			// lotterySsqConifgService.initFetchConfig();
			// lotterySsqConifgService.initFilterConfig();

			// service.filterCurrentRedCodeFirst();
			// collectservice.collectResultDispose();
			// lotterySsqFileService.deleteFileRedCode();
			// initService.deleteMediaFetch();
			// LotterySsqCustomerCaipiaoService caipiao = (LotterySsqCustomerCaipiaoService)
			// context.getBean("lotterySsqCustomerCaipiaoService");
			// caipiao.fetchCaipiaoProjectCode();
			// initService.fetchMediaSinaContent();
			// initService.fetchMedia500WanContent();
			// initService.tempDeleteMediaRedCodeFromFilter();
			// initService.initFilterResult();
			// 看中奖号码在用户投注中的表现

			// lotterySsqFileService.deleteFileRedCode();
			// initService.deleteMediaFetch();
			// LotterySsqCustomerCaipiaoService caipiao = (LotterySsqCustomerCaipiaoService)
			// context.getBean("lotterySsqCustomerCaipiaoService");
			// caipiao.fetchCaipiaoProjectCode();
			// initService.fetchMediaSinaContent();
			// initService.fetchMedia500WanContent();
			// initService.tempDeleteMediaRedCodeFromFilter();
			// service.clearHisSsqData();
			// collectservice.backHisSsqCollectResult();
			// initService.initFilterResult();
			// 看中奖号码在用户投注中的表现

			// collectservice.fetchDyjCustomerProject();
			Scanner scanner = new Scanner(System.in);
			long currentTime = System.currentTimeMillis();
			logger.info("按y开始开始初始化操作，按n跳过一步，60秒钟后默认开始初始化操作.....");
			String in = "";
			in = scanner.nextLine();
			while (true && currentTime + 60000 > System.currentTimeMillis()) {
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
			// collectservice.deleteFilterResultFromCollect();
			// LotterySsqService service = (LotterySsqService) context.getBean("lotteryService");
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
			// service.getCurrentExpertSingleResult();
			// service.filterCurrentRedCodeFromFile();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private static Set<String> getCodeFromFile(String name) {
		Set<String> list = new LinkedHashSet<String>();
		String fileName = name;
		try {
			File file = new File(fileName);
			FileReader read = new FileReader(file);
			BufferedReader br = new BufferedReader(read);
			boolean start = true;
			String line = "abc";
			while (StringUtils.isNotBlank(line)) {
				line = br.readLine();
				if (StringUtils.isNotBlank(line)) {
					list.add(line);
				}
			}
			read.close();
		} catch (FileNotFoundException e) {
			logger.error(fileName + "没有找到..............");
		} catch (UnsupportedEncodingException e) {
			logger.error(fileName + "编码有问题..............");
		} catch (IOException e) {
			logger.error(fileName + "+++++" + e.getMessage() + "..............");
		}
		return list;
	}
}
// class TimeScanner extends Thread{
// Scanner scanner=null;
// public TimeScanner(Scanner scanner){
// this.scanner=scanner;
// start();
// }
// public void run(){
// long currentTime = System.currentTimeMillis();
// try{
// while(true){
// if(currentTime + 5000 < System.currentTimeMillis())
// {
// break;
// }
// }
// scanner.reclose();
// }catch(Exception e){
// scanner.close();
// }
// }
// }
