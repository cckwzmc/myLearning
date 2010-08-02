package com.lottery.ssq.config;

public class LotterySsqFilterConfig extends LotterySsqConfig {
	private int is_reFilter = 0;
	private int quOne = -1;
	private int quTwo = -1;
	private int quThree = -1;
	private int quOneNum = 11;
	private int quTwoNum = 22;
	// 不包含
	private String[] excludeRed = new String[] {};
	// 必须有的
	private String[] musthavered = new String[] {};
	// 是否从 文件中读取排除号码
	private int ishaveexclude = 0;
	// 第一个数字要求大于等于该数字
	private int firstMinCode = 1;
	// 第一个数字要求小于等于该数字
	private int firstMaxCode = 33;
	// 最大数字要求大于等于该数字
	private int lastMinCode = 29;
	// 最大数字要求小于等于该数字
	private int lastMaxCode = 34;
	// 有几个两连号
	private int haveTwoSeries = -1;
	// 有几个三连号
	private int haveThreeSeries = -1;
	// 有几个差值为1的，，如1，3，5 算两个
	private int haveOnediffer = -1;
	// 在这些号码中选择6个
	private String[] selectCode = new String[] {};
	// 不能同时出现的号码
	private String[] cannotSelectedTogethor = new String[] {};
	// 是否有边号
	private int haveSideCode = -1;
	// 包含includeRed中的几个数字
	private int includePreRedNum = 1;
	private int secondMinCode = 2;
	private int secondMaxCode = 20;
	private int thirdMinCode = 3;
	private int thirdMaxCode = 29;
	private int fourthMinCode = 4;
	private int fourthMaxCode = 33;
	// 最多选择其中的一个
	private String[] zuiduoSelectedOneCode = new String[] {};
	// 至少选其中的一个
	private String[] leastSelectedOneCode = new String[] {};
	// 必须选择其中的一个
	private String[] mustSelectedOneCode = new String[] {};
	// 必须选其中的两个
	private String[] mustSelectedTwoCode = new String[] {};
	// 至少选其中的两个
	private String[] leastSelectedTwoCode = new String[] {};
	// 至少选中其中的三个
	private String[] leastSelectedThreeCode = new String[] {};
	// 三个尾数相同的号码
	private int mantissaThreeSame;
	// 三个两倍数的号码
	private int haveThree2Multiple;
	private int haveThree3Multiple;
	// 连续4个奇数或偶数
	private int continueFourOddOreven;
	// 5个以上的奇数或偶数
	private int geFiveOddOrEven;
	// 只有一个用户投注的号码会中5个号码，并且6个号码在其中.
	private int customerLeCount3RedList;
	// 用户有5人以上投注的号码，都不可能中四个
	private int customerGtCount5RedList;
	private int genFilterRedCodeFromCollectResult;
	
//	isSinaRedCodeNodeSelected 新浪媒体擂台，新浪媒体推荐中至少有num注会一个号码也不中
//	selectedPreCodeOrDiffCode 基本过滤方法，必须中上一期一个号码或有隔号
//	selectedSeriCodeOrDiffCode 基本过滤方法，必须中隔号或连号
//	selectedSeriOrDiffOrPreCode 基本过滤方法，必须中上一期一个号码或连号或隔号
	
	private int selectedSeriOrDiffOrPreCode;
	private int selectedSeriCodeOrDiffCode;
	private int selectedPreCodeOrDiffCode;
	private int isSinaRedCodeNodeSelected;
	public int isUseLastFilter;
	private int threeQuNum;
	private int twoQuNum;
	private int oneQuNum;
	private Integer isFilterWebFourCode;
	private Integer isFilterHistoryFourCode;
	private int isFilterHistoryFiveSelecte;
	private int isFilterHistoryOneSelecte;
	private int isFilterHistoryThreeSelecte;
	private int selectedPreCodeOrSeriCode;

	public int getIsUseLastFilter() {
		return isUseLastFilter;
	}

	public void setIsUseLastFilter(int isUseLastFilter) {
		this.isUseLastFilter = isUseLastFilter;
	}

	public int getIsSinaRedCodeNodeSelected() {
		return isSinaRedCodeNodeSelected;
	}

	public void setIsSinaRedCodeNodeSelected(int isSinaRedCodeNodeSelected) {
		this.isSinaRedCodeNodeSelected = isSinaRedCodeNodeSelected;
	}

