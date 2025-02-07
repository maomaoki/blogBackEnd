package com.ym.blogBackEnd.manager.picture.impl;

import com.ym.blogBackEnd.enums.ErrorEnums;
import com.ym.blogBackEnd.exception.BusinessException;
import com.ym.blogBackEnd.manager.picture.PictureManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * @Title: LocalPictureManagerImpl
 * @Author YunMao
 * @Package com.ym.blogBackEnd.manager.picture.impl
 * @Date 2025/2/7 10:25
 * @description: 图片本地操作实现
 */
@Component
@Slf4j
@EnableAsync
public class LocalPictureManagerImpl implements PictureManager {

    /**
     * 保存文件
     *
     * @param inputSource 输入源
     * @param savePath    保存路径
     * @param fileName    文件名
     */
    @Async
    @Override
    public void uploadPicture(Object inputSource, String savePath, String fileName) {

        // 1. 校验参数
        if (inputSource == null || savePath == null || fileName == null) {
            throw new BusinessException(
                    ErrorEnums.OP_ERROR,
                    "文件参数错误!"
            );
        }

        // 2. 查看路径
        File file = new File(savePath);
        // 不存在 就 创建目录
        if (!file.exists()) {
            file.mkdirs();
        }

        // 3. 保存文件
        File saveFile;
        try {
            saveFile = new File(savePath, fileName);
            MultipartFile multipartFile = (MultipartFile) inputSource;
            multipartFile.transferTo(saveFile);
        } catch (IOException e) {
            log.error("文件保存失败!", e);
            throw new BusinessException(
                    ErrorEnums.OP_ERROR,
                    "文件保存失败!");
        }
    }
}
