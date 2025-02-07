package com.ym.blogBackEnd.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


import javax.annotation.Resource;

/**
 * 配置静态资源映射
 * @author YunMao
 * @since 2025/2/7
 */
@Component
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    @Resource
    private PictureConfig pictureConfig;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/images/**").addResourceLocations(String.format("file:%s",pictureConfig.getUploadDir()));
    }
}