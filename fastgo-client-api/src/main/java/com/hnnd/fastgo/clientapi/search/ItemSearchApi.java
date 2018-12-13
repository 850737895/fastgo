package com.hnnd.fastgo.clientapi.search;

import com.hnnd.fastgo.entity.TbItem;
import com.hnnd.fastgo.vo.SystemVo;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;

/**
 * 搜索远程服务
 * Created by 85073 on 2018/12/9.
 */
@FeignClient(value = "fastgo-search-service",fallback = ItemSearchApiImpl.class,path = "/remote/tbItem")
public interface ItemSearchApi {

    @RequestMapping("/searchList")
    public Map searchList(@RequestBody Map<String,Object> searchMap);

    @RequestMapping("/add2Solr")
    public SystemVo add2Solr(@RequestBody List<TbItem> tbItemList);

    @RequestMapping("/del4Solr")
    public SystemVo del4Solr(@RequestBody List<Long> skuIds);
}
