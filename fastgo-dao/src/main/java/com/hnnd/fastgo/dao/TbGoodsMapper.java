package com.hnnd.fastgo.dao;

import com.hnnd.fastgo.entity.TbGoods;
import org.apache.ibatis.annotations.Param;

import java.util.List;

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

    List<TbGoods> findList4Page(TbGoods tbGoods);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_goods
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(TbGoods record);

    int updateByExampleSelective(TbGoods record);

    int updateGoodsStatusBatch(@Param("sellerId") String sellerId,@Param("changeStatus") String changeStatus,@Param("ids") List<Long> ids);

    int updateGoodsDelStatusBatch(@Param("sellerId") String sellerId,@Param("changeStatus") String changeStatus,@Param("ids") List<Long> ids);

    int goodsUpOrDownMarket(@Param("sellerId") String sellerId,@Param("changeStatus") String changeStatus,@Param("ids") List<Long> ids);

    void aduitPass(@Param("goodIds") List<Long> goodIds,@Param("status") String status);
}