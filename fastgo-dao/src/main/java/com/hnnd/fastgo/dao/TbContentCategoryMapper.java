package com.hnnd.fastgo.dao;

import com.hnnd.fastgo.entity.TbContentCategory;

import java.util.List;

public interface TbContentCategoryMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_content_category
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_content_category
     *
     * @mbggenerated
     */
    int insert(TbContentCategory record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_content_category
     *
     * @mbggenerated
     */
    TbContentCategory selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_content_category
     *
     * @mbggenerated
     */
    List<TbContentCategory> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_content_category
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(TbContentCategory record);
}