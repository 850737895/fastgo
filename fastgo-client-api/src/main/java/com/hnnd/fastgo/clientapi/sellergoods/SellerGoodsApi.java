package com.hnnd.fastgo.clientapi.sellergoods;

import com.hnnd.fastgo.entity.TbBrand;
import com.hnnd.fastgo.vo.PageResultVo;
import com.hnnd.fastgo.vo.SystemVo;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * 商家商品管理client_api(用于暴露)
 * Created by Administrator on 2018/11/14.
 */
@FeignClient(name = "fastgo-sellergoods-service",fallback = SellerGoodsImpl.class ,path = "/sellerGoods/brand/")
public interface SellerGoodsApi {

    @GetMapping("/list")
    public SystemVo<List<TbBrand>> selectAll();

    @GetMapping("/pageList")
    public PageResultVo<TbBrand> selectAllByPage(@RequestParam(value = "pageNum",defaultValue = "1")Integer pageNum,
                                                 @RequestParam(value = "pageSzie",defaultValue = "10")Integer pageSize);
}
