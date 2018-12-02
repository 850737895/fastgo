package com.hnnd.fastgo.enumration;

import lombok.Getter;

/**
 * 商品是否删除
 * Created by 85073 on 2018/12/1.
 */
@Getter
public enum GoodsDelEnum {

    UN_DEL("0","未删除"),

    DEL("1","删除");


    private String code;

    private String desc;



    GoodsDelEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;

    }

}
