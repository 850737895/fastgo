package com.hnnd.fastgo.vo;

import com.hnnd.fastgo.entity.TbOrderItem;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 购物车VO对象
 * Created by Administrator on 2018/12/25.
 */
@Data
public class CartVo implements Serializable {

    private String sellerId;

    private String sellerName;

    private List<TbOrderItem> orderItemList;
}
