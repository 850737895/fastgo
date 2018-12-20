package com.hnnd.fastgo.service.impl;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;
import com.hnnd.fastgo.constant.RabbtMqConstant;
import com.hnnd.fastgo.constant.RedisConstant;
import com.hnnd.fastgo.dao.MsgLogMapper;
import com.hnnd.fastgo.dao.TbUserMapper;
import com.hnnd.fastgo.entity.MsgLog;
import com.hnnd.fastgo.entity.TbUser;
import com.hnnd.fastgo.enumration.MsgStatusEnum;
import com.hnnd.fastgo.enumration.SmsTypeEnum;
import com.hnnd.fastgo.service.IUserService;
import com.hnnd.fastgo.temp.Sms;
import com.hnnd.fastgo.util.MD5Util;
import com.hnnd.fastgo.vo.UserFormVo;
import com.redisoper.IRedisService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Random;
import java.util.UUID;
import java.util.Map;

/**
 * 用户服务接口
 * Created by Administrator on 2018/12/20.
 */
@Service
@Slf4j
public class UserServiceImpl implements IUserService {

    public static final Integer VALIDATE_CODE_LEN = 6;

    @Autowired
    private TbUserMapper tbUserMapper;

    @Autowired
    private IRedisService redisServiceImpl;

    @Autowired
    private MsgLogMapper msgLogMapper;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Override
    public boolean checkValidate(String checkType, String checkValue) {
        if("username".equals(checkType)&&tbUserMapper.checkUserName(checkValue)==0) {//校验用户名
            return true;
        }else if("phone".equals(checkType)&&tbUserMapper.checkUserName(checkValue)==0){
            return true;
        }
        return false;
    }

    @Override
    public void genSmsCode(String phone) {

        //生成6位随机数 验证码
        String randNum = randNumStr(VALIDATE_CODE_LEN);
        //把验证码保存进缓存
        redisServiceImpl.setex(RedisConstant.SMS_CODE_KEY_PREFIX+":"+phone,RedisConstant.SMS_CODE_EXPIRE_TIME,randNum);

        //发送到消息队列
        Sms sms = new Sms();
        sms.setReceiver(phone);
        sms.setSmsType(SmsTypeEnum.SMS_CODE_TEMPLATE.getCode());
        Map<String,Object> textMap = Maps.newHashMap();
        textMap.put("code",randNum);
        sms.setTextMap(textMap);

        MsgLog msgLog = builderMsgLog(sms);
        msgLogMapper.insert(msgLog);

        Map<String,Object> header = Maps.newHashMap();
        header.put("msgId",msgLog.getMsgId());
        MessageHeaders mhs = new MessageHeaders(header);
        Message msg = MessageBuilder.createMessage(JSON.toJSONString(sms), mhs);
        CorrelationData correlationData = new CorrelationData(msgLog.getMsgId());
        rabbitTemplate.convertAndSend(RabbtMqConstant.FASTGO_BIZ_EXCHANGE, RabbtMqConstant.FASTGO_SMS_KEY, msg, correlationData);

    }

    @Override
    public boolean validateSmsCode(String phone, String code) {
        String codeInCache = redisServiceImpl.get(RedisConstant.SMS_CODE_KEY_PREFIX+":"+phone);
        if(StringUtils.isEmpty(codeInCache)) {
            log.warn("短信验证码已失效");
            throw new  RuntimeException("短信验证码已失效");
        }
        if(!StringUtils.equals(code,codeInCache)) {
            log.warn("验证码错误");
            throw new RuntimeException("验证码错误");
        }
        return true;
    }

    @Override
    public boolean register(UserFormVo userFormVo) {
        //第一步:校验验证码
        if(validateSmsCode(userFormVo.getPhone(),userFormVo.getCode())) {
            //封装TbUser对象
            TbUser tbUser = new TbUser();
            tbUser.setUsername(userFormVo.getUsername());
            tbUser.setPassword(MD5Util.MD5EncodeUtf8(userFormVo.getPassword()));
            tbUser.setPhone(userFormVo.getPhone());
            tbUser.setCreated(new Date());
            tbUser.setUpdated(new Date());
            tbUserMapper.insert(tbUser);
        }

        return true;
    }

    private MsgLog builderMsgLog(Sms sms) {
        MsgLog msgLog = new MsgLog();
        String msgId = UUID.randomUUID().toString();
        msgLog.setMsgId(msgId);
        msgLog.setMsgStatus(MsgStatusEnum.MSG_SENDING.getCode());
        msgLog.setDestination(RabbtMqConstant.FASTGO_BIZ_EXCHANGE);
        msgLog.setRoutingKey(RabbtMqConstant.FASTGO_SMS_KEY);
        msgLog.setSendTime(new Date());
        msgLog.setMsgText(JSON.toJSONString(sms));
        msgLog.setCurrentRetryCount(RabbtMqConstant.INIT_RETRY_COUNT);
        msgLog.setMaxRetryCount(RabbtMqConstant.MAX_RETRY_COUNT);
        return msgLog;
    }

    /**
     * 生成指定位数的随机验证码
     * @param len 长度
     * @return String
     */
    private static String randNumStr(Integer len) {
        StringBuffer randNum = new StringBuffer();
        Random random = new Random();
        for(int index=0;index<len;index++) {
            randNum.append(random.nextInt(10));
        }
        return randNum.toString();
    }
}
