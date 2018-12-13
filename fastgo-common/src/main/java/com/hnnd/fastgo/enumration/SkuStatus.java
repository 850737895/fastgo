package com.hnnd.fastgo.enumration;

import lombok.Getter;

/**
 * sku记录的状态
 * Created by Administrator on 2018/12/13.
 */
@Getter
public enum SkuStatus {

    SKU_STATUS_0("0","无效"),

    SKU_STATUS_1("1","有效");


    private String code;

    private String desc;

    SkuStatus(String code,String desc) {
        this.code = code;
        this.desc = desc;
    }
}
