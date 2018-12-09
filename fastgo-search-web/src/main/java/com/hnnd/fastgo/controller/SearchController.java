package com.hnnd.fastgo.controller;

import com.hnnd.fastgo.clientapi.search.ItemSearchApi;
import com.hnnd.fastgo.enumration.SellerGoodsEnum;
import com.hnnd.fastgo.vo.SystemVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Map;

/**
 * 搜索服务控制层
 * Created by 85073 on 2018/12/9.
 */
@RestController
@RequestMapping("/tbItem")
@Slf4j
public class SearchController {

    @Autowired
    private ItemSearchApi searchApiImpl;

    /**
     * 关键字搜索
     * @param searchMap
     * @return
     */
    @RequestMapping("/searchList")
    public SystemVo<Map> searchList(@RequestBody Map<String,Object> searchMap) {
        Map resultMap = searchApiImpl.searchList(searchMap);
        if(resultMap==null) {
            log.error("调用远程搜索服务异常");
            return SystemVo.error(SellerGoodsEnum.KEYWORKDS_SEARCH_ERROR);
        }else {
            return SystemVo.success(resultMap,SellerGoodsEnum.SELLER_GOODS_SUCCESS);
        }
    }
}
