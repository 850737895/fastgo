package com.hnnd.fastgo.service.impl;

import com.hnnd.fastgo.constant.RedisConstant;
import com.hnnd.fastgo.enumration.SellerGoodsEnum;
import com.hnnd.fastgo.service.IGoodsDetailService;
import com.hnnd.fastgo.vo.SystemVo;
import com.redisoper.IRedisService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * 生成商品详情
 * Created by Administrator on 2018/12/18.
 */
@Service
@Slf4j
public class GoodsDetailServiceImpl implements IGoodsDetailService {

    @Autowired
    private IRedisService redisServiceImpl;

    @Override
    public SystemVo genHtml(Long goodsId) throws IOException {
        //去redis中获取html的字符串
        String htmlStr = redisServiceImpl.get(RedisConstant.GOODS_DETAIL_PREFIX+":"+goodsId);
        //是否生成过hmtl
        String htmlFileName = redisServiceImpl.get(RedisConstant.GOODS_DETAILHTML_IS_GEN+":"+goodsId);

        String genFileName = "";

        //表示没有生成过
        if(!StringUtils.isEmpty(htmlStr) && StringUtils.isEmpty(htmlFileName) ) {
            //生成html文件
            genFileName = genHtml(goodsId.toString(),htmlStr);
            //写入redis表示已经生成过了
            redisServiceImpl.setnx(RedisConstant.GOODS_DETAILHTML_IS_GEN+":"+goodsId,genFileName);
        }else if(!StringUtils.isEmpty(htmlStr) && StringUtils.isNotEmpty(htmlFileName)) {//生成过Html
            genFileName = htmlFileName;
        }

        return SystemVo.success(genFileName,SellerGoodsEnum.SELLER_GOODS_SUCCESS);
    }

    /**
     * 生成商品详情页
     * @param prefix 页面前缀名称
     * @param content 页面内容
     * @return 页面的名称
     */
    private String genHtml(String prefix,String content) throws IOException {
        //文件上传的路径
        File path = new File(ResourceUtils.getURL("classpath:static").getPath());

        String fileName = path.getAbsolutePath()+"/"+prefix+".html";

        FileWriter fileWriter = new FileWriter(fileName);
        fileWriter.write(content);
        fileWriter.flush();
        fileWriter.close();
        return prefix+".html";
    }
}
