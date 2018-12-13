package com.hnnd.fastgo.service.impl;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;
import com.hnnd.fastgo.dao.TbItemMapper;
import com.hnnd.fastgo.entity.TbItem;
import com.hnnd.fastgo.service.ITbItemService;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.solr.core.SolrTemplate;
import org.springframework.data.solr.core.mapping.SolrDocument;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 数据初始化
 * Created by 85073 on 2018/12/8.
 */
@Service
public class TbItemServiceImpl implements ITbItemService {

    @Autowired
    private TbItemMapper tbItemMapper;

    @Autowired
    private SolrClient solrClient;


    @Override
    public List<TbItem> initImportSolrList() {
        List<TbItem> tbItemList = tbItemMapper.selectSolrList();
        for (TbItem tbItem: tbItemList) {
            Map<String,String> specMap = JSON.parseObject(tbItem.getSpec(),Map.class);
            tbItem.setSpecMap(dealMapKey(specMap));
        }
        return tbItemList;
    }

    /**
     * 由于动态域没有自动添加，所以自己拼接
     * @return
     */
    private Map<String,String> dealMapKey(Map<String,String> specMap) {
        Map<String ,String> dealSpecMap = Maps.newHashMap();
        Set<String> keys = specMap.keySet();
        Iterator<String> keyIterator = keys.iterator();
        while (keyIterator.hasNext()) {
            String oldKey = keyIterator.next();
            String newKey = "item_spec_"+oldKey;
            dealSpecMap.put(newKey,specMap.get(oldKey));
        }
        return dealSpecMap;
    }


}
