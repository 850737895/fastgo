package com.hnnd.fastgo.controller;

import com.hnnd.fastgo.clientapi.sellergoods.spec.SellerGoodsSpecApi;
import com.hnnd.fastgo.entity.TbSpecification;
import com.hnnd.fastgo.enumration.SellerGoodsEnum;
import com.hnnd.fastgo.service.ISellerGoodsSpecService;
import com.hnnd.fastgo.vo.PageResultVo;
import com.hnnd.fastgo.vo.SpecVo;
import com.hnnd.fastgo.vo.SystemVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


/**
 * 规格服务
 * Created by Administrator on 2018/11/16.
 */
@RestController
@RequestMapping("/sellerGoods/spec")
@Slf4j
public class SellerGoodsSpecController implements SellerGoodsSpecApi {

    @Autowired
    private ISellerGoodsSpecService sellerGoodsSpecServiceImpl;

    @RequestMapping("/list")
    public PageResultVo<TbSpecification> list(@RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize,
                                                    @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                                    @RequestParam(value = "queryCondition",required = false) String queryCondition)
    {
        return sellerGoodsSpecServiceImpl.selectAllByPage(pageNum,pageSize,queryCondition);
    }


    @RequestMapping("/initSpecList")
    public List<Map<String,Object>> initSpecList() {
        return sellerGoodsSpecServiceImpl.initSpecList();
    }

    @RequestMapping("/save")
    public SystemVo saveSpec(@RequestBody SpecVo specVo) {
        if(null ==specVo) {
            return SystemVo.error(SellerGoodsEnum.SELLER_GOODS_IN_PARAM_ERROR);
        }
        try {
            return sellerGoodsSpecServiceImpl.saveSpec(specVo);
        } catch (Exception e) {
            log.error("保存商品规格异常:{}",e);
            return SystemVo.error(SellerGoodsEnum.SELLER_GOODS_SAVE_SPEC_ERROR);
        }

    }

    @RequestMapping("/findOne/{id}")
    public SystemVo<SpecVo> findOne(@PathVariable("specId") Long specId) {
        try {
            SpecVo specVo = sellerGoodsSpecServiceImpl.findOne(specId);
            return  SystemVo.success(specVo,SellerGoodsEnum.SELLER_GOODS_SUCCESS);
        } catch (Exception e) {
            log.error("根据规格ID:{}查询规格信息异常:{}",specId,e);
            return SystemVo.error(SellerGoodsEnum.SELLER_GOODS_FINDONE_SPEC_ERROR);
        }
    }

}
