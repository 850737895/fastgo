package com.hnnd.fastgo.controller;

import com.hnnd.fastgo.entity.TbTypeTemplate;
import com.hnnd.fastgo.enumration.SellerGoodsEnum;
import com.hnnd.fastgo.service.ISellerGoodsTempService;
import com.hnnd.fastgo.vo.PageResultVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 商品模版管理
 * Created by Administrator on 2018/11/20.
 */
@Slf4j
@RestController
@RequestMapping("/sellerGoods/temp")
public class SellerGoodsTempController {

    @Autowired
    private ISellerGoodsTempService sellerGoodsTempServiceImpl;

    /**
     * 类型模版列表
     * @return SystemVo<PageResultVo<TbTypeTemplate>>
     */
    @RequestMapping("/list")
    public PageResultVo<TbTypeTemplate> list(@RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum,
                                                       @RequestParam(value = "pageSize",defaultValue = "10")Integer pageSize,
                                                       @RequestParam(value = "qryCondition",required = false) String qryCondition) {
        return sellerGoodsTempServiceImpl.list4Page(pageNum,pageSize,qryCondition);
    }
}
