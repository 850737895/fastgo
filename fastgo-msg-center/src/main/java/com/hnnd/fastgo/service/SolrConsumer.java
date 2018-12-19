package com.hnnd.fastgo.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.hnnd.fastgo.constant.RabbtMqConstant;
import com.hnnd.fastgo.dao.MsgLogMapper;
import com.hnnd.fastgo.entity.MsgLog;
import com.hnnd.fastgo.entity.TbItem;
import com.hnnd.fastgo.enumration.MsgStatusEnum;
import com.hnnd.fastgo.vo.SystemVo;
import com.rabbitmq.client.Channel;
import com.redisoper.IRedisService;
import lombok.extern.slf4j.Slf4j;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrServerException;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * 处理solr索引库消费者
 * Created by Administrator on 2018/12/18.
 */
@Component
@Slf4j
public class SolrConsumer {

    @Autowired
    private IRedisService redisServiceImpl;

    @Autowired
    private SolrClient solrClient;

    @Autowired
    private MsgLogMapper msgLogMapper;

    @RabbitListener(queues = {RabbtMqConstant.FASTGO_SOLR_QUEUE})
    public void importSolr(Message message, Channel channel) {
        log.info("接受即将导入到solr数据:{}",message);
        String msgId = message.getHeaders().get("msgId").toString();
        try {
            if(redisServiceImpl.setnx(RabbtMqConstant.FASTOG_SMS_LOCK_KEY+msgId,msgId)==1) {
                //非重复消费的消息

                //1:用于消息签收
                Long deliveryTag = (Long) message.getHeaders().get(AmqpHeaders.DELIVERY_TAG);

                String msgBody = message.getPayload().toString();

                List<TbItem> tbItemList = JSON.parseObject(msgBody,new TypeReference<List<TbItem>>(){});

                solrClient.addBeans(tbItemList);

                solrClient.commit();

                //更新消息表
                MsgLog msgLog = new MsgLog();
                msgLog.setMsgId(msgId);
                msgLog.setAckTime(new Date());
                msgLog.setMsgStatus(MsgStatusEnum.MSG_ACK.getCode());
                msgLogMapper.updateByPrimaryKey(msgLog);

                //消息签收
                channel.basicAck(deliveryTag,true);

            }else {
                log.warn("该消息属于重复消费的消息:{}",message);
            }
        } catch (Exception e){
            log.warn("处理solr导入异常:{}",e);
            //去除短信重复消费,需要重新发送短信
            redisServiceImpl.expire(RabbtMqConstant.FASTOG_SMS_LOCK_KEY+msgId,0);
            throw new RuntimeException("处理solr导入异常");
        }
    }

    @RabbitListener(queues = {RabbtMqConstant.FASTGO_DELSOLR_QUEUE})
    public void delSolr(Message message, Channel channel) {
        log.info("接受即将导入到solr数据:{}",message);
        String msgId = message.getHeaders().get("msgId").toString();
        try {
            if(redisServiceImpl.setnx(RabbtMqConstant.FASTOG_SMS_LOCK_KEY+msgId,msgId)==1) {
                //非重复消费的消息

                //1:用于消息签收
                Long deliveryTag = (Long) message.getHeaders().get(AmqpHeaders.DELIVERY_TAG);

                String msgBody = message.getPayload().toString();

                List<String> goodslists = JSON.parseObject(msgBody,new TypeReference<List<String>>(){});

                solrClient.deleteById(goodslists);

                solrClient.commit();

                //更新消息表
                MsgLog msgLog = new MsgLog();
                msgLog.setMsgId(msgId);
                msgLog.setAckTime(new Date());
                msgLog.setMsgStatus(MsgStatusEnum.MSG_ACK.getCode());
                msgLogMapper.updateByPrimaryKey(msgLog);

                //消息签收
                channel.basicAck(deliveryTag,true);

            }else {
                log.warn("该消息属于重复消费的消息:{}",message);
            }
        } catch (Exception e){
            log.warn("处理solr导入异常:{}",e);
            //去除短信重复消费,需要重新发送短信
            redisServiceImpl.expire(RabbtMqConstant.FASTOG_SMS_LOCK_KEY+msgId,0);
            throw new RuntimeException("处理solr导入异常");
        }
    }
}
