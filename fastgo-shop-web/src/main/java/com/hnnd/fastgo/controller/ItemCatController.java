package com.hnnd.fastgo.controller;

import com.hnnd.fastgo.clientapi.sellergoods.itemcat.ISellerGoodsItemCatApi;
import com.hnnd.fastgo.entity.TbItemCat;
import com.hnnd.fastgo.enumration.SellerGoodsEnum;
import com.hnnd.fastgo.vo.ItemCatVo;
import com.hnnd.fastgo.vo.PageResultVo;
import com.hnnd.fastgo.vo.SystemVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 商品类目管理
 * Created by Administrator on 2018/11/21.
 */
@RestController
@RequestMapping("/itemCat")
@Slf4j
public class ItemCatController {

    @Autowired
    private ISellerGoodsItemCatApi sellerGoodsItemCatApi;

    /**
     * 分页查询商品类目列表
     * @param pageNum 当前页
     * @param pageSize 每页多少条
     * @param parentId 父类ID
     * @return PageResultVo<TbItemCat>
     */
    @RequestMapping("/level")
    public SystemVo<PageResultVo<TbItemCat>> qryItemCatLevel(@RequestParam(value = "pageNum",defaultValue = "1")Integer pageNum,
                                                   @RequestParam(value = "pageSize",defaultValue = "10")Integer pageSize,
                                                   @RequestParam(value = "parentId",defaultValue = "0",required = false) Integer parentId,
                                                   @RequestParam(value = "qryCondition",required = false) String qryCondition)
    {
        PageResultVo<TbItemCat> pageResultVo= sellerGoodsItemCatApi.qryItemCatLevel(pageNum,pageSize,parentId,qryCondition);
        if(null == pageResultVo) {
            log.error("分页查询商品类目异常");
            return SystemVo.error(SellerGoodsEnum.SELLER_GOODS_QRY_ITEMCAT_ERROR);
        }
        return SystemVo.success(pageResultVo, SellerGoodsEnum.SELLER_GOODS_SUCCESS);
    }

    /**
     * 根据上级ID查询商品分类列表
     * @param parentId
     * @return
     */
    @RequestMapping("/findByParentId")
    public SystemVo<List<TbItemCat>> findByParentId(@RequestParam("parentId") Integer parentId){
        List<TbItemCat> tbItemCatList = sellerGoodsItemCatApi.findByParentId(parentId);
        if(null == tbItemCatList) {
            log.error("查询商品类目服务异常");
            return SystemVo.error(SellerGoodsEnum.SELLER_GOODS_QRY_ITEMCAT_ERROR);
        }
        return SystemVo.success(tbItemCatList,SellerGoodsEnum.SELLER_GOODS_SUCCESS);
    }

    @RequestMapping("/save")
    public SystemVo save(@RequestBody ItemCatVo itemCatVo,@RequestParam("parentId")Long parentId) {

        if(itemCatVo == null || parentId==null) {
            log.error("保存商品类目入参为空");
            return SystemVo.error(SellerGoodsEnum.SELLER_GOODS_ERROR.SELLER_GOODS_SAVE_ITEMCAT_INPARAM_ERROR);
        }
        log.info("接受到前端传入的itemCatVo对象:{}",itemCatVo);
        itemCatVo.setParentId(parentId);
        return sellerGoodsItemCatApi.save(itemCatVo);
    }

    @RequestMapping("/findOne/{id}")
    public SystemVo<ItemCatVo> findOneById(@PathVariable("id") Long id) {
        ItemCatVo itemCatVo = sellerGoodsItemCatApi.findOneById(id);
        if(null == itemCatVo) {
            log.error("更加商品类目ID查询信息异常:{}",id);
            return SystemVo.error(SellerGoodsEnum.SELLER_GOODS_QRY_ITEMCAT_ERROR);
        }
        return SystemVo.success(sellerGoodsItemCatApi.findOneById(id),SellerGoodsEnum.SELLER_GOODS_SUCCESS);
    }

    @RequestMapping("/modify")
    public SystemVo modify(@RequestBody ItemCatVo itemCatVo) {
        return sellerGoodsItemCatApi.modify(itemCatVo);
    }

    @RequestMapping("/del")
    public SystemVo del(@RequestParam("ids") String[] ids) {
        return sellerGoodsItemCatApi.del(ids);
    }

    @RequestMapping("/findAll")
    public SystemVo<List<TbItemCat>> findAll() {
        List<TbItemCat> tbItemCatList = sellerGoodsItemCatApi.findAll();
        if(null == tbItemCatList) {
            return SystemVo.error(SellerGoodsEnum.SELLER_GOODS_QRY_ITEMCAT_ERROR);
        }

        return SystemVo.success(tbItemCatList,SellerGoodsEnum.SELLER_GOODS_SUCCESS);
    }


}
