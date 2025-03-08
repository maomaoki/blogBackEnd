package com.ym.blogBackEnd.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Title: PictureConfig
 * @Author YunMao
 * @Package com.ym.blogBackEnd.config
 * @Date 2025/2/7 10:17
 * @description: 图片配置
 */
@Component
@Data
@ConfigurationProperties(prefix = "ym.picture")
public class PictureConfig {

    /**
     * 图片上传父级目录
     */
    private String uploadDir;

    /**
     * 头像上传目录
     */
    private String avatarDir;

    /**
     * 博客图片上传目录
     */
    private String blogDir;

    /**
     * 其他图片上传目录
     */
    private String otherDir;

    /**
     * BANNER图片上传目录
     */
    private String bannerDir;


    /**
     * 图片上传最大大小（MB）
     */
    private int maxFileSize;

    /**
     *  允许上传的文件类型
     */
    private List<String> allowedFileTypes;

    /**
     * 图片访问前缀
     */
    private String httpUrl;

}