	public void setSelectedSeriOrDiffOrPreCode(int selectedSeriOrDiffOrPreCode) {
		this.selectedSeriOrDiffOrPreCode = selectedSeriOrDiffOrPreCode;
	}

	public void setSelectedSeriCodeOrDiffCode(int selectedSeriCodeOrDiffCode) {
		this.selectedSeriCodeOrDiffCode = selectedSeriCodeOrDiffCode;
	}

	public void setSelectedPreCodeOrDiffCode(int selectedPreCodeOrDiffCode) {
		this.selectedPreCodeOrDiffCode = selectedPreCodeOrDiffCode;
	}

	public int getIs_reFilter() {
		return is_reFilter;
	}

	public void setIs_reFilter(int isReFilter) {
		is_reFilter = isReFilter;
	}

	public int getQuOne() {
		return quOne;
	}

	public int getQuTwo() {
		return quTwo;
	}

	public void setQuTwo(int quTwo) {
		this.quTwo = quTwo;
	}

	public int getQuThree() {
		return quThree;
	}

	public void setQuThree(int quThree) {
		this.quThree = quThree;
	}

	public int getQuOneNum() {
		return quOneNum;
	}

	public void setQuOneNum(int quOneNum) {
		this.quOneNum = quOneNum;
	}

	public int getQuTwoNum() {
		return quTwoNum;
	}

	public void setQuTwoNum(int quTwoNum) {
		this.quTwoNum = quTwoNum;
	}

	public String[] getExcludeRed() {
		return excludeRed;
	}

	public void setExcludeRed(String[] excludeRed) {
		this.excludeRed = excludeRed;
	}

	public String[] getMusthavered() {
		return musthavered;
	}

	public void setMusthavered(String[] musthavered) {
		this.musthavered = musthavered;
	}

	public int getIshaveexclude() {
		return ishaveexclude;
	}

