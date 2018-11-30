package com.hnnd.fastgo.controller;

import com.hnnd.fastgo.Qo.QryTbsellerQo;
import com.hnnd.fastgo.clientapi.sellergoods.seller.SellerApi;
import com.hnnd.fastgo.entity.MsgLog;
import com.hnnd.fastgo.entity.TbSeller;
import com.hnnd.fastgo.entity.TbSpecificationOption;
import com.hnnd.fastgo.service.ISellerGoodsSpecService;
import com.hnnd.fastgo.service.ISellerService;
import com.hnnd.fastgo.enumration.SellerGoodsEnum;
import com.hnnd.fastgo.enumration.SellerAccoutStatusEnum;
import com.hnnd.fastgo.vo.PageResultVo;
import com.hnnd.fastgo.vo.SpecVo;
import com.hnnd.fastgo.vo.SystemVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 商家管理控制器
 * Created by Administrator on 2018/11/23.
 */
@RestController
@Slf4j
@RequestMapping("/seller")
public class SellerController implements SellerApi {

    @Autowired
    private ISellerService sellerServiceImpl;

    @RequestMapping("/loadUserByUsername/{sellerId}")
    public SystemVo<TbSeller> loadUserByUsername(@PathVariable("sellerId") String sellerId) {
        if(null == sellerId) {
            log.error("通过ID:{}查询商家信息入参为空{}",sellerId);
            return SystemVo.error(SellerGoodsEnum.SELLER_OPER_IN_PARAM_NULL);
        }
        TbSeller tbSeller = sellerServiceImpl.loadUserByUserName(sellerId, SellerAccoutStatusEnum.SELLER_ACCOUNT_PASS_ADUIT.getCode());
        if(null == tbSeller) {
            return SystemVo.error(SellerGoodsEnum.SELLER_OPER_GET_ERROR);
        }
        return SystemVo.success(tbSeller,SellerGoodsEnum.SELLER_GOODS_SUCCESS);
    }

    /**
     * 商家用户注册
     * @param tbSeller
     * @return SystemVo
     */
    @RequestMapping("/register")
    public SystemVo register(@RequestBody  TbSeller tbSeller) {
        if(null ==tbSeller) {
            return SystemVo.error(SellerGoodsEnum.SELLER_OPER_IN_PARAM_NULL);
        }
        //保存商家用户信息
        try {
            sellerServiceImpl.register(tbSeller);
            return SystemVo.success(SellerGoodsEnum.SELLER_GOODS_SUCCESS);
        } catch (Exception e) {
            log.error("商家用户注册异常:{}",e);
            return SystemVo.error(SellerGoodsEnum.SELLER_OPER_SAVE_ERROR);
        }
    }

    @RequestMapping("/validate/{checkType}/{checkValue}")
    public SystemVo validateForm(@PathVariable("checkType") String checkType, @PathVariable("checkValue") String checkValue) {
        log.info("校验的类型:{},校验的值{}",checkType,checkValue);

        //校验用户注册信息
        try {
            if(sellerServiceImpl.validateForm(checkType,checkValue)) {
                return SystemVo.success(SellerGoodsEnum.SELLER_GOODS_SUCCESS);
            }
            return SystemVo.error(SellerGoodsEnum.SELLER_CHECK_FORM_EIXST);
        } catch (Exception e) {
            log.error("商家用户校验异常:{}",e);
            return SystemVo.error(SellerGoodsEnum.SELLER_CHECK_FORM_ERROR);
        }
    }

    @RequestMapping("/findListByPage")
    public PageResultVo<TbSeller> qryTbSellerListByPage(@RequestParam(name = "pageNum",defaultValue = "1")Integer pageNum,
                                                        @RequestParam(name="pageSize",defaultValue = "10") Integer pageSize,
                                                        @RequestBody(required = false) QryTbsellerQo qryTbsellerQo){
        log.info("接受到的查询参数:{}",qryTbsellerQo);
        return sellerServiceImpl.qryTbSellerListByPage(pageNum,pageSize,qryTbsellerQo);
    }

    @RequestMapping("/findOneById/{sellerId}")
    public SystemVo<TbSeller> findOneById(@PathVariable("sellerId") String sellerId) {
        try {
            TbSeller tbSeller = sellerServiceImpl.findOneById(sellerId);
            return SystemVo.success(tbSeller,SellerGoodsEnum.SELLER_GOODS_SUCCESS);
        }catch (Exception e){
            log.error("根据商家信息ID查询商家信息异常");
            return SystemVo.error(SellerGoodsEnum.SELLER_OPER_GET_ERROR);
        }
    }

    /**
     * 审核账户状态
     * @param sellerId 商家用户信息ID
     * @param status 账户状态
     * @return
     */
    @RequestMapping("/updateAccountStatus")
    public SystemVo updateAcctStatus(@RequestParam("sellerId")String sellerId,
                                     @RequestParam("status") String status) {
        try{
            //更新账户状态
            sellerServiceImpl.updateAcctStatus(sellerId,status);
        }catch (Exception e) {
            log.error("审核商家用户状态异常",e);
            return SystemVo.error(SellerGoodsEnum.SELLER_MODIFY_SELLER_ERROR);
        }
        return SystemVo.success(SellerGoodsEnum.SELLER_GOODS_SUCCESS);

    }


}
