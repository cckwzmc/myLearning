package com.lotteryCommend.ssq.model.commend;

import java.io.Serializable;

public class SsqCalculateModel implements Serializable {

	private final long serialVersionUID = -9022764301392106505L;
	// 设置为几个区,暂时只支持 3区
	private int setZone = 3;
	private int firstZone;
	private int secondZone;
	private int thirdZone;
	private int fourthZone;
	private int fifthZone;

	// 包含其中一个数字
	private String includeRed = "";
	// 不包含
	private String excludeRed = "";
	// 必须有的
	private String musthavered = "";
	// 是否从 文件中读取排除号码
	private int ishaveexclude = 0;
	// 有几个两连号
	private int haveTwoSeries = 1;
	// 有几个三连号
	private int haveThreeSeries = 0;
	// 有几个差值为1的，，如1，3，5 算两个
	private int haveOnediffer = 1;
	// 在这些号码中选择6个
	private String selectCode = "";
	// 不能同时出现的号码
	private String cannotSelectedTogethor = "";
	// 是否有边号
	private int haveSideCode = 1;
	// 包含includeRed中的几个数字
	private int includeRedNum = 1;
	// 计算出上一期的所有的边号
	private String preSideCode = "";

	/*
	 * ~~~~~~~~~~~ 数字的取值范围~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	 */
	// 第一个数字要求大于等于该数字
	private int firstMinCode = 0;
	// 第一个数字要求小于等于该数字
	private int firstMaxCode = 0;
	private int secondMinCode = 0;
	private int secondMaxCode = 0;
	private int thirdMinCode = 0;
	private int thirdMaxCode = 0;
	private int fourthMinCode = 0;
	private int fourthMaxCode = 0;
	private int fifthMinCode = 0;
	private int fithMaxCode = 0;
	// 最大数字要求大于等于该数字
	private int lastMinCode = 0;
	// 最大数字要求小于等于该数字
	private int lastMaxCode = 0;
	// 从中取号码
	private String selectFirstZone = "";
	private String selectSecondZone = "";
	private String selectThirdZone = "";

	public int getFifthMinCode() {
		return fifthMinCode;
	}

	public void setFifthMinCode(int fifthMinCode) {
		this.fifthMinCode = fifthMinCode;
	}

	public int getFithMaxCode() {
		return fithMaxCode;
	}

	public void setFithMaxCode(int fithMaxCode) {
		this.fithMaxCode = fithMaxCode;
	}

	public int getSetZone() {
		return setZone;
	}

	public void setSetZone(int setZone) {
		this.setZone = setZone;
	}

	public int getFirstZone() {
		return firstZone;
	}

	public void setFirstZone(int firstZone) {
		this.firstZone = firstZone;
	}

	public int getSecondZone() {
		return secondZone;
	}

	public void setSecondZone(int secondZone) {
		this.secondZone = secondZone;
	}

	public int getThirdZone() {
		return thirdZone;
	}

	public void setThirdZone(int thirdZone) {
		this.thirdZone = thirdZone;
	}

	public int getFourthZone() {
		return fourthZone;
	}

	public void setFourthZone(int fourthZone) {
		this.fourthZone = fourthZone;
	}

	public int getFifthZone() {
		return fifthZone;
	}

	public void setFifthZone(int fifthZone) {
		this.fifthZone = fifthZone;
	}

	public String getIncludeRed() {
		return includeRed;
	}

	public void setIncludeRed(String includeRed) {
		this.includeRed = includeRed;
	}

	public String getExcludeRed() {
		return excludeRed;
	}

	public void setExcludeRed(String excludeRed) {
		this.excludeRed = excludeRed;
	}

	public String getMusthavered() {
		return musthavered;
	}

	public void setMusthavered(String musthavered) {
		this.musthavered = musthavered;
	}

	public int getIshaveexclude() {
		return ishaveexclude;
	}

	public void setIshaveexclude(int ishaveexclude) {
		this.ishaveexclude = ishaveexclude;
	}

