package com.hnnd.fastgo.dao;

import com.hnnd.fastgo.entity.TbProvinces;

import java.util.List;

public interface TbProvincesMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_provinces
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_provinces
     *
     * @mbggenerated
     */
    int insert(TbProvinces record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_provinces
     *
     * @mbggenerated
     */
    TbProvinces selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_provinces
     *
     * @mbggenerated
     */
    List<TbProvinces> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_provinces
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(TbProvinces record);


}