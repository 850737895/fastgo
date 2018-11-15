package com.hnnd;

import com.hnnd.fastgo.entity.TbBrand;
import com.hnnd.fastgo.service.ISellerGoodsService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FastgoSellergoodsApplicationTests {

	@Autowired
	private ISellerGoodsService brandServiceImpl;

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



}
