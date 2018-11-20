package com.hnnd.fastgo.clientapi.sellergoods.typetemplate;

import com.hnnd.fastgo.entity.TbTypeTemplate;
import com.hnnd.fastgo.vo.PageResultVo;
import com.hnnd.fastgo.vo.SystemVo;
import com.hnnd.fastgo.vo.TemplateTypeVo;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 类型模版
 * Created by Administrator on 2018/11/20.
 */
@FeignClient(name = "fastgo-sellergoods-service",fallback =SellerGoodsTempImpl.class,path = "/sellerGoods/temp")
public interface SellerGoodsTempApi {

    /**
     * 分页查询列表
     * @param pageNum 当前页码数
     * @param pageSize 每页的条数
     * @param qryCondition 查询条件
     * @return PageResultVo<TbTypeTemplate>
     */
    @RequestMapping("/list")
    public PageResultVo<TbTypeTemplate> list(@RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum,
                                                       @RequestParam(value = "pageSize",defaultValue = "10")Integer pageSize,
                                                       @RequestParam(value = "qryCondition",required = false) String qryCondition);

    /**
     * 保存商品模版
     * @param templateTypeVo 商品模版vo
     * @return SystemVo
     */
    @RequestMapping("/save")
    public SystemVo save(@RequestBody TemplateTypeVo templateTypeVo);

    @RequestMapping("/findOne/{id}")
    public SystemVo<TbTypeTemplate> findOne(@PathVariable("id") Long id);

    @RequestMapping("/modify")
    public SystemVo modify(@RequestBody TemplateTypeVo templateTypeVo);


    @RequestMapping("/del")
    public SystemVo del(@RequestParam("ids") String[] ids);
}
