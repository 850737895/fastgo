package com.hnnd.fastgo.service;

import com.hnnd.fastgo.entity.TbAddress;
import com.hnnd.fastgo.entity.TbAreas;
import com.hnnd.fastgo.entity.TbCities;
import com.hnnd.fastgo.entity.TbProvinces;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 地址服务层借口
 * Created by 85073 on 2018/12/26.
 */
public interface IAddresService {

    /**
     * 初始化加载省份列表到缓存中
     * @return
     */
    List<TbProvinces> initProvinceList();

    /**
     * 根据省份id 查询该省份下的所有城市
     * @param provinceId 省份id
     * @return 城市列表
     */
    List<TbCities> selectAllCitesByProvinceId(String provinceId);


    /**
     * 根据城市id 查询该城市下的所有地区列表
     * @param cityId 城市id
     * @return 区域列表
     */
    List<TbAreas> selectAllAreaByCityId(String cityId);

    /**
     * 保存收货地址
     * @param tbAddress 入参
     */
    void saveAddress(TbAddress tbAddress);

    /**
     * 根据用户名查询所有的收货地址
     * @param loginUserName 登录用户名
     * @return  List<Map<String,Object>>
     */
     List<Map<String,Object>> selectAddressList(String loginUserName);

    /**
     * 根据地址id删除地址信息
     * @param id 地址id
     */
    void deleteById(Long id);

    TbAddress findOne(Long adderssId,String loginUserName);

    /**
     * 修改地址信息
     * @param tbAddress 地址信息
     */
    void modifyAddress(TbAddress tbAddress);


}

