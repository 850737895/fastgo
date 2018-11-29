package com.hnnd.fastgo.service;

import com.hnnd.fastgo.bo.DfsFileBo;

/**
 * 服务服务
 * Created by Administrator on 2018/11/29.
 */
public interface IFileService {

    /**
     * 插入删除文件记录表
     * @param dfsFileBo dfsFileBo
     */
     void insertFileLog(DfsFileBo dfsFileBo);
}
