package com.hnnd.fastgo.enumration;

import lombok.Getter;

/**
 * 短信模版类型
 * Created by Administrator on 2018/12/19.
 */
@Getter
public enum  SmsTypeEnum {

    /**
     * 商家帐号审核通过模版
     */
    SELLER_ACCOUNT_ADUIT_PASS(1000,"SMS_151830137"),

    SELLER_ACCOUNT_ADUIT_UNPASS(1001,"SMS_151830322");



    private Integer code;

    private String msg;

    SmsTypeEnum(Integer code,String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static String getValueByCode(Integer code){
        for(SmsTypeEnum smsTypeEnum:SmsTypeEnum.values()){
            if(code.equals(smsTypeEnum.getCode())){
                return smsTypeEnum.getMsg();
            }
        }
        return  null;
    }

}
