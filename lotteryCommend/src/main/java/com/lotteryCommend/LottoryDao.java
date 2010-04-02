package com.lotteryCommend;

import java.util.List;

import com.myfetch.myfetch.dao.JdbcBaseDao;

public class LottoryDao  extends JdbcBaseDao {

	public void saveLottoryResult(String redResult) {
		String sql="insert into lottoryresult(value) values(?)";
		this.getJdbcTemplate().update(sql, new Object[]{redResult});
	}
	
	public void saveTempLottoryResult(String redResult) {
		String sql="insert into temp_lottoryresult(value) values(?)";
		this.getJdbcTemplate().update(sql, new Object[]{redResult});
	}
	
	@SuppressWarnings("unchecked")
	public List getLottoryResultLimit(int first,int page){
		String sql="select value from lottoryresult t order by id limit "+first+","+page;
		return this.getJdbcTemplate().queryForList(sql);
	}
	@SuppressWarnings("unchecked")
	public List getLottoryResultAll(){
		String sql="select value from lottoryresult t order by id ";
		return this.getJdbcTemplate().queryForList(sql);
	}

	public int getTotalLottoryResult() {
		String sql="select count(*) from lottoryresult";
		return this.getJdbcTemplate().queryForInt(sql);
	}
}
