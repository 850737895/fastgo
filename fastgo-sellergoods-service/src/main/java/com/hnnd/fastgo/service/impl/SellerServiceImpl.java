package com.hnnd.fastgo.service.impl;

import com.hnnd.fastgo.dao.TbSellerMapper;
import com.hnnd.fastgo.entity.TbSeller;
import com.hnnd.fastgo.service.ISellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 商家商户信息
 * Created by Administrator on 2018/11/23.
 */
@Service
public class SellerServiceImpl implements ISellerService {

    @Autowired
    private TbSellerMapper tbSellerMapper;

    @Override
    public TbSeller findOneById(String sellerId) {
        return tbSellerMapper.selectByPrimaryKey(sellerId);
    }

    @Override
    public TbSeller loadUserByUserName(String userName, String status) {
        return tbSellerMapper.loadUserByUserName(userName,status);
    }

    @Override
    public void register(TbSeller tbSeller) {
        tbSellerMapper.register(tbSeller);
    }
}
