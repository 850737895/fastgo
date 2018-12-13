package com.hnnd.fastgo.search.controller;

import com.hnnd.fastgo.search.service.ISearchService;
import com.hnnd.fastgo.vo.SystemVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @RequestMapping("/initImportSolrList")
    public SystemVo initImportSolrList(){
        return searchServiceImpl.initImportSolrList();
    }
}
