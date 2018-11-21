package com.hnnd.fastgo.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hnnd.fastgo.dao.TbItemCatMapper;
import com.hnnd.fastgo.entity.TbItemCat;
import com.hnnd.fastgo.service.ISellerGoodsItemCatService;
import com.hnnd.fastgo.vo.PageResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 商品类目服务
 * Created by Administrator on 2018/11/21.
 */
@Service
public class SellerGoodsItemCatServiceImpl implements ISellerGoodsItemCatService {

    @Autowired
    private TbItemCatMapper tbItemCatMapper;

    @Override
    public PageResultVo<TbItemCat> level(Integer pageNum, Integer pageSize, Integer parentId, String qyrCondition) {
        PageHelper.startPage(pageNum,pageSize);
        List<TbItemCat> tbItemCatList = tbItemCatMapper.level(parentId,qyrCondition);
        PageInfo<TbItemCat> tbItemCatPageInfo = new PageInfo<>(tbItemCatList);
        return new PageResultVo<>(tbItemCatPageInfo.getTotal(),tbItemCatPageInfo.getList());
    }

    @Override
    public List<TbItemCat> findByParentId(Integer parentId) {
        List<TbItemCat> tbItemCatList = tbItemCatMapper.level(parentId,null);
        return tbItemCatList;
    }
}
