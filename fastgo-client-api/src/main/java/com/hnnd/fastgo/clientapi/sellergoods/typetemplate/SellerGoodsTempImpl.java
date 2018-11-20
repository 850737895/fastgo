package com.hnnd.fastgo.clientapi.sellergoods.typetemplate;

import com.hnnd.fastgo.entity.TbTypeTemplate;
import com.hnnd.fastgo.enumration.SellerGoodsEnum;
import com.hnnd.fastgo.vo.PageResultVo;
import com.hnnd.fastgo.vo.SystemVo;
import com.hnnd.fastgo.vo.TemplateTypeVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * 服务降级方法调用
 * Created by Administrator on 2018/11/20.
 */
@Slf4j
@Component
public class SellerGoodsTempImpl implements SellerGoodsTempApi {

    @Override
    public PageResultVo<TbTypeTemplate> list(Integer pageNum, Integer pageSize, String qryCondition) {
        log.warn("商家商品模版列表分页服务异常");
        return null;
    }

    @Override
    public SystemVo save(TemplateTypeVo templateTypeVo) {
        log.error("保存商品模版异常");
        return SystemVo.error(SellerGoodsEnum.SELLER_GOODS_SAVE_TEMPTYPE_ERROR);
    }

    @Override
    public SystemVo<TbTypeTemplate> findOne(Long id) {
        log.error("根据模版ID:{}查询模版信息异常",id);
        return SystemVo.error(SellerGoodsEnum.SELLER_GOODS_FINDONE_TEMPTYPE_ERROR);
    }

    @Override
    public SystemVo modify(TemplateTypeVo templateTypeVo) {
        log.error("通过模版ID:{}更新模版信息异常:{}",templateTypeVo.getId(),templateTypeVo);
        return SystemVo.error(SellerGoodsEnum.SELLER_GOODS_MODIFY_TEMPTYPE_ERROR);
    }

    @Override
    public SystemVo del(String[] ids) {
        log.error("批量删除模版信息异常:{}",ids);
        return SystemVo.error(SellerGoodsEnum.SELLER_GOODS_MODIFY_TEMPTYPE_ERROR);
    }
}
