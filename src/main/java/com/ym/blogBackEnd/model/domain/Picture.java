package com.ym.blogBackEnd.model.domain;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 图片
 * @TableName picture
 */
@TableName(value ="picture")
@Data
public class Picture implements Serializable {
    /**
     * id
     */
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 图片 url
     */
    private String pictureUrl;

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
     * 图片宽度
     */
    private Integer pictureWidth;

    /**
     * 图片高度
     */
    private Integer pictureHeight;

    /**
     * 图片宽高比例
     */
    private Double pictureScale;

    /**
     * 图片格式
     */
    private String pictureFormat;

    /**
     * 创建用户 id
     */
    private Long createUserId;

    /**
     * 使用人 id
     */
    private Long usedUserId;

    /**
     * 审核人 id
     */
    private Long reviewUserId;

    /**
     * 审核状态 0待审核 1 审核通过 2 审核拒绝
     */
    private Integer reviewStatus;

    /**
     * 审核原因
     */
    private String reviewReason;

    /**
     * 审核时间
     */
    private Date reviewTime;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 编辑时间
     */
    private Date editTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 是否删除
     */
    @TableLogic
    private Integer isDelete;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}