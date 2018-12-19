package com.hnnd.fastgo.service.impl;

import com.google.common.collect.Maps;
import com.hnnd.fastgo.constant.RedisConstant;
import com.hnnd.fastgo.dao.TbGoodsDescMapper;
import com.hnnd.fastgo.dao.TbGoodsMapper;
import com.hnnd.fastgo.dao.TbItemCatMapper;
import com.hnnd.fastgo.dao.TbItemMapper;
import com.hnnd.fastgo.entity.TbGoods;
import com.hnnd.fastgo.entity.TbGoodsDesc;
import com.hnnd.fastgo.entity.TbItem;
import com.hnnd.fastgo.enumration.SellerGoodsEnum;
import com.hnnd.fastgo.enumration.SkuStatus;
import com.hnnd.fastgo.service.IGoodsDetailService;
import com.hnnd.fastgo.vo.SystemVo;
import com.redisoper.IRedisService;
import freemarker.template.Configuration;
import freemarker.template.Template;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.*;
import java.util.List;
import java.util.Map;

/**
 * 生成业务服务接口
 * Created by Administrator on 2018/12/14.
 */
@Service
@Slf4j
public class GoodsDetailServiceImpl implements IGoodsDetailService {

    @Autowired
    private Configuration configuration;

    @Autowired
    private TbGoodsMapper tbGoodsMapper;

    @Autowired
    private TbGoodsDescMapper tbGoodsDescMapper;

    @Autowired
    private TbItemCatMapper tbItemCatMapper;

    @Autowired
    private TbItemMapper tbItemMapper;

    @Autowired
    private IRedisService redisServiceImpl;
    @Override
    public SystemVo generatorHtmlByGoodsId(Long goodsId) {

        try {
            String goodDetailHtml = redisServiceImpl.get(RedisConstant.GOODS_DETAIL_PREFIX+":"+goodsId);
            if(StringUtils.isEmpty(goodDetailHtml)) {
                //获取模型数据
                Map<String,Object> modelMap = obtainModelData(goodsId);
                //根据模型生成html字符串
                String htmlStr = genHtmlStrByModelData(modelMap);

                //把字符串保存到redis中
                redisServiceImpl.set(RedisConstant.GOODS_DETAIL_PREFIX+":"+goodsId,htmlStr);
            }
        } catch (Exception e) {
            log.error("根据商品id生成静态网页异常:{}",e);
            return SystemVo.error(SellerGoodsEnum.GEN_GOODSDETAIL_ERROR);
        }

        return SystemVo.success(goodsId,SellerGoodsEnum.SELLER_GOODS_SUCCESS);
    }

    /**
     * 获取模版模型数据
     * @param goodsId 商品id
     * @return 模型数据
     */
    private Map<String,Object> obtainModelData(Long goodsId) {
        TbGoods goods = tbGoodsMapper.selectByPrimaryKey(goodsId);
        if(goods==null) {
            log.error("根据商品ID没有查询到商品基本信息");
            throw new RuntimeException("根据商品ID没有查询到商品基本信息");
        }
        TbGoodsDesc goodsDesc = tbGoodsDescMapper.selectByPrimaryKey(goodsId);
        if(goodsDesc==null) {
            log.error("根据商品ID没有查询到商品详情信息");
            throw new RuntimeException("根据商品ID没有查询到商品详情信息");
        }

        //查询商品分离id
        String itemCat1Name = tbItemCatMapper.selectByPrimaryKey(goods.getCategory1Id()).getName();
        String itemCat2Name = tbItemCatMapper.selectByPrimaryKey(goods.getCategory2Id()).getName();
        String itemCat3Name = tbItemCatMapper.selectByPrimaryKey(goods.getCategory3Id()).getName();

        //读取sku列表
        List<TbItem> tbItemList = tbItemMapper.selectSkuListByStausWithSpuId(goodsId, SkuStatus.SKU_STATUS_0.getCode());
        if(null == tbItemList) {
            log.error("根据商品ID没有查询到商品sku列表信息");
            throw new RuntimeException("根据商品ID没有查询到商品sku列表信息");
        }


        //封装数据
        Map<String,Object> modelMap = Maps.newHashMap();
        modelMap.put("goods",goods);
        modelMap.put("goodsDesc",goodsDesc);
        modelMap.put("itemCat1Name",itemCat1Name);
        modelMap.put("itemCat2Name",itemCat2Name);
        modelMap.put("itemCat3Name",itemCat3Name);
        modelMap.put("itemList",tbItemList);

        return modelMap;
    }

    /**
     * 根据模型数据 生成Html字符串代码
     * @param modelData
     * @return
     */
    public String genHtmlStrByModelData(Map<String,Object> modelData) {
        String goodDetailHtml="";
        try {
            StringWriter sw = new StringWriter();
            Template template = configuration.getTemplate("goodsDetail.ftl");
            template.process(modelData,sw);
            goodDetailHtml = sw.toString();
            //把模版写入到指定文件下
        } catch (Exception e) {
            log.error("调用freemaker生成模版异常:{}",e);
            throw new RuntimeException("调用freemaker生成模版异常");
        }
        return goodDetailHtml;
    }



    private void genHtml(String prifixFileName,String htmlStr) throws FileNotFoundException {
        File path = new File(ResourceUtils.getURL("classpath:static").getPath());
        //如果上传目录为/static/images/upload/，则可以如下获取：
        String fileName = path.getAbsolutePath()+"/"+prifixFileName+".html";


        try {
            FileWriter fileWriter = new FileWriter(fileName);
            fileWriter.write(htmlStr);
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
