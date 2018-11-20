package com.hnnd.fastgo.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.hnnd.fastgo.dao.TbSpecificationMapper;
import com.hnnd.fastgo.dao.TbSpecificationOptionMapper;
import com.hnnd.fastgo.entity.TbSpecification;
import com.hnnd.fastgo.entity.TbSpecificationOption;
import com.hnnd.fastgo.enumration.SellerGoodsEnum;
import com.hnnd.fastgo.service.ISellerGoodsSpecService;
import com.hnnd.fastgo.vo.PageResultVo;
import com.hnnd.fastgo.vo.SpecOpsVo;
import com.hnnd.fastgo.vo.SpecVo;
import com.hnnd.fastgo.vo.SystemVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Administrator on 2018/11/16.
 */
@Service
public class SellerGoodsSpecServiceImpl implements ISellerGoodsSpecService {

    @Autowired
    private TbSpecificationMapper tbSpecificationMapper;

    @Autowired
    private TbSpecificationOptionMapper tbSpecificationOptionMapper;


    @Override
    public PageResultVo<TbSpecification> selectAllByPage(Integer pageNum, Integer pageSize, String queryCondition) {
        PageHelper.startPage(pageNum,pageSize);
        List<TbSpecification> tbSpecificationList = tbSpecificationMapper.selectAllByPage(queryCondition);
        PageInfo<TbSpecification> pageInfo = new PageInfo<>(tbSpecificationList);
        return new PageResultVo<TbSpecification>(pageInfo.getTotal(),pageInfo.getList());
    }

    @Transactional
    @Override
    public SystemVo saveSpec(SpecVo specVo) {
        TbSpecification tbSpecification = new TbSpecification();
        tbSpecification.setSpecName(specVo.getSpecName());

        //保存规格表
        tbSpecificationMapper.insert(tbSpecification);

        //保存规格选项表
        for (SpecOpsVo item: specVo.getSpecOps()) {
            TbSpecificationOption tbSpecificationOption = new TbSpecificationOption();
            tbSpecificationOption.setOptionName(item.getOptionName());
            tbSpecificationOption.setOrders(item.getOrders());
            tbSpecificationOption.setSpecId(tbSpecification.getId());
            tbSpecificationOptionMapper.insert(tbSpecificationOption);
        }
        return SystemVo.success(SellerGoodsEnum.SELLER_GOODS_SUCCESS);
    }

    @Transactional
    @Override
    public SystemVo modifySpec(SpecVo specVo) {
        //修改规格表
        TbSpecification tbSpecification = new TbSpecification();
        tbSpecification.setId(specVo.getId());
        tbSpecification.setSpecName(specVo.getSpecName());
        tbSpecificationMapper.updateByPrimaryKey(tbSpecification);

        //删除原来的规格选项
        tbSpecificationOptionMapper.deleteTbSpecOpsBySpecId(tbSpecification.getId());

        //保存规格选项表
        List<TbSpecificationOption> tbSpecificationOptions = Lists.newArrayList();
        for (SpecOpsVo item: specVo.getSpecOps()) {
            TbSpecificationOption tbSpecificationOption = new TbSpecificationOption();
            tbSpecificationOption.setOptionName(item.getOptionName());
            tbSpecificationOption.setOrders(item.getOrders());
            tbSpecificationOption.setSpecId(tbSpecification.getId());
            tbSpecificationOptions.add(tbSpecificationOption);
        }

        tbSpecificationOptionMapper.insertTbSpecOpsBatch(tbSpecificationOptions);
        return SystemVo.success(SellerGoodsEnum.SELLER_GOODS_SUCCESS);
    }

    @Override
    public SpecVo findOne(Long specId) {
        SpecVo specVo = new SpecVo();

        TbSpecification tbSpecification =tbSpecificationMapper.selectByPrimaryKey(specId);
        specVo.setSpecName(tbSpecification.getSpecName());
        specVo.setId(tbSpecification.getId());

        //查询规格选项
        List<TbSpecificationOption> tbSpecificationOptions = tbSpecificationOptionMapper.selectBySpecId(tbSpecification.getId());
        List<SpecOpsVo> specOpsVoList = Lists.newArrayList();
        for (TbSpecificationOption item:tbSpecificationOptions) {
            SpecOpsVo specOpsVo = new SpecOpsVo();
            BeanUtils.copyProperties(item,specOpsVo);
            specOpsVoList.add(specOpsVo);
        }
        specVo.setSpecOps(specOpsVoList);
        return specVo;
    }

    @Transactional
    @Override
    public void delSpecBySpecId(String[] specIds) {
        //删除商品规格表
        List<Long> ids = Lists.newArrayList();
        for (String id:specIds) {
            ids.add(Long.valueOf(id));
        }
        tbSpecificationMapper.delSpecByIdsInBatch(ids);

        //删除规格选项表


    }
}