	public int getHaveTwoSeries() {
		return haveTwoSeries;
	}

	public void setHaveTwoSeries(int haveTwoSeries) {
		this.haveTwoSeries = haveTwoSeries;
	}

	public int getHaveThreeSeries() {
		return haveThreeSeries;
	}

	public void setHaveThreeSeries(int haveThreeSeries) {
		this.haveThreeSeries = haveThreeSeries;
	}

	public int getHaveOnediffer() {
		return haveOnediffer;
	}

	public void setHaveOnediffer(int haveOnediffer) {
		this.haveOnediffer = haveOnediffer;
	}

	public String getSelectCode() {
		return selectCode;
	}

	public void setSelectCode(String selectCode) {
		this.selectCode = selectCode;
	}

	public String getCannotSelectedTogethor() {
		return cannotSelectedTogethor;
	}

	public void setCannotSelectedTogethor(String cannotSelectedTogethor) {
		this.cannotSelectedTogethor = cannotSelectedTogethor;
	}

	public int getHaveSideCode() {
		return haveSideCode;
	}

	public void setHaveSideCode(int haveSideCode) {
		this.haveSideCode = haveSideCode;
	}

	public int getIncludeRedNum() {
		return includeRedNum;
	}

	public void setIncludeRedNum(int includeRedNum) {
		this.includeRedNum = includeRedNum;
	}

	public String getPreSideCode() {
		return preSideCode;
	}

	public void setPreSideCode(String preSideCode) {
		this.preSideCode = preSideCode;
	}

	public int getFirstMinCode() {
		return firstMinCode;
	}

	public void setFirstMinCode(int firstMinCode) {
		this.firstMinCode = firstMinCode;
	}

	public int getFirstMaxCode() {
		return firstMaxCode;
	}

	public void setFirstMaxCode(int firstMaxCode) {
		this.firstMaxCode = firstMaxCode;
	}

	public int getLastMinCode() {
		return lastMinCode;
	}

	public void setLastMinCode(int lastMinCode) {
		this.lastMinCode = lastMinCode;
	}

	public int getLastMaxCode() {
		return lastMaxCode;
	}

	public void setLastMaxCode(int lastMaxCode) {
		this.lastMaxCode = lastMaxCode;
	}

	public int getSecondMinCode() {
		return secondMinCode;
	}

	public void setSecondMinCode(int secondMinCode) {
		this.secondMinCode = secondMinCode;
	}

	public int getSecondMaxCode() {
		return secondMaxCode;
	}

	public void setSecondMaxCode(int secondMaxCode) {
		this.secondMaxCode = secondMaxCode;
	}

	public int getThirdMinCode() {
		return thirdMinCode;
	}

	public void setThirdMinCode(int thirdMinCode) {
		this.thirdMinCode = thirdMinCode;
	}

	public int getThirdMaxCode() {
		return thirdMaxCode;
	}

	public void setThirdMaxCode(int thirdMaxCode) {
		this.thirdMaxCode = thirdMaxCode;
	}

	public int getFourthMinCode() {
		return fourthMinCode;
	}

	public void setFourthMinCode(int fourthMinCode) {
		this.fourthMinCode = fourthMinCode;
	}

	public int getFourthMaxCode() {
		return fourthMaxCode;
	}

	public void setFourthMaxCode(int fourthMaxCode) {
		this.fourthMaxCode = fourthMaxCode;
	}

	public String getSelectFirstZone() {
		return selectFirstZone;
	}

	public void setSelectFirstZone(String selectFirstZone) {
		this.selectFirstZone = selectFirstZone;
	}

	public String getSelectSecondZone() {
		return selectSecondZone;
	}

	public void setSelectSecondZone(String selectSecondZone) {
		this.selectSecondZone = selectSecondZone;
	}

	public String getSelectThirdZone() {
		return selectThirdZone;
	}

	public void setSelectThirdZone(String selectThirdZone) {
		this.selectThirdZone = selectThirdZone;
	}

	public long getSerialVersionUID() {
		return serialVersionUID;
	}

}
