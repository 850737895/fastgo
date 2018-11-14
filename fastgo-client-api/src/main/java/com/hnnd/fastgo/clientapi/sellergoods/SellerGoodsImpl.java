package com.hnnd.fastgo.clientapi.sellergoods;

import com.hnnd.fastgo.entity.TbBrand;
import com.hnnd.fastgo.enumration.SellerGoodsEnum;
import com.hnnd.fastgo.vo.SystemVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 商家商品服务的熔断服务
 * Created by Administrator on 2018/11/14.
 */
@Component
@Slf4j
public class SellerGoodsImpl implements SellerGoodsApi {
    @Override
    public SystemVo<List<TbBrand>> selectAll() {
        log.warn("商家商品查询列表服务异常");
        return SystemVo.error(SellerGoodsEnum.SELLER_GOODS_ERROR);
    }
}
