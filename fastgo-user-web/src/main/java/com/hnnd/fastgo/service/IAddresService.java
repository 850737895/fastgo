package com.hnnd.fastgo.service;

import com.hnnd.fastgo.entity.TbCities;
import com.hnnd.fastgo.entity.TbProvinces;
import com.hnnd.fastgo.vo.SystemVo;

import java.util.List;

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


}
