package com.lyxmq.lottery.test;

import java.util.List;

import com.myfetch.myfetch.dao.JdbcBaseDao;

public class LottoryDao  extends JdbcBaseDao {

	public void saveLottoryResult(String redResult) {
		String sql="insert into ssq_lottery_all_result(value) values(?)";
		this.getJdbcTemplate().update(sql, new Object[]{redResult});
	}
	
	public void saveTempLottoryResult(String redResult) {
		String sql="insert into ssq_lottery_filter_result(value) values(?)";
		this.getJdbcTemplate().update(sql, new Object[]{redResult});
	}
	
	@SuppressWarnings("unchecked")
	public List getLottoryResultLimit(int first,int page){
		String sql="select value from ssq_lottery_all_result t order by id limit "+first+","+page;
		return this.getJdbcTemplate().queryForList(sql);
	}
	@SuppressWarnings("unchecked")
	public List getLottoryResultAll(){
		String sql="select value from ssq_lottery_all_result t order by id ";
		return this.getJdbcTemplate().queryForList(sql);
	}

	public int getTotalLotteryResult() {
		String sql="select count(*) from ssq_lottery_all_result";
		return this.getJdbcTemplate().queryForInt(sql);
	}
	//~~~~~~~~过滤后的查询
	public List getLotteryFilterResultAll(){
		String sql="select value from ssq_lottery_filter_result t order by id ";
		return this.getJdbcTemplate().queryForList(sql);
	}
	@SuppressWarnings("unchecked")
	public List getLottoryFilterResultLimit(int first,int page){
		String sql="select value from ssq_lottery_filter_result t order by id limit "+first+","+page;
		return this.getJdbcTemplate().queryForList(sql);
	}
}
