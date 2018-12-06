package com.hnnd.fastgo.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hnnd.fastgo.dao.TbContentCategoryMapper;
import com.hnnd.fastgo.entity.TbContent;
import com.hnnd.fastgo.entity.TbContentCategory;
import com.hnnd.fastgo.service.IContentCategoryService;
import com.hnnd.fastgo.vo.PageResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * 广告分类接口实现类
 * Created by Administrator on 2018/12/5.
 */
@Service
public class ContentCategoryServiceImpl implements IContentCategoryService {

    @Autowired
    private TbContentCategoryMapper tbContentCategoryMapper;

    @Override
    public PageResultVo<TbContentCategory> list(Integer pageNum, Integer pageSize, String contentCategoryName) {
        PageHelper.startPage(pageNum,pageSize);
        List<TbContentCategory> tbContentCategoryList =tbContentCategoryMapper.selectAllByCategoryName(contentCategoryName);
        PageInfo<TbContentCategory> categoryPageInfo = new PageInfo<>(tbContentCategoryList);
        return new PageResultVo<>(categoryPageInfo.getTotal(),categoryPageInfo.getList());
    }

    @Override
    public void save(TbContentCategory tbContentCategory) {
        tbContentCategoryMapper.insert(tbContentCategory);
    }

    @Override
    public void del(Long[] ids) {
        List<Long> idList = Arrays.asList(ids);
        tbContentCategoryMapper.delByIdInBatch(idList);
    }

    @Override
    public void modify(TbContentCategory tbContentCategory) {
        tbContentCategoryMapper.updateByPrimaryKey(tbContentCategory);
    }

    @Override
    public TbContentCategory findOneById(Long id) {
        return tbContentCategoryMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<TbContentCategory> findAll() {
        return tbContentCategoryMapper.selectAll();
    }
}
