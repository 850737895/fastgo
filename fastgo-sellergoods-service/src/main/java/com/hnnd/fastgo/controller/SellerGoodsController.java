package com.hnnd.fastgo.controller;

import com.hnnd.fastgo.clientapi.sellergoods.SellerGoodsApi;
import com.hnnd.fastgo.entity.TbBrand;
import com.hnnd.fastgo.enumration.SellerGoodsEnum;
import com.hnnd.fastgo.service.ISellerGoodsService;
import com.hnnd.fastgo.vo.PageResultVo;
import com.hnnd.fastgo.vo.SystemVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 品牌管理controller
 * Created by 85073 on 2018/11/13.
 */
@RestController
@RequestMapping("/sellerGoods")
public class SellerGoodsController implements SellerGoodsApi {

    @Autowired
    private ISellerGoodsService brandServiceImpl;

    /**
     * 查询品牌列表
     * @return SystemVo<List<TbBrand>>
     */
    @GetMapping("/brand/list")
    public SystemVo<List<TbBrand>> selectAll() {
        List<TbBrand> tbBrandList = brandServiceImpl.selectAll();
        return SystemVo.success(tbBrandList, SellerGoodsEnum.SELLER_GOODS_SUCCESS);
    }

    @GetMapping("/brand/pageList")
    public PageResultVo<TbBrand> selectAllByPage(@RequestParam(value = "pageNum",defaultValue = "1")Integer pageNum,
                                                 @RequestParam(value = "pageSzie",defaultValue = "10")Integer pageSize) {
        return brandServiceImpl.selectAllByPage(pageNum,pageSize);
    }


}
