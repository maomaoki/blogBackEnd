package com.ym.blogBackEnd.model.vo.comment;

import com.baomidou.mybatisplus.annotation.TableField;
import com.ym.blogBackEnd.model.vo.user.UserCommentVo;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @Title: CommentVo
 * @Author YunMao
 * @Package com.ym.blogBackEnd.model.vo.comment
 * @Date 2025/2/20 21:42
 * @description: 评论返回vo
 */
@Data
public class CommentVo implements Serializable {
    /**
     * 评论主键id
     */
    private Long id;

    /**
     * 文章ID,用于关联文章
     */
    private Long articleId;

    /**
     * 评论内容
     */
    private String content;

//    /**
//     * 评论人ID
//     */
//    private Long userId;

    /**
     * 塞入 评论人 的 用户 信息
     */
    private UserCommentVo userCommentVo;


    /**
     * 是否热门评论 0未热门 1热门
     */
    private Integer isHot;

    /**
     * 是否置顶评论 0未置顶 1置顶
     */
    private Integer isSticky;


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
     * 创建时间
     */
    private Date createTime;

    @Serial
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}
