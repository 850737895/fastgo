package com.hnnd.fastgo.dao;


import com.hnnd.fastgo.entity.TbAddress;

import java.util.List;

public interface TbAddressMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_address
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_address
     *
     * @mbggenerated
     */
    int insert(TbAddress record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_address
     *
     * @mbggenerated
     */
    TbAddress selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_address
     *
     * @mbggenerated
     */
    List<TbAddress> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_address
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(TbAddress record);
}