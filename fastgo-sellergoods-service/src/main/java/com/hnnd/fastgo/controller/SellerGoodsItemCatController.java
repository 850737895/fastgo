package com.hnnd.fastgo.controller;

import com.hnnd.fastgo.entity.TbItemCat;
import com.hnnd.fastgo.enumration.SellerGoodsEnum;
import com.hnnd.fastgo.service.ISellerGoodsItemCatService;
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
@RequestMapping("/sellerGoods/itemCat")
@Slf4j
public class SellerGoodsItemCatController {

    @Autowired
    private ISellerGoodsItemCatService sellerGoodsItemCatServiceImpl;

    /**
     * 分页查询商品类目列表
     * @param pageNum 当前页
     * @param pageSize 每页多少条
     * @param parentId 父类ID
     * @return PageResultVo<TbItemCat>
     */
    @RequestMapping("/level")
    public PageResultVo<TbItemCat> qryItemCatLevel(@RequestParam(value = "pageNum",defaultValue = "1")Integer pageNum,
                                                   @RequestParam(value = "pageSize",defaultValue = "10")Integer pageSize,
                                                   @RequestParam(value = "parentId",defaultValue = "0",required = false) Integer parentId,
                                                   @RequestParam(value = "qryCondition",required = false) String qryCondition) {
        return sellerGoodsItemCatServiceImpl.level(pageNum,pageSize,parentId,qryCondition);
    }

    @RequestMapping("/findByParentId")
    public List<TbItemCat> findByParentId(@RequestParam("parentId") Integer parentId){
        return sellerGoodsItemCatServiceImpl.findByParentId(parentId);
    }

    @RequestMapping("/save")
    public SystemVo save(@RequestBody ItemCatVo itemCatVo) {
        try {
            sellerGoodsItemCatServiceImpl.save(itemCatVo);
            return SystemVo.success(SellerGoodsEnum.SELLER_GOODS_SUCCESS);
        } catch (Exception e) {
            log.error("保存商品类目异常:{}",e);
            return SystemVo.error(SellerGoodsEnum.SELLER_GOODS_SAVE_ITEMCAT_ERROR);
        }
    }

    @RequestMapping("/findOne/{id}")
    public ItemCatVo findOneById(@PathVariable("id") Long id) {
        return sellerGoodsItemCatServiceImpl.findOneById(id);
    }

    @RequestMapping("/modify")
    public SystemVo modify(@RequestBody ItemCatVo itemCatVo) {
        try {
            sellerGoodsItemCatServiceImpl.modify(itemCatVo);
            return SystemVo.success(SellerGoodsEnum.SELLER_GOODS_SUCCESS);
        } catch (Exception e) {
            log.error("更新商品类目异常:{}",e);
            return SystemVo.error(SellerGoodsEnum.SELLER_GOODS_MODIFY_ITEMCAT_ERROR);
        }
    }

    @RequestMapping("/del")
    public SystemVo del(@RequestParam("ids") String[] ids) {
        try {
            sellerGoodsItemCatServiceImpl.del(ids);
            return SystemVo.success(SellerGoodsEnum.SELLER_GOODS_SUCCESS);
        } catch (Exception e) {
            log.error("批量删除类目信息异常:{}",e);
            return SystemVo.error(SellerGoodsEnum.SELLER_GOODS_DEL_ITEMCAT_ERROR);
        }
    }


    @RequestMapping("/findAll")
    public List<TbItemCat> findAll() {
        return sellerGoodsItemCatServiceImpl.findAll();
    }
}
