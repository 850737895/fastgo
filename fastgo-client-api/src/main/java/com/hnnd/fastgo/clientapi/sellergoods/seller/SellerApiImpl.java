package com.hnnd.fastgo.clientapi.sellergoods.seller;

import com.hnnd.fastgo.Qo.QryTbsellerQo;
import com.hnnd.fastgo.entity.TbSeller;
import com.hnnd.fastgo.enumration.SellerGoodsEnum;
import com.hnnd.fastgo.vo.PageResultVo;
import com.hnnd.fastgo.vo.SystemVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * Created by Administrator on 2018/11/23.
 */
@Component
@Slf4j
public class SellerApiImpl implements SellerApi {
    @Override
    public SystemVo<TbSeller> loadUserByUsername(String sellerId) {
        return SystemVo.error(SellerGoodsEnum.SELLER_OPER_GET_ERROR);
    }

    @Override
    public SystemVo register(TbSeller tbSeller) {
        return SystemVo.error(SellerGoodsEnum.SELLER_OPER_SAVE_ERROR);
    }

    @Override
    public SystemVo validateForm(String checkType, String checkValue) {
        return SystemVo.error(SellerGoodsEnum.SELLER_CHECK_FORM_ERROR);
    }

    @Override
    public PageResultVo<TbSeller> qryTbSellerListByPage(Integer pageNum, Integer pageSize, QryTbsellerQo qryTbsellerQo) {
        return null;
    }

    @Override
    public SystemVo<TbSeller> findOneById(String sellerId) {
        log.error("通过商家用户信息查询商家详情异常:{}",sellerId);
        return SystemVo.error(SellerGoodsEnum.SELLER_QRY_SELLER_DETAIL_ERROR);
    }

    @Override
    public SystemVo updateAcctStatus(String sellerId, String status) {
        log.error("通过商家sellerId:{}更新商家账户状态异常:{}",sellerId,status);
        return SystemVo.error(SellerGoodsEnum.SELLER_MODIFY_SELLER_ERROR);
    }
}
