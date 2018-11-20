package com.hnnd.fastgo.controller;

import com.hnnd.fastgo.clientapi.sellergoods.spec.SellerGoodsSpecApi;
import com.hnnd.fastgo.entity.TbSpecification;
import com.hnnd.fastgo.enumration.SellerGoodsEnum;
import com.hnnd.fastgo.vo.PageResultVo;
import com.hnnd.fastgo.vo.SpecVo;
import com.hnnd.fastgo.vo.SystemVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


/**
 * 规格管理控制层
 * Created by Administrator on 2018/11/16.
 */
@RestController
@RequestMapping("/spec")
public class SpecController {

    @Autowired
    private SellerGoodsSpecApi sellerGoodsSpecApi;

    /**
     * 带条件查询分页列表
     * @param pageSize 每页条数
     * @param pageNum 当前页码
     * @param queryCondition 查询条件
     * @return
     */
    @RequestMapping("/list")
    public SystemVo<PageResultVo<TbSpecification>> list(@RequestParam( value = "pageSize",defaultValue = "10") Integer pageSize,
                                                       @RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum,
                                                       @RequestParam(value = "queryCondition",required = false) String queryCondition) {
        PageResultVo<TbSpecification> specLists = sellerGoodsSpecApi.list(pageSize,pageNum,queryCondition);
        if(null ==specLists) {
            return SystemVo.error(SellerGoodsEnum.SELLER_GOODS_PAGELIST_ERROR);
        }
        return SystemVo.success(specLists,SellerGoodsEnum.SELLER_GOODS_SUCCESS);
    }

    @RequestMapping("/initSpecList")
    public SystemVo<List<Map<String,Object>>> initSpecList() {
        if(null == sellerGoodsSpecApi.initSpecList()) {
            return SystemVo.error(SellerGoodsEnum.SELLER_GOODS_INIT_SELECT2_SPECLIST_ERROR);
        }
       return SystemVo.success(sellerGoodsSpecApi.initSpecList(),SellerGoodsEnum.SELLER_GOODS_SUCCESS);
    }

    /**
     * 保存规格
     * @param specVo 规格vo
     * @return SystemVo
     */
    @RequestMapping("/save")
    public SystemVo saveSpec(@RequestBody SpecVo specVo) {
        return sellerGoodsSpecApi.saveSpec(specVo);
    }

    @RequestMapping("/findOne/{id}")
    public SystemVo<SpecVo> findOne(@PathVariable("specId") Long specId){
        return sellerGoodsSpecApi.findOne(specId);
    }


}
