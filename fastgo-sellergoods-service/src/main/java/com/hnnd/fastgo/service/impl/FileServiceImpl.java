package com.hnnd.fastgo.service.impl;

import com.hnnd.fastgo.bo.DfsFileBo;
import com.hnnd.fastgo.dao.DelFileLogMapper;
import com.hnnd.fastgo.entity.DelFileLog;
import com.hnnd.fastgo.service.IFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * 插入文件删除记录表
 * Created by Administrator on 2018/11/29.
 */
@Service
public class FileServiceImpl implements IFileService {

    @Autowired
    private DelFileLogMapper delFileLogMapper;

    @Override
    public void insertFileLog(DfsFileBo dfsFileBo) {
        //文件记录表
        DelFileLog delFileLog = new DelFileLog();
        delFileLog.setGroup(dfsFileBo.getGorup());
        delFileLog.setPath(delFileLog.getPath());
        delFileLog.setCreateTime(new Date());
        delFileLog.setStatus(0);
        delFileLogMapper.insert(delFileLog);
    }
}
