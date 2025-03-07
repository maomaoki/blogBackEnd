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
     * 回复人的id(回复某个人的评论)
     */
    private Long replyUserId;

    /**
     * 祖先评论id(最高层id-用于快速删除,可能没有用)
     */
    private Long forebearId;

    /**
     * 父级评论Id(一般只有回复评论才会有)
     */
    private Long fatherId;

    /**
     * 是否热门评论 0未热门 1热门
     */
    private Integer isHot;

    /**
     * 是否置顶评论(最高层有最高层的,次级有次级,需要配合fatherId使用) 0未置顶 1置顶
     */
    private Integer isSticky;

    /**
     * 是否展示 0未展示 1展示
     */
    private Integer isShow;

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