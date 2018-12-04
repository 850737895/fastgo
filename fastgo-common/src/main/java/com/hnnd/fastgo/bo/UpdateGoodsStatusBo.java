package com.hnnd.fastgo.bo;

import lombok.Data;

import java.util.List;

/**
 * Created by 85073 on 2018/12/4.
 */
@Data
public class UpdateGoodsStatusBo {

    private String sellerId;

    private List<Long> goodIdList;

    private String changeStatus;
}
