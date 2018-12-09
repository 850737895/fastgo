package com.hnnd.fastgo.search.service;
import org.apache.solr.client.solrj.SolrServerException;

import java.io.IOException;
import java.util.Map;

/**
 * 搜索服务
 * Created by 85073 on 2018/12/9.
 */
public interface ISearchService {

    Map searchList(Map<String,Object> searchMap) throws IOException, SolrServerException;
}
