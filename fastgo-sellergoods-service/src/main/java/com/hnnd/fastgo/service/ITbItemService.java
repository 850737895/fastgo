package com.hnnd.fastgo.service;

import com.hnnd.fastgo.entity.TbItem;
import org.apache.solr.client.solrj.SolrServerException;

import java.io.IOException;
import java.util.List;

/**
 * sku service
 * Created by 85073 on 2018/12/8.
 */
public interface ITbItemService {

    /**
     * solr数据初始化
     */
    List<TbItem> initImportSolrList();
}

