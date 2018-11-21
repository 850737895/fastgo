package com.hnnd.fastgo.service;

import com.hnnd.fastgo.entity.TbItemCat;
import com.hnnd.fastgo.vo.PageResultVo;

import java.util.List;

/**
 * 商品类目管理
 * Created by Administrator on 2018/11/21.
 */
public interface ISellerGoodsItemCatService {

    /**
     * 按照层级查询分页商品类目
     * @param pageNum 当前页码
     * @param pageSize 每页多少条
     * @param parentId 父类ID
     * @param qyrCondition 查询条件
     * @return PageResultVo<TbItemCat>
     */
    PageResultVo<TbItemCat> level(Integer pageNum,Integer pageSize,Integer parentId,String qyrCondition);

    /**
     * 根据父类商品类目
     * @param parentId
     * @return
     */
     List<TbItemCat> findByParentId(Integer parentId);
}
