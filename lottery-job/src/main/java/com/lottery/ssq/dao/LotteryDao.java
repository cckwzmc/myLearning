package com.lottery.ssq.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.ObjectUtils;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;

import com.lottery.dao.JdbcBaseDao;

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
	public List getLottoryAllResultLimit(int first, int page) {
		String sql = "select value from ssq_lottery_all_result t order by id limit " + first + "," + page;
		return this.getJdbcTemplate().queryForList(sql);
	}

	@SuppressWarnings("unchecked")
	public List getLottoryAllResultLimit(int first, int page, String lValue) {
		String sql = "select value from ssq_lottery_all_result t where t.value=? order by id limit " + first + ","
				+ page;
		return this.getJdbcTemplate().queryForList(sql, new Object[] { lValue });
	}

	@SuppressWarnings("unchecked")
	public List getSsqLottoryAllResult() {
		String sql = "select value from ssq_lottery_all_result t order by id ";
		return this.getJdbcTemplate().queryForList(sql);
	}

	public int getTotalLotteryAllResult() {
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
	public boolean isGenLotteryResult(String type, String lotteryQh) {
		String sql = "select is_gen from lottery_gen_log t where t.type=? and t.lottery_qh=? and is_gen=1";
		List list = this.getJdbcTemplate().queryForList(sql, new Object[] { type, lotteryQh });
		if (CollectionUtils.isNotEmpty(list)) {
			return true;
		}
		return false;
	}

	@SuppressWarnings("unchecked")
	public String getGenLotteryMaxExpect(String type) {
		String sql = "select max(lottery_qh) expect from lottery_gen_log t where t.type=? and t.is_gen=1";
		List list = this.getJdbcTemplate().queryForList(sql, new Object[] { type });
		if (CollectionUtils.isNotEmpty(list)) {
			return (String) ((Map) list.get(0)).get("expect");
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
	public void saveSsqLotteryHistoryStat(String expect, String type, String code, String num, String fen,
			boolean isTrue) {
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
			e.printStackTrace();
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
		String sql = "update ssq_lottery_media set content=? where expect=? and type=?";
		if (this.getJdbcTemplate().update(sql, new Object[] { xmlData, expect, type }) < 1) {
			sql = "insert into ssq_lottery_media values(?,?,?)";
			this.getJdbcTemplate().update(sql, new Object[] { expect, xmlData, type });
		}
	}

	public int getSsqLotteryMediaByExpect(String expect, String type) {
		String sql = "select count(*) from ssq_lottery_media t where t.expect=? and t.type=?";
		return this.getJdbcTemplate().queryForInt(sql, new Object[] { expect, type });
	}

	/**
	 * @param expect
	 * @param type
	 *            0:500wan 1:sina
	 * @return
	 */
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

	@SuppressWarnings("unchecked")
	public List getSsqLotteryCollectFetchLimit(int first, int page, String net) {
		String sql = "select id,code from ssq_lottery_collect_fetch t where t.net=? and code!='-1'  limit " + first
				+ "," + page;
		return this.getJdbcTemplate().queryForList(sql, new Object[] { net });
	}

	@SuppressWarnings("unchecked")
	public List getSsqLotteryCollectResultLimit(int first, int page) {
		String sql = "select first,second,third,fourth,firth,sixth from ssq_lottery_collect_result t  limit " + first
				+ "," + page;
		return this.getJdbcTemplate().queryForList(sql);
	}

	public void deleteSsqLotteryAllFilterResult() {
		String sql = "delete from ssq_lottery_filter_result where value in(select value from ssq_lottery_collect_result)";
		this.getJdbcTemplate().update(sql);
	}

	/**
	 * @param danSet
	 * @param type
	 *            0:专业媒体;1用户
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
		String sql = "select first redcode ,sum(c) cc from (select * from (select first,count(*) c from ssq_lottery_collect_result t group by t.first order by c desc) t where c>20000 union all"
				+ " select * from (select second,count(*) c from ssq_lottery_collect_result t group by t.second order by c desc) t   union all"
				+ " select * from (select third,count(*) c from ssq_lottery_collect_result t group by t.third order by c desc) t   union all "
				+ " select * from (select fourth,count(*) c from ssq_lottery_collect_result t group by t.fourth order by c desc) t   union all "
				+ " select * from (select firth,count(*) c from ssq_lottery_collect_result t group by t.firth order by c desc) t   union all "
				+ " select * from (select sixth,count(*) c from ssq_lottery_collect_result t group by t.sixth order by c desc) t  )"
				+ " ccc group by first   order by cc desc  limit 0,10";
		return this.getJdbcTemplate().queryForList(sql);
	}

	/**
	 * 用户选择号码统计
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List getSsqLotteryFetchResultSort() {
		String sql = "select first redcode ,sum(c) cc from (select * from (select first,count(*) c from ssq_lottery_collect_result t group by t.first order by c desc) t where c>20000 union all"
				+ " select * from (select second,count(*) c from ssq_lottery_collect_result t group by t.second order by c desc) t   union all"
				+ " select * from (select third,count(*) c from ssq_lottery_collect_result t group by t.third order by c desc) t   union all "
				+ " select * from (select fourth,count(*) c from ssq_lottery_collect_result t group by t.fourth order by c desc) t   union all "
				+ " select * from (select firth,count(*) c from ssq_lottery_collect_result t group by t.firth order by c desc) t   union all "
				+ " select * from (select sixth,count(*) c from ssq_lottery_collect_result t group by t.sixth order by c desc) t  )"
				+ " ccc group by first   order by cc desc ";
		return this.getJdbcTemplate().queryForList(sql);
	}

	public void backupSsqLotteryCollectResult(String expect) {
		// try{
		// String sql="select * from  ssq_lottery_collect_result_"+expect+" t limit 0,1";
		// List list=this.getJdbcTemplate().queryForList(sql);
		// }catch(Exception e)
		// {
		String sql = "create table if not exists ssq_lottery_collect_result_" + expect
				+ " as select * from ssq_lottery_collect_result";
		this.getJdbcTemplate().execute(sql);
		// }
	}

	@SuppressWarnings("unchecked")
	public String getMaxLotteryFetchJob() {
		String sql = "select count(*) from lottery_fetch_job t where t.is_complete=0";
		if (this.getJdbcTemplate().queryForInt(sql) > 1) {
			sql = "update lottery_fetch_job  is_complete=1 where expect<>(select max(expect) expect from lottery_fetch_job)";
		}
		sql = "select max(expect) expect from lottery_fetch_job  where is_complete=1";
		List list = this.getJdbcTemplate().queryForList(sql);
		if (CollectionUtils.isNotEmpty(list)) {
			return ObjectUtils.toString(((Map) list.get(0)).get("expect"));
		}
		return "";
	}

	public void batchInitSaveSsqLotteryFilterResult(final List list) {
		if (CollectionUtils.isEmpty(list)) {
			return;
		}
		BatchPreparedStatementSetter pps = null;
		String sql = "insert into ssq_lottery_filter_result(value) values (?)";
		pps = new BatchPreparedStatementSetter() {

			@Override
			public void setValues(PreparedStatement ps, int i) throws SQLException {
				ps.setObject(1, ((Map) list.get(i)).get("value"));
			}

			@Override
			public int getBatchSize() {
				return list.size();
			}
		};
		try {
			this.getJdbcTemplate().batchUpdate(sql, pps);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}

	public void clearLotterySsqDanResult() {
		String sql = "DELETE from ssq_lottery_dan_result";
		this.getJdbcTemplate().update(sql);
	}

	public List getSsqLotteryCollectResultTopN(int n) {
		String sql = "select redcode,count(*) c from (select CONCAT_ws(',',s.first,s.second,s.third,s.fourth,firth,sixth) redcode from ssq_lottery_collect_result s ) t group by t.redcode order by c desc limit 0,"
				+ n;
		return this.getJdbcTemplate().queryForList(sql);
	}

	public int getTotalLotteryCollectResult(String expect) {
		try {
			return this.getJdbcTemplate().queryForInt("select count(*) from ssq_lottery_collect_result_" + expect);
		} catch (Exception e) {
			return 0;
		}
	}

	@SuppressWarnings("unchecked")
	public List getSsqLotteryCollectResultLimit(int first, int page, String expect) {
		// try {
		// String sql = "select first,second,third,fourth,firth,sixth from ssq_lottery_collect_result_" + expect +
		// " t  limit " + first + "," + page;
		// return this.getJdbcTemplate().queryForList(sql);
		// } catch (Exception e) {
		// return null;
		// }
		//	
		try {
			String sql = "select redcode,count(*) c from (select CONCAT_ws(',',s.first,s.second,s.third,s.fourth,firth,sixth) redcode from ssq_lottery_collect_result_"
					+ expect + " s ) t group by t.redcode order by c  limit " + first + "," + page;
			return this.getJdbcTemplate().queryForList(sql);
		} catch (Exception e) {
			return null;
		}
	}

	public List getSsqLotteryCollectResult(String location, int start, int end) {
		String sql = "";
		if ("first".equals(location)) {
			sql = "select t.first redcode,count(*) c from ssq_lottery_collect_result t group by t.first order by c desc limit "
					+ start + "," + end;
		}
		if ("second".equals(location)) {
			sql = "select t.second redcode,count(*) c from ssq_lottery_collect_result t group by t.second order by c desc limit "
					+ start + "," + end;
		}
		if ("third".equals(location)) {
			sql = "select t.third redcode,count(*) c from ssq_lottery_collect_result t group by t.third order by c desc limit "
					+ start + "," + end;
		}
		if ("fourth".equals(location)) {
			sql = "select t.fourth redcode,count(*) c from ssq_lottery_collect_result t group by t.fourth order by c desc limit "
					+ start + "," + end;
		}
		if ("firth".equals(location)) {
			sql = "select t.firth redcode,count(*) c from ssq_lottery_collect_result t group by t.firth order by c desc limit "
					+ start + "," + end;
		}
		if ("sixth".equals(location)) {
			sql = "select t.sixth redcode,count(*) c from ssq_lottery_collect_result t group by t.sixth order by c desc limit "
					+ start + "," + end;
		}
		if ("".equals(sql)) {
			return null;
		}
		return this.getJdbcTemplate().queryForList(sql);
	}

	/**
	 * 投注的统计大于5个以上的红球号码
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List getSsqLotteryCollectResultCountLessThan5() {
		String sql = "select * from (select redcode,count(*) c from (select CONCAT_ws(',',s.first,s.second,s.third,s.fourth,firth,sixth) redcode from ssq_lottery_collect_result s ) t group by t.redcode order by c desc ) t where t.c>5";
		return this.getJdbcTemplate().queryForList(sql);
	}

	@SuppressWarnings("unchecked")
	public List getSsqLotteryCollectFetchByType(String net) {
		String sql = "select * from ssq_lottery_collect_fetch t where t.net=?";
		return this.getJdbcTemplate().queryForList(sql, new Object[] { net });
	}

	/**
	 * 更新LotterySSqFILTERCONFIG
	 * 
	 * @param cfgValue
	 * @param cfgName
	 */
	public void updateLotterySsqFilterConfig(String cfgValue, String cfgName) {
		String sql = "update ssq_lottery_filter_config set config_value=? where config_name=?";
		this.getJdbcTemplate().update(sql, new Object[] { cfgValue, cfgName });
	}

	/**
	 * 动态过滤方法查询
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List getSsqLotteryDynamicFilterMethod() {
		String sql = "select * from ssq_lottery_dynamic_filter_method t where t.enabled=1";
		return this.getJdbcTemplate().queryForList(sql);
	}

	/**
	 * 有且仅有一个组红球
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List getSsqLotteryCollectResultCountLe3(int first, int last) {
		String sql = "select * from (select redcode,count(*) c from (select CONCAT_ws(',',s.first,s.second,s.third,s.fourth,firth,sixth) redcode from ssq_lottery_collect_result s ) t group by t.redcode order by c desc ) t where t.c<=3 limit "
				+ first + "," + last;
		return this.getJdbcTemplate().queryForList(sql);
	}

	@SuppressWarnings("unchecked")
	public List getSsqLotteryCollectResultCountLe1(int first, int last) {
		String sql = "select * from (select redcode,count(*) c from (select CONCAT_ws(',',s.first,s.second,s.third,s.fourth,firth,sixth) redcode from ssq_lottery_collect_result s ) t group by t.redcode order by c desc ) t where t.c=1 limit "
				+ first + "," + last;
		return this.getJdbcTemplate().queryForList(sql);
	}

	public void saveCollectBlueCodeResult(Map<String, Integer> blueMap, String expect) {
		if (MapUtils.isEmpty(blueMap)) {
			return;
		}
		for (String bluecode : blueMap.keySet()) {
			String sql = "";
			sql = "update ssq_lottery_collect_bluecode_result set statnum=statnum+" + blueMap.get(bluecode).intValue()
					+ " where bluecode=? and expect=?";
			int count = this.getJdbcTemplate().update(sql, new Object[] { bluecode, expect });
			if (count == 0) {
				sql = "insert into ssq_lottery_collect_bluecode_result(bluecode,expect,statnum) values (?,?,?)";
				this.getJdbcTemplate().update(sql, new Object[] { bluecode, expect, blueMap.get(bluecode).intValue() });
			}
		}
	}
	
	
	/**
	 * 
	 * ssq_lottery_collect_result
	 * 投注用户小于等于2的号码
	 * @param first
	 * @param last
	 * @return
	 */
	public List getSsqLotteryCollectResultCountLe2(int first,int last){
		String sql=" select * from (select value,count(*) c from " +
				"(select CONCAT_ws(',',s.first,s.second,s.third,s.fourth,firth,sixth) value from ssq_lottery_collect_result s ) t" +
				"  group by t.redcode) t  where c<=2 order by c limit "+ first + "," + last;
		return this.getJdbcTemplate().queryForList(sql);
	}
}
