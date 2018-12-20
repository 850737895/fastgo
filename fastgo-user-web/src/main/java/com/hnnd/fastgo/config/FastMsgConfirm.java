package com.hnnd.fastgo.config;

import com.hnnd.fastgo.dao.MsgLogMapper;
import com.hnnd.fastgo.entity.MsgLog;
import com.hnnd.fastgo.enumration.MsgStatusEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 消息确认模块
 * Created by 85073 on 2018/11/26.
 */
@Component
@Slf4j
public class FastMsgConfirm implements RabbitTemplate.ConfirmCallback {

    @Autowired
    private MsgLogMapper msgLogMapper;

    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        String msgId = correlationData.getId();
        log.info("接受broker的确认消息:{}",msgId);
        //客户端成功接受到消息 需要更新消息数据表中的消息
        MsgLog msgLog = new MsgLog();
        msgLog.setMsgId(msgId);
        if(ack) {
            //更新消息表中的消息
            msgLog.setMsgStatus(MsgStatusEnum.MSG_SEND_BROKEN.getCode());
            msgLogMapper.updateByPrimaryKey(msgLog);
            //msgLogMapper.updateMsgStatus(msgId,MsgStatusEnum.MSG_SEND_BROKEN.getCode());
        }else {
            log.warn("rm 的broken 没有成功的接受到消息:{}:原因:{}",msgId,cause);
            msgLog.setMsgStatus(MsgStatusEnum.MSG_NOT_DELIVERY.getCode());
            msgLogMapper.updateByPrimaryKey(msgLog);
            //msgLogMapper.updateMsgStatus(msgId,MsgStatusEnum.MSG_NOT_DELIVERY.getCode());
        }
    }
}
