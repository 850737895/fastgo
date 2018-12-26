package com.hnnd.fastgo.controller;

import com.hnnd.fastgo.entity.TbAddress;
import com.hnnd.fastgo.entity.TbProvinces;
import com.hnnd.fastgo.enumration.SellerGoodsEnum;
import com.hnnd.fastgo.service.IAddresService;
import com.hnnd.fastgo.vo.SystemVo;
import com.sun.org.apache.bcel.internal.generic.IADD;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 收货地址服务
 * Created by 85073 on 2018/12/26.
 */
@RestController
@RequestMapping("/address")
public class AddressController {

    @Autowired
    private IAddresService addresServiceImpl;

    @RequestMapping("/initProvince")
    public SystemVo<List<TbProvinces>> initProvinceList() {
        return SystemVo.success(addresServiceImpl.initProvinceList(), SellerGoodsEnum.SELLER_GOODS_SUCCESS);
    }
}
