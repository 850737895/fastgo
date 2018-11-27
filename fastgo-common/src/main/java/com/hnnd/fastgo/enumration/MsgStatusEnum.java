package com.hnnd.fastgo.enumration;

import lombok.Getter;

/**
 * 消息状态枚举
 * Created by Administrator on 2018/11/27.
 */
@Getter
public enum MsgStatusEnum {

    MSG_SENDING(0,"发送中"),

    MSG_SEND_BROKEN(1,"短信发送到队列上"),

    MSG_ACK(2,"消息被签收"),

    MSG_NOT_DELIVERY(3,"消息不可达");

    private Integer code;

    private String desc;

    MsgStatusEnum(Integer code,String desc) {
        this.code = code;
        this.desc = desc;
    }
}
