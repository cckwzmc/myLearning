package com.lyxmq.lottery.football.util;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

public class FootballLotteryUtils {

	public static List<String> doCallAllCode(String[][] ftCodes) {
		List<String> list = new ArrayList<String>();
		for (int i = 0; i < ftCodes[0].length; i++) {
			String tempI = ftCodes[0][i];
			for (int j = 0; j < ftCodes[1].length; j++) {
				String tempJ = ftCodes[1][j];
				for (int j2 = 0; j2 < ftCodes[2].length; j2++) {
					String tempJ2 = ftCodes[2][j2];
					for (int k = 0; k < ftCodes[3].length; k++) {
						String tempK = ftCodes[3][k];
						for (int k2 = 0; k2 < ftCodes[4].length; k2++) {
							String tempK2 = ftCodes[4][k2];
							for (int l = 0; l < ftCodes[5].length; l++) {
								String tempL = ftCodes[5][l];
								for (int l2 = 0; l2 < ftCodes[6].length; l2++) {
									String tempL2 = ftCodes[6][l2];
									for (int m = 0; m < ftCodes[7].length; m++) {
										String tempM = ftCodes[7][m];
										for (int m2 = 0; m2 < ftCodes[8].length; m2++) {
											String tempM2 = ftCodes[8][m2];
											for (int n = 0; n < ftCodes[9].length; n++) {
												String tempN = ftCodes[9][n];
												for (int n2 = 0; n2 < ftCodes[10].length; n2++) {
													String tempN2 = ftCodes[10][n2];
													for (int o = 0; o < ftCodes[11].length; o++) {
														String tempO = ftCodes[11][o];
														for (int o2 = 0; o2 < ftCodes[12].length; o2++) {
															String tempO2 = ftCodes[12][o2];
															for (int p = 0; p < ftCodes[13].length; p++) {
																String tempP = ftCodes[13][p];
																String result = tempI + "," + tempJ + "," + tempJ2 + "," + tempK + "," + tempK2 + "," + tempL + "," + tempL2 + "," + tempM + "," + tempM2 + "," + tempN + "," + tempN2 + "," + tempO + "," + tempO2 + "," + tempP;
																list.add(result);
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
		return list;
	}
	/**
	 * 是否符合最基本的条件
	 * 必须包含一个3/1/0
	 * 3/1/0个数必须小于8个
	 * 不能连续出现超过4个以上的相同号码
	 * @param result
	 * @return
	 */
	public static boolean isAccordBasicRequire(String result){
		String[] rs = result.split(",");
		int sameOne = 0;
		int sameThree = 0;
		int sameTwo = 0;
		int cs = 0;
		for (int i = 0; i < rs.length; i++) {
			if ("0".equals(rs[i])) {
				sameOne++;
			}
			if ("1".equals(rs[i])) {
				sameTwo++;
			}
			if ("3".equals(rs[i])) {
				sameThree++;
			}
			if (i > 0) {
				if (StringUtils.equals(rs[i - 1], rs[i])) {
					cs++;
				} else if (cs < 4) {
					cs = 0;
				}
			}
		}
		if (sameOne > 0 && sameThree > 0 && sameTwo > 0 && sameOne < 8 && sameThree < 8 && sameTwo < 8 && cs <5) {
			return true;
		}
		return false;
	}
	/**
	 * ","分隔的
	 * @param code
	 * @return
	 */
	public static String[][] splitFootballCode(String code){
		String[] codes=code.split(",");
		String[][] ftCode = new String[14][];
		for (int i = 0; i < codes.length; i++) {
			String tt = "";
			for (int j = 0; j < codes[i].length(); j++) {
				if ("".equals(tt)) {
					tt = codes[i].substring(j, j + 1);
				} else {
					tt += "," + codes[i].substring(j, j + 1);
				}
			}
			ftCode[i] = tt.split(",");
		}
		return ftCode;
	}
}
