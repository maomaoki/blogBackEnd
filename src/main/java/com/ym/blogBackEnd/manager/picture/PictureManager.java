package com.ym.blogBackEnd.manager.picture;

import java.io.File;

/**
 * @Title: PictureManager
 * @Author YunMao
 * @Package com.ym.blogBackEnd.manager.picture
 * @Date 2025/2/7 10:24
 * @description: 图片操作接口
 */
public interface PictureManager {

    /**
     * 保存文件
     * @param inputSource 输入源
     * @param savePath 保存路径
     * @param fileName 文件名
     */
    void uploadPicture(Object inputSource,String savePath , String fileName);

}
