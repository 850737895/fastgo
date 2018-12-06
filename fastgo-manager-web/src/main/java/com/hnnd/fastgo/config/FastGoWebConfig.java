package com.hnnd.fastgo.config;

import com.alibaba.fastjson.parser.Feature;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.github.tobato.fastdfs.service.DefaultAppendFileStorageClient;
import com.github.tobato.fastdfs.service.DefaultFastFileStorageClient;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import com.google.common.collect.Lists;
import com.hnnd.fastgo.util.FastDFSUploadUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.nio.charset.Charset;
import java.util.List;

/**
 * web 配置类
 * Created by 85073 on 2018/11/14.
 */
@Configuration
public class FastGoWebConfig extends WebMvcConfigurerAdapter {

    /**
     * 配置消息转换器
     * @param converters
     */
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        FastJsonHttpMessageConverter fastJsonHttpMessageConverter = new FastJsonHttpMessageConverter();
        fastJsonHttpMessageConverter.setDefaultCharset(Charset.forName("utf-8"));

        //支持的媒体类型
        List<MediaType> supportedMediaTypes = Lists.newArrayList();
        supportedMediaTypes.add(MediaType.APPLICATION_JSON_UTF8);
        fastJsonHttpMessageConverter.setSupportedMediaTypes(supportedMediaTypes);

        //FastJsonConfig配置
        FastJsonConfig  fastJsonConfig = new FastJsonConfig();
        fastJsonConfig.setSerializerFeatures(SerializerFeature.WriteMapNullValue,SerializerFeature.WriteDateUseDateFormat);
        fastJsonHttpMessageConverter.setFastJsonConfig(fastJsonConfig);
        converters.add(fastJsonHttpMessageConverter);
    }

}
