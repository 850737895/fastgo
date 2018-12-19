package com.hnnd.fastgo.clientapi.search;

import com.hnnd.fastgo.enumration.SellerGoodsEnum;
import com.hnnd.fastgo.vo.SystemVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * 商品详情页面服务
 * Created by Administrator on 2018/12/18.
 */
@Component
@Slf4j
public class GenGoodsDetailApiImpl implements GenGoodsDetailApi {
    @Override
    public SystemVo genHtml(Long goodsId) {
        log.error("根据商品id 生成html静态页面异常");
        return SystemVo.error(SellerGoodsEnum.GEN_GOODSDETAIL_ERROR);
    }

    @Override
    public SystemVo delHtmlByGoodsId(Long goodsId) {
        log.error("根据商品id 删除html静态页面异常");
        return SystemVo.error(SellerGoodsEnum.DEL_GOODSDETAIL_INPARAM_ERROR);
    }
}
