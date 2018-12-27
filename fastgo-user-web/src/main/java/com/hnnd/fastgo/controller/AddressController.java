package com.hnnd.fastgo.controller;

import com.hnnd.fastgo.entity.TbAddress;
import com.hnnd.fastgo.entity.TbAreas;
import com.hnnd.fastgo.entity.TbCities;
import com.hnnd.fastgo.entity.TbProvinces;
import com.hnnd.fastgo.enumration.SellerGoodsEnum;
import com.hnnd.fastgo.service.IAddresService;
import com.hnnd.fastgo.vo.AddressVo;
import com.hnnd.fastgo.vo.SystemVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import java.util.List;
import java.util.Map;

/**
 * 收货地址服务
 * Created by 85073 on 2018/12/26.
 */
@RestController
@RequestMapping("/address")
@Slf4j
public class AddressController {

    @Autowired
    private IAddresService addresServiceImpl;

    /**
     * 查询省份列表
     * @return SystemVo<List<TbProvinces>>
     */
    @RequestMapping("/initProvince")
    public SystemVo<List<TbProvinces>> initProvinceList() {
        List<TbProvinces> tbProvinces;
        try {
            tbProvinces = addresServiceImpl.initProvinceList();
        } catch (Exception e){
            log.error("初始化省份下拉列表异常");
            return SystemVo.error(SellerGoodsEnum.INIT_PROVINCES_SELECT_LIST_ERROR);
        }
        return SystemVo.success(tbProvinces, SellerGoodsEnum.SELLER_GOODS_SUCCESS);
    }

    /**
     * 根据省份id  加载所属城市下拉列表
     * @param provinceId 省份id
     * @return  SystemVo<List<TbCities>>
     */
    @RequestMapping("/selectCityListByProvinceId")
    public SystemVo<List<TbCities>> selectCityListByProvinceId(@RequestParam("provinceId") String provinceId) {

        if(StringUtils.isEmpty(provinceId)) {
            log.error("城市下拉列表异常");
            return SystemVo.error(SellerGoodsEnum.INIT_ADDRESS_INPARAM_IS_NULL);
        }

        List<TbCities> tbCitiesList;
        try {
            tbCitiesList = addresServiceImpl.selectAllCitesByProvinceId(provinceId);
        } catch (Exception e) {
            log.error("初始化城市下拉列表异常:{}",e);
            return SystemVo.error(SellerGoodsEnum.INIT_CITY_SELECT_LIST_ERROR);
        }
        return SystemVo.success(tbCitiesList,SellerGoodsEnum.SELLER_GOODS_SUCCESS);

    }

    /**
     * 根据城市Id 加载所属区域列表
     * @param cityId 城市id
     * @return  SystemVo<TbAreas>
     */
    @RequestMapping("/selectAreaListByCityId")
    public SystemVo<TbAreas> selectAreaListByCityId(@RequestParam("cityId") String cityId) {

        if(StringUtils.isEmpty(cityId)) {
            log.error("城市下拉列表异常");
            return SystemVo.error(SellerGoodsEnum.INIT_ADDRESS_INPARAM_IS_NULL);
        }
        List<TbAreas> tbAreasList;
        try {
            tbAreasList = addresServiceImpl.selectAllAreaByCityId(cityId);
        } catch (Exception e) {
            log.error("初始化区域列表异常:{}",e);
            return SystemVo.error(SellerGoodsEnum.INIT_AREA_SELECT_LIST_ERROR);
        }
        return SystemVo.success(tbAreasList,SellerGoodsEnum.SELLER_GOODS_SUCCESS);

    }

