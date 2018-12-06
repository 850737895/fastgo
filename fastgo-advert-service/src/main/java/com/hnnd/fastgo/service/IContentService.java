package com.hnnd.fastgo.service;

import com.hnnd.fastgo.entity.TbContent;
import com.hnnd.fastgo.vo.PageResultVo;

import java.util.List;

/**
 * 广告接口
 * Created by Administrator on 2018/12/5.
 */
public interface IContentService {

    /**
     * 分页查询广告
     * @param pageSize  每页
     * @param pageNum 当前页
     * @return  PageResultVo<TbContent>
     */
    PageResultVo<TbContent> list4Page(Integer pageNum,Integer pageSize);

    void save(TbContent content);

    /**
     * 根据广告类别查询广告
     * @param categoryId 广告类别id
     * @return  List<TbContent>
     */
    List<TbContent> findListByCategoryId(Long categoryId);

    /**
     * 根据id查询广告信息
     * 根据id
     * @param id
     * @return
     */
    TbContent findOneById(Long id);

    /**
     * 更新广告信息
     * @param tbContent
     */
    void modify(TbContent tbContent);

    void del(Long[] ids);
}
