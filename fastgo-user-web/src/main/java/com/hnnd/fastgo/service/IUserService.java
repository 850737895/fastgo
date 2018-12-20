package com.hnnd.fastgo.service;

import com.hnnd.fastgo.vo.SystemVo;
import com.hnnd.fastgo.vo.UserFormVo;

/**
 * 用户服务接口
 * Created by Administrator on 2018/12/20.
 */
public interface IUserService {

    /**
     * 校验表单
     * @param checkType 校验类型
     * @param checkValue 校验值
     * @return
     */
    boolean checkValidate(String checkType,String checkValue);

    /**
     * 生成短信验证码
     * @param phone 手机号码
     */
    void genSmsCode(String phone);

    /**
     * 校验对象验证码
     * @param phone 手机号码
     * @param code 短信
     * @return
     */
    boolean validateSmsCode(String phone,String code);

    /**
     * 用户注册
     * @param userFormVo 用户注册表单信息
     * @return  true|false
     */
    boolean register(UserFormVo userFormVo);
}
