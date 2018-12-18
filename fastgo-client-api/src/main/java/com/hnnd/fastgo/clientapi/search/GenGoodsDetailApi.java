package com.hnnd.fastgo.clientapi.search;

import com.hnnd.fastgo.clientapi.sellergoods.goodsDetail.GoodsDetailApiImpl;
import com.hnnd.fastgo.vo.SystemVo;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 生成商品详情服务 给搜索列表页面点击调用
 * Created by Administrator on 2018/12/18.
 */
@FeignClient(name ="fastgo-detail-web",fallback = GenGoodsDetailApiImpl.class, path = "/goodsDetail")
public interface GenGoodsDetailApi {

    @RequestMapping("/genHtml/{goodsId}")
    public SystemVo genHtml(@PathVariable("goodsId") Long goodsId);

}
