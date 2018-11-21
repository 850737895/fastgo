package com.hnnd.fastgo.clientapi.sellergoods.itemcat;

import com.hnnd.fastgo.entity.TbItemCat;
import com.hnnd.fastgo.vo.PageResultVo;
import jdk.nashorn.internal.runtime.logging.Logger;
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
}
