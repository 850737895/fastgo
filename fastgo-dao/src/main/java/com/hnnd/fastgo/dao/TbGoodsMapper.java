package com.hnnd.fastgo.dao;

import com.hnnd.fastgo.entity.TbGoods;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TbGoodsMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_goods
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_goods
     *
     * @mbggenerated
     */
    int insert(TbGoods record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_goods
     *
     * @mbggenerated
     */
    TbGoods selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_goods
     *
     * @mbggenerated
     */
    List<TbGoods> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_goods
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(TbGoods record);
}