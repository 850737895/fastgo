package com.hnnd.fastgo.temp;

import lombok.Data;

/**
 *  商家账户审核对象模版
 * Created by 85073 on 2018/11/26.
 */
@Data
public class SmsContext {
    //商家账号
    private String sellerId;
    //短信接受的人
    private String reciver;
    //审核通过或者不通过
    private String aduitStatus;
    //商家名称
    private String companyName;
    /**审核不通过的原因*/
    private String reason;
}
