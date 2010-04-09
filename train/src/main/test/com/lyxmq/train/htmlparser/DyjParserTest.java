package com.lyxmq.train.htmlparser;

import java.util.Random;

import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.RandomUtils;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.lyxmq.lottery.ssq.LotterySsqConifgService;
import com.lyxmq.lottery.ssq.LotterySsqCustomerDyjService;
import com.lyxmq.lottery.ssq.service.BaseTestCase;
import com.myfetch.service.http.util.HttpHtmlService;

public class DyjParserTest extends BaseTestCase{
	private static final Logger logger=LoggerFactory.getLogger(DyjParserTest.class);
	
	@Autowired
	private LotterySsqCustomerDyjService lotterySsqCustomerDyjService=null;
	public void testTradeHtml(){
		new LotterySsqConifgService();
		String url=LotterySsqConifgService.getDyjUrl();
		String dyjXmlData=HttpHtmlService.getXmlContent(url);
		logger.info(dyjXmlData);
		url="http://trade.cpdyj.com/Trade/download.asp?id=870758&lottype=3&playtype=3";
		String content=HttpHtmlService.getHtmlContent(url, "GB2312");
		logger.info(content+"\n"+StringUtils.indexOf(content, "\n")+" "+StringUtils.lastIndexOf(content, "\r\n"));
		
		url="http://trade.cpdyj.com/Trade/download.asp?id=877022&lottype=3&playtype=3";
		content=HttpHtmlService.getHtmlContent(url, "GB2312");
		logger.info(content);
	}
	@Test
	public void testLotterySsqCustomerDyjService(){
		lotterySsqCustomerDyjService.saveDyjProjectRedCode();
	}
	public static void main(String[] args) {
		//小数点后16位
		for(int i=0;i<1000;i++)
		{
			double rnd=RandomUtils.nextDouble();
			String d=ObjectUtils.toString(rnd);
			if(d.length()>=18){
				d=d.substring(0, 18);
			}else{
				for (int j = 1; j <=18; j++) {
					if(d.length()<j){
						d+="0";
					}
				}
			}
			logger.info(d+"  ");
		}
	}
}
