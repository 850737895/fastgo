package com.hnnd.fastgo.service;

import com.hnnd.fastgo.entity.TbSeller;

/**
 * 商品用户服务接口
 * Created by Administrator on 2018/11/23.
 */
public interface ISellerService {

    /**
     * 根据用户ID查询商家用户信息
     * @param sellerId 商家用户ID
     * @return TbSeller
     */
    TbSeller findOneById(String sellerId);

    /**
     * 查询商家用户信息
     * @param userName 用户名
     * @param status 用户状态
     * @return TbSeller
     */
    TbSeller loadUserByUserName(String userName,String status);

    /**
     * 商家用户注册
     * @param tbSeller 用户
     */
    void register(TbSeller tbSeller);
}
