package com.hnnd.fastgo.clientapi.search;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

/**
 * 搜索远程服务
 * Created by 85073 on 2018/12/9.
 */
@FeignClient(value = "fastgo-search-service",fallback = ItemSearchApiImpl.class,path = "/remote/tbItem")
public interface ItemSearchApi {

    @RequestMapping("/searchList")
    public Map searchList(@RequestBody Map<String,Object> searchMap);
}
