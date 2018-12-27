package com.hnnd.fastgo.constant;

/**
 * 系统常量类
 * Created by Administrator on 2018/12/20.
 */
public class SysConst {

    /**密码加盐*/
    public static final String PASSWORD_SALT = "geelysdafaqj23ou89ZXcj@#$@#$#@KJdjklj;D../dSF.,";
    /**Spring security 安全框架匿名登录用户名*/
    public static final String ANONYMOUS_USER = "anonymousUser";
    /**存储在cookie中的购物车的名称*/
    public static final String CARTLIST_STORE_IN_COOKIE = "cartlist";
    /**cookie的编码方式*/
    public static final String ENCODE_CHARSET = "UTF-8";

    public static final Integer CARTLIST_IN_COOKIE_EXIRE_TIME = 3600*24*2;

    public static final String PROVINCES_LIST="province_list";

    public static final String PROVINCE_MAPPING_CITY="prov_mapping_city";

    public static final String CITY_MAPPING_AREA="city_mapping_area";

    public static final String FAST_GO_ADDRESS_LOCK="fastgo:address_lock";

    //是默认的收货地址
    public static final String IS_DEFAULT_ADDRESS="1";


}
