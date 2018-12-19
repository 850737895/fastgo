package com.hnnd.fastgo.service.impl;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.hnnd.fastgo.Qo.QryTbsellerQo;
import com.hnnd.fastgo.config.FastMsgConfirm;
import com.hnnd.fastgo.config.FastMsgReturn;
import com.hnnd.fastgo.constant.RabbtMqConstant;
import com.hnnd.fastgo.dao.MsgLogMapper;
import com.hnnd.fastgo.dao.TbSellerMapper;
import com.hnnd.fastgo.entity.MsgLog;
import com.hnnd.fastgo.entity.TbSeller;
import com.hnnd.fastgo.enumration.MsgStatusEnum;
import com.hnnd.fastgo.enumration.SellerAccoutStatusEnum;
import com.hnnd.fastgo.enumration.SmsTypeEnum;
import com.hnnd.fastgo.service.ISellerService;
import com.hnnd.fastgo.temp.Sms;
import com.hnnd.fastgo.vo.PageResultVo;
import org.apache.commons.lang.StringUtils;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.Map;


/**
 * 商家商户信息
 * Created by Administrator on 2018/11/23.
 */
@Service
public class SellerServiceImpl implements ISellerService {

    @Autowired
    private TbSellerMapper tbSellerMapper;

    @Autowired
    private MsgLogMapper msgLogMapper;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private FastMsgConfirm fastMsgConfirm;

    @Autowired
    private FastMsgReturn fastMsgReturn;

    @Override
    public TbSeller findOneById(String sellerId) {
        return tbSellerMapper.selectByPrimaryKey(sellerId);
    }

    @Override
    public TbSeller loadUserByUserName(String userName, String status) {
        return tbSellerMapper.loadUserByUserName(userName,status);
    }

    @Override
    public void register(TbSeller tbSeller) {
        tbSellerMapper.register(tbSeller);
    }

    @Override
    public boolean validateForm(String checkType, String checkValue) {
        boolean checkResult = false;
        //校验用户名
        if("sellerId".endsWith(checkType)) {
            if(tbSellerMapper.checkSellerId(checkValue)==0) {
                checkResult = true;
            }
        }
        if("nickName".endsWith(checkType)) {
            if(tbSellerMapper.checkNickName(checkValue)==0) {
                checkResult = true;
            }
        }
        if("linkmanMobile".endsWith(checkType)) {
            if(tbSellerMapper.checkLinkmanMobile(checkValue)==0) {
                checkResult = true;
            }
        }
        return checkResult;
    }

    @Override
    public PageResultVo<TbSeller> qryTbSellerListByPage(Integer pageNum, Integer pageSize, QryTbsellerQo qryTbsellerQo) {
        PageHelper.startPage(pageNum,pageSize);
        List<TbSeller> tbSellerList = Lists.newArrayList();
        if(null==qryTbsellerQo){
             tbSellerList = tbSellerMapper.selectAll();
        }else {
             tbSellerList = tbSellerMapper.findListByCondition(obtainByQo(qryTbsellerQo));
        }
        PageInfo<TbSeller> pageInfo = new PageInfo<>(tbSellerList);
        return new PageResultVo<>(pageInfo.getTotal(),pageInfo.getList());
    }

    @Transactional
    @Override
    public void updateAcctStatus(String sellerId, String status) {
        //检查status是否正确
        if(!validateAcctStatus(status)){
            throw new RuntimeException("对审核账户状态位非法");
        }

        //更新账户状态
        int updateRows = tbSellerMapper.updateAcctStatus(sellerId, status);
        if(updateRows==0) {
            return;
        }
        //根据sellerId查询商家账户信息
        TbSeller tbSeller = tbSellerMapper.selectByPrimaryKey(sellerId);
        if(null ==tbSeller) {
            throw new RuntimeException("根据sellerId:{}查询不到商家用户信息");
        }

        //构建发送到消息队列中的消息对象
        Sms sms = new Sms();
        sms.setReceiver(tbSeller.getLinkmanMobile());
        Map<String,Object> textMap = Maps.newHashMap();
        textMap.put("name",tbSeller.getName());
        //审核不通过
        if(SellerAccoutStatusEnum.SELLER_ACCOUNT_UNPASS_ADUIT.getCode().equals(status)) {
            textMap.put("reason","由于材料缺少");
            sms.setSmsType(SmsTypeEnum.SELLER_ACCOUNT_ADUIT_UNPASS.getCode());
        }else{
            sms.setSmsType(SmsTypeEnum.SELLER_ACCOUNT_ADUIT_PASS.getCode());
        }
        textMap.put("sellerId",sellerId);
        sms.setTextMap(textMap);

        //插入消息表
        MsgLog msgLog = builderMsgLog(sms);
        msgLogMapper.insert(msgLog);

        //发送消息
        Map<String,Object> header = Maps.newHashMap();
        header.put("msgId",msgLog.getMsgId());
        MessageHeaders mhs = new MessageHeaders(header);
        Message msg = MessageBuilder.createMessage(JSON.toJSONString(sms), mhs);
        CorrelationData correlationData = new CorrelationData(msgLog.getMsgId());
        rabbitTemplate.setConfirmCallback(fastMsgConfirm);
        rabbitTemplate.setReturnCallback(fastMsgReturn);
        rabbitTemplate.convertAndSend(RabbtMqConstant.FASTGO_BIZ_EXCHANGE, RabbtMqConstant.FASTGO_SMS_KEY, msg, correlationData);
    }

    /**
     * 根据状态来判断审核的动作为通过还是不通过
     * @return
     */
    private boolean validateAcctStatus(String status) {

        if(SellerAccoutStatusEnum.SELLER_ACCOUNT_PASS_ADUIT.getCode().equals(status)||
                SellerAccoutStatusEnum.SELLER_ACCOUNT_UNPASS_ADUIT.getCode().equals(status)||
                SellerAccoutStatusEnum.SELLER_ACCOUNT_CLOSE.equals(status))
        {
            return true;
        }
        return false;
    }

    /**
     * 构建消息日志对象
     * @param sms
     * @return
     */
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
     * 通过QO对象转为视图对象
     * @param qryTbsellerQo
     * @return
     */
    private TbSeller obtainByQo(QryTbsellerQo qryTbsellerQo) {
        TbSeller tbSeller = new TbSeller();
        if(StringUtils.isNotEmpty(qryTbsellerQo.getNickName())) {
            tbSeller.setNickName(qryTbsellerQo.getNickName());
        }
        if(StringUtils.isNotEmpty(qryTbsellerQo.getCompanyName())) {
            tbSeller.setName(qryTbsellerQo.getCompanyName());
        }
        if(StringUtils.isNotEmpty(qryTbsellerQo.getStatus())) {
            tbSeller.setStatus(qryTbsellerQo.getStatus());
        }
        return tbSeller;
    }
}
