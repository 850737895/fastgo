package com.hnnd.fastgo.strategy;


import com.alibaba.fastjson.JSON;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.google.common.collect.Maps;
import com.hnnd.fastgo.constant.AliSmsConstants;
import com.hnnd.fastgo.temp.SmsContext;

import java.util.Map;


/**
 * 商家信息审核
 * Created by Administrator on 2018/11/27.
 */
public class SmsBizAduitStrategy extends AbstractSmsReqStrategy {

    @Override
    public void supplementExtraProp(SendSmsRequest request,SmsContext smsContext) {
        request.setTemplateCode(AliSmsConstants.ALIYUN_ADUIT_PASS_TEMPID);
        Map<String,Object> inParam = Maps.newHashMap();
        inParam.put("name",smsContext.getCompanyName());
        inParam.put("sellerId",smsContext.getSellerId());
        request.setTemplateParam(JSON.toJSON(inParam).toString());
    }

}
