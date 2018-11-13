package com.hnnd.fastgo.dao;

import com.hnnd.fastgo.entity.TbAreas;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TbAreasMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_areas
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_areas
     *
     * @mbggenerated
     */
    int insert(TbAreas record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_areas
     *
     * @mbggenerated
     */
    TbAreas selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_areas
     *
     * @mbggenerated
     */
    List<TbAreas> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_areas
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(TbAreas record);
}