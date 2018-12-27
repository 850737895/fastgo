package com.hnnd.fastgo.service.impl;

import com.alibaba.druid.support.json.JSONUtils;
import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.hnnd.fastgo.constant.RedisConstant;
import com.hnnd.fastgo.constant.SysConst;
import com.hnnd.fastgo.dao.TbAddressMapper;
import com.hnnd.fastgo.dao.TbAreasMapper;
import com.hnnd.fastgo.dao.TbCitiesMapper;
import com.hnnd.fastgo.dao.TbProvincesMapper;
import com.hnnd.fastgo.entity.TbAddress;
import com.hnnd.fastgo.entity.TbAreas;
import com.hnnd.fastgo.entity.TbCities;
import com.hnnd.fastgo.entity.TbProvinces;
import com.hnnd.fastgo.service.IAddresService;
import com.redisoper.IRedisService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.SetUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.*;

/**
 * 收货地址服务层接口实现
 * Created by 85073 on 2018/12/26.
 */
@Service
@Slf4j
public class AddresServiceImpl implements IAddresService {

    @Autowired
    private TbProvincesMapper tbProvincesMapper;

    @Autowired
    private IRedisService redisServiceImpl;

    @Autowired
    private TbCitiesMapper tbCitiesMapper;

    @Autowired
    private TbAreasMapper tbAreasMapper;

    @Autowired
    private TbAddressMapper tbAddressMapper;

    @PostConstruct
    public void initCity() {
        try{
            if(redisServiceImpl.setnx(SysConst.FAST_GO_ADDRESS_LOCK,"0")==1){
                log.info("开始初始化address信息");
                List<TbCities> tbCitiesList = tbCitiesMapper.selectAll();
                List<TbAreas> tbAreasList = tbAreasMapper.selectAll();
                Set<String> provinceIds = Sets.newHashSet();
                Set<String> cityIds = Sets.newHashSet();

                //提取所有的省份id
                for (TbCities tbCities:tbCitiesList) {
                    provinceIds.add(tbCities.getProvinceid());
                    cityIds.add(tbCities.getCityid());
                }
                //缓存城市
                initMapping(provinceIds,tbCitiesList,TbCities.class);
                //缓存区域
                initMapping(cityIds,tbAreasList,TbAreas.class);
            }
        }catch (Exception e) {
            log.error("初始address缓存出错");
            redisServiceImpl.expire(SysConst.FAST_GO_ADDRESS_LOCK,0);
        }

    }

    @Override
    public List<TbProvinces> initProvinceList() {
        Map<String,String> provincesMap = redisServiceImpl.hgetAll(RedisConstant.PROVINCES_LIST_KEY+":"+SysConst.PROVINCES_LIST);

        if(provincesMap==null || provincesMap.isEmpty()) {//缓存中没有找到
            List<TbProvinces> tbProvincesList = tbProvincesMapper.selectAll();
            List<Map<String,String>> provinceList = Lists.newArrayList();
            for(TbProvinces tbProvinces:tbProvincesList) {
                Map<String,String> provinceMap = Maps.newHashMap();
                provinceMap.put(tbProvinces.getProvinceid(),JSON.toJSONString(tbProvinces));
                provinceList.add(provinceMap);
            }
            redisServiceImpl.hmsetWithBatch(RedisConstant.PROVINCES_LIST_KEY+":"+SysConst.PROVINCES_LIST,provinceList);
            return tbProvincesList;
        }else{//缓存中有
            List<TbProvinces> tbProvincesList = loadList4CacheMap(provincesMap,TbProvinces.class);
            return tbProvincesList;

        }

    }

    @Override
    public List<TbCities> selectAllCitesByProvinceId(String provinceId) {
        //从redis中加载
        Map<String,String> cityMap =  redisServiceImpl.hgetAll(RedisConstant.PROVINCES_LIST_KEY+":"+SysConst.PROVINCE_MAPPING_CITY+":"+provinceId);
        //缓存中没有
        if(cityMap==null ||cityMap.size()==0) {
            List<TbCities> tbCitiesList = tbCitiesMapper.selectCityListByProvinceId(provinceId);
            initMapping(Sets.newHashSet(provinceId),tbCitiesList, TbCities.class);
            return tbCitiesList;
        }else {//缓存中有
            List<TbCities> tbCitiesList = loadList4CacheMap(cityMap,TbCities.class);
            return tbCitiesList;
        }
    }

    @Override
    public List<TbAreas> selectAllAreaByCityId(String cityId) {

        Map<String,String> cacheMap = redisServiceImpl.hgetAll(RedisConstant.PROVINCES_LIST_KEY+":"+SysConst.CITY_MAPPING_AREA+":"+cityId);
        if(cacheMap==null || cacheMap.isEmpty()) {
            List<TbAreas> tbAreasList = tbAreasMapper.selectTbAreaListByCityId(cityId);
            initMapping(Sets.newHashSet(cityId),tbAreasList, TbAreas.class);
            return tbAreasList;
        }else{
            List<TbAreas> tbAreasList = loadList4CacheMap(cacheMap,TbAreas.class);
            return tbAreasList;
        }
    }

    @Transactional
    @Override
    public void saveAddress(TbAddress tbAddress) {
        tbAddress.setCreateDate(new Date());
        tbAddressMapper.insert(tbAddress);
        //是默认地址需要把 其他的默认地址设置为非默认的
        if(SysConst.IS_DEFAULT_ADDRESS.equals(tbAddress.getIsDefault())) {
            long addressId = tbAddress.getId();
            tbAddressMapper.updateOtherDefaultAddress(addressId,tbAddress.getUserId());
        }
    }

