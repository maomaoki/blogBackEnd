package com.ym.blogBackEnd.model.dto.picture.admin;
import lombok.Data;

import java.io.Serializable;

/**
 * @Title: AdminEditPictureDto
 * @Author YunMao
 * @Package com.ym.blogBackEnd.model.dto.picture.admin
 * @Date 2025/2/8 15:49
 * @description: 管理员编辑图片请求类
 */
@Data
public class AdminEditPictureDto implements Serializable {
    /**
     * id
     */
    private Long id;

    /**
     * 图片名称
     */
    private String pictureName;

    /**
     * 简介
     */
    private String pictureIntroduction;

    /**
     * 分类
     */
    private String pictureCategory;

    /**
     * 标签（JSON 数组）
     */
    private String pictureTags;


    private static final long serialVersionUID = 1L;
}
