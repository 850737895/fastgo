package com.hnnd.fastgo.enumration;

import lombok.Getter;

/**
 * 商家登录账户枚举
 * Created by Administrator on 2018/11/23.
 */
@Getter
public enum SellerAccoutStatusEnum {

    SELLER_ACCOUNT_ANADUIT("0","商家用户账号没审核"),

    SELLER_ACCOUNT_PASS_ADUIT("1","商家用户账号审核通过"),

    SELLER_ACCOUNT_UNPASS_ADUIT("2","商家用户账号审核不通过"),

    SELLER_ACCOUNT_CLOSE("3","商家用户账号关闭");

    private String code;

    private String desc;

    SellerAccoutStatusEnum(String code,String desc) {
        this.code = code;
        this.desc = desc;
    }

}
