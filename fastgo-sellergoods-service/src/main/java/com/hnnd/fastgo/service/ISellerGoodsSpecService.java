package com.hnnd.fastgo.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hnnd.fastgo.entity.TbBrand;
import com.hnnd.fastgo.entity.TbSpecification;
import com.hnnd.fastgo.vo.PageResultVo;
import com.hnnd.fastgo.vo.SpecVo;
import com.hnnd.fastgo.vo.SystemVo;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


/**
 * 规格服务
 * Created by Administrator on 2018/11/16.
 */
public interface ISellerGoodsSpecService {

    /**
     * 查询规格分页
     * @param pageNum 当前页
     * @param pageSize 每页条数
     * @param queryCondition 查询条件
     * @return  PageResultVo<TbBrand>
     */
    PageResultVo<TbSpecification> selectAllByPage(Integer pageNum, Integer pageSize, String queryCondition);

    SystemVo saveSpec( SpecVo specVo);

    /**
     * 修改商品信息
     * @param specVo 商品vo
     * @return SystemVo
     */
    SystemVo modifySpec( SpecVo specVo);

    SpecVo findOne(Long specId);

    /**
     * 删除商品规格
     * @param specIds 规格id
     */
    void delSpecBySpecId(@RequestParam("specIds") String[] specIds);

}
