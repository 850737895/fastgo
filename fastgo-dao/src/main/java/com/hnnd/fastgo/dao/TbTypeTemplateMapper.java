package com.hnnd.fastgo.dao;

import com.hnnd.fastgo.entity.TbTypeTemplate;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TbTypeTemplateMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_type_template
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_type_template
     *
     * @mbggenerated
     */
    int insert(TbTypeTemplate record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_type_template
     *
     * @mbggenerated
     */
    TbTypeTemplate selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_type_template
     *
     * @mbggenerated
     */
    List<TbTypeTemplate> selectAll();

    List<TbTypeTemplate> selectPageByCondition(@Param("qryCondition") String qryCondition);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_type_template
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(TbTypeTemplate record);
}