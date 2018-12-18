package com.hnnd.fastgo.service;

import com.hnnd.fastgo.vo.SystemVo;

import java.io.IOException;

/**
 * 商品详情页生成
 * Created by Administrator on 2018/12/18.
 */
public interface IGoodsDetailService {

    SystemVo genHtml(Long goodsId) throws IOException;
}
