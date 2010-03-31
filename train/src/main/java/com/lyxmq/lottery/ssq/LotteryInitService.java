package com.lyxmq.lottery.ssq;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.slf4j.LoggerFactory;

import com.lyxmq.lottery.ssq.utils.LotterySsqMediaUtils;
import com.lyxmq.lottery.ssq.utils.LotteryUtils;
import com.myfetch.service.http.HttpHtmlService;

public class LotteryInitService {
	private static final org.slf4j.Logger logger = LoggerFactory.getLogger(LotteryInitService.class);
	LotteryDao dao = null;
	LotterySsqMediaService lotterySsqMediaService = null;

	public void setDao(LotteryDao dao) {
		this.dao = dao;
	}

	public void setLotterySsqMediaService(LotterySsqMediaService lotterySsqMediaService) {
		this.lotterySsqMediaService = lotterySsqMediaService;
	}

	public void saveAllRedResult() {
		logger.info("初始化数据................");
		select(6);
	}

	public void select(int k) {
		String[] result = new String[k];
		subselect(0, 1, result, k);

	}

	private void subselect(int head, int index, String[] r, int k) {
		for (int i = head; i < LotteryConstant.redBall.length + index - k; i++) {
			if (index < k) {
				r[index - 1] = LotteryConstant.redBall[i];
				subselect(i + 1, index + 1, r, k);
			} else if (index == k) {
				r[index - 1] = LotteryConstant.redBall[i];
				this.saveLottoryResult(StringUtils.join(r, ","));
				subselect(i + 1, index + 1, r, k);
			} else {
				return;
			}

		}
	}

	/**
	 * 一个区不能有超过4个的号码 不能有>4的连号 不能有3个三连号 不能同时存在三个差值为1或2的 如果号码分布在三个区，那么三个区的号码差值不能相同 TODO 六个数之间的5差值不能相等有<=4个差值以上
	 * 
	 * @param redCode
	 */
	private void saveLottoryResult(String redCode) {
		String[] codeSix = redCode.split(",");

		int qOne = 0;
		int qTwo = 0;
		int qThree = 0;
		for (int i = 0; i < codeSix.length; i++) {
			if (NumberUtils.toInt(codeSix[i]) <= 11) {
				qOne++;
			}
			if (NumberUtils.toInt(codeSix[i]) > 11 && NumberUtils.toInt(codeSix[i]) <= 22) {
				qTwo++;
			}
			if (NumberUtils.toInt(codeSix[i]) > 22 && NumberUtils.toInt(codeSix[i]) <= 33) {
				qThree++;
			}
		}

		if (qOne == 0 || qTwo == 0 || qThree == 0) {
			return;
		}
		if (qOne >= 4 || qTwo >= 4 || qThree >= 4) {
			return;
		}
		boolean isReturn = false;
		int lhCode = 0;
		int one = NumberUtils.toInt(codeSix[0]);
		int two = NumberUtils.toInt(codeSix[1]);
		int three = NumberUtils.toInt(codeSix[2]);
		int four = NumberUtils.toInt(codeSix[3]);
		int five = NumberUtils.toInt(codeSix[4]);
		int six = NumberUtils.toInt(codeSix[5]);

		if (two - one == 1) {
			lhCode++;
		}
		if (three - two == 1) {
			lhCode++;
		}
		if (four - three == 1) {
			lhCode++;
		}
		if (five - four == 1) {
			lhCode++;
		}
		if (six - five == 1) {
			lhCode++;
		}
		if (lhCode >= 3) {
			return;
		}
		int czCode = 0;
		if (two - one == 2) {
			czCode++;
		}
		if (three - two == 2) {
			czCode++;
		}
		if (four - three == 2) {
			czCode++;
		}
		if (five - four == 2) {
			czCode++;
		}
		if (six - five == 2) {
			czCode++;
		}
		if (czCode >= 3) {
			return;
		}
		czCode = 0;
		if (two - one == 3) {
			czCode++;
		}
		if (three - two == 3) {
			czCode++;
		}
		if (four - three == 3) {
			czCode++;
		}
		if (five - four == 3) {
			czCode++;
		}
		if (six - five == 3) {
			czCode++;
		}
		if (czCode >= 3) {
			return;
		}
		czCode = 0;
		int cz1 = two - one;
		int cz2 = three - two;
		int cz3 = four - three;
		int cz4 = five - four;
		int cz5 = six - five;
		int[] czs = { cz1, cz2, cz3, cz4, cz5 };
		for (int i = 0; i < 5; i++) {
			int tmpCount = 0;
			int tmp = czs[i];
			for (int j = 0; j < 5; j++) {
				if (tmp == czs[j]) {
					tmpCount++;
				}
			}
			if (tmpCount >= 4) {
				return;
			}
		}
		if (czCode >= 3) {
			return;
		}
		if (qOne == 2 && qTwo == 2 && qThree == 2 && ((two - one) == (four - three) && (four - three) == (six - five))) {
			return;
		}
		if (isReturn) {
			return;
		}
		this.dao.saveSsqLottoryResult(redCode);
	}

