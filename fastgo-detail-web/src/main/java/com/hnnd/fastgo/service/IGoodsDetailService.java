package com.hnnd.fastgo.service;

import com.hnnd.fastgo.vo.SystemVo;

import java.io.IOException;

/**
 * 商品详情页生成
 * Created by Administrator on 2018/12/18.
 */
public interface IGoodsDetailService {

    /**
     * 根据商品id生成商品详情页
     * @param goodsId 商品id
     * @return
     * @throws IOException
     */
    SystemVo genHtml(Long goodsId) throws IOException;

    /**
     * 根据商品id删除商品详情页
     * @param goodsIs
     * @throws Exception
     */
    void delHtmlByGoodsId(Long goodsIs) throws Exception;
}
