package com.hnnd.fastgo.compent;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.stereotype.Component;

/**
 * 消息确认模块
 * Created by 85073 on 2018/11/26.
 */
@Component
@Slf4j
public class FastMsgConfirm implements RabbitTemplate.ConfirmCallback {

    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        log.info("接受broker的确认消息:{}",correlationData.getId());
        //客户端成功接受到消息 需要更新消息数据表中的消息
        if(ack) {
            //todo 更新消息表中的消息
        }else {
            log.warn("rm 的broken 没有成功的接受到消息:{}:原因:{}",correlationData.getId(),cause);
        }
    }
}
