package com.hnnd.fastgo.controller;

import com.hnnd.fastgo.clientapi.advert.contentCategory.ContentCategoryApi;
import com.hnnd.fastgo.entity.TbContentCategory;
import com.hnnd.fastgo.enumration.SellerGoodsEnum;
import com.hnnd.fastgo.service.IContentCategoryService;
import com.hnnd.fastgo.vo.PageResultVo;
import com.hnnd.fastgo.vo.SystemVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 广告服务层
 * Created by Admnistrator on 2018/12/5.
 */
@RestController
@RequestMapping("/advertService/contentCategory")
@Slf4j
public class ContentCategoryController implements ContentCategoryApi {

    @Autowired
    private IContentCategoryService contentCategoryServiceImpl;

    @RequestMapping("/list")
    public PageResultVo<TbContentCategory> list(@RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum,
                                                @RequestParam(value = "pageSize",defaultValue = "10")Integer pageSize,
                                                @RequestParam(value = "contentCategoryName") String contentCategoryName){
        try {
            return contentCategoryServiceImpl.list(pageNum,pageSize,contentCategoryName);
        } catch (Exception e) {
            log.error("查询广告分类列表异常{}",e);
            return null;
        }
    }

    @RequestMapping("/save")
    public SystemVo save(@RequestBody TbContentCategory tbContentCategory) {
        try {
            contentCategoryServiceImpl.save(tbContentCategory);
            return SystemVo.success(SellerGoodsEnum.SELLER_GOODS_SUCCESS);
        } catch (Exception e) {
            log.error("保存广告类别信息异常:{}",e);
            return SystemVo.error(SellerGoodsEnum.SELLER_MRG_ADVERT_SAVE_ERROR);
        }
    }
    @RequestMapping("/del")
    public SystemVo del(@RequestParam("ids") Long[] ids) {
        try {
            contentCategoryServiceImpl.del(ids);
            return SystemVo.success(SellerGoodsEnum.SELLER_GOODS_SUCCESS);
        } catch (Exception e) {
            log.error("批量删除广告类别异常:{}",e);
            return SystemVo.error(SellerGoodsEnum.SELLER_MRG_ADVERT_DEL_ERROR);
        }
    }

    @RequestMapping("/modify")
    public SystemVo modify(@RequestBody TbContentCategory tbContentCategory) {
        try {
            contentCategoryServiceImpl.modify(tbContentCategory);
            return SystemVo.success(SellerGoodsEnum.SELLER_GOODS_SUCCESS);
        } catch (Exception e) {
            log.error("更新商品信息异常:{}",e);
            return SystemVo.error(SellerGoodsEnum.SELLER_MRG_ADVERT_MODIFY_ERROR);
        }

    }

    @RequestMapping("/findOneById/{id}")
    public TbContentCategory findOneById(@PathVariable("id") Long id){

        try {
            return contentCategoryServiceImpl.findOneById(id);
        } catch (Exception e) {
            log.error("根据广告类别ID查询广告类别信息异常:{}",e);
            return null;
        }
    }

    @RequestMapping("/findAll")
    public List<TbContentCategory> findAll() {

        try {
            return contentCategoryServiceImpl.findAll();
        } catch (Exception e) {
            log.error("查询广告类别列表异常",e);
            return null;
        }
    }


}
