package com.hnnd.fastgo.enumration;

/**
 * 系统状态枚举
 * Created by Administrator on 2018/11/14.
 */

public enum SellerGoodsEnum {
    //商家商品服务系统枚举模块
    SELLER_GOODS_SUCCESS(0,"调用服务成功"),

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

    SELLER_GOODS_DEL_INPARAM_TEMPTYPE_ERROR(-20,"批量删除模版信息入参为空"),

    SELLER_GOODS_QRY_ITEMCAT_ERROR(-21,"查询商品类目服务异常"),

    SELLER_GOODS_INIT_TEMPTYPELIST_ERROR(-22,"初始化商品类目下拉列表异常"),

    SELLER_GOODS_SAVE_ITEMCAT_INPARAM_ERROR(-23,"保存商品类目入参为空"),

    SELLER_GOODS_SAVE_ITEMCAT_ERROR(-24,"保存商品类目服务异常"),

    SELLER_GOODS_MODIFY_ITEMCAT_ERROR(-25,"更新商品类目服务异常"),

    SELLER_GOODS_DEL_ITEMCAT_ERROR(-26,"批量删除商品类目服务异常"),

    SELLER_GOODS_IMG_URL_NULL(-26,"删除的图片地址为null"),

    //==============================商家异常信息操作===========================
    SELLER_OPER_IN_PARAM_NULL(-30,"商家用户操作入参为null"),

    SELLER_OPER_GET_ERROR(-31,"根据商家用户ID查询商户信息异常"),

    SELLER_OPER_SAVE_ERROR(-32,"保存商家用户信息异常"),

    SELLER_CHECK_FORM_ERROR(-33,"表单校验服务异常"),

    SELLER_CHECK_FORM_EIXST(-34,"表单校验已存在"),

    SELLER_QRY_LIST_ERROR(-35,"查询商家用户信息列表异常"),
    SELLER_QRY_SELLER_DETAIL_ERROR(-36,"根据商家用户ID查询商家异常"),

    SELLER_MODIFY_SELLER_ERROR(-37,"审核商家用户ID查询商家异常"),

    SELLER_QRY_SPECINFO_BY_TEMPID_ERROR(-38,"通过商品模板ID查询商品规格信息异常"),

    SELLER_QRY_SPECINFO_BY_TEMPID_ISNULL(-39,"模板ID为null"),

    //================================文件操作模块===============================

    SELLER_GOODSINFO_SAVE_ERROR(-40,"保存商品信息服务异常"),

    SELLER_GOODS_SAVE_INPARAM_NULL(-41,"保存商品信息入參为空"),

    SELLER_GOODS_QRY_GOODS_LIST_ERROR(-42,"查询商品列表服务异常"),

    SELLER_GOODS_QRY_GOODSINFO_PARAM_NULL(-43,"传入的商品id为null"),

    SELLER_GOODS_QRY_GOODSINFO_ERROR(-44,"根据商品ID查询商品信息异常"),

    SELLER_GOODS_UPDATE_GOODSINFO_ERROR(-45,"根据商品ID更新商品信息异常"),

    SELLER_GOODS_APPLYADUIT_GOODSIDS_NULL(-46,"提交审核的商品列表入参为空"),

    SELLER_GOODS_APPLYADUIT_ERROR(-47,"提交审核的商品列表审核异常"),

    SELLER_GOODSIFNO_DEL_INPARAM_NULL(-48,"删除商品传入商品ID列表入参为空"),

    SELLER_GOODSIFNO_DEL_ERROR(-49,"删除商品传入商品ID列表异常"),
    SELLER_GOODSIFNO_ADUIT_PARAM_ERROR(-50,"审核商品入参为空"),
    SELLER_GOODSIFNO_ADUIT__ERROR(-51,"审核商品异常"),
    SELLER_GOODS_UP_OR_DOWN_MARKET_IN_PARAM(-52,"商品上下架入参为空"),
    SELLER_GGOODS_UP_OR_DOWN_MARKET_ERROR(-53,"商品上下架异常"),

    SELLER_MRG_ADVERT_LIST_ERROR(-54,"查询广告类别列表异常"),

