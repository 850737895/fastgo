package com.hnnd;

import com.alibaba.fastjson.JSON;
import com.hnnd.fastgo.entity.TbItem;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrInputDocument;
import org.assertj.core.util.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RunWith(SpringRunner.class)
@SpringBootTest
public class FastgoPortalWebApplicationTests {


	@Autowired
	private SolrClient solrClient;


	@Test
	public void contextLoads() {

	}

	@Test
	public void testSolrSave() throws IOException, SolrServerException {
/*
		SolrInputDocument doc = new SolrInputDocument();
		doc.setField("item_title","小米8");
		doc.setField("item_price",3000.00);
		doc.setField("item_goodsid",1000L);
		doc.setField("id",1);
		doc.setField("item_goodsid",1000);
		doc.setField("item_spec_网络","4G");
		doc.setField("item_spec_内存","32");

		solrClient.add(doc);
		solrClient.commit();*/

		TbItem tbItem = new TbItem();
		tbItem.setTitle("小米8");
		tbItem.setPrice(3000.00);
		tbItem.setCategory("手机");
		tbItem.setBrand("小米");
		tbItem.setSeller("小米自营旗舰店");
		tbItem.setGoodsId(1000L);
		tbItem.setId(2l);
		Map<String,String> specMap = new HashMap<>();
		specMap.put("item_spec_网络","联通");
		specMap.put("item_spec_内存","4G");
		tbItem.setSpecMap(specMap);


		solrClient.addBean(tbItem);
		solrClient.commit();
	}

	@Test
	public void saveInBatch() throws IOException, SolrServerException {
		List<TbItem> tbItemList = Lists.newArrayList();
		for(int index=0;index<100;index++) {
			TbItem tbItem = new TbItem();
			tbItem.setTitle("小米"+index);
			tbItem.setPrice(3000.00);
			tbItem.setCategory("手机");
			tbItem.setBrand("小米");
			tbItem.setSeller("小米自营旗舰店");
			tbItem.setGoodsId(100l+index);
			tbItem.setId(1L+index);
			tbItemList.add(tbItem);

		}
		solrClient.addBeans(tbItemList);
		solrClient.commit();
	}

	@Test
	public void updateSolr() throws IOException, SolrServerException {
		TbItem tbItem = new TbItem();
		tbItem.setTitle("小米88888888888");
		tbItem.setPrice(3000.00);
		tbItem.setCategory("手机");
		tbItem.setBrand("小米");
		tbItem.setSeller("小米自营旗舰店");
		tbItem.setGoodsId(3L);
		tbItem.setId(3L);
		solrClient.addBean(tbItem);
		solrClient.commit();
	}

	@Test
	public void getById() throws IOException, SolrServerException {
	  SolrDocument document =  solrClient.getById("3");
		System.out.println(document);
	}

	@Test
	public void del() throws IOException, SolrServerException {
		solrClient.deleteByQuery("*:*");
		solrClient.commit();
	}

	@Test
	public void delById() throws IOException, SolrServerException {
		List<String> ids = Lists.newArrayList();
		ids.add("3");
		ids.add("4");
		ids.add("5");
		solrClient.deleteById(ids);
		solrClient.commit();
	}

	@Test
	public void query() throws IOException, SolrServerException {
		SolrQuery solrQuery = new SolrQuery();
		//设置每次查询多少条
		solrQuery.addFilterQuery("item_title:小米2");

		System.out.println("=============="+solrClient.query(solrQuery));
	}

	@Test
	public void keyWordSearch() throws IOException, SolrServerException {
		SolrQuery solrQuery = new SolrQuery("*:*");
		solrQuery.set("df","item_keywords");
		solrQuery.setQuery("电视");
		QueryResponse queryResponse = solrClient.query(solrQuery);
		System.out.println("======================="+ queryResponse.getResults().get(0));
		TbItem tbItem =JSON.parseObject(JSON.toJSONString(queryResponse.getResults().get(0)),TbItem.class);
		System.out.println(tbItem);
	}


}
