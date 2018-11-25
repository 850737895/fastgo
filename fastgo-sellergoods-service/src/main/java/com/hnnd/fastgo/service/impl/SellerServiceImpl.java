package com.hnnd.fastgo.service.impl;

import com.github.pagehelper.PageHelper;
import com.hnnd.fastgo.Qo.QryTbsellerQo;
import com.hnnd.fastgo.dao.TbSellerMapper;
import com.hnnd.fastgo.entity.TbSeller;
import com.hnnd.fastgo.service.ISellerService;
import com.hnnd.fastgo.vo.PageResultVo;
import org.apache.ibatis.annotations.Param;
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

    @Override
    public boolean validateForm(String checkType, String checkValue) {

        boolean checkResult = false;

        //校验用户名
        if("sellerId".endsWith(checkType)) {
            if(tbSellerMapper.checkSellerId(checkValue)==0) {
                checkResult = true;
            }
        }

        if("nickName".endsWith(checkType)) {
            if(tbSellerMapper.checkNickName(checkValue)==0) {
                checkResult = true;
            }
        }

        if("linkmanMobile".endsWith(checkType)) {
            if(tbSellerMapper.checkLinkmanMobile(checkValue)==0) {
                checkResult = true;
            }
        }

        return checkResult;
    }

    @Override
    public PageResultVo<TbSeller> qryTbSellerListByPage(Integer pageNum, Integer pageSize, QryTbsellerQo qryTbsellerQo) {
        PageHelper.startPage(pageNum,pageSize);

        return null;
    }
}
