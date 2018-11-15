package com.hnnd.fastgo.service;

import com.hnnd.fastgo.entity.TbBrand;
import com.hnnd.fastgo.vo.PageResultVo;
import com.hnnd.fastgo.vo.SystemVo;

import java.util.List;

/**
 * 品牌管理接口
 * Created by 85073 on 2018/11/13.
 */
public interface ISellerGoodsService {

    /**
     * 查询品牌列表
     * @return TbBrand 列表
     */
    List<TbBrand> selectAll();

    /**
     * 分页查询Tbrand列表
     * @param pageNum 当前页
     * @param pageSize 每页多少条
     * @return PageResultVo<TbBrand>
     */
    PageResultVo<TbBrand> selectAllByPage(Integer pageNum,Integer pageSize);

    /**
     * 保存商品品牌
     * @param tbBrand 入参数
     */
    void save(TbBrand tbBrand);

    /**
     * 根据id查询到品牌信息
     * @param id  用户id
     * @return TbBrand
     */
    TbBrand findOneById(Long id);

    /**
     * 根据主键修改品牌信息
     * @param tbBrand
     */
    void modifyBrandById(TbBrand tbBrand);

    /**
     * 根据id删除品牌信息
     * @param ids 品牌id
     */
    void delBrandById(String []  ids);

    /**
     * 条件搜索
     * @param tbBrand 查询条件
     * @param pageNum 当前页
     * @param pageSize 每页的条数
     * @return PageResultVo<TbBrand>
     */
    PageResultVo<TbBrand> search(TbBrand tbBrand,Integer pageNum,Integer pageSize);
}
