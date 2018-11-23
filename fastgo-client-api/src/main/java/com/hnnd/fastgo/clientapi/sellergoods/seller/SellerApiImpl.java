package com.hnnd.fastgo.clientapi.sellergoods.seller;

import com.hnnd.fastgo.entity.TbSeller;
import com.hnnd.fastgo.enumration.SellerGoodsEnum;
import com.hnnd.fastgo.vo.SystemVo;
import org.springframework.stereotype.Component;

/**
 * Created by Administrator on 2018/11/23.
 */
@Component
public class SellerApiImpl implements SellerApi {
    @Override
    public SystemVo<TbSeller> loadUserByUsername(String sellerId) {
        return SystemVo.error(SellerGoodsEnum.SELLER_OPER_GET_ERROR);
    }

    @Override
    public SystemVo register(TbSeller tbSeller) {
        return SystemVo.error(SellerGoodsEnum.SELLER_OPER_SAVE_ERROR);
    }
}
