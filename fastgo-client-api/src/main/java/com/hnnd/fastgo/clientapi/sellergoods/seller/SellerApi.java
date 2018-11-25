package com.hnnd.fastgo.clientapi.sellergoods.seller;

import com.hnnd.fastgo.clientapi.sellergoods.spec.SellerGoodsSpecImpl;
import com.hnnd.fastgo.entity.TbSeller;
import com.hnnd.fastgo.vo.SystemVo;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Administrator on 2018/11/23.
 */
@FeignClient(name ="fastgo-sellergoods-service",fallback = SellerApiImpl.class, path = "/seller")
public interface SellerApi {

    @RequestMapping("/loadUserByUsername/{sellerId}")
    public SystemVo<TbSeller> loadUserByUsername(@PathVariable("sellerId") String sellerId);

    @RequestMapping("/register")
    public SystemVo register(@RequestBody TbSeller tbSeller);

    @RequestMapping("/validate/{checkType}/{checkValue}")
    public SystemVo validateForm(@PathVariable("checkType") String checkType, @PathVariable("checkValue") String checkValue);
}
