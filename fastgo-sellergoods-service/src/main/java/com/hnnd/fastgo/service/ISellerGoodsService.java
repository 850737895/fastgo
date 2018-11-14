package com.hnnd.fastgo.service;

import com.hnnd.fastgo.entity.TbBrand;
import com.hnnd.fastgo.vo.PageResultVo;

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
}
