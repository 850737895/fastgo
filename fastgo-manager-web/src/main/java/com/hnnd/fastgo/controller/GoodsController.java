package com.hnnd.fastgo.controller;

import com.hnnd.fastgo.clientapi.sellergoods.goods.GoodsApi;
import com.hnnd.fastgo.entity.TbGoods;
import com.hnnd.fastgo.enumration.GoodsAduitEnum;
import com.hnnd.fastgo.enumration.SellerGoodsEnum;
import com.hnnd.fastgo.vo.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * 商品模块
 * Created by Administrator on 2018/11/29.
 */
@RestController
@RequestMapping("/goods")
@Slf4j
public class GoodsController {


    @Autowired
    private GoodsApi goodsApi;

    @RequestMapping("/findList4Page")
    public SystemVo<PageResultVo<TbGoods>> findList4Page(@RequestBody TbGoods tbGoods,
                                                         @RequestParam(value = "pageNum",defaultValue = "1")Integer pageNum,
                                                         @RequestParam(value = "pageSize",defaultValue = "10") Integer pageSize)
    {
        PageResultVo<TbGoods> pageResultVo = goodsApi.findList4Page(tbGoods,pageNum,pageSize);
        if(pageResultVo==null) {
            return SystemVo.error(SellerGoodsEnum.SELLER_GOODS_QRY_GOODS_LIST_ERROR);
        }
        return SystemVo.success(pageResultVo,SellerGoodsEnum.SELLER_GOODS_SUCCESS);
    }

    /**
     * 商品信息审核通过或者不通过
     * @param ids 商品id列表
     * @param status 审核状态
     * @return
     */
    @RequestMapping("/aduitPass")
    public SystemVo aduitPass(@RequestParam("ids") Long[] ids,@RequestParam("status") String status) {

        if(ArrayUtils.isEmpty(ids) || StringUtils.isEmpty(status)) {
            log.error("审核商品入参为空:ids:{},status:{}",ids,status);
            return SystemVo.error(SellerGoodsEnum.SELLER_GOODSIFNO_ADUIT_PARAM_ERROR);
        }
        return goodsApi.aduitPass(ids,status);
    }


}
