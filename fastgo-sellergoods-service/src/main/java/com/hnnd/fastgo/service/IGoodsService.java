package com.hnnd.fastgo.service;

import com.hnnd.fastgo.entity.TbGoods;
import com.hnnd.fastgo.vo.GoodsVo;
import com.hnnd.fastgo.vo.PageResultVo;

/**
 * 商品服务接口
 * Created by 85073 on 2018/12/1.
 */
public interface IGoodsService {

    /**
     * 保存商品
     * @param goodsVo 商品VO对象
     */
    void save(GoodsVo goodsVo) throws RuntimeException;

    /**
     * 条件分页查询
     * @param tbGoods 查询条件
     * @param pageNum 当前页码
     * @param pageSize 每页多少条
     * @return  PageResultVo<TbGoods>
     */
    PageResultVo<TbGoods> findList4Page(TbGoods tbGoods,Integer pageNum,Integer pageSize);
}
