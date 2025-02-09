package com.ym.blogBackEnd.model.dto.picture.admin;
import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.ym.blogBackEnd.common.request.PageRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * @Title: AdminPagePictureDto
 * @Author YunMao
 * @Package com.ym.blogBackEnd.model.dto.picture.admin
 * @Date 2025/2/8 15:44
 * @description: 管理员分页查询图片列表
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class AdminPagePictureDto extends PageRequest implements Serializable {
    /**
     * id
     */
    private Long id;


    /**
     * 图片物理存放地址
     */
    private String picturePath;

    /**
     * 图片上传方式
     */
    private String pictureUpload;

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

    /**
     * 图片体积
     */
    private Long pictureSize;

    /**
     * 图片格式
     */
    private String pictureFormat;

    /**
     * 创建用户 id
     */
    private Long createUserId;

    /**
     * 审核状态 0待审核 1 审核通过 2 审核拒绝
     */
    private Integer reviewStatus;

    /**
     * 审核 开始时间
     */
    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date reviewStartTime;

    /**
     * 审核 结束时间
     */
    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date reviewEndTime;


    /**
     * 创建 开始时间
     */
    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createStartTime;

    /**
     * 创建 结束时间
     */
    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createEndTime;


    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}
