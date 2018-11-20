package com.hnnd.fastgo.service;

import com.hnnd.fastgo.entity.TbTypeTemplate;
import com.hnnd.fastgo.vo.PageResultVo;

/**
 * 商品模版接口
 * Created by Administrator on 2018/11/20.
 */
public interface ISellerGoodsTempService {

    /**
     * 分页查询
     * @param pageNum 当前页
     * @param pageSize 每页条数
     * @param qryConditon 查询条件
     * @return  PageResultVo<TbTypeTemplate>
     */
    PageResultVo<TbTypeTemplate> list4Page(Integer pageNum,Integer pageSize,String qryConditon );
}
