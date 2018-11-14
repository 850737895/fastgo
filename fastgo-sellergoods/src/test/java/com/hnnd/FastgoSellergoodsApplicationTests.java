package com.hnnd;

import com.hnnd.fastgo.service.IBrandService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FastgoSellergoodsApplicationTests {

	@Autowired
	private IBrandService brandServiceImpl;

	@Test
	public void contextLoads() {
	}

	@Test
	public void testBrandService() {
		System.out.println(brandServiceImpl.selectAll());
	}



}
