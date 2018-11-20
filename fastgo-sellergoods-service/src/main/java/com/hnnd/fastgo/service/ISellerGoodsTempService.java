package com.hnnd.fastgo.service;

import com.hnnd.fastgo.entity.TbTypeTemplate;
import com.hnnd.fastgo.vo.PageResultVo;
import com.hnnd.fastgo.vo.SystemVo;
import com.hnnd.fastgo.vo.TemplateTypeVo;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * 商品模版接口
 * Created by Administrator on 2018/11/20.
 */
public interface ISellerGoodsTempService {

    /**
     * 分页查询
     * @param pageNum 当前页
     * @param pageSize 每页条数
     * @param qryConditon 查询条件
     * @return  PageResultVo<TbTypeTemplate>
     */
    PageResultVo<TbTypeTemplate> list4Page(Integer pageNum,Integer pageSize,String qryConditon );

    /**
     * 保存商品模版
     * @param templateTypeVo 模版实体类
     * @return SystemVo
     */
     void save( TemplateTypeVo templateTypeVo);

    /**
     * 根据模版id 查询模版信息
     * @param id 模版id
     * @return TbTypeTemplate
     */
     TbTypeTemplate findOne(Long id);

    /**
     * 更新模版信息
     * @param tbTypeTemplate 实体对象
     * @return 更新数据库成功的行数
     */
     int modify(TbTypeTemplate tbTypeTemplate);

    /**
     * 批量删除模版信息
     * @param ids id集合
     */
     void del(List<Long> ids);
}
