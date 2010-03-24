package com.lyxmq.lottery.ssq;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.slf4j.LoggerFactory;

public class LotteryInitService {
	private static final org.slf4j.Logger logger = LoggerFactory.getLogger(LotteryInitService.class);
	LotteryDao dao = null;

	public void setDao(LotteryDao dao) {
		this.dao = dao;
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
	 * 一个区不能有超过4个的号码
	 * 不能有>4的连号
	 * 不能有3个三连号
	 * 不能同时存在三个差值为1或2的
	 * 如果号码分布在三个区，那么三个区的号码差值不能相同
	 * TODO 六个数之间的5差值不能相等有<=4个差值以上
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
		if(qOne >= 4 || qTwo >=4 || qThree >=4){
			return;
		}
		boolean isReturn=false;
		int lhCode=0;
		int one=NumberUtils.toInt(codeSix[0]);
		int two=NumberUtils.toInt(codeSix[1]);
		int three=NumberUtils.toInt(codeSix[2]);
		int four=NumberUtils.toInt(codeSix[3]);
		int five=NumberUtils.toInt(codeSix[4]);
		int six=NumberUtils.toInt(codeSix[5]);
		
		if(two-one==1){
			lhCode++;
		}
		if(three-two==1){
			lhCode++;
		}
		if(four-three==1){
			lhCode++;
		}
		if(five-four==1){
			lhCode++;
		}
		if(six-five==1){
			lhCode++;
		}
		if(lhCode>=3){
			return;
		}
		int czCode=0;
		if(two-one==2){
			czCode++;
		}
		if(three-two==2){
			czCode++;
		}
		if(four-three==2){
			czCode++;
		}
		if(five-four==2){
			czCode++;
		}
		if(six-five==2){
			czCode++;
		}
		if(czCode>=3){
			return;
		}
		czCode=0;
		if(two-one==3){
			czCode++;
		}
		if(three-two==3){
			czCode++;
		}
		if(four-three==3){
			czCode++;
		}
		if(five-four==3){
			czCode++;
		}
		if(six-five==3){
			czCode++;
		}
		if(czCode>=3){
			return;
		}
		czCode=0;
		int cz1=two-one;
		int cz2=three-two;
		int cz3=four-three;
		int cz4=five-four;
		int cz5=six-five;
		int[] czs={cz1,cz2,cz3,cz4,cz5};
		for(int i=0;i<5;i++){
			int tmpCount=0;
			int tmp=czs[i];
			for(int j=0;j<5;j++){
				if(tmp==czs[j]){
					tmpCount++;
				}
			}
			if(tmpCount>=4){
				return ;
			}
		}
		if(czCode>=3){
			return;
		}
		if(qOne==2&&qTwo==2&&qThree==2&&((two-one)==(four-three)&&(four-three)==(six-five))){
			return;
		}
		if(isReturn){
			return;
		}
		this.dao.saveSsqLottoryResult(redCode);
	}
}

