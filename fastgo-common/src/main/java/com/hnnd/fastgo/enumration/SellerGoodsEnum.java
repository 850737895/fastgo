package com.hnnd.fastgo.enumration;

/**
 * 系统状态枚举
 * Created by Administrator on 2018/11/14.
 */

public enum SellerGoodsEnum {
    //商家商品服务系统枚举模块
    SELLER_GOODS_SUCCESS(0,"调用商家商品服务成功"),

    SELLER_GOODS_ERROR(-1,"调用商家商品服务失败"),

    SELLER_GOODS_PAGELIST_ERROR(-2,"调用商家商品分页列表服务异常"),

    SELLER_GOODS_SAVE_ERROR(-3,"保存商品品牌异常"),

    SELLER_GOODS_FINDONE_ERROR(-4,"没有查询到该品牌信息"),

    SELLER_GOODS_MODIFY_ERROR(-5,"修改品牌信息异常"),

    SELLER_GOODS_DEL_ERROR(-6,"删除品牌信息异常"),

    SELLER_GOODS_DEL_NULL_PARAM(-6,"请选择需要删除的品牌信息");

    private Integer code;

    private String msg;

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

     SellerGoodsEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }


}
