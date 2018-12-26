package com.hnnd.fastgo.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.google.common.collect.Lists;
import com.hnnd.fastgo.constant.SysConst;
import com.hnnd.fastgo.entity.TbOrderItem;
import com.hnnd.fastgo.enumration.SellerGoodsEnum;
import com.hnnd.fastgo.service.ICartService;
import com.hnnd.fastgo.util.CookieUtil;
import com.hnnd.fastgo.vo.CartVo;
import com.hnnd.fastgo.vo.SystemVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
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
    @CrossOrigin(value = {"http://localhost:9104"},allowCredentials = "true")
    public SystemVo addCartList(@RequestParam("skuId")Long skuId,@RequestParam("num")Integer num) {
        if(skuId==null || num == null) {
            log.error("添加购物车的入参为空");
            return SystemVo.error(SellerGoodsEnum.ADD_CART_INPARAM_NULL);
        }
        String loginName = SecurityContextHolder.getContext().getAuthentication().getName();
        log.info("当前登录的用户名:{}",loginName);
        List<CartVo> cartVoList = findCartList().getData();
        try {
            //获取购物车列表

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

        return SystemVo.success(cartVoList,SellerGoodsEnum.SELLER_GOODS_SUCCESS);
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

    /**
     * 加载勾选的购物车列表
     * @param skuIds item
     * @return  SystemVo<List<SystemVo>>
     */
    @RequestMapping("/loadSelectCartList")
    public SystemVo<List<SystemVo>> loadSelectCartList(@RequestParam("skuIds") String [] skuIds) {
        if(skuIds==null || skuIds.length==0) {
            log.error("购物车结算入参异常");
            return SystemVo.error(SellerGoodsEnum.CAL_CART_LIST_INPARAM_NULL);
        }
        List<CartVo> cartVoList = findCartList().getData();
        List<CartVo> selectCartList = searchSelectCartList(cartVoList,skuIds);
        return SystemVo.success(selectCartList,SellerGoodsEnum.SELLER_GOODS_SUCCESS);
    }

    /**
     * 从所有购物车中搜索出选择的购物车
     */
    private List<CartVo> searchSelectCartList(List<CartVo> allCartList,String[] skuIds) {
        if(allCartList==null || allCartList.isEmpty()) {
            throw new RuntimeException("购物车不存在");
        }

        List<String> skuList = Arrays.asList(skuIds);
        List<CartVo> selectCartVoList = Lists.newArrayList();

        //遍历所有的购物车选项
        for(CartVo cartVo : allCartList) {
            //一个购物车中的购物明细
            CartVo retCartVo = selectOrderItem(skuList,cartVo);
            if(retCartVo!=null) {
                selectCartVoList.add(retCartVo);
            }
        }
        return selectCartVoList;
    }

    /**
     * 判断订单明细是否是被勾选
     * @param skuIds
     * @param cartVo
     * @return
     */
    private CartVo  selectOrderItem(List<String> skuIds,CartVo cartVo) {
        CartVo retCartVo = new CartVo();
        List<TbOrderItem> tbOrderItemList = Lists.newArrayList();
        for (TbOrderItem tbOrderItem:cartVo.getOrderItemList()) {
            if(skuIds.contains(tbOrderItem.getItemId().toString())) {
                tbOrderItemList.add(tbOrderItem);
            }
        }
        //存在勾选的
        if(tbOrderItemList.size()>0) {
            retCartVo.setSellerId(cartVo.getSellerId());
            retCartVo.setSellerName(cartVo.getSellerName());
            retCartVo.setOrderItemList(tbOrderItemList);
            return retCartVo;
        }else{
            return null;
        }
    }
}
