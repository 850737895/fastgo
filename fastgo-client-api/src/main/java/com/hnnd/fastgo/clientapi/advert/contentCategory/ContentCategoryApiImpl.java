package com.hnnd.fastgo.clientapi.advert.contentCategory;

import com.hnnd.fastgo.entity.TbContentCategory;
import com.hnnd.fastgo.enumration.SellerGoodsEnum;
import com.hnnd.fastgo.vo.PageResultVo;
import com.hnnd.fastgo.vo.SystemVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 广告类容降级服务
 * Created by Administrator on 2018/12/5.
 */
@Component
@Slf4j
public class ContentCategoryApiImpl implements ContentCategoryApi {
    @Override
    public PageResultVo<TbContentCategory> list(Integer pageNum, Integer pageSize, String contentCategoryName) {
        log.error("查询广告类别列表异常");
        return null;
    }

    @Override
    public SystemVo save(TbContentCategory tbContentCategory) {
        log.error("保存广告类别异常");
        return SystemVo.error(SellerGoodsEnum.SELLER_MRG_ADVERT_SAVE_ERROR);
    }

    @Override
    public SystemVo del(Long[] ids) {
        log.error("批量删除广告类别异常:{}",ids);
        return SystemVo.error(SellerGoodsEnum.SELLER_MRG_ADVERT_DEL_ERROR);
    }

    @Override
    public SystemVo modify(TbContentCategory tbContentCategory) {
        log.error("更新广告类别异常:{}",tbContentCategory);
        return SystemVo.error(SellerGoodsEnum.SELLER_MRG_ADVERT_MODIFY_ERROR);
    }

    @Override
    public TbContentCategory findOneById(Long id) {
        log.error("通过广告类别ID 查询广告类别信息异常:{}",id);
        return null;

    }

    @Override
    public List<TbContentCategory> findAll() {
        log.error("查询广告类别列表异常");
        return null;
    }
}
