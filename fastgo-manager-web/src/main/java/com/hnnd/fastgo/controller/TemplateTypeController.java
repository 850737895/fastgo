package com.hnnd.fastgo.controller;

import com.hnnd.fastgo.clientapi.sellergoods.typetemplate.SellerGoodsTempApi;
import com.hnnd.fastgo.entity.TbTypeTemplate;
import com.hnnd.fastgo.enumration.SellerGoodsEnum;
import com.hnnd.fastgo.vo.PageResultVo;
import com.hnnd.fastgo.vo.SystemVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 类型模版管理
 * Created by Administrator on 2018/11/20.
 */
@RestController
@RequestMapping("/templateType")
@Slf4j
public class TemplateTypeController {

    @Autowired
    private SellerGoodsTempApi sellerGoodsTempApi;

    /**
     * 类型模版列表
     * @return SystemVo<PageResultVo<TbTypeTemplate>>
     */
    @RequestMapping("/list")
    public SystemVo<PageResultVo<TbTypeTemplate>> list(@RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum,
                                                       @RequestParam(value = "pageSize",defaultValue = "10")Integer pageSize,
                                                       @RequestParam(value = "qryCondition",required = false) String qryCondition) {
        PageResultVo<TbTypeTemplate> pageResultVo = sellerGoodsTempApi.list(pageNum,pageSize,qryCondition);
        //查询分页出错
        if(null == pageResultVo) {
            log.error("分页查询商品模版列表出错");
            return SystemVo.error(SellerGoodsEnum.SELLER_GOODS_TEMPLATE_ERROR);
        }
        return SystemVo.success(pageResultVo,SellerGoodsEnum.SELLER_GOODS_SUCCESS);
    }
}
