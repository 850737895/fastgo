package com.hnnd.fastgo.service;

import com.hnnd.fastgo.entity.TbBrand;

import java.util.List;

/**
 * 品牌管理接口
 * Created by 85073 on 2018/11/13.
 */
public interface IBrandService {

    /**
     * 查询品牌列表
     * @return TbBrand 列表
     */
    List<TbBrand> selectAll();
}
