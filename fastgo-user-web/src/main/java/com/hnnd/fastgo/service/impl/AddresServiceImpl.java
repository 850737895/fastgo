package com.hnnd.fastgo.service.impl;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.hnnd.fastgo.constant.RedisConstant;
import com.hnnd.fastgo.constant.SysConst;
import com.hnnd.fastgo.dao.TbAreasMapper;
import com.hnnd.fastgo.dao.TbCitiesMapper;
import com.hnnd.fastgo.dao.TbProvincesMapper;
import com.hnnd.fastgo.entity.TbAreas;
import com.hnnd.fastgo.entity.TbCities;
import com.hnnd.fastgo.entity.TbProvinces;
import com.hnnd.fastgo.service.IAddresService;
import com.redisoper.IRedisService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 收货地址服务层接口实现
 * Created by 85073 on 2018/12/26.
 */
@Service
@Slf4j
public class AddresServiceImpl implements IAddresService{

    @Autowired
    private TbProvincesMapper tbProvincesMapper;

    @Autowired
    private IRedisService redisServiceImpl;

    @Autowired
    private TbCitiesMapper tbCitiesMapper;

    @Autowired
    private TbAreasMapper tbAreasMapper;

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
                initProvinceMappingCity(provinceIds,tbCitiesList);

                //缓存区域
                initCityMappingArea(cityIds,tbAreasList);
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
            List<TbProvinces> tbProvincesList = Lists.newArrayList();
            Set<String> keySet = provincesMap.keySet();
            Iterator<String> iterator = keySet.iterator();
            while (iterator.hasNext()) {
                String key = iterator.next();
                TbProvinces tbProvinces = JSON.parseObject(JSON.toJSON(provincesMap.get(key)).toString(),TbProvinces.class);
                tbProvincesList.add(tbProvinces);
            }
            return tbProvincesList;

        }

    }

    private void initProvinceMappingCity( Set<String> provinceIds,List<TbCities> tbCitiesList) {
        //分组缓存城市
        for(String provinceId:provinceIds) {
            List<Map<String,String>> tbCitiesListGroup = Lists.newArrayList();
            for(TbCities tbCities:tbCitiesList) {
                if(tbCities.getProvinceid().equals(provinceId)) {
                    Map<String,String> cityMap = Maps.newHashMap();
                    cityMap.put(tbCities.getCityid(),JSON.toJSONString(tbCities));
                    tbCitiesListGroup.add(cityMap);
                }
            }
            redisServiceImpl.hmsetWithBatch(RedisConstant.PROVINCES_LIST_KEY+":"+SysConst.PROVINCE_MAPPING_CITY+":"+provinceId,tbCitiesListGroup);
        }
    }

    private void initCityMappingArea(Set<String> cityIds,List<TbAreas> tbAreasList){
        //分组缓存地区
        for(String cityId:cityIds) {
            List<Map<String,String>> tbAreaList = Lists.newArrayList();
            for(TbAreas tbAreas:tbAreasList) {
                if(cityId.equals(tbAreas.getCityid())){
                    Map<String,String> areaMap = Maps.newHashMap();
                    areaMap.put(tbAreas.getAreaid(),JSON.toJSONString(tbAreas));
                    tbAreaList.add(areaMap);
                }
            }
            redisServiceImpl.hmsetWithBatch(RedisConstant.PROVINCES_LIST_KEY+":"+SysConst.CITY_MAPPING_AREA+":"+cityId,tbAreaList);
        }
    }

}
