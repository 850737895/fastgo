package com.hnnd.fastgo.controller;

import com.hnnd.fastgo.enumration.SellerGoodsEnum;
import com.hnnd.fastgo.service.IGoodsDetailService;
import com.hnnd.fastgo.vo.SystemVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**生成
 * Created by Administrator on 2018/12/18.
 */
@RestController
@RequestMapping("/goodsDetail")
@Slf4j
public class GoodsDetailGenController {

    @Autowired
    private IGoodsDetailService goodsDetailServiceImpl;

    @RequestMapping("/genHtml/{goodsId}")
    public SystemVo genHtml(@PathVariable("goodsId") Long goodsId) {
        if(goodsId==null) {
            log.error("根据商品ID 生成详情页码入参为空");
            return SystemVo.error(SellerGoodsEnum.GEN_GOODSDETAIL_INPARAM_NULL);
        }

        //生成html文件名称
        try {
            return goodsDetailServiceImpl.genHtml(goodsId);
        } catch (IOException e) {
            log.error("生成html静态模版异常:{}",e);
            return SystemVo.error(SellerGoodsEnum.GEN_GOODSDETAIL_ERROR);
        }
    }
}
