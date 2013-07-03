package com.toney.dal.dao.generator;

import com.toney.dal.model.generator.AdminAuthDetailMapping;
import com.toney.dal.model.generator.AdminAuthDetailMappingExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AdminAuthDetailMappingMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_admin_auth_detail
     *
     * @mbggenerated Tue Jul 02 10:47:25 CST 2013
     */
    int countByExample(AdminAuthDetailMappingExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_admin_auth_detail
     *
     * @mbggenerated Tue Jul 02 10:47:25 CST 2013
     */
    int deleteByExample(AdminAuthDetailMappingExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_admin_auth_detail
     *
     * @mbggenerated Tue Jul 02 10:47:25 CST 2013
     */
    int deleteByPrimaryKey(Short id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_admin_auth_detail
     *
     * @mbggenerated Tue Jul 02 10:47:25 CST 2013
     */
    int insert(AdminAuthDetailMapping record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_admin_auth_detail
     *
     * @mbggenerated Tue Jul 02 10:47:25 CST 2013
     */
    int insertSelective(AdminAuthDetailMapping record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_admin_auth_detail
     *
     * @mbggenerated Tue Jul 02 10:47:25 CST 2013
     */
    List<AdminAuthDetailMapping> selectByExample(AdminAuthDetailMappingExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_admin_auth_detail
     *
     * @mbggenerated Tue Jul 02 10:47:25 CST 2013
     */
    AdminAuthDetailMapping selectByPrimaryKey(Short id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_admin_auth_detail
     *
     * @mbggenerated Tue Jul 02 10:47:25 CST 2013
     */
    int updateByExampleSelective(@Param("record") AdminAuthDetailMapping record, @Param("example") AdminAuthDetailMappingExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_admin_auth_detail
     *
     * @mbggenerated Tue Jul 02 10:47:25 CST 2013
     */
    int updateByExample(@Param("record") AdminAuthDetailMapping record, @Param("example") AdminAuthDetailMappingExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_admin_auth_detail
     *
     * @mbggenerated Tue Jul 02 10:47:25 CST 2013
     */
    int updateByPrimaryKeySelective(AdminAuthDetailMapping record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_admin_auth_detail
     *
     * @mbggenerated Tue Jul 02 10:47:25 CST 2013
     */
    int updateByPrimaryKey(AdminAuthDetailMapping record);
}