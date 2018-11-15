package com.hnnd.fastgo.controller;

import com.hnnd.fastgo.clientapi.sellergoods.SellerGoodsApi;
import com.hnnd.fastgo.entity.TbBrand;
import com.hnnd.fastgo.enumration.SellerGoodsEnum;
import com.hnnd.fastgo.vo.PageResultVo;
import com.hnnd.fastgo.vo.SystemVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 商品品牌管理
 * Created by Administrator on 2018/11/14.
 */
@RestController
@RequestMapping("/brand")
@Slf4j
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

    @RequestMapping("/save")
    public SystemVo saveBrand(@RequestBody TbBrand tbBrand) {
        return sellerGoodsApi.save(tbBrand);
    }

    @RequestMapping("/findOne/{id}")
    public SystemVo findOneById(@PathVariable("id") Long id) {
        return sellerGoodsApi.findOneById(id);
    }

    @RequestMapping("/modify")
    public SystemVo modify(@RequestBody TbBrand tbBrand) {
        return sellerGoodsApi.modifyBrandById(tbBrand);
    }

    @RequestMapping("/del")
    public SystemVo delBrandById(@RequestParam("ids") String[] ids){
        if(null == ids|| ids.length==0) {
            return SystemVo.error(SellerGoodsEnum.SELLER_GOODS_DEL_NULL_PARAM);
        }
        return sellerGoodsApi.delBrandById(ids);
    }

    /**
     * 分页收索
     * @param tbBrand 搜索对象
     * @param pageNum 当前页
     * @param pageSize 每页多少条
     * @return SystemVo<List<TbBrand>>
     */
    @RequestMapping("/search")
    public SystemVo<PageResultVo<TbBrand>> search(@RequestBody TbBrand tbBrand,
                           @RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum,
                           @RequestParam(value = "pageSize",defaultValue = "10") Integer pageSize) {
        PageResultVo<TbBrand> pageResultVo = sellerGoodsApi.search(tbBrand,pageNum,pageSize);
        if(null == pageResultVo) {
            return SystemVo.error(SellerGoodsEnum.SELLER_GOODS_PAGELIST_ERROR);
        }
        return SystemVo.success(pageResultVo,SellerGoodsEnum.SELLER_GOODS_SUCCESS);
    }
}
