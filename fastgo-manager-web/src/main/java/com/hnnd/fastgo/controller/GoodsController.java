package com.hnnd.fastgo.controller;

import com.hnnd.fastgo.bo.SmallImageBo;
import com.hnnd.fastgo.clientapi.sellergoods.goods.GoodsApi;
import com.hnnd.fastgo.clientapi.sellergoods.spec.SellerGoodsSpecApi;
import com.hnnd.fastgo.entity.TbGoods;
import com.hnnd.fastgo.enumration.SellerGoodsEnum;
import com.hnnd.fastgo.vo.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * 商品模块
 * Created by Administrator on 2018/11/29.
 */
@RestController
@RequestMapping("/goods")
@Slf4j
public class GoodsController {

    @Value("${fdfs.thumbImage.width}")
    private Integer smallImageWeigth;
    @Value("${fdfs.thumbImage.height}")
    private Integer smallImageHeigth;

    @Autowired
    private GoodsApi goodsApi;

    @Autowired
    private SellerGoodsSpecApi sellerGoodsSpecApi;

    @RequestMapping("/findList4Page")
    public SystemVo<PageResultVo<TbGoods>> findList4Page(@RequestBody TbGoods tbGoods,
                                                         @RequestParam(value = "pageNum",defaultValue = "1")Integer pageNum,
                                                         @RequestParam(value = "pageSize",defaultValue = "10") Integer pageSize)
    {
        PageResultVo<TbGoods> pageResultVo = goodsApi.findList4Page(tbGoods,pageNum,pageSize);
        if(pageResultVo==null) {
            return SystemVo.error(SellerGoodsEnum.SELLER_GOODS_QRY_GOODS_LIST_ERROR);
        }
        return SystemVo.success(pageResultVo,SellerGoodsEnum.SELLER_GOODS_SUCCESS);
    }

    /**
     * 商品信息审核通过或者不通过
     * @param ids 商品id列表
     * @param status 审核状态
     * @return
     */
    @RequestMapping("/aduitPass")
    public SystemVo aduitPass(@RequestParam("ids") Long[] ids,@RequestParam("status") String status) {

        if(ArrayUtils.isEmpty(ids) || StringUtils.isEmpty(status)) {
            log.error("审核商品入参为空:ids:{},status:{}",ids,status);
            return SystemVo.error(SellerGoodsEnum.SELLER_GOODSIFNO_ADUIT_PARAM_ERROR);
        }
        return goodsApi.aduitPass(ids,status);
    }

    /**
     * 通过商品id 查询商品goodsVo
     * @param goodsId 商品id
     * @return  public SystemVo<GoodsVo>
     */
    @RequestMapping("/findGoodsVoById/{goodsId}")
    public SystemVo<GoodsVo> findGoodsVoById(@PathVariable("goodsId")Long goodsId) {
        if(null == goodsId) {
            log.warn("传入的商品ID{}为空",goodsId);
            return SystemVo.error(SellerGoodsEnum.SELLER_GOODS_QRY_GOODSINFO_PARAM_NULL);
        }
        //设置图片属性
        SystemVo<GoodsVo> systemVo = goodsApi.findGoodsVoById(goodsId);
        GoodsVo goodsVo = systemVo.getData();
        supplementGoodsVo(goodsVo);
        return systemVo;
    }

    /**
     * 补充goodsVo的额外信息
     * @param goodsVo
     */
    private void supplementGoodsVo(GoodsVo goodsVo) {
        //设置小图属性
        SmallImageBo smallImageBo = new SmallImageBo();
        smallImageBo.setWeight(smallImageWeigth);
        smallImageBo.setHeight(smallImageHeigth);
        goodsVo.setSmallImageBo(smallImageBo);
    }

    /**
     * 通过模版ID查询规格列表
     * @return SystemVo<List<TbSpecificationOption>>
     */
    @RequestMapping("/specOpsList/{typeTempId}")
    public SystemVo<List<SpecOpsVo>> findSpecOpsByTypeTempId(@PathVariable("typeTempId") Long typeTempId) {
        if(null==typeTempId) {
            SystemVo.error(SellerGoodsEnum.SELLER_QRY_SPECINFO_BY_TEMPID_ISNULL);
        }
        List<SpecVo> specOpsVoList = sellerGoodsSpecApi.findSpecOpsByTypeTempId(typeTempId);
        if(null == specOpsVoList) {
            return SystemVo.error(SellerGoodsEnum.SELLER_QRY_SPECINFO_BY_TEMPID_ERROR);
        }
        return SystemVo.success(specOpsVoList,SellerGoodsEnum.SELLER_GOODS_SUCCESS);
    }
}
