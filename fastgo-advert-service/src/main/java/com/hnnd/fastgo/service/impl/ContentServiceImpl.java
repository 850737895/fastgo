package com.hnnd.fastgo.service.impl;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.hnnd.fastgo.constant.FastGoContant;
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

import javax.annotation.PostConstruct;
import java.util.*;

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

    /**
     *
     */
    @PostConstruct
    public void initAllContent() {
        List<TbContent> tbContents = tbContentMapper.selectAll();
        log.info("初始化广告缓存:{}",tbContents);
        if(tbContents!=null) {
            for (TbContent tbContent:tbContents) {
                delObj4Cache(tbContent.getId(),tbContent.getCategoryId()+"");
                pushObj2Cache(tbContent);
            }
        }
    }

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
        if(cacheMap==null) {
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
        categoryId = redisServiceImpl.get(RedisConstant.CONTENTID_MAPPING_CATEGORYID+id);

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

    @Override
    public void modify(TbContent tbContent) {
        //更新数据库
        tbContentMapper.updateByPrimaryKey(tbContent);

        //去缓存中尝试获取老的映射
        String oldCategoryId = redisServiceImpl.get(RedisConstant.CONTENTID_MAPPING_CATEGORYID+tbContent.getId());

        //修改过广告的分类
        if(!oldCategoryId.isEmpty()&&!(oldCategoryId.equals(tbContent))) {
            //需要删除老的
            delObj4Cache(tbContent.getId(),oldCategoryId);
        }
        //更新缓存
        pushObj2Cache(tbContent);
    }

    @Override
    public void del(Long[] ids) {
        List<Long> idList = Arrays.asList(ids);
        tbContentMapper.deleteInBatch(idList);
        //从缓存中移除
        for(Long id:ids) {
            String categoryId = redisServiceImpl.get(RedisConstant.CONTENTID_MAPPING_CATEGORYID+id);
            delObj4Cache(id,categoryId);
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
            //有效的新闻才能显示
            if(FastGoContant.CONTENT_STATUS_1.equals(tbContent.getStatus())) {
                tbContentList.add(tbContent);
            }
        }
        return tbContentList;
    }

    /**
     * 把数据加载到缓存
     * @param tbContent 广告对象
     */
    private void pushObj2Cache(TbContent tbContent) {

        //第二步加载到缓存中
        String contentJson = JSON.toJSONString(tbContent);
        //缓存数据
        redisServiceImpl.hset(RedisConstant.CONTENT_KEY+tbContent.getCategoryId(),RedisConstant.CONTENT_FIELD_PREFIX+tbContent.getId(),contentJson);
        //缓存contentId 和 categoryID的映射关系
        redisServiceImpl.set(RedisConstant.CONTENTID_MAPPING_CATEGORYID+tbContent.getId(),tbContent.getCategoryId()+"");
    }

    private void delObj4Cache(long id,String categoryId) {
        redisServiceImpl.del(RedisConstant.CONTENTID_MAPPING_CATEGORYID+id);
        redisServiceImpl.hdel(RedisConstant.CONTENT_KEY+categoryId,RedisConstant.CONTENT_FIELD_PREFIX+id);
    }

}
