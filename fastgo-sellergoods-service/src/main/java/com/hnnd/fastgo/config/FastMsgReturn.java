package com.hnnd.fastgo.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

/**
 * 处理不可达的消息
 * Created by 85073 on 2018/11/26.
 */
@Component
@Slf4j
public class FastMsgReturn implements RabbitTemplate.ReturnCallback {
    @Override
    public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
        log.warn("不可达消息:{},replayCode:{},replyText:{},exchange:{},routingKey:{}",message,replyCode,replyText,exchange,routingKey);
        //todo 处理不可达的消息
    }
}
