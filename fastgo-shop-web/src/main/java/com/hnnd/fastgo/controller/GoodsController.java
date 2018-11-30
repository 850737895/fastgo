package com.hnnd.fastgo.controller;

import com.hnnd.fastgo.clientapi.sellergoods.seller.SellerApi;
import com.hnnd.fastgo.clientapi.sellergoods.spec.SellerGoodsSpecApi;
import com.hnnd.fastgo.entity.TbSpecificationOption;
import com.hnnd.fastgo.enumration.SellerGoodsEnum;
import com.hnnd.fastgo.vo.GoodsVo;
import com.hnnd.fastgo.vo.SpecOpsVo;
import com.hnnd.fastgo.vo.SpecVo;
import com.hnnd.fastgo.vo.SystemVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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

    @Autowired
    private SellerGoodsSpecApi sellerGoodsSpecApi;

    @RequestMapping("/save")
    public SystemVo save(@RequestBody GoodsVo goodsVo) {
        log.info("接受到的入参GOODSVO:{}",goodsVo);
        return SystemVo.success(SellerGoodsEnum.SELLER_GOODS_SUCCESS);
    }

    /**
     * 通过模版ID查询规格列表
     * @return SystemVo<List<TbSpecificationOption>>
     */
    @RequestMapping("/specOpsList/{typeTempId}")
    public SystemVo<List<SpecOpsVo>> findSpecOpsByTypeTempId(@PathVariable("typeTempId") Long typeTempId) {
        if(null==typeTempId) {
            SystemVo.error(SellerGoodsEnum.SELLER_QRY_SPECINFO_BY_TEMPID_ISNULL);
        }
        List<SpecVo> specOpsVoList = sellerGoodsSpecApi.findSpecOpsByTypeTempId(typeTempId);
        if(null == specOpsVoList) {
            return SystemVo.error(SellerGoodsEnum.SELLER_QRY_SPECINFO_BY_TEMPID_ERROR);
        }
        return SystemVo.success(specOpsVoList,SellerGoodsEnum.SELLER_GOODS_SUCCESS);
    }
}
