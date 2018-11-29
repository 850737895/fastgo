package com.hnnd.fastgo.compent;

import com.github.tobato.fastdfs.domain.StorePath;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * fastdfs文件操作类
 * Created by Administrator on 2018/11/28.
 */
@Slf4j
@Component
public class FastDFSUploadUtils {

    @Autowired
    private FastFileStorageClient fastFileStorageClient;

    /**
     * 不生产缩略图
     * @param multipartFile 文件
     * @return storePath
     * @throws IOException
     */
    public String uploadFileNoThumbImage(MultipartFile multipartFile) throws IOException {
        StorePath storePath = fastFileStorageClient.uploadFile(multipartFile.getInputStream(),multipartFile.getSize(), FilenameUtils.getExtension(multipartFile.getOriginalFilename()),null);
        log.info("FastDfs返回的storePath:{}",storePath);
        return storePath.getFullPath();
    }

    /**
     * 生成缩略图
     * @param multipartFile
     * @return multipartFile
     * @throws IOException
     */
    public String uploadFileWithThumbImage(MultipartFile multipartFile) throws IOException {
        StorePath storePath = fastFileStorageClient.uploadImageAndCrtThumbImage(multipartFile.getInputStream(),multipartFile.getSize(), FilenameUtils.getExtension(multipartFile.getOriginalFilename()),null);
        log.info("FastDfs返回的storePath:{}",storePath);
        return storePath.getFullPath();
    }

    public String deleteFileByFileId(String fileId) {
        String group = null ;
        String path = null;
        fastFileStorageClient.deleteFile(group,path);
        return null;
    }

}
