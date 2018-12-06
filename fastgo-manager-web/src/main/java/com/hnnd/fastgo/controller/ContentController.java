package com.hnnd.fastgo.controller;

import com.hnnd.fastgo.clientapi.advert.content.ContentApi;
import com.hnnd.fastgo.entity.TbContent;
import com.hnnd.fastgo.enumration.SellerGoodsEnum;
import com.hnnd.fastgo.vo.PageResultVo;
import com.hnnd.fastgo.vo.SystemVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 广告控制层
 */
@RestController
@RequestMapping("/content")
@Slf4j
public class ContentController {

    @Autowired
    private ContentApi contentApi;

    @RequestMapping("/list4Page")
    public SystemVo<PageResultVo<TbContent>> list4Page(@RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum,
                                             @RequestParam(value = "pageSize",defaultValue = "10") Integer pageSize) {

        PageResultVo<TbContent> pageResultVo = contentApi.list4Page(pageNum,pageSize);
        if(null == pageResultVo) {
            return SystemVo.error(SellerGoodsEnum.SELLER_MRG_ADVERT_CONTENT_QRY__ERROR);
        }
        return SystemVo.success(pageResultVo,SellerGoodsEnum.SELLER_GOODS_SUCCESS);
    }

    @RequestMapping("/save")
    public SystemVo save(@RequestBody TbContent content) {
        if(null==content) {
            log.error("保存广告信息入参为空:{}",content);
            return SystemVo.error(SellerGoodsEnum.SELLER_MRG_ADVERT_CONTENT_SAVE_PARAM_NULL);
        }
        return contentApi.save(content);
    }

    @RequestMapping("/modify")
    public SystemVo modify(@RequestBody TbContent tbContent) {
        if(null ==tbContent) {
            log.error("修改广告信息异常:{}",tbContent);
            return SystemVo.error(SellerGoodsEnum.SELLER_MRG_ADVERT_CONTENT_MODIFY_ERROR);
        }
        return contentApi.modify(tbContent);
    }

    @RequestMapping("/findOneById/{id}")
    public SystemVo findOneById(@PathVariable("id")Long id) {
        if(id==null) {
            log.error("根据id查询广告信息入参为空:{}",id);
            return SystemVo.error(SellerGoodsEnum.SELLER_MRG_ADVERT_CONTENT_QRY_INPARAM_NULL);
        }

        TbContent tbContent = contentApi.findOneById(id);
        if(tbContent == null) {
            return SystemVo.error(SellerGoodsEnum.SELLER_MRG_ADVERT_CONTENT_QRY_ERROR);
        }
        return SystemVo.success(tbContent,SellerGoodsEnum.SELLER_GOODS_SUCCESS);

    }

    @RequestMapping("/del")
    public SystemVo del(@RequestParam("ids")Long[] ids){
        if(ids==null||ids.length==0) {
            log.error("根据id删除广告入参为空:{}",ids);
            return SystemVo.error(SellerGoodsEnum.SELLER_MRG_ADVERT_CONTENT_QRY_INPARAM_NULL);
        }
        return contentApi.del(ids);
    }
}
