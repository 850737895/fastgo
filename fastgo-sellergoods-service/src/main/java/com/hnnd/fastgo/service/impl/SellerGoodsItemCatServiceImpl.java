package com.hnnd.fastgo.service.impl;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.hnnd.fastgo.constant.RedisConstant;
import com.hnnd.fastgo.dao.TbItemCatMapper;
import com.hnnd.fastgo.dao.TbTypeTemplateMapper;
import com.hnnd.fastgo.entity.TbItemCat;
import com.hnnd.fastgo.service.ISellerGoodsItemCatService;
import com.hnnd.fastgo.vo.ItemCatVo;
import com.hnnd.fastgo.vo.PageResultVo;
import com.redisoper.IRedisService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 商品类目服务
 * Created by Administrator on 2018/11/21.
 */
@Service
@Slf4j
public class SellerGoodsItemCatServiceImpl implements ISellerGoodsItemCatService {

    @Autowired
    private TbItemCatMapper tbItemCatMapper;

    @Autowired
    private TbTypeTemplateMapper tbTypeTemplateMapper;
    @Autowired
    private IRedisService redisServiceImpl;


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
        //加载商品分类列表到缓存
        loadItemCateList2Redis();
        return tbItemCatList;
    }


    /**
     * 加载商品分类到缓存中
     */
    private void loadItemCateList2Redis() {
        long beginTime = System.currentTimeMillis();
        List<TbItemCat> itemCatList = tbItemCatMapper.selectAll();
        List<Map<String,String>> listMap = Lists.newArrayList();
        for(TbItemCat tbItemCat:itemCatList) {
            Map<String,String> map = Maps.newHashMap();
            map.put(tbItemCat.getName(),tbItemCat.getTypeId()+"");
            listMap.add(map);
        }
        redisServiceImpl.hmsetWithBatch(RedisConstant.ITEMCATE_LIST_KEY,listMap);
        long endTime = System.currentTimeMillis();
        log.info("加载到缓存耗时:{}",(endTime-beginTime));
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
