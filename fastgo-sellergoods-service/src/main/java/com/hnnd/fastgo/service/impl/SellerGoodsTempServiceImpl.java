package com.hnnd.fastgo.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hnnd.fastgo.dao.TbTypeTemplateMapper;
import com.hnnd.fastgo.entity.TbTypeTemplate;
import com.hnnd.fastgo.service.ISellerGoodsTempService;
import com.hnnd.fastgo.vo.PageResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *  商品模版接口服务实现类
 * Created by Administrator on 2018/11/20.
 */
@Service
public class SellerGoodsTempServiceImpl implements ISellerGoodsTempService {

    @Autowired
    private TbTypeTemplateMapper tbTypeTemplateMapper;

    @Override
    public PageResultVo<TbTypeTemplate> list4Page(Integer pageNum, Integer pageSize, String qryConditon) {
        PageHelper.startPage(pageNum,pageSize);
        List<TbTypeTemplate> tbTypeTemplates = tbTypeTemplateMapper.selectPageByCondition(qryConditon);
        PageInfo<TbTypeTemplate> pageInfo = new PageInfo<>(tbTypeTemplates);
        return new PageResultVo<>(pageInfo.getTotal(),pageInfo.getList());
    }
}
