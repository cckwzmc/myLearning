package com.lyxmq.lottery.football;

import java.util.List;

import com.myfetch.myfetch.dao.JdbcBaseDao;

public class FootballLotteryDao extends JdbcBaseDao {

	public void saveFootballLottoryResult(String redResult) {
		String sql = "insert into footballallresult(value) values(?)";
		this.getJdbcTemplate().update(sql, new Object[] { redResult });
	}

	@SuppressWarnings("unchecked")
	public List getFootballLottoryResultLimit(int first, int page) {
		String sql = "select value from footballallresult t order by id limit " + first + "," + page;
		return this.getJdbcTemplate().queryForList(sql);
	}

	@SuppressWarnings("unchecked")
	public List getFootballLottoryResultAll() {
		String sql = "select value from footballallresult t order by id ";
		return this.getJdbcTemplate().queryForList(sql);
	}

	public int getTotalFootballLottoryResult() {
		String sql = "select count(*) from footballallresult";
		return this.getJdbcTemplate().queryForInt(sql);
	}
}
