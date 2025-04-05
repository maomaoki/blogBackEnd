package com.ym.blogBackEnd.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ym.blogBackEnd.model.domain.Comment;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ym.blogBackEnd.model.dto.comment.CommentByArticlePageDto;
import com.ym.blogBackEnd.model.dto.comment.CommentSaveDto;
import com.ym.blogBackEnd.model.vo.comment.CommentVo;

import javax.servlet.http.HttpServletRequest;

/**
 * @author 54621
 * @description 针对表【comment(评论表)】的数据库操作Service
 * @createDate 2025-04-03 21:38:37
 */
public interface CommentService extends IService<Comment> {

    /**
     * 保存 评论
     *
     * @param saveDto 保存 评论 dto
     * @param request 请求 登录凭证
     * @return 是否保存成功
     */
    Boolean saveComment(CommentSaveDto saveDto, HttpServletRequest request);


    /**
     * 根据 文章 id 分页 查询 评论列表
     *
     * @param commentByArticlePageDto
     * @return
     */
    Page<CommentVo> pageCommentByArticleId(CommentByArticlePageDto commentByArticlePageDto);
}
