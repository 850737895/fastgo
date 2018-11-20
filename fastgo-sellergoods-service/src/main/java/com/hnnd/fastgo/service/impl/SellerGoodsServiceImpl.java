package com.hnnd.fastgo.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hnnd.fastgo.dao.TbBrandMapper;
import com.hnnd.fastgo.entity.TbBrand;
import com.hnnd.fastgo.service.ISellerGoodsService;
import com.hnnd.fastgo.vo.PageResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * 品牌管理业务逻辑层
 * Created by 85073 on 2018/11/13.
 */
@Service
public class SellerGoodsServiceImpl implements ISellerGoodsService {

    @Autowired
    private TbBrandMapper tbBrandMapper;

    @Override
    public List<TbBrand> selectAll() {
        return tbBrandMapper.selectAll();
    }

    @Override
    public List<Map<String, Object>> initBrandList() {
        return tbBrandMapper.initBrandList();
    }

    @Override
    public PageResultVo<TbBrand> selectAllByPage(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<TbBrand> tbBrandList = tbBrandMapper.selectAll();
        PageInfo<TbBrand> pageInfo = new PageInfo<>(tbBrandList);
        return new PageResultVo<>(pageInfo.getTotal(),pageInfo.getList());
    }

    @Override
    public void save(TbBrand tbBrand) {
        tbBrandMapper.insert(tbBrand);
    }

    @Override
    public TbBrand findOneById(Long id) {
        return tbBrandMapper.selectByPrimaryKey(id);
    }

    @Override
    public void modifyBrandById(TbBrand tbBrand) {
        tbBrandMapper.updateByPrimaryKey(tbBrand);
    }

    @Transactional
    @Override
    public void delBrandById(String[] ids) {
        for (String id: ids) {
            tbBrandMapper.deleteByPrimaryKey(Long.valueOf(id));
        }
    }

    @Override
    public PageResultVo<TbBrand> search(TbBrand tbBrand, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<TbBrand> tbBrandList = tbBrandMapper.selectListByConditon(tbBrand);
        PageInfo<TbBrand> pageInfo = new PageInfo<>(tbBrandList);
        return new PageResultVo<>(pageInfo.getTotal(),pageInfo.getList());
    }
}
