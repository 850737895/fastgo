package com.hnnd.fastgo.clientapi.sellergoods.goods;

import com.hnnd.fastgo.bo.UpdateGoodsStatusBo;
import com.hnnd.fastgo.clientapi.sellergoods.file.FileApiImpl;
import com.hnnd.fastgo.entity.TbGoods;
import com.hnnd.fastgo.vo.GoodsVo;
import com.hnnd.fastgo.vo.PageResultVo;
import com.hnnd.fastgo.vo.SystemVo;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by Administrator on 2018/11/29.
 */
@FeignClient(name ="fastgo-sellergoods-service",fallback = GoodsApiImpl.class, path = "/sellerGoods/goods")
public interface GoodsApi {


    @RequestMapping("/save")
    public SystemVo save(@RequestBody GoodsVo goodsVo);

    @RequestMapping("/update")
    public SystemVo update(@RequestBody GoodsVo goodsVo);


    @RequestMapping("/findList4Page")
    public PageResultVo<TbGoods> findList4Page(@RequestBody TbGoods tbGoods,
                                               @RequestParam(value = "pageNum",defaultValue = "1")Integer pageNum,
                                               @RequestParam(value = "pageSize",defaultValue = "10") Integer pageSize);

    @RequestMapping("/findGoodsVoById/{goodsId}")
    public SystemVo<GoodsVo> findGoodsVoById(@PathVariable("goodsId")Long goodsId);

    @RequestMapping("/applyAduit")
    public SystemVo applyAduit(@RequestBody  UpdateGoodsStatusBo updateGoodsStatusBo);

    @RequestMapping("/del")
    public SystemVo del(@RequestBody UpdateGoodsStatusBo updateGoodsStatusBo);

    @RequestMapping("/aduitPass")
    public SystemVo aduitPass(@RequestParam("ids") Long[] ids,@RequestParam("status") String status);

    @RequestMapping("/goodsUpOrDownMarket")
    public SystemVo goodsUpOrDownMarket(@RequestBody UpdateGoodsStatusBo updateGoodsStatusBo);

}
