package com.hnnd.fastgo.controller;

import com.hnnd.fastgo.entity.TbBrand;
import com.hnnd.fastgo.service.IBrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 品牌管理controller
 * Created by 85073 on 2018/11/13.
 */
@RestController
@RequestMapping("/brand")
public class BrandController {

    @Autowired
    private IBrandService brandServiceImpl;

    @GetMapping("/list")
    public List<TbBrand> selectAll() {
        return brandServiceImpl.selectAll();
    }
}
