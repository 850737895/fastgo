package com.hnnd.fastgo.service.impl;

import com.hnnd.fastgo.dao.TbBrandMapper;
import com.hnnd.fastgo.entity.TbBrand;
import com.hnnd.fastgo.service.IBrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 品牌管理业务逻辑层
 * Created by 85073 on 2018/11/13.
 */
@Service
public class BrandServiceImpl implements IBrandService {

    @Autowired
    private TbBrandMapper tbBrandMapper;

    @Override
    public List<TbBrand> selectAll() {
        return tbBrandMapper.selectAll();
    }
}
