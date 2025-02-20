package com.ym.blogBackEnd.model.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
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
    @TableId(type = IdType.AUTO)
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
    private Integer isDelete;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}