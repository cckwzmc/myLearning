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
import com.lottery.ssq.fetch.exact.service.LotterySsqCustomerTaobaoService;
import com.lottery.ssq.fetch.service.LotterySsqCustomerTaobaoFetchService;
import com.lottery.ssq.service.LotteryInitService;
import com.lottery.ssq.service.LotterySsqCollectService;
import com.lottery.ssq.service.LotterySsqConifgService;
import com.lottery.ssq.service.LotterySsqFileService;
import com.lottery.ssq.service.LotterySsqService;
import com.lottery.ssq.service.LotterySsqWebCollectService;
import com.lottery.ssq.service.self.LotterySsqSelfService;

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
			LotterySsqCollectService collectservice = (LotterySsqCollectService) context.getBean("lotteryCollectService");
			LotterySsqService service = (LotterySsqService) context.getBean("lotteryService");
			LotteryInitService initService = (LotteryInitService) context.getBean("initLotteryService");
			LotterySsqFileService lotterySsqFileService = (LotterySsqFileService) context.getBean("lotterySsqFileService");
			lotterySsqConifgService.initSsqConfig();
			lotterySsqConifgService.initFetchConfig();
			lotterySsqConifgService.initFilterConfig(null);
			ISsqLotteryFetch ssqLotterySohuFetchImpl = (ISsqLotteryFetch) context.getBean("ssqLotterySohuFetchImpl");
			ISsqLotteryFetch ssqLottery163FetchImpl = (ISsqLotteryFetch) context.getBean("ssqLottery163FetchImpl");
			ISsqLotteryFetch ssqLotterySinaFetchImpl = (ISsqLotteryFetch) context.getBean("ssqLotterySinaFetchImpl");
			LotterySsqWebCollectService lotterySsqWebCollectService = (LotterySsqWebCollectService) context.getBean("lotterySsqWebCollectService");
			LotterySsqSelfService lotterySsqSelfService = (LotterySsqSelfService) context.getBean("lotterySsqSelfService");
			// LotterySsqCustomerTaobaoFetchService lotterySsqCustomerTaobaoFetchService = (LotterySsqCustomerTaobaoFetchService) context.getBean("lotterySsqCustomerTaobaoFetchService");
			// LotterySsqCustomerTaobaoService lotterySsqCustomerTaobaoService = (LotterySsqCustomerTaobaoService) context.getBean("lotterySsqCustomerTaobaoService");
			// lotterySsqCustomerTaobaoFetchService.saveFecthTaobaoData();
			// lotterySsqCustomerTaobaoService.saveTaobaoProjectCode();
			// service.getCurrentExpertSingleResult(lotterySsqConifgService.initFilterConfig());
			// initService.testHistoryRedCode();
			// ssqLotterySohuFetchImpl.getSsqLotteryDetail("", "");
			// initService.initFilterResult();
			// collectservice.collectResultDispose();
			Set<String> codes = getCodeFromFile("D:/ttcode.txt");
			if (CollectionUtils.isNotEmpty(codes)) {
				service.genFilterRedCodeFromCollectResult(lotterySsqConifgService.initFilterConfig(null), codes);
			}
			Set<String> filterCode = getCodeFromFile("D:/ttcode_filter.txt");
			if (CollectionUtils.isNotEmpty(filterCode)) {
				service.getCurrentExpertSingleResult(lotterySsqConifgService.initFilterConfig(null), filterCode);
			}
			 lotterySsqSelfService.filterLastResult();
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
			// initService.hisDrawsRedcode("02,03,13,19,20,23","10055");
			// initService.hisDrawsRedcode("01,06,08,12,14,25","10054");
			// initService.hisDrawsRedcode("03,22,24,27,28,30","10053");
			// initService.hisDrawsRedcode("01,02,19,23,27,29","10055");
			// initService.hisDrawsRedcode("01,02,03,08,13,32","10056");
			// initService.hisDrawsRedcode("05,11,12,19,25,32","10057");
			// initService.hisDrawsRedcode("01,04,11,17,19,29","10058");
			// initService.hisDrawsRedcode("01,09,11,12,18,30","10059");
			// initService.hisDrawsRedcode("01,08,15,18,22,27","10060");
			// initService.hisDrawsRedcode("02,04,16,19,22,26","10061");
			// initService.hisDrawsRedcode("11,13,15,20,31,33","10062");
			// initService.hisDrawsRedcode("02,06,10,17,23,24","10063");
			// initService.hisDrawsRedcode("08,14,25,26,30,31","10064");
			// initService.hisDrawsRedcode("02,07,08,17,30,32","10065");
			// initService.hisDrawsRedcode("01,03,21,22,31,32","10066");
			// initService.hisDrawsRedcode("01,07,10,14,21,25","10067");
			// initService.hisDrawsRedcode("03,13,18,20,23,28","10068");
			// initService.hisDrawsRedcode("12,14,20,22,24,32","10069");
			// initService.hisDrawsRedcode("08,13,23,27,31,32","10070");
			// initService.hisDrawsRedcode("05,13,14,27,20,26","10071");
			// initService.hisDrawsRedcode("06,08,22,23,30,31","10072");
			// initService.hisDrawsRedcode("01,16,20,23,27,31","10073");
			// initService.hisDrawsRedcode("02,10,17,18,19,29", "10074");
			// initService.hisDrawsRedcode("05,09,12,13,15,22", "10075");
			// initService.hisDrawsRedcode("01,12,14,29,31,32", "10076");
			// initService.hisDrawsRedcode("02,08,14,20,21,24", "10077");
			// initService.hisDrawsRedcode("01,03,09,11,17,23", "10078");
			// initService.hisDrawsRedcode("08,11,12,14,18,22", "10079");
			// initService.hisDrawsRedcode("08,10,13,14,16,23", "10080");
			// initService.hisDrawsRedcode("02,03,09,24,26,27", "10081");
			// initService.hisDrawsRedcode("01,08,13,14,27,31", "10082");
			// initService.hisDrawsRedcode("02,20,21,22,23,31", "10083");
			// initService.hisDrawsRedcode("02,10,14,18,20,30", "10084");
			// initService.hisDrawsRedcode("01,08,12,13,24,27", "10085");
			// initService.hisDrawsRedcode("05,21,28,29,30,31", "10086");
			// initService.hisDrawsRedcode("01,08,16,17,25,30", "10087");
			// initService.hisDrawsRedcode("05,06,15,23,27,30", "10087");
			// initService.hisDrawsRedcode("07,08,09,16,23,26", "10089");
			// initService.hisDrawsRedcode("01,06,10,15,25,31", "10090");

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
			// initService.hisDrawsRedcode("02,03,13,19,20,23","10055");
			// initService.hisDrawsRedcode("01,06,08,12,14,25","10054");
			// initService.hisDrawsRedcode("03,22,24,27,28,30","10053");
			// initService.hisDrawsRedcode("01,02,19,23,27,29","10055");
			// initService.hisDrawsRedcode("01,02,03,08,13,32","10056");
			// initService.hisDrawsRedcode("05,11,12,19,25,32","10057");
			// initService.hisDrawsRedcode("01,04,11,17,19,29","10058");
			// initService.hisDrawsRedcode("01,09,11,12,18,30","10059");
			// initService.hisDrawsRedcode("01,08,15,18,22,27","10060");
			// initService.hisDrawsRedcode("02,04,16,19,22,26","10061");
			// initService.hisDrawsRedcode("11,13,15,20,31,33","10062");
			// initService.hisDrawsRedcode("02,06,10,17,23,24","10063");
			// initService.hisDrawsRedcode("08,14,25,26,30,31","10064");
			// initService.hisDrawsRedcode("02,07,08,17,30,32","10065");
			// initService.hisDrawsRedcode("01,03,21,22,31,32","10066");
			// initService.hisDrawsRedcode("01,07,10,14,21,25","10067");
			// initService.hisDrawsRedcode("03,13,18,20,23,28","10068");
			// initService.hisDrawsRedcode("12,14,20,22,24,32","10069");
			// initService.hisDrawsRedcode("01,02,19,23,27,29","");

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
