package com.hnnd.fastgo.config;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

/**
 * Created by 85073 on 2018/12/18.
 */
@Configuration
public class InitRabbitTemplate {

    @Autowired
    private FastMsgConfirm fastMsgConfirm;

    @Autowired
    private FastMsgReturn fastMsgReturn;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @PostConstruct
    public void initRabbitTemplate() {
        rabbitTemplate.setReturnCallback(fastMsgReturn);
        rabbitTemplate.setConfirmCallback(fastMsgConfirm);
    }

}
