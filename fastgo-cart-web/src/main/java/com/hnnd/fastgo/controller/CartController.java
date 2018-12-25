package com.hnnd.fastgo.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.google.common.collect.Lists;
import com.hnnd.fastgo.constant.SysConst;
import com.hnnd.fastgo.enumration.SellerGoodsEnum;
import com.hnnd.fastgo.service.ICartService;
import com.hnnd.fastgo.util.CookieUtil;
import com.hnnd.fastgo.vo.CartVo;
import com.hnnd.fastgo.vo.SystemVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 购物车
 * Created by Administrator on 2018/12/25.
 */
@RestController
@RequestMapping("/cart")
@Slf4j
public class CartController {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private HttpServletResponse response;

    @Autowired
    private ICartService cartServiceImpl;

    /**
     *
     * @param skuId skuID
     * @param num 数量
     * @return
     */
    @RequestMapping("/addCartList")
    public SystemVo addCartList(@RequestParam("skuId")Long skuId,@RequestParam("num")Integer num) {
        if(skuId==null || num == null) {
            log.error("添加购物车的入参为空");
            return SystemVo.error(SellerGoodsEnum.ADD_CART_INPARAM_NULL);
        }
        String loginName = SecurityContextHolder.getContext().getAuthentication().getName();
        log.info("当前登录的用户名:{}",loginName);

        try {
            //获取购物车列表
            List<CartVo> cartVoList = findCartList().getData();
            log.info("没增加skuId:{}之前的购物车明细:{}",skuId,cartVoList);

            cartVoList = cartServiceImpl.addCartList(cartVoList,skuId,num);
            log.info("增加skuId:{}之后的购物车明细:{}",skuId,cartVoList);

            if(SysConst.ANONYMOUS_USER.equals(loginName)) {//没登录
                CookieUtil.setCookie(request,response,SysConst.CARTLIST_STORE_IN_COOKIE,JSON.toJSONString(cartVoList),SysConst.CARTLIST_IN_COOKIE_EXIRE_TIME,SysConst.ENCODE_CHARSET);
            }else {//登录
                cartServiceImpl.saveCartList2Redis(cartVoList,loginName);
            }
        } catch (Exception e) {
            log.error("添加购物车失败:{}",e);
            return SystemVo.error(SellerGoodsEnum.ADD_CART_LIST_ERROR);
        }

        return SystemVo.success(SellerGoodsEnum.SELLER_GOODS_SUCCESS);
    }

    @RequestMapping("/cartList")
    public SystemVo<List<CartVo>> findCartList() {
        String loginName = SecurityContextHolder.getContext().getAuthentication().getName();
        log.info("当前登录的用户名:{}",loginName);

        String cookieCartList = CookieUtil.getCookieValue(request,SysConst.CARTLIST_STORE_IN_COOKIE,SysConst.ENCODE_CHARSET);
        if(StringUtils.isEmpty(cookieCartList)) {
            cookieCartList="[]";
        }
        List<CartVo> cartVoList = JSON.parseObject(cookieCartList,new TypeReference<List<CartVo>>(){});

        if(SysConst.ANONYMOUS_USER.equals(loginName)) {//没有登录
            log.info("从cookie中加载出购物车:{}",cartVoList);
            return SystemVo.success(cartVoList,SellerGoodsEnum.SELLER_GOODS_SUCCESS);
        }else{//登录
            List<CartVo> redisCartList = cartServiceImpl.findCartList4Redis(loginName);
            if(cartVoList.size()>0) {//需要合并cookie和redis中的购物车
                if(null == redisCartList) {
                    redisCartList = Lists.newArrayList();
                }

                //合并购物车
                cartServiceImpl.mergeCookieCartList2Redis(cartVoList,redisCartList);

                cartServiceImpl.saveCartList2Redis(redisCartList,loginName);

                //删除本地购物车
                CookieUtil.deleteCookie(request,response,SysConst.CARTLIST_STORE_IN_COOKIE);
            }
            return SystemVo.success(redisCartList, SellerGoodsEnum.SELLER_GOODS_SUCCESS);
        }
    }
}
