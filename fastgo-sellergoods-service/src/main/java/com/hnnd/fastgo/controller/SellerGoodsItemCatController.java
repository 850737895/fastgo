package com.hnnd.fastgo.controller;

import com.hnnd.fastgo.entity.TbItemCat;
import com.hnnd.fastgo.service.ISellerGoodsItemCatService;
import com.hnnd.fastgo.vo.PageResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 商品类目管理
 * Created by Administrator on 2018/11/21.
 */
@RestController
@RequestMapping("/sellerGoods/itemCat")
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
}
