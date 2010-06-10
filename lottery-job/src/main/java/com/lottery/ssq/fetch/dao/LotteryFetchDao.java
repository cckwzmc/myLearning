package com.lottery.ssq.fetch.dao;



import java.util.List;

import com.lottery.dao.JdbcBaseDao;

public class LotteryFetchDao extends JdbcBaseDao {
	public void saveSsqLotteryWebFetchList(String title){
		
	}
	@SuppressWarnings("unchecked")
	public List getSsqLotteryWebFetchList(int flag){
		String sql="select * from ssq_lottery_web_fetch_list t where web_flag=?";
		return this.getJdbcTemplate().queryForList(sql,new Object[]{flag});
	}
}
