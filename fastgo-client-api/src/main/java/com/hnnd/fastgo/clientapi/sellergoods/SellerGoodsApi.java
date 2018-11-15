package com.hnnd.fastgo.clientapi.sellergoods;

import com.hnnd.fastgo.entity.TbBrand;
import com.hnnd.fastgo.vo.PageResultVo;
import com.hnnd.fastgo.vo.SystemVo;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;

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

    @RequestMapping("/save")
    public SystemVo save(@RequestBody TbBrand tbBrand);

    @RequestMapping("/findOne/{id}")
    public SystemVo<TbBrand> findOneById(@PathVariable("id") Long id);

    @RequestMapping("/modify")
    public SystemVo modifyBrandById(@RequestBody TbBrand tbBrand);

    @RequestMapping("/del")
    public SystemVo delBrandById(@RequestParam("ids") String[] ids);

    @RequestMapping("/search")
    public PageResultVo<TbBrand> search(@RequestBody TbBrand tbBrand,
                           @RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum,
                           @RequestParam(value = "pageSize",defaultValue = "10") Integer pageSize);
}
