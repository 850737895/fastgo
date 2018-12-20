package com.hnnd.fastgo.controller;

import com.alibaba.druid.support.json.JSONUtils;
import com.hnnd.fastgo.enumration.SellerGoodsEnum;
import com.hnnd.fastgo.service.IUserService;
import com.hnnd.fastgo.vo.SystemVo;
import com.hnnd.fastgo.vo.UserFormVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 用户控制层
 * Created by Administrator on 2018/12/20.
 */
@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    @Autowired
    private IUserService userServiceImpl;

    @RequestMapping("/register")
    public SystemVo register(@RequestBody UserFormVo userFormVo) {
        if(null ==userFormVo) {
            log.error("用户注册信息为空:{}", JSONUtils.toJSONString(userFormVo));
            return  SystemVo.error(SellerGoodsEnum.USER_REGISTER_VALIDATE_INPARAM_NULL);
        }
        return null;
    }

    /**
     * 用户注册校验
     * @param checkType 校验类型
     * @param checkValue 校验值
     * @return SystemVo校验结果
     */
    @RequestMapping("/checkValidate/{checkType}/{checkValue}")
    public SystemVo checkValidate(@PathVariable("checkType")String checkType,
                                    @PathVariable("checkValue") String checkValue) {
        if(StringUtils.isEmpty(checkType) || StringUtils.isEmpty(checkValue)) {
            log.error("校验信息入参为空:checkType:{},checkValue:{}",checkType,checkValue);
            return SystemVo.error(SellerGoodsEnum.USER_REGISTER_VALIDATE_INPARAM_NULL);
        }
        try {
            //开启校验
            if(userServiceImpl.checkValidate(checkType,checkValue)) {
                return SystemVo.success(SellerGoodsEnum.SELLER_GOODS_SUCCESS);
            }else{//校验值已经存在
                return SystemVo.error(SellerGoodsEnum.USER_REGISTER_VALIDATE_ERROR);
            }
        }catch (Exception e) {
            log.error("执行校验异常:{}",e);
            return SystemVo.error(SellerGoodsEnum.USER_REGISTER_VALIDATE_ERROR);
        }

    }

    @RequestMapping("/genSmsCode/{phone}")
    public SystemVo genSmsCode(@PathVariable("phone") String phone) {
        if(StringUtils.isEmpty(phone)) {
            log.error("手机号码为空");
            return SystemVo.error(SellerGoodsEnum.SEND_SMS_CODE_MOBILE_ISNUMM);
        }
        try {
            userServiceImpl.genSmsCode(phone);
            return SystemVo.success(SellerGoodsEnum.SELLER_GOODS_SUCCESS);
        }catch (Exception e) {
            log.error("生成验证码失败:{}",e);
            return SystemVo.error(SellerGoodsEnum.GEN_SMS_CODE_ERROR);
        }

    }

    /**
     * 校验验证码
     * @param phone 手机号码
     * @param code 验证码
     * @return
     */
    @RequestMapping("/validateSmsCode")
    public SystemVo validateSmsCode(@RequestParam("phone")String phone,@RequestParam("code") String code) {
        if(StringUtils.isEmpty(phone) || StringUtils.isEmpty(code)) {
            log.error("手机号码为空:{},或者验证码为空:{}",phone,code);
            return SystemVo.error(SellerGoodsEnum.SEND_SMS_CODE_MOBILE_ISNUMM);
        }

        try{
            userServiceImpl.validateSmsCode(phone,code);
            return SystemVo.success(SellerGoodsEnum.SELLER_GOODS_SUCCESS);
        }catch (Exception e) {
            log.error("验证短信验证码异常:{}",e);
            return SystemVo.error(SellerGoodsEnum.VALIDATE_SMS_CODE_ERROR.getCode(),e.getMessage());
        }
    }

}
