package com.hnnd.fastgo.search.controller;

import com.hnnd.fastgo.entity.TbItem;
import com.hnnd.fastgo.enumration.SellerGoodsEnum;
import com.hnnd.fastgo.search.service.ISearchService;
import com.hnnd.fastgo.vo.SystemVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.solr.client.solrj.SolrServerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * sku 搜索工程
 * Created by 85073 on 2018/12/9.
 */
@RestController
@RequestMapping("/remote/tbItem")
@Slf4j
public class TbItemSearchController {

    @Autowired
    private ISearchService searchServiceImpl;

    @RequestMapping("/searchList")
    public Map searchList(@RequestBody  Map<String,Object> searchMap) {

        try {
            return searchServiceImpl.searchList(searchMap);
        } catch (Exception e) {
            log.error("执行solr搜索服务异常:",e);
            return null;
        }
    }

    /**
     * 系统第一次启动的时候，全量导入
     * @return
     */
    @RequestMapping("/initImportSolrList")
    public SystemVo initImportSolrList(){
        return searchServiceImpl.initImportSolrList();
    }

    /**
     * 商品商家 增量导入
     * @param tbItemList
     * @return
     */
    @RequestMapping("/add2Solr")
    public SystemVo add2Solr(@RequestBody List<TbItem> tbItemList){
        if(tbItemList.isEmpty()) {
            log.warn("导入到solr库中的sku列表为空");
            return SystemVo.error(SellerGoodsEnum.ADD_SOLR_SKULIST_IS_EMPTY);
        }
        //导入数据
        try {
            searchServiceImpl.add2Solr(tbItemList);
            return SystemVo.success(SellerGoodsEnum.SELLER_GOODS_SUCCESS);
        } catch (Exception e) {
            log.error("数据导入到索引库异常:{}",e);
            return SystemVo.error(SellerGoodsEnum.IMPORT_ADUIT_PASS_SKU_ERROR);
        }
    }

    /**
     * 商品下架 删除
     * @param skuIds
     * @return
     */
    @RequestMapping("/del4Solr")
    public SystemVo del4Solr(@RequestBody List<Long> skuIds){
        if(skuIds.isEmpty()) {
            log.error("根据skuid批量删除操作入参异常:{}",skuIds);
            return SystemVo.error(SellerGoodsEnum.DEL_SOLR_SKULIST_IS_EMPTY);
        }

        try {
            searchServiceImpl.del4Solr(skuIds);
            return SystemVo.success(SellerGoodsEnum.SELLER_GOODS_SUCCESS);
        } catch (Exception e) {
            log.error("数据从索引库删除异常:{}",e);
            return SystemVo.error(SellerGoodsEnum.DEL_SKU_4_SOLR_ERROR);
        }
    }
}
