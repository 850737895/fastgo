package com.hnnd.fastgo.clientapi.sellergoods.goods;

import com.hnnd.fastgo.clientapi.sellergoods.file.FileApiImpl;
import com.hnnd.fastgo.vo.GoodsVo;
import com.hnnd.fastgo.vo.SystemVo;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Administrator on 2018/11/29.
 */
@FeignClient(name ="fastgo-sellergoods-service",fallback = GoodsApiImpl.class, path = "/sellerGoods/goods")
public interface GoodsApi {


    @RequestMapping("/save")
    public SystemVo save(@RequestBody GoodsVo goodsVo);
}
