package com.hnnd.fastgo.service;

import com.hnnd.fastgo.entity.TbItemCat;
import com.hnnd.fastgo.vo.ItemCatVo;
import com.hnnd.fastgo.vo.PageResultVo;
import com.hnnd.fastgo.vo.SystemVo;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * 商品类目管理
 * Created by Administrator on 2018/11/21.
 */
public interface ISellerGoodsItemCatService {

    /**
     * 按照层级查询分页商品类目
     * @param pageNum 当前页码
     * @param pageSize 每页多少条
     * @param parentId 父类ID
     * @param qyrCondition 查询条件
     * @return PageResultVo<TbItemCat>
     */
    PageResultVo<TbItemCat> level(Integer pageNum,Integer pageSize,Integer parentId,String qyrCondition);

    /**
     * 根据父类商品类目
     * @param parentId
     * @return
     */
     List<TbItemCat> findByParentId(Integer parentId);

    /**
     * 保存商品类目
     * @param itemCatVo itemCatVo
     */
     void save( ItemCatVo itemCatVo);

    /**
     * 根据ID查询商品类目
     * @param id 类目ID
     * @return TbItemCat
     */
     ItemCatVo findOneById(Long id);

    /**
     * 根据类目ID更新商品类目
     * @param itemCatVo 对象
     */
    void modify(ItemCatVo itemCatVo);

    /**
     * 批量删除类目信息异常
     * @param ids  类目id 数组
     */
    void del(String[] ids);

    List<TbItemCat> findAll();


}
