package com.lyxmq.lottery.ssq.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.ObjectUtils;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;

import com.myfetch.myfetch.dao.JdbcBaseDao;

public class LotteryDao extends JdbcBaseDao {

	public void saveSsqLottoryResult(String redResult) {
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
	public List getSsqLottoryResultAll() {
		String sql = "select value from ssq_lottery_all_result t order by id ";
		return this.getJdbcTemplate().queryForList(sql);
	}

	public int getTotalLotteryResult() {
		String sql = "select count(*) from ssq_lottery_all_result";
		return this.getJdbcTemplate().queryForInt(sql);
	}

	// ~~~~~~~~过滤后的查询
	public List getSsqLotteryFilterResultAll() {
		String sql = "select value from ssq_lottery_filter_result t order by id ";
		return this.getJdbcTemplate().queryForList(sql);
	}

	@SuppressWarnings("unchecked")
	public List getSsqLottoryFilterResultLimit(int first, int page) {
		String sql = "select value from ssq_lottery_filter_result t limit " + first + "," + page;
		return this.getJdbcTemplate().queryForList(sql);
	}

	public void addSsqLotteryFilterResult(String redCode) {
		String sql = "insert into ssq_lottery_filter_result(value) values (?)";
		this.getJdbcTemplate().update(sql, new Object[] { redCode });
	}

	/**
	 * @param type 1:ssq 2:football
	 * @param lotteryQh 期号 is_gen=0为生成 1:已生成
	 * @return
	 */
	public void saveLotteryGenLog(String type, String lotteryQh, String isGen) {
		String sql = "insert into lottery_gen_log(type,lottery_qh,is_gen) values(?,?,?)";
		this.getJdbcTemplate().update(sql, new Object[] { type, lotteryQh, isGen });
	}

	/**
	 * @param type 1:ssq 2:football
	 * @param lotteryQh 期号 is_gen=0为生成 1:已生成
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

	public void clearSsqLotteryFilterResult() {
		String sql = "delete from ssq_lottery_filter_result ";
		this.getJdbcTemplate().update(sql);
	}

	public void clearSsqLotteryCollectResult() {
		String sql = "delete from ssq_lottery_collect_result ";
		this.getJdbcTemplate().update(sql);
	}

	public void deleteSsqLotteryFilterResult() {
		String sql = "delete from ssq_lottery_filter_result";
		this.getJdbcTemplate().update(sql);
	}

	/**
	 * @param expect
	 * @param type
	 * @param code
	 * @param num
	 * @param fen
	 * @param isTrue
	 */
	public void saveSsqLotteryHistoryStat(String expect, String type, String code, String num, String fen, boolean isTrue) {
		String sql = "insert into ssq_lottery_his_media_stat values(?,?,?,?,?,?)";
		this.getJdbcTemplate().update(sql, new Object[] { expect, type, code, num, fen, isTrue ? 1 : 0 });
	}

	/**
	 * 保存收集的号码
	 * 
	 * @param redMedia
	 */
	public void saveSsqLotteryCollectRedCod(final List<String[]> redCodeList) {
		if (CollectionUtils.isEmpty(redCodeList)) {
			return;
		}
		BatchPreparedStatementSetter pps = null;
		String sql = "insert into ssq_lottery_collect_result(first,second,third,fourth,firth,sixth) values(?,?,?,?,?,?)";
		pps = new BatchPreparedStatementSetter() {

			@Override
			public void setValues(PreparedStatement ps, int i) throws SQLException {
				ps.setString(1, redCodeList.get(i)[0]);
				ps.setString(2, redCodeList.get(i)[1]);
				ps.setString(3, redCodeList.get(i)[2]);
				ps.setString(4, redCodeList.get(i)[3]);
				ps.setString(5, redCodeList.get(i)[4]);
				ps.setString(6, redCodeList.get(i)[5]);
			}

			@Override
			public int getBatchSize() {
				return redCodeList.size();
			}
		};
		try {
			this.getJdbcTemplate().batchUpdate(sql, pps);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}

	public boolean isExistSsqLotteryCollect(String lValue) {
		String sql = "select count(*) from ssq_lottery_collect_result where  value=?";
		return this.getJdbcTemplate().queryForInt(sql, new Object[] { lValue }) > 0 ? true : false;
	}

	/**
	 * 经过媒体过滤、文件过滤后留下的红球号码
	 * 
	 * @param redList
	 */
	public void batchSaveSsqLotteryFilterResult(final List<String> redList) {
		if (CollectionUtils.isEmpty(redList)) {
			return;
		}
		BatchPreparedStatementSetter pps = null;
		String sql = "insert into ssq_lottery_filter_result(value) values (?)";
		pps = new BatchPreparedStatementSetter() {

			@Override
			public void setValues(PreparedStatement ps, int i) throws SQLException {
				ps.setString(1, redList.get(i));
			}

			@Override
			public int getBatchSize() {
				return redList.size();
			}
		};
		try {
			this.getJdbcTemplate().batchUpdate(sql, pps);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}

	/**
	 * 保存历史开奖号码s
	 * 
	 * @param historyOpenRedcode
	 * @param join
	 * @param sum
	 * @param expect
	 */
	public void saveSsqLotteryHisOpenCode(String blueCode, String redCode, int sum, String expect) {
		String sql = "insert into ssq_lottery_his_open_code values(?,?,?,?)";
		this.getJdbcTemplate().update(sql, new Object[] { expect, redCode, blueCode, sum });

	}

	/**
	 * 媒体统计中最近的一期
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Map getSsqLotteryHisMediaStatMaxExpect() {
		String sql = "select expect from ssq_lottery_his_media_stat order by expect desc limit 0,1";
		List list = this.getJdbcTemplate().queryForList(sql);
		if (CollectionUtils.isNotEmpty(list)) {
			return (Map) list.get(0);
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public Map getSsqLotteryHisOpenCodeMaxExpect() {
		String sql = "select expect from ssq_lottery_his_open_code order by expect desc limit 0,1";
		List list = this.getJdbcTemplate().queryForList(sql);
		if (CollectionUtils.isNotEmpty(list)) {
			return (Map) list.get(0);
		}
		return null;
	}

	public void batchDelSsqLotteryFilterResult(final List<String> redList) {
		if (CollectionUtils.isEmpty(redList)) {
			return;
		}
		BatchPreparedStatementSetter pps = null;
		String sql = "delete from  ssq_lottery_filter_result where value=?";
		pps = new BatchPreparedStatementSetter() {

			@Override
			public void setValues(PreparedStatement ps, int i) throws SQLException {
				ps.setString(1, redList.get(i));
			}

			@Override
			public int getBatchSize() {
				return redList.size();
			}
		};
		try {
			this.getJdbcTemplate().batchUpdate(sql, pps);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}

	public void deleteSsqLotteryFilterResultInCollect() {
		String sql = "delete from ssq_lottery_filter_result  where value in(select value from ssq_lottery_collect_result)";
		this.getJdbcTemplate().update(sql);
	}

	public int getTotalLotteryCollectResult() {
		String sql = "select count(*) from ssq_lottery_collect_result";
		return this.getJdbcTemplate().queryForInt(sql);
	}

	public void batchSaveSsqLottoryResult(final List<String> redList) {
		if (CollectionUtils.isEmpty(redList)) {
			return;
		}
		BatchPreparedStatementSetter pps = null;
		String sql = "insert into ssq_lottery_all_result(value) values (?)";
		pps = new BatchPreparedStatementSetter() {

			@Override
			public void setValues(PreparedStatement ps, int i) throws SQLException {
				ps.setString(1, redList.get(i));
			}

			@Override
			public int getBatchSize() {
				return redList.size();
			}
		};
		try {
			this.getJdbcTemplate().batchUpdate(sql, pps);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}

	public void saveSsqLotteryMedia(String type, String expect, String xmlData) {
		String sql = "insert into ssq_lottery_media values(?,?,?)";
		this.getJdbcTemplate().update(sql, new Object[] { expect, xmlData, type });
	}

	public int getSsqLotteryMediaByExpect(String expect, String type) {
		String sql = "select count(*) from ssq_lottery_media t where t.expect=? and t.type=?";
		return this.getJdbcTemplate().queryForInt(sql, new Object[] { expect, type });
	}

	@SuppressWarnings("unchecked")
	public String getSsqLotteryMediaContentByExpect(String expect, String type) {
		String sql = "select content from ssq_lottery_media t where t.expect=? and t.type=?";
		List list = this.getJdbcTemplate().queryForList(sql, new Object[] { expect, type });
		if (CollectionUtils.isNotEmpty(list)) {
			return ObjectUtils.toString(((Map) list.get(0)).get("content"));
		}
		return "";
	}

	public void saveSsqLotteryFilterResult() {
		String sql = "insert into ssq_lottery_filter_result(value) select value from ssq_lottery_all_result";
		this.getJdbcTemplate().update(sql);

	}

	public int[] deleteSsqLotteryFilterResult(final List<String> redList) {
		if (CollectionUtils.isEmpty(redList)) {
			return null;
		}
		BatchPreparedStatementSetter pps = null;
		String sql = "delete from  ssq_lottery_filter_result where value=?";
		pps = new BatchPreparedStatementSetter() {

			@Override
			public void setValues(PreparedStatement ps, int i) throws SQLException {
				ps.setObject(1, redList.get(i));
			}

			@Override
			public int getBatchSize() {
				return redList.size();
			}
		};
		try {
			int[] ret = this.getJdbcTemplate().batchUpdate(sql, pps);
			return ret;
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return null;
	}

	public int getCountSsqLotteryCollectFetchByProid(String proid, String net) {
		String sql = "select count(*) from ssq_lottery_collect_fetch t where t.id=? and t.net=?";
		return this.getJdbcTemplate().queryForInt(sql, new Object[] { proid, net });
	}

	public void saveSsqLotteryCollectFetch(String string, String string2, String join) {

	}

	public void batchSaveSsqLotteryCollectFetch(final List<Map<String, String>> resultList) {
		if (CollectionUtils.isEmpty(resultList)) {
			return;
		}
		BatchPreparedStatementSetter pps = null;
		String sql = "insert into ssq_lottery_collect_fetch(id,net,code,expect,isfail) values(?,?,?,?,?)";
		pps = new BatchPreparedStatementSetter() {

			@Override
			public void setValues(PreparedStatement ps, int i) throws SQLException {
				ps.setString(1, resultList.get(i).get("proid"));
				ps.setString(2, resultList.get(i).get("net"));
				ps.setString(3, resultList.get(i).get("code"));
				ps.setString(4, resultList.get(i).get("expect"));
				ps.setString(5, resultList.get(i).get("isfail"));
			}

			@Override
			public int getBatchSize() {
				return resultList.size();
			}
		};
		try {
			this.getJdbcTemplate().batchUpdate(sql, pps);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}

	public void clearHisFetchProjectCode(String expect, String net) {
		String sql = "delete from ssq_lottery_collect_fetch where net=? and expect<>?";
		this.getJdbcTemplate().update(sql, new Object[] { net, expect });
	}

	public List getSsqLotteryCollectFetchLimit(int first, int page, String net) {
		String sql = "select id,code from ssq_lottery_collect_fetch t where t.net=? and code!='-1'  limit " + first + "," + page;
		return this.getJdbcTemplate().queryForList(sql, new Object[] { net });
	}

	public List getSsqLotteryCollectResultLimit(int first, int page) {
		String sql = "select first,second,third,fourth,firth,sixth from ssq_lottery_collect_result t  limit " + first + "," + page;
		return this.getJdbcTemplate().queryForList(sql);
	}

	public void deleteSsqLotteryAllFilterResult() {
		String sql = "delete from ssq_lottery_filter_result where value in(select value from ssq_lottery_collect_result)";
		this.getJdbcTemplate().update(sql);
	}

	/**
	 * @param danSet
	 * @param type 0:专业媒体;1用户
	 */
	public void batchSqqLotteryDanResult(final List<String> danSet, final String type) {
		if (CollectionUtils.isEmpty(danSet)) {
			return;
		}
		BatchPreparedStatementSetter pps = null;
		String sql = "insert into ssq_lottery_dan_result(dan,type) values(?,?)";
		pps = new BatchPreparedStatementSetter() {

			@Override
			public void setValues(PreparedStatement ps, int i) throws SQLException {
				ps.setString(1, danSet.get(i));
				ps.setString(2, type);
			}

			@Override
			public int getBatchSize() {
				return danSet.size();
			}
		};
		try {
			this.getJdbcTemplate().batchUpdate(sql, pps);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}

	public List getSsqLotteryDanResult(String type) {
		String sql = "select dan from ssq_lottery_dan_result t where t.type=?";
		return this.getJdbcTemplate().queryForList(sql, new Object[] { type });
	}

	public List getSsqLotteryDanResult() {
		String sql = "select dan from ssq_lottery_dan_result t ";
		return this.getJdbcTemplate().queryForList(sql);
	}

	public List getSsqLotteryFilterResultTop10() {
		String sql="select first redcode ,sum(c) cc from (select * from (select first,count(*) c from ssq_lottery_collect_result t group by t.first order by c desc) t where c>20000 union all" +
				" select * from (select second,count(*) c from ssq_lottery_collect_result t group by t.second order by c desc) t   union all" +
				" select * from (select third,count(*) c from ssq_lottery_collect_result t group by t.third order by c desc) t   union all " +
				" select * from (select fourth,count(*) c from ssq_lottery_collect_result t group by t.fourth order by c desc) t   union all " +
				" select * from (select firth,count(*) c from ssq_lottery_collect_result t group by t.firth order by c desc) t   union all " +
				" select * from (select sixth,count(*) c from ssq_lottery_collect_result t group by t.sixth order by c desc) t  )" +
				" ccc group by first   order by cc desc  limit 0,10";
		return this.getJdbcTemplate().queryForList(sql);
	}
	/**
	 * 用户选择号码统计
	 * @return
	 */
	public List getSsqLotteryFetchResultSort() {
		String sql="select first redcode ,sum(c) cc from (select * from (select first,count(*) c from ssq_lottery_collect_result t group by t.first order by c desc) t where c>20000 union all" +
				" select * from (select second,count(*) c from ssq_lottery_collect_result t group by t.second order by c desc) t   union all" +
				" select * from (select third,count(*) c from ssq_lottery_collect_result t group by t.third order by c desc) t   union all " +
				" select * from (select fourth,count(*) c from ssq_lottery_collect_result t group by t.fourth order by c desc) t   union all " +
				" select * from (select firth,count(*) c from ssq_lottery_collect_result t group by t.firth order by c desc) t   union all " +
				" select * from (select sixth,count(*) c from ssq_lottery_collect_result t group by t.sixth order by c desc) t  )" +
				" ccc group by first   order by cc desc ";
		return this.getJdbcTemplate().queryForList(sql);
	}
}
