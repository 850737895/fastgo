package com.hnnd.fastgo.controller;

import com.hnnd.fastgo.clientapi.sellergoods.SellerGoodsApi;
import com.hnnd.fastgo.entity.TbBrand;
import com.hnnd.fastgo.enumration.SellerGoodsEnum;
import com.hnnd.fastgo.vo.PageResultVo;
import com.hnnd.fastgo.vo.SystemVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
        return  sellerGoodsApi.selectAll();
    }

    @GetMapping("/pageList")
    public SystemVo<PageResultVo<TbBrand>> selectAllByPage(@RequestParam(value = "pageNum",defaultValue = "1")Integer pageNum,
                                                 @RequestParam(value = "pageSize",defaultValue = "10")Integer pageSize) {
        PageResultVo<TbBrand> pageResultVo = sellerGoodsApi.selectAllByPage(pageNum,pageSize);
        if(null == pageResultVo) {
            return SystemVo.error(SellerGoodsEnum.SELLER_GOODS_PAGELIST_ERROR);
        }
        return SystemVo.success(pageResultVo,SellerGoodsEnum.SELLER_GOODS_SUCCESS);
    }
}
