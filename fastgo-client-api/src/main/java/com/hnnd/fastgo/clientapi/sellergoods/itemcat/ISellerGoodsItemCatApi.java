package com.hnnd.fastgo.clientapi.sellergoods.itemcat;

import com.hnnd.fastgo.clientapi.sellergoods.spec.SellerGoodsSpecImpl;
import com.hnnd.fastgo.entity.TbItemCat;
import com.hnnd.fastgo.vo.ItemCatVo;
import com.hnnd.fastgo.vo.PageResultVo;
import com.hnnd.fastgo.vo.SystemVo;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Feign服务调用类
 * Created by Administrator on 2018/11/21.
 */
@FeignClient(name ="fastgo-sellergoods-service",fallback = SellerGoodsItemCatImpl.class, path = "/sellerGoods/itemCat")
public interface ISellerGoodsItemCatApi {

    @RequestMapping("/level")
    public PageResultVo<TbItemCat> qryItemCatLevel(@RequestParam(value = "pageNum",defaultValue = "1")Integer pageNum,
                                                   @RequestParam(value = "pageSize",defaultValue = "10")Integer pageSize,
                                                   @RequestParam(value = "parentId",defaultValue = "0",required = false) Integer parentId,
                                                   @RequestParam(value = "qryCondition",required = false) String qryCondition);

    @RequestMapping("/findByParentId")
    public List<TbItemCat> findByParentId(@RequestParam("parentId") Integer parentId);

    @RequestMapping("/save")
    public SystemVo save(@RequestBody ItemCatVo itemCatVo);

    @RequestMapping("/findOne/{id}")
    public ItemCatVo findOneById(@PathVariable("id") Long id);

    @RequestMapping("/modify")
    public SystemVo modify(@RequestBody ItemCatVo itemCatVo);

    @RequestMapping("/del")
    public SystemVo del(@RequestParam("ids") String[] ids);
}
