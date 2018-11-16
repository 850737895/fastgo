package com.hnnd.fastgo.clientapi.sellergoods.brand;

import com.hnnd.fastgo.entity.TbBrand;
import com.hnnd.fastgo.enumration.SellerGoodsEnum;
import com.hnnd.fastgo.vo.PageResultVo;
import com.hnnd.fastgo.vo.SystemVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 商家商品服务的熔断服务
 * Created by Administrator on 2018/11/14.
 */
@Component
@Slf4j
public class SellerGoodsBrandImpl implements SellerGoodsBrandApi {
    @Override
    public SystemVo<List<TbBrand>> selectAll() {
        log.warn("商家商品查询列表服务异常");
        return SystemVo.error(SellerGoodsEnum.SELLER_GOODS_ERROR);
    }

    @Override
    public PageResultVo<TbBrand> selectAllByPage(Integer pageNum, Integer pageSize) {
        log.warn("商家商品查询分页列表服务异常");
        return null;
    }

    @Override
    public SystemVo save(TbBrand tbBrand) {
        log.warn("保存品牌出错");
        return SystemVo.error(SellerGoodsEnum.SELLER_GOODS_SAVE_ERROR);
    }

    @Override
    public SystemVo<TbBrand> findOneById(Long id) {
        return SystemVo.error(SellerGoodsEnum.SELLER_GOODS_FINDONE_ERROR);
    }

    @Override
    public SystemVo modifyBrandById(TbBrand tbBrand) {
        return SystemVo.error(SellerGoodsEnum.SELLER_GOODS_MODIFY_ERROR);
    }

    @Override
    public SystemVo delBrandById(String [] ids) {
        return SystemVo.error(SellerGoodsEnum.SELLER_GOODS_DEL_ERROR);
    }

    @Override
    public PageResultVo<TbBrand> search(TbBrand tbBrand, Integer pageNum, Integer pageSize) {
        log.error("根据查询条件查询分页列表出错:{}",tbBrand);
        return null;
    }
}
