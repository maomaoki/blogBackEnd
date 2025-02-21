package com.ym.blogBackEnd.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ym.blogBackEnd.model.domain.Comment;
import com.ym.blogBackEnd.model.dto.comment.CommentSaveDto;
import com.ym.blogBackEnd.model.vo.comment.CommentVo;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author 54621
 * @description 针对表【comment(评论表)】的数据库操作Service
 * @createDate 2025-02-20 21:26:27
 */
public interface CommentService extends IService<Comment> {


    /**
     * 用户 发表 评论
     *
     * @param commentSaveDto 保存评论 请求 dto
     * @param request        请求
     * @return 是否发表成功
     */
    Boolean userSaveComment(CommentSaveDto commentSaveDto, HttpServletRequest request);


    /**
     * 文章 评论列表
     *
     * @param articleId 文章id
     * @return 评论列表
     */
    List<CommentVo> articleCommentList(Long articleId);
}