    SELLER_MRG_ADVERT_SAVE_ERROR(-55,"查询广告类别列表异常"),

    SELLER_MRG_ADVERT_DEL_ERROR(-56,"删除广告类别列表异常"),

    SELLER_MRG_ADVERT_DEL_INPARAM_NULL(-57,"删除广告类别入参异常"),

    SELLER_MRG_ADVERT_MODIFY_INPARAM_NULL(-57,"更新广告类别入参异常"),

    SELLER_MRG_ADVERT_MODIFY_ERROR(-58,"更新广告类别异常"),

    SELLER_MRG_ADVERT_QRY_INPARAM_NULL(-59,"查询广告类别入参为空"),

    SELLER_MRG_ADVERT_QRY__ERROR(-60,"查询广告类别异常"),

    SELLER_MRG_ADVERT_CONTENT_QRY__ERROR(-60,"查询广告列表异常"),

    SELLER_MRG_ADVERT_CONTENT_SAVE_PARAM_NULL(-61,"保存广告信息入参为空"),

    SELLER_MRG_ADVERT_CONTENT_SAVE_ERROR(-62,"保存广告信息异常"),

    SELLER_MRG_ADVERT_CONTENT_QRY_INPARAM_NULL(-63,"根据ID查询广告信息入参为空"),

    SELLER_MRG_ADVERT_CONTENT_QRY_ERROR(-64,"根据ID查询广告信息异常"),

    SELLER_MRG_ADVERT_CONTENT_MODIFY_INPARAM_NULL(-65,"修改广告信息入参为空"),

    SELLER_MRG_ADVERT_CONTENT_MODIFY_ERROR(-66,"修改广告信息异常"),

    SELLER_MRG_ADVERT_CONTENT_DEL_INPARAM_NULL(-67,"根据ID删除广告信息入参为空"),
    SELLER_MRG_ADVERT_CONTENT_DEL_ERROR(-68,"根据ID删除广告信息异常"),

    SELLER_MRG_ADVERT_CONTENT_LIST_BY_CATEGORY_ERROR(-69,"根据广告类别ID查询广告信息异常"),
    SELLER_MRG_ADVERT_CONTENT_LIST_BY_CATEGORY_NULL_PARAM(-70,"根据广告类别ID查询广告信息入参为空"),

    SOLR_INIT_ERROR(-71,"加载数据到solr服务异常"),

    KEYWORKDS_SEARCH_ERROR(-72,"执行搜索服务异常"),

    REMOTE_INVOKE_SKULIST_ERROR(-73,"调用商家商品服务获取sku列表异常"),

    IMPORT_SKULIST_ERROR(-74,"把sku列表导入solr库异常"),

    IMPORT_ADUIT_PASS_SKU_ERROR(-75,"商品详情sku列表导入异常"),

    DEL_SKU_4_SOLR_ERROR(-76,"根据skuid从索引库中删除数据异常"),

    ADD_SOLR_SKULIST_IS_EMPTY(-77,"数据导入到solr库中入参为空"),

    DEL_SOLR_SKULIST_IS_EMPTY(-78,"数据导入到solr库中入参为空"),

    GEN_GOODSDETAIL_INPARAM_NULL(-79,"生成商品详情入参为空"),

    GEN_GOODSDETAIL_ERROR(-80,"生成商品详情页异常"),

    DEL_GOODSDETAIL_INPARAM_IS_NULL(-81,"根据商品ID删除商品详情页入参为空"),

    DEL_GOODSDETAIL_INPARAM_ERROR(-82,"根据商品ID删除商品详情页异常"),

    USER_REGISTER_VALIDATE_INPARAM_NULL(-83,"用户注册校验入参为空"),

    USER_REGISTER_VALIDATE_ERROR(-84,"用户注册校验异常"),

    SEND_SMS_CODE_MOBILE_ISNUMM(-85,"发送短信验证码为空"),

    GEN_SMS_CODE_ERROR(-86,"生成短信验证码异常"),

    VALIDATE_SMS_CODE_ERROR(-87,"验证短信验证码异常"),


    DEL_IMG_ERROR(-70,"删除文件信息异常");

















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
