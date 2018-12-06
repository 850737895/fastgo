package com.hnnd.fastgo.controller;

import com.google.common.collect.Maps;
import com.hnnd.fastgo.constant.FastDfsConstant;
import com.hnnd.fastgo.util.FastDFSUploadUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

/**
 * 文件控制层
 * Created by Administrator on 2018/12/5.
 */
@RestController
@RequestMapping("/file")
@Slf4j
public class FileController {

    @Autowired
    private FastDFSUploadUtils fastDFSUploadUtils;

    @RequestMapping("/upload")
    public Map<String,Object> uploadFile(MultipartFile file) {
        Map<String,Object> resultVo = Maps.newHashMap();
        try {
            String url= fastDFSUploadUtils.uploadFileWithThumbImage(file);
            resultVo.put("url", FastDfsConstant.IMG_ACCESS_URL_IP+url);
            resultVo.put("error",0);
        } catch (IOException e) {
            log.error("文件上传失败:{}",e);
            resultVo.put("error",1);
            resultVo.put("message","文件上传失败");
        }
        return resultVo;
    }
}
