package com.hnnd.fastgo.service;

import com.hnnd.fastgo.vo.SystemVo;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * 生成页面服务
 * Created by Administrator on 2018/12/14.
 */
public interface IGoodsDetailService {
    /**
     * 根据商品ID 生产商品详情页面
     * @param goodsId 商品id
     * @return
     */
    SystemVo generatorHtmlByGoodsId(@PathVariable("goodsId") Long goodsId);
}
