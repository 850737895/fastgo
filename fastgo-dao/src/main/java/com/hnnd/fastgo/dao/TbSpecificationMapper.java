package com.hnnd.fastgo.dao;

import com.hnnd.fastgo.entity.TbSpecification;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TbSpecificationMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_specification
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_specification
     *
     * @mbggenerated
     */
    int insert(TbSpecification record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_specification
     *
     * @mbggenerated
     */
    TbSpecification selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_specification
     *
     * @mbggenerated
     */
    List<TbSpecification> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_specification
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(TbSpecification record);
}