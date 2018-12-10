package com.hnnd.fastgo.service.impl;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hnnd.fastgo.constant.RedisConstant;
import com.hnnd.fastgo.dao.TbSpecificationOptionMapper;
import com.hnnd.fastgo.dao.TbTypeTemplateMapper;
import com.hnnd.fastgo.entity.TbSpecificationOption;
import com.hnnd.fastgo.entity.TbTypeTemplate;
import com.hnnd.fastgo.service.ISellerGoodsTempService;
import com.hnnd.fastgo.vo.PageResultVo;
import com.hnnd.fastgo.vo.TemplateTypeVo;
import com.redisoper.IRedisService;
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

    @Autowired
    private TbSpecificationOptionMapper tbSpecificationOptionMapper;

    @Autowired
    private IRedisService redisServiceImpl;

    @Override
    public PageResultVo<TbTypeTemplate> list4Page(Integer pageNum, Integer pageSize, String qryConditon) {
        PageHelper.startPage(pageNum,pageSize);
        List<TbTypeTemplate> tbTypeTemplates = tbTypeTemplateMapper.selectPageByCondition(qryConditon);
        PageInfo<TbTypeTemplate> pageInfo = new PageInfo<>(tbTypeTemplates);
        //加载缓存
        load2Redis();

        return new PageResultVo<>(pageInfo.getTotal(),pageInfo.getList());
    }

    //加载到缓存
    public void load2Redis() {
        //加载所有的模版
        List<TbTypeTemplate> tbTypeTemplates = tbTypeTemplateMapper.selectAll();
        for (TbTypeTemplate tbTypeTemplate:tbTypeTemplates) {
            List<Map> brandList = JSON.parseArray(tbTypeTemplate.getBrandIds(), Map.class);
            String brandListStr = JSON.toJSONString(brandList);
            redisServiceImpl.hset(RedisConstant.TEMPLATE_KEY,RedisConstant.TEMPLATE_BRAND_KEY+":"+tbTypeTemplate.getId()+"",brandListStr);

            List<Map> specList = getSpecList(tbTypeTemplate);
            redisServiceImpl.hset(RedisConstant.TEMPLATE_KEY,RedisConstant.TEMPLATE_SPEC_KEY+":"+tbTypeTemplate.getId(),JSON.toJSONString(specList));
        }
    }

    /**
     * 通过模版获取规格列表
     * @param tbTypeTemplate 模版选项
     * @return List<TbSpecification>
     */
    private List<Map> getSpecList(TbTypeTemplate tbTypeTemplate)  {
        //[{"id":38,"text":"内存条"},{"id":48,"text":"CPU型号"}]
        List<Map> specList = JSON.parseArray(tbTypeTemplate.getSpecIds(), Map.class);
        for(Map specMap:specList) {
            String specId = specMap.get("id").toString();
            //查询规格选项
            List<TbSpecificationOption> tbSpecificationOptionList = tbSpecificationOptionMapper.selectBySpecId(Long.parseLong(specId));
            specMap.put("options",tbSpecificationOptionList);
        }
        return specList;
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
