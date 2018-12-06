package com.hnnd.fastgo.service.impl;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.hnnd.fastgo.constant.RedisConstant;
import com.hnnd.fastgo.dao.TbContentMapper;
import com.hnnd.fastgo.entity.TbContent;
import com.hnnd.fastgo.service.IContentService;
import com.hnnd.fastgo.vo.PageResultVo;
import com.redisoper.IRedisService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 广告接口实现类
 * Created by Administrator on 2018/12/5.
 */
@Service
@Slf4j
public class ContentServiceImpl implements IContentService {

    @Autowired
    private TbContentMapper tbContentMapper;

    @Autowired
    private IRedisService redisServiceImpl;

    @Override
    public PageResultVo<TbContent> list4Page(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<TbContent> tbContentList = tbContentMapper.selectAll();
        PageInfo<TbContent> pageInfo = new PageInfo<>(tbContentList);
        return new PageResultVo<>(pageInfo.getTotal(),pageInfo.getList());
    }

    @Override
    public void save(TbContent content) {
        //第一步 先保存到数据库
        tbContentMapper.insert(content);
        //该方法类中进行异常处理(缓存服务器挂了，但是业务系统需要能够正常的保存);
        pushObj2Cache(content);

    }


    public List<TbContent> findListByCategoryId(Long categoryId) {

        //先去缓存中查询
        Map<String,String> cacheMap = redisServiceImpl.hgetAll(RedisConstant.CONTENT_KEY+categoryId);
        //缓存中有
        if(!cacheMap.isEmpty()) {
            log.info("从缓存中,通过广告类别ID加载的广告列表:{}",JSON.toJSONString(cacheMap));
            return getTbContentList4CacheMap(cacheMap);
        }
        //缓存没有去db中查询
        List<TbContent> tbContentList = tbContentMapper.selectListByCategoryId(categoryId );
        if(!tbContentList.isEmpty()) {
            for (TbContent tbContent:tbContentList) {
                pushObj2Cache(tbContent);
            }
        }
        return tbContentList;
    }

    @Override
    public TbContent findOneById(Long id) {
        String categoryId=null;
        try {
             categoryId = redisServiceImpl.get(RedisConstant.CONTENTID_MAPPING_CATEGORYID+id);
        } catch (Exception e) {
            log.error("在缓存中通过id查找映射的categoryId异常:{}",e);
        }

        if(!StringUtils.isEmpty(categoryId)) {
            //有该id的映射
            String tbContentJson = redisServiceImpl.hget(RedisConstant.CONTENT_KEY+categoryId,RedisConstant.CONTENT_FIELD_PREFIX+id);
            if(StringUtils.isEmpty(tbContentJson)) { //缓存中获取对象异常
                TbContent tbContent = tbContentMapper.selectByPrimaryKey(id);
                pushObj2Cache(tbContent);
                return tbContent;
            }else{//
                return JSON.parseObject(tbContentJson,TbContent.class);
            }
        }else{//没有id映射
            TbContent tbContent = tbContentMapper.selectByPrimaryKey(id);
            pushObj2Cache(tbContent);
            return tbContent;
        }

    }

    /**
     * 从缓存map中获取广告列表
     * @param cahceMap 缓存中加载出来的map
     * @return List<TbContent>
     */
    private List<TbContent> getTbContentList4CacheMap(Map<String,String> cahceMap) {
        List<TbContent> tbContentList = Lists.newArrayList();
        Set<String> mapkeys = cahceMap.keySet();
        Iterator<String> stringIterator = mapkeys.iterator();
        while (stringIterator.hasNext()) {
            String mapKey = stringIterator.next();
            String mapValue = cahceMap.get(mapKey);
            TbContent tbContent = JSON.parseObject(mapValue,TbContent.class);
            tbContentList.add(tbContent);
        }
        return tbContentList;
    }

    /**
     * 把数据加载到缓存
     * @param tbContent 广告对象
     */
    private void pushObj2Cache(TbContent tbContent) {
        try {
            //第二步加载到缓存中
            String contentJson = JSON.toJSONString(tbContent);
            //缓存数据
            redisServiceImpl.hset(RedisConstant.CONTENT_KEY+tbContent.getCategoryId(),RedisConstant.CONTENT_FIELD_PREFIX+tbContent.getId(),contentJson);
            //缓存contentId 和 categoryID的映射关系
            redisServiceImpl.set(RedisConstant.CONTENTID_MAPPING_CATEGORYID+tbContent.getId(),tbContent.getCategoryId()+"");
        } catch (Exception e) {
            log.warn("为了保证缓存服务器挂了还是能够保存业务数据:{},异常为:{}",tbContent,e);
        }
    }

}
