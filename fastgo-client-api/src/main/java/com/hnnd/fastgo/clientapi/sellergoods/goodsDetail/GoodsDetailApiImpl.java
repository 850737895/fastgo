package com.hnnd.fastgo.clientapi.sellergoods.goodsDetail;

import com.hnnd.fastgo.enumration.SellerGoodsEnum;
import com.hnnd.fastgo.vo.SystemVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * 商品详情降级服务
 * Created by Administrator on 2018/12/17.
 */
@Component
@Slf4j
public  class GoodsDetailApiImpl implements GoodsDetialApi {
    @Override
    public SystemVo generatorHtmlByGoodsId(Long goodsId) {
        log.error("根据商品id 生成html静态页面异常");
        return SystemVo.error(SellerGoodsEnum.GEN_GOODSDETAIL_ERROR);
    }
}
