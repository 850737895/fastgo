package com.hnnd.fastgo.clientapi.sellergoods.typetemplate;

import com.hnnd.fastgo.entity.TbTypeTemplate;
import com.hnnd.fastgo.vo.PageResultVo;
import com.hnnd.fastgo.vo.SystemVo;
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
}
