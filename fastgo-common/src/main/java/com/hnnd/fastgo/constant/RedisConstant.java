package com.hnnd.fastgo.constant;

/**
 * redis涉及的常量类
 * Created by Administrator on 2018/12/6.
 */
public class RedisConstant {

    /**
     * redis缓存广告的hash key
     */
    public static final String CONTENT_KEY="fastgo:contentCategoryId:";

    public static final String CONTENT_FIELD_PREFIX="contentId:";
    /**广告ID映射广告类别id(保存时候插入,查询的时候用)*/
    public static final String CONTENTID_MAPPING_CATEGORYID = "fastgo:id_mapping_category:";

    public static final String ITEMCATE_LIST_KEY = "fastgo:itemCateList";

    public static final String TEMPLATE_KEY = "fastgo:typeTemplate";

    public static final String TEMPLATE_BRAND_KEY = "brand";

    public static final String TEMPLATE_SPEC_KEY = "spec";

    public static final String GOODS_DETAIL_PREFIX="fastgo:goodsDetail";

    public static final String GOODS_DETAILHTML_IS_GEN= "fastgo:goodsDetailHmtlIsGen";

    public static final String SMS_CODE_KEY_PREFIX = "fastgo:smscode";
    //短信验证码有效期
    public static final int SMS_CODE_EXPIRE_TIME = 300;
    //redis存储到购物车列表的key
    public static final String CARTLIST_IN_REDIS_KEY = "fastgo:cartlist:key";

    public static final String SELECT_CARTLIST_IN_REDIS_KEY = "fastgo:selectcartlist:key";

    public static final String PROVINCES_LIST_KEY = "fastgo:address";
}
