package com.hnnd.fastgo.controller;

import com.google.common.collect.Maps;
import com.hnnd.fastgo.bo.DfsFileBo;
import com.hnnd.fastgo.clientapi.sellergoods.file.FileApi;
import com.hnnd.fastgo.compent.FastDFSUploadUtils;
import com.hnnd.fastgo.constant.FastDfsConstant;
import com.hnnd.fastgo.enumration.SellerGoodsEnum;
import com.hnnd.fastgo.vo.SystemVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

/**
 * 文件操作类
 * Created by Administrator on 2018/11/29.
 */
@RestController
@RequestMapping("/file")
@Slf4j
public class FileController {

    @Autowired
    private FileApi fileApi;

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

    @RequestMapping("/del")
    public SystemVo delFileByFileUrl(@RequestParam("fileUrl")String fileUrl) {

        log.warn("需要删除的文件地址:{}",fileUrl);
        if(StringUtils.isEmpty(fileUrl)) {
            log.warn("需要删除文件的URL路径不能为空:{}",fileUrl);
            return SystemVo.error(SellerGoodsEnum.SELLER_GOODS_IMG_URL_NULL);
        }

        //文件删除
        try{
            DfsFileBo dfsFileBo = parseFileUrl(fileUrl);
            return fileApi.delFile(dfsFileBo);
        }catch (Exception e) {
            log.error("删除文件信息异常:文件信息:{},异常信息:{}",fileUrl,e);
            return SystemVo.error(SellerGoodsEnum.DEL_IMG_ERROR);
        }
    }

    /**
     * 解析文件URL
     * @param url 需要解析的url
     * @return
     */
    private DfsFileBo parseFileUrl(String url) {
        Integer index = url.indexOf(FastDfsConstant.IMG_ACCESS_URL_IP);
        //出去tracker地址端口的 的文件路径
        String fileUrlSuffix = StringUtils.substring(url,index+FastDfsConstant.IMG_ACCESS_URL_IP.length());
        int indexOf = fileUrlSuffix.indexOf("/");
        //获取gourp
        String gourp = fileUrlSuffix.substring(0,indexOf);
        //获取path
        String path = fileUrlSuffix.substring(indexOf+1);

        DfsFileBo dfsFileBo = new DfsFileBo();
        dfsFileBo.setGorup(gourp);
        dfsFileBo.setPath(path);
        dfsFileBo.setFullPath(url);
        return dfsFileBo;
    }
}
