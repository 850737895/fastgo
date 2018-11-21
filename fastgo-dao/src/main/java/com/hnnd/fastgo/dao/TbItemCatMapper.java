package com.hnnd.fastgo.dao;

import com.hnnd.fastgo.entity.TbItemCat;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TbItemCatMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_item_cat
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Long id);


    int delInbatch(List<Long> idList);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_item_cat
     *
     * @mbggenerated
     */
    int insert(TbItemCat record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_item_cat
     *
     * @mbggenerated
     */
    TbItemCat selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_item_cat
     *
     * @mbggenerated
     */
    List<TbItemCat> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_item_cat
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(TbItemCat record);

     List<TbItemCat> level(@Param("parentId") Integer parentId, @Param("qryCondition") String qyrCondition);
}