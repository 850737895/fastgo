package com.hnnd.fastgo.controller;

import com.hnnd.fastgo.clientapi.sellergoods.typetemplate.SellerGoodsTempApi;
import com.hnnd.fastgo.entity.TbTypeTemplate;
import com.hnnd.fastgo.enumration.SellerGoodsEnum;
import com.hnnd.fastgo.vo.PageResultVo;
import com.hnnd.fastgo.vo.SystemVo;
import com.hnnd.fastgo.vo.TemplateTypeVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @RequestMapping("/save")
    public SystemVo save(@RequestBody TemplateTypeVo templateTypeVo) {
        log.info("接收到的模版类型:{}",templateTypeVo);
        return sellerGoodsTempApi.save(templateTypeVo);
    }

    @RequestMapping("/findOne/{id}")
    public SystemVo<TbTypeTemplate> findOne(@PathVariable("id") Long id) {
        return sellerGoodsTempApi.findOne(id);
    }

    @RequestMapping("/modify")
    public SystemVo modify(@RequestBody TemplateTypeVo templateTypeVo) {
        log.info("接收到的模版类型:{}", templateTypeVo);
        if(templateTypeVo == null) {
           return  SystemVo.error(SellerGoodsEnum.SELLER_GOODS_MODIFY_INPARAM_TEMPTYPE_ERROR);
        }
        return sellerGoodsTempApi.modify(templateTypeVo);
    }

    @RequestMapping("/del")
    public SystemVo del(@RequestParam("ids") String[] ids){
        if(null ==ids || ids.length ==0) {
            return SystemVo.error(SellerGoodsEnum.SELLER_GOODS_DEL_INPARAM_TEMPTYPE_ERROR);
        }
        return sellerGoodsTempApi.del(ids);

    }
}
