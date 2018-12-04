package com.hnnd.fastgo.service;

import com.hnnd.fastgo.bo.UpdateGoodsStatusBo;
import com.hnnd.fastgo.entity.TbGoods;
import com.hnnd.fastgo.vo.GoodsVo;
import com.hnnd.fastgo.vo.PageResultVo;
import com.hnnd.fastgo.vo.SystemVo;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 商品服务接口
 * Created by 85073 on 2018/12/1.
 */
public interface IGoodsService {

    /**
     * 保存商品
     * @param goodsVo 商品VO对象
     */
    void save(GoodsVo goodsVo) throws RuntimeException;

    /**
     * 修改商品信息
     * @param goodsVo 商品VO对象
     */
    void update(GoodsVo goodsVo) throws RuntimeException;

    /**
     * 条件分页查询
     * @param tbGoods 查询条件
     * @param pageNum 当前页码
     * @param pageSize 每页多少条
     * @return  PageResultVo<TbGoods>
     */
    PageResultVo<TbGoods> findList4Page(TbGoods tbGoods,Integer pageNum,Integer pageSize);

    /**
     * 通过商品Id查询商品详情ixnx
     * @param goodsId 商品id
     * @return goodsVo
     */
     GoodsVo findGoodsVoById(Long goodsId);

    /**
     * 提交商品审核
     * @param updateGoodsStatusBo 提交的商品审核列表
     */
    void applyAduit(UpdateGoodsStatusBo updateGoodsStatusBo);
}
