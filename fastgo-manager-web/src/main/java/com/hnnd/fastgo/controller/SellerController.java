package com.hnnd.fastgo.controller;

import com.hnnd.fastgo.Qo.QryTbsellerQo;
import com.hnnd.fastgo.clientapi.sellergoods.seller.SellerApi;
import com.hnnd.fastgo.entity.TbSeller;
import com.hnnd.fastgo.enumration.SellerGoodsEnum;
import com.hnnd.fastgo.vo.PageResultVo;
import com.hnnd.fastgo.vo.SystemVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * 商家管理模块
 * Created by 85073 on 2018/11/24.
 */
@RestController
@Controller
@RequestMapping("/seller")
@Slf4j
public class SellerController {

    @Autowired
    private SellerApi sellerApi;

    @RequestMapping("/findListByPage")
    public SystemVo<PageResultVo<TbSeller>> qryTbSellerListByPage(@RequestParam(name = "pageNum",defaultValue = "1")Integer pageNum,
                                                                  @RequestParam(name="pageSize",defaultValue = "10") Integer pageSize,
                                                                  @RequestBody(required = false) QryTbsellerQo qryTbsellerQo){
        log.info("接受的參數:{}",qryTbsellerQo);
        if(qryTbsellerQo == null) {
            qryTbsellerQo = new QryTbsellerQo();
            qryTbsellerQo.setStatus("");
        }
        PageResultVo<TbSeller> pageResultVo = sellerApi.qryTbSellerListByPage(pageNum,pageSize,qryTbsellerQo);
        if(null == pageResultVo) {
            return SystemVo.error(SellerGoodsEnum.SELLER_QRY_LIST_ERROR);
        }
        return SystemVo.success(pageResultVo,SellerGoodsEnum.SELLER_GOODS_SUCCESS);
    }

    /**
     * 根据SellerId查询商家信息
     * @param sellerId 商家ID
     * @return  SystemVo<TbSeller>
     */
    @RequestMapping("/findOneById/{sellerId}")
    public SystemVo<TbSeller> findOneById(@PathVariable("sellerId") String sellerId) {
        log.info("接受到的入参:{}",sellerId);
        if(null == sellerId) {
            return SystemVo.error(SellerGoodsEnum.SELLER_OPER_GET_ERROR);
        }
        return sellerApi.findOneById(sellerId);
    }

    /**
     * 审核账户状态
     * @param sellerId 商家用户信息ID
     * @param status 账户状态
     * @return SystemVo
     */
    @RequestMapping("/updateAccountStatus")
    public SystemVo updateAcctStatus(@RequestParam("sellerId")String sellerId,
                                     @RequestParam("status") String status) {
        log.info("接受的入参 sellerId:{},status:{}",sellerId,status);
        if(StringUtils.isEmpty(sellerId) || StringUtils.isEmpty(status)) {
            return SystemVo.error(SellerGoodsEnum.SELLER_OPER_IN_PARAM_NULL);
        }
        return sellerApi.updateAcctStatus(sellerId,status);
    }
}
