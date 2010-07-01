package com.lottery.ssq.fetch.dao;

import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;

import com.lottery.dao.JdbcBaseDao;

public class LotteryFetchDao extends JdbcBaseDao {
	public void saveSsqLotteryWebFetchList(String title) {

	}

	@SuppressWarnings("unchecked")
	public List getSsqLotteryWebFetchList(int flag) {
		String sql = "select * from ssq_lottery_web_fetch_list t where web_flag=?";
		return this.getJdbcTemplate().queryForList(sql, new Object[] { flag });
	}

	/**
	 * @param type
	 *            '0:双色球；1：足彩',
	 * @param is_complet
	 *            '是否已完成；0：未完成；1：已完成',
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List getLotterySsqExpectConfig(int type, int is_complete) {
		String sql = "select * from lottery_fetch_job where type=? and is_complete=? order by id desc limit 0,1";
		List list = this.getJdbcTemplate().queryForList(sql, new Object[] { type, is_complete });
		return list;
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

	/**
	 * @param config_name
	 *            init_filter_date:初始化数据；gen_data：生成投注号码
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Map getLotterySsqFetchConfig(String config_name) {
		String sql = "select * from ssq_lottery_config t where t.config_name=? and is_reFilter=0";
		List list = this.getJdbcTemplate().queryForList(sql, new Object[] { config_name });
		if (CollectionUtils.isNotEmpty(list)) {
			return (Map) list.get(0);
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public List getLotterySsqFetchConfig() {
		String sql = "select * from ssq_lottery_fetch_config t where t.enabled=1";
		return this.getJdbcTemplate().queryForList(sql);
	}

	/**
	 * 号码过滤配置
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List getLotterySsqFilterConfig(String configName, String configValue) {
		String sql = "select * from ssq_lottery_filter_config t where t.enabled=1 and t.config_name=? and config_value=?";
		return this.getJdbcTemplate().queryForList(sql, new Object[] { configName, configValue });
	}

	/**
	 * 号码过滤配置
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List getLotterySsqFilterConfig() {
		String sql = "select * from ssq_lottery_filter_config t where t.enabled=1";
		return this.getJdbcTemplate().queryForList(sql);
	}

	/**
	 * 更新LotterySSqFILTERCONFIG
	 * 
	 * @param cfgValue
	 * @param cfgName
	 */
	public void updateLotterySsqFilterConfig(String cfgValue, String cfgName) {
		String sql = "update ssq_lottery_config set config_value=? where config_name=?";
		this.getJdbcTemplate().update(sql, new Object[] { cfgValue, cfgName });
	}
}