    /**
     * 保存收货地址
     * @param addressVo 入参
     * @param bindingResult 校验结果
     * @return SystemVo
     */
    @RequestMapping("/saveAddress")
    public SystemVo saveAddress(@Valid @RequestBody AddressVo addressVo, BindingResult bindingResult){
        if(bindingResult.hasErrors()) {
            String fieldErrMsg = bindingResult.getFieldError().getDefaultMessage();
            log.error("注册参数校验异常:{}",fieldErrMsg);
            return  SystemVo.error(SellerGoodsEnum.ADD_ADRESS_INPARAM_NUMM);
        }
        try {
            TbAddress tbAddress = new TbAddress();
            BeanUtils.copyProperties(addressVo,tbAddress);
            tbAddress.setUserId(SecurityContextHolder.getContext().getAuthentication().getName());
            addresServiceImpl.saveAddress(tbAddress);
            return SystemVo.success(SellerGoodsEnum.SELLER_GOODS_SUCCESS);
        } catch (BeansException e) {
            log.error("保存收货地址异常:{}",e);
            return SystemVo.error(SellerGoodsEnum.ADD_ADRESS_ERROR);
        }
    }

    @RequestMapping("/modifyAddress")
    public SystemVo modifyAddress(@Valid @RequestBody AddressVo addressVo, BindingResult bindingResult){
        if(bindingResult.hasErrors()) {
            String fieldErrMsg = bindingResult.getFieldError().getDefaultMessage();
            log.error("注册参数校验异常:{}",fieldErrMsg);
            return  SystemVo.error(SellerGoodsEnum.ADD_ADRESS_INPARAM_NUMM);
        }
        try {
            TbAddress tbAddress = new TbAddress();
            BeanUtils.copyProperties(addressVo,tbAddress);
            tbAddress.setUserId(SecurityContextHolder.getContext().getAuthentication().getName());
            addresServiceImpl.modifyAddress(tbAddress);
            return SystemVo.success(SellerGoodsEnum.SELLER_GOODS_SUCCESS);
        } catch (BeansException e) {
            log.error("保存收货地址异常:{}",e);
            return SystemVo.error(SellerGoodsEnum.ADD_ADRESS_ERROR);
        }
    }



    @RequestMapping("/selectAddressList")
    public SystemVo< List<Map<String,Object>>> selectAddressList() {
        try {
            String loginUserName = SecurityContextHolder.getContext().getAuthentication().getName();
            List<Map<String,Object>> tbAddressList =addresServiceImpl.selectAddressList(loginUserName);
            return SystemVo.success(tbAddressList,SellerGoodsEnum.SELLER_GOODS_SUCCESS);
        } catch (Exception e) {
            log.error("查询收货地址列表异常:{}",e);
            return SystemVo.error(SellerGoodsEnum.QRY_ADDRESS_LIST_ERROR);
        }
    }

    @RequestMapping("/delete/{addressId}")
    public SystemVo deleteAddressById(@PathVariable("addressId") Long adderssId) {
        if(adderssId==null) {
            log.error("传入参数为空");
            return SystemVo.error(SellerGoodsEnum.SELLER_GOODS_ERROR.IN_PARAM_IS_NULL);
        }
        try {
            addresServiceImpl.deleteById(adderssId);
            return SystemVo.success(SellerGoodsEnum.SELLER_GOODS_SUCCESS);
        } catch (Exception e) {
            log.error("根据地址ID:{}删除地址新异常",e);
            return SystemVo.error(SellerGoodsEnum.DEL_ADDRESS_BY_ID_ERROR);
        }
    }

    @RequestMapping("/findOne/{adderssId}")
    public SystemVo<TbAddress> findOne(@PathVariable("adderssId") Long adderssId) {
        if(adderssId==null) {
            log.error("传入参数为空");
            return SystemVo.error(SellerGoodsEnum.SELLER_GOODS_ERROR.IN_PARAM_IS_NULL);
        }
        try {
            String loginUserName = SecurityContextHolder.getContext().getAuthentication().getName();
            TbAddress tbAddress = addresServiceImpl.findOne(adderssId,loginUserName);
            if(null == tbAddress) {
                return SystemVo.error(SellerGoodsEnum.QRY_ADDRESS_BY_ID_ERROR);
            }
            return SystemVo.success(tbAddress,SellerGoodsEnum.SELLER_GOODS_SUCCESS);
        } catch (Exception e) {
            log.error("根据地址ID查询地址信息异常:{}",e);
            return SystemVo.error(SellerGoodsEnum.QRY_ADDRESS_BY_ID_ERROR);
        }

    }
}
