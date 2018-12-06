package com.hnnd.fastgo.controller;

import com.hnnd.fastgo.clientapi.advert.content.ContentApi;
import com.hnnd.fastgo.entity.TbContent;
import com.hnnd.fastgo.entity.TbContentCategory;
import com.hnnd.fastgo.enumration.SellerGoodsEnum;
import com.hnnd.fastgo.service.IContentService;
import com.hnnd.fastgo.vo.PageResultVo;
import com.hnnd.fastgo.vo.SystemVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 广告内容控制层
 * Created by Administrator on 2018/12/5.
 */
@RestController
@RequestMapping("/advertService/content")
@Slf4j
public class ContentController implements ContentApi {

    @Autowired
    private IContentService contentServiceImpl;

    /**
     * 分页查询广告对象
     * @param pageNum 当前页码
     * @param pageSize 每页条数
     * @return  PageResultVo<TbContent>
     */
    @RequestMapping("/list4Page")
    public PageResultVo<TbContent> list4Page(@RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum,
                                                @RequestParam(value = "pageSize",defaultValue = "10") Integer pageSize) {

        try {
            return contentServiceImpl.list4Page(pageNum,pageSize);
        } catch (Exception e) {
            log.error("分页查询广告列表异常");
            return null;
        }
    }

    @RequestMapping("/save")
    public SystemVo save(@RequestBody TbContent content) {
        try {
            contentServiceImpl.save(content);
            return SystemVo.success(SellerGoodsEnum.SELLER_GOODS_SUCCESS);
        } catch (Exception e) {
            log.error("保存广告信息异常:{}",e);
            return SystemVo.error(SellerGoodsEnum.SELLER_MRG_ADVERT_CONTENT_SAVE_ERROR);
        }
    }

    @RequestMapping("/findListByCategoryId/{categoryId}")
    public List<TbContent> findListByCategoryId(@PathVariable("categoryId")Long categoryId) {
        try {
            return contentServiceImpl.findListByCategoryId(categoryId);
        } catch (Exception e) {
            log.error("根据广告类别ID查询广告异常");
            return null;
        }
    }

    @RequestMapping("/findOneById/{id}")
    public TbContent findOneById(@PathVariable("id")Long id) {
        try {
            return contentServiceImpl.findOneById(id);
        } catch (Exception e) {
            log.error("根据广告类别ID查询广告异常");
            return null;
        }
    }

}
