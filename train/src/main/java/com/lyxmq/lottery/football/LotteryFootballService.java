package com.lyxmq.lottery.football;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LotteryFootballService {
	private static Logger logger = LoggerFactory.getLogger(LotteryFootballService.class);

	private LotteryFootballDao footballLotteryDao = null;
	private LotteryFootballFileService fileLotteryService=null;
	private LotteryFootballMedia500WanService lotteryFootballMedia500WanService=null; 
	
	public void setLotteryFootballMedia500WanService(LotteryFootballMedia500WanService lotteryFootballMedia500WanService) {
		this.lotteryFootballMedia500WanService = lotteryFootballMedia500WanService;
	}

	public void setFootballLotteryDao(LotteryFootballDao footballLotteryDao) {
		this.footballLotteryDao = footballLotteryDao;
	}

	public void setFileLotteryService(LotteryFootballFileService fileLotteryService) {
		this.fileLotteryService = fileLotteryService;
	}

	public void filterFootballCode(){
		fileLotteryService.saveFootballFileCode();
		this.lotteryFootballMedia500WanService.saveCurrentExpectFootballMedia();
	}
	@SuppressWarnings("unchecked")
	private void filterMedia(List<String> filterCode, List list) {
		for (Iterator iterator = list.iterator(); iterator.hasNext();) {
			Map map = (Map) iterator.next();
			String code=ObjectUtils.toString(map.get("value"));
			if(filterCode.contains(code)){
				filterCode.remove(code);
			}else {	
				this.footballLotteryDao.saveFootballLottoryFilterResult(code);
			}
		}
		
	}
}
