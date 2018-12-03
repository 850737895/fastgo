package com.hnnd.fastgo.service.impl;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.hnnd.fastgo.dao.TbItemCatMapper;
import com.hnnd.fastgo.dao.TbTypeTemplateMapper;
import com.hnnd.fastgo.entity.TbItemCat;
import com.hnnd.fastgo.service.ISellerGoodsItemCatService;
import com.hnnd.fastgo.vo.ItemCatVo;
import com.hnnd.fastgo.vo.PageResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 商品类目服务
 * Created by Administrator on 2018/11/21.
 */
@Service
public class SellerGoodsItemCatServiceImpl implements ISellerGoodsItemCatService {

    @Autowired
    private TbItemCatMapper tbItemCatMapper;

    @Autowired
    private TbTypeTemplateMapper tbTypeTemplateMapper;

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

    @Override
    public void save(ItemCatVo itemCatVo) {
        tbItemCatMapper.insert(vo2pojo(itemCatVo));
    }

    @Override
    public ItemCatVo findOneById(Long id) {
        TbItemCat tbItemCat = tbItemCatMapper.selectByPrimaryKey(id);
        //构建 ItemCatVo
        ItemCatVo itemCatVo = new ItemCatVo();
        itemCatVo.setParentId(tbItemCat.getParentId());
        itemCatVo.setItemCatName(tbItemCat.getName());
        itemCatVo.setId(tbItemCat.getId());

        //查询模版ID //下拉列表
        Map<String,Object> tempTypeSelect = tbTypeTemplateMapper.queryTempTypeSelect(tbItemCat.getTypeId());
        String tempSelectList = JSON.toJSONString(tempTypeSelect);
        itemCatVo.setTempTypeId(tempSelectList);
        return itemCatVo;
    }

    @Override
    public void modify(ItemCatVo itemCatVo) {
        tbItemCatMapper.updateByPrimaryKey(vo2pojo(itemCatVo));
    }

    @Override
    public void del(String[] ids) {
        List<Long> idList = Lists.newArrayList();
        for (String id: ids) {
            idList.add(Long.valueOf(id));
        }
        tbItemCatMapper.delInbatch(idList);
    }

    @Override
    public List<TbItemCat> findAll() {
        return tbItemCatMapper.selectAll();
    }

    private TbItemCat vo2pojo(ItemCatVo itemCatVo) {
        TbItemCat tbItemCat = new TbItemCat();
        tbItemCat.setParentId(itemCatVo.getParentId());
        tbItemCat.setName(itemCatVo.getItemCatName());
        tbItemCat.setId(itemCatVo.getId());

        //解析模版ID
        Map<String,Object> tempType = JSON.parseObject(itemCatVo.getTempTypeId(),Map.class);
        Long tempTypeId = Long.parseLong(tempType.get("id").toString());
        tbItemCat.setTypeId(tempTypeId);
        return tbItemCat;
    }


}
