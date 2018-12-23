package com.hnnd.fastgo.util;

import org.springframework.util.StringUtils;

import java.util.regex.Pattern;

/**
 * 校验工具类
 * Created by 85073 on 2018/3/30.
 */
public class ValidatorUtils {
    /**
     *匹配手机正则
     */
    public static final String REGEX_MOBILE = "^((17[0-9])|(14[0-9])|(13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$";


    /**
     * 校验是否为手机号码
     * @param mobile 手机号码
     * @return true|false
     */
    public static boolean checkMobileFormat(String mobile){
        boolean isMobile = true;
        if(StringUtils.isEmpty(mobile)) {
            return isMobile;
        }else {
            isMobile = Pattern.matches(REGEX_MOBILE, mobile);
        }
        return isMobile;
    }

    public static void main(String[] args) {
        System.out.println(checkMobileFormat("17373965471"));
    }
}
