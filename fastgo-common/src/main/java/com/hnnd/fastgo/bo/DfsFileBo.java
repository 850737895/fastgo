package com.hnnd.fastgo.bo;

import lombok.Data;

import java.io.Serializable;

/**
 * fastdfs 文件业务对象
 * Created by Administrator on 2018/11/29.
 */
@Data
public class DfsFileBo implements Serializable {

    private String gorup;

    private String path;

    private String fullPath;
}
