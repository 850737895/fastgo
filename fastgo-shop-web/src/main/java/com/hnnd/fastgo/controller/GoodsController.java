package com.hnnd.fastgo.controller;

import com.hnnd.fastgo.clientapi.sellergoods.seller.SellerApi;
import com.hnnd.fastgo.enumration.SellerGoodsEnum;
import com.hnnd.fastgo.vo.GoodsVo;
import com.hnnd.fastgo.vo.SystemVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 商品模块
 * Created by Administrator on 2018/11/29.
 */
@RestController
@RequestMapping("/goods")
@Slf4j
public class GoodsController {


    @Autowired
    private SellerApi sellerApi;

    @RequestMapping("/save")
    public SystemVo save(@RequestBody GoodsVo goodsVo) {
        log.info("接受到的入参GOODSVO:{}",goodsVo);
        return SystemVo.success(SellerGoodsEnum.SELLER_GOODS_SUCCESS);
    }
}
