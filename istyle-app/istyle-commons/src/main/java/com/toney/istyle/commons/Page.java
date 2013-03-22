package com.toney.istyle.commons;

import java.util.Collections;
import java.util.List;

/**
 * 
 * 分页使用.
 * 
 */
public class Page<T> {

	// 默认分页条数.
	public static final int DEFAULT_PAGESIZE = 25;

	// 分页参数
	protected Integer pageNo = 1;

	protected int pageSize = DEFAULT_PAGESIZE;
	private int totalPages = 10;
	// 返回结果
	protected List<T> result = Collections.emptyList();

	private int firstRecord;
	private int endRecord;

	public Page() {
		super();
	}

	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	/**
	 * 获得当前页的页号,序号从1开始,默认为1.
	 */
	public Integer getPageNo() {
		return pageNo;
	}

	/**
	 * 设置当前页的页号,序号从1开始,低于1时自动调整为1.
	 */
	public void setPageNo(final Integer pageNo) {
		Integer pageNoTmp=pageNo;
		this.pageNo = pageNo;
		if(pageNoTmp==null||pageNo < 1) {
			this.pageNo=1;
		} else if (this.pageNo >= this.totalPages) {
			this.pageNo = pageNo;
		}
	}

	/**
	 * 获得每页的记录数量,默认为25.
	 */
	public int getPageSize() {
		return pageSize;
	}

	/**
	 * 根据pageNo和pageSize计算当前页第一条记录在总结果集中的位置,Mysql 序号从0开始.
	 */
	public int getFirstRecord() {
		return ((getPageNo() - 1) * getPageSize());
	}

	/**
	 * 根据pageNo和PageSize计算当前最后一条记录在结果集中的位置，序号从0开始.
	 * 
	 * @return
	 */
	public int getEndRecord() {
		int end = getFirstRecord() + pageSize;
		return end;
	}

	/**
	 * 取得页内的记录列表.
	 */
	public List<T> getResult() {
		return result;
	}

	public void setResult(List<T> result) {
		this.result = result;
	}

	public int getTotalPages() {
		return totalPages;
	}

	@Override
	public String toString() {
		return "Page [pageNo=" + pageNo + ", pageSize=" + pageSize + ", totalPages=" + totalPages + ", result=" + result + ", firstRecord=" + firstRecord + ", endRecord="
				+ endRecord + "]";
	}

}
