package com.hnnd.fastgo.clientapi.advert.content;

import com.hnnd.fastgo.entity.TbContent;
import com.hnnd.fastgo.vo.PageResultVo;
import com.hnnd.fastgo.vo.SystemVo;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * 广告调用服务
 * Created by Administrator on 2018/12/5.
 */
@FeignClient(value = "fastgo-advert-service",fallback =ContentApiImpl.class,path = "/advertService/content")
public interface ContentApi {

    @RequestMapping("/list4Page")
    public PageResultVo<TbContent> list4Page(@RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum,
                                             @RequestParam(value = "pageSize",defaultValue = "10") Integer pageSize);

    @RequestMapping("/save")
    public SystemVo save(@RequestBody TbContent content);

    @RequestMapping("/findOneById/{id}")
    public TbContent findOneById(@PathVariable("id")Long id);

    @RequestMapping("/modify")
    public SystemVo modify(@RequestBody TbContent tbContent) ;

    @RequestMapping("/del")
    public SystemVo del(@RequestParam("ids")Long[] ids);

    @RequestMapping("/findListByCategoryId/{categoryId}")
    public List<TbContent> findListByCategoryId(@PathVariable("categoryId")Long categoryId);

}
