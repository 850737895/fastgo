package com.hnnd.fastgo.controller;

import com.hnnd.fastgo.entity.TbItem;
import com.hnnd.fastgo.enumration.SellerGoodsEnum;
import com.hnnd.fastgo.service.ITbItemService;
import com.hnnd.fastgo.vo.SystemVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


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
     * 系统第一次初始化导入sku列表到solr的查询方法
     * @return
     */
    @RequestMapping("/initImportSolrList")
    public List<TbItem> initImportSolrList() {
        return tbItemServiceImpl.initImportSolrList();
    }
}
