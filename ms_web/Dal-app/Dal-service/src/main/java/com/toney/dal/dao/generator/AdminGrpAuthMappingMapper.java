package com.toney.dal.dao.generator;

import com.toney.dal.model.generator.AdminGrpAuthMapping;
import com.toney.dal.model.generator.AdminGrpAuthMappingExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AdminGrpAuthMappingMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_admingrp_auth
     *
     * @mbggenerated Tue Jul 02 10:47:25 CST 2013
     */
    int countByExample(AdminGrpAuthMappingExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_admingrp_auth
     *
     * @mbggenerated Tue Jul 02 10:47:25 CST 2013
     */
    int deleteByExample(AdminGrpAuthMappingExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_admingrp_auth
     *
     * @mbggenerated Tue Jul 02 10:47:25 CST 2013
     */
    int deleteByPrimaryKey(Short id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_admingrp_auth
     *
     * @mbggenerated Tue Jul 02 10:47:25 CST 2013
     */
    int insert(AdminGrpAuthMapping record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_admingrp_auth
     *
     * @mbggenerated Tue Jul 02 10:47:25 CST 2013
     */
    int insertSelective(AdminGrpAuthMapping record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_admingrp_auth
     *
     * @mbggenerated Tue Jul 02 10:47:25 CST 2013
     */
    List<AdminGrpAuthMapping> selectByExample(AdminGrpAuthMappingExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_admingrp_auth
     *
     * @mbggenerated Tue Jul 02 10:47:25 CST 2013
     */
    AdminGrpAuthMapping selectByPrimaryKey(Short id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_admingrp_auth
     *
     * @mbggenerated Tue Jul 02 10:47:25 CST 2013
     */
    int updateByExampleSelective(@Param("record") AdminGrpAuthMapping record, @Param("example") AdminGrpAuthMappingExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_admingrp_auth
     *
     * @mbggenerated Tue Jul 02 10:47:25 CST 2013
     */
    int updateByExample(@Param("record") AdminGrpAuthMapping record, @Param("example") AdminGrpAuthMappingExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_admingrp_auth
     *
     * @mbggenerated Tue Jul 02 10:47:25 CST 2013
     */
    int updateByPrimaryKeySelective(AdminGrpAuthMapping record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_admingrp_auth
     *
     * @mbggenerated Tue Jul 02 10:47:25 CST 2013
     */
    int updateByPrimaryKey(AdminGrpAuthMapping record);
}