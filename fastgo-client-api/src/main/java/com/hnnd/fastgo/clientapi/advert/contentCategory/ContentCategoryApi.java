package com.hnnd.fastgo.clientapi.advert.contentCategory;

import com.hnnd.fastgo.entity.TbContent;
import com.hnnd.fastgo.entity.TbContentCategory;
import com.hnnd.fastgo.vo.PageResultVo;
import com.hnnd.fastgo.vo.SystemVo;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * 广告类别降级服务
 * Created by Administrator on 2018/12/5.
 */
@FeignClient(value = "fastgo-advert-service",fallback =ContentCategoryApiImpl.class,path = "/advertService/contentCategory")
public interface ContentCategoryApi {

    @RequestMapping("/list")
    public PageResultVo<TbContentCategory> list(@RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum,
                                                          @RequestParam(value = "pageSize",defaultValue = "10")Integer pageSize,
                                                          @RequestParam(value = "contentCategoryName") String contentCategoryName);

    @RequestMapping("/save")
    public SystemVo save(@RequestBody TbContentCategory tbContentCategory);

    @RequestMapping("/del")
    public SystemVo del(@RequestParam("ids") Long[] ids) ;

    @RequestMapping("/modify")
    public SystemVo modify(@RequestBody TbContentCategory tbContentCategory);

    @RequestMapping("/findOneById/{id}")
    public TbContentCategory findOneById(@PathVariable("id") Long id);

    @RequestMapping("/findAll")
    public List<TbContentCategory> findAll();

}
