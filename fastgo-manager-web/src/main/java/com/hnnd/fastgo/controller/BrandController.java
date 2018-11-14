package com.hnnd.fastgo.controller;

import com.hnnd.fastgo.clientapi.sellergoods.SellerGoodsApi;
import com.hnnd.fastgo.entity.TbBrand;
import com.hnnd.fastgo.vo.SystemVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 商品品牌管理
 * Created by Administrator on 2018/11/14.
 */
@RestController
@RequestMapping("/brand")
public class BrandController {

    @Autowired
    private SellerGoodsApi sellerGoodsApi;

    @RequestMapping("/list")
    public SystemVo<List<TbBrand>>  selectAll() {
        SystemVo systemVo = sellerGoodsApi.selectAll();
        return  sellerGoodsApi.selectAll();
    }
}
