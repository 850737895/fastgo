package com.hnnd.fastgo.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.hnnd.fastgo.dao.TbTypeTemplateMapper;
import com.hnnd.fastgo.entity.TbTypeTemplate;
import com.hnnd.fastgo.service.ISellerGoodsTempService;
import com.hnnd.fastgo.vo.PageResultVo;
import com.hnnd.fastgo.vo.SystemVo;
import com.hnnd.fastgo.vo.TemplateTypeVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

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

    @Override
    public void save(TemplateTypeVo templateTypeVo) {
        TbTypeTemplate tbTypeTemplate = new TbTypeTemplate();
        BeanUtils.copyProperties(templateTypeVo,tbTypeTemplate);
        tbTypeTemplateMapper.insert(tbTypeTemplate);
    }

    @Override
    public TbTypeTemplate findOne(Long id) {
        return tbTypeTemplateMapper.selectByPrimaryKey(id);
    }

    @Override
    public int modify(TbTypeTemplate tbTypeTemplate) {
        return tbTypeTemplateMapper.updateByPrimaryKey(tbTypeTemplate);
    }

    @Override
    public void del(List<Long> ids) {
        tbTypeTemplateMapper.delByInBatch(ids);
    }

    @Override
    public List<Map<String, Object>> initTempTypeList() {
        return tbTypeTemplateMapper.initTempTypeList();
    }
}
