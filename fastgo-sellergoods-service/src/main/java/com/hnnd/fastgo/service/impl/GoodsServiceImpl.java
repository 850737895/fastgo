package com.hnnd.fastgo.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.hnnd.fastgo.bo.ItemImageBo;
import com.hnnd.fastgo.bo.SmallImageBo;
import com.hnnd.fastgo.bo.UpdateGoodsStatusBo;
import com.hnnd.fastgo.constant.GoodsItemConstant;
import com.hnnd.fastgo.dao.*;
import com.hnnd.fastgo.entity.*;
import com.hnnd.fastgo.enumration.GoodsAduitEnum;
import com.hnnd.fastgo.enumration.GoodsDelEnum;
import com.hnnd.fastgo.enumration.GoodsMarkableEnum;
import com.hnnd.fastgo.enumration.GoodsStartSpecEnum;
import com.hnnd.fastgo.service.IGoodsService;
import com.hnnd.fastgo.vo.GoodsVo;
import com.hnnd.fastgo.vo.PageResultVo;
import com.hnnd.fastgo.vo.SystemVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 商品接口实现类
 * Created by 85073 on 2018/12/1.
 */
@Service
@Slf4j
public class GoodsServiceImpl implements IGoodsService {


    @Autowired
    private TbGoodsMapper tbGoodsMapper;

    @Autowired
    private TbGoodsDescMapper tbGoodsDescMapper;

    @Autowired
    private TbBrandMapper tbBrandMapper;

    @Autowired
    private TbSellerMapper tbSellerMapper;

    @Autowired
    private TbItemMapper tbItemMapper;

    @Autowired
    private TbItemCatMapper tbItemCatMapper;

    @Transactional
    @Override
    public void save(GoodsVo goodsVo) {

        //设置主键
        Long goodsId = System.currentTimeMillis();
        goodsVo.getGoods().setId(goodsId);

        //保存goods表
        TbGoods tbGoods = supplementGoods(goodsVo);
        tbGoodsMapper.insert(tbGoods);

        //保存goodsDesc表
        goodsVo.getGoodsDesc().setGoodsId(goodsId);
        tbGoodsDescMapper.insert(goodsVo.getGoodsDesc());

        //保存item表
        if(GoodsStartSpecEnum.STARTER.getCode().equals(goodsVo.getGoods().getIsEnableSpec())) {
            startSpecSave(goodsVo);
        }else {
            unStartSpec(goodsVo);
        }
    }

    @Transactional
    @Override
    public void update(GoodsVo goodsVo) throws RuntimeException {

        try {
            //更新 goods表
            TbGoods tbGoods = supplementGoods(goodsVo);
            tbGoodsMapper.updateByExampleSelective(tbGoods);

            //更新goodsDesc表
            tbGoodsDescMapper.updateByExampleSelective(goodsVo.getGoodsDesc());

            //更新TbItem表
            List<TbItem> tbItemList = JSON.parseObject(goodsVo.getItemList(),new TypeReference<List<TbItem>>(){});
            for(TbItem tbItem:tbItemList) {
                tbItemMapper.updateByExampleSelective(tbItem);
            }
        } catch (Exception e) {
            log.error("更新商品信息异常:{}",e);
            throw new RuntimeException("更新商品信息异常"+e);
        }
    }

    @Override
    public PageResultVo<TbGoods> findList4Page(TbGoods tbGoods, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<TbGoods> tbGoodsList = tbGoodsMapper.findList4Page(tbGoods);
        PageInfo<TbGoods> pageInfo = new PageInfo<>(tbGoodsList);
        return new PageResultVo<>(pageInfo.getTotal(),pageInfo.getList());
    }

    @Override
    public GoodsVo findGoodsVoById(Long goodsId) {
        GoodsVo goodsVo = new GoodsVo();

        TbGoods tbGoods = tbGoodsMapper.selectByPrimaryKey(goodsId);
        goodsVo.setGoods(tbGoods);

        TbGoodsDesc tbGoodsDesc = tbGoodsDescMapper.selectByPrimaryKey(goodsId);
        goodsVo.setGoodsDesc(tbGoodsDesc);

        List<TbItem> tbItems = tbItemMapper.selectByGoodsId(goodsId);
        goodsVo.setItemList(JSON.toJSONString(tbItems));
        return goodsVo;
    }

    @Override
    public void applyAduit(UpdateGoodsStatusBo updateGoodsStatusBo) {
        List<Long> ids = Lists.newArrayList();

        //批量更新商品状态
        tbGoodsMapper.updateGoodsStatusBatch(updateGoodsStatusBo.getSellerId(),updateGoodsStatusBo.getChangeStatus(),updateGoodsStatusBo.getGoodIdList());
    }


    /**
     * 封装goods对象
     * @param goodsVo 对象
     * @return TbGoods
     */
    private TbGoods supplementGoods(GoodsVo goodsVo) {

        TbGoods tbGoods= goodsVo.getGoods();
        //设置商品审核状态
        tbGoods.setAuditStatus(GoodsAduitEnum.WAITT_APPLY.getCode());
        tbGoods.setIsMarketable(GoodsMarkableEnum.IS_MARK.getCode());
        tbGoods.setIsDelete(GoodsDelEnum.UN_DEL.getCode());

        //上传了图片保存第一张图片(设置小图)
        List<ItemImageBo> itemImageBoList = buliderItemImage(goodsVo.getGoodsDesc().getItemImages());
        if(itemImageBoList.size()!=0) {
            tbGoods.setSmallPic(buildSmallImageUrl(itemImageBoList.get(0).getUrl(),goodsVo.getSmallImageBo()));
        }
        return tbGoods;
    }

