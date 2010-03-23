package com.lyxmq.lottery.ssq;

import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;

import com.myfetch.myfetch.dao.JdbcBaseDao;

public class LotteryDao extends JdbcBaseDao {

	public void saveLottoryResult(String redResult) {
		String sql = "insert into ssq_lottery_all_result(value) values(?)";
		this.getJdbcTemplate().update(sql, new Object[] { redResult });
	}

	public void saveTempLottoryResult(String redResult) {
		String sql = "insert into ssq_lottery_filter_result(value) values(?)";
		this.getJdbcTemplate().update(sql, new Object[] { redResult });
	}

	@SuppressWarnings("unchecked")
	public List getLottoryResultLimit(int first, int page) {
		String sql = "select value from ssq_lottery_all_result t order by id limit " + first + "," + page;
		return this.getJdbcTemplate().queryForList(sql);
	}

	@SuppressWarnings("unchecked")
	public List getLottoryResultAll() {
		String sql = "select value from ssq_lottery_all_result t order by id ";
		return this.getJdbcTemplate().queryForList(sql);
	}

	public int getTotalLotteryResult() {
		String sql = "select count(*) from ssq_lottery_all_result";
		return this.getJdbcTemplate().queryForInt(sql);
	}

	// ~~~~~~~~过滤后的查询
	public List getLotteryFilterResultAll() {
		String sql = "select value from ssq_lottery_filter_result t order by id ";
		return this.getJdbcTemplate().queryForList(sql);
	}

	@SuppressWarnings("unchecked")
	public List getLottoryFilterResultLimit(int first, int page) {
		String sql = "select value from ssq_lottery_filter_result t order by id limit " + first + "," + page;
		return this.getJdbcTemplate().queryForList(sql);
	}

	public void addSsqLotteryFilterResult(String redCode) {
		String sql = "insert into ssq_lottery_filter_result(value) values (?)";
		this.getJdbcTemplate().update(sql, new Object[] { redCode });
	}

	/**
	 * @param type
	 *            1:ssq 2:football
	 * @param lotteryQh
	 *            期号 is_gen=0为生成 1:已生成
	 * @return
	 */
	public void saveLotteryGenLog(String type, String lotteryQh, String isGen) {
		String sql = "insert into lottery_gen_log(type,lottery_qh,is_gen) values(?,?,?)";
		this.getJdbcTemplate().update(sql, new Object[] { type, lotteryQh, isGen });
	}

	/**
	 * @param type
	 *            1:ssq 2:football
	 * @param lotteryQh
	 *            期号 is_gen=0为生成 1:已生成
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Map isGenLotteryResult(String type, String lotteryQh) {
		String sql = "select is_gen from lottery_gen_log t where t.type=? and t.lottery_qh=?";
		List list = this.getJdbcTemplate().queryForList(sql, new Object[] { type, lotteryQh });
		if (CollectionUtils.isNotEmpty(list)) {
			return (Map) list.get(0);
		}
		return null;
	}
	public int getTotalLotteryFilterResult() {
		String sql = "select count(*) from ssq_lottery_filter_result";
		return this.getJdbcTemplate().queryForInt(sql);
	}
<<<<<<< .mine

	public void clearSsqLotteryFilterResult() {
		String sql="delete from ssq_lottery_filter_result ";
		this.getJdbcTemplate().update(sql);
	}
=======

	public void deleteSsqLotteryFilterResult() {
		String sql="delete from ssq_lottery_filter_result";
		this.getJdbcTemplate().update(sql);
	}
>>>>>>> .r203
}
