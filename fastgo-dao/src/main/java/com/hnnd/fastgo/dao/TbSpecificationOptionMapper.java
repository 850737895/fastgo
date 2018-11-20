package com.hnnd.fastgo.dao;

import com.hnnd.fastgo.entity.TbSpecificationOption;

import java.util.List;

public interface TbSpecificationOptionMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_specification_option
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_specification_option
     *
     * @mbggenerated
     */
    int insert(TbSpecificationOption record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_specification_option
     *
     * @mbggenerated
     */
    TbSpecificationOption selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_specification_option
     *
     * @mbggenerated
     */
    List<TbSpecificationOption> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_specification_option
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(TbSpecificationOption record);

    List<TbSpecificationOption> selectBySpecId(Long specId);

    /**
     * 批量插入规格选项表
     * @param tbSpecificationOptions
     * @return
     */
    int insertTbSpecOpsBatch(List<TbSpecificationOption> tbSpecificationOptions);

    int deleteTbSpecOpsBySpecId(Long specId);

    int delTbSpecOpsBySepcIdsInBatch(List<Long> specIds);
}