package com.hnnd.fastgo.clientapi.sellergoods.Item;

import com.hnnd.fastgo.entity.TbItem;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Administrator on 2018/12/13.
 */
@Component
@Slf4j
public class ItemApiImpl implements ItemApi {
    @Override
    public List<TbItem> initImportSolrList() {
        log.error("查询solr初始化导入sku列表异常");
        return null;
    }
}
