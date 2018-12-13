package com.hnnd.fastgo.clientapi.search;

import com.hnnd.fastgo.entity.TbItem;
import com.hnnd.fastgo.enumration.SellerGoodsEnum;
import com.hnnd.fastgo.vo.SystemVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * 搜索降级服务
 * Created by 85073 on 2018/12/9.
 */
@Component
@Slf4j
public class ItemSearchApiImpl implements ItemSearchApi {
    @Override
    public Map searchList(Map<String, Object> searchMap) {
        log.error("根据搜索条件:{}执行搜索服务异常",searchMap);
        return null;
    }

    @Override
    public SystemVo add2Solr(List<TbItem> tbItemList) {
        log.error("添加审核通过的商品详情到solr库中异常");
        return SystemVo.error(SellerGoodsEnum.IMPORT_ADUIT_PASS_SKU_ERROR);
    }

    @Override
    public SystemVo del4Solr(List<Long> skuIds) {
        log.error("从索引库中删除商品sku异常");
        return SystemVo.error(SellerGoodsEnum.DEL_SKU_4_SOLR_ERROR);
    }
}
