package com.hnnd.fastgo.clientapi.sellergoods.goods;

import com.hnnd.fastgo.bo.UpdateGoodsStatusBo;
import com.hnnd.fastgo.entity.TbGoods;
import com.hnnd.fastgo.enumration.SellerGoodsEnum;
import com.hnnd.fastgo.vo.GoodsVo;
import com.hnnd.fastgo.vo.PageResultVo;
import com.hnnd.fastgo.vo.SystemVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.netflix.feign.FeignClient;
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

    @Override
    public SystemVo update(GoodsVo goodsVo) {
        log.error("根据商品ID修改商品信息异常");
        return SystemVo.error(SellerGoodsEnum.SELLER_GOODS_UPDATE_GOODSINFO_ERROR);
    }

    @Override
    public PageResultVo<TbGoods> findList4Page(TbGoods tbGoods, Integer pageNum, Integer pageSize) {
        log.error("分页查询商品列表异常");
        return null;
    }

    @Override
    public SystemVo<GoodsVo> findGoodsVoById(Long goodsId) {
        log.error("通过商品ID查询商品信息异常：goodsId:{}",goodsId);
        return SystemVo.error(SellerGoodsEnum.SELLER_GOODS_QRY_GOODSINFO_ERROR);
    }

    @Override
    public SystemVo applyAduit(UpdateGoodsStatusBo updateGoodsStatusBo) {
        log.error("提交商品审核异常");
        return SystemVo.error(SellerGoodsEnum.SELLER_GOODS_APPLYADUIT_ERROR);
    }

    @Override
    public SystemVo del(UpdateGoodsStatusBo updateGoodsStatusBo) {
        log.error("根据商品ID列表删除商品信息异常:{}",updateGoodsStatusBo);
        return SystemVo.error(SellerGoodsEnum.SELLER_GOODSIFNO_DEL_ERROR);
    }

    @Override
    public SystemVo aduitPass(Long[] ids, String status) {
        log.error("审核商品状态异常");
        return SystemVo.error(SellerGoodsEnum.SELLER_GOODSIFNO_ADUIT__ERROR);
    }

    @Override
    public SystemVo goodsUpOrDownMarket(UpdateGoodsStatusBo updateGoodsStatusBo) {
        log.error("商品上下架操作异常:{}",updateGoodsStatusBo);
        return SystemVo.error(SellerGoodsEnum.SELLER_GGOODS_UP_OR_DOWN_MARKET_ERROR);
    }
}
