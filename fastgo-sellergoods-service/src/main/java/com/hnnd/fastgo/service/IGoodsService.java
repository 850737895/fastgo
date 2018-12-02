package com.hnnd.fastgo.service;

import com.hnnd.fastgo.vo.GoodsVo;
import com.hnnd.fastgo.vo.SystemVo;

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
}
