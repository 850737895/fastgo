package com.hnnd.fastgo.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.github.tobato.fastdfs.FdfsClientConfig;
import com.hnnd.fastgo.bo.ItemImageBo;
import com.hnnd.fastgo.bo.SmallImageBo;
import com.hnnd.fastgo.clientapi.sellergoods.goods.GoodsApi;
import com.hnnd.fastgo.clientapi.sellergoods.seller.SellerApi;
import com.hnnd.fastgo.clientapi.sellergoods.spec.SellerGoodsSpecApi;
import com.hnnd.fastgo.enumration.SellerGoodsEnum;
import com.hnnd.fastgo.vo.GoodsVo;
import com.hnnd.fastgo.vo.SpecOpsVo;
import com.hnnd.fastgo.vo.SpecVo;
import com.hnnd.fastgo.vo.SystemVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 商品模块
 * Created by Administrator on 2018/11/29.
 */
@RestController
@RequestMapping("/goods")
@Slf4j
public class GoodsController {


    @Autowired
    private GoodsApi goodsApi;

    @Autowired
    private SellerGoodsSpecApi sellerGoodsSpecApi;

    @Value("${fdfs.thumbImage.width}")
    private Integer smallImageWeigth;
    @Value("${fdfs.thumbImage.height}")
    private Integer smallImageHeigth;

    //保存商品信息
    @RequestMapping("/save")
    public SystemVo save(@RequestBody GoodsVo goodsVo) {

        log.info("接受到的入参GOODSVO:{}", JSON.toJSONString(goodsVo));
        if(null == goodsVo) {
            log.error("商品入参为空:{}",goodsVo);
            return SystemVo.error(SellerGoodsEnum.SELLER_GOODS_SAVE_INPARAM_NULL);
        }
       supplementGoodsVo(goodsVo);

        SystemVo systemVo = null;
        try {
            systemVo = goodsApi.save(goodsVo);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("SystemVo:"+systemVo);
        return systemVo;
    }

    /**
     * 补充goodsVo的额外信息
     * @param goodsVo
     */
    private void supplementGoodsVo(GoodsVo goodsVo) {
        //设置卖家
        String loginName = SecurityContextHolder.getContext().getAuthentication().getName();
        goodsVo.getGoods().setSellerId(loginName);

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
