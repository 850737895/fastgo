package com.hnnd.fastgo.service;

import com.hnnd.fastgo.clientapi.sellergoods.seller.SellerApi;
import com.hnnd.fastgo.entity.TbSeller;
import com.hnnd.fastgo.vo.SystemVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * 查询登录用户
 * Created by Administrator on 2018/11/23.
 */
@Service
@Slf4j
public class ShopUserDetailService implements UserDetailsService {

    @Autowired
    private SellerApi sellerApi;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SystemVo<TbSeller> sellerSystemVo = sellerApi.loadUserByUsername(username);
        if(!sellerSystemVo.isSuccess()) {
            log.error("根据用户id{}查询商家用户信息异常:{}",username,sellerSystemVo.getMsg());
            return null;
        }
        TbSeller tbSeller = sellerSystemVo.getData();
        return new User(tbSeller.getSellerId(),passwordEncoder.encode(tbSeller.getPassword()), AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_SELLER"));
    }
}
