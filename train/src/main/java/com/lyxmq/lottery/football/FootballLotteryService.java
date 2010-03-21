package com.lyxmq.lottery.football;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.LoggerFactory;

public class FootballLotteryService {
	private static org.slf4j.Logger logger = LoggerFactory.getLogger(FootballLotteryService.class);

	private FootballLotteryDao footballLotteryDao = null;
	private static int count = 0;
	private StringBuffer out = new StringBuffer();
	public void setFootballLotteryDao(FootballLotteryDao footballLotteryDao) {
		this.footballLotteryDao = footballLotteryDao;
	}

	private static final int FOOTBALLMATCH = 14;

	/**
	 * 生成所有可能性的号码
	 */
	public void genAllCode() {
		logger.info("开始生成-所有可能性的结果..........................");
		this.doCallAllCode();

	}
	public void filterFootballCode(){
		this.deleteFileFootballCode();
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
	private void doCallAllCode() {
		for (int i = 0; i < FootballLotteryConstant.footBall[0].length; i++) {
			String tempI = FootballLotteryConstant.footBall[0][i];
			for (int j = 0; j < FootballLotteryConstant.footBall[1].length; j++) {
				String tempJ = FootballLotteryConstant.footBall[1][j];
				for (int j2 = 0; j2 < FootballLotteryConstant.footBall[2].length; j2++) {
					String tempJ2 = FootballLotteryConstant.footBall[2][j2];
					for (int k = 0; k < FootballLotteryConstant.footBall[3].length; k++) {
						String tempK = FootballLotteryConstant.footBall[3][k];
						for (int k2 = 0; k2 < FootballLotteryConstant.footBall[4].length; k2++) {
							String tempK2 = FootballLotteryConstant.footBall[4][k2];
							for (int l = 0; l < FootballLotteryConstant.footBall[5].length; l++) {
								String tempL = FootballLotteryConstant.footBall[5][l];
								for (int l2 = 0; l2 < FootballLotteryConstant.footBall[6].length; l2++) {
									String tempL2 = FootballLotteryConstant.footBall[6][l2];
									for (int m = 0; m < FootballLotteryConstant.footBall[7].length; m++) {
										String tempM = FootballLotteryConstant.footBall[7][m];
										for (int m2 = 0; m2 < FootballLotteryConstant.footBall[8].length; m2++) {
											String tempM2 = FootballLotteryConstant.footBall[8][m2];
											for (int n = 0; n < FootballLotteryConstant.footBall[9].length; n++) {
												String tempN = FootballLotteryConstant.footBall[9][n];
												for (int n2 = 0; n2 < FootballLotteryConstant.footBall[10].length; n2++) {
													String tempN2 = FootballLotteryConstant.footBall[10][n2];
													for (int o = 0; o < FootballLotteryConstant.footBall[11].length; o++) {
														String tempO = FootballLotteryConstant.footBall[11][o];
														for (int o2 = 0; o2 < FootballLotteryConstant.footBall[12].length; o2++) {
															String tempO2 = FootballLotteryConstant.footBall[12][o2];
															for (int p = 0; p < FootballLotteryConstant.footBall[13].length; p++) {
																String tempP = FootballLotteryConstant.footBall[13][p];
																String result = tempI + "," + tempJ + "," + tempJ2 + "," + tempK + "," + tempK2 + "," + tempL
																		+ "," + tempL2 + "," + tempM + "," + tempM2 + "," + tempN + "," + tempN2 + "," + tempO
																		+ "," + tempO2 + "," + tempP;
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
		File file = new File("d:/ft.sql");
		FileWriter fw;
		try {
			fw = new FileWriter(file);
			fw.write(out.toString());
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void doCallAllCode(String[][] ftCodes) {
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
																String result = tempI + "," + tempJ + "," + tempJ2 + "," + tempK + "," + tempK2 + "," + tempL
																		+ "," + tempL2 + "," + tempM + "," + tempM2 + "," + tempN + "," + tempN2 + "," + tempO
																		+ "," + tempO2 + "," + tempP;
																this.footballLotteryDao.deleteFootballFilterCode(result);
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

	private void deleteFileFootballCode() {
		List<String> fileList = new ArrayList<String>();
		File file = new File("D:/2009055.txt");
		try {
			FileReader fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);
			String line = br.readLine();
			while (line != null) {
				fileList.add(line);
				line = br.readLine();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (CollectionUtils.isNotEmpty(fileList)) {
			for (Iterator<String> iterator = fileList.iterator(); iterator.hasNext();) {
				String value = (String) iterator.next();
				value=value.replace(" ", "");
				if (StringUtils.indexOf(value, "|") != -1) {
					String[] tmp = StringUtils.split(value, "|");
					this.convertArray(tmp[1], "(");
				} else if (value.indexOf("-") != -1) {
					this.convertArray(value, "-");
				}else if(value.length()==14){
					convertArray(value, "14");
				}
			}
		}
	}

	private void convertArray(String value, String split) {
		String[] tmp = new String[14];
		if ("(".equals(split)) {
			List<String> code = new ArrayList<String>();
			while (StringUtils.isNotBlank(value)) {
				int leftIndex = value.indexOf("(");
				String subTmp = "";
				if (leftIndex != -1) {
					subTmp = value.substring(0, leftIndex);
					value = value.substring(leftIndex + 1);
					if (subTmp.length() > 0) {
						for (int i = 0; i < subTmp.length(); i++) {
							code.add(subTmp.substring(i, i + 1));
						}
					}
				}
				int rightIndex = value.indexOf(")");
				try {
					if (leftIndex != -1) {
						code.add(value.substring(0, rightIndex));
						value = value.substring(rightIndex + 1);
					}
				} catch (StringIndexOutOfBoundsException e) {
					value = "";
				}
				if (value.indexOf("(") == -1 && value.indexOf(")") == -1) {
					for (int i = 0; i < value.length(); i++) {
						code.add(value.substring(i, i + 1));
					}
					value = "";
				}
			}
			for (int i = 0; i < code.size(); i++) {
				String ft = code.get(i);
				tmp[i] = ft;
			}
		}
		if (value.indexOf("-") != -1) {
			tmp = value.split("-");
		}
		if (value.indexOf(",") != -1) {
			tmp = value.split(",");
		}
		if("14".equals(split)){
			List<String> code = new ArrayList<String>();
			for (int i = 0; i < value.length(); i++) {
				code.add(value.substring(i, i + 1));
			}
			for (int i = 0; i < code.size(); i++) {
				String ft = code.get(i);
				tmp[i] = ft;
			}
		}
		String[][] ftCode = new String[14][];
		for (int i = 0; i < tmp.length; i++) {
			String tt = "";
			for (int j = 0; j < tmp[i].length(); j++) {
				if ("".equals(tt)) {
					tt = tmp[i].substring(j, j + 1);
				} else {
					tt += "," + tmp[i].substring(j, j + 1);
				}
			}
			ftCode[i] = tt.split(",");
		}
		this.doCallAllCode(ftCode);
	}

	/**
	 * + // 排除>=8个相同号码的组合 // 排除连续>=5个相同号码的组合
	 * 
	 * @param result
	 */
	private void disposeResultData(String result) {
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
		if (sameOne > 0 && sameThree > 0 && sameTwo > 0 && sameOne < 8 && sameThree < 8 && sameTwo < 8 && cs < 4) {
			count++;
			this.footballLotteryDao.saveFootballLottoryResult(result);
		}
	}

}
