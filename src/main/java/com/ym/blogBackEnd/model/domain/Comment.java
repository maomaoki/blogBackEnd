package com.ym.blogBackEnd.model.domain;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 评论表
 * @TableName comment
 */
@TableName(value ="comment")
@Data
public class Comment implements Serializable {
    /**
     * 评论主键id
     */
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 文章ID,用于关联文章
     */
    private Long articleId;

    /**
     * 评论内容
     */
    private String content;

    /**
     * 评论人ID
     */
    private Long userId;

    /**
     * 是否热门评论 0未热门 1热门
     */
    private Integer isHot;

    /**
     * 是否置顶评论 0未置顶 1置顶
     */
    private Integer isSticky;

    /**
     * 是否展示 0未展示 1展示
     */
    private Integer isShow;

    /**
     * 点赞数
     */
    private Integer likeNumber;

    /**
     * 回复数
     */
    private Integer replyNumber;

    /**
     * 评论定位地址
     */
    private String commentPositionAddress;

    /**
     * 评论的设备
     */
    private String commentDevice;

    /**
     * 评论的浏览器设备
     */
    private String commentBrowserDevice;

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
     * 编辑时间
     */
    private Date editTime;

    /**
     * 创建时间
     */
    private Date createTime;

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