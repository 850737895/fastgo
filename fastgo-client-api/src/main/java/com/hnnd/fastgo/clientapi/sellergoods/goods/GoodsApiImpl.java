package com.hnnd.fastgo.clientapi.sellergoods.goods;

import com.hnnd.fastgo.enumration.SellerGoodsEnum;
import com.hnnd.fastgo.vo.GoodsVo;
import com.hnnd.fastgo.vo.SystemVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * 商品模块降级服务
 * Created by 85073 on 2018/12/2.
 */
@Component
@Slf4j
public class GoodsApiImpl implements GoodsApi {
    @Override
    public SystemVo save(GoodsVo goodsVo) {
        log.error("保存商品信息服务异常");
        return SystemVo.error(SellerGoodsEnum.SELLER_GOODSINFO_SAVE_ERROR);
    }
}
