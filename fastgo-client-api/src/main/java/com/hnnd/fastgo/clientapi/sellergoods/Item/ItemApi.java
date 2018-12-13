package com.hnnd.fastgo.clientapi.sellergoods.Item;

import com.hnnd.fastgo.entity.TbItem;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Created by Administrator on 2018/12/13.
 */
@FeignClient(name ="fastgo-sellergoods-service",fallback = ItemApiImpl.class, path = "/sellerGoods/tbItem")
public interface ItemApi {

    @RequestMapping("/initImportSolrList")
    public List<TbItem> initImportSolrList();

}
