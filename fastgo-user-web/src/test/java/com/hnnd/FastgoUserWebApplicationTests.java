package com.hnnd;

import com.hnnd.fastgo.service.IAddresService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FastgoUserWebApplicationTests {

	@Autowired
	private IAddresService addresServiceImpl;

	@Test
	public void contextLoads() {

		System.out.println(addresServiceImpl.initProvinceList());
	}

}

