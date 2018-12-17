package com.hnnd.fastgo.controller;

import com.hnnd.fastgo.enumration.SellerGoodsEnum;
import com.hnnd.fastgo.service.IGoodsDetailService;
import com.hnnd.fastgo.vo.SystemVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



/**
 * 商品详情页码
 * Created by Administrator on 2018/12/14.
 */
@RestController
@Slf4j
@RequestMapping("/remote/goodsDetail")
public class GoodsDetailController {

    @Autowired
    private IGoodsDetailService goodsDetailService;

    @RequestMapping("/generatorHtmlByGoodsId/{goodsId}")
    public SystemVo generatorHtmlByGoodsId(@PathVariable("goodsId") Long goodsId) {
        if(goodsId==null) {
            log.error("根据商品ID 生成详情页码入参为空");
            return SystemVo.error(SellerGoodsEnum.GEN_GOODSDETAIL_INPARAM_NULL);
        }
        //生成模版
        try {
            return goodsDetailService.generatorHtmlByGoodsId(goodsId);
        }catch (Exception e) {
            log.error("生成商品详情静态模版异常:{}",e);
            return SystemVo.error(SellerGoodsEnum.GEN_GOODSDETAIL_ERROR);
        }
    }
}
