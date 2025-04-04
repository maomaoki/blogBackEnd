package com.ym.blogBackEnd.model.dto.comment;

import com.ym.blogBackEnd.common.request.PageRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Title: CommentByArticleDto
 * @Author YunMao
 * @Package com.ym.blogBackEnd.model.dto.comment
 * @Date 2025/4/4 20:04
 * @description: 关联文章分页查询评论
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class CommentByArticlePageDto extends PageRequest {

    /**
     * 文章ID,用于关联文章
     */
    private Long articleId;


}
