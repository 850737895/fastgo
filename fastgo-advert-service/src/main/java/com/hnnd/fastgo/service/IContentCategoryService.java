package com.hnnd.fastgo.service;

import com.hnnd.fastgo.entity.TbContent;
import com.hnnd.fastgo.entity.TbContentCategory;
import com.hnnd.fastgo.vo.PageResultVo;
import com.hnnd.fastgo.vo.SystemVo;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * 广告分类接口
 * Created by Administrator on 2018/12/5.
 */
public interface IContentCategoryService {
    /**
     * 分页查询广告分类管理
     * @param pageNum 当前页
     * @param pageSize 每页多少条
     * @param contentCategoryName 查询条件
     * @return PageResultVo<TbContentCategory>
     */
    PageResultVo<TbContentCategory> list( Integer pageNum, Integer pageSize, String contentCategoryName) ;

    /**
     * 保存广告类别信息
     * @param tbContentCategory  广告类别信息
     */
    void save( TbContentCategory tbContentCategory);

    void del(Long[] ids);

    void modify(TbContentCategory tbContentCategory);

    TbContentCategory findOneById(Long id);

    List<TbContentCategory> findAll();


}
