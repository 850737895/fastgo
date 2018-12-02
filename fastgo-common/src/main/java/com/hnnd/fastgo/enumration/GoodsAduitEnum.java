package com.hnnd.fastgo.enumration;

import lombok.Getter;

/**
 * 商品审核状态枚举
 * Created by 85073 on 2018/12/1.
 */
@Getter
public enum GoodsAduitEnum {

    WAITT_ADUIT("0","商品等待审核"),

    ADUIT_PASS("1","商品审核通过"),

    ADUIT_UNPASS("2","商品审核不通过");

    private String code;

    private String desc;



    GoodsAduitEnum(String code,String desc) {
        this.code = code;
        this.desc = desc;

    }

}
