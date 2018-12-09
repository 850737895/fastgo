package com.hnnd.fastgo.config;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.solr.core.SolrTemplate;

/**
 * solr配置类
 * Created by 85073 on 2018/12/8.
 */
@Configuration
public class SolrConfig {

    @Autowired
    private SolrClient solrClient;

/*   @Bean
    public SolrTemplate solrTemplate() {
       //创建连接
       SolrServer solrServer = new HttpSolrServer("http://localhost:8080/solr");
        SolrTemplate solrTemplate = new SolrTemplate(solrClient);
        return solrTemplate;
    }*/
}
