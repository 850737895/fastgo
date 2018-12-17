package com.hnnd;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

import java.io.IOException;
import java.io.StringWriter;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FastgoDetailServiceApplicationTests {

	@Autowired
	private Configuration configuration;

	@Test
	public void contextLoads() {
	}

	@Test
	public void testGenHtml() throws IOException, TemplateException {
	  Template template =	configuration.getTemplate("my.ftl");
	  StringWriter swriter = new StringWriter();
	  Map<String,Object> map = new HashMap<>();
	  map.put("name","朱伟");
	  template.process(map,swriter);
		System.out.println(swriter.toString());
	}

}

