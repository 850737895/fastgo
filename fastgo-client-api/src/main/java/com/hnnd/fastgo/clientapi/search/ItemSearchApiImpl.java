package com.hnnd.fastgo.clientapi.search;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 搜索降级服务
 * Created by 85073 on 2018/12/9.
 */
@Component
@Slf4j
public class ItemSearchApiImpl implements ItemSearchApi {
    @Override
    public Map searchList(Map<String, Object> searchMap) {
        log.error("根据搜索条件:{}执行搜索服务异常",searchMap);
        return null;
    }
}
