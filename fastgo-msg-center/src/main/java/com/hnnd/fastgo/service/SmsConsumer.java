package com.hnnd.fastgo.service;

import com.alibaba.fastjson.JSON;
import com.aliyuncs.exceptions.ClientException;
import com.hnnd.fastgo.compent.AliSmsCodeSender;
import com.hnnd.fastgo.constant.RabbtMqConstant;
import com.hnnd.fastgo.dao.MsgLogMapper;
import com.hnnd.fastgo.entity.MsgLog;
import com.hnnd.fastgo.enumration.MsgStatusEnum;
import com.hnnd.fastgo.temp.Sms;
import com.rabbitmq.client.Channel;
import com.redisoper.IRedisService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.Date;

/**
 * 短信消费者
 * Created by 85073 on 2018/11/26.
 */
@Component
@Slf4j
public class SmsConsumer {

    @Autowired
    private IRedisService redisServiceImpl;

    @Autowired
    private AliSmsCodeSender aliSmsCodeSender;

    @Autowired
    private MsgLogMapper msgLogMapper;

    //尊敬用户【公司名称】,您在极速购平台申请的帐号【sellerId】,审核通过,已经授权登录系统
    //尊敬用户【公司名称】,您在极速购平台申请的帐号【sellerId】,审核不通过,原因【】
    @RabbitListener(queues = RabbtMqConstant.FASTGO_SMS_QUEUE)
    @RabbitHandler
    @Transactional
    public void sendMsg(Message message, Channel channel) {
        log.info("接收到message:{}",message);
        String smsMsgId = message.getHeaders().get("msgId").toString();

        try {
            if(redisServiceImpl.setnx(RabbtMqConstant.FASTOG_SMS_LOCK_KEY+smsMsgId,smsMsgId)==1) {
                //非重复消费的消息

                //1:用于消息签收
                Long deliveryTag = (Long) message.getHeaders().get(AmqpHeaders.DELIVERY_TAG);

                //2:反序列化消息对象
                String smsContextJson = message.getPayload().toString();
                Sms sms = JSON.parseObject(smsContextJson,Sms.class);

                //更新消息表
                //todo 更新消息表
                MsgLog msgLog = new MsgLog();
                msgLog.setMsgId(smsMsgId);
                msgLog.setAckTime(new Date());
                msgLog.setMsgStatus(MsgStatusEnum.MSG_ACK.getCode());
                msgLogMapper.updateByPrimaryKey(msgLog);

                //发送短信
                aliSmsCodeSender.sender(sms);

                //签收消息
                channel.basicAck(deliveryTag,false);

            }else{
                //属于重复消费的消息
                log.warn("该消息属于重复消费的消息:{}",message);
            }
        } catch (ClientException e) {
            //短信发送失败
            log.warn("发送短信失败:{}",e);
            //去除短信重复消费,需要重新发送短信
            redisServiceImpl.expire(RabbtMqConstant.FASTOG_SMS_LOCK_KEY+smsMsgId,0);
            throw new RuntimeException("发送短信失败");
        } catch (IOException e) {
            //消息签收异常
            log.warn("消息手动签收失败:{}",e);
            throw new RuntimeException("签收消息异常");
        }
    }
}
