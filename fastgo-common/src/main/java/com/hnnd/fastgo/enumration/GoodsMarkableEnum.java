package com.hnnd.fastgo.enumration;

import lombok.Getter;

/**
 * 商品上下架状态枚举
 * Created by 85073 on 2018/12/1.
 */
@Getter
public enum GoodsMarkableEnum {

    IS_MARK("1","商品上架"),

    NOT_MARK("0","商品下架");


    private String code;

    private String desc;



    GoodsMarkableEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;

    }

}
