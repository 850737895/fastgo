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

    SELLER_GOODS_DEL_NULL_PARAM(-6,"请选择需要删除的品牌信息"),

    SELLER_GOODS_SAVE_SPEC_ERROR(-7,"保存商品规格异常"),

    SELLER_GOODS_IN_PARAM_ERROR(-8,"商品规格入参数为空"),


    SELLER_GOODS_MODIFY_SPEC_ERROR(-9,"修改商品规格异常"),


    SELLER_GOODS_FINDONE_SPEC_ERROR(-10,"根据商品规格ID查询规格信息异常"),

    SELLER_GOODS_DEL_SPEC_IN_PARAM_ERROR(-11,"删除商品规格入参为空"),

    SELLER_GOODS_DEL_SPEC_ERROR(-12,"删除商品规格异常"),

    SELLER_GOODS_TEMPLATE_ERROR(-13,"查询商品模版分页列表出错"),

    SELLER_GOODS_INIT_SELECT2_SPECLIST_ERROR(-14,"初始化商品规格下拉列表出错"),

    SELLER_GOODS_SAVE_TEMPTYPE_ERROR(-15,"保存商品模版异常"),

    SELLER_GOODS_FINDONE_TEMPTYPE_ERROR(-16,"根据模版ID查询模版信息异常"),

    SELLER_GOODS_MODIFY_TEMPTYPE_ERROR(-17,"根据模版ID更新模版信息异常"),

    SELLER_GOODS_MODIFY_INPARAM_TEMPTYPE_ERROR(-18,"更新模版服务入参为空"),

    SELLER_GOODS_DEL_TEMPTYPE_ERROR(-19,"批量删除模版信息异常"),

    SELLER_GOODS_DEL_INPARAM_TEMPTYPE_ERROR(-20,"批量删除模版信息入参为空");











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
