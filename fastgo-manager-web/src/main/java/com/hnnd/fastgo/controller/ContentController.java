package com.hnnd.fastgo.controller;

import com.hnnd.fastgo.clientapi.advert.content.ContentApi;
import com.hnnd.fastgo.entity.TbContent;
import com.hnnd.fastgo.enumration.SellerGoodsEnum;
import com.hnnd.fastgo.vo.PageResultVo;
import com.hnnd.fastgo.vo.SystemVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
}