	/**
	 * 从当前配置URL补充媒体预测号码
	 */
	@SuppressWarnings("unchecked")
	public void initHistoryMediaStat() {
		new LotterySsqConifgService();
		String xmlData = HttpHtmlService.getXmlContent(LotterySsqConifgService.getXmlUrl());
		if (StringUtils.isBlank(xmlData)) {
			return;
		}
		Document document = null;
		try {
			document = DocumentHelper.parseText(xmlData);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		String expect = LotterySsqMediaUtils.getMediaExpect(document);
		int expectInt = NumberUtils.toInt(expect);
		String xmlContent = "";
		Map map=this.dao.getSsqLotteryHisMediaStatMaxExpect();
		String hisExpect="";
		if(MapUtils.isNotEmpty(map)){
			hisExpect=(String) map.get("expect");
		}
		for (int i = expectInt; i > NumberUtils.toInt(hisExpect); i--) {
			xmlContent = LotterySsqMediaUtils.getHistoryMediaXml(i < 10000 ? "0" + i : i + "");
			if (StringUtils.isNotBlank(xmlContent)) {
				this.lotterySsqMediaService.saveHistoryMediaStat(xmlContent,expectInt<10000?"0"+expectInt:expectInt+"");
			}
		}
		
		
	}
	
	/**
	 * 从当前配置URL补充历史开奖号码
	 */
	@SuppressWarnings("unchecked")
	public void initHistoryOpenCode() {
		new LotterySsqConifgService();
		String xmlData = HttpHtmlService.getXmlContent(LotterySsqConifgService.getXmlUrl());
		if (StringUtils.isBlank(xmlData)) {
			return;
		}
		Document document = null;
		try {
			document = DocumentHelper.parseText(xmlData);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		String expect = LotterySsqMediaUtils.getMediaExpect(document);
		int expectInt = NumberUtils.toInt(expect);
		String xmlContent = "";
		Map map=this.dao.getSsqLotteryHisOpenCodeMaxExpect();
		String hisExpect="";
		if(MapUtils.isNotEmpty(map)){
			hisExpect=(String) map.get("expect");
		}
		for (int i = expectInt; i > NumberUtils.toInt(hisExpect); i--) {
			xmlContent = LotterySsqMediaUtils.getHistoryMediaXml(i < 10000 ? "0" + i : i + "");
			if (StringUtils.isNotBlank(xmlContent)) {
				int sum=0;
				String[] redCode=LotterySsqMediaUtils.getHistoryOpenRedcode(document);
				if(redCode==null||redCode.length!=6){
					continue;
				}
				for (int j = 0; j < redCode.length; j++) {
					sum+=NumberUtils.toInt(redCode[j]);
				}
				this.dao.saveSsqLotteryHisOpenCode(LotterySsqMediaUtils.getHistoryOpenBlueCode(document),StringUtils.join(redCode, ","),sum,expectInt<10000?"0"+expectInt:expectInt+"");
			}
		}
		
		
	}
	/**
	 * 从文件中读取媒体历史预测号码
	 */
	public void initHistoryMediaStatForFile() {
		String path="D:/myproject/myselflearning/lottery/ssq_media/";
		File file=new File(path);
		File[] listFile=null;
		if(file.isDirectory()){
			listFile=file.listFiles();
		}
		if(listFile!=null){
			for (int i = 0; i < listFile.length; i++) {
				try {
					String xmlContent=LotteryUtils.getFileContent(listFile[i]);
					Document document = null;
					try {
						document = DocumentHelper.parseText(xmlContent);
					} catch (Exception e) {
						logger.error(e.getMessage());
						e.printStackTrace();
						logger.error(xmlContent);
					}
					String expect = LotterySsqMediaUtils.getMediaExpect(document);
					this.lotterySsqMediaService.saveHistoryMediaStat(xmlContent,expect);
				} catch (IOException e) {
					logger.error(listFile[i].getName()+"==="+e.getMessage());
				}
			}
		}
	}
	/**
	 * 从文件中读取媒体历史开奖号码
	 */
	public void initHistoryOpenCodeFromFile(){
		String path="D:/myproject/myselflearning/lottery/ssq_media/";
		File file=new File(path);
		File[] listFile=null;
		if(file.isDirectory()){
			listFile=file.listFiles();
		}
		if(listFile!=null){
			for (int i = 0; i < listFile.length; i++) {
				try {
					String xmlContent=LotteryUtils.getFileContent(listFile[i]);
					Document document = null;
					try {
						document = DocumentHelper.parseText(xmlContent);
					} catch (Exception e) {
						logger.error(e.getMessage());
						e.printStackTrace();
						logger.error(xmlContent);
					}
					String expect = LotterySsqMediaUtils.getMediaExpect(document);
					int sum=0;
					String[] redCode=LotterySsqMediaUtils.getHistoryOpenRedcode(document);
					for (int j = 0; j < redCode.length; j++) {
						sum+=NumberUtils.toInt(redCode[j]);
					}
					this.dao.saveSsqLotteryHisOpenCode(LotterySsqMediaUtils.getHistoryOpenBlueCode(document),StringUtils.join(redCode, ","),sum,expect);
				} catch (IOException e) {
					logger.error(listFile[i].getName()+"==="+e.getMessage());
				}
			}
		}
	}
}
