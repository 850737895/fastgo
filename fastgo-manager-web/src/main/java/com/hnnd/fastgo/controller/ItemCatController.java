package com.hnnd.fastgo.controller;

import com.hnnd.fastgo.clientapi.sellergoods.itemcat.ISellerGoodsItemCatApi;
import com.hnnd.fastgo.entity.TbItemCat;
import com.hnnd.fastgo.enumration.SellerGoodsEnum;
import com.hnnd.fastgo.vo.PageResultVo;
import com.hnnd.fastgo.vo.SystemVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

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

}
