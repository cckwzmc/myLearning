package com.lottery.ssq;

import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.w3c.dom.Node;

import com.lottery.ssq.service.LotterySsqConifgService;
import com.lottery.ssq.service.LotterySsqCustomer500WanService;
import com.lottery.ssq.service.LotterySsqCustomerCaipiaoService;

public class Test {
	private static org.slf4j.Logger logger = LoggerFactory.getLogger(ReverseTestMain.class);

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext(new String[] { "classpath:/spring/applicationContext.xml", "classpath:/lottery/ssq/applicationContext-database.xml", "classpath:/lottery/ssq/applicationContext-dao.xml",
				"classpath:/lottery/ssq/applicationContext-service.xml" });
		try {
			LotterySsqConifgService lotterySsqConifgService = (LotterySsqConifgService) context.getBean("lotterySsqConifgService");
			lotterySsqConifgService.initFetchConfig();
			lotterySsqConifgService.initFilterConfig(filterConfig);
			LotterySsqCustomer500WanService ssq500wanService= (LotterySsqCustomer500WanService) context.getBean("lotterySsqCustomer500WanService");
			LotterySsqCustomerCaipiaoService ssq2CaiService= (LotterySsqCustomerCaipiaoService) context.getBean("lotterySsqCustomerCaipiaoService");
//			ssq500wanService.save500WanProjectRedCode();
			ssq2CaiService.fetchCaipiaoProjectCode();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void print(Node node, String indent) {
		System.out.println(node.getTextContent());
	}

}