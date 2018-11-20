package com.hnnd.fastgo.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hnnd.fastgo.entity.TbBrand;
import com.hnnd.fastgo.entity.TbSpecification;
import com.hnnd.fastgo.vo.PageResultVo;
import com.hnnd.fastgo.vo.SpecVo;
import com.hnnd.fastgo.vo.SystemVo;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Map;


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

    SpecVo findOne(Long specId);

    /**
     * 用于加载select2组件的 规格下拉列表
     * @return  List<Map<String,Object>>
     */
    List<Map<String,Object>> initSpecList();
}
