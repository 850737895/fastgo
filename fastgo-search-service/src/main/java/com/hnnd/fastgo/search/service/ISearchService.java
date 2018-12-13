package com.hnnd.fastgo.search.service;
import com.hnnd.fastgo.entity.TbItem;
import com.hnnd.fastgo.vo.SystemVo;
import org.apache.solr.client.solrj.SolrServerException;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * 搜索服务
 * Created by 85073 on 2018/12/9.
 */
public interface ISearchService {

    Map searchList(Map<String,Object> searchMap) throws IOException, SolrServerException;

    SystemVo initImportSolrList();

    void add2Solr(List<TbItem> tbItemList) throws IOException, SolrServerException;

    void del4Solr(List<Long> skuIds) throws IOException, SolrServerException;
}
