package com.lottery.ssq.fetch.dao;

import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lottery.dao.JdbcBaseDao;

/**
 * @author ly.zy.ljh
 */
public class LotteryFetchDao extends JdbcBaseDao {
	private static final Logger logger = LoggerFactory.getLogger(LotteryFetchDao.class);

	public void saveSsqLotteryWebFetchList(String title) {

	}

	@SuppressWarnings("unchecked")
	public List getSsqLotteryWebFetchList(int flag) {
		String sql = "select * from ssq_lottery_web_fetch_list t where web_flag=?";
		return this.getJdbcTemplate().queryForList(sql, new Object[] { flag });
	}

	/**
	 * @param type '0:双色球；1：足彩',
	 * @param is_complet '是否已完成；0：未完成；1：已完成',
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List getLotterySsqExpectConfig(int type, int is_complete) {
		String sql = "select * from lottery_fetch_job where type=? and is_complete=? order by id desc limit 0,1";
		List list = this.getJdbcTemplate().queryForList(sql, new Object[] { type, is_complete });
		return list;
	}

	/**
	 * @param type '0:双色球；1：足彩',
	 * @param is_complet '是否已完成；0：未完成；1：已完成',
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List getLotterySsqExpectConfig(int type, int is_complete, String expect) {
		String sql = "select * from lottery_fetch_job where type=? and is_complete=? and expect=? order by id desc limit 0,1";
		List list = this.getJdbcTemplate().queryForList(sql, new Object[] { type, is_complete, expect });
		return list;
	}

	/**
	 * @param type 1:ssq 2:football
	 * @param lotteryQh 期号 is_gen=0为生成 1:已生成
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
	 * @param config_name init_filter_date:初始化数据；gen_data：生成投注号码
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
	 * 号码过滤配置
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List getLotterySsqFilterConfig(Integer isSelf) {
		String sql = "select * from ssq_lottery_filter_config t where t.enabled=1 and t.is_self="+isSelf;
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

	/**
	 * 在各大网站保存抓取数据
	 * 
	 * @param webFetchcode
	 * @param expect
	 * @param object
	 */
	public void batchLotterySsqCommendCode(String webFetchcode, String expect, Object fid) {
		try {
			String sql = "insert into ssq_lottery_web_fetch_result values(?,?,?)";
			super.getJdbcTemplate().update(sql, new Object[] { expect, fid, webFetchcode });
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}

	/**
	 * 各大网站收集的数据.
	 * 
	 * @param expect
	 * @return
	 */
	public List getWebCollectList(String expect) {
		String sql = "select content from ssq_lottery_web_fetch_result t where t.expect=?";
		return this.getJdbcTemplate().queryForList(sql, new Object[] { expect });
	}

	@SuppressWarnings("unchecked")
	public List getSsqLotteryWebFetchResult(String ids, String expect) {
		String sql = "select content from ssq_lottery_web_fetch_result t where t.expect=? and fid in(" + ids + ")";
		return this.getJdbcTemplate().queryForList(sql, new Object[] { expect });
	}

	public void saveWebFetchDanResult(String code) {
		try {
			String sql = "insert into ssq_lottery_dan_result(dan,type) values(?,?)";
			this.getJdbcTemplate().update(sql, new Object[] { code, 2 });
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}
}
