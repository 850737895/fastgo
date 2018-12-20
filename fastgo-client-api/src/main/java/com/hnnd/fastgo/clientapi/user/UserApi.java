package com.hnnd.fastgo.clientapi.user;

import com.hnnd.fastgo.vo.SystemVo;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 用户中心远程调用接口
 * Created by Administrator on 2018/12/20.
 */
@FeignClient
public interface UserApi {

    @RequestMapping("/checkValidate/{checkType}/{checkValue}")
    public SystemVo checkValidate(@PathVariable("checkType")String checkType,
                                  @PathVariable("checkValue") String checkValue);
}
