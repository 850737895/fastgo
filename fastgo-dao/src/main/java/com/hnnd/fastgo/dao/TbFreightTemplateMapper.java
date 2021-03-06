package com.hnnd.fastgo.dao;

import com.hnnd.fastgo.entity.TbFreightTemplate;

import java.util.List;

public interface TbFreightTemplateMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_freight_template
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_freight_template
     *
     * @mbggenerated
     */
    int insert(TbFreightTemplate record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_freight_template
     *
     * @mbggenerated
     */
    TbFreightTemplate selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_freight_template
     *
     * @mbggenerated
     */
    List<TbFreightTemplate> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_freight_template
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(TbFreightTemplate record);
}