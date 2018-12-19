package com.hnnd.fastgo.config;

import com.hnnd.fastgo.constant.RabbtMqConstant;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 消息中心
 * Created by 85073 on 2018/11/26.
 */
@Configuration
public class RabbittmqBeanConfig {


    /**
     * 交换机
     * @return TopicExchange
     */
    @Bean
    public TopicExchange fastgoTopicExchange() {
        return new TopicExchange(RabbtMqConstant.FASTGO_BIZ_EXCHANGE,true,false);
    }

    /**
     * 短信队列
     * @return Queue
     */
    @Bean
    public Queue fastogoSmsQueue() {
        return new Queue(RabbtMqConstant.FASTGO_SMS_QUEUE,true,false,false);
    }

    /**
     * 处理solr的队列
     * @return
     */
    @Bean
    public Queue fastgoSolrQueue() {
        return new Queue(RabbtMqConstant.FASTGO_SOLR_QUEUE,true,false,false);
    }

    /**
     * 异步异常solr索引库的队列
     * @return
     */
    @Bean
    public Queue fastgoRmSolrQueue() {
        return new Queue(RabbtMqConstant.FASTGO_DELSOLR_QUEUE,true,false,false);
    }

    /**
     * 短信队列绑定到交换机上
     * @return
     */
    @Bean
    public Binding smsQueueBinding() {
        return BindingBuilder.bind(fastogoSmsQueue()).to(fastgoTopicExchange()).with(RabbtMqConstant.FASTGO_SMS_KEY);
    }

    @Bean
    public Binding solrQueueBinding(){
        return BindingBuilder.bind(fastgoSolrQueue()).to(fastgoTopicExchange()).with(RabbtMqConstant.FASTGO_SOLR_KEY);
    }
    @Bean
    public Binding delSolrQueueBinding() {
        return BindingBuilder.bind(fastgoRmSolrQueue()).to(fastgoTopicExchange()).with(RabbtMqConstant.FASTGO_DELSOLR_KEY);
    }



}
