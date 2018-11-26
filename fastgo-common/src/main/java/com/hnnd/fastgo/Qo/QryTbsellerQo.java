package com.hnnd.fastgo.Qo;

import lombok.Data;

import java.io.Serializable;

/**
 * 商家信息查询对象
 * Created by 85073 on 2018/11/24.
 */
@Data
public class QryTbsellerQo implements Serializable {

    private String companyName;

    private String nickName;

    private String status;
}
