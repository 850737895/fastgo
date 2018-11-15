package com.hnnd.fastgo.controller;

import com.hnnd.fastgo.clientapi.sellergoods.SellerGoodsApi;
import com.hnnd.fastgo.entity.TbBrand;
import com.hnnd.fastgo.enumration.SellerGoodsEnum;
import com.hnnd.fastgo.service.ISellerGoodsService;
import com.hnnd.fastgo.vo.PageResultVo;
import com.hnnd.fastgo.vo.SystemVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 品牌管理controller
 * Created by 85073 on 2018/11/13.
 */
@RestController
@RequestMapping("/sellerGoods")
@Slf4j
public class SellerGoodsController implements SellerGoodsApi {

    @Autowired
    private ISellerGoodsService brandServiceImpl;

    /**
     * 查询品牌列表
     * @return SystemVo<List<TbBrand>>
     */
    @GetMapping("/brand/list")
    public SystemVo<List<TbBrand>> selectAll() {
        List<TbBrand> tbBrandList = brandServiceImpl.selectAll();
        return SystemVo.success(tbBrandList, SellerGoodsEnum.SELLER_GOODS_SUCCESS);
    }

    @GetMapping("/brand/pageList")
    public PageResultVo<TbBrand> selectAllByPage(@RequestParam(value = "pageNum",defaultValue = "1")Integer pageNum,
                                                 @RequestParam(value = "pageSzie",defaultValue = "10")Integer pageSize) {
        return brandServiceImpl.selectAllByPage(pageNum,pageSize);
    }

    @RequestMapping("/brand/save")
    public SystemVo save(@RequestBody TbBrand tbBrand) {
        try {
            brandServiceImpl.save(tbBrand);
            return SystemVo.success(SellerGoodsEnum.SELLER_GOODS_SUCCESS);
        }catch (Exception e) {
            log.error("保存商品品牌出错:{}",e);
            return SystemVo.error(SellerGoodsEnum.SELLER_GOODS_SAVE_ERROR);
        }
    }

    @RequestMapping("/brand/findOne/{id}")
    public SystemVo<TbBrand> findOneById(@PathVariable("id") Long id) {
        try {
            return SystemVo.success(brandServiceImpl.findOneById(id),SellerGoodsEnum.SELLER_GOODS_SUCCESS);
        } catch (Exception e) {
            log.error("根据id:{}查询品牌信息异常:{}",id,e);
            return SystemVo.error(SellerGoodsEnum.SELLER_GOODS_FINDONE_ERROR);
        }
    }

    @RequestMapping("/brand/modify")
    public SystemVo modifyBrandById(@RequestBody TbBrand tbBrand) {
        try {
            brandServiceImpl.modifyBrandById(tbBrand);
            return SystemVo.success(SellerGoodsEnum.SELLER_GOODS_SUCCESS);
        } catch (Exception e) {
            log.error("修改品牌信息:{}异常:{}",tbBrand,e);
            return SystemVo.error(SellerGoodsEnum.SELLER_GOODS_MODIFY_ERROR);
        }
    }

    @RequestMapping("/brand/del")
    public SystemVo delBrandById(@RequestParam("ids") String[] ids) {
        try {
            brandServiceImpl.delBrandById(ids);
            return SystemVo.success(SellerGoodsEnum.SELLER_GOODS_SUCCESS);
        } catch (Exception e) {
            log.error("通过ID:{}删除品牌信息异常{}",ids,e);
            return SystemVo.error(SellerGoodsEnum.SELLER_GOODS_DEL_ERROR);
        }
    }

    @RequestMapping("/brand/search")
    public PageResultVo<TbBrand> search(@RequestBody TbBrand tbBrand,
                                        @RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum,
                                        @RequestParam(value = "pageSize",defaultValue = "10") Integer pageSize) {
        return brandServiceImpl.search(tbBrand,pageNum,pageSize);
    }

}
