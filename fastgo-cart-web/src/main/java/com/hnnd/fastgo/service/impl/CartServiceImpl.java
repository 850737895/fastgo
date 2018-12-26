package com.hnnd.fastgo.service.impl;

import com.alibaba.druid.support.json.JSONUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.google.common.collect.Lists;
import com.hnnd.fastgo.constant.RedisConstant;
import com.hnnd.fastgo.constant.SysConst;
import com.hnnd.fastgo.dao.TbItemMapper;
import com.hnnd.fastgo.entity.TbItem;
import com.hnnd.fastgo.entity.TbOrder;
import com.hnnd.fastgo.entity.TbOrderItem;
import com.hnnd.fastgo.enumration.SkuStatus;
import com.hnnd.fastgo.service.ICartService;
import com.hnnd.fastgo.vo.CartVo;
import com.redisoper.IRedisService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.omg.SendingContext.RunTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

/**
 * 购物车服务接口类
 * Created by Administrator on 2018/12/25.
 */
@Slf4j
@Service
public class CartServiceImpl implements ICartService {

    @Autowired
    private IRedisService redisServiceImpl;

    @Autowired
    private TbItemMapper tbItemMapper;

    @Override
    public List<CartVo> findCartList4Redis(String loginUserName) {
        String cartList = redisServiceImpl.hget(RedisConstant.CARTLIST_IN_REDIS_KEY,loginUserName);
        if(StringUtils.isEmpty(cartList)) {
            log.info("从redis中加载购物车列表为空");
            return null;
        }else{
            List<CartVo> cartVoList = JSON.parseObject(cartList,new TypeReference<List<CartVo>>(){});
            return cartVoList;
        }
    }

    @Override
    public List<CartVo> mergeCookieCartList2Redis(List<CartVo> cookieCartList, List<CartVo> redisCartList) {
        for(CartVo cartVo:cookieCartList) {
           for(TbOrderItem tbOrderItem:cartVo.getOrderItemList()) {
               addCartList(redisCartList,tbOrderItem.getItemId(),tbOrderItem.getNum());
           }
        }

        return redisCartList;
    }

    @Override
    public List<CartVo> addCartList(List<CartVo> cartList, Long skuId, Integer num) {
        //根据skuId 查询商品明细表
        TbItem tbItem = tbItemMapper.selectByPrimaryKey(skuId);
        if(null == tbItem) {
            log.error("通过SKUID:{}查询不到商品详情信息",skuId);
            throw new RuntimeException("根据商品ID查询不到对应的商品详情信息");
        }
        if(SkuStatus.SKU_STATUS_0.getCode().equals(tbItem.getStatus())) {
            log.error("商品详情信息状态错误:{}",tbItem.getStatus());
        }

        //判断当前购物车是否包含该商家的商品
        String sellerId = tbItem.getSellerId();
        CartVo cartVo = findCartBySellerId(cartList,sellerId);

        if(null == cartVo) {//没有商家的购物车
            addSellerCartVo2CartList(cartList,tbItem,num);
        }else {//购物车有该商家的商品
            modifyCartItems(cartList,cartVo,tbItem,skuId,num);
        }
        return cartList;
    }

    @Override
    public void saveCartList2Redis(List<CartVo> cartVoList, String loginUserName) {
        redisServiceImpl.hset(RedisConstant.CARTLIST_IN_REDIS_KEY,loginUserName, JSON.toJSONString(cartVoList));
    }

    @Override
    public void saveSelectCartList(String[] skuIds,String loginUserName) {
        redisServiceImpl.hset(RedisConstant.SELECT_CARTLIST_IN_REDIS_KEY,loginUserName,JSON.toJSONString(skuIds));
    }

    @Override
    public String[] getSelectCartList4Redis(String loginUserName) {
        String skuIdStr = redisServiceImpl.hget(RedisConstant.SELECT_CARTLIST_IN_REDIS_KEY,loginUserName);
        String [] skuIds = JSON.parseObject(skuIdStr,new TypeReference<String[]>(){});
        return skuIds;
    }

