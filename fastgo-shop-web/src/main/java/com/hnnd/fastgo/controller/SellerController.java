package com.hnnd.fastgo.controller;

import com.hnnd.fastgo.clientapi.sellergoods.seller.SellerApi;
import com.hnnd.fastgo.util.FastDFSUploadUtils;
import com.hnnd.fastgo.entity.TbSeller;
import com.hnnd.fastgo.enumration.SellerAccoutStatusEnum;
import com.hnnd.fastgo.enumration.SellerGoodsEnum;
import com.hnnd.fastgo.vo.SystemVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

/**
 * 商家管理
 * Created by Administrator on 2018/11/23.
 */
@RestController
@RequestMapping("/seller")
@Slf4j
public class SellerController {

    @Autowired
    private SellerApi sellerApi;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private FastDFSUploadUtils fastDFSUploadUtils;

    @RequestMapping("/register")
    public SystemVo register(@RequestBody  TbSeller tbSeller) {
        if(null ==tbSeller) {
            return SystemVo.error(SellerGoodsEnum.SELLER_OPER_IN_PARAM_NULL);
        }
        tbSeller.setPassword(passwordEncoder.encode(tbSeller.getPassword()));
        tbSeller.setStatus(SellerAccoutStatusEnum.SELLER_ACCOUNT_ANADUIT.getCode());
        return sellerApi.register(tbSeller);
    }

    @RequestMapping("/validate/{checkType}/{checkValue}")
    public SystemVo validateForm(@PathVariable("checkType") String checkType, @PathVariable("checkValue") String checkValue) {
        log.info("校验的类型:{},校验的值{}",checkType,checkValue);
        if(StringUtils.isEmpty(checkType) || StringUtils.isEmpty(checkValue)) {
            return SystemVo.error(SellerGoodsEnum.SELLER_OPER_IN_PARAM_NULL);
        }
        return sellerApi.validateForm(checkType,checkValue);
    }


}
