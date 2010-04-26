package com.lyxmq.lottery.football.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lyxmq.lottery.football.dao.LotteryFootballDao;
import com.lyxmq.lottery.football.util.FootballLotteryUtils;

public class LotteryFootballInitService {
	private static Logger logger = LoggerFactory.getLogger(LotteryFootballInitService.class);
	private LotteryFootballCustomer500WanService lotteryFootballCustomer500WanService = null;

	public void setLotteryFootballCustomer500WanService(LotteryFootballCustomer500WanService lotteryFootballCustomer500WanService) {
		this.lotteryFootballCustomer500WanService = lotteryFootballCustomer500WanService;
	}

	private LotteryFootballDao footballLotteryDao = null;

	public void setFootballLotteryDao(LotteryFootballDao footballLotteryDao) {
		this.footballLotteryDao = footballLotteryDao;
	}

	private List<String> footballList = new ArrayList<String>();

	private void initConfig() {
		new LotteryFootballConifgService();
	}

	/**
	 * 生成所有可能性的号码
	 */
	public void genAllCode() {
		logger.info("开始生成-所有可能性的结果..........................");
		this.doCallAllCode();

	}

	private void doCallAllCode() {
		for (int i = 0; i < LotteryFootballConstant.footBall[0].length; i++) {
			String tempI = LotteryFootballConstant.footBall[0][i];
			for (int j = 0; j < LotteryFootballConstant.footBall[1].length; j++) {
				String tempJ = LotteryFootballConstant.footBall[1][j];
				for (int j2 = 0; j2 < LotteryFootballConstant.footBall[2].length; j2++) {
					String tempJ2 = LotteryFootballConstant.footBall[2][j2];
					for (int k = 0; k < LotteryFootballConstant.footBall[3].length; k++) {
						String tempK = LotteryFootballConstant.footBall[3][k];
						for (int k2 = 0; k2 < LotteryFootballConstant.footBall[4].length; k2++) {
							String tempK2 = LotteryFootballConstant.footBall[4][k2];
							for (int l = 0; l < LotteryFootballConstant.footBall[5].length; l++) {
								String tempL = LotteryFootballConstant.footBall[5][l];
								for (int l2 = 0; l2 < LotteryFootballConstant.footBall[6].length; l2++) {
									String tempL2 = LotteryFootballConstant.footBall[6][l2];
									for (int m = 0; m < LotteryFootballConstant.footBall[7].length; m++) {
										String tempM = LotteryFootballConstant.footBall[7][m];
										for (int m2 = 0; m2 < LotteryFootballConstant.footBall[8].length; m2++) {
											String tempM2 = LotteryFootballConstant.footBall[8][m2];
											for (int n = 0; n < LotteryFootballConstant.footBall[9].length; n++) {
												String tempN = LotteryFootballConstant.footBall[9][n];
												for (int n2 = 0; n2 < LotteryFootballConstant.footBall[10].length; n2++) {
													String tempN2 = LotteryFootballConstant.footBall[10][n2];
													for (int o = 0; o < LotteryFootballConstant.footBall[11].length; o++) {
														String tempO = LotteryFootballConstant.footBall[11][o];
														for (int o2 = 0; o2 < LotteryFootballConstant.footBall[12].length; o2++) {
															String tempO2 = LotteryFootballConstant.footBall[12][o2];
															for (int p = 0; p < LotteryFootballConstant.footBall[13].length; p++) {
																String tempP = LotteryFootballConstant.footBall[13][p];
																String result = tempI + "," + tempJ + "," + tempJ2 + "," + tempK + "," + tempK2 + "," + tempL + "," + tempL2 + "," + tempM + "," + tempM2 + "," + tempN + "," + tempN2 + "," + tempO + "," + tempO2 + "," + tempP;
																this.disposeResultData(result);
															}
														}
													}
												}
											}
										}
									}
								}
							}
						}
					}
				}
			}
		}
	}

	/**
	 * @param result
	 */
	private void disposeResultData(String result) {
		if (FootballLotteryUtils.isAccordBasicRequire(result)) {
			footballList.add(result);
		} else {
			return;
		}
		if (footballList.size() > 5000) {
			this.footballLotteryDao.batchSaveFootballLottoryAllResult(footballList);
			footballList.clear();
		}
		if (CollectionUtils.isNotEmpty(footballList)) {
			this.footballLotteryDao.batchSaveFootballLottoryAllResult(footballList);
			footballList.clear();
		}
	}

	/**
	 * 媒体胜负彩原始资料收集
	 */
	public void collectMediaSfc() {
		this.initConfig();
		if(this.footballLotteryDao.getCountFootballFetchMedia(LotteryFootballConifgService.getFootballExpect(),"0")==0)
		{
			String content = this.lotteryFootballCustomer500WanService.getCurrentExpectXmlDataFromNet(LotteryFootballConifgService.getFootballExpect());
			this.footballLotteryDao.saveFootballFetchMedia("0",content,LotteryFootballConifgService.getFootballExpect());
		}
	}

}
