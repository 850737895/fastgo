package com.hnnd;

import com.hnnd.fastgo.dao.TbItemCatMapper;
import com.hnnd.fastgo.entity.TbBrand;
import com.hnnd.fastgo.service.ISellerGoodsService;
import com.hnnd.fastgo.service.ITbItemService;
import com.redisoper.IRedisService;
import org.apache.solr.client.solrj.SolrServerException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FastgoSellergoodsApplicationTests {

	@Autowired
	private ISellerGoodsService brandServiceImpl;

	@Autowired
	private IRedisService redisServiceImpl;

	@Autowired
	private TbItemCatMapper tbItemCatMapper;

	@Autowired
	private ITbItemService tbItemService;

	@Test
	public void contextLoads() {
	}

	@Test
	public void testBrandService() {
		System.out.println(brandServiceImpl.selectAll());
	}

	@Test
	public void testSearch() {
		TbBrand tbBrand = new TbBrand();
		tbBrand.setName("海");
		tbBrand.setFirstChar("H");
		System.out.println(brandServiceImpl.search(tbBrand,1,10));

	}

	@Test
	public void testBatch() {
		List<Map<String,String>> listMap = tbItemCatMapper.initItemCatCache();

		Long bt = System.currentTimeMillis();
		redisServiceImpl.hmsetWithBatch("test",listMap);
		Long et = System.currentTimeMillis();
		System.out.println("耗时"+(et-bt));
	}





}
