package com.hnnd.fastgo.clientapi.sellergoods.file;

import com.hnnd.fastgo.bo.DfsFileBo;
import com.hnnd.fastgo.enumration.SellerGoodsEnum;
import com.hnnd.fastgo.vo.SystemVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * 文件服务降级
 * Created by Administrator on 2018/11/29.
 */
@Component
@Slf4j
public class FileApiImpl implements FileApi {

    @Override
    public SystemVo delFile(DfsFileBo dfsFileBo) {
        log.error("删除文件异常:{}",dfsFileBo);
        return SystemVo.error(SellerGoodsEnum.DEL_IMG_ERROR);
    }
}
