package com.hnnd.fastgo.search.service.impl;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.hnnd.fastgo.constant.RedisConstant;
import com.hnnd.fastgo.search.service.ISearchService;
import com.redisoper.IRedisService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrRequest;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.Group;
import org.apache.solr.client.solrj.response.GroupCommand;
import org.apache.solr.client.solrj.response.GroupResponse;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.params.GroupParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;

/**
 * 搜素服务接口
 * Created by 85073 on 2018/12/9.
 */
@Service
@Slf4j
public class SearchServiceImpl implements ISearchService {

    @Autowired
    private SolrClient solrClient;

    @Autowired
    private IRedisService redisServiceImpl;

    @Override
    public Map searchList(Map<String, Object> searchMap) throws IOException, SolrServerException {
        Map<String,Object> resultMap = new HashMap<>();

        resultMap.putAll(searchListByKeyWords(searchMap));
        //获取分组的名称
        List<String> groupNames = searchCategoryGroup(searchMap);
        if(groupNames!=null &&groupNames.size()>0) {
            resultMap.put("groupNames",groupNames);
            //设置规格以及品牌
            Map brandAndSpecMap = searchBrandListAndSpecList(groupNames.get(0));
            if(!brandAndSpecMap.isEmpty()) {
                resultMap.putAll(brandAndSpecMap);
            }
        }

        return resultMap;
    }

    /**
     * 根据模版id 查询品牌列表以及规格列表
     * @param itemCategoryName 商品分类名称
     * @return
     */
    private Map searchBrandListAndSpecList(String itemCategoryName) {
        Map<String,Object> resultMap = Maps.newHashMap();
        //获取模版id
        String typeId =  redisServiceImpl.hget(RedisConstant.ITEMCATE_LIST_KEY,itemCategoryName);
        if(StringUtils.isEmpty(typeId)) {
            log.warn("通过itemCategoryName:{}从redis加载typeId为空",itemCategoryName);
            return null;
        }
        //从缓存中取该模版关联的指定品牌列表
        String brandListStr = redisServiceImpl.hget(RedisConstant.TEMPLATE_KEY,RedisConstant.TEMPLATE_BRAND_KEY+":"+typeId);
        if(StringUtils.isNotEmpty(brandListStr)) {
            List brandList = JSON.parseObject(brandListStr,List.class);
            resultMap.put("brandList",brandList);
        }

        //缓存中取该模版关联的指定规格选项列表
        String specListStr = redisServiceImpl.hget(RedisConstant.TEMPLATE_KEY,RedisConstant.TEMPLATE_SPEC_KEY+":"+typeId);
        if(StringUtils.isNotEmpty(specListStr)) {
            List specList = JSON.parseObject(specListStr,List.class);
            resultMap.put("specList",specList);
        }

        return resultMap;
    }

    /**
     * 根据关键字查询结果(高亮);
     * @param searchMap
     * @return
     * @throws IOException
     * @throws SolrServerException
     */
    private Map searchListByKeyWords(Map<String,Object> searchMap) throws IOException, SolrServerException {
        Map<String,Object> resultMap = new HashMap<>();
        SolrQuery solrQuery = new SolrQuery("*:*");
        //设置查询所有
        if(!searchMap.isEmpty()&& StringUtils.isNotEmpty(searchMap.get("keywords").toString())) {
            //默认查询条件
            solrQuery.set("df","item_keywords");
            //设置值
            solrQuery.setQuery(searchMap.get("keywords").toString());
        }
        //设置高亮
        solrQuery.setHighlight(true);
        //设置高亮字段
        solrQuery.addHighlightField("item_title");
        solrQuery.setHighlightSimplePre("<em style = 'color:red'><b>");
        solrQuery.setHighlightSimplePost("</b></em>");

        QueryResponse queryResponse =solrClient.query(solrQuery);
        replaceHL(queryResponse);

        resultMap.put("rows",queryResponse.getResults());
        return resultMap;
    }

    /**
     * 查询分组
     * @param searchMap
     * @return
     * @throws IOException
     * @throws SolrServerException
     */
    private List<String> searchCategoryGroup(Map<String,Object> searchMap) throws IOException, SolrServerException {
        List<String> groupNames = Lists.newArrayList();
        SolrQuery solrQuery = new SolrQuery("*:*");
        //设置查询所有
        if(!searchMap.isEmpty()&& StringUtils.isNotEmpty(searchMap.get("keywords").toString())) {
            //默认查询条件
            solrQuery.set("df","item_keywords");
            //设置值
            solrQuery.setQuery(searchMap.get("keywords").toString());
        }
        //设置分组查询
        solrQuery.setParam(GroupParams.GROUP,true);
        //根据商品分类分组
        solrQuery.setParam(GroupParams.GROUP_FIELD,"item_category");
        solrQuery.setParam(GroupParams.GROUP_LIMIT,"100");
        QueryResponse queryResponse = solrClient.query(solrQuery, SolrRequest.METHOD.POST);
        GroupResponse groupResponse = queryResponse.getGroupResponse();
        List<GroupCommand> groupCommandList =groupResponse.getValues();
        for (GroupCommand groupCommand:groupCommandList) {
            List<Group> groups =  groupCommand.getValues();
            for (Group group:groups) {
                groupNames.add(group.getGroupValue());
            }
        }
        return groupNames;

    }

    /**
     * 设置高亮
     * @param queryResponse solr返回结果集
     */
    private  void replaceHL(QueryResponse queryResponse) {
        //高亮结果集
        Map<String, Map<String, List<String>>> hlMap = queryResponse.getHighlighting();

        //设置高亮
        SolrDocumentList solrDocuments=queryResponse.getResults();
        if(solrDocuments==null||solrDocuments.size()==0){
            return ;
        }
        if(hlMap !=null && hlMap.size()>=0) {
            for(SolrDocument solrDocument:solrDocuments) {
                String documentId = solrDocument.get("id").toString();
                Map<String,List<String>> hlFiledMap = hlMap.get(documentId);
                if(!hlFiledMap.isEmpty()) {
                    String hlFieldStr =   hlFiledMap.get("item_title").get(0);
                    solrDocument.setField("item_title",hlFieldStr);
                }
            }
        }
    }
}
