package com.hnnd.fastgo.dao;

import com.hnnd.fastgo.entity.TbSeckillGoods;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

public interface TbSeckillGoodsMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_seckill_goods
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_seckill_goods
     *
     * @mbggenerated
     */
    int insert(TbSeckillGoods record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_seckill_goods
     *
     * @mbggenerated
     */
    TbSeckillGoods selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_seckill_goods
     *
     * @mbggenerated
     */
    List<TbSeckillGoods> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_seckill_goods
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(TbSeckillGoods record);
}