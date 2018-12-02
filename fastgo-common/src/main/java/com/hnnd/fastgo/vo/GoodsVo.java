package com.hnnd.fastgo.vo;

import com.hnnd.fastgo.bo.SmallImageBo;
import com.hnnd.fastgo.entity.TbGoods;
import com.hnnd.fastgo.entity.TbGoodsDesc;
import com.hnnd.fastgo.entity.TbItem;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 商品表单VO
 * Created by Administrator on 2018/11/29.
 */
@Data
public class GoodsVo implements Serializable{

    private TbGoods goods;

    private TbGoodsDesc goodsDesc;

    private String itemList;

    private SmallImageBo smallImageBo;
}
