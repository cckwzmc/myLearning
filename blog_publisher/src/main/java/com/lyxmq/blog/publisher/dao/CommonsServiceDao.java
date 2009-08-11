package com.lyxmq.blog.publisher.dao;

import java.util.List;

public class CommonsServiceDao extends JdbcBaseDao{
	/**
	 * 查询某个站点的发布数据
	 * @param toblog 发送到那个站点
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List getPublishDataList(String toblog){
		String sql="select * from publish_data t where (t.toblog=? or t.toblog='ALL') and ispublish=0 order by username";
		return this.getJdbcTemplate().queryForList(sql,new Object[]{toblog});
	}
	
	/**
	 * 查询发布数据
	 * @param toblog 发送到那个站点
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List getPublishDataList(){
		String sql="select * from publish_data t where  ispublish=0";
		return this.getJdbcTemplate().queryForList(sql);
	}
	
	/**
	 * 更新已经发布的数据
	 * @param id
	 */
	public void updatePublishData(Integer id){
		String sql="update  publish_data set ispublish=1 where id=?";
		this.getJdbcTemplate().update(sql, new Object[]{id});
	}

	public void saveFailPublish(Integer publishId, String result) {
		String sql="insert into blog_publisher_log(blogId,failnum,faildesc) values(?,0,?)";
		this.getJdbcTemplate().update(sql, new Object[]{publishId,result});
	}
	@SuppressWarnings("unchecked")
	public List getFailPublishById(Integer publishId) {
		String sql="select blogId,failnum,faildesc from blog_publisher_log t where t.blogId=?";
		return this.getJdbcTemplate().queryForList(sql, new Object[]{publishId});
	}
}
