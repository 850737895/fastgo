package com.hnnd.fastgo.controller;

import com.hnnd.fastgo.bo.DfsFileBo;
import com.hnnd.fastgo.enumration.SellerGoodsEnum;
import com.hnnd.fastgo.service.IFileService;
import com.hnnd.fastgo.vo.SystemVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 实际的文件服务
 * Created by Administrator on 2018/11/29.
 */
@RestController
@RequestMapping("/sellerGoods/file")
@Slf4j
public class FileController {

    @Autowired
    private IFileService fileServiceImpl;

    @RequestMapping("/del")
    public SystemVo delFile(@RequestBody DfsFileBo dfsFileBo) {
        log.info("接收到需要保存的删除文件记录:{}",dfsFileBo);
        try{
            fileServiceImpl.insertFileLog(dfsFileBo);
            return SystemVo.success(SellerGoodsEnum.SELLER_GOODS_SUCCESS);
        }catch (Exception e){
            log.error("保存需要删除的文件信息异常:{}",e);
            return SystemVo.error(SellerGoodsEnum.DEL_IMG_ERROR);
        }
    }
}
