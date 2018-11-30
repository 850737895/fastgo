package com.hnnd.fastgo.controller;

import com.hnnd.fastgo.enumration.SellerGoodsEnum;
import com.hnnd.fastgo.vo.SystemVo;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**获取登录信息
 * Created by Administrator on 2018/11/22.
 */
@RestController
@RequestMapping("/login")
public class LoginController {

    @RequestMapping("/getLoginName")
    public SystemVo getLoginName() {
        String loginName = SecurityContextHolder.getContext().getAuthentication().getName();
        return SystemVo.success(loginName, SellerGoodsEnum.SELLER_GOODS_SUCCESS);
    }
}
