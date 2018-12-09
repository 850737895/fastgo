package com.hnnd.fastgo.search.service.impl;

import com.alibaba.fastjson.JSON;
import com.hnnd.fastgo.search.service.ISearchService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.Key;
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

    @Override
    public Map searchList(Map<String, Object> searchMap) throws IOException, SolrServerException {
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
        //高亮结果集
        Map<String, Map<String, List<String>>> hlMap = queryResponse.getHighlighting();

        //替换成高亮
        SolrDocumentList solrDocuments=queryResponse.getResults();
        if(solrDocuments==null||solrDocuments.size()==0){
            return null;
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

        resultMap.put("rows",queryResponse.getResults());
        return resultMap;
    }

    public static void main(String[] args) {
        String test= "responseHeader={status=0,QTime=2,params={q=手机,df=item_keywords,hl=true,hl.simple.post=</em>,hl.fl=item_title,wt=javabin,version=2,hl.simple.pre=<em style = 'color:red'>}},response={numFound=731,start=0,docs=[SolrDocument{id=1231490, item_title=小米4 白色 联通3G手机, item_price=1999.0, item_image=http://img14.360buyimg.com/n1/s450x450_jfs/t4135/297/388153057/335769/45cf4be5/58b3fa6eN2602572e.jpg, item_goodsid=1, item_category=手机, item_brand=小米, item_seller=小米, item_spec_网络=联通3G, item_spec_机身内存=16G, _version_=1619303407749693440}, SolrDocument{id=1295341, item_title=OPPO 1100 白色 联通4G手机, item_price=1099.0, item_image=http://img12.360buyimg.com/n1/s450x450_jfs/t3406/78/398725171/104821/42382f9c/5808a510N9f0ce731.jpg, item_goodsid=1, item_category=手机, item_brand=OPPO, item_seller=OPPO, item_spec_网络=联通4G, item_spec_机身内存=16G, _version_=1619303407836725250}, SolrDocument{id=830972, item_title=飞利浦 老人手机 (X2560) 深情蓝 移动联通2G手机 双卡双待, item_price=489.0, item_image=http://img11.360buyimg.com/n1/s450x450_jfs/t3115/243/2876210567/110536/f736e20b/57e7e8dbN1d7d7f90.jpg, item_goodsid=1, item_category=手机, item_brand=飞利浦, item_seller=飞利浦, item_spec_网络=联通2G, item_spec_机身内存=16G, _version_=1619303407503278080}, SolrDocument{id=847276, item_title=飞利浦 老人手机 (X2560) 喜庆红 移动联通2G手机 双卡双待, item_price=489.0, item_image=http://img11.360buyimg.com/n1/s450x450_jfs/t3115/243/2876210567/110536/f736e20b/57e7e8dbN1d7d7f90.jpg, item_goodsid=1, item_category=手机, item_brand=飞利浦, item_seller=飞利浦, item_spec_网络=联通2G, item_spec_机身内存=16G, _version_=1619303407508520960}, SolrDocument{id=847278, item_title=飞利浦 老人手机 (X2560) 硬朗黑 移动联通2G手机 双卡双待, item_price=469.0, item_image=http://img11.360buyimg.com/n1/s450x450_jfs/t3115/243/2876210567/110536/f736e20b/57e7e8dbN1d7d7f90.jpg, item_goodsid=1, item_category=手机, item_brand=飞利浦, item_seller=飞利浦, item_spec_网络=联通2G, item_spec_机身内存=16G, _version_=1619303407513763840}, SolrDocument{id=883893, item_title=联想 MA388 老人手机 星夜黑 移动联通2G手机 双卡双待, item_price=287.0, item_image=http://img14.360buyimg.com/n1/s450x450_jfs/t3736/175/107179033/114926/c0bca93e/57ff5de0N8e231194.jpg, item_goodsid=1, item_category=手机, item_brand=联想, item_seller=联想, item_spec_网络=联通2G, item_spec_机身内存=16G, _version_=1619303407519006721}, SolrDocument{id=967021, item_title=TCL 老人手机 (i310) 暗夜黑 移动联通2G手机, item_price=199.0, item_image=http://img14.360buyimg.com/n1/s450x450_jfs/t3532/159/131329856/208385/d2e05067/58004df9Ncaaf71cc.jpg, item_goodsid=1, item_category=手机, item_brand=TCL, item_seller=TCL, item_spec_网络=联通2G, item_spec_机身内存=16G, _version_=1619303407522152451}, SolrDocument{id=1023752, item_title=飞利浦 老人手机 (W8578) 黑色 联通3G手机 双卡双待, item_price=1799.0, item_image=http://img11.360buyimg.com/n1/s450x450_jfs/t3115/243/2876210567/110536/f736e20b/57e7e8dbN1d7d7f90.jpg, item_goodsid=1, item_category=手机, item_brand=飞利浦, item_seller=飞利浦, item_spec_网络=联通3G, item_spec_机身内存=16G, _version_=1619303407555706880}, SolrDocument{id=1027857, item_title=TCL 老人手机 (i310) 纯净白 移动联通2G手机, item_price=199.0, item_image=http://img14.360buyimg.com/n1/s450x450_jfs/t3532/159/131329856/208385/d2e05067/58004df9Ncaaf71cc.jpg, item_goodsid=1, item_category=手机, item_brand=TCL, item_seller=TCL, item_spec_网络=联通2G, item_spec_机身内存=16G, _version_=1619303407556755457}, SolrDocument{id=1158860, item_title=TCL 老人手机 (i330) 暗夜黑 移动联通2G手机 双卡双待, item_price=199.0, item_image=http://img11.360buyimg.com/n1/s450x450_jfs/t2278/328/1482029120/347965/7755349f/565e97aaN5710a07d.jpg, item_goodsid=1, item_category=手机, item_brand=TCL, item_seller=TCL, item_spec_网络=联通2G, item_spec_机身内存=16G, _version_=1619303407638544390}]},highlighting={1231490={item_title=[小米4 白色 联通3G<em style = 'color:red'>手机</em>]},1295341={item_title=[OPPO 1100 白色 联通4G<em style = 'color:red'>手机</em>]},830972={item_title=[飞利浦 老人<em style = 'color:red'>手机</em> (X2560) 深情蓝 移动联通2G<em style = 'color:red'>手机</em> 双卡双待]},847276={item_title=[飞利浦 老人<em style = 'color:red'>手机</em> (X2560) 喜庆红 移动联通2G<em style = 'color:red'>手机</em> 双卡双待]},847278={item_title=[飞利浦 老人<em style = 'color:red'>手机</em> (X2560) 硬朗黑 移动联通2G<em style = 'color:red'>手机</em> 双卡双待]},883893={item_title=[联想 MA388 老人<em style = 'color:red'>手机</em> 星夜黑 移动联通2G<em style = 'color:red'>手机</em> 双卡双待]},967021={item_title=[TCL 老人<em style = 'color:red'>手机</em> (i310) 暗夜黑 移动联通2G<em style = 'color:red'>手机</em>]},1023752={item_title=[飞利浦 老人<em style = 'color:red'>手机</em> (W8578) 黑色 联通3G<em style = 'color:red'>手机</em> 双卡双待]},1027857={item_title=[TCL 老人<em style = 'color:red'>手机</em> (i310) 纯净白 移动联通2G<em style = 'color:red'>手机</em>]},1158860={item_title=[TCL 老人<em style = 'color:red'>手机</em> (i330) 暗夜黑 移动联通2G<em style = 'color:red'>手机</em> 双卡双待]}}}\n";
        System.out.println(JSON.toJSON(test));
    }
}