    /**
     * 解析构建 图片文件节点
     * @param itemImageJsonStr 图片字符串
     * @return  List<ItemImageBo>
     */
    private List<ItemImageBo> buliderItemImage(String itemImageJsonStr) {
        JSONArray jsonArray = JSONArray.parseArray(itemImageJsonStr);
        List<ItemImageBo> itemImageBoList = Lists.newArrayList();

        for(int index=0;index<jsonArray.size();index++) {
            //解析成Json对象
            JSONObject imageJson = JSON.parseObject(jsonArray.get(index).toString());
            ItemImageBo itemImageBo = new ItemImageBo();
            if(imageJson.containsKey("color")) {
                itemImageBo.setColor(imageJson.getString("color"));
            }
            itemImageBo.setUrl(imageJson.getString("url"));
            itemImageBoList.add(itemImageBo);
        }

        return itemImageBoList;
    }

    /**
     * 设置商品详情信息
     * @param tbItem 商品详情
     * @param goodsVo goodsVo对象
     */
    private void setGoodsItem(TbItem tbItem,GoodsVo goodsVo) {
        tbItem.setGoodsId(goodsVo.getGoodsDesc().getGoodsId());
        //设置品牌
        TbBrand tbBrand = tbBrandMapper.selectByPrimaryKey(goodsVo.getGoods().getBrandId());
        tbItem.setBrand(tbBrand.getName());

        //设置商家信息
        TbSeller tbSeller = tbSellerMapper.selectByPrimaryKey(goodsVo.getGoods().getSellerId());
        tbItem.setSeller(tbSeller.getNickName());
        tbItem.setSellerId(tbSeller.getSellerId());

        //设置商品分类信息
        TbItemCat tbItemCat = tbItemCatMapper.selectByPrimaryKey(goodsVo.getGoods().getCategory3Id());
        tbItem.setCategoryid(goodsVo.getGoods().getCategory3Id());
        tbItem.setCategory(tbItemCat.getName());

        //设置日期
        tbItem.setCreateTime(new Date());
        tbItem.setUpdateTime(new Date());

        //设置图片信息(取第一个图)
        List<ItemImageBo> itemImageBoList = buliderItemImage(goodsVo.getGoodsDesc().getItemImages());
        if(itemImageBoList.size()!=0) {
            tbItem.setImage(itemImageBoList.get(0).getUrl());
        }
    }

    /**
     * 构建小图路径
     * @param bigImageUrl  大图路径
     * @param smallImageBo 小图属性
     * @return 大图路径
     */
    private  String buildSmallImageUrl(String bigImageUrl,SmallImageBo smallImageBo) {
        //文件的最后一个点的位置
        Integer lastPoint = StringUtils.lastIndexOf(bigImageUrl,".");

        //文件前缀
        String urlPrefix = StringUtils.substring(bigImageUrl,0,StringUtils.lastIndexOf(bigImageUrl,"."));

        //文件扩展名称
        String urlSuffix = StringUtils.substring(bigImageUrl,lastPoint+1);

        StringBuffer smallImageUrl = new StringBuffer(urlPrefix);
        smallImageUrl.append("_");
        smallImageUrl.append(""+smallImageBo.getWeight());
        smallImageUrl.append("x");
        smallImageUrl.append(""+smallImageBo.getHeight());
        smallImageUrl.append(".");
        smallImageUrl.append(urlSuffix);

        return smallImageUrl.toString();
    }

    /**
     * 通过spuName +规格属性获取skuName
     * @param spuName skuname
     * @param specMap  规格属性
     * @return skuname
     */
    private String generatorSkuName(String spuName,Map<String,Object> specMap) {
        String skuName = spuName;
        for(String specKey:specMap.keySet()) {
            skuName+=" "+specMap.get(specKey);
        }
        return skuName;
    }

    /**
     * 起开商品规格保存逻辑
     */
    private void startSpecSave(GoodsVo goodsVo){
        //开启商品规格逻辑
        List<TbItem> tbItemList = JSON.parseObject(goodsVo.getItemList(),new TypeReference<List<TbItem>>(){});

        //拼接商品sku的名称(spuName+spec的各个属性)
        String goodsTitle = goodsVo.getGoods().getGoodsName();

        for (TbItem tbItem: tbItemList) {
            Map<String,Object> specMap = JSON.parseObject(tbItem.getSpec());
            //设置skuName
            tbItem.setTitle(generatorSkuName(goodsTitle,specMap));

            setGoodsItem(tbItem,goodsVo);
            //保存信息
            tbItemMapper.insert(tbItem);
        }
    }

    /**
     * 不开启商品规格保存逻辑
     * @param goodsVo
     */
    private void unStartSpec(GoodsVo goodsVo) {
        //不启用商品规格逻辑
        TbItem tbItem = new TbItem();
        tbItem.setTitle(goodsVo.getGoods().getGoodsName());
        tbItem.setPrice(goodsVo.getGoods().getPrice());

        tbItem.setStatus(GoodsItemConstant.ITEM_STATUS);//状态
        tbItem.setIsDefault(GoodsItemConstant.ITEM_IS_DEFAULT);//是否默认
        tbItem.setNum(99999);//库存数量
        tbItem.setSpec("{}");

        setGoodsItem(tbItem,goodsVo);
        tbItemMapper.insert(tbItem);
    }

}
