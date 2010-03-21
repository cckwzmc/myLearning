package com.lyxmq.lottery.football;

import java.util.List;

import com.myfetch.myfetch.dao.JdbcBaseDao;

public class FootballLotteryDao extends JdbcBaseDao {

	public void saveFootballLottoryResult(String redResult) {
		String sql = "insert into ft_lottery_all_result(value) values(?)";
		this.getJdbcTemplate().update(sql, new Object[] { redResult });
	}

	@SuppressWarnings("unchecked")
	public List getFootballLottoryResultLimit(int first, int page) {
		String sql = "select value from ft_lottery_all_result t order by id limit " + first + "," + page;
		return this.getJdbcTemplate().queryForList(sql);
	}

	@SuppressWarnings("unchecked")
	public List getFootballLottoryResultAll() {
		String sql = "select value from ft_lottery_all_result t order by id ";
		return this.getJdbcTemplate().queryForList(sql);
	}

	public int getTotalFootballLottoryResult() {
		String sql = "select count(*) from ft_lottery_all_result";
		return this.getJdbcTemplate().queryForInt(sql);
	}

	public void saveFootballLottoryFilterResult(String redResult) {
		String sql = "insert into ft_lottery_filter_result(value) values(?)";
		this.getJdbcTemplate().update(sql, new Object[] { redResult });
	}

	public void insertFootballFilterCode() {
		String sql="insert into ft_lottery_filter_result(id,value) select id ,value from ft_lottery_all_result";
		this.getJdbcTemplate().update(sql);
	}

	public void deleteFootballFilterCode(String value) {
		String sql="delete from ft_lottery_filter_result where value=?";
		this.getJdbcTemplate().update(sql,new Object[]{value});
	}
}
