package com.hnnd.fastgo.vo;

import com.hnnd.fastgo.enumration.SellerGoodsEnum;
import lombok.Data;

import java.io.Serializable;

/**
 * 系统响应对象
 * Created by Administrator on 2018/11/14.
 */
@Data
public class SystemVo<T> implements Serializable {

    private Integer code;

    private String msg;

    private T Data;

    /**
     * 通过code,msg构造错误对象
     * @param code 错误码
     * @param msg 错误消息
     * @return  SystemVo
     */
    public  static SystemVo error(Integer code,String msg) {
        SystemVo systemVo = new SystemVo();
        systemVo.setCode(code);
        systemVo.setMsg(msg);
        return systemVo;
    }

    /**
     * 通过系统枚举来构建错误对象
     * @param systemEnum 系统枚举
     * @return SystemVo
     */
    public static SystemVo error(SellerGoodsEnum systemEnum) {
        SystemVo systemVo = new SystemVo();
        systemVo.setCode(systemEnum.getCode());
        systemVo.setMsg(systemEnum.getMsg());
        return systemVo;
    }

    /**
     * 构建成功对象
     * @param data
     * @param systemEnum
     * @return
     */
    public static SystemVo success(Object data,SellerGoodsEnum systemEnum) {
        SystemVo systemVo = new SystemVo();
        systemVo.setCode(systemEnum.getCode());
        systemVo.setMsg(systemEnum.getMsg());
        systemVo.setData(data);
        return systemVo;
    }

    /**
     * 根据code来判断是否成功
     * @return
     */
    public  boolean isSuccess() {
        if(code ==0) {
            return true;
        }else {
            return false;
        }
    }
}
