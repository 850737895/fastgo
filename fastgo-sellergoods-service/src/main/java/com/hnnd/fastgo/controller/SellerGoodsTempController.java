package com.hnnd.fastgo.controller;

import com.google.common.collect.Lists;
import com.hnnd.fastgo.clientapi.sellergoods.typetemplate.SellerGoodsTempApi;
import com.hnnd.fastgo.entity.TbTypeTemplate;
import com.hnnd.fastgo.enumration.SellerGoodsEnum;
import com.hnnd.fastgo.service.ISellerGoodsTempService;
import com.hnnd.fastgo.vo.PageResultVo;
import com.hnnd.fastgo.vo.SystemVo;
import com.hnnd.fastgo.vo.TemplateTypeVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 商品模版管理
 * Created by Administrator on 2018/11/20.
 */
@Slf4j
@RestController
@RequestMapping("/sellerGoods/temp")
public class SellerGoodsTempController implements SellerGoodsTempApi {

    @Autowired
    private ISellerGoodsTempService sellerGoodsTempServiceImpl;

    /**
     * 类型模版列表
     * @return SystemVo<PageResultVo<TbTypeTemplate>>
     */
    @RequestMapping("/list")
    public PageResultVo<TbTypeTemplate> list(@RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum,
                                                       @RequestParam(value = "pageSize",defaultValue = "10")Integer pageSize,
                                                       @RequestParam(value = "qryCondition",required = false) String qryCondition) {
        return sellerGoodsTempServiceImpl.list4Page(pageNum,pageSize,qryCondition);
    }


    /**
     * 保存商品模版
     * @param templateTypeVo 商品模版vo
     * @return SystemVo
     */
    @RequestMapping("/save")
    public SystemVo save(@RequestBody TemplateTypeVo templateTypeVo) {
        try {
            sellerGoodsTempServiceImpl.save(templateTypeVo);
            return SystemVo.success(SellerGoodsEnum.SELLER_GOODS_SUCCESS);
        } catch (Exception e) {
            log.error("保存商品模版信息异常:{},异常信息为",templateTypeVo,e);
            return SystemVo.error(SellerGoodsEnum.SELLER_GOODS_SAVE_TEMPTYPE_ERROR);
        }
    }

    @RequestMapping("/findOne/{id}")
    public SystemVo<TbTypeTemplate> findOne(@PathVariable("id") Long id){
        try {
            return SystemVo.success(sellerGoodsTempServiceImpl.findOne(id),SellerGoodsEnum.SELLER_GOODS_SUCCESS);
        } catch (Exception e) {
            log.error("根据模版ID{}查询模版信息异常",id);
            return SystemVo.error(SellerGoodsEnum.SELLER_GOODS_FINDONE_TEMPTYPE_ERROR);
        }
    }

    @RequestMapping("/modify")
    public SystemVo modify(@RequestBody TemplateTypeVo templateTypeVo) {
        log.info("接收到的模版类型:{}", templateTypeVo);
        try {
            TbTypeTemplate tbTypeTemplate = new TbTypeTemplate();
            BeanUtils.copyProperties(templateTypeVo,tbTypeTemplate);
            sellerGoodsTempServiceImpl.modify(tbTypeTemplate);
            return SystemVo.success(SellerGoodsEnum.SELLER_GOODS_SUCCESS);
        } catch (BeansException e) {
            log.error("根据模版ID{}更新模版信息:{}异常:{}",templateTypeVo.getId(),templateTypeVo,e);
            return SystemVo.error(SellerGoodsEnum.SELLER_GOODS_MODIFY_TEMPTYPE_ERROR);
        }
    }

    @RequestMapping("/del")
    public SystemVo del(@RequestParam("ids") String[] ids){
        try {
            List<Long> idList = Lists.newArrayList();
            for (String id:ids) {
                idList.add(Long.valueOf(id));
            }
            sellerGoodsTempServiceImpl.del(idList);
            return SystemVo.success(SellerGoodsEnum.SELLER_GOODS_SUCCESS);
        } catch (NumberFormatException e) {
            return SystemVo.error(SellerGoodsEnum.SELLER_GOODS_DEL_TEMPTYPE_ERROR);

        }
    }

    @RequestMapping("/initTempTypeList")
    public List<Map<String,Object>> initTempTypeList() {
        return sellerGoodsTempServiceImpl.initTempTypeList();
    }
}
