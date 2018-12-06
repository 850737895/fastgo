package com.hnnd.fastgo.clientapi.advert.content;

import com.hnnd.fastgo.entity.TbContent;
import com.hnnd.fastgo.enumration.SellerGoodsEnum;
import com.hnnd.fastgo.vo.PageResultVo;
import com.hnnd.fastgo.vo.SystemVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * 广告降级废物
 * Created by Administrator on 2018/12/5.
 */
@Component
@Slf4j
public class ContentApiImpl implements ContentApi {

    @Override
    public PageResultVo<TbContent> list4Page(Integer pageNum, Integer pageSize) {
        log.error("查询广告列表服务异常");
        return null;
    }

    @Override
    public SystemVo save(TbContent content) {
        log.error("保存广告信息异常");
        return SystemVo.error(SellerGoodsEnum.SELLER_MRG_ADVERT_CONTENT_SAVE_ERROR);
    }
}
