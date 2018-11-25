package com.hnnd.fastgo.service;

import com.hnnd.fastgo.Qo.QryTbsellerQo;
import com.hnnd.fastgo.entity.TbSeller;
import com.hnnd.fastgo.vo.PageResultVo;

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

    /**
     * 检查表单校验
     * @param checkType 校验的类型
     * @param checkValue 校验值
     * @return
     */
    boolean validateForm(String checkType,String checkValue);

    /**
     * 分页查询列表
     * @param pageNum 当前页
     * @param pageSize  每页的条数
     * @param qryTbsellerQo 查询对象
     * @return
     */
    public PageResultVo<TbSeller> qryTbSellerListByPage(Integer pageNum, Integer pageSize, QryTbsellerQo qryTbsellerQo);
}