	public void setIshaveexclude(int ishaveexclude) {
		this.ishaveexclude = ishaveexclude;
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

	public String[] getSelectCode() {
		return selectCode;
	}

	public void setSelectCode(String[] selectCode) {
		this.selectCode = selectCode;
	}

	public String[] getCannotSelectedTogethor() {
		return cannotSelectedTogethor;
	}

	public void setCannotSelectedTogethor(String[] cannotSelectedTogethor) {
		this.cannotSelectedTogethor = cannotSelectedTogethor;
	}

	public int getHaveSideCode() {
		return haveSideCode;
	}

	public void setHaveSideCode(int haveSideCode) {
		this.haveSideCode = haveSideCode;
	}

	public int getIncludePreRedNum() {
		return includePreRedNum;
	}

	public void setIncludePreRedNum(int includePreRedNum) {
		this.includePreRedNum = includePreRedNum;
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

	public String[] getZuiduoSelectedOneCode() {
		return zuiduoSelectedOneCode;
	}

	public void setZuiduoSelectedOneCode(String[] zuiduoSelectedOneCode) {
		this.zuiduoSelectedOneCode = zuiduoSelectedOneCode;
	}

	public String[] getLeastSelectedOneCode() {
		return leastSelectedOneCode;
	}

	public void setLeastSelectedOneCode(String[] leastSelectedOneCode) {
		this.leastSelectedOneCode = leastSelectedOneCode;
	}

	public String[] getMustSelectedOneCode() {
		return mustSelectedOneCode;
	}

	public void setMustSelectedOneCode(String[] mustSelectedOneCode) {
		this.mustSelectedOneCode = mustSelectedOneCode;
	}

	public String[] getMustSelectedTwoCode() {
		return mustSelectedTwoCode;
	}

	public void setMustSelectedTwoCode(String[] mustSelectedTwoCode) {
		this.mustSelectedTwoCode = mustSelectedTwoCode;
	}

	public String[] getLeastSelectedTwoCode() {
		return leastSelectedTwoCode;
	}

	public void setLeastSelectedTwoCode(String[] leastSelectedTwoCode) {
		this.leastSelectedTwoCode = leastSelectedTwoCode;
	}

	public String[] getLeastSelectedThreeCode() {
		return leastSelectedThreeCode;
	}

	public void setLeastSelectedThreeCode(String[] leastSelectedThreeCode) {
		this.leastSelectedThreeCode = leastSelectedThreeCode;
	}

	public int getMantissaThreeSame() {
		return mantissaThreeSame;
	}

	public void setMantissaThreeSame(int mantissaThreeSame) {
		this.mantissaThreeSame = mantissaThreeSame;
	}

	public int getHaveThree2Multiple() {
		return haveThree2Multiple;
	}

	public void setHaveThree2Multiple(int haveThree2Multiple) {
		this.haveThree2Multiple = haveThree2Multiple;
	}

	public int getHaveThree3Multiple() {
		return haveThree3Multiple;
	}

	public void setHaveThree3Multiple(int haveThree3Multiple) {
		this.haveThree3Multiple = haveThree3Multiple;
	}

	public int getContinueFourOddOreven() {
		return continueFourOddOreven;
	}

	public void setContinueFourOddOreven(int continueFourOddOreven) {
		this.continueFourOddOreven = continueFourOddOreven;
	}

	public int getGeFiveOddOrEven() {
		return geFiveOddOrEven;
	}

	public void setGeFiveOddOrEven(int geFiveOddOrEven) {
		this.geFiveOddOrEven = geFiveOddOrEven;
	}

	public int getCustomerLeCount3RedList() {
		return customerLeCount3RedList;
	}

	public void setCustomerLeCount3RedList(int customerLeCount3RedList) {
		this.customerLeCount3RedList = customerLeCount3RedList;
	}

	public int getCustomerGtCount5RedList() {
		return customerGtCount5RedList;
	}

	public void setCustomerGtCount5RedList(int customerGtCount5RedList) {
		this.customerGtCount5RedList = customerGtCount5RedList;
	}

	public int getGenFilterRedCodeFromCollectResult() {
		return genFilterRedCodeFromCollectResult;
	}

	public void setGenFilterRedCodeFromCollectResult(int genFilterRedCodeFromCollectResult) {
		this.genFilterRedCodeFromCollectResult = genFilterRedCodeFromCollectResult;
	}

	public void setQuOne(int quOne) {
		this.quOne = quOne;
	}

	public int getSelectedSeriOrDiffOrPreCode() {
		return this.selectedSeriOrDiffOrPreCode;
	}

	public int getSelectedSeriCodeOrDiffCode() {
		return this.selectedSeriCodeOrDiffCode;
	}

	public int getSelectedPreCodeOrDiffCode() {
		return this.selectedPreCodeOrDiffCode;
	}

	public int getThreeQuNum() {
		return threeQuNum;
	}

	public void setThreeQuNum(int threeQuNum) {
		this.threeQuNum = threeQuNum;
	}

	public int getTwoQuNum() {
		return twoQuNum;
	}

	public void setTwoQuNum(int twoQuNum) {
		this.twoQuNum = twoQuNum;
	}

	public int getOneQuNum() {
		return oneQuNum;
	}

	public void setOneQuNum(int oneQuNum) {
		this.oneQuNum = oneQuNum;
	}

	public void setIsFilterWebFourCode(Integer isFilterWebFourCode) {
		this.isFilterWebFourCode = isFilterWebFourCode;
	}

	public Integer getIsFilterWebFourCode() {
		return this.isFilterWebFourCode;
	}

	public void setIsFilterHistoryFourCode(Integer isFilterHistoryFourCode) {
		this.isFilterHistoryFourCode = isFilterHistoryFourCode;
	}

	public Integer getIsFilterHistoryFourCode() {
		return isFilterHistoryFourCode;
	}

	public void setIsFilterHistoryFiveSelecte(int isFilterHistoryFiveSelecte) {
		this.isFilterHistoryFiveSelecte = isFilterHistoryFiveSelecte;
	}

	public int getIsFilterHistoryFiveSelecte() {
		return this.isFilterHistoryFiveSelecte;
	}

	public void setIsFilterHistoryOneSelecte(int isFilterHistoryOneSelecte) {
		this.isFilterHistoryOneSelecte = isFilterHistoryOneSelecte;
	}

	public int getIsFilterHistoryOneSelecte() {
		return this.isFilterHistoryOneSelecte;
	}

	public void setIsFilterHistoryThreeSelecte(int isFilterHistoryThreeSelecte) {
		this.isFilterHistoryThreeSelecte = isFilterHistoryThreeSelecte;
	}

	public int getIsFilterHistoryThreeSelecte() {
		return this.isFilterHistoryThreeSelecte;
	}

	public void setSelectedPreCodeOrSeriCode(int selectedPreCodeOrSeriCode) {
		this.selectedPreCodeOrSeriCode = selectedPreCodeOrSeriCode;
	}

	public int getSelectedPreCodeOrSeriCode() {
		return this.selectedPreCodeOrSeriCode;
	}

}
