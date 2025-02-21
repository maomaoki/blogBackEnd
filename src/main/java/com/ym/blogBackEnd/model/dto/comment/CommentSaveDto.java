package com.ym.blogBackEnd.model.dto.comment;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Title: CommentSaveDto
 * @Author YunMao
 * @Package com.ym.blogBackEnd.model.dto.comment
 * @Date 2025/2/20 21:40
 * @description: 评论保存请求dto
 */
@Data
public class CommentSaveDto implements Serializable {

    /**
     * 文章ID,用于关联文章
     */
    private Long articleId;

    /**
     * 评论内容
     */
    private String content;


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


    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}