    /**
     * 通过商家id搜索购物车
     * @param cartList 当前的购物车列表
     * @param sellerId 商家id
     * @return 当前商家的购物车信息
     */
    private CartVo findCartBySellerId(List<CartVo> cartList, String sellerId) {
        for(CartVo cartVo:cartList) {
            if(cartVo.getSellerId().equals(sellerId)) {
                return cartVo;
            }
        }
        return null;
    }

    /**
     * 通过skuId 查询购物明细
     * @param orderItemList 购物车明细列表
     * @param skuId skuid
     * @return TbOrderItem
     */
    private TbOrderItem findOrderItembySkuId(List<TbOrderItem> orderItemList,Long skuId){
        for (TbOrderItem orderItem:orderItemList) {
            if(orderItem.getItemId().longValue()==skuId.longValue()) {
                return orderItem;
            }
        }
        return null;
    }

    /**
     * 添加当前商家的购物车列表
     * @param cartVoList 当前的购物车列表
     * @param tbItem 商品详情
     */
    private void addSellerCartVo2CartList(List<CartVo> cartVoList,TbItem tbItem,Integer num) {
        CartVo cartVo = new CartVo();
        cartVo.setSellerId(tbItem.getSellerId());
        cartVo.setSellerName(tbItem.getSeller());
        TbOrderItem tbOrderItem = createOrderItem(tbItem,num);
        List<TbOrderItem> tbOrderItemList = Lists.newArrayList();
        tbOrderItemList.add(tbOrderItem);
        cartVo.setOrderItemList(tbOrderItemList);
        cartVoList.add(cartVo);
    }

    /**
     * 修改购物车中的数据和金额
     */
    private void modifyCartItems(List<CartVo> cartList, CartVo cartVo,TbItem tbItem,Long skuId,Integer num){
        TbOrderItem tbOrderItem = findOrderItembySkuId(cartVo.getOrderItemList(),skuId);
        if(null == tbOrderItem) { //购物车没有该skuid的商品
            tbOrderItem = createOrderItem(tbItem,num);
            cartVo.getOrderItemList().add(tbOrderItem);
        }else {//有该skuid的商品

            //修改商品数据
            tbOrderItem.setNum(tbOrderItem.getNum()+num);

            //商品明细数量为0,异常商品明细
            if(tbOrderItem.getNum()<=0) {
                cartVo.getOrderItemList().remove(tbOrderItem);
            }

            //商品明细列表为空 移除该购物车
            if(cartVo.getOrderItemList().size()==0) {
                cartList.remove(cartVo);
            }
            //修改商品总价
            tbOrderItem.setTotalFee(new BigDecimal(tbOrderItem.getNum()*tbItem.getPrice()));
        }
    }

    /**
     * 构建购物车明细
     * @param tbItem 商品明细
     * @param num 购物车数量
     * @return TbOrderItem
     */
    private TbOrderItem createOrderItem(TbItem tbItem,Integer num) {
        TbOrderItem tbOrderItem = new TbOrderItem();
        tbOrderItem.setItemId(tbItem.getId());
        tbOrderItem.setPrice(new BigDecimal(tbItem.getPrice()).setScale(2,BigDecimal.ROUND_HALF_DOWN));
        tbOrderItem.setGoodsId(tbItem.getGoodsId());
        tbOrderItem.setNum(num);
        tbOrderItem.setPicPath(tbItem.getImage());
        tbOrderItem.setTitle(tbItem.getTitle());
        tbOrderItem.setTotalFee(new BigDecimal(tbItem.getPrice()*num).setScale(2,BigDecimal.ROUND_HALF_DOWN));
        tbOrderItem.setSellerId(tbItem.getSellerId());
        return tbOrderItem;
    }

    public static void main(String[] args) {
        String [] test={"123","456"};

        System.out.println(JSON.toJSONString(test));
    }
}
