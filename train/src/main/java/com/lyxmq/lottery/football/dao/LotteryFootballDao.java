package com.lyxmq.lottery.football.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;

import com.myfetch.myfetch.dao.JdbcBaseDao;

public class LotteryFootballDao extends JdbcBaseDao {

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

	public void batchSaveFootballLottoryAllResult(final List<String> footballList) {
		if (CollectionUtils.isEmpty(footballList)) {
			return;
		}
		BatchPreparedStatementSetter pps = null;
		String sql = "insert into ft_lottery_all_result(value) values(?)";
		pps = new BatchPreparedStatementSetter() {

			@Override
			public void setValues(PreparedStatement ps, int i) throws SQLException {
				ps.setString(1, footballList.get(i));
			}

			@Override
			public int getBatchSize() {
				return footballList.size();
			}
		};
		try {
			this.getJdbcTemplate().batchUpdate(sql, pps);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}		
	}

	public void batchSaveFootballCollectionResult(final List<String> footballList) {
		if (CollectionUtils.isEmpty(footballList)) {
			return;
		}
		BatchPreparedStatementSetter pps = null;
		String sql = "insert into ft_lottery_collect_result(value) values(?)";
		pps = new BatchPreparedStatementSetter() {

			@Override
			public void setValues(PreparedStatement ps, int i) throws SQLException {
				ps.setString(1, footballList.get(i));
			}

			@Override
			public int getBatchSize() {
				return footballList.size();
			}
		};
		try {
			this.getJdbcTemplate().batchUpdate(sql, pps);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}	
	}
	public void batchSaveFootballCollectionFetch(final List<String> footballList,final int type,final String expect) {
		if (CollectionUtils.isEmpty(footballList)) {
			return;
		}
		BatchPreparedStatementSetter pps = null;
		String sql = "insert into ft_lottery_collect_fetch(code,type,expect) values(?,?,?)";
		pps = new BatchPreparedStatementSetter() {

			@Override
			public void setValues(PreparedStatement ps, int i) throws SQLException {
				ps.setString(1, footballList.get(i));
				ps.setInt(2, type);
				ps.setString(3, expect);
			}

			@Override
			public int getBatchSize() {
				return footballList.size();
			}
		};
		try {
			this.getJdbcTemplate().batchUpdate(sql, pps);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}	
	}
	public void saveFootballLottoryFilterResult() {
		String sql="insert into ft_lottery_filter_result(value) select value from ft_lottery_all_result";
		this.getJdbcTemplate().update(sql);
		sql="delete from ft_lottery_all_result where value in(select value from ft_lottery_collect_result)";
		this.getJdbcTemplate().update(sql);
	}

	@SuppressWarnings("unchecked")
	public Map getFtLotteryMedia(String expect, String type) {
		String sql="select t.content from ft_lottery_media t where t.expect=? and t.type=?";
		List list=this.getJdbcTemplate().queryForList(sql,new Object[]{expect,type});
		if(CollectionUtils.isNotEmpty(list)){
			return (Map)list.get(0);
		}
		return null;
	}

	public void saveFootballFetchMedia(String type, String content, String expect) {
		String sql="insert into ft_lottery_media(expect,content,type) values(?,?,?)";
		this.getJdbcTemplate().update(sql,new Object[]{expect,content,type});
	}
	public int getCountFootballFetchMedia(String type, String expect) {
		String sql="select count(*) from ft_lottery_media t where t.expect=? and t.type=?";
		return this.getJdbcTemplate().queryForInt(sql,new Object[]{expect,type});
	}
}
