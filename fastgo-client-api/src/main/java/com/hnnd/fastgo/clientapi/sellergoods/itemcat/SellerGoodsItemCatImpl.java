package com.hnnd.fastgo.clientapi.sellergoods.itemcat;

import com.hnnd.fastgo.entity.TbItemCat;
import com.hnnd.fastgo.enumration.SellerGoodsEnum;
import com.hnnd.fastgo.vo.ItemCatVo;
import com.hnnd.fastgo.vo.PageResultVo;
import com.hnnd.fastgo.vo.SystemVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 服务降级方法
 * Created by Administrator on 2018/11/21.
 */
@Component
@Slf4j
public class SellerGoodsItemCatImpl implements ISellerGoodsItemCatApi {
    @Override
    public PageResultVo<TbItemCat> qryItemCatLevel(Integer pageNum, Integer pageSize, Integer parentId, String qryCondition) {
        log.error("分页查询商品类目异常parentId:{},qryConditon:{}",parentId,qryCondition);
        return null;
    }

    @Override
    public List<TbItemCat> findByParentId(Integer parentId) {
        log.error("查询顶级父类商品类目异常:{}",parentId);
        return null;
    }

    @Override
    public SystemVo save(ItemCatVo itemCatVo) {
        log.error("保存商品模版异常");
        return SystemVo.error(SellerGoodsEnum.SELLER_GOODS_SAVE_ITEMCAT_ERROR);
    }

    @Override
    public ItemCatVo findOneById(Long id) {
        log.error("根据商品类目id:{}查询类目信息异常",id);
        return null;
    }

    @Override
    public SystemVo modify(ItemCatVo itemCatVo) {
        log.error("更新商品类目信息异常:{}",itemCatVo);
        return SystemVo.error(SellerGoodsEnum.SELLER_GOODS_MODIFY_ITEMCAT_ERROR);
    }

    @Override
    public SystemVo del(String[] ids) {
        log.error("批量删除商品类目信息异常:{}",ids);
        return SystemVo.success(SellerGoodsEnum.SELLER_GOODS_DEL_ITEMCAT_ERROR);
    }
}
