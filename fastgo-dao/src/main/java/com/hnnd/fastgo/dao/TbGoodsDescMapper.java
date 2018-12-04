package com.hnnd.fastgo.dao;

import com.hnnd.fastgo.entity.TbGoodsDesc;

import java.util.List;

public interface TbGoodsDescMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_goods_desc
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Long goodsId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_goods_desc
     *
     * @mbggenerated
     */
    int insert(TbGoodsDesc record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_goods_desc
     *
     * @mbggenerated
     */
    TbGoodsDesc selectByPrimaryKey(Long goodsId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_goods_desc
     *
     * @mbggenerated
     */
    List<TbGoodsDesc> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_goods_desc
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(TbGoodsDesc record);

    int updateByExampleSelective(TbGoodsDesc record);
}