    @Override
    public List<Map<String,Object>> selectAddressList(String loginUserName) {
        List<TbAddress> tbAddressList = tbAddressMapper.selectAddressListByUserId(loginUserName);
        List<Map<String,Object>> listMap = Lists.newArrayList();
        for(TbAddress tbAddress:tbAddressList) {
            Map<String,Object> temp = JSON.parseObject(JSON.toJSONString(tbAddress),Map.class);
            //根据id解析省份的名称

            temp.put("provinceName",parseAddressPropById(tbAddress,TbProvinces.class));

            //根据城市Id 解析城市的名称

            temp.put("cityName",parseAddressPropById(tbAddress,TbCities.class));

            //根据地区id解析地区的名称

            temp.put("areaName",parseAddressPropById(tbAddress,TbAreas.class));
            listMap.add(temp);
        }
        return listMap;
    }

    @Override
    public void deleteById(Long id) {
        tbAddressMapper.deleteByPrimaryKey(id);
    }

    @Override
    public TbAddress findOne(Long addressId, String loginUserName) {
        return tbAddressMapper.findOneByIdAndUserId(addressId,loginUserName);
    }

    @Override
    public void modifyAddress(TbAddress tbAddress) {
        tbAddressMapper.updateByIdAndLoginUserName(tbAddress);
        if(SysConst.IS_DEFAULT_ADDRESS.equals(tbAddress.getIsDefault())) {
            long addressId = tbAddress.getId();
            tbAddressMapper.updateOtherDefaultAddress(addressId,tbAddress.getUserId());
        }

    }


    /**
     * 根据id解析名称
     * @param clazz 解析的类型
     * @param <T> 解析的名称
     * @return
     */
    private  <T> String parseAddressPropById(TbAddress tbAddress,Class<T> clazz) {
        if(clazz == TbProvinces.class) {//解析省份
            String provinceJsonStr= redisServiceImpl.hget(RedisConstant.PROVINCES_LIST_KEY+":"+SysConst.PROVINCES_LIST,tbAddress.getProvinceId());
            TbProvinces tbProvinces = JSON.parseObject(provinceJsonStr,TbProvinces.class);
            return tbProvinces.getProvince();
        }else if(clazz == TbCities.class){//解析城市
            String cityStr = redisServiceImpl.hget(RedisConstant.PROVINCES_LIST_KEY+":"+SysConst.PROVINCE_MAPPING_CITY+":"+tbAddress.getProvinceId(),tbAddress.getCityId());
            TbCities tbCities = JSON.parseObject(cityStr,TbCities.class);
            return tbCities.getCity();
        }else{//解析区域
            String areaStr = redisServiceImpl.hget(RedisConstant.PROVINCES_LIST_KEY+":"+SysConst.CITY_MAPPING_AREA+":"+tbAddress.getCityId(),tbAddress.getTownId());
            TbAreas tbAreas = JSON.parseObject(areaStr,TbAreas.class);
            return tbAreas.getArea();
        }
    }


    /**
     * 从缓存中加载城市列表
     * @param cacheMap 缓存中的map
     * @return
     */
    private <T> List<T>  loadList4CacheMap(Map<String,String> cacheMap,Class<T> clazz) {
        List<T> retList = Lists.newArrayList();
        Set<String> cacheMapKey = cacheMap.keySet();
        Iterator<String> iterator = cacheMapKey.iterator();
        while (iterator.hasNext()) {
            String key = iterator.next();
            String valueJson = JSON.toJSON(cacheMap.get(key)).toString();
            T t = JSON.parseObject(valueJson,clazz);
            retList.add(t);
        }
        return retList;
    }

    private <T> void initMapping(Set<String> ids,List<T> list,Class clazz) {
        for(String id:ids) {
            List<Map<String,String>> loadList = Lists.newArrayList();
            loadCacheList(list,id,loadList,clazz);
            if(clazz == TbCities.class) {
                redisServiceImpl.hmsetWithBatch(RedisConstant.PROVINCES_LIST_KEY+":"+SysConst.PROVINCE_MAPPING_CITY+":"+id,loadList);
            }else if(clazz == TbAreas.class) {
                redisServiceImpl.hmsetWithBatch(RedisConstant.PROVINCES_LIST_KEY+":"+SysConst.CITY_MAPPING_AREA+":"+id,loadList);
            }
        }
    }

    /**
     * 处理省份映射城市
     * @param provinceId 省份
     * @param tbCities 城市对象
     * @param loadList 加载到缓存中的对象
     */
    private  void dealCity(String provinceId,TbCities tbCities, List<Map<String,String>> loadList) {
        if(tbCities.getProvinceid().equals(provinceId)) {
            Map<String,String> cityMap = Maps.newHashMap();
            cityMap.put(tbCities.getCityid(),JSON.toJSONString(tbCities));
            loadList.add(cityMap);
        }
    }

    private <T> void loadCacheList(List<T> dbList,String id,List<Map<String,String>> loadList,Class clazz){
        for( T t:dbList) {
            if(clazz == TbCities.class) {
                TbCities tbCities = (TbCities) t;
                dealCity(id,tbCities,loadList);
            }else if(clazz == TbAreas.class) {
                TbAreas tbAreas = (TbAreas) t;
                dealArea(id,tbAreas,loadList);
            }
        }
    }

    /**
     * 处理区域对象
     * @param cityId 城市id
     * @param tbAreas 地区
     * @param loadList  加载到缓存中的对象
     */
    private void dealArea(String cityId,TbAreas tbAreas,List<Map<String,String>> loadList) {
        if(cityId.equals(tbAreas.getCityid())){
            Map<String,String> areaMap = Maps.newHashMap();
            areaMap.put(tbAreas.getAreaid(),JSON.toJSONString(tbAreas));
            loadList.add(areaMap);
        }
    }

}
