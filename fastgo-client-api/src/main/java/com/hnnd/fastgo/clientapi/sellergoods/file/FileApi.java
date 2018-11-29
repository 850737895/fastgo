package com.hnnd.fastgo.clientapi.sellergoods.file;

import com.hnnd.fastgo.bo.DfsFileBo;
import com.hnnd.fastgo.clientapi.sellergoods.itemcat.SellerGoodsItemCatImpl;
import com.hnnd.fastgo.vo.SystemVo;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 文件远程服务
 * Created by Administrator on 2018/11/29.
 */
@FeignClient(name ="fastgo-sellergoods-service",fallback = FileApiImpl.class, path = "/sellerGoods/file")
public interface FileApi {

    @RequestMapping("/del")
    public SystemVo delFile(@RequestBody DfsFileBo dfsFileBo);
}
