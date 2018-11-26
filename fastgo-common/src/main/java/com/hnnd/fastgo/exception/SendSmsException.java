package com.hnnd.fastgo.exception;

/**
 * 发送短信异常
 * Created by 85073 on 2018/9/8.
 */
public class SendSmsException extends RuntimeException {

    private Integer code;

    private String msg;

    public SendSmsException(Integer code, String msg) {
        super(msg);
        this.code = code;
        this.msg = msg;
    }
}
