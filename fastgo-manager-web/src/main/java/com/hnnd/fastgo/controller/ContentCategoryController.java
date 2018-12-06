package com.hnnd.fastgo.controller;

import com.alibaba.fastjson.JSON;
import com.hnnd.fastgo.clientapi.advert.contentCategory.ContentCategoryApi;
import com.hnnd.fastgo.entity.TbContentCategory;
import com.hnnd.fastgo.enumration.SellerGoodsEnum;
import com.hnnd.fastgo.vo.PageResultVo;
import com.hnnd.fastgo.vo.SystemVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 广告分类管理
 * Created by Administrator on 2018/12/5.
 */
@RestController
@Slf4j
@RequestMapping("//contentCategory")
public class ContentCategoryController {

    @Autowired
    private ContentCategoryApi contentCategoryApi;

    /**
     * 条件列表查询
     * @param pageNum 当前页
     * @param pageSize 每页多少条
     * @param contentCategoryName 查询条件
     * @return  SystemVo<PageResultVo<TbContentCategory>>
     */
    @RequestMapping("/list4Page")
    public SystemVo<PageResultVo<TbContentCategory>> list(@RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum,
                                                               @RequestParam(value = "pageSize",defaultValue = "10")Integer pageSize,
                                                               @RequestParam(value = "contentCategoryName") String contentCategoryName) {
        PageResultVo<TbContentCategory> pageResultVo = contentCategoryApi.list(pageNum,pageSize,contentCategoryName);
        if(null == pageResultVo) {
            log.error("查询广告类别列表异常");
            return SystemVo.error(SellerGoodsEnum.SELLER_MRG_ADVERT_LIST_ERROR);
        }
        return SystemVo.success(pageResultVo,SellerGoodsEnum.SELLER_GOODS_SUCCESS);
    }

    @RequestMapping("/save")
    public SystemVo save(@RequestBody TbContentCategory tbContentCategory) {
        log.info("接收的入参:TbContentCategory{}", JSON.toJSONString(tbContentCategory));
        if(null ==tbContentCategory) {
            log.error("保存广告类别入参为空{}",tbContentCategory);
        }
        return contentCategoryApi.save(tbContentCategory);
    }

    @RequestMapping("/del")
    public SystemVo del(@RequestParam("ids") Long[] ids) {
        log.info("接收的入参:ids{}", ids);
        if(ArrayUtils.isEmpty(ids)) {
            log.error("删除广告类别入参为空{}",ids);
            return SystemVo.error(SellerGoodsEnum.SELLER_MRG_ADVERT_DEL_INPARAM_NULL);
        }
        return contentCategoryApi.del(ids);
    }

    @RequestMapping("/modify")
    public SystemVo modify(@RequestBody TbContentCategory tbContentCategory) {
        log.info("接受到修改的入参:{}",tbContentCategory);
        if(null == tbContentCategory) {
            return SystemVo.error(SellerGoodsEnum.SELLER_MRG_ADVERT_MODIFY_INPARAM_NULL);
        }
        return contentCategoryApi.modify(tbContentCategory);
    }

    @RequestMapping("/findOneById/{id}")
    public SystemVo<TbContentCategory> findOneById(@PathVariable("id") Long id) {
        if(null ==id) {
            log.error("传入的入参为空");
            return SystemVo.error(SellerGoodsEnum.SELLER_MRG_ADVERT_QRY_INPARAM_NULL);
        }
        TbContentCategory tbContentCategory = contentCategoryApi.findOneById(id);
        if(null == tbContentCategory) {
            return SystemVo.error(SellerGoodsEnum.SELLER_MRG_ADVERT_QRY__ERROR);
        }
        return SystemVo.success(tbContentCategory,SellerGoodsEnum.SELLER_GOODS_SUCCESS);
    }

    @RequestMapping("/findAll")
    public SystemVo<List<TbContentCategory>> findAll() {
        List<TbContentCategory> tbContentCategoryList = contentCategoryApi.findAll();
        if(null==tbContentCategoryList) {
            log.info("查询广告类别列表异常");
            return SystemVo.error(SellerGoodsEnum.SELLER_MRG_ADVERT_LIST_ERROR);
        }
       return SystemVo.success(tbContentCategoryList,SellerGoodsEnum.SELLER_GOODS_SUCCESS);
    }


}
