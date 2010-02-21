package com.lyxmq.lottery.football;

import java.util.List;

import org.slf4j.LoggerFactory;

public class FootballLotteryService {
	private static org.slf4j.Logger logger = LoggerFactory.getLogger(FootballLotteryService.class);

	private FootballLotteryDao footballLotteryDao = null;
	private static int count = 0;

	public void setFootballLotteryDao(FootballLotteryDao footballLotteryDao) {
		this.footballLotteryDao = footballLotteryDao;
	}

	private static final int FOOTBALLMATCH = 14;

	/**
	 * 生成所有可能性的号码
	 */
	public void genAllCode() {
		logger.info("开始生成-所有可能性的结果..........................");
		// 310出现的次数必须小于等于的个数
		int maxOne = 8;
		// 310出现的次数必须小于等于的个数
		int maxThree = 8;
		// 310出现的次数必须小于等于的个数
		int maxZero = 8;
		// 310出现的次数必须大于1次
		int minNum = 1;
		int temp = 0;
		for (int i = 0; i < FootballLotteryConstant.footBall.length; i++) {
			for (int j = 0; j < FootballLotteryConstant.footBall[i].length; j++) {
				for (int k = 0; k < FootballLotteryConstant.footBall.length; k++) {
					temp++;
				}
			}
		}
		System.out.println(temp);

	}

	public static void main(String[] args) {
		FootballLotteryService test = new FootballLotteryService();
		int totalNumber = 1;
		for (int i = 0; i < FootballLotteryConstant.footBall.length; i++) {
			totalNumber *= FootballLotteryConstant.footBall[i].length;
		}
//		test.doCalAllCode(0, 0, 1, "", 1, totalNumber);
		test.doCalAllCode(FootballLotteryConstant.footBall);
		System.out.println(count);
	}

	/**
	 * 可以使用递归的方法
	 * 
	 * @param startI
	 * @param startJ
	 * @param index
	 * @param currentResult
	 * @param totalNumber中注数
	 */
	private void doCalAllCode(int startI, int startJ, int index, String currentResult, int currentNumber, int totalNumber) {
		for (int i = startI; i < FootballLotteryConstant.footBall.length; i++) {
			for (int j = startJ; j < FootballLotteryConstant.footBall[i].length; j++) {
				if (currentNumber <= totalNumber && FOOTBALLMATCH == currentResult.length()) {
					// 完成了一次选号，并要求重新选号
					System.out.println(currentResult);
					count++;
					doCalAllCode(i + 1, 0, 1, "", currentNumber + 1, totalNumber);
					break;
				} else if (currentNumber <= totalNumber && index < FOOTBALLMATCH) {
					// 选号中
					currentResult += FootballLotteryConstant.footBall[i][j];
					doCalAllCode(i + 1, 0, index + 1, currentResult, currentNumber, totalNumber);
				} else {
					return;
				}
			}
		}
	}

	/**
	 * 最恶心的方法
	 * 
	 * @param footballResult
	 */
	private void doCalAllCode(String[][] footballResult) {
		String code1 = "";
		String code2 = "";
		String code3 = "";
		String code4 = "";
		String code5 = "";
		String code6 = "";
		String code7 = "";
		String code8 = "";
		String code9 = "";
		String code10 = "";
		String code11 = "";
		String code12 = "";
		String code13 = "";
		String code14 = "";
		for (int i = 0; i < footballResult.length; i++) {
			for (int j = 0; j < footballResult[i].length; j++) {
				code1 = footballResult[i][j];
				for (int j2 = 1; j2 < footballResult.length; j2++) {
					for (int k = 0; k < footballResult[j2].length; k++) {
						code2 = footballResult[j2][k];
						for (int k2 = 2; k2 < footballResult.length; k2++) {
							for (int l = 0; l < footballResult[k2].length; l++) {
								code3 = footballResult[k2][l];
								for (int l2 = 3; l2 < footballResult.length; l2++) {
									for (int m = 0; m < footballResult[l2].length; m++) {
										code4 = footballResult[l2][m];
										for (int m2 = 4; m2 < footballResult.length; m2++) {
											for (int n = 0; n < footballResult[m2].length; n++) {
												code5 = footballResult[m2][n];
												for (int n2 = 5; n2 < footballResult.length; n2++) {
													for (int o = 0; o < footballResult[n2].length; o++) {
														code6 = footballResult[n2][o];
														for (int o2 = 6; o2 < footballResult.length; o2++) {
															for (int p = 0; p < footballResult[o2].length; p++) {
																code7 = footballResult[o2][p];
																for (int p2 = 7; p2 < footballResult.length; p2++) {
																	for (int q = 0; q < footballResult[p2].length; q++) {
																		code8 = footballResult[p2][q];
																		for (int q2 = 8; q2 < footballResult.length; q2++) {
																			for (int r = 0; r < footballResult[q2].length; r++) {
																				code9 = footballResult[q2][r];
																				for (int r2 = 9; r2 < footballResult.length; r2++) {
																					for (int s = 0; s < footballResult[r2].length; s++) {
																						code10 = footballResult[r2][s];
																						for (int s2 = 10; s2 < footballResult.length; s2++) {
																							for (int t = 0; t < footballResult[s2].length; t++) {
																								code11 = footballResult[s2][t];
																								for (int t2 = 11; t2 < footballResult.length; t2++) {
																									for (int u = 0; u < footballResult[t2].length; u++) {
																										code12 = footballResult[t2][u];
																										for (int u2 = 12; u2 < footballResult.length; u2++) {
																											for (int v = 0; v < footballResult[u2].length; v++) {
																												code13 = footballResult[u2][v];
																												for (int v2 = 13; v2 < footballResult.length; v2++) {
																													for (int w = 0; w < footballResult[v2].length; w++) {
																														code14 = footballResult[v2][w];
																														if(count>2000000){
																															System.out.println(count);
																														}
																														count++;
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
