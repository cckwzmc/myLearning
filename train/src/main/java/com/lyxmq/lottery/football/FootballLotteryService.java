package com.lyxmq.lottery.football;

import java.util.List;

import org.apache.commons.lang.StringUtils;
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
		// int totalNumber = 1;
		// for (int i = 0; i < FootballLotteryConstant.footBall.length; i++) {
		// totalNumber *= FootballLotteryConstant.footBall[i].length;
		// }
		// test.doCalAllCode(0, 0, 1, "", 1, totalNumber);
		test.doCallAllCode();
		System.out.println(count);
	}

	private void doCallAllCode() {
		for (int i = 0; i < FootballLotteryConstant.footBall[0].length; i++) {
			String tempI=FootballLotteryConstant.footBall[0][i];
			for (int j = 0; j < FootballLotteryConstant.footBall[1].length; j++) {
				String tempJ=FootballLotteryConstant.footBall[0][j];
				for (int j2 = 0; j2 < FootballLotteryConstant.footBall[2].length; j2++) {
					String tempJ2=FootballLotteryConstant.footBall[0][j2];
					for (int k = 0; k < FootballLotteryConstant.footBall[3].length; k++) {
						String tempK=FootballLotteryConstant.footBall[0][k];
						for (int k2 = 0; k2 < FootballLotteryConstant.footBall[4].length; k2++) {
							String tempK2=FootballLotteryConstant.footBall[0][k2];
							for (int l = 0; l < FootballLotteryConstant.footBall[5].length; l++) {
								String tempL=FootballLotteryConstant.footBall[0][l];
								for (int l2 = 0; l2 < FootballLotteryConstant.footBall[6].length; l2++) {
									String tempL2=FootballLotteryConstant.footBall[0][l2];
									for (int m = 0; m < FootballLotteryConstant.footBall[7].length; m++) {
										String tempM=FootballLotteryConstant.footBall[0][m];
										for (int m2 = 0; m2 < FootballLotteryConstant.footBall[8].length; m2++) {
											String tempM2=FootballLotteryConstant.footBall[0][m2];
											for (int n = 0; n < FootballLotteryConstant.footBall[9].length; n++) {
												String tempN=FootballLotteryConstant.footBall[0][n];
												for (int n2 = 0; n2 < FootballLotteryConstant.footBall[10].length; n2++) {
													String tempN2=FootballLotteryConstant.footBall[0][n2];
													for (int o = 0; o < FootballLotteryConstant.footBall[11].length; o++) {
														String tempO=FootballLotteryConstant.footBall[0][o];
														for (int o2 = 0; o2 < FootballLotteryConstant.footBall[12].length; o2++) {
															String tempO2=FootballLotteryConstant.footBall[0][o2];
															for (int p = 0; p < FootballLotteryConstant.footBall[13].length; p++) {
																String tempP=FootballLotteryConstant.footBall[0][p];
																String result=tempI+","+tempJ+","+tempJ2+","+tempK+","+tempK2+","+tempL+","+tempL2+","+tempM+","+tempM2+","+tempN+","+tempN2+","+tempO+","+tempO2+","+tempP;
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

	private void disposeResultData(String result) {
		String[] rs=result.split(",");
		//排除>=8个相同号码的组合
		//排除连续>=5个相同号码的组合
		int sameOne=0;
		int sameThree=0;
		int sameTwo=0;
		int cs=0;
		for (int i = 0; i < rs.length; i++) {
			if("0".equals(rs[i])){
				sameOne++;
			}
			if("1".equals(rs[i])){
				sameTwo++;
			}
			if("3".equals(rs[i])){
				sameThree++;
			}
			if(i>0){
				if(StringUtils.equals(rs[i-1], rs[i])){
					cs++;
				}else if(cs<4){
					cs=0;
				}
			}
		}
		if(sameOne<8&&sameThree<8&&sameTwo<8&&cs<4)
		{
			count++;
			System.out.println(count+"===="+result);
		}
	}

	/**
	 * 可以使用递归的方法 8个相同的数字以上的都要剔除
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
	 * @param index1
	 *            第一个号码
	 * @param index1_1第一个号码
	 *            中子号码
	 * @param index2
	 *            第二个号码
	 * @param index2_2
	 *            第二个号码 中的子号码
	 * @param result
	 *            一注的结果
	 * @param selectedNum
	 *            要求选几个号码
	 * @param curSelectNum
	 *            已选择了几个号码
	 */
	private void doCalFootballCode(int index1, int index1_1, int index2, int index2_2, String result, int selectedNum, int curSelectNum) {
		for (int i = index1; i < FootballLotteryConstant.footBall.length; i++) {
			// 循环2 begin
			for (int n = index1_1; n < FootballLotteryConstant.footBall[index1].length; n++) {
				// 循环3 begin
				for (int j = index2; j < FootballLotteryConstant.footBall.length; j++) {
					// 循环4 begin
					for (int k = index2_2; k < FootballLotteryConstant.footBall[index2].length; k++) {
						if (selectedNum == curSelectNum) {
							System.out.println(result);
							break;
						} else {
							if (curSelectNum == 0) {
								result = FootballLotteryConstant.footBall[index1][index1_1];
								doCalFootballCode(index1, index1_1, index2, index2_2, result, selectedNum, curSelectNum);
							}

						}
					}
					// 循环4 end；
					if (selectedNum == curSelectNum) {
						System.out.println(result);
						break;
					}
				}
				// 循环3 end
			}
			if (selectedNum == curSelectNum) {
				System.out.println(result);
				break;
			}
			// 循环2 end;
		}
	}
}
