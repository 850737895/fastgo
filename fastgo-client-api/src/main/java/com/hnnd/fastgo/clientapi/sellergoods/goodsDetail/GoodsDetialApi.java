package com.hnnd.fastgo.clientapi.sellergoods.goodsDetail;

import com.hnnd.fastgo.clientapi.sellergoods.goods.GoodsApiImpl;
import com.hnnd.fastgo.vo.SystemVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 生成Html服务
 * Created by Administrator on 2018/12/17.
 */
@FeignClient(name ="fastgo-detail-service",fallback = GoodsDetailApiImpl.class, path = "/remote/goodsDetail")
public interface GoodsDetialApi {

    @RequestMapping("/generatorHtmlByGoodsId/{goodsId}")
    public SystemVo generatorHtmlByGoodsId(@PathVariable("goodsId") Long goodsId);
}
