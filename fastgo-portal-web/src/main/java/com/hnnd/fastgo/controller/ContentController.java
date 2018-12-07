package com.hnnd.fastgo.controller;

import com.hnnd.fastgo.clientapi.advert.content.ContentApi;
import com.hnnd.fastgo.entity.TbContent;
import com.hnnd.fastgo.enumration.SellerGoodsEnum;
import com.hnnd.fastgo.vo.SystemVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 广告管理
 * Created by Administrator on 2018/12/7.
 */
@RestController
@RequestMapping("/content")
@Slf4j
public class ContentController {

    @Autowired
    private ContentApi contentApi;

    @RequestMapping("/findListByCategoryId/{categoryId}")
    public SystemVo<List<TbContent>> findListByCategoryId(@PathVariable("categoryId")Long categoryId) {
        if(null == categoryId) {
            log.info("通过广告类别ID 查询广告列表入参为空:{}",categoryId);
            return SystemVo.error(SellerGoodsEnum.SELLER_MRG_ADVERT_CONTENT_LIST_BY_CATEGORY_NULL_PARAM);
        }
        List<TbContent>  tbContentList= contentApi.findListByCategoryId(categoryId);
        if(null == tbContentList) {
            return SystemVo.error(SellerGoodsEnum.SELLER_MRG_ADVERT_CONTENT_LIST_BY_CATEGORY_ERROR);
        }
        return SystemVo.success(tbContentList,SellerGoodsEnum.SELLER_GOODS_SUCCESS);
    }

}
