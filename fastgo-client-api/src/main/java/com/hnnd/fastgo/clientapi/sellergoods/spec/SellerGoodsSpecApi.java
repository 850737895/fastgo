package com.hnnd.fastgo.clientapi.sellergoods.spec;

import com.hnnd.fastgo.entity.TbSpecification;
import com.hnnd.fastgo.vo.PageResultVo;
import com.hnnd.fastgo.vo.SpecVo;
import com.hnnd.fastgo.vo.SystemVo;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;


/**
 *
 * Created by Administrator on 2018/11/16.
 */
@FeignClient(name ="fastgo-sellergoods-service",fallback = SellerGoodsSpecImpl.class, path = "/sellerGoods/spec")
public interface SellerGoodsSpecApi {

    @RequestMapping("/list")
    public PageResultVo<TbSpecification> list(@RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize,
                                                    @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                                    @RequestParam(value = "queryCondition",required = false) String queryCondition);

    @RequestMapping("/save")
    public SystemVo saveSpec(@RequestBody SpecVo specVo);

    @RequestMapping("/modify")
    public SystemVo modifySpec(@RequestBody SpecVo specVo);

    @RequestMapping("/findOne/{specId}")
    public SystemVo<SpecVo> findOne(@PathVariable("specId") Long specId);


    @RequestMapping("/delSpecBySpecId")
    public SystemVo delSpecBySpecId(@RequestParam("specIds") String[] specIds);

    @RequestMapping("/initSpecList")
    public List<Map<String,Object>> initSpecList();

    @RequestMapping("/specOpsList/{typeTempId}")
    public List<SpecVo> findSpecOpsByTypeTempId(@PathVariable("typeTempId") Long typeTempId);

}