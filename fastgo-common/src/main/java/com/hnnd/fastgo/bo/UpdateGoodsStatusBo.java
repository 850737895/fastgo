package com.hnnd.fastgo.bo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 更新商品状态的业务对象
 * Created by Administrator on 2018/12/4.
 */
@Data
public class UpdateGoodsStatusBo implements Serializable {

    //需要变更的商品id集合
    private List<Long> goodIdList;

    //需要变动到的状态
    private String changeStatus;
    /**防止横向越权*/
    private String sellerId;
}
