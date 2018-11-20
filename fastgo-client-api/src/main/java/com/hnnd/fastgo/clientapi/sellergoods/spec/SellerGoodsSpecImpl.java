package com.hnnd.fastgo.clientapi.sellergoods.spec;

import com.hnnd.fastgo.entity.TbSpecification;
import com.hnnd.fastgo.enumration.SellerGoodsEnum;
import com.hnnd.fastgo.vo.PageResultVo;
import com.hnnd.fastgo.vo.SpecVo;
import com.hnnd.fastgo.vo.SystemVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;


/**
 * 规格降级服务
 * Created by Administrator on 2018/11/16.
 */
@Component
@Slf4j
public class SellerGoodsSpecImpl implements SellerGoodsSpecApi {

    /**
     * 分页列表降级服务
     * @param pageSize 每页条数
     * @param pageNum 页码
     * @param queryCondition 查询条件
     * @return
     */
    @Override
    public PageResultVo<TbSpecification> list(Integer pageSize, Integer pageNum, String queryCondition) {
        log.warn("商家商品规格列表分页服务异常");
        return null;
    }

    /**
     * 保存规格异常降级方法
     * @param specVo
     * @return
     */
    @Override
    public SystemVo saveSpec(SpecVo specVo) {
        log.error("保存商品规格异常:{}",specVo);
        return SystemVo.error(SellerGoodsEnum.SELLER_GOODS_SAVE_SPEC_ERROR);
    }

    @Override
    public SystemVo modifySpec(SpecVo specVo) {
        log.error("修改商品规格异常:{}",specVo);
        return SystemVo.error(SellerGoodsEnum.SELLER_GOODS_MODIFY_SPEC_ERROR);
    }

    @Override
    public SystemVo<SpecVo> findOne(Long specId) {
        log.error("保存商品规格异常:{}",specId);
        return SystemVo.error(SellerGoodsEnum.SELLER_GOODS_FINDONE_SPEC_ERROR);
    }

    @Override
<<<<<<< HEAD
    public SystemVo delSpecBySpecId(String[] specIds) {
        return SystemVo.error(SellerGoodsEnum.SELLER_GOODS_DEL_SPEC_ERROR);
=======
    public List<Map<String, Object>> initSpecList() {
        log.warn("商家商品规格列表分页服务异常");
        return null;
>>>>>>> c5a039e81ea04dd98164310a8cd028a03154db16
    }
}
