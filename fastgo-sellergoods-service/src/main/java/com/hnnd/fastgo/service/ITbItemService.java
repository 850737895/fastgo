package com.hnnd.fastgo.service;

import org.apache.solr.client.solrj.SolrServerException;

import java.io.IOException;

/**
 * sku service
 * Created by 85073 on 2018/12/8.
 */
public interface ITbItemService {

    /**
     * solr数据初始化
     */
    void initSolr() throws IOException, SolrServerException;
}

