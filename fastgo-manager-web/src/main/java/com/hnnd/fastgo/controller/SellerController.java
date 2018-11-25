package com.hnnd.fastgo.controller;

import com.hnnd.fastgo.Qo.QryTbsellerQo;
import com.hnnd.fastgo.entity.TbSeller;
import com.hnnd.fastgo.vo.PageResultVo;
import com.hnnd.fastgo.vo.SystemVo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 商家管理模块
 * Created by 85073 on 2018/11/24.
 */
@RestController
@Controller("/seller")
public class SellerController {

    @RequestMapping("/findListByPage")
    public PageResultVo<TbSeller> qryTbSellerListByPage(@RequestParam(name = "pageNum",defaultValue = "1")Integer pageNum,
                                                        @RequestParam(name="pageSize",defaultValue = "10") Integer pageSize,
                                                        @RequestBody QryTbsellerQo qryTbsellerQo){
        return null;
    }
}
