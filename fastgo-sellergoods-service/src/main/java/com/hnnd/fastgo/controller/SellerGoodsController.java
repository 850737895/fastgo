package com.hnnd.fastgo.controller;

import com.alibaba.fastjson.JSON;
import com.hnnd.fastgo.enumration.SellerGoodsEnum;
import com.hnnd.fastgo.service.IGoodsService;
import com.hnnd.fastgo.vo.GoodsVo;
import com.hnnd.fastgo.vo.SystemVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 商品服务controlelr
 * Created by 85073 on 2018/12/1.
 */
@RestController
@RequestMapping("/sellerGoods/goods")
@Slf4j
public class SellerGoodsController {

    @Autowired
    private IGoodsService goodsServiceImpl;


    @RequestMapping("/save")
    public SystemVo save(@RequestBody GoodsVo goodsVo) {
        log.info("接受到的入参GOODSVO:{}", JSON.toJSONString(goodsVo));
        try {
            goodsServiceImpl.save(goodsVo);
            log.info("保存商品信息成功");
            return SystemVo.success(SellerGoodsEnum.SELLER_GOODS_SUCCESS);
        } catch (Exception e) {
            log.info("保存商品信息异常:{}",e);
            return SystemVo.error(SellerGoodsEnum.SELLER_GOODSINFO_SAVE_ERROR);
        }

    }

}