package com.hnnd.fastgo.enumration;

import lombok.Getter;

/**
 * 商品审核状态枚举
 * Created by 85073 on 2018/12/1.
 */
@Getter
public enum GoodsStartSpecEnum {

    UN_STARTER("0","商品不启用规格"),

    STARTER("1","商品启用规格");


    private String code;

    private String desc;



    GoodsStartSpecEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;

    }

}
