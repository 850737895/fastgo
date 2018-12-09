package com.hnnd.fastgo.controller;

import com.hnnd.fastgo.enumration.SellerGoodsEnum;
import com.hnnd.fastgo.service.ITbItemService;
import com.hnnd.fastgo.vo.SystemVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * 商品sku
 * Created by 85073 on 2018/12/8.
 */
@RestController
@RequestMapping("/sellerGoods/tbItem")
@Slf4j
public class TbItemController {

    @Autowired
    private ITbItemService tbItemServiceImpl;

    /**
     * solr 初始化数据
     * @return
     */
    @RequestMapping("/initSolr")
    public SystemVo initTbItemSolr() {
        try {
            tbItemServiceImpl.initSolr();
            return SystemVo.success(SellerGoodsEnum.SELLER_GOODS_SUCCESS);
        } catch (Exception e) {
            log.error("从数据库中加载sku列表到solr服务器异常:{}",e);
            return SystemVo.error(SellerGoodsEnum.SOLR_INIT_ERROR);
        }
    }
}
