package com.hnnd.fastgo.service;

import com.hnnd.fastgo.vo.CartVo;

import java.util.List;

/**
 * Created by Administrator on 2018/12/25.
 */
public interface ICartService {

    /**
     * 根据用户名查询该用户的购物车
     * @param loginUserName 登录用户名
     * @return List<CartVo>
     */
    List<CartVo> findCartList4Redis(String loginUserName);

    /**
     * 合并本地购物车到远程购物车
     * @param cookieCartList 本地购物车
     * @param redisCartList 远程购物车
     * @return 合并后的购物车
     */
    List<CartVo> mergeCookieCartList2Redis(List<CartVo> cookieCartList,List<CartVo> redisCartList);

    /**
     * 添加购物车
     * @param cartList 当前购物车
     * @param skuId
     * @param num
     * @return
     */
    List<CartVo> addCartList(List<CartVo> cartList,Long skuId,Integer num);

    /**
     * 保存购物车列表到redis
     * @param cartVoList 购物车里诶啊哦
     * @param loginUserName 登录用户名
     */
    void saveCartList2Redis(List<CartVo> cartVoList,String loginUserName);
}
